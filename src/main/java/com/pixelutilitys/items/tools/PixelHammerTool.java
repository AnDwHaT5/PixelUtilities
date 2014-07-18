package com.pixelutilitys.items.tools;

import com.pixelmonmod.pixelmon.config.PixelmonBlocks;
import com.pixelmonmod.pixelmon.items.ItemHammer;
import com.pixelutilitys.Basemod;
import com.pixelutilitys.config.PixelUtilitysTools;
import com.pixelutilitys.creativetabs.PixelUtilitysCreativeTabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class PixelHammerTool extends ItemHammer {

    public PixelHammerTool(ToolMaterial par3EnumToolMaterial, String itemString, String itemName) {
        super(par3EnumToolMaterial, itemString, itemName);

        setTextureName(itemString);
        setUnlocalizedName(itemName);
        setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools);
    }


    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister) {
        this.itemIcon = par1IconRegister.registerIcon(this.getIconString());
    }

    @Override
    public Item setCreativeTab(CreativeTabs tabs) {
        super.setCreativeTab(tabs);
        if (!PixelUtilitysTools.getInstance().getToolList().contains(this))
            PixelUtilitysTools.getInstance().getToolList().add(this);
        return null;
    }

    @Override
    public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block) {
        if (par2Block == PixelmonBlocks.anvil) {
            if (toolMaterial == ToolMaterial.WOOD)
                return 1;
            else if (toolMaterial == ToolMaterial.STONE)
                return 2;
            else if (toolMaterial == ToolMaterial.IRON)
                return 3;
            else if (toolMaterial == ToolMaterial.GOLD)
                return 4;
            else if (toolMaterial == ToolMaterial.EMERALD)
                return 5;
            else if (toolMaterial == Basemod.RUBY)
                return 4;
            else if (toolMaterial == Basemod.SAPHIRE)
                return 4;
            else if (toolMaterial == Basemod.FIRESTONE)
                return 6;
            else if (toolMaterial == Basemod.WATERSTONE)
                return 6;
            else if (toolMaterial == Basemod.LEAFSTONE)
                return 4;
        }
        return 1;
    }

}
