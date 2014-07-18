package com.pixelutilitys.arcade.emulators.AEPgb;

/**
 * this source file released under the GNU Public Licence.
 * see the accompanying copyright.txt for more information
 * Copyright (C) 2000-2001 Ben Mazur
 */

import java.awt.Button;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * PgbNetplay is contains the Pgb network client and server
 * code.  It is responsible for handling the serial and the
 * I/R port.
 * 
 * transmission codes:
 * 
 * 0x10, n, ... = name transmission, len, byte[...]
 * 0x11, n, ... = emulator transmission, len, byte[...]
 * 0x12, n, ... = version transmission, len, byte[...]
 * 0x02 = IR transmission, 1 or 0
 * 0x03 = serial transmission, serial data
 * 
 * 
 * Currently, connecting causes what seems like way too many
 * serial interrupts.  Weird.
 */
public class PgbNetplay	implements ActionListener {
	
	boolean					connected;
	Socket					socket;
	
	PgbConnectionStatus		pcs;
	
	byte					serialData;
	byte					serialControl;
	
	byte					lastSerialData;
	boolean					newSerialData;
	boolean					waitingOnSerial;
	int						serialCount;
	int						serialRetryCount;
	
	boolean					irTransmit;
	boolean					irReceive;
	boolean					irReadOn;
	
	public void PgbNetPlay() {
		socket = null;
		connected = false;
		
		serialCount = 0;
		serialControl = 0;
		
		irReadOn = false;
	}
	
	/**
	 * how many cycles until the next possible interrupt?
	 */
	public int cyclesLeft() {
		if(!waitingOnSerial) {
			// if we're not waiting on the serial port, then
			// we're not going to interrupt
			return Integer.MAX_VALUE;
		}
		return serialCount;
	}
	
	public byte cycle(int cv) {
		// check the serial port
		if(waitingOnSerial) {
			// if we're waiting, decrement the count
			serialCount -= cv;
			if(serialCount <= 0) {
				// when the count is reached,
				doInput();
				if(newSerialData) {
					// yay, new data!  return it.
					serialData = lastSerialData;
					newSerialData = false;
					serialControl = 0;
					waitingOnSerial = false;
					if(connected) {
						pcs.setStatus("read serial: " + serialData);
					}
					return PgbMemory.INT_SERIAL;
				} else {
					// no new data, retry or fail
					if(serialRetryCount-- > 0) {
						// retry in a lil' bit.
						serialCount = 122 * 8 * 4 * 5;
					} else {
						// return last byte, I guess
						serialData = lastSerialData;
						newSerialData = false;
						serialControl = 0;
						waitingOnSerial = false;
						if(connected) {
							pcs.setStatus("read serial: " + serialData);
						}
						return PgbMemory.INT_SERIAL;
					}
				}
			}
		}
		return 0;
	}
	
	public void setSerialData(byte data) {
		//System.out.println("serial set:" + (data & 0xFF));
		serialData = data;
	}
	public byte getSerialData() {
		//System.out.println("serial returned:" + (serialData & 0xFF));
		return serialData;
	}
	
	public void setSerialControl(byte control) {
		serialControl = control;
		if((serialControl & 0x80) == 0x80) {
			// transfer data
			if((serialControl & 0x01) == 0x01 || connected) {
				// internal clock or connected, wait for a
				// the count, then check and interrupt
				waitingOnSerial = true;
				serialCount = 122 * 8 * 4;
				serialRetryCount = 2;
			} else {
				// external and not connected = ignore
				waitingOnSerial = false;
			}
			if(connected) {
				// write out the data
				try {
					socket.setTcpNoDelay(true);
					OutputStream os = socket.getOutputStream();
					os.write(0x03);
					os.write(serialData & 0xFF);
					pcs.setStatus("wrote serial: " + serialData);
				} catch(IOException ex) {
					//System.out.println("couldn't write");
				}
			}
		} else {
			waitingOnSerial = false;
		}
	}
	public byte getSerialControl() {
		return serialControl;
	}
	
	public void setIR(byte control) {
		//System.out.println("write to GBC IR port:" + Integer.toHexString(control & 0xFF));
		irTransmit = (control & 0x01) == 0x01;
		irReadOn = (control & 0xC0) == 0xC0;
		if(connected) {
			try {
				socket.setTcpNoDelay(true);
				OutputStream os = socket.getOutputStream();
				os.write(0x02);
				os.write(irTransmit ? 1 : 0);
				pcs.setStatus("wrote I/R: " + irTransmit);
			} catch(IOException ex) {
				//System.out.println("couldn't write");
			}
		}
	}
	public byte getIR() {
		if(irReadOn) {
			doInput();
		}
		//System.out.println("read from GBC IR port: " + Integer.toHexString(irReceive ? 0xC2 : 0xC0));
		return irReceive ? (byte)0xC2 : (byte)0xC0;
	}
	
