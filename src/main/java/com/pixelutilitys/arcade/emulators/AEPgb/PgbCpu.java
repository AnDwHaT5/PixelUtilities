package com.pixelutilitys.arcade.emulators.AEPgb;
/**
 * this source file released under the GNU Public Licence.
 * see the accompanying copyright.txt for more information
 * Copyright (C) 2000-2001 Ben Mazur
 * modified by retroK, XTale and baka0815 2004 http://aepgb.aep-emu.de/
 */

/**
 * PgbCpu keeps the state of the CPU registers and executes
 * the current opcode.
 * 
 * It is a closed-box type of architecture for performance
 * purposes.  Sorry for all the private final methods...
 * 
 * The exec() method executes the current opcode and 
 * increases the program counter.  exec() also calls the 
 * memory cycle() function and processes any resulting 
 * interrupts.
 */
public final class PgbCpu {
	private final int				HALTON = -1;//0x0150;
	
	final static int				Z_FLAG = 0x80;       /* 1: Result is zero          */
	final static int				N_FLAG = 0x40;       /* 1: Subtraction occured     */
	final static int				H_FLAG = 0x20;       /* 1: Halfcarry/Halfborrow    */
	final static int				C_FLAG = 0x10;       /* 1: Carry/Borrow occured    */
	
	PgbMemory						mem;
	
	// registers
	private PgbRegister				BC;
	private PgbRegister				DE;
	private PgbRegister				HL;
	private PgbRegisterW			SP;
	private PgbRegisterW			PC;
	private PgbRegisterB			AF;
	
	private boolean					ime; // interrupt master enable
	
	// by retroK	
	SoundChip soundChip;

	
	public PgbCpu(PgbMemory mem) {
		this.mem = mem;
		//initOpcodes();
	}
	
	public void reset() {
		AF = new PgbRegisterB(0x0180);
		if(PgbSettings.system == PgbSettings.SYS_GBP) {
			AF = new PgbRegisterB(0xFF80);
		}
		if(PgbSettings.system == PgbSettings.SYS_GBC) {
			AF = new PgbRegisterB(0x1180);
		}
		BC = new PgbRegisterB(0x0013);
		DE = new PgbRegisterB(0x00D8);
		HL = new PgbRegisterW(0x014D);
		SP = new PgbRegisterW(0xFFFE);
		PC = new PgbRegisterW(0x0100);
		
		ime = false;
		
		PgbSettings.clockspeed = 4.194304;
	}
	
