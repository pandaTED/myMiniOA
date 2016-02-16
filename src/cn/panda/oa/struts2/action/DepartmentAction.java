package cn.panda.oa.struts2.action;

import cn.panda.oa.base.BaseAction;
import cn.panda.oa.domain.Department;
import cn.panda.oa.utils.DepartmentUtils;
import com.opensymphony.xwork2.ActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Scope("prototype")
public class DepartmentAction extends BaseAction<Department> {

    private Long parentId;

    /**
     * 列表，列表页面只显示一层的（同级的）部门数据，默认显示最顶级的部门列表
     */
    public String list() {
        List<Department> departmentList = null;

        if (parentId == null) {
            // 查询所有的顶级部门列表
            departmentList = departmentService.findTopList();
        } else {
            // 查询指定部门的子部门列表
            departmentList = departmentService.findChildren(parentId);
            // 查询上级部门信息
            Department parent = departmentService.getById(parentId);
            ActionContext.getContext().put("parent", parent);
        }

        ActionContext.getContext().put("departmentList", departmentList);
        return "list";
    }

    /**
     * 删除，同时删除此部门的所有下级部门
     */
    public String delete() {
        departmentService.delete(model.getId());
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

        return "saveUI";
    }

    /**
     * 添加
     */
    public String add() {
        // 新建对象并设置属性（也可以使用model）
        // Department department = new Department();
        // department.setName(model.getName());
        // department.setDescription(model.getDescription());
        // department.setParent(departmentService.getById(parentId));
        model.setParent(departmentService.getById(parentId));

        // 保存
        // departmentService.save(department);
        departmentService.save(model);

        return "toList";
    }

    /**
     * 修改页面
     */
    public String editUI() {
        // 准备回显的数据
        Department department = departmentService.getById(model.getId());
        ActionContext.getContext().getValueStack().push(department);
        if (department.getParent() != null) {
            parentId = department.getParent().getId();
        }

        // 准备数据：部门列表， 应显示为树状结构
        List<Department> topList = departmentService.findTopList();
        List<Department> departmentList = DepartmentUtils.getAllDepartmentList(topList); // 使用递归得到所有的部门，并且已经修改了名称以表示层次
        // 从集合中移除指定部门及他的子孙部门
        DepartmentUtils.removeDepartmentAndChildren(departmentList, department);
        ActionContext.getContext().put("departmentList", departmentList);

        return "saveUI";
    }

    /**
     * 修改
     */
    public String edit() {
        // 从数据库中获取原对象
        Department department = departmentService.getById(model.getId());

        // 设置要修改的属性
        department.setName(model.getName());
        department.setDescription(model.getDescription());
        department.setParent(departmentService.getById(parentId));

        // 更新
        departmentService.update(department);

        return "toList";
    }

    // ------------------------------------------------

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

}
