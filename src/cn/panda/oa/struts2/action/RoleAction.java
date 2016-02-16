package cn.panda.oa.struts2.action;

import cn.panda.oa.base.BaseAction;
import cn.panda.oa.domain.Privilege;
import cn.panda.oa.domain.Role;
import com.opensymphony.xwork2.ActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Created by panda on 2015/12/13 0013.
 */

@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {


    private Long[] privilegeIds;


    //显示岗位页面
    public String list() {
        List<Role> roleList = roleService.findAll();
        ActionContext.getContext().put("roleList", roleList);
        return "list";
    }

    //删除
    //转到list
    public String delete() {
        roleService.delete(model.getId());
        return "toList";
    }


    //修改
    public String edit() {
        Role role = roleService.getById(model.getId());
        role.setName(model.getName());
        role.setDescription(model.getDescription());
        roleService.update(role);
        return "toList";
    }

    //添加
    public String add() {
        //1，创建对象并设置属性
        Role role = new Role();
        role.setName(model.getName());
        role.setDescription(model.getDescription());
        roleService.save(role);
        return "toList";
    }

    //添加岗位页面
    //转到list
    public String addUI() {
        return "saveUI";
    }

    //修改岗位页面
    //转到list
    public String editUI() {
        Role role = roleService.getById(model.getId());
        ActionContext.getContext().getValueStack().push(role);
        return "saveUI";
    }

    public String setPrivilegeUI() {
        //准备数据：岗位信息
        Role role = roleService.getById(model.getId());
        System.out.println("1被修改的岗位的名字是：" + role.getName());
        ActionContext.getContext().getValueStack().push(role);

        //准备数据：权限列表   TODO 应是树状结构，先用所有权限列表代替
//        List<Privilege> privilegesList = privilegeService.findAll();
//        ActionContext.getContext().put("privilegesList",privilegesList);

        //查找顶层数据
        List<Privilege> topPrivilegesList = privilegeService.findTopList();//todo
        ActionContext.getContext().put("topPrivilegesList", topPrivilegesList);
        //查找上层的所有子类
//        List<Privilege> childrenPrivilegeList = privilegeService.findChildren();
//        ActionContext.getContext().put("topPrivilegesList",topPrivilegesList);

        //准备回显的数据
        privilegeIds = new Long[role.getPrivileges().size()];
        int index = 0;
        for (Privilege privilege : role.getPrivileges()) {
            privilegeIds[index++] = privilege.getId();
        }

        return "savePrivilegeList";
    }

    public String setPrivilege() {
        //获取要修改的岗位role
        Role role = roleService.getById(model.getId());
        //修改这个岗位的权限
        System.out.println("2被修改的岗位的名字是：" + role.getName());
        role.setPrivileges(privilegeService.getByIds(privilegeIds));

        roleService.update(role);

        return "toList";
    }

    public Long[] getPrivilegeIds() {
        return privilegeIds;
    }

    public void setPrivilegeIds(Long[] privilegeIds) {
        this.privilegeIds = privilegeIds;
    }

}
