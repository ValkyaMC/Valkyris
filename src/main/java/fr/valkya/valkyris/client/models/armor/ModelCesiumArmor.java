package fr.valkya.valkyris.client.models.armor;

import fr.valkya.valkyris.References;
import fr.valkya.valkyris.client.models.ModelRenderOBJ;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class ModelCesiumArmor extends ModelBiped {
	
	public ModelRenderOBJ head;
    public ModelRenderOBJ body;
    public ModelRenderOBJ rightArm;
    public ModelRenderOBJ leftArm;
    public ModelRenderOBJ rightLeg;
    public ModelRenderOBJ leftLeg;
    public ModelRenderOBJ rightBoot;
    public ModelRenderOBJ leftBoot;
	
	public ModelCesiumArmor(float f, boolean isHelmet, boolean isChestplate, boolean isLeggings, boolean isBoots) {
		super(f, 0.0f, 128, 128);
		
		this.bipedHead = new ModelRenderer(this, 0, 0);
	    this.bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);
	    this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);

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
        
        this.head = new ModelRenderOBJ(this, "head", new ResourceLocation(References.MODID, "models/armor/cesium/helmet.obj"), new ResourceLocation(References.MODID, "textures/models/armor/cesium/helmet.png"));
        
        this.body = new ModelRenderOBJ(this, "body", new ResourceLocation(References.MODID, "models/armor/cesium/body.obj"), new ResourceLocation(References.MODID, "textures/models/armor/cesium/body.png"));
        
        this.rightArm = new ModelRenderOBJ(this, "rightArm", new ResourceLocation(References.MODID, "models/armor/cesium/rightArm.obj"), new ResourceLocation(References.MODID, "textures/models/armor/cesium/rightArm.png"));
        this.leftArm = new ModelRenderOBJ(this, "leftArm", new ResourceLocation(References.MODID, "models/armor/cesium/leftArm.obj"), new ResourceLocation(References.MODID, "textures/models/armor/cesium/leftArm.png"));
        
        this.rightLeg = new ModelRenderOBJ(this, "rightLeg", new ResourceLocation(References.MODID, "models/armor/cesium/rightLeg.obj"), new ResourceLocation(References.MODID, "textures/models/armor/cesium/rightLeg.png"));
        this.leftLeg = new ModelRenderOBJ(this, "leftLeg", new ResourceLocation(References.MODID, "models/armor/cesium/leftLeg.obj"), new ResourceLocation(References.MODID, "textures/models/armor/cesium/leftLeg.png"));
        
        this.rightBoot = new ModelRenderOBJ(this, "rightBoot", new ResourceLocation(References.MODID, "models/armor/cesium/rightFoot.obj"), new ResourceLocation(References.MODID, "textures/models/armor/cesium/rightFoot.png"));
        this.leftBoot = new ModelRenderOBJ(this, "leftBoot", new ResourceLocation(References.MODID, "models/armor/cesium/leftFoot.obj"), new ResourceLocation(References.MODID, "textures/models/armor/cesium/leftFoot.png"));
        
        this.bipedHead.cubeList.clear();
        this.bipedHeadwear.cubeList.clear();
        this.bipedBody.cubeList.clear();
        this.bipedRightArm.cubeList.clear();
        this.bipedLeftArm.cubeList.clear();
        this.bipedLeftLeg.cubeList.clear();
        this.bipedRightLeg.cubeList.clear();
        
        /*Casque / Helmet*/
        
        head.offsetX = -0.325F;
        head.offsetY = 0.375F;
        head.offsetZ = -0.375F;
        head.scale = 0.18F;
        head.rotateAngleY = 4.65F;
        
        /*Corps / Body*/
        
        body.offsetX = -0.35F;
        body.offsetY = 1.1F;
        body.offsetZ = -0.156F;
        body.scale = 0.16F;
        body.rotateAngleY = -1.56F;
        
        /*Bras / Arms*/
        rightArm.offsetX = -0.32F;
        rightArm.offsetY = 1F;
        rightArm.offsetZ = -0.18F;
        rightArm.scale = 0.175F;
        rightArm.rotateAngleY = -1.6F;
        
        leftArm.offsetX = -0.12F;
        leftArm.offsetY = 1F;
        leftArm.offsetZ = -0.17F;
        leftArm.scale = 0.175F;
        leftArm.rotateAngleY = -1.6F;
        
        /*Jambes / Legs*/
        
        rightLeg.offsetX = -0.26F;
        rightLeg.offsetY = 1F;
        rightLeg.offsetZ = -0.17F;
        rightLeg.rotateAngleY = 4.73F;
        rightLeg.scale = 0.17F;
        
        leftLeg.offsetX = -0.161F;
        leftLeg.offsetY = 1F;
        leftLeg.offsetZ = -0.17F;
        leftLeg.rotateAngleY = 4.73F;
        leftLeg.scale = 0.17F;
        
        /*Chaussures / Boots */
        
        rightBoot.offsetX = -0.18F;
        rightBoot.offsetY = 1.13F;
        rightBoot.offsetZ = 0.17F;
        rightBoot.scale = 0.18F;
        
        leftBoot.offsetX = 0.18F;
        leftBoot.offsetY = 1.13F;
        leftBoot.offsetZ = -0.19F;
        leftBoot.scale = 0.18F;
        leftBoot.rotateAngleY = 3.13F;
        
        if (isHelmet) {
            this.bipedHead.addChild(head);
        }
        if (isChestplate) {
            this.bipedBody.addChild(body);
            this.bipedLeftArm.addChild(leftArm);
            this.bipedRightArm.addChild(rightArm);
        }
        if (isLeggings) {
            this.bipedLeftLeg.addChild(leftLeg);
            this.bipedRightLeg.addChild(rightLeg);
        }
        if (isBoots) {
            this.bipedLeftLeg.addChild(leftBoot);
            this.bipedRightLeg.addChild(rightBoot);
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
        this.leftLeg.rotationPointZ = 0F;
        this.rightLeg.rotationPointZ = 0F;
        this.bipedRightArm.rotateAngleZ = 0.0F;
        this.bipedLeftArm.rotateAngleZ = 0.0F;
    }

}
