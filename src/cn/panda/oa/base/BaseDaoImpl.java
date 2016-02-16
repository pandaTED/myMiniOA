package cn.panda.oa.base;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Transactional
@SuppressWarnings("unchecked")
public abstract class BaseDaoImpl<T> implements BaseDao<T> {
	
    protected Class<T> clazz;
    @Resource
    //自动注入sessionFactory
    private SessionFactory sessionFactory;
    
    //获取session，currentSession
    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }
    
    //重写构造函数，生成具体的clazz
    public BaseDaoImpl() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        clazz = (Class<T>) pt.getActualTypeArguments()[0];
        System.out.println("---> clazz = " + clazz);
    }
    
    
    //保存
    public void save(T entity) {
        getSession().save(entity);
    }
    
    //更新
    public void update(T entity) {
        getSession().update(entity);
    }
    
    
    //删除
    public void delete(Long id) {
    	//先根据id获取具体的obj，再用delete删除
        Object obj = getSession().get(clazz, id);
        getSession().delete(obj);
    }
    
    
    //findById
    public T getById(Long id) {
        if (id == null) {
            return null;
        }
        return (T) getSession().get(clazz, id);
    }
    
    
    //根据id的数组查询对应的list
    public Set<T> getByIds(Long[] ids) {
        if (ids == null || ids.length == 0) {
            return null;
        }

        // FROM User WHERE id IN (?)
        // return getSession().createQuery(//
        // "FROM User WHERE id in (:ids)")//
        // .setParameterList("ids", ids)//
        // .list();
        
        List list = getSession().createCriteria(clazz)//
                .add(Restrictions.in("id", ids))//
                .list();
        return new HashSet(list);
    }
    
    
    //查询所有
    public List<T> findAll() {
        // return getSession().createQuery("FROM " + clazz.getSimpleName()).list();
        return getSession().createCriteria(clazz).list();
    }

    /**
     * 获取当前可用的Session
     *
     * @return
     */

}
