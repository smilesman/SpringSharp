package lab.sharp.core.service;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import lab.sharp.core.dao.jpa.BaseDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;


public abstract class BaseService<T extends Persistable<? extends Serializable>, ID extends Serializable> {

    private final Logger logger = LoggerFactory.getLogger(BaseService.class);

    /** 泛型对应的Class定义 */
    private Class<T> entityClass;

    /** 子类设置具体的DAO对象实例 */
    abstract protected BaseDao<T, ID> getEntityDao();

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
	protected Class<T> getEntityClass() {
        if (entityClass == null) {
            try {
                // 通过反射取得Entity的Class.
                Object genericClz = getClass().getGenericSuperclass();
                if (genericClz instanceof ParameterizedType) {
                    entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
                }
            } catch (Exception e) {
                logger.error("error detail:", e);
            }
        }
        return entityClass;
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }


    /**
     * 创建数据保存数据之前额外操作回调方法 默认为空逻辑，子类根据需要覆写添加逻辑即可
     * 
     * @param entity
     *            待创建数据对象
     */
    protected void preInsert(T entity) {

    }

    /**
     * 更新数据保存数据之前额外操作回调方法 默认为空逻辑，子类根据需要覆写添加逻辑即可
     * 
     * @param entity
     *            待更新数据对象
     */
    protected void preUpdate(T entity) {

    }

    /**
     * 数据保存操作
     * 
     * @param entity
     * @return
     */
    public T save(T entity) {
        if (entity.isNew()) {
            preInsert(entity);
        } else {
            preUpdate(entity);
        }
        getEntityDao().save(entity);
        return entity;
    }

    /**
     * 批量数据保存操作 其实现只是简单循环集合每个元素调用 {@link #save(Persistable)}
     * 因此并无实际的Batch批量处理，如果需要数据库底层批量支持请自行实现
     * 
     * @param entities
     *            待批量操作数据集合
     * @return
     */
    public List<T> save(Iterable<T> entities) {
        List<T> result = new ArrayList<T>();
        if (entities == null) {
            return result;
        }
        for (T entity : entities) {
            result.add(save(entity));
        }
        return result;
    }

    /**
     * 基于主键查询单一数据对象
     * 
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public T findOne(ID id) {
        Assert.notNull(id);
        return getEntityDao().findOne(id);
    }


    /**
     * 基于主键集合查询集合数据对象
     * 
     * @param ids 主键集合
     * @return
     */
    @Transactional(readOnly = true)
    public List<T> findAll(final ID... ids) {
        Assert.isTrue(ids != null && ids.length > 0, "必须提供有效查询主键集合");
        Specification<T> spec = new Specification<T>() {
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                @SuppressWarnings("rawtypes")
                Path expression = root.get("id");
                return expression.in(ids);
            }
        };
        return this.getEntityDao().findAll(spec);
    }

    /**
     * 数据删除操作
     * 
     * @param entity
     *            待操作数据
     */
    public void delete(T entity) {
        getEntityDao().delete(entity);
    }

    /**
     * 批量数据删除操作 其实现只是简单循环集合每个元素调用 {@link #delete(Persistable)}
     * 因此并无实际的Batch批量处理，如果需要数据库底层批量支持请自行实现
     * 
     * @param entities
     *            待批量操作数据集合
     * @return
     */
    public void delete(Iterable<T> entities) {
        for (T entity : entities) {
            delete(entity);
        }
    }


    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Transactional(readOnly = true)
    public Object findEntity(Class entityClass, Serializable id) {
        return getEntityManager().find(entityClass, id);
    }

}
