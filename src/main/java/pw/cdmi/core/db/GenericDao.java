package pw.cdmi.core.db;

import java.io.Serializable;
import java.util.List;

/**
 * The basic GenericDao interface with CRUD methods Finders are added with
 * interface inheritance and AOP introductions for concrete implementations
 * 
 * Extended interfaces may declare methods starting with find... list...
 * iterate... or scroll... They will execute a preconfigured query that is
 * looked up based on the rest of the method name
 */
public interface GenericDao {

	/**
	 * 保存一个对象
	 * 
	 * @param entity 要保存的对象实例
	 */
	<T> void save(T entity);

	/**
	 * 保存一组对象
	 * 
	 * @param entities 要保存的一组对象实例
	 */
	<T> List<T> save(Iterable<T> entities);
	
	/**
	 * 保存或更新对象实例
	 * 
	 * @param obj 修改或保存后的对象实例
	 */
	<T> void saveOrUpdate(T entity);
	
	/**
	 * 删除对象实例
	 * 
	 * @param entity 要删除的对象实例
	 */
	<T, PK extends Serializable> void delete(PK id ,Class<T> clazz);
	
	
	/**
	 * 根据条件删除符合条件的对象实例
	 * 
	 * @param query 要删除的对象实例
	 */
	<T> void delete(JPQuery jPQ);
	
	/**
	 * 删除对象实例
	 * 
	 * @param entity 要删除的对象实例
	 */
	<T> void delete(T entity);
	
	/**
	 * 删除一组对象实例
	 * 
	 * @param entity 要删除的对象实例
	 */
	<T, PK extends Serializable> void delete(Iterable<PK> ids ,Class<T> clazz);
	
	/**
	 * 删除对象实例
	 * 
	 * @param entity 要删除的对象实例
	 */
	<T> void delete(String jpql);
	
	/**
	 * 删除存储的所有对象实例
	 * 
	 * @return 返回删除存储的对象实例数量
	 */
	<T> long deleteAll(Class<T> clazz);
	
	/**
	 * 修改对象实例
	 * 
	 * @param transientObject 修改过的实例
	 */
	<T> void update(T entity);
	
	/**
	 * 修改对象实例
	 * 
	 * @param transientObject 修改过的实例
	 */
	<T> void update(JPQuery jPQ);
	
	/**
	 * 修改对象实例
	 * 
	 * @param transientObject 修改过的实例
	 */
	<T> void update(String jpql);
	
	/**
	 * 根据对象id判断对象实例是否存在
	 * 
	 * @param id 对象id
	 * @param clazz 对象的class
	 * @return 对象实例
	 */
	<T, PK extends Serializable> boolean exists(PK id, Class<T> clazz);
	
	/**
	 * 根据特定的查询条件判断对象实例是否存在
	 * 
	 * @param id 对象id
	 * @param clazz 对象的class
	 * @return 对象实例
	 */
	<T, PK extends Serializable> boolean exists(JPQuery jPQ);
	/**
	 * 根据特定的查询条件判断对象实例是否存在
	 * 
	 * @param id 对象id
	 * @param clazz 对象的class
	 * @return 对象实例
	 */
	<T, PK extends Serializable> boolean exists(String jpql);
	
	
	/**
	 * 根据命名查询语句getCount获取所有对象的总记录条数，
	 * 需要在实体对象上提供命名查询语句getCount
	 * 
	 * @param clazz 对象的class
	 * @return 总对象记录数
	 */
	<T> long getCount(Class<T> clazz);
	
	/**
	 * 根据命名查询语句getCount获取所有对象的总记录条数，
	 * 需要在实体对象上提供命名查询语句getCount
	 * 
	 * @param clazz 对象的class
	 * @return 总对象记录数
	 */
	<T> long getCount(JPQuery jPQ);
	
	/**
	 * 根据命名查询语句getCount获取所有对象的总记录条数，
	 * 需要在实体对象上提供命名查询语句getCount
	 * 
	 * @param clazz 对象的class
	 * @return 总对象记录数
	 */
	<T> long getCount(String jpql);
	
	/**
	 * 根据对象id获取对象实例
	 * 
	 * @param id 对象id
	 * @param clazz 对象的class
	 * @return 对象实例
	 */
	<T, PK extends Serializable> T get(PK id, Class<T> clazz);
	
