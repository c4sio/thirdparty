package com.gvt.eng.ipvod.proccess.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.gvt.eng.vod.model.IpvodIngestStage;

@Repository
@Transactional
public class IngestDAOImpl implements IngestDAO {

	Logger logger = Logger.getLogger(IngestDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public IpvodIngestStage findIngestByAssetInfo(String assetInfo) {
		Session session = sessionFactory.getCurrentSession();
		IpvodIngestStage ipvodIngestStage = new IpvodIngestStage();
		ipvodIngestStage = (IpvodIngestStage) session
				.createQuery(
						"SELECT ing FROM IpvodIngestStage ing WHERE assetInfo = :assetInfo and ing.stageType = 3")
				.setParameter("assetInfo", assetInfo).uniqueResult();
		session.flush();
		session.clear();
		return ipvodIngestStage;
	}

	@Override
	public void update(IpvodIngestStage ipvodIngestStage) {
		Session session = sessionFactory.getCurrentSession();
		session.merge(ipvodIngestStage);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IpvodIngestStage> findFilesToExecute() {
		List<IpvodIngestStage> listData = new ArrayList<IpvodIngestStage>();
		try {
			logger.info("Buscando arquivos no ingest para processar");
			Session session = sessionFactory.getCurrentSession();
			listData = (List<IpvodIngestStage>) session
					.createQuery(
							"SELECT ing FROM IpvodIngestStage ing, IpvodIngestType ingt WHERE ing.stageType.id = ingt.id AND ingt.id = 3 and ing.ipvodAsset.assetId is null")
					.list();
		} catch (Exception e) {
			logger.error("Erro ao listar dados de ingest: ", e);
		}
		return listData;
	}

}