	public final void exec(int cte) {
		int cv;
		
		/*
		// debug halt
		if(PgbSettings.DEBUG) {
			if(PC.data == HALTON) {
				PgbSettings.paused = true;
				System.out.println("PC hit HALTON:" + Integer.toHexString(HALTON));
			}
		}
		*/
		
		
		/*
		if(PgbSettings.debuglevel == 5) {
			System.out.print("op:" + Integer.toHexString(opcode) + " pc:" + Integer.toHexString(PC.getR() - 1) + " af:" + Integer.toHexString(AF.getR()) + " bc:" + Integer.toHexString(BC.getR()) + " de:" + Integer.toHexString(DE.getR()) + " hl:" + Integer.toHexString(HL.getR()) + " sp:" + Integer.toHexString(SP.getR()) + " ");
			System.out.println("LCD y:" + Integer.toHexString(mem.video.ly) + " IF:" + Integer.toBinaryString(mem.IF));
		}
		*/
		
		while(cte > 0) {
			// process interrupts
			if(ime && (mem.IF & mem.IE) != 0) {
				interrupt(mem.IF & mem.IE);
			}
			

			int memread = (int)(mem.read(PC.data++) & 0xFF);
			cv = 4;
			
			//System.out.println("memread: 0x" + Integer.toHexString(memread));
			switch (memread) {
				case 0x00: // NOP
					cv = 4;
					break;
				case 0x01: // LD   BC,nnnn
					BC.setR(readWord());
					cv = 20;
					break;
				case 0x02: // LD   (BC),A
					mem.write(BC.getR(), AF.getH());
					cv = 8;
					break;
				case 0x03: // INC  BC
					incR(BC);
					cv = 8;
					break;
				case 0x04: // INC  B
					incH(BC);
					cv = 4;
					break;
				case 0x05: // DEC  B
					decH(BC);
					cv = 4;
					break;
				case 0x06: // LD   B,nn
					BC.setH(readByte());
					cv = 8;
					break;
				case 0x07: // RLCA
					rlcH(AF);
					setZ(false);
					cv = 4;
					break;
				case 0x08: // LD   (nnnn),SP
					mem.writeWord(readWord(), SP.getR());
					cv = 20;
					break;
				case 0x09: // LD   (nnnn),SP
					addR(HL, BC);
					cv = 12;
					break;
				case 0x0A: // LD   A,(BC)
					AF.setH(BC.getI(mem));
					cv = 8;
					break;
				case 0x0B: // DEC  BC
					decR(BC);
					cv = 8;
					break;
				case 0x0C: // INC  C
					incL(BC);
					cv = 4;
					break;
				case 0x0D: // DEC C
					decL(BC);
					cv = 4;
					break;
				case 0x0E: // LD   C,nn
					BC.setL(readByte());
					cv = 8;
					break;
				case 0x0F: // RRCA
					rrcH(AF);
					setZ(false);
					cv = 4;
					break;
				case 0x10: // STOP
					readByte();
					if(PgbSettings.system == PgbSettings.SYS_GBC) {
						// change speed
						if((mem.gbcGetSpeed() & 0x01) == 0x01) {
							PgbSettings.clockspeed = 8.388;
							mem.gbcSetSpeed((byte)0x80);
						} else {
							PgbSettings.clockspeed = 4.194;
							mem.gbcSetSpeed((byte)0x00);
						}
						//System.out.println("speed change : " + PgbSettings.clockspeed);
					} else {
						unsupported(0x10);
					}
					cv = 4;
					break;
				case 0x11: // LD   DE,nnnn
					DE.setR(readWord());
					cv = 12;
					break;
				case 0x12: // LD   (DE),A
					DE.setI(AF.getH(), mem);
					cv = 8;
					break;
				case 0x13: // INC  DE
					incR(DE);
					cv = 8;
					break;
				case 0x14: // INC  D
					incH(DE);
					cv = 4;
					break;
				case 0x15: // DEC  D
					decH(DE);
					cv = 4;
					break;
				case 0x16: // LD   D,nn
					DE.setH(readByte());
					cv = 8;
					break;
				case 0x17: // RLA
					rlH(AF);
					setZ(false);
					cv = 4;
					break;
				case 0x18: // JR   disp
					jr(readByte());
					cv = 12;
					break;
				case 0x19: // ADD  HL,DE
					addR(HL, DE);
					cv = 16;
					break;
				case 0x1A: // LD   A,(DE)
					AF.setH(DE.getI(mem));
					cv = 8;
					break;
				case 0x1B: // DEC  DE
					decR(DE);
					cv = 8; 
					break;
				case 0x1C: // INC  E
					incL(DE);
					cv = 4;
					break;
				case 0x1D: // DEC  E
					decL(DE);
					cv = 4;
					break;
				case 0x1E: // LD   E,nn
					DE.setL(readByte());
					cv = 8;
					break;
				case 0x1F: // RRA
					rrH(AF);
					setZ(false);
					cv = 4;
					break;
				case 0x20: // JR   NZ,disp
					if(!getZ()) {
						jr(readByte());
						cv = 12;
					} else {
						readByte(); // and NOP it!
						cv = 8;
					}
					break;
				case 0x21: // LD   HL,nnnn
					HL.setR(readWord());
					cv = 12;
					break;
				case 0x22: // LDI  (HL),A
					HL.setI(AF.getH(), mem);
					incR(HL);
					cv = 16;
					break;
				case 0x23: // INC  HL
					incR(HL);
					cv = 8;
					break;
				case 0x24: // INC  H
					incH(HL);
					cv = 4;
					break;
				case 0x25: // DEC  H
					decH(HL);
					cv = 4;
					break;
				case 0x26: // LD   H,nn
					HL.setH(readByte());
					cv = 8;
					break;
				case 0x27: // DAA
					daa();
					cv = 4;
					break;
				case 0x28: // JR   Z,disp
					if(getZ()) {
						jr(readByte());
						cv = 12;
					} else {
						readByte(); // and NOP it!
						cv = 8;
					}
					break;
				case 0x29: // ADD  HL,HL
					addR(HL, HL);
					cv = 12;
					break;
				case 0x2A: // LDI  A,(HL)
					AF.setH(HL.getI(mem));
					incR(HL);
					cv = 16;
					break;
				case 0x2B: // DEC HL
					decR(HL);
					cv = 8;
					break;
				case 0x2C: // INC  L
					incL(HL);
					cv = 4;
					break;
				case 0x2D: // DEC  L
					decL(HL);
					cv = 4;
					break;
				case 0x2E: // LD   L,nn
					HL.setL(readByte());
					cv = 8;
					break;
				case 0x2F: // CPL
					cpl();
					cv = 4;
					break;
				case 0x30: // JR   NC,disp  
					if(!getC()) {
						jr(readByte());
						cv = 12;
					} else {
						readByte(); // and NOP it!
						cv = 8;
					}
					break;
				case 0x31: // LD   SP,nnnn
					SP.setR(readWord());
					cv = 12;
					break;
				case 0x32: // LDD  (HL),A
					HL.setI(AF.getH(), mem);
					decR(HL);
					cv = 16;
					break;
				case 0x33: // INC  SP
					incR(SP);
					cv = 8;
					break;
				case 0x34: // INC  (HL)
					incI(HL);
					cv = 12;
					break;
				case 0x35: // DEC  (HL)
					decI(HL);
					cv = 12;
					break;
				case 0x36: // LD   (HL),nn
					HL.setI(readByte(), mem);
					cv = 12;
					break;
				case 0x37: // SCF
					setC(true);
					cv = 4;
					break;
				case 0x38: // JR   C,disp
					if(getC()) {
						jr(readByte());
						cv = 12;
					} else {
						readByte(); // and NOP it!
						cv = 8;
					}
					break;
				case 0x39: // ADD  HL,SP
					addR(HL, SP);
					cv = 12;
					break;
				case 0x3A: // LDD  A,(HL)
					AF.setH(HL.getI(mem));
					decR(HL);
					cv = 16;
					break;
				case 0x3B: // DEC  SP
					decR(SP);
					cv = 8;
					break;
				case 0x3C: // INC  A
					incH(AF);
					cv = 4;
					break;
				case 0x3D: // DEC  A
					decH(AF);
					cv = 4;
					break;
				case 0x3E: // LD   A,nn
					AF.setH(readByte());
					cv = 8;
					break;
				case 0x3F: // CCF
					setC(!getC());
					cv = 4;
					break;
				case 0x40: // LD   B,B
					//XXX senseless (B -> B)
					//BC.setH(BC.getH());
					cv = 4;
					break;
				case 0x41: // LD   B,C
					BC.setH(BC.getL());
					cv = 4;
					break;
				case 0x42: // LD   B,D
					BC.setH(DE.getH());
					cv = 4;
					break;
				case 0x43: // LD   B,E
					BC.setH(DE.getL());
					cv = 4;
					break;
				case 0x44: // LD   B,H
					BC.setH(HL.getH());
					cv = 4;
					break;
				case 0x45: // LD   B,L
					BC.setH(HL.getL());
					cv = 4;
					break;
				case 0x46: // LD   B,(HL)
					BC.setH(HL.getI(mem));
					cv = 8;
					break;
				case 0x47: // LD   B,A
					BC.setH(AF.getH());
					cv = 4;
					break;
				case 0x48: // LD   C,B
					BC.setL(BC.getH());
					cv = 4;
					break;
				case 0x49: // LD   C,C
					//XXX senseless (C -> C)
					//BC.setL(BC.getL());
					cv = 4;
					break;
				case 0x4A: // LD   C,D
					BC.setL(DE.getH());
					cv = 4;
					break;
				case 0x4B: // LD   C,E
					BC.setL(DE.getL());
					cv = 4;
					break;
				case 0x4C: // LD   C,H
					BC.setL(HL.getH());
					cv = 4;
					break;
				case 0x4D: // LD   C,L
					BC.setL(HL.getL());
					cv = 4;
					break;
				case 0x4E: // LD   C,(HL)
					BC.setL(HL.getI(mem));
					cv = 8;
					break;
				case 0x4F: // LD   C,A
					BC.setL(AF.getH());
					cv = 4;
					break;
				case 0x50: // LD   D,B
					DE.setH(BC.getH());
					cv = 4;
					break;
				case 0x51: // LD   D,C
					DE.setH(BC.getL());
					cv = 4;
					break;
				case 0x52: // LD   D,D
					//XXX senseless (D -> D)
					//DE.setH(DE.getH());
					cv = 4;
					break;
				case 0x53: // LD   D,E
					DE.setH(DE.getL());
					cv = 4;
					break;
				case 0x54: // LD   D,H
					DE.setH(HL.getH());
					cv = 4;
					break;
				case 0x55: // LD   D,L
					DE.setH(HL.getL());
					cv = 4;
					break;
				case 0x56: // LD   D,(HL)
					DE.setH(HL.getI(mem));
					cv = 8;
					break;
				case 0x57: // LD   D,A
					DE.setH(AF.getH());
					cv = 4;
					break;
				case 0x58: // LD   E,B
					DE.setL(BC.getH());
					cv = 4;
					break;
				case 0x59: // LD   E,C
					DE.setL(BC.getL());
					cv = 4;
					break;
				case 0x5A: // LD   E,D
					DE.setL(DE.getH());
					cv = 4;
					break;
				case 0x5B: // LD   E,E
					//XXX senseless (E -> E)
					//DE.setL(DE.getL());
					cv = 4;
					break;
				case 0x5C: // LD   E,H
					DE.setL(HL.getH());
					cv = 4;
					break;
				case 0x5D: // LD   E,L
					DE.setL(HL.getL());
					cv = 4;
					break;
				case 0x5E: // LD   E,(HL)
					DE.setL(HL.getI(mem));
					cv = 8;
					break;
				case 0x5F: // LD   E,A
					DE.setL(AF.getH());
					cv = 4;
					break;
				case 0x60: // LD   H,B
					HL.setH(BC.getH());
					cv = 4;
					break;
				case 0x61: // LD   H,C
					HL.setH(BC.getL());
					cv = 4;
					break;
				case 0x62: // LD   H,D
					HL.setH(DE.getH());
					cv = 4;
					break;
				case 0x63: // LD   H,E
					HL.setH(DE.getL());
					cv = 4;
					break;
				case 0x64: // LD   H,H
					//XXX senseless (H -> H)
					//HL.setH(HL.getH()); 
					cv = 4;
					break;
				case 0x65: // LD   H,L
					HL.setH(HL.getL());
					cv = 4;
					break;
				case 0x66: // LD   H,(HL)
					HL.setH(HL.getI(mem));
					cv = 8;
					break;
				case 0x67: // LD   H,A
					HL.setH(AF.getH());
					cv = 4;
					break;
				case 0x68: // LD   L,B
					HL.setL(BC.getH());
					cv = 4;
					break;
				case 0x69: // LD   L,C
					HL.setL(BC.getL());
					cv = 4;
					break;
				case 0x6A: // LD   L,D
					HL.setL(DE.getH());
					cv = 4;
					break;
				case 0x6B: // LD   L,E
					HL.setL(DE.getL());
					cv = 4;
					break;
				case 0x6C: // LD   L,H
					HL.setL(HL.getH());
					cv = 4;
					break;
				case 0x6D: // LD   L,L
					//XXX senseless (L -> L)
					//HL.setL(HL.getL());
					cv = 4;
					break;
				case 0x6E: // LD   L,(HL)
					HL.setL(HL.getI(mem));
					cv = 8;
					break;
				case 0x6F: // LD   L,A
					HL.setL(AF.getH());
					cv = 4;
					break;
				case 0x70: // LD   (HL),B
					mem.write(HL.getR(), BC.getH());
					cv = 8;
					break;
				case 0x71: // LD   (HL),C
					mem.write(HL.getR(), BC.getL());
					cv = 8;
					break;
				case 0x72: // LD   (HL),D
					mem.write(HL.getR(), DE.getH());
					cv = 8;
					break;
				case 0x73: // LD   (HL),E
					mem.write(HL.getR(), DE.getL());
					cv = 8;
					break;
				case 0x74: // LD   (HL),H
					mem.write(HL.getR(), HL.getH());
					cv = 8;
					break;
				case 0x75: // LD   (HL),L
					mem.write(HL.getR(), HL.getL());
					cv = 8;
					break;
				case 0x76: // HALT
					if(ime) {
						//decR(PC);
						//System.out.println("cpu HALTed.");
						mem.recalcCyclesLeft();
						cv = mem.cyclesLeft;
					} else {
						//System.out.println("cpu not HALTed because IME=" + ime + " and IE=" + mem.IE);
						cv = 4;
					}
					break;
				case 0x77: // LD   (HL),A
					mem.write(HL.getR(), AF.getH());
					cv = 8;
					break;
				case 0x78: // LD   A,B
					AF.setH(BC.getH());
					cv = 4;
					break;
				case 0x79: // LD   A,C
					AF.setH(BC.getL());
					cv = 4;
					break;
				case 0x7A: // LD   A,D
					AF.setH(DE.getH());
					cv = 4;
					break;
				case 0x7B: // LD   A,E
					AF.setH(DE.getL());
					cv = 4;
					break;
				case 0x7C: // LD   A,H
					AF.setH(HL.getH());
					cv = 4;
					break;
				case 0x7D: // LD   A,L
					AF.setH(HL.getL());
					cv = 4;
					break;
				case 0x7E: // LD   A,(HL)
					AF.setH(HL.getI(mem));
					cv = 8;
					break;
				case 0x7F: // LD   A,A
					//XXX senseless (A -> A)
					//AF.setH(AF.getH()); 
					cv = 4;
					break;
				case 0x80: // ADD  A,B
					add(BC.getH());
					cv = 4;
					break;
				case 0x81: // ADD  A,C
					add(BC.getL());
					cv = 4;
					break;
				case 0x82: // ADD  A,D
					add(DE.getH());
					cv = 4;
					break;
				case 0x83: // ADD  A,E
					add(DE.getL());
					cv = 4;
					break;
				case 0x84: // ADD  A,H
					add(HL.getH());
					cv = 4;
					break;
				case 0x85: // ADD  A,L
					add(HL.getL());
					cv = 4;
					break;
				case 0x86: // ADD  A,(HL)
					add(HL.getI(mem));
					cv = 8; 
					break;
				case 0x87: // ADD  A,A
					add(AF.getH());
					cv = 4;
					break;
				case 0x88: // ADC  A,B
					adc(BC.getH());
					cv = 4;
					break;
				case 0x89: // ADC  A,C
					adc(BC.getL());
					cv = 4;
					break;
				case 0x8A: // ADC  A,D
					adc(DE.getH());
					cv = 4;
					break;
				case 0x8B: // ADC  A,E
					adc(DE.getL());
					cv = 4;
					break;
				case 0x8C: // ADC  A,H
					adc(HL.getH());
					cv = 4;
					break;
				case 0x8D: // ADC  A,L
					adc(HL.getL());
					cv = 4;
					break;
				case 0x8E: // ADC  A,(HL)
					adc(HL.getI(mem));
					cv = 8; 
					break;
				case 0x8F: // ADC A,A
					adc(AF.getH());
					cv = 4;
					break;
				case 0x90: // SUB  B
					sub(BC.getH());
					cv = 4;
					break;
				case 0x91: // SUB  C
					sub(BC.getL());
					cv = 4;
					break;
				case 0x92: // SUB  D
					sub(DE.getH());
					cv = 4;
					break;
				case 0x93: // SUB  E
					sub(DE.getL());
					cv = 4;
					break;
				case 0x94: // SUB  H
					sub(HL.getH());
					cv = 4;
					break;
				case 0x95: // SUB  L
					sub(HL.getL());
					cv = 4;
					break;
				case 0x96: // SUB  (HL)
					sub(HL.getI(mem));
					cv = 8;
					break;
				case 0x97: // SUB  A
					sub(AF.getH());
					cv = 4;
					break;
				case 0x98: // SBC  A,B
					sbc(BC.getH());
					cv = 4;
					break;
				case 0x99: // SBC  A,C
					sbc(BC.getL());
					cv = 4;
					break;
				case 0x9A: // SBC  A,D
					sbc(DE.getH());
					cv = 4;
					break;
				case 0x9B: // SBC  A,E
					sbc(DE.getL());
					cv = 4;
					break;
				case 0x9C: // SBC  A,H
					sbc(HL.getH());
					cv = 4;
					break;
				case 0x9D: // SBC  A,L
					sbc(HL.getL());
					cv = 4;
					break;
				case 0x9E: // SBC  A,(HL)
					sbc(HL.getI(mem));
					cv = 8;
					break;
				case 0x9F: // SBC  A,A
					sbc(AF.getH());
					cv = 4;
					break;
				case 0xA0: // AND  B
					and(BC.getH());
					cv = 4;
					break;
				case 0xA1: // AND  C
					and(BC.getL());
					cv = 4;
					break;
				case 0xA2: // AND  D
					and(DE.getH());
					cv = 4;
					break;
				case 0xA3: // AND  E
					and(DE.getL());
					cv = 4;
					break;
				case 0xA4: // AND  H
					and(HL.getH());
					cv = 4;
					break;
				case 0xA5: // AND  L
					and(HL.getL());
					cv = 4;
					break;
				case 0xA6: // AND  (HL)
					and(HL.getI(mem));
					cv = 8;
					break;
				case 0xA7: // AND  A
					and(AF.getH());
					cv = 4;
					break;
				case 0xA8: // XOR  B
					xor(BC.getH());
					cv = 4;
					break;
				case 0xA9: // XOR  C
					xor(BC.getL());
					cv = 4;
					break;
				case 0xAA: // XOR  D
					xor(DE.getH());
					cv = 4;
					break;
				case 0xAB: // XOR  E
					xor(DE.getL());
					cv = 4;
					break;
				case 0xAC: // XOR  H
					xor(HL.getH());
					cv = 4;
					break;
				case 0xAD: // XOR  L
					xor(HL.getL());
					cv = 4;
					break;
				case 0xAE: // XOR  (HL)
					xor(HL.getI(mem));
					cv = 8;
					break;
				case 0xAF: // XOR  A
					xor(AF.getH());
					cv = 4;
					break;
				case 0xB0: // OR   B
					or(BC.getH());
					cv = 4;
					break;
				case 0xB1: // OR   C
					or(BC.getL());
					cv = 4;
					break;
				case 0xB2: // OR   D
					or(DE.getH());
					cv = 4;
					break;
				case 0xB3: // OR   E
					or(DE.getL());
					cv = 4;
					break;
				case 0xB4: // OR   H
					or(HL.getH());
					cv = 4;
					break;
				case 0xB5: // OR   L
					or(HL.getL());
					cv = 4;
					break;
				case 0xB6: // OR   (HL)
					or(HL.getI(mem));
					cv = 8;
					break;
				case 0xB7: // OR   A
					or(AF.getH());
					cv = 4;
					break;
				case 0xB8: // CP   B
					cp(BC.getH());
					cv = 4;
					break;
				case 0xB9: // CP   C
					cp(BC.getL());
					cv = 4;
					break;
				case 0xBA: // CP   D
					cp(DE.getH());
					cv = 4;
					break;
				case 0xBB: // CP   E
					cp(DE.getL());
					cv = 4;
					break;
				case 0xBC: // CP   H
					cp(HL.getH());
					cv = 4;
					break;
				case 0xBD: // CP   L
					cp(HL.getL());
					cv = 4;
					break;
				case 0xBE: // CP   (HL)
					cp(HL.getI(mem));
					cv = 8;
					break;
				case 0xBF: // CP   A
					cp(AF.getH());
					cv = 4;
					break;
				case 0xC0: // RET  NZ
					if(!getZ()) {
						ret();
						cv = 20;
					} else {
						cv = 8;
					}
					break;
				case 0xC1: // POP  BC
					pop(BC);
					cv = 12;
					break;
				case 0xC2: // JP   NZ,nnnn
					if(!getZ()) {
						jp(readWord());
						cv = 16;
					} else {
						readWord();
						cv = 12;
					}
					break;
				case 0xC3: // JP nnnn
					jp(readWord());
					cv = 16;
					break;
				case 0xC4: // CALL NZ,nnnn
					if(!getZ()) {
						call(readWord());
						cv = 24;
					} else {
						readWord();
						cv = 12;
					}
					break;
				case 0xC5: // PUSH BC
					push(BC);
					cv = 16;
					break;
				case 0xC6: // ADD  A,nn
					add(readByte());
					cv = 8;
					break;
				case 0xC7: // RST  0H
					rst(0x0);
					cv = 16;
					break;
				case 0xC8: // RET  Z
					if(getZ()) {
						ret();
						cv = 20;
					} else {
						cv = 8;
					}
					break;
				case 0xC9: // RET
					ret();
					cv = 16;
					break;
				case 0xCA: // JP   Z,nnnn
					if(getZ()) {
						jp(readWord());
						cv = 16;
					} else {
						readWord();
						cv = 12;
					}
					break;
				case 0xCB: // -- special --
					int b = readByte();
					if (PgbSettings.DEBUG)
						System.out.println("0xCB, executing SubOpCode 0x" + Integer.toHexString(b));
					//cv = cbcodes[b].exec();
					switch(b) {
						case 0x00: // RLC  B
							rlcH(BC);
							cv = 8; 
							break;
						case 0x01: // RLC  C
							rlcL(BC);
							cv = 8;
							break;
						case 0x02: // RLC  D
							rlcH(DE);
							cv = 8;
							break;
						case 0x03: // RLC  E
							rlcL(DE);
							cv = 8;
							break;
						case 0x04: // RLC  H
							rlcH(HL);
							cv = 8;
							break;
						case 0x05: // RLC  L
							rlcL(HL);
							cv = 8; 
							break;
						case 0x06: // RLC  (HL)
							rlcI(HL.getR()); 
							cv = 16; 
							break;
						case 0x07: // RLC  A
							rlcH(AF);
							cv = 8; 
							break;
						case 0x08: // RRC  B
							rrcH(BC);
							cv = 8; 
							break;
						case 0x09: // RRC  C
							rrcL(BC);
							cv = 8; 
							break;
						case 0x0A: // RRC  D
							rrcH(DE);
							cv = 8; 
							break;
						case 0x0B: // RRC  E
							rrcL(DE);
							cv = 8; 
							break;
						case 0x0C: // RRC  H
							rrcH(HL);
							cv = 8; 
							break;
						case 0x0D: // RRC  L
							rrcL(HL);
							cv = 8; 
							break;
						case 0x0E: // RRC  (HL)
							rrcI(HL.getR()); 
							cv = 16; 
							break;
						case 0x0F: // RRC  A
							rrcH(AF);
							cv = 8; 
							break;
						case 0x10: // RL   B
							rlH(BC);
							cv = 8; 
							break;
						case 0x11: // RL   C
							rlL(BC);
							cv = 8; 
							break;
						case 0x12: // RL   D
							rlH(DE);
							cv = 8; 
							break;
						case 0x13: // RL   E
							rlL(DE);
							cv = 8; 
							break;
						case 0x14: // RL   H
							rlH(HL);
							cv = 8; 
							break;
						case 0x15: // RL   L
							rlL(HL);
							cv = 8; 
							break;
						case 0x16: // RL   (HL)
							rlI(HL.getR()); 
							cv = 16; 
							break;
						case 0x17: // RL   A
							rlH(AF);
							cv = 8; 
							break;
						case 0x18: // RR   B
							rrH(BC);
							cv = 8; 
							break;
						case 0x19: // RR   C
							rrL(BC);
							cv = 8; 
							break;
						case 0x1A: // RR   D
							rrH(DE);
							cv = 8; 
							break;
						case 0x1B: // RR   E
							rrL(DE);
							cv = 8; 
							break;
						case 0x1C: // RR   H
							rrH(HL);
							cv = 8; 
							break;
						case 0x1D: // RR   L
							rrL(HL);
							cv = 8; 
							break;
						case 0x1E: // RR   (HL)
							rrI(HL.getR()); 
							cv = 16; 
							break;
						case 0x1F: // RR   A
							rrH(AF);
							cv = 8; 
							break;
						case 0x20: // SLA  B
							slaH(BC);
							cv = 8; 
							break;
						case 0x21: // SLA  C
							slaL(BC);
							cv = 8; 
							break;
						case 0x22: // SLA  D
							slaH(DE);
							cv = 8; 
							break;
						case 0x23: // SLA  E
							slaL(DE);
							cv = 8; 
							break;
						case 0x24: // SLA  H
							slaH(HL);
							cv = 8; 
							break;
						case 0x25: // SLA  L
							slaL(HL);
							cv = 8; 
							break;
						case 0x26: // SLA  (HL)
							slaI(HL.getR()); 
							cv = 16; 
							break;
						case 0x27: // SLA  A
							slaH(AF);
							cv = 8; 
							break;
						case 0x28: // SRA  B
							sraH(BC);
							cv = 8; 
							break;
						case 0x29: // SRA  C
							sraL(BC);
							cv = 8; 
							break;
						case 0x2A: // SRA  D
							sraH(DE);
							cv = 8; 
							break;
						case 0x2B: // SRA  E
							sraL(DE);
							cv = 8; 
							break;
						case 0x2C: // SRA  H
							sraH(HL);
							cv = 8; 
							break;
						case 0x2D: // SRA  L
							sraL(HL);
							cv = 8; 
							break;
						case 0x2E: // SRA  (HL)
							sraI(HL.getR()); 
							cv = 16; 
							break;
						case 0x2F: // SRA  A
							sraH(AF);
							cv = 8; 
							break;
						case 0x30: // SWAP B
							swapH(BC);
							cv = 8; 
							break;
						case 0x31: // SWAP C
							swapL(BC);
							cv = 8; 
							break;
						case 0x32: // SWAP D
							swapH(DE);
							cv = 8; 
							break;
						case 0x33: // SWAP E
							swapL(DE);
							cv = 8; 
							break;
						case 0x34: // SWAP H
							swapH(HL);
							cv = 8; 
							break;
						case 0x35: // SWAP L
							swapL(HL);
							cv = 8; 
							break;
						case 0x36: // SWAP (HL)
							swapI(HL.getR()); 
							cv = 16; 
							break;
						case 0x37: // SWAP A
							swapH(AF);
							cv = 8; 
							break;
						case 0x38: // SRL  B
							srlH(BC);
							cv = 8; 
							break;
						case 0x39: // SRL  C
							srlL(BC);
							cv = 8; 
							break;
						case 0x3A: // SRL  D
							srlH(DE);
							cv = 8; 
							break;
						case 0x3B: // SRL  E
							srlL(DE);
							cv = 8; 
							break;
						case 0x3C: // SRL  H
							srlH(HL);
							cv = 8; 
							break;
						case 0x3D: // SRL  L
							srlL(HL);
							cv = 8; 
							break;
						case 0x3E: // SRL  (HL)
							srlI(HL.getR()); 
							cv = 16; 
							break;
						case 0x3F: // SRL  A
							srlH(AF);
							cv = 8; 
							break;
						case 0x40: // BIT  0,B
							bit(0, BC.getH());
							cv = 8; 
							break;
						case 0x41: // BIT  0,C
							bit(0, BC.getL());
							cv = 8; 
							break;
						case 0x42: // BIT  0,D
							bit(0, DE.getH());
							cv = 8; 
							break;
						case 0x43: // BIT  0,E
							bit(0, DE.getL());
							cv = 8; 
							break;
						case 0x44: // BIT  0,H
							bit(0, HL.getH());
							cv = 8; 
							break;
						case 0x45: // BIT  0,L
							bit(0, HL.getL());
							cv = 8; 
							break;
						case 0x46: // BIT  0,(HL)
							bit(0, HL.getI(mem));
							cv = 16; 
							break;
						case 0x47: // BIT  0,A
							bit(0, AF.getH());
							cv = 8; 
							break;
						case 0x48: // BIT  1,B
							bit(1, BC.getH());
							cv = 8; 
							break;
						case 0x49: // BIT  1,C
							bit(1, BC.getL());
							cv = 8; 
							break;
						case 0x4A: // BIT  1,D
							bit(1, DE.getH());
							cv = 8; 
							break;
						case 0x4B: // BIT  1,E
							bit(1, DE.getL());
							cv = 8; 
							break;
						case 0x4C: // BIT  1,H
							bit(1, HL.getH());
							cv = 8; 
							break;
						case 0x4D: // BIT  1,L
							bit(1, HL.getL());
							cv = 8; 
							break;
						case 0x4E: // BIT  1,(HL)
							bit(1, HL.getI(mem)); 
							cv = 16; 
							break;
						case 0x4F: // BIT  1,A
							bit(1, AF.getH());
							cv = 8; 
							break;
						case 0x50: // BIT  2,B
							bit(2, BC.getH());
							cv = 8; 
							break;
						case 0x51: // BIT  2,C
							bit(2, BC.getL());
							cv = 8; 
							break;
						case 0x52: // BIT  2,D
							bit(2, DE.getH());
							cv = 8; 
							break;
						case 0x53: // BIT  2,E
							bit(2, DE.getL());
							cv = 8; 
							break;
						case 0x54: // BIT  2,H
							bit(2, HL.getH());
							cv = 8; 
							break;
						case 0x55: // BIT  2,L
							bit(2, HL.getL());
							cv = 8; 
							break;
						case 0x56: // BIT  2,(HL)
							bit(2, HL.getI(mem)); 
							cv = 16; 
							break;
						case 0x57: // BIT  2,A
							bit(2, AF.getH());
							cv = 8; 
							break;
						case 0x58: // BIT  3,B
							bit(3, BC.getH());
							cv = 8; 
							break;
						case 0x59: // BIT  3,C
							bit(3, BC.getL());
							cv = 8; 
							break;
						case 0x5A: // BIT  3,D
							bit(3, DE.getH());
							cv = 8; 
							break;
						case 0x5B: // BIT  3,E
							bit(3, DE.getL());
							cv = 8; 
							break;
						case 0x5C: // BIT  3,H
							bit(3, HL.getH());
							cv = 8; 
							break;
						case 0x5D: // BIT  3,L
							bit(3, HL.getL());
							cv = 8; 
							break;
						case 0x5E: // BIT  3,(HL)
							bit(3, HL.getI(mem)); 
							cv = 16; 
							break;
						case 0x5F: // BIT  3,A
							bit(3, AF.getH());
							cv = 8; 
							break;
						case 0x60: // BIT  4,B
							bit(4, BC.getH());
							cv = 8; 
							break;
						case 0x61: // BIT  4,C
							bit(4, BC.getL());
							cv = 8; 
							break;
						case 0x62: // BIT  4,D
							bit(4, DE.getH());
							cv = 8; 
							break;
						case 0x63: // BIT  4,E
							bit(4, DE.getL());
							cv = 8; 
							break;
						case 0x64: // BIT  4,H
							bit(4, HL.getH());
							cv = 8; 
							break;
						case 0x65: // BIT  4,L
							bit(4, HL.getL());
							cv = 8; 
							break;
						case 0x66: // BIT  4,(HL)
							bit(4, HL.getI(mem)); 
							cv = 16; 
							break;
						case 0x67: // BIT  4,A
							bit(4, AF.getH());
							cv = 8; 
							break;
						case 0x68: // BIT  5,B
							bit(5, BC.getH());
							cv = 8; 
							break;
						case 0x69: // BIT  5,C
							bit(5, BC.getL());
							cv = 8; 
							break;
						case 0x6A: // BIT  5,D
							bit(5, DE.getH());
							cv = 8; 
							break;
						case 0x6B: // BIT  5,E
							bit(5, DE.getL());
							cv = 8; 
							break;
						case 0x6C: // BIT  5,H
							bit(5, HL.getH());
							cv = 8; 
							break;
						case 0x6D: // BIT  5,L
							bit(5, HL.getL());
							cv = 8; 
							break;
						case 0x6E: // BIT  5,(HL)
							bit(5, HL.getI(mem)); 
							cv = 16; 
							break;
						case 0x6F: // BIT  5,A
							bit(5, AF.getH());
							cv = 8; 
							break;
						case 0x70: // BIT  6,B
							bit(6, BC.getH());
							cv = 8; 
							break;
						case 0x71: // BIT  6,C
							bit(6, BC.getL());
							cv = 8; 
							break;
						case 0x72: // BIT  6,D
							bit(6, DE.getH());
							cv = 8; 
							break;
						case 0x73: // BIT  6,E
							bit(6, DE.getL());
							cv = 8; 
							break;
						case 0x74: // BIT  6,H
							bit(6, HL.getH());
							cv = 8; 
							break;
						case 0x75: // BIT  6,L
							bit(6, HL.getL());
							cv = 8; 
							break;
						case 0x76: // BIT  6,(HL)
							bit(6, HL.getI(mem)); 
							cv = 16; 
							break;
						case 0x77: // BIT  6,A
							bit(6, AF.getH());
							cv = 8; 
							break;
						case 0x78: // BIT  7,B
							bit(7, BC.getH());
							cv = 8; 
							break;
						case 0x79: // BIT  7,C
							bit(7, BC.getL());
							cv = 8; 
							break;
						case 0x7A: // BIT  7,D
							bit(7, DE.getH());
							cv = 8; 
							break;
						case 0x7B: // BIT  7,E
							bit(7, DE.getL());
							cv = 8; 
							break;
						case 0x7C: // BIT  7,H
							bit(7, HL.getH());
							cv = 8; 
							break;
						case 0x7D: // BIT  7,L
							bit(7, HL.getL());
							cv = 8; 
							break;
						case 0x7E: // BIT  7,(HL)
							bit(7, HL.getI(mem)); 
							cv = 16; 
							break;
						case 0x7F: // BIT  7,A
							bit(7, AF.getH());
							cv = 8; 
							break;
						case 0x80: // RES  0,B
							resH(0, BC);
							cv = 8; 
							break;
						case 0x81: // RES  0,C
							resL(0, BC);
							cv = 8; 
							break;
						case 0x82: // RES  0,D
							resH(0, DE);
							cv = 8; 
							break;
						case 0x83: // RES  0,E
							resL(0, DE);
							cv = 8; 
							break;
						case 0x84: // RES  0,H
							resH(0, HL);
							cv = 8; 
							break;
						case 0x85: // RES  0,L
							resL(0, HL);
							cv = 8; 
							break;
						case 0x86: // RES  0,(HL)
							resI(0, HL.getR()); 
							cv = 16; 
							break;
						case 0x87: // RES  0,A
							resH(0, AF);
							cv = 8; 
							break;
						case 0x88: // RES  1,B
							resH(1, BC);
							cv = 8; 
							break;
						case 0x89: // RES  1,C
							resL(1, BC);
							cv = 8; 
							break;
						case 0x8A: // RES  1,D
							resH(1, DE);
							cv = 8; 
							break;
						case 0x8B: // RES  1,E
							resL(1, DE);
							cv = 8; 
							break;
						case 0x8C: // RES  1,H
							resH(1, HL);
							cv = 8; 
							break;
						case 0x8D: // RES  1,L
							resL(1, HL);
							cv = 8; 
							break;
						case 0x8E: // RES  1,(HL)
							resI(1, HL.getR()); 
							cv = 16; 
							break;
						case 0x8F: // RES  1,A
							resH(1, AF);
							cv = 8; 
							break;
						case 0x90: // RES  2,B
							resH(2, BC);
							cv = 8; 
							break;
						case 0x91: // RES  2,C
							resL(2, BC);
							cv = 8; 
							break;
						case 0x92: // RES  2,D
							resH(2, DE);
							cv = 8; 
							break;
						case 0x93: // RES  2,E
							resL(2, DE);
							cv = 8; 
							break;
						case 0x94: // RES  2,H
							resH(2, HL);
							cv = 8; 
							break;
						case 0x95: // RES  2,L
							resL(2, HL);
							cv = 8; 
							break;
						case 0x96: // RES  2,(HL)
							resI(2, HL.getR()); 
							cv = 16; 
							break;
						case 0x97: // RES  2,A
							resH(2, AF);
							cv = 8; 
							break;
						case 0x98: // RES  3,B
							resH(3, BC);
							cv = 8; 
							break;
						case 0x99: // RES  3,C
							resL(3, BC);
							cv = 8; 
							break;
						case 0x9A: // RES  3,D
							resH(3, DE);
							cv = 8; 
							break;
						case 0x9B: // RES  3,E
							resL(3, DE);
							cv = 8; 
							break;
						case 0x9C: // RES  3,H
							resH(3, HL);
							cv = 8; 
							break;
						case 0x9D: // RES  3,L
							resL(3, HL);
							cv = 8; 
							break;
						case 0x9E: // RES  3,(HL)
							resI(3, HL.getR()); 
							cv = 16; 
							break;
						case 0x9F: // RES  3,A
							resH(3, AF);
							cv = 8; 
							break;
						case 0xA0: // RES  4,B
							resH(4, BC);
							cv = 8; 
							break;
						case 0xA1: // RES  4,C
							resL(4, BC);
							cv = 8; 
							break;
						case 0xA2: // RES  4,D
							resH(4, DE);
							cv = 8; 
							break;
						case 0xA3: // RES  4,E
							resL(4, DE);
							cv = 8; 
							break;
						case 0xA4: // RES  4,H
							resH(4, HL);
							cv = 8; 
							break;
						case 0xA5: // RES  4,L
							resL(4, HL);
							cv = 8; 
							break;
						case 0xA6: // RES  4,(HL)
							resI(4, HL.getR()); 
							cv = 16; 
							break;
						case 0xA7: // RES  4,A
							resH(4, AF);
							cv = 8; 
							break;
						case 0xA8: // RES  5,B
							resH(5, BC);
							cv = 8; 
							break;
						case 0xA9: // RES  5,C
							resL(5, BC);
							cv = 8; 
							break;
						case 0xAA: // RES  5,D
							resH(5, DE);
							cv = 8; 
							break;
						case 0xAB: // RES  5,E
							resL(5, DE);
							cv = 8; 
							break;
						case 0xAC: // RES  5,H
							resH(5, HL);
							cv = 8; 
							break;
						case 0xAD: // RES  5,L
							resL(5, HL);
							cv = 8; 
							break;
						case 0xAE: // RES  5,(HL)
							resI(5, HL.getR()); 
							cv = 16; 
							break;
						case 0xAF: // RES  5,A
							resH(5, AF);
							cv = 8; 
							break;
						case 0xB0: // RES  6,B
							resH(6, BC);
							cv = 8; 
							break;
						case 0xB1: // RES  6,C
							resL(6, BC);
							cv = 8; 
							break;
						case 0xB2: // RES  6,D
							resH(6, DE);
							cv = 8; 
							break;
						case 0xB3: // RES  6,E
							resL(6, DE);
							cv = 8; 
							break;
						case 0xB4: // RES  6,H
							resH(6, HL);
							cv = 8; 
							break;
						case 0xB5: // RES  6,L
							resL(6, HL);
							cv = 8; 
							break;
						case 0xB6: // RES  6,(HL)
							resI(6, HL.getR()); 
							cv = 16; 
							break;
						case 0xB7: // RES  6,A
							resH(6, AF);
							cv = 8; 
							break;
						case 0xB8: // RES  7,B
							resH(7, BC);
							cv = 8; 
							break;
						case 0xB9: // RES  7,C
							resL(7, BC);
							cv = 8; 
							break;
						case 0xBA: // RES  7,D
							resH(7, DE);
							cv = 8; 
							break;
						case 0xBB: // RES  7,E
							resL(7, DE);
							cv = 8; 
							break;
						case 0xBC: // RES  7,H
							resH(7, HL);
							cv = 8; 
							break;
						case 0xBD: // RES  7,L
							resL(7, HL);
							cv = 8; 
							break;
						case 0xBE: // RES  7,(HL)
							resI(7, HL.getR()); 
							cv = 16; 
							break;
						case 0xBF: // RES  7,A
							resH(7, AF);
							cv = 8; 
							break;
						case 0xC0: // SET  0,B
							setH(0, BC);
							cv = 8; 
							break;
						case 0xC1: // SET  0,C
							setL(0, BC);
							cv = 8; 
							break;
						case 0xC2: // SET  0,D
							setH(0, DE);
							cv = 8; 
							break;
						case 0xC3: // SET  0,E
							setL(0, DE);
							cv = 8; 
							break;
						case 0xC4: // SET  0,H
							setH(0, HL);
							cv = 8; 
							break;
						case 0xC5: // SET  0,L
							setL(0, HL);
							cv = 8; 
							break;
						case 0xC6: // SET  0,(HL)
							setI(0, HL.getR()); 
							cv = 16; 
							break;
						case 0xC7: // SET  0,A
							setH(0, AF);
							cv = 8; 
							break;
						case 0xC8: // SET  1,B
							setH(1, BC);
							cv = 8; 
							break;
						case 0xC9: // SET  1,C
							setL(1, BC);
							cv = 8; 
							break;
						case 0xCA: // SET  1,D
							setH(1, DE);
							cv = 8; 
							break;
						case 0xCB: // SET  1,E
							setL(1, DE);
							cv = 8; 
							break;
						case 0xCC: // SET  1,H
							setH(1, HL);
							cv = 8; 
							break;
						case 0xCD: // SET  1,L
							setL(1, HL);
							cv = 8; 
							break;
						case 0xCE: // SET  1,(HL)
							setI(1, HL.getR()); 
							cv = 8; 
							break;
						case 0xCF: // SET  1,A
							setH(1, AF);
							cv = 8; 
							break;
						case 0xD0: // SET  2,B
							setH(2, BC);
							cv = 8; 
							break;
						case 0xD1: // SET  2,C
							setL(2, BC);
							cv = 8; 
							break;
						case 0xD2: // SET  2,D
							setH(2, DE);
							cv = 8; 
							break;
						case 0xD3: // SET  2,E
							setL(2, DE);
							cv = 8; 
							break;
						case 0xD4: // SET  2,H
							setH(2, HL);
							cv = 8; 
							break;
						case 0xD5: // SET  2,L
							setL(2, HL);
							cv = 8; 
							break;
						case 0xD6: // SET  2,(HL)
							setI(2, HL.getR()); 
							cv = 16; 
							break;
						case 0xD7: // SET  2,A
							setH(2, AF);
							cv = 8; 
							break;
						case 0xD8: // SET  3,B
							setH(3, BC);
							cv = 8; 
							break;
						case 0xD9: // SET  3,C
							setL(3, BC);
							cv = 8; 
							break;
						case 0xDA: // SET  3,D
							setH(3, DE);
							cv = 8; 
							break;
						case 0xDB: // SET  3,E
							setL(3, DE);
							cv = 8; 
							break;
						case 0xDC: // SET  3,H
							setH(3, HL);
							cv = 8; 
							break;
						case 0xDD: // SET  3,L
							setL(3, HL);
							cv = 8; 
							break;
						case 0xDE: // SET  3,(HL)
							setI(3, HL.getR()); 
							cv = 16; 
							break;
						case 0xDF: // SET  3,A
							setH(3, AF);
							cv = 8; 
							break;
						case 0xE0: // SET  4,B
							setH(4, BC);
							cv = 8; 
							break;
						case 0xE1: // SET  4,C
							setL(4, BC);
							cv = 8; 
							break;
						case 0xE2: // SET  4,D
							setH(4, DE);
							cv = 8; 
							break;
						case 0xE3: // SET  4,E
							setL(4, DE);
							cv = 8; 
							break;
						case 0xE4: // SET  4,H
							setH(4, HL);
							cv = 8; 
							break;
						case 0xE5: // SET  4,L
							setL(4, HL);
							cv = 8; 
							break;
						case 0xE6: // SET  4,(HL)
							setI(4, HL.getR()); 
							cv = 16; 
							break;
						case 0xE7: // SET  4,A
							setH(4, AF);
							cv = 8; 
							break;
						case 0xE8: // SET  5,B
							setH(5, BC);
							cv = 8; 
							break;
						case 0xE9: // SET  5,C
							setL(5, BC);
							cv = 8; 
							break;
						case 0xEA: // SET  5,D
							setH(5, DE);
							cv = 8; 
							break;
						case 0xEB: // SET  5,E
							setL(5, DE);
							cv = 8; 
							break;
						case 0xEC: // SET  5,H
							setH(5, HL);
							cv = 8; 
							break;
						case 0xED: // SET  5,L
							setL(5, HL);
							cv = 8; 
							break;
						case 0xEE: // SET  5,(HL)
							setI(5, HL.getR()); 
							cv = 16; 
							break;
						case 0xEF: // SET  5,A
							setH(5, AF);
							cv = 8; 
							break;
						case 0xF0: // SET  6,B
							setH(6, BC);
							cv = 8; 
							break;
						case 0xF1: // SET  6,C
							setL(6, BC);
							cv = 8; 
							break;
						case 0xF2: // SET  6,D
							setH(6, DE);
							cv = 8; 
							break;
						case 0xF3: // SET  6,E
							setL(6, DE);
							cv = 8; 
							break;
						case 0xF4: // SET  6,H
							setH(6, HL);
							cv = 8; 
							break;
						case 0xF5: // SET  6,L
							setL(6, HL);
							cv = 8; 
							break;
						case 0xF6: // SET  6,(HL)
							setI(6, HL.getR()); 
							cv = 16; 
							break;
						case 0xF7: // SET  6,A
							setH(6, AF);
							cv = 8; 
							break;
						case 0xF8: // SET  7,B
							setH(7, BC);
							cv = 8; 
							break;
						case 0xF9: // SET  7,C
							setL(7, BC);
							cv = 8; 
							break;
						case 0xFA: // SET  7,D
							setH(7, DE);
							cv = 8; 
							break;
						case 0xFB: // SET  7,E
							setL(7, DE);
							cv = 8; 
							break;
						case 0xFC: // SET  7,H
							setH(7, HL);
							cv = 8; 
							break;
						case 0xFD: // SET  7,L
							setL(7, HL);
							cv = 8; 
							break;
						case 0xFE: // SET  7,(HL)
							setI(7, HL.getR()); 
							cv = 16; 
							break;
						case 0xFF: // SET  7,A
							setH(7, AF);
							cv = 8; 
							break;
						default:
							//cv = cbcodes[b].exec();
							unsupportedCB(b);
							cv = 4;
					}
					break;
				case 0xCC: // CALL Z,nnnn
					if(getZ()) {
						call(readWord());
						cv = 24;
					} else {
						readWord();
						cv = 12;
					}
					break;
				case 0xCD: // CALL nnnn
					call(readWord());
					cv = 24;
					break;
				case 0xCE: // ADC  A,nn
					adc(readByte());
					cv = 8;
					break;
				case 0xCF: // RST  8H
					rst(0x8);
					cv = 16;
					break;
				case 0xD0: // RET  NC
					if(!getC()) {
						ret();
						cv = 20;
					} else {
						cv = 8;
					}
					break;
				case 0xD1: // POP  DE
					pop(DE);
					cv = 10;
					break;
				case 0xD2: // JP   NC,nnnn
					if(!getC()) {
						jp(readWord());
						cv = 20;
					} else {
						readWord();
						cv = 8;
					}
					break;
				case 0xD3: // -
					unsupported(0xD3);
					cv = 4;
					break;
				case 0xD4: // CALL NC,nnnn
					if(!getC()) {
						call(readWord());
						cv = 24;
					} else {
						readWord();
						cv = 12;
					}
					break;
				case 0xD5: // PUSH DE
					push(DE);
					cv = 16;
					break;
				case 0xD6: // SUB  nn
					sub(readByte());
					cv = 8;
					break;
				case 0xD7: // RST  10h
					rst(0x10);
					cv = 16;
					break;
				case 0xD8: // RET  C
					if(getC()) {
						ret();
						cv = 20;
					} else {
						cv = 8;
					}
					break;
				case 0xD9: // RETI
					ret();
					ei();
					cv = 16;
					break;
				case 0xDA: // JP   C,nnnn
					if(getC()) {
						jp(readWord());
						cv = 16;
					} else {
						readWord();
						cv = 12;
					}
					break;
				case 0xDB: // -
					unsupported(0xDB);
					cv = 4;
					break;
				case 0xDC: // CALL C,nnnn
					if(getC()) {
						call(readWord());
						cv = 24;
					} else {
						readWord();
						cv = 12;
					}
					break;
				case 0xDD: // -
					unsupported(0xDD);
					cv = 4;
					break;
				case 0xDE: // SBC  A,nn
					sbc(readByte());
					cv = 8;
					break;
				case 0xDF: // RST  18h
					rst(0x18);
					cv = 16;
					break;
				case 0xE0: // LD   ($FF00+nn),A
					mem.write(0xFF00 + readByte(), AF.getH());
					cv = 12;
					break;
				case 0xE1: // POP  HL
					pop(HL);
					cv = 12;
					break;
				case 0xE2: // LD   ($FF00+C),A
					mem.write(0xFF00 + BC.getL(), AF.getH());
					cv = 8;
					break;
				case 0xE3: // -
					unsupported(0xE3);
					cv = 4;
					break;
				case 0xE4: // -
					unsupported(0xE4);
					cv = 4;
					break;
				case 0xE5: // PUSH HL
					push(HL);
					cv = 16;
					break;
				case 0xE6: // AND  nn
					and(readByte());
					cv = 8;
					break;
				case 0xE7: // RST  20h
					rst(0x20);
					cv = 16;
					break;
				case 0xE8: // ADD  SP,dd
					SP.setR(SP.getR() + (byte)readByte());
					setZ(false);
					cv = 16;
					break;
				case 0xE9: // JP   (HL)
					jp(HL.getR());
					cv = 4;
					break;
				case 0xEA: // LD   (nnnn),A
					mem.write(readWord(), AF.getH());
					cv = 16;
					break;
				case 0xEB: // -
					unsupported(0xEB);
					cv = 4;
					break;
				case 0xEC: // -
					unsupported(0xEC);
					cv = 4;
					break;
				case 0xED: // -
					unsupported(0xED);
					cv = 4;
					break;
				case 0xEE: // XOR  nn
					xor(readByte());
					cv = 8;
					break;
				case 0xEF: // RST 28h
					rst(0x28);
					cv = 16;
					break;
				case 0xF0: // LD   A,($FF00+nn)
					AF.setH(mem.read(0xFF00 + readByte()));
					cv = 12;
					break;
				case 0xF1: // POP  AF
					pop(AF);
					cv = 12;
					break;
				case 0xF2: // LD   A,(C) [same as LD   A,($FF00+C)]
					AF.setH(mem.read(0xFF00 + BC.getL()));
					cv = 8;
					break;
				case 0xF3: // DI
					di();
					cv = 4;
					break;
				case 0xF4: // -
					unsupported(0xF4);
					cv = 4;
					break;
				case 0xF5: // PUSH AF
					push(AF);
					cv = 16;
					break;
				case 0xF6: // OR   nn
					or(readByte());
					cv = 8;
					break;
				case 0xF7: // RST  30h
					rst(0x30);
					cv = 16;
					break;
				case 0xF8: // LD   HL,SP+dd
					HL.setR(SP.getR() + (byte)readByte());
					cv = 12;
					break;
				case 0xF9: // LD   SP,HL
					SP.setR(HL.getR());
					cv = 8;
					break;
				case 0xFA: // LD   A,(nnnn)
					AF.setH(mem.read(readWord()));
					cv = 16;
					break;
				case 0xFB: // EI
					ei();
					cv = 4;
					break;
				case 0xFC: // -
					unsupported(0xFC);
					cv = 4;
					break;
				case 0xFD: // -
					unsupported(0xFD); //XXX was ei()
					cv = 4;
					break;
				case 0xFE: // CP   nn
					cp(readByte());
					cv = 8;
					break;
				case 0xFF: // RST  38h
					rst(0x38);
					cv = 16;
					break;
				default:
					//System.out.println("Code: 0x" + Integer.toHexString(memread));
					unsupported(memread);
					cv = 4;
			}
			// cycle all hardware and set the interrupt flag
			mem.cycle(cv);
			
			// loop
			cte -= cv;		
		}
	}
	
