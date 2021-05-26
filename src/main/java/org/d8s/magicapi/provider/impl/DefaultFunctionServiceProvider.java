package org.d8s.magicapi.provider.impl;

import org.d8s.magicapi.adapter.Resource;
import org.d8s.magicapi.model.Constants;
import org.d8s.magicapi.provider.FunctionServiceProvider;
import org.d8s.magicapi.provider.GroupServiceProvider;

public class DefaultFunctionServiceProvider extends FunctionServiceProvider {


	public DefaultFunctionServiceProvider(GroupServiceProvider groupServiceProvider, Resource workspace) {
		super(workspace.getResource(Constants.PATH_FUNCTION), groupServiceProvider);
	}


}
