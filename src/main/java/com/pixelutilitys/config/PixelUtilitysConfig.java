package com.pixelutilitys.config;

import com.pixelmonmod.pixelmon.enums.EnumPokemon;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;

public class PixelUtilitysConfig {

    private Configuration configuration;

    private static PixelUtilitysConfig instance;

    // Call Once
    private boolean called = false;

    public boolean coinDrops = false;
    public boolean grassBattles = false;
    public boolean onlyGrassSpawns = false;
    public int grassSpawnRate;
    public int waterSpawnRate;
    public boolean waterBattles = false;
    public int coinDropRate;

    public String BattleMusicURL;
    public boolean battleMusicEnabled;

    static {
        if (instance == null) {
            instance = new PixelUtilitysConfig();
        }
    }

    public static PixelUtilitysConfig getInstance() {
        return instance;
    }

    private PixelUtilitysConfig() {

    }

    public void setEvent(FMLPreInitializationEvent event) {
        configuration = new Configuration(event.getSuggestedConfigurationFile());
        configuration.load();
        this.loadConfig();
        configuration.save();
    }

    private void loadConfig() {

        PixelUtilitysTools.getInstance().load(this.configuration); //Get instance sets it up.
        PixelUtilitysItems.load(this.configuration);
        PixelUtilitysBlocks.load(this.configuration);
        PixelUtilitysArmor.load(this.configuration);
        PixelUtilitysBlocks.addNames();
        PixelUtilitysItems.addNames();
        PixelUtilitysArmor.addNames();
        PixelUtilitysTools.getInstance().addNames();

        coinDrops = this.configuration.get("general", "Make Pixelmon drop PokeCoins", false).getBoolean(false);
        grassBattles = this.configuration.get("general", "Allow pixelmon spawning blocks to spawn pixelmon", true).getBoolean(true);
        onlyGrassSpawns = this.configuration.get("general", "Only spawn Pixelmon in grass", false).getBoolean(false);
        grassSpawnRate = this.configuration.get("general", "Pixelmon in grass spawn rate", 200).getInt();
        coinDropRate = this.configuration.get("general", "Pixelmon coin drop rate", 4).getInt();
        waterSpawnRate = this.configuration.get("general", "Pixelmon in water spawn rate", 400).getInt();

        //music
        BattleMusicURL = this.configuration.get("General", "BattleMusic Music Song URL (If youtube make sure http not https)", "http://www.youtube.com/watch?v=WnkhVPmapc8").getString();
        battleMusicEnabled = this.configuration.get("General", "Battle Music Enabled", true).getBoolean(true);
        this.configuration.save();
    }


}