	private final void interrupt(int i) {
		if((i & PgbMemory.INT_VBLANK) == PgbMemory.INT_VBLANK) {
			// vBlank
			mem.IF &= ~PgbMemory.INT_VBLANK;
			di();
			rst(0x0040);
			return;
		}
		if((i & PgbMemory.INT_LCD) == PgbMemory.INT_LCD) {
			// LCD
			mem.IF &= ~PgbMemory.INT_LCD;
			di();
			rst(0x0048);
			return;
		}
		if((i & PgbMemory.INT_TIMER) == PgbMemory.INT_TIMER) {
			// timer overflow
			mem.IF &= ~PgbMemory.INT_TIMER;
			di();
			rst(0x0050);
			return;
		}
		if((i & PgbMemory.INT_SERIAL) == PgbMemory.INT_SERIAL) {
			// serial
			mem.IF &= ~PgbMemory.INT_SERIAL;
			di();
			rst(0x0058);
			return;
		}
		if((i & PgbMemory.INT_JOYPAD) == PgbMemory.INT_JOYPAD) {
			// joypad
			mem.IF &= ~PgbMemory.INT_JOYPAD;
			di();
			rst(0x0060);
			return;
		}
	}

	/**
	 * interrupts
	 */
	private final void di() {
		//System.out.println("Interrupts DISabled.");
		ime = false;
	}
	
