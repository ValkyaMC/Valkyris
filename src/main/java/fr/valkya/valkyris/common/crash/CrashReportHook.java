package fr.valkya.valkyris.common.crash;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

import cpw.mods.fml.common.ModContainer;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;

public class CrashReportHook {
	
	public static void preHook(CrashReport owo) {
		StacktraceDeobfuscator.deobfuscateThrowable(owo.getCrashCause());
	}
	
	public static void enhance(CrashReport crash, CrashReportCategory category) {
		category.addCrashSection("Suspected Mods", getSuspectedMods(crash));
	}
	
	private static String getSuspectedMods(CrashReport crash) {
		try {
            Set<ModContainer> suspectedMods = ModIdentifier.identifyFromStacktrace(crash.getCrashCause());

            String modListString = "Unknown";
            List<String> modNames = new ArrayList<>();
            for (ModContainer mod : suspectedMods) {
                modNames.add(mod.getName() + " (" + mod.getModId() + ")");
            }

            if (!modNames.isEmpty()) {
                modListString = StringUtils.join(modNames, ", ");
            }
            return modListString;
        } catch (Throwable e) {
            return ExceptionUtils.getStackTrace(e).replace("\t", "    ");
        }
	}

}
