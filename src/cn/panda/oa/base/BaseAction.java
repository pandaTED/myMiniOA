package cn.panda.oa.base;

import cn.panda.oa.service.DepartmentService;
import cn.panda.oa.service.PrivilegeService;
import cn.panda.oa.service.RoleService;
import cn.panda.oa.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;

/**
 * Created by panda on 2015/12/15 0015.
 */
public abstract class BaseAction<T> extends ActionSupport implements ModelDriven<T> {


    @Resource
    protected RoleService roleService;

    @Resource
    protected DepartmentService departmentService;

    @Resource
    protected UserService userService;

    @Resource
    protected PrivilegeService privilegeService;


    protected T model;


    private Class<T> modelClass;        //声明clazz

    public BaseAction() {        //将传入的方法内的clazz修改为传入的类型
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        modelClass = (Class) pt.getActualTypeArguments()[0];
    }

    public T getModel() {
        try {
            if (model == null) {
                model = modelClass.newInstance();
            }
            return model;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}