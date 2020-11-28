package fr.valkya.valkyris.common.tweak;

import java.util.Map;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.MCVersion;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.Name;

@Name("ValkyrisTweaker")
@MCVersion("1.7.10")
public class VTweaker implements IFMLLoadingPlugin {

//	private final Logger logger;
//
//	public VTweaker() {
//		logger = LogManager.getLogger("ValkyrisTweaker");
//
//		RuntimeMXBean bean = ManagementFactory.getRuntimeMXBean();
//		Class<? extends RuntimeMXBean> clazz = bean.getClass();
//		List<Method> methods = Arrays.asList(clazz.getDeclaredMethods()).stream()
//				.filter(m -> m.getReturnType() == String.class).filter(m -> m.getParameterCount() == 0)
//				.collect(Collectors.toList());
//
//		logger.info("getRuntimeMXBean->" + bean);
//		
//		methods.forEach(m -> {
//			m.setAccessible(true);
//			try {
//				logger.info(m.getName() + "->" + m.invoke(bean));
//			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
//				e.printStackTrace();
//			}
//		});
//		
//		logger.info("isBootClassPathSupported->" + bean.isBootClassPathSupported());
//		
//		logger.info("getInputArguments->");
//		bean.getInputArguments().forEach(a -> logger.info("\"" + a + "\""));
//		
//		logger.info("getSystemProperties->");
//		bean.getSystemProperties().forEach((k, v) -> {
//			logger.info("\"" + k + "\"->\"" + v + "\"");
//		});
//	}

	@Override
	public String[] getASMTransformerClass() {
		return null;
	}

	@Override
	public String getModContainerClass() {
		return null;
	}

	@Override
	public String getSetupClass() {
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data) {

	}

	@Override
	public String getAccessTransformerClass() {
		return VTransformer.class.getName();
	}

}
