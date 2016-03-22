package com.gvt.eng.ipvod.proccess.dao;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.gvt.eng.vod.model.IpvodRating;

@Repository
@Transactional
public class RatingDAOImpl implements RatingDAO {

	Logger logger = Logger.getLogger(RatingDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public IpvodRating findRating(String value, boolean isAdult) {
		IpvodRating ipvodRating = new IpvodRating();
		Session session = sessionFactory.getCurrentSession();
		ipvodRating = (IpvodRating) session
				.createQuery(
						"SELECT ir FROM IpvodRating ir WHERE ir.adult = :isAdult AND ir.rating =:rating")
				.setParameter("isAdult", isAdult).setParameter("rating", value)
				.uniqueResult();
		return ipvodRating;
	}

}
