// Date: 2/7/2014 9:16:12 PM
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX


package com.pixelutilitys.models.Pokedolls;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class GalladePokedollModel extends ModelBase {
    //fields
    ModelRenderer Leg_Right;
    ModelRenderer Feet_Right;
    ModelRenderer Leg_Left;
    ModelRenderer Feet_Left;
    ModelRenderer Body_Bottom;
    ModelRenderer Body_Middle;
    ModelRenderer Body_Spike;
    ModelRenderer Arm_Left;
    ModelRenderer Arm_Blade_Left;
    ModelRenderer Arm_Blade_Tip_Left;
    ModelRenderer Arm_Right;
    ModelRenderer Arm_Blade_Right;
    ModelRenderer Arm_Blade_Tip_Right;
    ModelRenderer Head;
    ModelRenderer Head_Spike;

    public GalladePokedollModel() {
        textureWidth = 128;
        textureHeight = 64;

        Leg_Right = new ModelRenderer(this, 24, 35);
        Leg_Right.addBox(-1F, -1F, -5F, 2, 2, 5);
        Leg_Right.setRotationPoint(-2F, 22.5F, 2F);
        Leg_Right.setTextureSize(64, 32);
        Leg_Right.mirror = true;
        setRotation(Leg_Right, 0F, 0.2617994F, 0F);
        Feet_Right = new ModelRenderer(this, 24, 27);
        Feet_Right.addBox(-1.5F, -1.5F, -10F, 3, 3, 5);
        Feet_Right.setRotationPoint(-2F, 22.5F, 2F);
        Feet_Right.setTextureSize(64, 32);
        Feet_Right.mirror = true;
        setRotation(Feet_Right, 0F, 0.2617994F, 0F);
        Leg_Left = new ModelRenderer(this, 0, 8);
        Leg_Left.addBox(-1F, -1F, -5F, 2, 2, 5);
        Leg_Left.setRotationPoint(2F, 22.5F, 2F);
        Leg_Left.setTextureSize(64, 32);
        Leg_Left.mirror = true;
        setRotation(Leg_Left, 0F, -0.2617994F, 0F);
        Feet_Left = new ModelRenderer(this, 0, 0);
        Feet_Left.addBox(-1.5F, -1.5F, -10F, 3, 3, 5);
        Feet_Left.setRotationPoint(2F, 22.5F, 2F);
        Feet_Left.setTextureSize(64, 32);
        Feet_Left.mirror = true;
        setRotation(Feet_Left, 0F, -0.2617994F, 0F);
        Body_Bottom = new ModelRenderer(this, 16, 0);
        Body_Bottom.addBox(-3.5F, -1.5F, -2F, 7, 3, 4);
        Body_Bottom.setRotationPoint(0F, 22.5F, 2F);
        Body_Bottom.setTextureSize(64, 32);
        Body_Bottom.mirror = true;
        setRotation(Body_Bottom, 0F, 0F, 0F);
        Body_Middle = new ModelRenderer(this, 16, 7);
        Body_Middle.addBox(-2.5F, -8.5F, -1.5F, 5, 7, 3);
        Body_Middle.setRotationPoint(0F, 22.5F, 2F);
        Body_Middle.setTextureSize(64, 32);
        Body_Middle.mirror = true;
        setRotation(Body_Middle, 0F, 0F, 0F);
        Body_Spike = new ModelRenderer(this, 16, 17);
        Body_Spike.addBox(0F, -7.5F, -3.5F, 1, 3, 7);
        Body_Spike.setRotationPoint(0F, 22.5F, 2F);
        Body_Spike.setTextureSize(64, 32);
        Body_Spike.mirror = true;
        setRotation(Body_Spike, 0F, 0F, 0F);
        Arm_Left = new ModelRenderer(this, 0, 15);
        Arm_Left.addBox(0F, 0F, -0.5F, 1, 5, 1);
        Arm_Left.setRotationPoint(1.5F, 14.5F, 2F);
        Arm_Left.setTextureSize(64, 32);
        Arm_Left.mirror = true;
        setRotation(Arm_Left, 0F, 0F, -0.5235988F);
        Arm_Blade_Left = new ModelRenderer(this, 4, 15);
        Arm_Blade_Left.addBox(0F, 4F, 2F, 1, 6, 2);
        Arm_Blade_Left.setRotationPoint(1.5F, 14.5F, 2F);
        Arm_Blade_Left.setTextureSize(64, 32);
        Arm_Blade_Left.mirror = true;
        setRotation(Arm_Blade_Left, -0.5235988F, 0F, -0.5235988F);
        Arm_Blade_Tip_Left = new ModelRenderer(this, 0, 21);
        Arm_Blade_Tip_Left.addBox(0F, 3F, 3F, 1, 1, 1);
        Arm_Blade_Tip_Left.setRotationPoint(1.5F, 14.5F, 2F);
        Arm_Blade_Tip_Left.setTextureSize(64, 32);
        Arm_Blade_Tip_Left.mirror = true;
        setRotation(Arm_Blade_Tip_Left, -0.5235988F, 0F, -0.5235988F);
        Arm_Right = new ModelRenderer(this, 10, 23);
        Arm_Right.addBox(-1F, 0F, -0.5F, 1, 5, 1);
        Arm_Right.setRotationPoint(-1.5F, 14.5F, 2F);
        Arm_Right.setTextureSize(64, 32);
        Arm_Right.mirror = true;
        setRotation(Arm_Right, 0F, 0F, 0.5235988F);
        Arm_Blade_Right = new ModelRenderer(this, 10, 15);
        Arm_Blade_Right.addBox(-1F, 4F, 2F, 1, 6, 2);
        Arm_Blade_Right.setRotationPoint(-1.5F, 14.5F, 2F);
        Arm_Blade_Right.setTextureSize(64, 32);
        Arm_Blade_Right.mirror = true;
        setRotation(Arm_Blade_Right, -0.5235988F, 0F, 0.5235988F);
        Arm_Blade_Tip_Right = new ModelRenderer(this, 10, 29);
        Arm_Blade_Tip_Right.addBox(-1F, 3F, 3F, 1, 1, 1);
        Arm_Blade_Tip_Right.setRotationPoint(-1.5F, 14.5F, 2F);
        Arm_Blade_Tip_Right.setTextureSize(64, 32);
        Arm_Blade_Tip_Right.mirror = true;
        setRotation(Arm_Blade_Tip_Right, -0.5235988F, 0F, 0.5235988F);
        Head = new ModelRenderer(this, 0, 32);
        Head.addBox(-3F, -6F, -3F, 6, 6, 6);
        Head.setRotationPoint(0F, 14F, 2F);
        Head.setTextureSize(64, 32);
        Head.mirror = true;
        setRotation(Head, 0F, 0F, 0F);
        Head_Spike = new ModelRenderer(this, 0, 23);
        Head_Spike.addBox(-1F, -11F, -1F, 1, 5, 4);
        Head_Spike.setRotationPoint(0F, 14F, 2F);
        Head_Spike.setTextureSize(64, 32);
        Head_Spike.mirror = true;
        setRotation(Head_Spike, 0F, 0F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Leg_Right.render(f5);
        Feet_Right.render(f5);
        Leg_Left.render(f5);
        Feet_Left.render(f5);
        Body_Bottom.render(f5);
        Body_Middle.render(f5);
        Body_Spike.render(f5);
        Arm_Left.render(f5);
        Arm_Blade_Left.render(f5);
        Arm_Blade_Tip_Left.render(f5);
        Arm_Right.render(f5);
        Arm_Blade_Right.render(f5);
        Arm_Blade_Tip_Right.render(f5);
        Head.render(f5);
        Head_Spike.render(f5);
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
