package com.gvt.eng.ipvod.proccess.dao;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.gvt.eng.vod.model.IpvodContentProvider;

@Repository
@Transactional
public class ContentProviderDAOImpl implements ContentProviderDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public IpvodContentProvider findContendProviderByParam(String providerName,
			String providerID) {
		Session session = sessionFactory.getCurrentSession();
		IpvodContentProvider ipvodContentProvider = new IpvodContentProvider();
		ipvodContentProvider = (IpvodContentProvider) session
				.createQuery(
						"SELECT ip FROM IpvodContentProvider ip WHERE ip.providerName = :providerName AND ip.providerId =:providerId")
				.setParameter("providerName", providerName)
				.setParameter("providerId", providerID).uniqueResult();
		session.flush();
		session.clear();
		return ipvodContentProvider;
	}

}