	public void doInput() {
		int		input;
		int		len;
		byte[]	data;
		if(connected) {
			try {
				// don't block for long if no data
				socket.setTcpNoDelay(true);
				socket.setSoTimeout(1);
				InputStream is = socket.getInputStream();
				while(true) { // until throw...
					input = is.read();
					//System.out.println("sio read:" + input);
					switch(input) {
					case 0x10:
						// game
						len = is.read();
						data = new byte[len];
						is.read(data);
						pcs.setGame(new String(data));
						break;
					case 0x11:
						// emulator
						len = is.read();
						data = new byte[len];
						is.read(data);
						pcs.setGame(new String(data));
						break;
					case 0x12:
						// version
						len = is.read();
						data = new byte[len];
						is.read(data);
						pcs.setGame(new String(data));
						break;
					case 0x02:
						// I/R
						irReceive = is.read() != 0;
						pcs.setStatus("read I/R: " + irReceive);
						break;
					case 0x03:
						// serial
						lastSerialData = (byte)is.read();
						newSerialData = true;
						break;
					}
				}
			} catch(IOException ex) {
				//System.out.println("couldn't read");
			}
		} else {
			// send disconnected signal to GB
			lastSerialData = (byte)0xFF;
			newSerialData = true;
		}
	}
	
	public void sendInfo() {
		if(connected) {
			try {
				socket.setTcpNoDelay(true);
				OutputStream os = socket.getOutputStream();
				os.write(0x10);
				os.write(PgbSettings.gamestring.length());
				os.write(PgbSettings.gamestring.getBytes());
			} catch(IOException ex) {
				//System.out.println("couldn't write");
			}
		}
	}
	
	public void popNetDialog(Frame frame) {
		PgbNetDialog nd = new PgbNetDialog(frame);
		
		nd.setLocation(frame.getLocation().x - 60, frame.getLocation().y + 80);
		nd.setVisible(true);
	
		if(nd.socket == null) {
			connected = false;
			return;
		}
		
		socket = nd.socket;
		connected = true;
		pcs = new PgbConnectionStatus(frame, this, socket);
		pcs.setLocation(frame.getLocation().x - 310, frame.getLocation().y);
		pcs.setVisible(true);
	}
	
	public void disconnect() {
		if(connected) {
			connected = false;
			try {
				socket.close();
			} catch(Exception ex) {
				// duh...
			}
			pcs.setVisible(false);
			pcs.dispose();
		}
	}
	
	public void actionPerformed(ActionEvent ev) {
		if(ev.getActionCommand().equals("disconnect")) {
			disconnect();
		}
		if(ev.getActionCommand().equals("close")) {
			pcs.setVisible(false);
			pcs.dispose();
		}
	}
}

/**
 * PgbConnectionStatus is a non-modal dialog box displaying
 * the netplay connection status.
 * 
 * It is created by PgbNetplay when a connection is 
 * established and disposed of when the connection is
 * closed.
 * 
 * It has no real control over the connection socket and is
 * entirely manipulated from within PgbNetplay.
 */
class PgbConnectionStatus extends Dialog {
	String					game, emulator, version;
	
	Label					connectedTo, playing, status;
	TextField				addressField, gameField, statusField;
	Button					disconnect;
	
	public PgbConnectionStatus(Frame frame, ActionListener al, Socket socket) {
		super(frame, "Pgb Connection Status", false);
		
		setSize(300, 150);
		setResizable(false);

		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		setLayout(gridbag);
		
		c.insets = new Insets(2, 10, 2, 10);

		connectedTo = new Label("Connected To:");
		gridbag.setConstraints(connectedTo, c);
		add(connectedTo);
		
		addressField = new TextField(socket.getInetAddress().getHostName(), 20);
		addressField.setEditable(false);
		c.gridwidth = GridBagConstraints.REMAINDER;		
		gridbag.setConstraints(addressField, c);
		add(addressField);
		
		playing = new Label("Playing:");
		c.gridwidth = 1;		
		gridbag.setConstraints(playing, c);
		add(playing);
		
		gameField = new TextField("", 20);
		gameField.setEditable(false);
		c.gridwidth = GridBagConstraints.REMAINDER;		
		gridbag.setConstraints(gameField, c);
		add(gameField);

		status = new Label("Status:");
		c.gridwidth = 1;		
		gridbag.setConstraints(status, c);
		add(status);
		
		statusField = new TextField("", 20);
		statusField.setEditable(false);
		c.gridwidth = GridBagConstraints.REMAINDER;		
		gridbag.setConstraints(statusField, c);
		add(statusField);

		disconnect = new Button("Disconnect");
		disconnect.setActionCommand("disconnect");
		disconnect.addActionListener(al);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = GridBagConstraints.REMAINDER;		
		gridbag.setConstraints(disconnect, c);
		add(disconnect);
	}
	
	public void setStatus(String newstatus) {
		statusField.setText(newstatus);
	}
	
	public void setGame(String game) {
		this.game = game;
		gameField.setText(game);
	}
	
	public void setEmulator(String emulator) {
		this.emulator = emulator;
		gameField.setText(game);
	}
	
	public void setVersion(String version) {
		this.version = version;
		gameField.setText(game);
	}
	
