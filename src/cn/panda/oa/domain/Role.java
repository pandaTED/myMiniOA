package cn.panda.oa.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 岗位
 *
 * @author panda
 */
//岗位
public class Role implements java.io.Serializable {
    private Long id;					//id
    private String name;			//名称
    private String description;	//描述
    private Set<User> users = new HashSet<User>();			//该岗位下的用户，一对多
    private Set<Privilege> privileges = new HashSet<Privilege>();			//该岗位具有的权限，一对多


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Set<Privilege> privileges) {
        this.privileges = privileges;
    }

}
