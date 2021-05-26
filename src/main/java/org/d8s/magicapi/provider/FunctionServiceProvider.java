package org.d8s.magicapi.provider;

import org.d8s.magicapi.adapter.Resource;
import org.d8s.magicapi.model.FunctionInfo;

public abstract class FunctionServiceProvider extends StoreServiceProvider<FunctionInfo> {

	public FunctionServiceProvider(Resource workspace, GroupServiceProvider groupServiceProvider) {
		super(FunctionInfo.class, workspace, groupServiceProvider);
	}

	public boolean exists(FunctionInfo info) {
		return infos.values().stream()
				.anyMatch(it -> info.getGroupId().equals(it.getGroupId()) && (info.getName().equals(it.getName()) || info.getPath().equals(it.getPath())));
	}

	public boolean existsWithoutId(FunctionInfo info) {
		return infos.values().stream()
				.anyMatch(it -> !info.getId().equals(it.getId()) && info.getGroupId().equals(it.getGroupId()) && (info.getName().equals(it.getName()) || info.getPath().equals(it.getPath())));
	}

	@Override
	public byte[] serialize(FunctionInfo info) {
		return super.serialize(info);
	}
}
