package cn.panda.oa.interceptor;

import cn.panda.oa.domain.User;
import cn.panda.oa.struts2.action.LoginLogoutAction;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@SuppressWarnings("serial")
public class CheckPrivilegeInterceptor extends AbstractInterceptor {

    public String intercept(ActionInvocation invocation) throws Exception {
        System.out.println("拦截器正常工作!");

        // 获取当前的登录用户
        User user = (User) ActionContext.getContext().getSession().get("user");
        // 获取当前访问的URL（action的名称），需要处理一下UI后缀
        String url = invocation.getProxy().getActionName();
        if (url.endsWith("UI")) {
            url = url.substring(0, url.length() - 2);
        }

        // 一、未登录的情况
        if (user == null) {
            if (invocation.getProxy().getAction() instanceof LoginLogoutAction) {
                // 如果正在使用登录用功能，则放行
                return invocation.invoke(); // 放行
            } else {
                // 如果不是正在使用登录功能，则转到登录页面
                return "loginUI";
            }
        }
        // 二、已登录的情况
        else {
            if (user.hasPrivilegeByUrl(url)) {
                // 如果有权限，则放行
                return invocation.invoke(); // 放行
            } else {
                // 如果没有有权，则转到错误页面
                return "privilegeError";
            }
        }
    }
}
