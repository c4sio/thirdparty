package com.gvt.eng.ipvod.proccess.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.gvt.eng.vod.model.IpvodAsset;
import br.com.gvt.eng.vod.model.IpvodIngestStage;
import br.com.gvt.eng.vod.model.IpvodIngestType;

@Repository
@Transactional
public class AssetDAOImpl implements AssetDAO {

	Logger logger = Logger.getLogger(AssetDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private IngestDAO ingestDAO;

	@SuppressWarnings("unchecked")
	public List<IpvodAsset> findAll() {
		Session session = sessionFactory.getCurrentSession();
		logger.info("Lista Assets");
		return (List<IpvodAsset>) session.createQuery("from IpvodAsset").list();
	}

	public void save(IpvodAsset asset) {
		Session session = sessionFactory.getCurrentSession();
		logger.info("Save Asset " + asset.getTitle());
		session.save(asset);
		session.flush();
		session.clear();
	}

	private void updateIngest(IpvodAsset ipvodAsset) {
		// Busca dados de Ingest
		IpvodIngestStage ipvodIngestStage = new IpvodIngestStage();
		ipvodIngestStage = ingestDAO.findIngestByAssetInfo(ipvodAsset
				.getAssetInfo());

		// Atribui dados de Asset para o Ingest
		if (ipvodIngestStage != null) {
			System.out.println("Save Ingest ID " + ipvodIngestStage.getId()
					+ " com dados do Asset " + ipvodAsset.getAssetId());
			logger.info("Save Ingest ID " + ipvodIngestStage.getId()
					+ " com dados do Asset " + ipvodAsset.getAssetId());
			ipvodIngestStage.setIpvodAsset(ipvodAsset);
			IpvodIngestType ipvodIngestType = new IpvodIngestType();
			ipvodIngestType.setId(new Long(4));
			ipvodIngestStage.setStageType(ipvodIngestType);
			ingestDAO.update(ipvodIngestStage);
		}
	}

	public boolean createAsset(IpvodAsset asset) {
		try {
			// Salva os dados de Asset
			Session session = sessionFactory.getCurrentSession();
			logger.info("Save Asset " + asset.getTitle());
			session.save(asset);

			// Atualiza dados Ingest
			updateIngest(asset);

			session.flush();
			session.clear();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Erro ao criar Asset - " + asset.getTitle() + " - "
					+ e.getMessage());
			return false;
		}
	}
}
