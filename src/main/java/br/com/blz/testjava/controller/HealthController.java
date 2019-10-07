package br.com.blz.testjava.controller;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.testjava.util.HostnameSourceUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api
@Path("/about")
@RestController
@Produces(MediaType.TEXT_HTML)
public class HealthController {
	public static final String WILDCARD_HOST_NAME = "@env.host_name@";

	@Autowired
	private ApplicationContext applicationContext;

	@GET
	@ResponseBody
	@ApiOperation(value = "Serviço que retorna informações da aplicação", produces = "text/html;charset=utf-8")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Serviço esta disponível")})
	public String about() throws IOException {
		Resource resource = applicationContext.getResource("classpath:about.properties");
		return "<pre>" + IOUtils.toString(resource.getInputStream()).replace(HealthController.WILDCARD_HOST_NAME,
				HostnameSourceUtil.getHostname()) + "</pre>";
	}

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
}
