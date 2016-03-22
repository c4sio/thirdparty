package com.gvt.eng.ipvod.proccess.dao;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.gvt.eng.vod.model.IpvodLanguage;

@Repository
@Transactional
public class LanguageDAOImpl implements LanguageDAO {

	Logger logger = Logger.getLogger(LanguageDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public String setCodeLanguageByName(String languageName) {
		logger.info("Verificando os dados de Language");
		if (languageName == null || languageName.equals("")) {
			return "";
		}
		String[] values = languageName.split(",");
		String result[] = new String[values.length];
		try {
			Session session = sessionFactory.getCurrentSession();
			IpvodLanguage ipvodLanguage = new IpvodLanguage();
			for (int i = 0; i < values.length; i++) {
				ipvodLanguage = (IpvodLanguage) session
						.createQuery(
								"SELECT ila FROM IpvodLanguage ila WHERE upper(ila.ipvodLanguagePK.variants) =:languageName")
						.setParameter("languageName", values[i].toString())
						.uniqueResult();
				if (ipvodLanguage.getIpvodLanguagePK().getLanguage() != null) {
					result[i] = ipvodLanguage.getIpvodLanguagePK()
							.getLanguage();
				}
			}
		} catch (Exception e) {
			logger.error("Erro ao verificar dados Language: ", e);
		}
		return StringUtils.join(result, ",");
	}

}
