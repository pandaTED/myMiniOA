package cn.panda.oa.service.impl;

import cn.panda.oa.base.BaseDaoImpl;
import cn.panda.oa.domain.Department;
import cn.panda.oa.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@SuppressWarnings("unchecked")
public class DepartmentServiceImpl extends BaseDaoImpl<Department> implements DepartmentService {

    public List<Department> findTopList() {
        return getSession().createQuery(//
                "FROM Department d WHERE d.parent IS NULL")//
                .list();
    }

    public List<Department> findChildren(Long parentId) {
        return getSession().createQuery(//
                "FROM Department d WHERE d.parent.id=?")//
                .setParameter(0, parentId)//
                .list();
    }

}