	private final void ei() {
		//System.out.println("Interrupts ENabled.");
		ime = true;
	}
	
	/**
	 * 8-bit acumulator arithmetic functions
	 */
	
	/**
	 * add
	 * @param val
	 */
	private final void add(int val) {
		int res = AF.getH() + val;
		setC(res != (res &= 0xFF));
		setZ(res);
		setN(false);
		AF.setH(res);
	}
	
	/**
	 * add with carrier
	 * @param val
	 */
	private final void adc(int val) {
		int res = AF.getH() + val + (getC() ? 1 : 0);
		setC(res != (res &= 0xFF));
		setZ(res);
		setN(false);
		AF.setH(res);
	}
	
	/**
	 * substract
	 * @param val
	 */
	private final void sub(int val) {
		int res = AF.getH() - val;
		setC(res != (res &= 0xFF));
		setZ(res);
		setN(true);
		AF.setH(res);
	}
	
	/**
	 * substract with carrier
	 * @param val
	 */
	private final void sbc(int val) {
		int res = AF.getH() - val - (getC() ? 1 : 0);
		setC(res != (res &= 0xFF));
		setZ(res);
		setN(true);
		AF.setH(res);
	}
	
	/**
	 * logical and
	 * @param val
	 */
	private final void and(int val) {
		int res = AF.getH() & val;
		setC(false);
		setZ(res);
		setN(false);
		AF.setH(res);
	}	
	
