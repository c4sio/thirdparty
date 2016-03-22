package br.com.gvt.createuser.rest;

import br.com.gvt.createuser.exception.ClientException;
import br.com.gvt.createuser.exception.ServerException;
import br.com.gvt.createuser.vo.UserVO;

public interface UserRestService {

	/**
	 * @param userVo
	 * @return UserVO
	 * @throws ClientException
	 * @throws ServerException
	 */
	public abstract UserVO createUser(UserVO userVo) throws ClientException,
			ServerException;

	/**
	 * @param userVo
	 * @return UserVO
	 * @throws ClientException
	 * @throws ServerException
	 */
	public abstract UserVO activeUser(UserVO userVo) throws ClientException,
			ServerException;

	/**
	 * @param userVo
	 * @return UserVO
	 * @throws ClientException
	 * @throws ServerException
	 */
	public abstract UserVO updateService(UserVO userVo) throws ClientException,
			ServerException;

}
