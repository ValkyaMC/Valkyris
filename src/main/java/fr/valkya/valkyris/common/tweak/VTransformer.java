package fr.valkya.valkyris.common.tweak;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

import fr.valkya.valkyris.References;
import fr.valkya.valkyris.client.splash.VSplashProgress;
import fr.valkya.valkyris.common.authentication.VC01PacketEncryptionResponse;
import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraft.network.login.client.C01PacketEncryptionResponse;

public class VTransformer implements IClassTransformer, Opcodes {

	private final Logger logger;
	private final boolean betterCrashSystem = false;
	
	public VTransformer() {
		logger = LogManager.getLogger("VTransformer");
	}
	
	@Override
	public byte[] transform(String name, String transformedName, byte[] basicClass) {
		if (name.startsWith("fr.valkya")) return basicClass;
		
		if (transformedName.equalsIgnoreCase("net.minecraft.client.renderer.texture.TextureUtil")) {
			logger.info("Transforming TextureUtil");
			return transformTexUtil(basicClass);
		}
		if (transformedName.equalsIgnoreCase("net.minecraft.client.Minecraft") && !name.contains("$")) {
			logger.info("Transforming Minecraft");
			return transformMinecraft(basicClass);
		}
		if (transformedName.equalsIgnoreCase("cpw.mods.fml.client.FMLClientHandler")) {
			logger.info("Transforming FMLClientHandler");
			return transformClientHandler(basicClass);
		}
		if(References.enableCustomEncryption) {
			if (transformedName.startsWith("net.minecraft.network.EnumConnectionState$")) {
				logger.info("Transforming " + transformedName.substring(transformedName.lastIndexOf('.') + 1));
				return transformEnumConnectionState(basicClass);
			}
		}
		if(transformedName.equalsIgnoreCase("net.minecraft.client.renderer.entity.RenderPlayer")) {
			logger.info("Transforming RenderPlayer");
			return transformRenderPlayer(basicClass);
		}
		if(betterCrashSystem) {
			if (transformedName.equalsIgnoreCase("cpw.mods.fml.common.FMLCommonHandler")) {
				logger.info("Transforming FMLCommonHandler");
				return transformCommonHandler(basicClass);
			}
			if (transformedName.equalsIgnoreCase("net.minecraft.crash.CrashReport")) {
				logger.info("Transforming CrashReport");
				return transformCrashReport(basicClass);
			}
		}
		
		return basicClass;
	}
	
	private byte[] transformCrashReport(byte[] classBuffer) {
		ClassNode node = new ClassNode();
		ClassReader cr = new ClassReader(classBuffer);
		cr.accept(node, 0);

		for (MethodNode mn : node.methods) {
			if(mn.name.equalsIgnoreCase("populateEnvironment")) {
				InsnList list = new InsnList();
				list.add(new VarInsnNode(ALOAD, 0));
				list.add(new MethodInsnNode(INVOKESTATIC, "fr/valkya/valkyris/common/crash/CrashReportHook", "preHook", "(Lnet/minecraft/crash/CrashReport;)V", false));
				mn.instructions.insert(list);
			}
		}

		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
		node.accept(cw);
		return cw.toByteArray();
	}
	
	private byte[] transformCommonHandler(byte[] classBuffer) {
		ClassNode node = new ClassNode();
		ClassReader cr = new ClassReader(classBuffer);
		cr.accept(node, 0);

		for (MethodNode mn : node.methods) {
			if(mn.name.equalsIgnoreCase("enhanceCrashReport")) {
				InsnList list = new InsnList();
				list.add(new VarInsnNode(ALOAD, 1));
				list.add(new VarInsnNode(ALOAD, 2));
				list.add(new MethodInsnNode(INVOKESTATIC, "fr/valkya/valkyris/common/crash/CrashReportHook", "enhance", "(Lnet/minecraft/crash/CrashReport;Lnet/minecraft/crash/CrashReportCategory;)V", false));
				mn.instructions.insert(list);
			}
		}

		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
		node.accept(cw);
		return cw.toByteArray();
	}
	
