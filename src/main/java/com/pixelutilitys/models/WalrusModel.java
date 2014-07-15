package com.pixelutilitys.models;


import net.minecraft.client.model.ModelBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class WalrusModel extends ModelBase {
    public IModelCustom model;

    public WalrusModel() {
        ResourceLocation model = new ResourceLocation("/assets/pixelutilitys/models/WalrusStatue.obj");
        model = (ResourceLocation) AdvancedModelLoader.loadModel(model);
    }
}