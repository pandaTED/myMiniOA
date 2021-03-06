package cn.panda.oa.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by panda on 2015/12/17 0017.
 */
public class Privilege implements java.io.Serializable {

    /**
     * 权限
     */
	//权限
    private Long id;				//id
    private String name;		//名称
    private String url;			//操作时使用的action名称
    private String icon;		//前台显示时对应的图标
    private Privilege parent;//上级权限，一对一
    private Set<Privilege> children = new HashSet<Privilege>();		//子权限			一对多
    private Set<Role> roles = new HashSet<Role>();							//权限所属的岗位，一对多

    public Privilege() {

    }

    public Privilege(String name, String url, String icon, Privilege parent) {
        this.name = name;
        this.url = url;
        this.icon = icon;
        this.parent = parent;
    }

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Privilege getParent() {
        return parent;
    }

    public void setParent(Privilege parent) {
        this.parent = parent;
    }

    public Set<Privilege> getChildren() {
        return children;
    }

    public void setChildren(Set<Privilege> children) {
        this.children = children;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
