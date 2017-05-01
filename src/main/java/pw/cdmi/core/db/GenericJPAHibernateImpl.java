package pw.cdmi.core.db;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenericJPAHibernateImpl implements GenericDao {

	private static final Logger logger = LoggerFactory.getLogger(GenericJPAHibernateImpl.class);

	@PersistenceContext
	private EntityManager em;

	@Override
	public <T> void save(T entity) {
		em.persist(entity);
	}

	@Override
	public <T> List<T> save(Iterable<T> entities) {
		List<T> _entities = new ArrayList<T>();
		Iterator<T> iter = entities.iterator();
		while (iter.hasNext()) {
			T entity = iter.next();
			em.persist(entity);
			_entities.add(entity);
		}
		return _entities;
	}

	@Override
	public <T> void saveOrUpdate(T entity) {
		em.merge(entity);
	}

	@Override
	public <T, PK extends Serializable> void delete(PK id, Class<T> clazz) {
		T entity = em.find(clazz, id);
		if(entity != null){
			em.remove(entity);
		}else{
			logger.warn("未找到需要删除的记录");
		}
	}

	@Override
	public <T> void delete(JPQuery jPQ) {
		Query query = em.createQuery(jPQ.getJPQL());

		Map<String, Object> paramaters = jPQ.getParamaters();
		for (Map.Entry<String, Object> entry : paramaters.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}

		int num = query.executeUpdate();
		if (num < 1) {
			logger.warn("执行SQL[" + jPQ.getJPQL() + "],未找到需要删除的记录");
		}
	}

	@Override
	public <T> void delete(T entity) {
		em.remove(entity);
	}

	@Override
	public <T, PK extends Serializable> void delete(Iterable<PK> ids, Class<T> clazz) {
		Iterator<PK> iter = ids.iterator();
		while (iter.hasNext()) {
			PK id = iter.next();
			delete(id, clazz);
		}
	}

	@Override
	public <T> void delete(String jpql) {
		Query query = em.createQuery(jpql);
		int num = query.executeUpdate();
		if (num < 1) {
			logger.warn("执行SQL[" + jpql + "],未找到需要删除的记录");
		}
	}

	@Override
	public <T> long deleteAll(Class<T> clazz) {
		String jpql = "delete from " + clazz.getSimpleName();
		Query query = em.createQuery(jpql);
		return query.executeUpdate();
	}

	@Override
	public <T> void update(T entity) {
		em.merge(entity);
	}

	@Override
	public <T> void update(JPQuery jPQ) {
		Query query = em.createQuery(jPQ.getJPQL());

		Map<String, Object> paramaters = jPQ.getParamaters();
		for (Map.Entry<String, Object> entry : paramaters.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}

		int num = query.executeUpdate();
		if (num < 1) {
			logger.warn("执行SQL[" + jPQ.getJPQL() + "],未找到需要更新的记录");
		}
	}

	@Override
	public <T> void update(String jpql) {
		Query query = em.createQuery(jpql);
		int num = query.executeUpdate();

		if (num < 1) {
			logger.error("执行SQL[" + jpql + "],未找到需要更新的记录");
		}
	}

	@Override
	public <T, PK extends Serializable> boolean exists(PK id, Class<T> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T, PK extends Serializable> boolean exists(JPQuery jPQ) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T, PK extends Serializable> boolean exists(String jpql) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T> long getCount(Class<T> clazz) {
		String jpql = "SELECT count(e) FROM " + clazz.getSimpleName() + " e";
		Query query = em.createQuery(jpql);
		return ((Long) query.getSingleResult()).longValue();
	}

	@Override
	public <T> long getCount(JPQuery jPQ) {
		Query query = em.createQuery(jPQ.getJPQL());

		Map<String, Object> paramaters = jPQ.getParamaters();
		for (Map.Entry<String, Object> entry : paramaters.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		return ((Long) query.getSingleResult()).longValue();
	}

	@Override
	public <T> long getCount(String jpql) {
		Query query = em.createQuery(jpql);
		return ((Long) query.getSingleResult()).longValue();
	}

	@Override
	public <T, PK extends Serializable> T get(PK id, Class<T> clazz) {
		return em.find(clazz, id);
	}

	@Override
	public <T> List<T> findAll(Class<T> clazz) {
		String queryString = "from " + clazz.getSimpleName();
		return em.createQuery(queryString).getResultList();
	}

	@Override
	public <T> List<T> findAll(Class<T> clazz, int pageNo, int pageSize) {
		String jpql = "from " + clazz.getSimpleName();
		Query query = em.createQuery(jpql);
		query.setFirstResult((pageNo - 1) * pageSize);
		query.setMaxResults(pageSize);
		return query.getResultList();
	}

	@Override
	public <T> T findOne(JPQuery jPQ) {
		Query query = em.createQuery(jPQ.getJPQL());

		Map<String, Object> paramaters = jPQ.getParamaters();
		for (Map.Entry<String, Object> entry : paramaters.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}

		try {
			return (T) query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		} catch (NonUniqueResultException e) {
			logger.error("重复记录：执行SQL[" + jPQ.getJPQL() + "],存在多个重复的记录");
			return null;
		}
	}

	@Override
	public <T> T findOne(String jpql) {
		try {
			return (T) em.createQuery(jpql).getSingleResult();
		} catch (NoResultException ex) {
			return null;
		} catch (NonUniqueResultException e) {
			logger.error("重复记录：执行SQL[" + jpql + "],存在多个重复的记录");
			return null;
		}
	}

	@Override
	public <T> List<T> find(JPQuery jPQ) {
		Query query = em.createQuery(jPQ.getJPQL());

		Map<String, Object> paramaters = jPQ.getParamaters();
		for (Map.Entry<String, Object> entry : paramaters.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}

		return query.getResultList();
	}

	@Override
	public <T> List<T> find(JPQuery jPQ, int pageNo, int pageSize) {
		Query query = em.createQuery(jPQ.getJPQL());

		Map<String, Object> paramaters = jPQ.getParamaters();
		for (Map.Entry<String, Object> entry : paramaters.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		query.setFirstResult((pageNo - 1) * pageSize);
		query.setMaxResults(pageSize);

		return query.getResultList();
	}

	@Override
	public <T> List<T> find(String jpql) {
		Query query = em.createQuery(jpql);
		return query.getResultList();
	}

	@Override
	public <T> List<T> find(String jpql, int pageNo, int pageSize) {
		Query query = em.createQuery(jpql);
		query.setFirstResult((pageNo - 1) * pageSize);
		query.setMaxResults(pageSize);
		return query.getResultList();
	}

	@Override
	public <T> void saveByNamedQuery(JPQuery jPQ) {
		Query query = em.createNamedQuery(jPQ.getJPQL());

		Map<String, Object> paramaters = jPQ.getParamaters();
		for (Map.Entry<String, Object> entry : paramaters.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}

		int num = query.executeUpdate();
		if (num < 1) {
			logger.warn("执行SQL[" + jPQ.getJPQL() + "],未有记录被保存");
		}
	}

	@Override
	public <T> void saveByNamedQuery(String namedQuery) {
		Query query = em.createQuery(namedQuery);

		int num = query.executeUpdate();
		if (num < 1) {
			logger.warn("执行SQL[" + namedQuery + "],未有记录被保存");
		}
	}

	@Override
	public void deleteByNamedQuery(JPQuery jPQ) {
		Query query = em.createNamedQuery(jPQ.getJPQL());

		Map<String, Object> paramaters = jPQ.getParamaters();
		for (Map.Entry<String, Object> entry : paramaters.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}

		int num = query.executeUpdate();
		if (num < 1) {
			logger.warn("执行SQL[" + jPQ.getJPQL() + "],未有记录被删除");
		}
	}

	@Override
	public void deleteByNamedQuery(String namedQuery) {
		Query query = em.createQuery(namedQuery);

		int num = query.executeUpdate();
		if (num < 1) {
			logger.warn("执行SQL[" + namedQuery + "],未有记录被删除");
		}
	}

	@Override
	public <T> void updateByNamedQuery(JPQuery jPQ) {
		Query query = em.createNamedQuery(jPQ.getJPQL());

		Map<String, Object> paramaters = jPQ.getParamaters();
		for (Map.Entry<String, Object> entry : paramaters.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}

		int num = query.executeUpdate();
		if (num < 1) {
			logger.warn("执行SQL[" + jPQ.getJPQL() + "],未有记录被更新");
		}
	}

	@Override
	public <T> void updateByNamedQuery(String namedQuery) {
		Query query = em.createQuery(namedQuery);

		int num = query.executeUpdate();
		if (num < 1) {
			logger.warn("执行SQL[" + namedQuery + "],未有记录被更新");
		}
	}

	@Override
	public <T> T findOneByNamedQuery(JPQuery jPQ) {
		Query query = em.createNamedQuery(jPQ.getJPQL());

		Map<String, Object> paramaters = jPQ.getParamaters();
		for (Map.Entry<String, Object> entry : paramaters.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}

		try {
			return (T) query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		} catch (NonUniqueResultException e) {
			logger.error("重复记录：执行SQL[" + jPQ.getJPQL() + "],存在多个重复的记录");
			return null;
		}
	}

	@Override
	public <T> T findOneByNamedQuery(String namedQuery) {
		Query query = em.createNamedQuery(namedQuery);

		try {
			return (T) query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		} catch (NonUniqueResultException e) {
			logger.error("重复记录：执行SQL[" + namedQuery + "],存在多个重复的记录");
			return null;
		}
	}

	@Override
	public <T> List<T> findByNamedQuery(JPQuery jPQ) {
		Query query = em.createNamedQuery(jPQ.getJPQL());

		Map<String, Object> paramaters = jPQ.getParamaters();
		for (Map.Entry<String, Object> entry : paramaters.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}

		return query.getResultList();
	}

	@Override
	public <T> List<T> findByNamedQuery(JPQuery jPQ, int pageNo, int pageSize) {
		Query query = em.createNamedQuery(jPQ.getJPQL());

		Map<String, Object> paramaters = jPQ.getParamaters();
		for (Map.Entry<String, Object> entry : paramaters.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		query.setFirstResult((pageNo - 1) * pageSize);
		query.setMaxResults(pageSize);
		return query.getResultList();
	}

	@Override
	public <T> List<T> findByNamedQuery(String namedQuery) {
		Query query = em.createNamedQuery(namedQuery);
		return query.getResultList();
	}

	@Override
	public <T> List<T> findByNamedQuery(String namedQuery, int pageNo, int pageSize) {
		Query query = em.createNamedQuery(namedQuery);
		query.setFirstResult((pageNo - 1) * pageSize);
		query.setMaxResults(pageSize);
		return query.getResultList();
	}

}
