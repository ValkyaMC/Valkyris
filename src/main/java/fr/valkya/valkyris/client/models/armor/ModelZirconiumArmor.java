package fr.valkya.valkyris.client.models.armor;

import fr.valkya.valkyris.References;
import fr.valkya.valkyris.client.models.ModelRenderOBJ;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class ModelZirconiumArmor extends ModelBiped {
	
	public ModelRenderOBJ zirconiumhead;
    public ModelRenderOBJ zirconiumbody;
    public ModelRenderOBJ zirconiumrightArm;
    public ModelRenderOBJ zirconiumleftArm;
    public ModelRenderOBJ zirconiumrightLeg;
    public ModelRenderOBJ zirconiumleftLeg;
    public ModelRenderOBJ zirconiumrightBoot;
    public ModelRenderOBJ zirconiumleftBoot;
	
	public ModelZirconiumArmor(float f, boolean isHelmet, boolean isChestplate, boolean isLeggings, boolean isBoots) {
		super(f, 0.0f, 128, 128);
		
		this.bipedHead = new ModelRenderer(this, 0, 0);
	    this.bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);
	    this.bipedHead.addBox(-8.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);

	    this.bipedBody = new ModelRenderer(this, 16, 16);
	    this.bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);
	    this.bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);

	    this.bipedLeftArm = new ModelRenderer(this, 40, 16);
	    this.bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
	    this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);

	    this.bipedRightArm = new ModelRenderer(this, 40, 16);
	    this.bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
	    this.bipedRightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);

	    this.bipedLeftLeg = new ModelRenderer(this, 0, 16);
	    this.bipedLeftLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
	    this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);

        this.bipedRightLeg = new ModelRenderer(this, 0, 16);
        this.bipedRightLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
        this.bipedRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        
        this.zirconiumhead = new ModelRenderOBJ(this, "zirconiumhead", new ResourceLocation(References.MODID, "models/armor/zirconium/helmet.obj"), new ResourceLocation(References.MODID, "textures/models/armor/zirconium/helmet.png"));
        
        this.zirconiumbody = new ModelRenderOBJ(this, "zirconiumbody", new ResourceLocation(References.MODID, "models/armor/zirconium/body.obj"), new ResourceLocation(References.MODID, "textures/models/armor/zirconium/body.png"));
        
        this.zirconiumrightArm = new ModelRenderOBJ(this, "zirconiumrightArm", new ResourceLocation(References.MODID, "models/armor/zirconium/rightArm.obj"), new ResourceLocation(References.MODID, "textures/models/armor/zirconium/rightArm.png"));
        this.zirconiumleftArm = new ModelRenderOBJ(this, "zirconiumleftArm", new ResourceLocation(References.MODID, "models/armor/zirconium/leftArm.obj"), new ResourceLocation(References.MODID, "textures/models/armor/zirconium/leftArm.png"));
        
        this.zirconiumrightLeg = new ModelRenderOBJ(this, "zirconiumrightLeg", new ResourceLocation(References.MODID, "models/armor/zirconium/rightLeg.obj"), new ResourceLocation(References.MODID, "textures/models/armor/zirconium/rightLeg.png"));
        this.zirconiumleftLeg = new ModelRenderOBJ(this, "zirconiumleftLeg", new ResourceLocation(References.MODID, "models/armor/zirconium/leftLeg.obj"), new ResourceLocation(References.MODID, "textures/models/armor/zirconium/leftLeg.png"));
        
        this.zirconiumrightBoot = new ModelRenderOBJ(this, "zirconiumrightBoot", new ResourceLocation(References.MODID, "models/armor/zirconium/boots.obj"), new ResourceLocation(References.MODID, "textures/models/armor/zirconium/boots.png"));
        this.zirconiumleftBoot = new ModelRenderOBJ(this, "zirconiumleftBoot", new ResourceLocation(References.MODID, "models/armor/zirconium/boots.obj"), new ResourceLocation(References.MODID, "textures/models/armor/zirconium/boots.png"));
        
        this.bipedHead.cubeList.clear();
        this.bipedHeadwear.cubeList.clear();
        this.bipedBody.cubeList.clear();
        this.bipedRightArm.cubeList.clear();
        this.bipedLeftArm.cubeList.clear();
        this.bipedLeftLeg.cubeList.clear();
        this.bipedRightLeg.cubeList.clear();
        
        /*Casque / Helmet*/
        
        zirconiumhead.offsetZ = -0.355f;
        zirconiumhead.offsetX = -0.378F;
        zirconiumhead.offsetY = 0.35F;
        zirconiumhead.rotateAngleY = -1.555f;
        zirconiumhead.scale = 0.16F;
        
        /*Corps / Body*/
        
        zirconiumbody.rotateAngleY = -1.5725f;
        zirconiumbody.offsetX = -0.35F;
        zirconiumbody.offsetY = 0.95F;
        zirconiumbody.offsetZ = -0.2525F;
        zirconiumbody.scale = 0.14F;
        
        /* Bras / Arms*/
        zirconiumrightArm.offsetX = -0.41F;
        zirconiumrightArm.offsetY = 1.15F;
        zirconiumrightArm.offsetZ = -0.2225F;
        zirconiumrightArm.scale = 0.1475F;
        zirconiumrightArm.rotateAngleY = -1.575f;
        
        zirconiumleftArm.offsetX = -0.09F;
        zirconiumleftArm.offsetY = 1.15F;
        zirconiumleftArm.offsetZ = -0.2175F;
        zirconiumleftArm.scale = 0.1475F;
        zirconiumleftArm.rotateAngleY = -1.575f;
        
        /*Jambes / Legs*/
        
        zirconiumrightLeg.offsetX = -0.225F;
        zirconiumrightLeg.offsetY = .94F;
        zirconiumrightLeg.offsetZ = -0.255f;
        zirconiumrightLeg.rotateAngleY = -1.575f;
        zirconiumrightLeg.scale = 0.1475F;
        
        zirconiumleftLeg.offsetX = -0.15F;
        zirconiumleftLeg.offsetY = .94F;
        zirconiumleftLeg.offsetZ = -0.255f;
        zirconiumleftLeg.rotateAngleY = -1.575f;
        zirconiumleftLeg.scale = 0.1475F;
        
        /*Chaussures / Boots */
        
        zirconiumrightBoot.offsetX = -0.16F;
        zirconiumrightBoot.offsetY = 1.06F;
        zirconiumrightBoot.offsetZ = 0.155F;
        zirconiumrightBoot.scale = 0.1475F;
        
        zirconiumleftBoot.offsetX = 0.16F;
        zirconiumleftBoot.offsetY = 1.06F;
        zirconiumleftBoot.offsetZ = -0.165F;
        zirconiumleftBoot.scale = 0.1475F;
        zirconiumleftBoot.rotateAngleY = -1.575f;
        zirconiumleftBoot.rotateAngleY += -1.575f;
        
        if (isHelmet) {
            this.bipedHead.addChild(zirconiumhead);
        }
        if (isChestplate) {
            this.bipedBody.addChild(zirconiumbody);
            this.bipedLeftArm.addChild(zirconiumleftArm);
            this.bipedRightArm.addChild(zirconiumrightArm);
        }
        if (isLeggings) {
            this.bipedLeftLeg.addChild(zirconiumleftLeg);
            this.bipedRightLeg.addChild(zirconiumrightLeg);
        }
        if (isBoots) {
            this.bipedLeftLeg.addChild(zirconiumleftBoot);
            this.bipedRightLeg.addChild(zirconiumrightBoot);
        }
	}
	
	@Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        if (entity == null) {
            isSneak = false;
            isRiding = false;
            isChild = false;
            aimedBow = false;

            this.bipedRightArm.rotateAngleX = 0F;
            this.bipedRightArm.rotateAngleY = 0F;
            this.bipedRightArm.rotateAngleZ = 0F;
            this.bipedLeftArm.rotateAngleX = 0F;
            this.bipedLeftArm.rotateAngleY = 0F;
            this.bipedLeftArm.rotateAngleZ = 0F;

            bipedBody.rotateAngleX = 0F;
            bipedBody.rotateAngleY = 0F;
            bipedBody.rotateAngleZ = 0F;

            bipedHead.rotateAngleX = 0F;
            bipedHead.rotateAngleY = 0F;
            bipedHead.rotateAngleZ = 0F;

            bipedLeftLeg.rotateAngleX = 0F;
            bipedLeftLeg.rotateAngleY = 0F;
            bipedLeftLeg.rotateAngleZ = 0F;

            bipedRightLeg.rotateAngleX = 0F;
            bipedRightLeg.rotateAngleY = 0F;
            bipedRightLeg.rotateAngleZ = 0F;

            setRotationAngles(0, 0, 0, 0, 0, 0, null);
        } else super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        this.bipedHead.render(1F / 13F);
        this.bipedRightArm.render(1F / 15F);
        this.bipedLeftArm.render(1F / 15F);
        this.bipedBody.render(1F / 15F);
        this.bipedRightLeg.render(1F / 16F);
        this.bipedLeftLeg.render(1F / 16F);
    }

    public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float scale, Entity p_78087_7_) {
        this.bipedRightArm.rotateAngleZ = 0.0F;
        this.bipedLeftArm.rotateAngleZ = 0.0F;
        this.bipedRightArm.rotationPointZ = 0.0F;
        this.bipedLeftArm.rotationPointZ = 0.0F;
        this.bipedRightLeg.rotateAngleY = 0.0F;
        this.bipedLeftLeg.rotateAngleY = 0.0F;
        this.bipedRightArm.rotateAngleY = 0.0F;
        this.bipedLeftArm.rotateAngleY = 0.0F;
        this.bipedBody.rotateAngleX = 0.0F;
        this.bipedRightLeg.rotationPointZ = 0.1F;
        this.bipedLeftLeg.rotationPointZ = 0.1F;
        this.bipedRightLeg.rotationPointY = 12.0F;
        this.bipedLeftLeg.rotationPointY = 12.0F;
        this.bipedHead.rotationPointY = 0.0F;
        this.bipedHeadwear.rotationPointY = 0.0F;
        this.zirconiumleftLeg.rotationPointZ = 0F;
        this.zirconiumrightLeg.rotationPointZ = 0F;
        this.bipedRightArm.rotateAngleZ = 0.0F;
        this.bipedLeftArm.rotateAngleZ = 0.0F;
    }

}
