package cn.panda.oa.service;

import cn.panda.oa.base.BaseDao;
import cn.panda.oa.domain.User;

/**
 * Created by panda on 2015/12/15 0015.
 */

public interface UserService extends BaseDao<User> {

    /**
     * 初始化密码为1234
     *
     * @param id
     */
    void initPassword(Long id);

    User getUserByLoginNameAndPassword(String loginName, String password);
}
