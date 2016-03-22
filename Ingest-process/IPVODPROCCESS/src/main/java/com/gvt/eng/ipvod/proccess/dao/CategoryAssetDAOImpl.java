package com.gvt.eng.ipvod.proccess.dao;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.gvt.eng.vod.model.IpvodCategory;

@Repository
@Transactional
public class CategoryAssetDAOImpl implements CategoryAssetDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public long findCategoryByName(String categoryName) {
		Session session = sessionFactory.getCurrentSession();
		IpvodCategory ipvodCategory = new IpvodCategory();
		ipvodCategory = (IpvodCategory) session
				.createQuery(
						"SELECT ic FROM IpvodCategory ic WHERE ic.description = :description")
				.setParameter("description", categoryName).uniqueResult();

		// Inclui a categoria default caso não encontre
		if (ipvodCategory == null) {
			ipvodCategory = new IpvodCategory();
			ipvodCategory.setCategoryId(99L);
		}

		return ipvodCategory.getCategoryId();
	}

}
