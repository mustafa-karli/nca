package com.nauticana.basis.abstrct;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.basis.utils.FieldType;
import com.nauticana.basis.utils.Labels;
import com.nauticana.basis.utils.Utils;

public abstract class AbstractService<ModelBean, ModelId extends Serializable> implements IAbstractService<ModelBean, ModelId> {
	
	@Autowired
	protected JpaRepository<ModelBean, ModelId> r;

	@PersistenceContext
    protected EntityManager entityManager;

	@Override
	public boolean customerSpecific() {
		return false;
	};
	
	@Override
	public void setClient(ModelBean entity, int client) {
	};

	@Override
	public int getClient(ModelBean entity) {
		return -1;
	};

	@Override
	public boolean			organizationFiltered() {
		return false;
	}

	@Override
	public ModelBean findById(ModelId id) {
		return r.findById(id).get();
	}

	@Override
	public String idToStr(ModelId id) {
		return id.toString();
	}

	@SuppressWarnings("unchecked")
	@Override
	public 	Class<ModelBean> modelClass() {
		return (Class<ModelBean>) ((java.lang.reflect.ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	};

	
	@SuppressWarnings("unchecked")
	public List<ModelBean> search(ArrayList<String> fields, ArrayList<String> filters, ArrayList<Integer> types) throws ParseException {
		String sql;
		String whr = "";
		for (int i = 0; i < fields.size(); i++) {
			String filter = filters.get(i);
			if (filter.contains("*") || filter.contains("%")) {
				filters.set(i, filter.replaceAll("\\*", "\\%"));
				whr = whr + " AND " + fields.get(i) + " LIKE  ?";
			} else 
				whr = whr + " AND " + fields.get(i) + " =  ?";
		}
		if (Utils.emptyStr(whr))
			sql = "SELECT T.* FROM " + tableName() + " T";
		else
			sql = "SELECT T.* FROM " + tableName() + " T WHERE " + whr.substring(5);
		Query query = entityManager.createNativeQuery(sql, modelClass());
		for (int i = 0; i < filters.size(); i++) {
			switch (types.get(i)) {
			case FieldType.T_SHORT  : query.setParameter(i+1, Short.parseShort(filters.get(i)));   break;
			case FieldType.T_INT    : query.setParameter(i+1, Integer.parseInt(filters.get(i)));   break;
			case FieldType.T_LONG   : query.setParameter(i+1, Long.parseLong(filters.get(i)));     break;
			case FieldType.T_FLT    : query.setParameter(i+1, Double.parseDouble(filters.get(i))); break;
			case FieldType.T_DATE   : query.setParameter(i+1, Labels.dmyDF.parse(filters.get(i))); break;
			default: query.setParameter(i+1, filters.get(i));
			}
		}
		return query.getResultList();
	}
	
	@Override
	public ModelBean create(ModelBean entity) throws Exception {
		try {
			return r.save(entity);
		} catch(DataAccessException e) {
			throw new Exception(e.getRootCause().getMessage());
		} catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public void save(ModelBean entity) throws Exception {
		try {
			r.save(entity);
		} catch(DataAccessException e) {
			throw new Exception(e.getRootCause().getMessage());
		} catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public void remove(ModelId id) throws Exception {
		try {
			r.deleteById(id);
		} catch(DataAccessException e) {
			throw new Exception(e.getRootCause().getMessage());
		} catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public List<ModelBean> findAll() {
		return r.findAll();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ModelBean> findAll(int client) {
		if (customerSpecific()) {
			String findAllSql = "SELECT T.* FROM " + tableName() + " T WHERE OWNER_ID=?";
			ModelBean m = newEntityWithParentId(null);
			Query query = entityManager.createNativeQuery(findAllSql, m.getClass());
			query.setParameter(1, client);
			return query.getResultList();
		} else
			return r.findAll();
	}

	@Override
	public List<ModelBean> findByIds(List<String> ids) {
		List<ModelBean> entities = new ArrayList<ModelBean>();
		for (String s : ids) {
			if (s.equals("*"))
				return r.findAll();
			ModelId id = strToId(s);
			ModelBean entity = (ModelBean) r.findById(id).get();
			entities.add(entity);
		}
		return entities;
	}
	
	@Override
	public List<ModelBean> findByIdArray(ModelId[] ids) {
		List<ModelBean> entities = new ArrayList<ModelBean>();
		for (ModelId id : ids) {
			ModelBean entity = (ModelBean) r.findById(id).get();
			entities.add(entity);
		}
		return entities;
	}
	
	@Override
	public String[][] findAllForLookup(int client) {
		return null;
	}

	@Override
	public String tableName() {
	    Table t = modelClass().getAnnotation(Table.class);
	    if (t == null) return null;
	    return t.name();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ModelBean> findByOrganization(int client, String organizations) {
		String findAllSql;
		if (customerSpecific())
			findAllSql = "SELECT T.* FROM " + tableName() + " T WHERE OWNER_ID=" + client + " AND ORGANIZATION_ID IN (" + organizations + ")";
		else
			findAllSql = "SELECT T.* FROM " + tableName() + " T WHERE ORGANIZATION_ID IN (" + organizations + ")";
		Query query = entityManager.createNativeQuery(findAllSql, modelClass());
		return query.getResultList();
	}

}
