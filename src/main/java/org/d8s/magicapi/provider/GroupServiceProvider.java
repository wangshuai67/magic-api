package org.d8s.magicapi.provider;

import org.d8s.magicapi.adapter.Resource;
import org.d8s.magicapi.model.Group;
import org.d8s.magicapi.model.TreeNode;

import java.util.List;


public interface GroupServiceProvider {

	/**
	 * 添加分组
	 */
	boolean insert(Group group);

	/**
	 * 修改分组
	 */
	boolean update(Group group);

	/**
	 * 删除分组
	 */
	boolean delete(String groupId);

	/**
	 * 分组是否存在
	 */
	boolean exists(Group group);

	/**
	 * 是否有该分组
	 */
	boolean containsApiGroup(String groupId);

	Group readGroup(Resource resource);

	/**
	 * 接口分组列表
	 */
	TreeNode<Group> apiGroupTree();

	/**
	 * 函数分组列表
	 */
	TreeNode<Group> functionGroupTree();

	/**
	 * 分组列表
	 */
	List<Group> groupList(String type);

	/**
	 * 根据分组Id获取分组路径
	 */
	String getFullPath(String groupId);

	/**
	 * 根据分组Id获取分组名称
	 */
	String getFullName(String groupId);

	Resource getGroupResource(String groupId);
}
