package cn.panda.oa.base;

import java.util.List;
import java.util.Set;

public interface BaseDao<T> {

    void save(T entity);

    void update(T entity);

    void delete(Long id);

    T getById(Long id);

    Set<T> getByIds(Long[] ids);

    List<T> findAll();
}
