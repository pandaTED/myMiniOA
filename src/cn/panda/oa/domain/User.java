package cn.panda.oa.domain;

import com.opensymphony.xwork2.ActionContext;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


//用户，有一个初始超级管理员账户，admin，密码默认为1234
//新建用户时，密码都是1234
//密码保存时会将密码转为md5摘要
public class User implements java.io.Serializable {
    private Long id;			//id
    private Department department;	//所属部门，一对一
    private Set<Role> roles = new HashSet<Role>();		//岗位（职责），一对多
    private String loginName; 	// 登录名
    private String password; 	// 密码
    private String name; 		// 真实姓名
    private String gender; 	// 性别
    private String phoneNumber; // 电话号码
    private String email; // 电子邮件
    private String description; // 说明

    /**
     * 检测本用户是否拥有指定名称的权限
     *
     * @param privilegeName
     * @return
     */
    
    public boolean hasPrivilegeByName(String privilegeName) {
        // 超级管理员有所有的权限
        if (isAdmin()) {
            return true;
        }

        // 普通用户
        //验证权限
        //获取所有岗位
        for (Role role : roles) {
        	//获取岗位对应的权限
            for (Privilege privilege : role.getPrivileges()) {
            	//验证该用户是否具有该权限，如果没有，返回false
                if (privilegeName.equals(privilege.getName())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 检测本用户是否拥有指定URL的权限
     *
     * @param url （实际上是actionName，例如：userAction_delete）
     * @return
     */
    public boolean hasPrivilegeByUrl(String url) {
        // 超级管理员有所有的权限
        if (isAdmin()) {
            return true;
        }

        // 普通用户
        List<String> allPrivilegeUrls = (List<String>) ActionContext.getContext().getApplication().get("allPrivilegeUrls"); // 所有需要控制的权限URL的集合
        //验证所有权限中是否包含该权限
        if (!allPrivilegeUrls.contains(url)) {
            // 1，如果这个URL不是需要的控制的权限，则登录后就能访问
            return true;
        } else {
            // 2，如果这个URL是需要的控制的权限，则有权限才能访问
            for (Role role : roles) {
                for (Privilege privilege : role.getPrivileges()) {
                    if (url.equals(privilege.getUrl())) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    /**
     * 判断当前用户是否是超级管理员
     *
     * @return
     */
    //验证当前用户是否是超级管理员admin
    public boolean isAdmin() {
        return "admin".equals(loginName);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
