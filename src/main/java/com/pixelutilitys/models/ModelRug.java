package com.pixelutilitys.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelRug extends ModelBase {
    //fields
    ModelRenderer Rug;

    public ModelRug() {
        textureWidth = 128;
        textureHeight = 128;

        Rug = new ModelRenderer(this, 0, 0);
        Rug.addBox(0F, 0F, 0F, 32, 0, 16);
        Rug.setRotationPoint(-8F, 23.9F, -8F);
        Rug.setTextureSize(128, 128);
        Rug.mirror = true;
        setRotation(Rug, 0F, 0F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Rug.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }

}
