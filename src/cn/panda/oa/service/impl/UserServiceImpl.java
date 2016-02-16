package cn.panda.oa.service.impl;

import cn.panda.oa.base.BaseDaoImpl;
import cn.panda.oa.domain.User;
import cn.panda.oa.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

/**
 * Created by panda on 2015/12/15 0015.
 */
@Service
public class UserServiceImpl extends BaseDaoImpl<User> implements UserService {

    @Override
    public void save(User user) {
        // 把密码设为“1234”的MD5摘要
    	//apache-common-code包提供的功能
        String digest = DigestUtils.md5Hex("1234");
        user.setPassword(digest);
        getSession().save(user); // 保存
    }

    public void initPassword(Long id) {
        // 把密码设为“1234”的MD5摘要
    	//apache-common-code包提供的功能
        String digest = DigestUtils.md5Hex("1234");
        User user = getById(id);
        user.setPassword(digest);
        // 需要update()吗？不用，因为他是持久化状态
    }

    public User getUserByLoginNameAndPassword(String loginName, String password) {

        String digest = DigestUtils.md5Hex(password);
        return (User) getSession().createQuery(
                "FROM User u WHERE u.loginName = ? AND u.password = ?")
                .setParameter(0, loginName)
                .setParameter(1, digest)
                .uniqueResult();
    }

}
