package fr.valkya.valkyris.client.models.armor;

import fr.valkya.valkyris.References;
import fr.valkya.valkyris.client.models.ModelRenderOBJ;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class ModelValoArmor extends ModelBiped {
	
	public ModelRenderOBJ head;
    public ModelRenderOBJ body;
    public ModelRenderOBJ rightArm;
    public ModelRenderOBJ leftArm;
    public ModelRenderOBJ rightLeg;
    public ModelRenderOBJ leftLeg;
    public ModelRenderOBJ rightBoot;
    public ModelRenderOBJ leftBoot;
	
	public ModelValoArmor(float f, boolean isHelmet, boolean isChestplate, boolean isLeggings, boolean isBoots) {
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
        
        this.head = new ModelRenderOBJ(this, "head", new ResourceLocation(References.MODID, "models/armor/valkyrite/helmet.obj"), new ResourceLocation(References.MODID, "textures/models/armor/valkyrite/helmet.png"));
        
        this.body = new ModelRenderOBJ(this, "body", new ResourceLocation(References.MODID, "models/armor/valkyrite/body.obj"), new ResourceLocation(References.MODID, "textures/models/armor/valkyrite/body.png"));
        
        this.rightArm = new ModelRenderOBJ(this, "rightArm", new ResourceLocation(References.MODID, "models/armor/valkyrite/rightArm.obj"), new ResourceLocation(References.MODID, "textures/models/armor/valkyrite/arms.png"));
        this.leftArm = new ModelRenderOBJ(this, "leftArm", new ResourceLocation(References.MODID, "models/armor/valkyrite/leftArm.obj"), new ResourceLocation(References.MODID, "textures/models/armor/valkyrite/arms.png"));
        
        this.rightLeg = new ModelRenderOBJ(this, "rightLeg", new ResourceLocation(References.MODID, "models/armor/valkyrite/rightLeg.obj"), new ResourceLocation(References.MODID, "textures/models/armor/valkyrite/rightLeg.png"));
        this.leftLeg = new ModelRenderOBJ(this, "leftLeg", new ResourceLocation(References.MODID, "models/armor/valkyrite/leftLeg.obj"), new ResourceLocation(References.MODID, "textures/models/armor/valkyrite/leftLeg.png"));
        
        this.rightBoot = new ModelRenderOBJ(this, "rightBoot", new ResourceLocation(References.MODID, "models/armor/valkyrite/boots.obj"), new ResourceLocation(References.MODID, "textures/models/armor/valkyrite/boots.png"));
        this.leftBoot = new ModelRenderOBJ(this, "leftBoot", new ResourceLocation(References.MODID, "models/armor/valkyrite/boots.obj"), new ResourceLocation(References.MODID, "textures/models/armor/valkyrite/boots.png"));
        
        this.bipedHead.cubeList.clear();
        this.bipedHeadwear.cubeList.clear();
        this.bipedBody.cubeList.clear();
        this.bipedRightArm.cubeList.clear();
        this.bipedLeftArm.cubeList.clear();
        this.bipedLeftLeg.cubeList.clear();
        this.bipedRightLeg.cubeList.clear();
        
        /*Casque / Helmet*/
        
        head.offsetX -= 0.63F;
        head.offsetY += 0.675F;
        head.offsetZ += 0.51F;
        head.scale = 0.34F;
        
        /*Corps / Body*/
        
        body.offsetX = -0.3F;
        body.offsetY = 0.95F;
        body.offsetZ = 0.18F;
        body.scale = 0.065F;
        
        /*Bras / Arms*/
        rightArm.offsetX = 0.11F;
        rightArm.offsetY = 0.8F;
        rightArm.offsetZ = 0.21F;
        rightArm.scale = 0.09F;
        rightArm.rotateAngleY = 1.5F;
        
        leftArm.offsetX = -0.12F;
        leftArm.offsetY = 0.8F;
        leftArm.offsetZ = -0.2F;
        leftArm.scale = 0.09F;
        leftArm.rotateAngleY = -1.5F;
        
        /*Jambes / Legs*/
        
        rightLeg.offsetX = -0.15F;
        rightLeg.offsetY = 0.7F;
        rightLeg.offsetZ = 0.175F;
        rightLeg.rotateAngleY = 0.06F;
        rightLeg.scale = 0.05F;
        
        leftLeg.offsetX = -0.15F;
        leftLeg.offsetY = 0.7F;
        leftLeg.offsetZ = 0.175F;
        leftLeg.rotateAngleY = 0.06F;
        leftLeg.scale = 0.05F;
        
        /*Chaussures / Boots */
        
        rightBoot.offsetX = -0.18F;
        rightBoot.offsetY = 1.33F;
        rightBoot.offsetZ = 0.17F;
        rightBoot.scale = 0.27F;
        
        leftBoot.offsetX = -0.18F;
        leftBoot.offsetY = 1.33F;
        leftBoot.offsetZ = 0.17F;
        leftBoot.scale = 0.27F;
        
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
