package cn.panda.oa.service.impl;

import cn.panda.oa.base.BaseDaoImpl;
import cn.panda.oa.domain.Privilege;
import cn.panda.oa.service.PrivilegeService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by panda on 2015/12/18 0018.
 */
@Service
public class PrivilegeServiceImpl extends BaseDaoImpl<Privilege> implements PrivilegeService {
	
	
	//查找所有顶级权限
    public List<Privilege> findTopList() {
        return getSession().createQuery(//
                "FROM Privilege d WHERE d.parent IS NULL")//
                .list();
    }
    //查找该权限下的所有子权限
    public List<Privilege> findChildren(Long parentId) {
        return getSession().createQuery(//
                "FROM Privilege d WHERE d.parent.id=?")//
                .setParameter(0, parentId)//
                .list();
    }
    
    
    //查找所有的权限
    public List<String> getAllPrivilegeUrls() {
        return getSession().createQuery(//
                "SELECT DISTINCT p.url FROM Privilege p WHERE p.url IS NOT NULL")//
                .list();
    }
}
