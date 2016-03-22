package br.com.gvt.createuser.controller;

import javax.annotation.security.PermitAll;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.com.gvt.createuser.file.FileFunctions;
import br.com.gvt.createuser.rest.UserRestService;
import br.com.gvt.createuser.rest.UserRestServiceImpl;
import br.com.gvt.createuser.vo.UserVO;

@Path("/services")
public class CreateUserController {

	@PermitAll
	@POST
	@Path("/user")
	@Produces("application/json; charset=UTF-8")
	public Response saveUser(UserVO userVo) throws Exception {
		UserRestService userRestService = new UserRestServiceImpl();
		userVo = userRestService.createUser(userVo);
		// Se nao ocorreu erro ao criar o usuario efetua ativacao do mesmo
		if (!userVo.isError()) {
			userVo = userRestService.activeUser(userVo);
			// Se nao ocorreu erro ao Ativar o usuario efetua ativacao dos
			// produtos
			if (!userVo.isError()) {
				userVo = userRestService.updateService(userVo);
				// Grava dados
				if (!userVo.isError()) {
					FileFunctions fileFunctions = new FileFunctions();
					fileFunctions.writeInFile(userVo.getName() + ";"
							+ userVo.getSubscreberId() + ";"
							+ userVo.getEmail() + ";" + userVo.getPassword()
							+ ";" + userVo.getAndroidId() + ";"
							+ userVo.getAppleId());
				}
			}
		}
		return Response.status(201).entity(userVo).build();
	}

	@PermitAll
	@POST
	@Path("/active")
	@Produces("application/json; charset=UTF-8")
	public Response activeUser(UserVO userVo) throws Exception {
		UserRestService userRestService = new UserRestServiceImpl();
		userVo = userRestService.activeUser(userVo);
		return Response.status(201).entity(userVo).build();
	}

	@PermitAll
	@POST
	@Path("/update")
	@Produces("application/json; charset=UTF-8")
	public Response updateService(UserVO userVo) throws Exception {
		UserRestService userRestService = new UserRestServiceImpl();
		userVo = userRestService.updateService(userVo);
		return Response.status(201).entity(userVo).build();
	}

}
