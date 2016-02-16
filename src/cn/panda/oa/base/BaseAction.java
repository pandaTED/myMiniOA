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
	//自动注入需要的service

    @Resource
    protected RoleService roleService;

    @Resource
    protected DepartmentService departmentService;

    @Resource
    protected UserService userService;

    @Resource
    protected PrivilegeService privilegeService;


    protected T model;

    //根据传入不同的泛型T生成不同的class
    private Class<T> modelClass;        //声明clazz
    
    //将传入的方法内的clazz修改为传入的类型
    //重写默认的构造函数
    public BaseAction() {    
    	//利用反射技术获取传入参数化的类型
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        //根据参数类型生成modelClass
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