	/**
	 * or
	 * @param val
	 */
	private final void or(int val) {
		int res = AF.getH() | val;
		setC(false);
		setZ(res);
		setN(false);
		AF.setH(res);
	}
	
	/**
	 * exclusive or
	 * @param val
	 */
	private final void xor(int val) {
		int res = AF.getH() ^ val;
		setC(false);
		setZ(res);
		setN(false);
		AF.setH(res);
	}	
	
	/**
	 * compare
	 * @param val
	 */
	private final void cp(int val) {
		setC(AF.getH() < val);
		setZ(AF.getH() == val);
		setN(true);
	}
	
	/**
	 * 8-bit register arithmetic functions
	 */
	
	/**
	 * increase higher
	 * @param reg
	 */
	private final void incH(PgbRegister reg) {
		int res = (reg.getH() + 1);
		res &= 0xFF;
		setZ(res);
		setN(false);
		reg.setH(res);
	}
	
	/**
	 * increase lower
	 * @param reg
	 */
	private final void incL(PgbRegister reg) {
		int res = (reg.getL() + 1);
		res &= 0xFF;
		setZ(res);
		setN(false);
		reg.setL(res);
	}
	
	/**
	 * increase memory location (?)
	 * @param reg
	 */
	private final void incI(PgbRegister reg) {
		int res = reg.getI(mem) + 1;
		res &= 0xFF;
		setZ(res);
		setN(false);
		reg.setI(res, mem);
	}

