package cn.panda.oa.struts2.action;

import cn.panda.oa.domain.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class IndexAction extends ActionSupport {

    public String index() {
        User user = (User) ActionContext.getContext().getSession().get("user");

        if (user == null) {
            return "loginUI";
        } else {
            return "index";
        }
    }

    public String top() {
        return "top";
    }

    public String left() {
        return "left";
    }

    public String right() {
        return "right";
    }

    public String bottom() {
        return "bottom";
    }
}
