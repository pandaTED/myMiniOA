package cn.panda.oa.service.impl;

import cn.panda.oa.base.BaseDaoImpl;
import cn.panda.oa.domain.Department;
import cn.panda.oa.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@SuppressWarnings("unchecked")
public class DepartmentServiceImpl extends BaseDaoImpl<Department> implements DepartmentService {
	
	
	//查找所有顶级部门
    public List<Department> findTopList() {
        return getSession().createQuery(//
                "FROM Department d WHERE d.parent IS NULL")//
                .list();
    }
    
    
    //查找该部门下的所有子级部门
    public List<Department> findChildren(Long parentId) {
        return getSession().createQuery(//
                "FROM Department d WHERE d.parent.id=?")//
                .setParameter(0, parentId)//
                .list();
    }

}