	private byte[] transformTexUtil(byte[] classBuffer) {
		ClassNode node = new ClassNode();
		ClassReader cr = new ClassReader(classBuffer);
		cr.accept(node, 0);

		for (MethodNode mn : node.methods) {
			for (int i = 0; i < mn.instructions.size(); i++) {
				AbstractInsnNode insn = mn.instructions.get(i);
				if (insn.getOpcode() == LDC) {
					LdcInsnNode ldc = (LdcInsnNode) insn;
					if (ldc.cst instanceof Type) {
						ldc.cst = Type.getType(VSplashProgress.class);
					}
				}
			}
		}

		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
		node.accept(cw);
		return cw.toByteArray();
	}
	
	private byte[] transformRenderPlayer(byte[] classBuffer) {
		ClassNode node = new ClassNode();
		ClassReader cr = new ClassReader(classBuffer);
		cr.accept(node, 0);

		for (MethodNode mn : node.methods) {
			for (int i = 0; i < mn.instructions.size(); i++) {
				AbstractInsnNode insn = mn.instructions.get(i);
				if (insn.getOpcode() == LDC) {
					LdcInsnNode ldc = (LdcInsnNode) insn;
					if (ldc.cst instanceof String) {
						if(((String)ldc.cst).equalsIgnoreCase("deadmau5")) {
							ldc.cst = "xTrM_";
						}
					}
				}
			}
		}

		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
		node.accept(cw);
		return cw.toByteArray();
	}

	private byte[] transformMinecraft(byte[] classBuffer) {
		ClassNode node = new ClassNode();
		ClassReader cr = new ClassReader(classBuffer);
		cr.accept(node, 0);

		for (MethodNode mn : node.methods) {
			for (int i = 0; i < mn.instructions.size(); i++) {
				AbstractInsnNode insn = mn.instructions.get(i);
				if (insn.getOpcode() == INVOKESTATIC) {
					MethodInsnNode met = (MethodInsnNode) insn;
					if (met.owner.equalsIgnoreCase("cpw/mods/fml/client/SplashProgress")) {
						met.owner = VSplashProgress.class.getName().replace('.', '/');
					}
				}
			}
		}

		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
		node.accept(cw);
		return cw.toByteArray();
	}

	private byte[] transformClientHandler(byte[] classBuffer) {
		ClassNode node = new ClassNode();
		ClassReader cr = new ClassReader(classBuffer);
		cr.accept(node, 0);

		for (MethodNode mn : node.methods) {
			for (int i = 0; i < mn.instructions.size(); i++) {
				AbstractInsnNode insn = mn.instructions.get(i);
				if (insn.getOpcode() == INVOKESTATIC) {
					MethodInsnNode met = (MethodInsnNode) insn;
					if (met.owner.equalsIgnoreCase("cpw/mods/fml/client/SplashProgress")) {
						met.owner = VSplashProgress.class.getName().replace('.', '/');
					}
				}
			}
		}

		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
		node.accept(cw);
		return cw.toByteArray();
	}
	
	private byte[] transformEnumConnectionState(byte[] classBuffer) {
		ClassNode node = new ClassNode();
		ClassReader cr = new ClassReader(classBuffer);
		cr.accept(node, 0);

		Type coolType = Type.getType(C01PacketEncryptionResponse.class);
		Type coolerType = Type.getType(VC01PacketEncryptionResponse.class);
		
		for (MethodNode mn : node.methods) {
			for(AbstractInsnNode insn : mn.instructions.toArray()) {
				if(insn instanceof LdcInsnNode) {
					if(((LdcInsnNode) insn).cst instanceof Type) {
						Type type = (Type)((LdcInsnNode) insn).cst;
						if(type.getDescriptor().equalsIgnoreCase(coolType.getDescriptor())) {
							((LdcInsnNode) insn).cst = coolerType;
						}
					}
				}
			}
		}

		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
		node.accept(cw);
		return cw.toByteArray();
	}

}
