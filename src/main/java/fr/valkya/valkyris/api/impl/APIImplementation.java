package fr.valkya.valkyris.api.impl;

import fr.valkya.valkyris.api.IRenderer;
import fr.valkya.valkyris.api.ISharedRegistry;
import fr.valkya.valkyris.api.IValkyrisAPI;

/**
 * API Implementation
 * 
 * @author xTrM_
 * @since 1.00-BETA
 * @version 1.1.0
 */
public class APIImplementation implements IValkyrisAPI {

	private final ISharedRegistry sharedRegistry;
	private final IRenderer renderer;
	
	public APIImplementation() {
		this.sharedRegistry = new SharedRegistry();
		this.renderer = new RendererImpl();
	}
	
	@Override
	public ISharedRegistry getRegistry() {
		return this.sharedRegistry;
	}

	@Override
	public IRenderer getRenderer() {
		return this.renderer;
	}

}