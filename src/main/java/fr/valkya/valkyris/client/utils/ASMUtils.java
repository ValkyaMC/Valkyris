package fr.valkya.valkyris.client.utils;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.MethodNode;

public class ASMUtils {
	
	public static AbstractInsnNode getLastReturn(MethodNode mn) {
		AbstractInsnNode last = mn.instructions.getLast();
		while (last.getOpcode() != Opcodes.RETURN)
			last = last.getPrevious();
		return last;
	}

	public static AbstractInsnNode getLastIReturn(MethodNode mn) {
		AbstractInsnNode last = mn.instructions.getLast();
		while (last.getOpcode() != Opcodes.IRETURN)
			last = last.getPrevious();
		return last;
	}
}
