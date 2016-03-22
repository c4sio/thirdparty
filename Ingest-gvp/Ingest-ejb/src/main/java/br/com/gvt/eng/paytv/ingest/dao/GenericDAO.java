package br.com.gvt.eng.paytv.ingest.dao;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;

public abstract class GenericDAO<EntityClass> {

	public GenericDAO(Class<EntityClass> entityClass) {
		this.entityClass = entityClass;
	}

	private final static String UNIT_NAME = "Ingest-ejb";

	@PersistenceContext(unitName = UNIT_NAME)
	private EntityManager em;

	private Class<EntityClass> entityClass;

	/**
	 * @param Entity
	 */
	public EntityClass save(EntityClass Entity) {
		em.persist(Entity);
		em.flush();
		return Entity;
	}

	/**
	 * @param id
	 * @param classe
	 */
	protected void delete(Object id, Class<EntityClass> classe) {
		EntityClass entityToBeRemoved = em.getReference(classe, id);
		em.remove(entityToBeRemoved);
		em.flush();
	}

	/**
	 * @param entity
	 * @return result of update
	 */
	public EntityClass update(EntityClass entity) {
		return em.merge(entity);
	}

	/**
	 * @param entityID
	 * @return result of data
	 */
	public EntityClass find(long entityID) {
		return em.find(entityClass, entityID);
	}

	/**
	 * @return List of data
	 */
	@SuppressWarnings("unchecked")
	public List<EntityClass> findAll() {
		CriteriaQuery<EntityClass> cq = (CriteriaQuery<EntityClass>) em
				.getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		return em.createQuery(cq).getResultList();
	}

	/**
	 * @param namedQuery
	 * @param parameters
	 * @return EntityClass
	 */
	@SuppressWarnings("unchecked")
	protected EntityClass findOneResult(String namedQuery,
			Map<String, Object> parameters) {
		EntityClass result = null;

		try {
			Query query = em.createNamedQuery(namedQuery);

			/*
			 * Method that will populate parameters if they are passed not null
			 * and empty
			 */
			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}

			result = (EntityClass) query.getSingleResult();

		} catch (Exception e) {
			System.out.println("Error while running query: " + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	protected List<EntityClass> findResultByParameter(String namedQuery,
			Map<String, Object> parameters) {
		List<EntityClass> result = null;

		try {
			Query query = em.createNamedQuery(namedQuery);

			/*
			 * Method that will populate parameters if they are passed not null
			 * and empty
			 */
			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}

			result = (List<EntityClass>) query.getResultList();

		} catch (Exception e) {
			System.out.println("Error while running query: " + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @param query
	 * @param parameters
	 */
	private void populateQueryParameters(Query query,
			Map<String, Object> parameters) {
		for (Entry<String, Object> entry : parameters.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
	}

	/**
	 * @param namedQuery
	 * @param parameters
	 */
	protected int deleteByParameter(String namedQuery,
			Map<String, Object> parameters) {

		try {
			Query query = em.createNamedQuery(namedQuery);

			/*
			 * Method that will populate parameters if they are passed not null
			 * and empty
			 */
			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}

			return query.executeUpdate();

		} catch (Exception e) {
			System.out.println("Error while running query: " + e.getMessage());
			e.printStackTrace();
			return 0;
		}
	}

	protected Session getSession() {
		return (Session) em.getDelegate();
	}

}