	/**
	 * 获取所有的实体对象需要引用命令查询语句findAll
	 * 
	 * @param clazz 实体对象的class
	 * @return 所有的实体对象
	 */
	<T> List<T> findAll(Class<T> clazz);
	
	/**
	 * Returns a {@link Page} of entities meeting the paging restriction provided in the {@code Pageable} object.
	 * 
	 * @param pageable
	 * @return a page of entities
	 */
	<T> List<T> findAll(Class<T> clazz, final int pageNo, final int pageSize);
	
	
	<T> T findOne(JPQuery jPQ);
	
	/**
	 * 根据查询语句查询单个对象
	 * 
	 * @param queryString 查询语句
	 * @return 查询出的单个对象
	 */
	<T> T findOne(String jpql);
	
	/**********************以上参考接口为Spring Data提供******************************/
	
	/**
	 * 根据查询语句查询对象实例
	 * 
	 * @param query 查询语句
	 * @return 查询对象结果集
	 */
	<T> List<T> find(JPQuery jPQ);
	
	/**
	 * 根据查询语句查询对象实例
	 * 
	 * @param query 查询语句
	 * @return 查询对象结果集
	 */
	<T> List<T> find(JPQuery jPQ, final int pageNo, final int pageSize);
	
	/**
	 * 根据查询语句查询对象实例
	 * 
	 * @param jpql 查询语句
	 * @return 查询对象结果集
	 */
	<T> List<T> find(String jpql);

	/**
	 * 根据查询语句查询对象实例
	 * 
	 * @param jpql 查询语句
	 * @return 查询对象结果集
	 */
	<T> List<T> find(String jpql, final int pageNo, final int pageSize);
	
	/**
	 * 保存一个对象
	 * 
	 * @param entity 要保存的对象实例
	 */
	<T> void saveByNamedQuery(JPQuery jPQ);
	
	/**
	 * 保存一个对象
	 * 
	 * @param entity 要保存的对象实例
	 */
	<T> void saveByNamedQuery(String namedQuery);
	
	/**
	 * 根据查询语句删除对象
	 * 
	 * @param queryString 查询语句
	 * @return 查询出的单个对象
	 */
	void deleteByNamedQuery(JPQuery jPQ);
	
	/**
	 * 根据查询语句删除对象
	 * 
	 * @param queryString 查询语句
	 * @return 查询出的单个对象
	 */
	void deleteByNamedQuery(String namedQuery);
	
	/**
	 * 修改对象实例
	 * 
	 * @param transientObject 修改过的实例
	 */
	<T> void updateByNamedQuery(JPQuery jPQ);
	
	/**
	 * 修改对象实例
	 * 
	 * @param transientObject 修改过的实例
	 */
	<T> void updateByNamedQuery(String namedQuery);
	
	/**
	 * 根据查询语句查询单个对象
	 * 
	 * @param queryString 查询语句
	 * @return 查询出的单个对象
	 */
	<T> T findOneByNamedQuery(JPQuery jPQ);
	
	/**
	 * 根据查询语句查询单个对象
	 * 
	 * @param queryString 查询语句
	 * @return 查询出的单个对象
	 */
	<T> T findOneByNamedQuery(String namedQuery);
	
	/**
	 * 根据查询语句查询对象实例
	 * 
	 * @param query 查询语句
	 * @return 查询对象结果集
	 */
	<T> List<T> findByNamedQuery(JPQuery jPQ);
	
	/**
	 * 根据查询语句查询对象实例
	 * 
	 * @param query 查询语句
	 * @return 查询对象结果集
	 */
	<T> List<T> findByNamedQuery(JPQuery jPQ, final int pageNo, final int pageSize);
	
	/**
	 * 根据查询语句查询对象实例
	 * 
	 * @param jpql 查询语句
	 * @return 查询对象结果集
	 */
	<T> List<T> findByNamedQuery(String namedQuery);
	
	/**
	 * 根据查询语句查询对象实例
	 * 
	 * @param jpql 查询语句
	 * @return 查询对象结果集
	 */
	<T> List<T> findByNamedQuery(String namedQuery, final int pageNo, final int pageSize);

}