	/**
	 * decrease higher
	 * @param reg
	 */
	private final void decH(PgbRegister reg) {
		int res = reg.getH() - 1;
		res &= 0xFF;
		setZ(res);
		setN(true);
		reg.setH(res);
	}
	
	/**
	 * decrease lower
	 * @param reg
	 */
	private final void decL(PgbRegister reg) {
		int res = reg.getL() - 1;
		res &= 0xFF;
		setZ(res);
		setN(true);
		reg.setL(res);
	}
	
	/**
	 * decrease memory location(?)
	 * @param reg
	 */
	private final void decI(PgbRegister reg) {
		int res = reg.getI(mem) - 1;
		res &= 0xFF;
		setZ(res);
		setN(true);
		reg.setI(res, mem);
	}

	/**
	 * 16-bit register opcode functions
	 */
	
	/**
	 * increase register
	 * @param reg
	 */
	private final void incR(PgbRegister reg) {
		reg.setR(reg.getR() + 1 & 0xFFFF);
	}
	
	/**
	 * decrease register
	 * @param reg
	 */
	private final void decR(PgbRegister reg) {
		reg.setR(reg.getR() - 1 & 0xFFFF);
	}
	
	/**
	 * add register reg1 to register reg0
	 * @param reg0
	 * @param reg1
	 */
	private final void addR(PgbRegister reg0, PgbRegister reg1) {
		int res = reg0.getR() + reg1.getR();
		setC(res != (res &= 0xFFFF));
		reg0.setR(res);
	}
	
