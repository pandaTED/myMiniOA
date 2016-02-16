package cn.panda.oa.struts2.action;

import cn.panda.oa.base.BaseAction;
import cn.panda.oa.domain.Privilege;
import cn.panda.oa.domain.User;
import com.opensymphony.xwork2.ActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Scope("prototype")
public class LoginLogoutAction extends BaseAction<User> {

    /**
     * 登录
     */
    public String loginUI() {
        return "loginUI";
    }

    /**
     * 登录
     */
    public String login() {
        User user = userService.getUserByLoginNameAndPassword(model.getLoginName(), model.getPassword());
        if (user == null) {
            // 登录失败
            addFieldError("login", "用户名或密码不正确！");
            return "loginUI";
        } else {
            // 登录成功
            ActionContext.getContext().getSession().put("user", user);

            // 准备菜单列表数据 TODO 应是在程序初始化的时候只准备一次（放到application作用域）
            List<Privilege> topMenuList = privilegeService.findTopList();
            ActionContext.getContext().getApplication().put("topMenuList", topMenuList);

            // TODO 准备所有权限的URL列表并放到application作用域中
            List<String> allPrivilegeUrls = privilegeService.getAllPrivilegeUrls();
            ActionContext.getContext().getApplication().put("allPrivilegeUrls", allPrivilegeUrls);

            return "toIndex";
        }
    }

    /**
     * 注销
     */
    public String logout() {
        ActionContext.getContext().getSession().remove("user");
        return "logout";
    }
}
