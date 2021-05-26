package org.d8s.magicapi.provider.impl;

import org.d8s.magicapi.adapter.Resource;
import org.d8s.magicapi.model.Constants;
import org.d8s.magicapi.provider.ApiServiceProvider;
import org.d8s.magicapi.provider.GroupServiceProvider;

public class DefaultApiServiceProvider extends ApiServiceProvider {

	public DefaultApiServiceProvider(GroupServiceProvider groupServiceProvider, Resource workspace) {
		super(workspace.getResource(Constants.PATH_API), groupServiceProvider);
	}
}
