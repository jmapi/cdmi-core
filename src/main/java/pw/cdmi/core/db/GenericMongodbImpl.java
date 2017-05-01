package pw.cdmi.core.db;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.mongodb.WriteResult;

public class GenericMongodbImpl implements GenericDao {
	private static final Logger logger = LoggerFactory.getLogger(GenericMongodbImpl.class);

	@Autowired
	private MongoTemplate mongoTemplate;

//	/**
//	 * 修改满足条件的第一条记录
//	 * @param query
//	 * @param update
//	 * @param clazz
//	 */
//	public <T> void updateFirst(Query query, Update update, Class<T> clazz) {
//		logger.info("[Mongo Dao ]updateFirst:query(" + query + "),update(" + update + ")");
//		this.mongoTemplate.updateFirst(query, update, clazz);
//	}
//
//	/**
//	 * 修改满足条件的多条记录
//	 * @param query
//	 * @param update
//	 * @param clazz
//	 */
//	public <T> void updateMulti(Query query, Update update, Class<T> clazz) {
//
//	}
//
//	/**
//	 * 修改,如果要修改的对象不存在则添加
//	 * @param query
//	 * @param update
//	 * @param clazz
//	 */
//	public <T> void updateInsert(Query query, Update update, Class<T> clazz) {
//		logger.info("[Mongo Dao ]updateInser:query(" + query + "),update(" + update + ")");
//		this.mongoTemplate.upsert(query, update, clazz);
//	}


	@Override
	public <T> void save(T entity) {
		mongoTemplate.save(entity);
	}
	
	@Override
	public <T> List<T> save(Iterable<T> entities) {
		List<T> _entities = new ArrayList<T>();
		Iterator<T> iter = entities.iterator(); 
		while(iter.hasNext()) {
			T entity = iter.next();
			mongoTemplate.save(entity);
			_entities.add(entity); 
		}
		return _entities;
	}

	@Override
	public <T> void saveOrUpdate(T entity) {
		mongoTemplate.save(entity);
	}
	
	@Override
	public <T, PK extends Serializable> void delete(PK id, Class<T> clazz) {
		Criteria criteria = Criteria.where("_id").in(id);
		if (null != criteria) {
			Query query = new Query(criteria);
			logger.info("[Mongo Dao ]deleteById:" + query);
			if (null != query && mongoTemplate.find(query, clazz) != null) {
				this.delete(query);
			}
		}
	}
	@Override
	public <T> void delete(T entity) {
		mongoTemplate.remove(entity);
	}
	
	@Override
	public <T> void delete(JPQuery jPQ) {
		Query query = new Query();
//		FIXME 
//		Query query = mongoTemplate.(jPQ.getJPQL());
		
		Map<String, Object> paramaters = jPQ.getParamaters();
		for (Map.Entry<String, Object> entry : paramaters.entrySet()) {
			 Criteria criteria = Criteria.where(entry.getKey()).is(entry.getValue());
			 query.addCriteria(criteria);
		}
		
//		this.mongoTemplate.remove(query, clazz);
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> long deleteAll(Class<T> clazz) {
		//FIXME
		Query query = new Query();
		WriteResult result = mongoTemplate.remove(query, clazz);
		return result.getN();
	}
	
	@Override
	public <T> void update(JPQuery jPQ) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> void update(String jpql) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> void update(T transientObject) {
		mongoTemplate.save(transientObject);
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
	public <T, PK extends Serializable> boolean exists(PK id, Class<T> clazz) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public <T> long getCount(JPQuery jPQ) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <T> long getCount(String jpql) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <T> long getCount(Class<T> clazz) {
		Query query = new Query();
		return mongoTemplate.count(query, clazz);
	}
	
	@Override
	public <T, PK extends Serializable> T get(PK id, Class<T> clazz) {
		return (T) mongoTemplate.findById(id, clazz);
	}
	@Override
	public <T> List<T> findAll(Class<T> clazz) {
		return mongoTemplate.findAll(clazz);
	}
	
	@Override
	public <T> List<T> findAll(Class<T> clazz, int pageNo, int pageSize) {
		Query query = new Query();
		int start = (pageNo - 1) * pageSize;
		start = (start > 0) ? start : 0;
		query.skip(start);
		query.limit(pageSize);
		logger.info("[Mongo Dao ]queryPage:" + query + "(" + start + "," + pageSize + ")");
		return mongoTemplate.find(query, clazz);
	}

	@Override
	public <T> T findOne(JPQuery jPQ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T findOne(String jpql) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> find(JPQuery jPQ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> find(JPQuery jPQ, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> find(String jpql) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public <T> List<T> find(String jpql, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> void saveByNamedQuery(JPQuery jPQ) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> void saveByNamedQuery(String namedQuery) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByNamedQuery(JPQuery jPQ) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByNamedQuery(String namedQuery) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> void updateByNamedQuery(JPQuery jPQ) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> void updateByNamedQuery(String namedQuery) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> T findOneByNamedQuery(JPQuery jPQ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T findOneByNamedQuery(String namedQuery) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> findByNamedQuery(JPQuery jPQ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> findByNamedQuery(JPQuery jPQ, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> findByNamedQuery(String namedQuery) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> findByNamedQuery(String namedQuery, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

}
