package cn.panda.oa.service;

import cn.panda.oa.base.BaseDao;
import cn.panda.oa.domain.Department;

import java.util.List;

public interface DepartmentService extends BaseDao<Department> {

    /**
     * 查询所有的顶级部门列表
     *
     * @return
     */
    List<Department> findTopList();

    /**
     * 查询指定部门的子部门列表
     *
     * @param parentId
     * @return
     */
    List<Department> findChildren(Long parentId);

}
