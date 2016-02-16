package cn.panda.oa.struts2.action;


import cn.panda.oa.base.BaseAction;
import cn.panda.oa.domain.Department;
import cn.panda.oa.domain.Role;
import cn.panda.oa.domain.User;
import cn.panda.oa.utils.DepartmentUtils;
import com.opensymphony.xwork2.ActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {

    private Long departmentId;
    private Long[] roleIds;

    /**
     * 列表
     */
    public String list() {
        List<User> userList = userService.findAll();
        ActionContext.getContext().put("userList", userList);
        return "list";
    }

    /**
     * 删除
     */
    public String delete() {
        userService.delete(model.getId());
        return "toList";
    }

    /**
     * 添加页面
     */
    public String addUI() {
        // 准备数据：部门列表， 应显示为树状结构
        List<Department> topList = departmentService.findTopList();
        List<Department> departmentList = DepartmentUtils.getAllDepartmentList(topList); // 使用递归得到所有的部门，并且已经修改了名称以表示层次
        ActionContext.getContext().put("departmentList", departmentList);

        // 准备数据：岗位列表
        List<Role> roleList = roleService.findAll();
        ActionContext.getContext().put("roleList", roleList);

        return "saveUI";
    }

    /**
     * 添加
     */
    public String add() {
        // 1，新建对象并设置属性（也可以使用model）
        model.setDepartment(departmentService.getById(departmentId));
        model.setRoles(roleService.getByIds(roleIds));

        // 2，保存
        userService.save(model);

        return "toList";
    }

    /**
     * 修改页面
     */
    public String editUI() {
        // 准备数据：部门列表， 应显示为树状结构
        List<Department> topList = departmentService.findTopList();
        List<Department> departmentList = DepartmentUtils.getAllDepartmentList(topList); // 使用递归得到所有的部门，并且已经修改了名称以表示层次
        ActionContext.getContext().put("departmentList", departmentList);

        // 准备数据：岗位列表
        List<Role> roleList = roleService.findAll();
        ActionContext.getContext().put("roleList", roleList);

        // 准备回显的数据
        User user = userService.getById(model.getId());
        ActionContext.getContext().getValueStack().push(user);
        if (user.getDepartment() != null) {
            departmentId = user.getDepartment().getId();
        }
        if (user.getRoles() != null) {
            roleIds = new Long[user.getRoles().size()];
            int index = 0;
            for (Role role : user.getRoles()) {
                roleIds[index++] = role.getId();
            }
        }

        return "saveUI";
    }

    /**
     * 修改
     */
    public String edit() {
        // 1，从数据库中获取原对象
        User user = userService.getById(model.getId());

        // 2，设置要修改的属性
        user.setLoginName(model.getLoginName());
        user.setName(model.getName());
        user.setGender(model.getGender());
        user.setPhoneNumber(model.getPhoneNumber());
        user.setEmail(model.getEmail());
        user.setDescription(model.getDescription());

        user.setDepartment(departmentService.getById(departmentId));
        user.setRoles(roleService.getByIds(roleIds));

        // 3，更新
        userService.update(user);

        return "toList";
    }

    /**
     * 初始化密码为1234
     */
    public String initPassword() {
        userService.initPassword(model.getId());
        return "toList";
    }

    // ---------------------------------

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long[] getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Long[] roleIds) {
        this.roleIds = roleIds;
    }

}
