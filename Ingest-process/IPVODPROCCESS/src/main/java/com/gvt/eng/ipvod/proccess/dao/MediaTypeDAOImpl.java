package com.gvt.eng.ipvod.proccess.dao;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.gvt.eng.vod.model.IpvodMediaType;

@Repository
@Transactional
public class MediaTypeDAOImpl implements MediaTypeDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public IpvodMediaType findMediaTypeByName(String mediaTypeName) {
		Session session = sessionFactory.getCurrentSession();
		IpvodMediaType ipvodMediaType = new IpvodMediaType();

		ipvodMediaType = (IpvodMediaType) session
				.createQuery(
						"SELECT mt FROM IpvodMediaType mt where mt.description = :description")
				.setParameter("description", mediaTypeName).uniqueResult();
		session.flush();
		session.clear();

		return ipvodMediaType;
	}

}