	/**
	 * stack
	 */
	private final void pop(PgbRegister reg) {
		reg.setL(mem.read(SP.data++));
		reg.setH(mem.read(SP.data++));
	}
	private final void push(PgbRegister reg) {
		// arggh. some roms push with SP == 0x0000,
		// totally ruining the elegance of this expression
		mem.write(--SP.data & 0xFFFF, reg.getH());
		SP.data &= 0xFFFF;
		mem.write(--SP.data, reg.getL());
	}
	
	/**
	 * jumping around...
	 */
	private final void jp(int address) {
		PC.setR(address);
	}
	private final void jr(int offset) {
		PC.setR(PC.getR() + (byte)offset);
	}
	private final void call(int address) {
		push(PC);
		PC.setR(address);
	}
	private final void rst(int address) {
		push(PC);
		PC.setR(address);
	}
	private final void ret() {
		pop(PC);
	}
	
	/**
	 * bitwise operators
	 */
	private final void bit(int bit, int val) {
		int res = val & (int)(1 << bit);
		setZ(res);
		setN(false);
	}
	private final void resH(int bit, PgbRegister reg) {
		int res = reg.getH() & ~(int)(1 << bit);
		reg.setH(res);
	}
	private final void resL(int bit, PgbRegister reg) {
		int res = reg.getL() & ~(int)(1 << bit);
		reg.setL(res);
	}
	private final void resI(int bit, int address) {
		int res = unsign(mem.read(address)) & ~(int)(1 << bit);
		mem.write(address, res);
	}
	private final void setH(int bit, PgbRegister reg) {
		int res = reg.getH() | (int)(1 << bit);
		reg.setH(res);
	}
	private final void setL(int bit, PgbRegister reg) {
		int res = reg.getL() | (int)(1 << bit);
		reg.setL(res);
	}
	private final void setI(int bit, int address) {
		int res = unsign(mem.read(address)) | (int)(1 << bit);
		mem.write(address, res);
	}
	