	public void disconnected() {
		addressField.setText("DISCONNECTED");
		disconnect.setLabel("Close");
		disconnect.setActionCommand("close");
	}
}

/**
 * PgbNetDialog is a modal dialog box responsible for
 * estabishling a remote connection.
 * 
 * When the dialog is closed, socket should be either a
 * connected socket if one was established, or null if 
 * it failed.
 */
class PgbNetDialog extends Dialog implements ActionListener{
	
	Frame					frame;
	
	TextField				status;
	Button					listen, connect, cancel;
	
	public Socket			socket;
	
	public PgbNetDialog(Frame frame) {
		super(frame, "Pgb Netplay", true);
		this.frame = frame;
		
		setSize(250, 90);
		setResizable(false);

		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		setLayout(gridbag);
		
		c.insets = new Insets(5, 10, 5, 10);

		status = new TextField("", 30);
		status.setEditable(false);
		c.gridwidth = GridBagConstraints.REMAINDER;		
		gridbag.setConstraints(status, c);
		add(status);
		
		listen = new Button("Listen");
		listen.setActionCommand("listen");
		listen.addActionListener(this);
		c.gridwidth = 1;		
		gridbag.setConstraints(listen, c);
		add(listen);
		
		connect = new Button("Connect...");
		connect.setActionCommand("connect");
		connect.addActionListener(this);
		gridbag.setConstraints(connect, c);
		add(connect);
		
		cancel = new Button("Cancel");
		cancel.setActionCommand("cancel");
		cancel.addActionListener(this);
		c.gridwidth = GridBagConstraints.REMAINDER;		
		gridbag.setConstraints(cancel, c);
		add(cancel);
		
	}
	
	public void listen() {
		ServerSocket ss;
		
		status.setText("Listening...");
		try{
			ss = new ServerSocket(PgbSettings.netport);
			ss.setSoTimeout(PgbSettings.netlistentimeout);
			
			socket = ss.accept();
			
			status.setText("Listening... connected to " + socket.getInetAddress().getHostName());
			setVisible(false);
		} catch(Exception e) {
			status.setText("Listening... timed out");
			socket = null;
			return;
		}
	}
	
	public void connect() {
		PgbConnectDialog cd = new PgbConnectDialog(frame);
		String addressString;
		InetAddress inetAddress;
		ServerSocket	ss;
		
		cd.setLocation(this.getLocation().x + 30, this.getLocation().y + 30);
		cd.setVisible(true);

		addressString = cd.address;
		if(addressString.length() < 1) {
			return;
		}
		
		try {
			inetAddress = InetAddress.getByName(addressString);
		} catch(UnknownHostException ex) {
			status.setText("Could not resolve connection address.");
			return;
		}
		status.setText("Connecting to " + addressString + "...");
		
		try {
			socket = new Socket(inetAddress, PgbSettings.netport);

			status.setText("Connected to " + addressString + ".");
			setVisible(false);
		} catch(Exception ex) {
			status.setText("Connecting to " + addressString + "... timed out.");
			socket = null;
			return;
		}
		PgbSettings.lastnetaddress = addressString;
	}
	
	public void actionPerformed(ActionEvent ev) {
		if(ev.getActionCommand().equals("listen")) {
			listen();
		}
		if(ev.getActionCommand().equals("connect")) {
			connect();
		}
		if(ev.getActionCommand().equals("cancel")) {
			setVisible(false);
		}
	}
}

/**
 * PgbConnectDialog is a modal dialog to enter the address to
 * connect to.
 */
class PgbConnectDialog extends Dialog implements ActionListener {
	
	public String		address = "";
	
	Label				connectTo;
	TextField			addressField;
	Button				okay, cancel;
	
	public PgbConnectDialog(Frame frame) {
		super(frame, "Connect to...", true);

		setSize(250, 90);
		setResizable(false);

		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		setLayout(gridbag);
		
		c.insets = new Insets(5, 10, 5, 10);

		connectTo = new Label("Connect To:");
		gridbag.setConstraints(connectTo, c);
		add(connectTo);
		
		okay = new Button("Okay");
		okay.setActionCommand("okay");
		okay.addActionListener(this);
		c.gridwidth = GridBagConstraints.REMAINDER;		
		gridbag.setConstraints(okay, c);
		add(okay);
		
		addressField = new TextField(PgbSettings.lastnetaddress, 20);
		c.gridwidth = 1;		
		gridbag.setConstraints(addressField, c);
		add(addressField);
		
		cancel = new Button("Cancel");
		cancel.setActionCommand("cancel");
		cancel.addActionListener(this);
		c.gridwidth = GridBagConstraints.REMAINDER;		
		gridbag.setConstraints(cancel, c);
		add(cancel);
	}
	
	public void actionPerformed(ActionEvent ev) {
		if(ev.getActionCommand().equals("okay")) {
			address = addressField.getText();
			setVisible(false);
		}
		if(ev.getActionCommand().equals("cancel")) {
			setVisible(false);
		}
	}
}