package cn.panda.oa.utils;


import cn.panda.oa.domain.Department;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DepartmentUtils {

    /**
     * 使用递归得到所有的部门，并且已经修改了名称以表示层次
     *
     * @param topList
     * @return
     */
    public static List<Department> getAllDepartmentList(List<Department> topList) {
        List<Department> list = new ArrayList<Department>();
        walkDepartmentTreeList(topList, "┣", list);
        return list;
    }

    /**
     * 遍历所有的部门树，把遍历出的部门先改名称再放到同一个集合中
     *
     * @param topList
     * @param prefix
     * @param list
     */
    private static void walkDepartmentTreeList(Collection<Department> topList, String prefix, List<Department> list) {
        for (Department top : topList) {
            // 修改名称后放到集合中，要使用副本，因为原对象在Hibernate Session中
            Department copy = new Department();
            copy.setId(top.getId());
            copy.setName(prefix + top.getName());
            list.add(copy);
            // 处理子树
            walkDepartmentTreeList(top.getChildren(), "　" + prefix, list); // 使用的是全角的空格
        }
    }

    /**
     * 从集合中移除指定部门及他的子孙部门
     *
     * @param departmentList
     * @param department
     */
    public static void removeDepartmentAndChildren(List<Department> departmentList, Department department) {
        // 移除当前部门
        departmentList.remove(department);
        System.out.println("---> 已经移除部门：" + department.getName());

        // 移除子孙部门
        for (Department child : department.getChildren()) {
            removeDepartmentAndChildren(departmentList, child);
        }
    }
}
