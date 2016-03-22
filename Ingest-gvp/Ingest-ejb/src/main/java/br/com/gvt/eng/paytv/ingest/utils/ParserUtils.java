package br.com.gvt.eng.paytv.ingest.utils;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;

import br.com.gvt.eng.paytv.ingest.model.IngestAsset;
import br.com.gvt.eng.paytv.ingest.model.IngestFolder;
import br.com.gvt.eng.paytv.ingest.vo.IngestAssetVO;
import br.com.gvt.eng.paytv.ingest.vo.IngestFolderVO;

public class ParserUtils {

	// private static final Logger logger = Logger.getLogger(ParserUtils.class);

	private static final DozerBeanMapper mapper = new DozerBeanMapper();

	/**
	 * Constructs new instance of destinationClass and performs mapping between
	 * from source object
	 * 
	 * @param sourceObject
	 * @param destinationClass
	 *            return New instance of destinationClass
	 */
	protected static <T, V> V parse(T sourceObject, Class<V> destinationClass) {
		if (sourceObject == null) {
			return null;
		}

		V v = null;

		try {
			v = mapper.map(sourceObject, destinationClass);
		} catch (Exception e) {
			// logger.error("Could not convert object to destination class: "
			// + destinationClass.getSimpleName() + ". Object: "
			// + ToStringBuilder.reflectionToString(sourceObject), e);
		}

		return v;
	}

	/**
	 * @param ingestFolder
	 * @return IngestFolderVO
	 */
	public static IngestFolderVO parseIngestFolder(IngestFolder ingestFolder) {
		return ParserUtils.parse(ingestFolder, IngestFolderVO.class);
	}

	/**
	 * @param ingestFolderVO
	 * @return IngestFolder
	 */
	public static IngestFolder parseIngestFolderVO(IngestFolderVO ingestFolderVO) {
		return ParserUtils.parse(ingestFolderVO, IngestFolder.class);
	}

	/**
	 * @param values
	 * @return List<IngestFolderVO>
	 */
	public static List<IngestFolderVO> parseListIngestFolderVO(
			List<IngestFolder> values) {
		List<IngestFolderVO> vo = new ArrayList<IngestFolderVO>();
		for (IngestFolder ingestFolder : values) {
			vo.add(ParserUtils.parse(ingestFolder, IngestFolderVO.class));
		}
		return vo;
	}

	/**
	 * @param assetList
	 * @return List<IngestAsset>
	 */
	public static List<IngestAsset> parseListIngest(
			List<IngestAssetVO> assetList) {
		List<IngestAsset> assets = new ArrayList<IngestAsset>();
		for (IngestAssetVO ingestAssetVO : assetList) {
			assets.add(ParserUtils.parse(ingestAssetVO, IngestAsset.class));
		}
		return assets;
	}

	/**
	 * @param ingestAsset
	 * @return IngestAssetVO
	 */
	public static IngestAssetVO parseIngestVO(IngestAsset ingestAsset) {
		return ParserUtils.parse(ingestAsset, IngestAssetVO.class);
	}

	/**
	 * @param ingestAssetVO
	 * @return IngestAsset
	 */
	public static IngestAsset parseIngest(IngestAssetVO ingestAssetVO) {
		return ParserUtils.parse(ingestAssetVO, IngestAsset.class);
	}

}
