package cn.panda.oa.service;

import cn.panda.oa.base.BaseDao;
import cn.panda.oa.domain.Privilege;

import java.util.List;

/**
 * Created by panda on 2015/12/18 0018.
 */
public interface PrivilegeService extends BaseDao<Privilege> {

    List<Privilege> findTopList();//查找所有顶层

    List<Privilege> findChildren(Long parentId);//根据上层菜单找所有下层的子项

    List<String> getAllPrivilegeUrls();

}
