package com.pixelutilitys.config;

import com.pixelutilitys.Basemod;
import com.pixelutilitys.items.tools.*;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class PixelUtilitysTools {

    //Ruby Tools
    public  Item rubyPickaxe;
    public  Item rubyHammer;
    public  Item rubyAxe;
    public  Item rubyShovel;
    public  Item rubyHoe;
    public  Item rubySword;

    //Saphire Tools
    public  Item saphirePickaxe;
    public  Item saphireHammer;// new com.PixelUtilitys.tools.SaphireHammerTool(6052, Basemod.SAPHIRE, "saphireHammer", "Saphire Hammer").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("saphireHammer");
    public  Item saphireAxe;// new com.PixelUtilitys.tools.SaphireAxeTool(6053, Basemod.SAPHIRE, "pixelutilitys:SaphireAxe").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("saphireAxe");
    public  Item saphireShovel;// new com.PixelUtilitys.tools.SaphireShovelTool(6054, Basemod.SAPHIRE, "pixelutilitys:SaphireShovel").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("saphireShovel");
    public  Item saphireHoe;// new com.PixelUtilitys.tools.SaphireHoeTool(6055, Basemod.SAPHIRE, "PixelUtilitys:SaphireHoe").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("saphireHoe");
    public  Item saphireSword;// new com.PixelUtilitys.tools.SaphireSwordTool(6056, Basemod.SAPHIRE, "PixelUtilitys:SaphireSword").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("saphireSword");

    //Amethyst Tools
    public  Item amethystPickaxe;// new com.PixelUtilitys.tools.AmethystPickaxeTool(6057, 6.5F, Basemod.AMETHYST, "PixelUtilitys:AmethystPickaxe").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("amethystPickaxe");
    public  Item amethystHammer;// new com.PixelUtilitys.tools.AmethystHammerTool(6058, Basemod.AMETHYST, "AmethystHammer", "Amethyst Hammer").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("amethystHammer").setTextureName("PixelUtilitys:AmethystHammer");
    public  Item amethystShovel;// new com.PixelUtilitys.tools.AmethystShovelTool(6059, Basemod.AMETHYST, "PixelUtilitys:AmethystShovel").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("amethystShovel");
    public  Item amethystAxe;// new com.PixelUtilitys.tools.AmethystAxeTool(6060, Basemod.AMETHYST, "PixelUtilitys:AmethystAxe").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("amethystAxe");
    public  Item amethystHoe;// new com.PixelUtilitys.tools.AmethystHoeTool(6061, Basemod.AMETHYST, "PixelUtilitys:AmethystHoe").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("amethystHoe");
    public  Item amethystSword;// new com.PixelUtilitys.tools.AmethystSwordTool(6062, Basemod.AMETHYST, "PixelUtilitys:AmethystSword").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("amethystSword");

    //EvoTools
    //Firestone tools
    public  Item FirestonePickaxe;// new com.PixelUtilitys.tools.FirestonePickaxe(6083, 6.5F, FIRESTONE, "pixelutilitys:FirestonePickaxe").setTextureName("PixelUtilitys:FirestonePickaxe").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("FirestonePickaxe");
    public  Item FirestoneHammer;// new com.PixelUtilitys.tools.FirestoneHammer(6084, FIRESTONE, "FirestoneHammer", "Firestone Hammer").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setTextureName("PixelUtilitys:FirestoneHammer").setUnlocalizedName("FirestoneHammer");
    public  Item FirestoneAxe;// new com.PixelUtilitys.tools.FirestoneAxe(6085, FIRESTONE, "pixelutilitys:FirestoneAxe").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("FirestoneAxe").setTextureName("PixelUtilitys:FirestoneAxe");
    public  Item FirestoneShovel;// new com.PixelUtilitys.tools.FirestoneShovel(6086, FIRESTONE, "pixelutilitys:FirestoneShovel").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("FirestoneShovel").setTextureName("PixelUtilitys:FirestoneShovel");
    public  Item FirestoneHoe;// new com.PixelUtilitys.tools.FirestoneHoe(6087, FIRESTONE, "pixelutilitys:FirestoneHoe").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("FirestoneHoe").setTextureName("pixelutilitys:FirestoneHoe");
    public  Item FirestoneSword;// new com.PixelUtilitys.tools.FirestoneSword(6088, FIRESTONE, "pixelutilities:FirestoneSword").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("FirestoneSword").setTextureName("PixelUtilitys:FirestoneSword");

    //Waterstone tools
    public  Item WaterstonePickaxe;// new com.PixelUtilitys.tools.WaterstonePickaxe(6089, 6.5F, Basemod.WATERSTONE, "pixelutilitys:WaterstonePickaxe").setTextureName("PixelUtilitys:WaterstonePickaxe").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("WaterstonePickaxe");
    public  Item WaterstoneHammer;// new com.PixelUtilitys.tools.WaterstoneHammer(6090, Basemod.WATERSTONE, "WaterstoneHammer", "Waterstone Hammer").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setTextureName("PixelUtilitys:WaterstoneHammer").setUnlocalizedName("WaterstoneHammer");
    public  Item WaterstoneAxe;// new com.PixelUtilitys.tools.WaterstoneAxe(6091, Basemod.WATERSTONE, "pixelutilitys:WaterstoneAxe").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("WaterstoneAxe").setTextureName("PixelUtilitys:WaterstoneAxe");
    public  Item WaterstoneShovel;// new com.PixelUtilitys.tools.WaterstoneShovel(6092, Basemod.WATERSTONE, "pixelutilitys:WaterstoneShovel").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("WaterstoneShovel").setTextureName("PixelUtilitys:WaterstoneShovel");
    public  Item WaterstoneHoe;// new com.PixelUtilitys.tools.WaterstoneHoe(6093, Basemod.WATERSTONE, "pixelutilitys:WaterstoneHoe").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("WaterstoneHoe").setTextureName("pixelutilitys:WaterstoneHoe");
    public  Item WaterstoneSword;// new com.PixelUtilitys.tools.WaterstoneSword(6094, Basemod.WATERSTONE, "pixelutilitys:WaterstoneSword").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("WaterstoneSword").setTextureName("PixelUtilitys:WaterstoneSword");

    //Leafstone tools
    public  Item LeafstonePickaxe;// new com.PixelUtilitys.tools.LeafstonePickaxe(6095, 6.5F, Basemod.LEAFSTONE, "pixelutilitys:LeafstonePickaxe").setTextureName("PixelUtilitys:LeafstonePickaxe").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("LeafstonePickaxe");
    public  Item LeafstoneHammer;// new com.PixelUtilitys.tools.LeafstoneHammer(6096, Basemod.LEAFSTONE, "rubyHammer", "Leafstone Hammer").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setTextureName("PixelUtilitys:LeafstoneHammer").setUnlocalizedName("LeafstoneHammer");
    public  Item LeafstoneAxe;// new com.PixelUtilitys.tools.LeafstoneAxe(6097, Basemod.LEAFSTONE, "pixelutilitys:LeafstoneAxe").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("LeafstoneAxe").setTextureName("PixelUtilitys:LeafstoneAxe");
    public  Item LeafstoneShovel;// new com.PixelUtilitys.tools.LeafstoneShovel(6098, Basemod.LEAFSTONE, "pixelutilitys:LeafstoneShovel").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("LeafstoneShovel").setTextureName("PixelUtilitys:LeafstoneShovel");
    public  Item LeafstoneHoe;// new com.PixelUtilitys.tools.LeafstoneHoe(6099, Basemod.LEAFSTONE, "pixelutilitys:LeafstoneHoe").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("LeafstoneHoe").setTextureName("pixelutilitys:LeafstoneHoe");
    public  Item LeafstoneSword;// new com.PixelUtilitys.tools.LeafstoneSword(6100, Basemod.LEAFSTONE, "pixelutilitys:LeafstoneSword").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("LeafstoneSword").setTextureName("PixelUtilitys:LeafstoneSword");

    //Ruby Tools
    public  Item CrystalPickaxe;
    public  Item CrystalHammer;
    public  Item CrystalAxe;
    public  Item CrystalShovel;
    public  Item CrystalHoe;
    public  Item CrystalSword;
    private  List<Item> toolList = new ArrayList<Item>();
    //Reflection to allow non pixelmon use
    public  Constructor PixelHammerToolConstructor = null;

    public void load(Configuration configuration){

        try {
            if (Basemod.pixelmonPresent)
                PixelHammerToolConstructor = Class.forName("com.pixelutilitys.items.tools.PixelHammerTool").getConstructor(Item.ToolMaterial.class, String.class, String.class);
            else
                PixelHammerToolConstructor = Class.forName("com.pixelutilitys.items.tools.BaseHammerTool").getConstructor(Item.ToolMaterial.class, String.class, String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Ruby Tools
        rubyPickaxe = new PixelPickaxeTool(Basemod.RUBY, "pixelutilitys:RubyPickaxe", "rubyPickaxe");
        rubyHammer = registerHammer(Basemod.RUBY, "pixelutilitys:RubyHammer", "rubyHammer");
        rubyAxe = new PixelAxeTool(Basemod.RUBY, "pixelutilitys:RubyAxe", "rubyAxe");
        rubyShovel = new PixelShovelTool(Basemod.RUBY, "pixelutilitys:RubyShovel", "rubyShovel");
        rubyHoe = new PixelHoeTool(Basemod.RUBY, "pixelutilitys:RubyHoe", "rubyHoe");
        rubySword = new PixelSwordTool(Basemod.RUBY, "pixelutilitys:RubySword", "rubySword");

        //Saphire Tools
        saphirePickaxe = new PixelPickaxeTool(Basemod.SAPHIRE, "pixelutilitys:SaphirePickaxe", "saphirePickaxe");
        saphireHammer = registerHammer(Basemod.SAPHIRE, "pixelutilitys:SaphireHammer", "saphireHammer");
        saphireAxe = new PixelAxeTool(Basemod.SAPHIRE, "pixelutilitys:SaphireAxe", "saphireAxe");
        saphireShovel = new PixelShovelTool(Basemod.SAPHIRE, "pixelutilitys:SaphireShovel", "saphireShovel");
        saphireHoe = new PixelHoeTool(Basemod.SAPHIRE, "pixelutilitys:SaphireHoe", "saphireHoe");
        saphireSword = new PixelSwordTool(Basemod.SAPHIRE, "pixelutilitys:SaphireSword", "saphireSword");

        //Amethyst Tools
        amethystPickaxe = new PixelPickaxeTool(Basemod.AMETHYST, "pixelutilitys:AmethystPickaxe", "amethystPickaxe");
        amethystHammer = registerHammer(Basemod.AMETHYST, "pixelutilitys:AmethystHammer", "amethystHammer");
        amethystAxe = new PixelAxeTool(Basemod.AMETHYST, "pixelutilitys:AmethystAxe", "amethystAxe");
        amethystShovel = new PixelShovelTool(Basemod.AMETHYST, "pixelutilitys:AmethystShovel", "amethystShovel");
        amethystHoe = new PixelHoeTool(Basemod.AMETHYST, "pixelutilitys:AmethystHoe", "amethystHoe");
        amethystSword = new PixelSwordTool(Basemod.AMETHYST, "pixelutilitys:AmethystSword", "amethystSword");

        //EvoTools
        //Firestone tools
        FirestonePickaxe = new PixelPickaxeTool(Basemod.FIRESTONE, "pixelutilitys:FirestonePickaxe", "firestonePickaxe");
        FirestoneHammer = registerHammer(Basemod.FIRESTONE, "pixelutilitys:FirestoneHammer", "firestoneHammer");
        FirestoneAxe = new PixelAxeTool(Basemod.FIRESTONE, "pixelutilitys:FirestoneAxe", "firestoneAxe");
        FirestoneShovel = new PixelShovelTool(Basemod.FIRESTONE, "pixelutilitys:FirestoneShovel", "firestoneShovel");
        FirestoneHoe = new PixelHoeTool(Basemod.FIRESTONE, "pixelutilitys:FirestoneHoe", "firestoneHoe");
        FirestoneSword = new PixelSwordTool(Basemod.FIRESTONE, "pixelutilitys:FirestoneSword", "firestoneSword");

        //Waterstone tools
        WaterstonePickaxe = new PixelPickaxeTool(Basemod.WATERSTONE, "pixelutilitys:WaterstonePickaxe", "waterstonePickaxe");
        WaterstoneHammer = registerHammer(Basemod.WATERSTONE, "pixelutilitys:WaterstoneHammer", "waterstoneHammer");
        WaterstoneAxe = new PixelAxeTool(Basemod.WATERSTONE, "pixelutilitys:WaterstoneAxe", "waterstoneAxe");
        WaterstoneShovel = new PixelShovelTool(Basemod.WATERSTONE, "pixelutilitys:WaterstoneShovel", "waterstoneShovel");
        WaterstoneHoe = new PixelHoeTool(Basemod.WATERSTONE, "pixelutilitys:WaterstoneHoe", "waterstoneHoe");
        WaterstoneSword = new PixelSwordTool(Basemod.WATERSTONE, "pixelutilitys:WaterstoneSword", "waterstoneSword");

        //Leafstone tools
        LeafstonePickaxe = new PixelPickaxeTool(Basemod.LEAFSTONE, "pixelutilitys:LeafstonePickaxe", "leafstonePickaxe");
        LeafstoneHammer = registerHammer(Basemod.LEAFSTONE, "pixelutilitys:LeafstoneHammer", "leafstoneHammer");
        LeafstoneAxe = new PixelAxeTool(Basemod.LEAFSTONE, "pixelutilitys:LeafstoneAxe", "leafstoneAxe");
        LeafstoneShovel = new PixelShovelTool(Basemod.LEAFSTONE, "pixelutilitys:LeafstoneShovel", "leafstoneShovel");
        LeafstoneHoe = new PixelHoeTool(Basemod.LEAFSTONE, "pixelutilitys:LeafstoneHoe", "leafstoneHoe");
        LeafstoneSword = new PixelSwordTool(Basemod.LEAFSTONE, "pixelutilitys:LeafstoneSword", "leafstoneSword");

        //Crystal tools
        CrystalPickaxe = new PixelPickaxeTool(Basemod.CRYSTAL, "pixelutilitys:CrystalPickaxe", "crystalPickaxe");
        CrystalHammer = registerHammer(Basemod.CRYSTAL, "pixelutilitys:CrystalHammer", "crystalHammer");
        CrystalAxe = new PixelAxeTool(Basemod.CRYSTAL, "pixelutilitys:CrystalAxe", "crystalAxe");
        CrystalShovel = new PixelShovelTool(Basemod.CRYSTAL, "pixelutilitys:CrystalShovel", "crystalShovel");
        CrystalHoe = new PixelHoeTool(Basemod.CRYSTAL, "pixelutilitys:CrystalHoe", "crystalHoe");
        CrystalSword = new PixelSwordTool(Basemod.CRYSTAL, "pixelutilitys:CrystalSword", "crystalSword");

    }

    private  Item registerHammer(Item.ToolMaterial toolMaterial, String itemString, String itemName) {//hacky ass method because the jvm won't play nice
        try {
            Item hammer = (Item) PixelHammerToolConstructor.newInstance(toolMaterial, itemString, itemName);
            return hammer;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public  void addNames() {

        GameRegistry.registerItem(rubyPickaxe, "Ruby Pickaxe");
        GameRegistry.registerItem(rubyAxe, "Ruby Axe");
        GameRegistry.registerItem(rubyShovel, "Ruby Shovel");
        GameRegistry.registerItem(rubyHoe, "Ruby Hoe");
        GameRegistry.registerItem(rubySword, "Ruby Sword");
        GameRegistry.registerItem(rubyHammer, "Ruby Hammer");

        GameRegistry.registerItem(saphirePickaxe, "Saphire Pickaxe");
        GameRegistry.registerItem(saphireAxe, "Saphire Axe");
        GameRegistry.registerItem(saphireShovel, "Saphire Shovel");
        GameRegistry.registerItem(saphireHoe, "Saphire Hoe");
        GameRegistry.registerItem(saphireSword, "Saphire Sword");
        GameRegistry.registerItem(saphireHammer, "Saphire Hammer");

        GameRegistry.registerItem(amethystPickaxe, "Amethyst Pickaxe");
        GameRegistry.registerItem(amethystAxe, "Amethyst Axe");
        GameRegistry.registerItem(amethystShovel, "Amethyst Shovel");
        GameRegistry.registerItem(amethystHoe, "Amethyst Hoe");
        GameRegistry.registerItem(amethystSword, "Amethyst Sword");
        GameRegistry.registerItem(amethystHammer, "Amethyst Hammer");

        GameRegistry.registerItem(FirestonePickaxe, "Firestone Pickaxe");
        GameRegistry.registerItem(FirestoneAxe, "Firestone Axe");
        GameRegistry.registerItem(FirestoneHoe, "Firestone Hoe");
        GameRegistry.registerItem(FirestoneSword, "Firestone Sword");
        GameRegistry.registerItem(FirestoneShovel, "Firestone Shovel");
        GameRegistry.registerItem(FirestoneHammer, "Firestone Hammer");


        GameRegistry.registerItem(WaterstonePickaxe, "Waterstone Pickaxe");
        GameRegistry.registerItem(WaterstoneAxe, "Waterstone Axe");
        GameRegistry.registerItem(WaterstoneHoe, "Waterstone Hoe");
        GameRegistry.registerItem(WaterstoneSword, "Waterstone Sword");
        GameRegistry.registerItem(WaterstoneShovel, "Waterstone Shovel");
        GameRegistry.registerItem(WaterstoneHammer, "Waterstone Hammer");

        GameRegistry.registerItem(LeafstonePickaxe, "Leafstone Pickaxe");
        GameRegistry.registerItem(LeafstoneAxe, "Leafstone Axe");
        GameRegistry.registerItem(LeafstoneHoe, "Leafstone Hoe");
        GameRegistry.registerItem(LeafstoneSword, "Leafstone Sword");
        GameRegistry.registerItem(LeafstoneShovel, "Leafstone Shovel");
        GameRegistry.registerItem(LeafstoneHammer, "Leafstone Hammer");

        GameRegistry.registerItem(CrystalPickaxe, "Crystal Pickaxe");
        GameRegistry.registerItem(CrystalAxe, "Crystal Axe");
        GameRegistry.registerItem(CrystalHoe, "Crystal Hoe");
        GameRegistry.registerItem(CrystalSword, "Crystal Sword");
        GameRegistry.registerItem(CrystalShovel, "Crystal Shovel");
        GameRegistry.registerItem(CrystalHammer, "Crystal Hammer");

    }

    public List<Item> getToolList() {
        return toolList;
    }

    public void setToolList(List<Item> toolList) {
        this.toolList = toolList;
    }
}