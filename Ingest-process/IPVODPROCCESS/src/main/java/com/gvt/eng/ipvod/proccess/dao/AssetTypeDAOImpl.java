package com.gvt.eng.ipvod.proccess.dao;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.gvt.eng.vod.model.IpvodAssetType;

@Repository
@Transactional
public class AssetTypeDAOImpl implements AssetTypeDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public IpvodAssetType findAssetTypeByName(String assetTypeName) {
		Session session = sessionFactory.getCurrentSession();
		IpvodAssetType ipvodAssetType = new IpvodAssetType();

		ipvodAssetType = (IpvodAssetType) session
				.createQuery(
						"SELECT iat FROM IpvodAssetType iat where iat.description = :description")
				.setParameter("description", assetTypeName).uniqueResult();
		session.flush();
		session.clear();

		return ipvodAssetType;
	}

}
