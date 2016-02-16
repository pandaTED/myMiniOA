package cn.panda.oa.install;

import cn.panda.oa.domain.Privilege;
import cn.panda.oa.domain.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 
 * 部署项目时，首先运行该程序，将默认超级管理员账户和所有权限写入数据库
 * Created by panda on 2015/12/18 0018.
 */
@Component
public class Installer {

    @Resource
    private SessionFactory sessionFactory;

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        Installer installer = (Installer) ac.getBean("installer");
        installer.install();
        System.out.println("---安装完毕！---");
    }

    @Transactional
    public void install() {

        Session session = sessionFactory.getCurrentSession();
        //插入一个超级管理员用户
        User user = new User();
        user.setName("超级管理员");
        user.setLoginName("admin");
        user.setPassword(DigestUtils.md5Hex("admin"));//密码设置为admin的md5摘要
        session.save(user);

        //插入权限数据
        Privilege menu = new Privilege("系统管理", "", "FUNC20082.gif", null);
        Privilege menu1 = new Privilege("岗位管理", "roleAction_list", "null", menu);
        Privilege menu11 = new Privilege("岗位列表", "roleAction_list", "null", menu1);
        Privilege menu12 = new Privilege("岗位添加", "roleAction_add", "null", menu1);
        Privilege menu13 = new Privilege("岗位修改", "roleAction_edit", "null", menu1);
        Privilege menu14 = new Privilege("岗位删除", "roleAction_delete", "null", menu1);
        Privilege menu15 = new Privilege("岗位权限设置", "roleAction_editPrivilege", "null", menu1);

        Privilege menu2 = new Privilege("部门管理", "departmentAction_list", "null", menu);
        Privilege menu21 = new Privilege("部门列表", "departmentAction_list", "null", menu2);
        Privilege menu22 = new Privilege("部门添加", "departmentAction_add", "null", menu2);
        Privilege menu23 = new Privilege("部门删除", "departmentAction_delete", "null", menu2);
        Privilege menu24 = new Privilege("部门修改", "departmentAction_edit", "null", menu2);

        Privilege menu3 = new Privilege("用户管理", "userAction_list", "null", menu);
        Privilege menu31 = new Privilege("用户列表", "userAction_list", "null", menu3);
        Privilege menu32 = new Privilege("用户添加", "userAction_add", "null", menu3);
        Privilege menu33 = new Privilege("用户修改", "userAction_edit", "null", menu3);
        Privilege menu34 = new Privilege("用户删除", "userAction_delete", "null", menu3);
        Privilege menu35 = new Privilege("用户初始化密码", "userAction_initPassword", "null", menu3);

        Privilege menu4 = new Privilege("网上交流", "null", "FUNC20064.gif", null);
        Privilege menu5 = new Privilege("论坛管理", "fromManageAction_list", "null", menu4);
        Privilege menu6 = new Privilege("论坛", "formAction_list", "null", menu4);

        session.save(menu);
        session.save(menu1);
        session.save(menu11);
        session.save(menu12);
        session.save(menu13);
        session.save(menu14);
        session.save(menu15);
        session.save(menu2);
        session.save(menu21);
        session.save(menu22);
        session.save(menu23);
        session.save(menu24);
        session.save(menu3);
        session.save(menu31);
        session.save(menu32);
        session.save(menu33);
        session.save(menu34);
        session.save(menu35);
        session.save(menu4);
        session.save(menu5);
        session.save(menu6);

    }
}