	/**
	 * rotates and shifts
	 */
	private final void rrcH(PgbRegister reg) {
		int res = reg.getH();
		setC((res & 0x01) == 1);
		res = (res >> 1) | ((res & 0x01) << 7);
		setZ(res);
		setN(false);
		reg.setH(res);
	}
	private final void rrcL(PgbRegister reg) {
		int res = reg.getL();
		setC((res & 0x01) == 1);
		res = (res >> 1) | ((res & 0x01) << 7);
		setZ(res);
		setN(false);
		reg.setL(res);
	}
	private final void rrcI(int address) {
		int res = unsign(mem.read(address));
		setC((res & 0x01) == 1);
		res = (res >> 1) | ((res & 0x01) << 7);
		setZ(res);
		setN(false);
		mem.write(address, res);
	}
	private final void rrH(PgbRegister reg) {
		int res = reg.getH();
		res = (res >> 1) | (getC() ? 0x80 : 0);
		setC((reg.getH() & 0x01) == 1);
		setZ(res);
		setN(false);
		reg.setH(res);
	}
	private final void rrL(PgbRegister reg) {
		int res = reg.getL();
		res = (res >> 1) | (getC() ? 0x80 : 0);
		setC((reg.getL() & 0x01) == 1);
		setZ(res);
		setN(false);
		reg.setL(res);
	}
	private final void rrI(int address) {
		int res = unsign(mem.read(address));
		res = (res >> 1) | (getC() ? 0x80 : 0);
		setC((unsign(mem.read(address)) & 0x01) == 1);
		setZ(res);
		setN(false);
		mem.write(address, res);
	}
	private final void rlcH(PgbRegister reg) {
		int res = reg.getH();
		res = (res << 1) | (res >> 7);
		setC(res != (res &= 0xFF));
		setZ(res);
		setN(false);
		reg.setH(res);
	}
	private final void rlcL(PgbRegister reg) {
		int res = reg.getL();
		res = (res << 1) | (res >> 7);
		setC(res != (res &= 0xFF));
		setZ(res);
		setN(false);
		reg.setL(res);
	}
	private final void rlcI(int address) {
		int res = unsign(mem.read(address));
		res = (res << 1) | (res >> 7);
		setC(res != (res &= 0xFF));
		setZ(res);
		setN(false);
		mem.write(address, res);
	}
	private final void rlH(PgbRegister reg) {
		int res = (reg.getH() << 1) | (getC() ? 1 : 0);
		setC(res != (res &= 0xFF));
		setZ(res);
		setN(false);
		reg.setH(res);
	}
	private final void rlL(PgbRegister reg) {
		int res = (reg.getL() << 1) | (getC() ? 1 : 0);
		setC(res != (res &= 0xFF));
		setZ(res);
		setN(false);
		reg.setL(res);
	}
	private final void rlI(int address) {
		int res = (unsign(mem.read(address)) << 1) | (getC() ? 1 : 0);
		setC(res != (res &= 0xFF));
		setZ(res);
		setN(false);
		mem.write(address, res);
	}
	private final void sraH(PgbRegister reg) {
		int res = reg.getH();
		setC((res & 1) == 1);
		res = (res >> 1) | (res & 0x80);
		setZ(res);
		setN(false);
		reg.setH(res);
	}
	private final void sraL(PgbRegister reg) {
		int res = reg.getL();
		setC((res & 1) == 1);
		res = (res >> 1) | (res & 0x80);
		setZ(res);
		setN(false);
		reg.setL(res);
	}
	private final void sraI(int address) {
		int res = unsign(mem.read(address));
		setC((res & 1) == 1);
		res = (res >> 1) | (res & 0x80);
		setZ(res);
		setN(false);
		mem.write(address, res);
	}
	private final void slaH(PgbRegister reg) {
		int res = reg.getH() << 1;
		setC(res != (res &= 0xFF));
		setZ(res);
		setN(false);
		reg.setH(res);
	}
	private final void slaL(PgbRegister reg) {
		int res = reg.getL() << 1;
		setC(res != (res &= 0xFF));
		setZ(res);
		setN(false);
		reg.setL(res);
	}
	private final void slaI(int address) {
		int res = unsign(mem.read(address));
		res *= 2;
		setC(res != (res &= 0xFF));
		setZ(res);
		setN(false);
		mem.write(address, res);
	}
	private final void srlH(PgbRegister reg) {
		int res = reg.getH();
		setC((res & 1) == 1);
		res >>= 1;
		setZ(res);
		setN(false);
		reg.setH(res);
	}
	private final void srlL(PgbRegister reg) {
		int res = reg.getL();
		setC((res & 1) == 1);
		res >>= 1;
		setZ(res);
		setN(false);
		reg.setL(res);
	}
	private final void srlI(int address) {
		int res = unsign(mem.read(address));
		setC((res & 1) == 1);
		res >>= 1;
		setZ(res);
		setN(false);
		mem.write(address, res);
	}
	
	/**
	 * misc operations
	 */
	private final void cpl() {
		int res = ~AF.getH() & 0xFF;
		setN(true);
		AF.setH(res);
	}
	private final void daa() {
		int res = AF.getH();
		if(getN()) {
			if(res / 16 > 9) {
				res -= 0x60;
			}
			if((res & 15) > 9) {
				res -= 0x06;
			}
		} else {
			if(res / 16 > 9) {
				res += 0x60;
			}
			if((res & 15) > 9) {
				res += 0x06;
			}
		}
		setC(res != (res &= 0xFF));
		setZ(res);
		AF.setH(res);
	}
	private final void swapH(PgbRegister reg) {
		int res = ((reg.getH() << 4) & 0xF0) | (reg.getH() >> 4);
		setC(false);
		setZ(res);
		setN(false);
		reg.setH(res);
	}
	private final void swapL(PgbRegister reg) {
		int res = ((reg.getL() << 4) & 0xF0) | (reg.getL() >> 4);
		setC(false);
		setZ(res);
		setN(false);
		reg.setL(res);
	}
	private final void swapI(int address) {
		int res = ((unsign(mem.read(address)) << 4) & 0xF0) | (unsign(mem.read(address)) >> 4);
		setC(false);
		setZ(res);
		setN(false);
		mem.write(address, res);
	}
	
	/**
	 * flaggies?
	 */
	private final void setZ(boolean zval) {
		AF.lo &= ~Z_FLAG;
		if(zval) {
			AF.lo |= Z_FLAG;
		}
	}
	private final void setZ(int zval) {
		AF.lo &= ~Z_FLAG;
		if(zval == 0) {
			AF.lo |= Z_FLAG;
		}
	}
	private final void setN(boolean nval) {
		AF.lo &= ~N_FLAG;
		if(nval) {
			AF.lo |= N_FLAG;
		}
	}
	private final void setH(boolean hval) {
		AF.lo &= ~H_FLAG;
		if(hval) {
			AF.lo |= H_FLAG;
		}
	}
	private final void setC(boolean cval) {
		AF.lo &= ~C_FLAG;
		if(cval) {
			AF.lo |= C_FLAG;
		}
	}
	private final boolean getZ() {
		return (AF.getL() & Z_FLAG) == Z_FLAG;
	}
	private final boolean getN() {
		return (AF.getL() & N_FLAG) == N_FLAG;
	}
	private final boolean getH() {
		return (AF.getL() & H_FLAG) == H_FLAG;
	}
	private final boolean getC() {
		return (AF.getL() & C_FLAG) == C_FLAG;
	}
	
	/**
	 * reads
	 */
	private final int readWord() {
		return unsign(mem.read(PC.data++)) | unsign(mem.read(PC.data++)) << 8;
	}
	
	private final int readByte() {
		return unsign(mem.read(PC.data++));
	}
	
	/**
	 * status displays
	 */
	private void unsupportedCB(int cb) {
		System.out.println("unsupported CB code: " + Integer.toHexString(cb));
		//PgbSettings.paused = true;
	}
	
	private void unsupported(int op) {
		System.out.println(Integer.toHexString(PC.data - 1) + " unsupported opcode: " + Integer.toHexString(op));
		//PgbSettings.paused = true;;
	}
	
	/**
	 * unsign a byte
	 */
	private final int unsign(byte b) {
		return (int)(b & 0xFF);
	}
}
