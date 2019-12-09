package br.com.blz.testjava.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.testjava.dto.SKUDTO;
import br.com.blz.testjava.service.SKUService;

@RestController
public class SKUController {

	@Autowired
	SKUService service;

	/**
	 * 
	 * @param sku
	 * @param response
	 * @return
	 */
	@RequestMapping(path = "/belezanaweb/{sku}", method = RequestMethod.GET)
	public @ResponseBody SKUDTO getSKU(@PathVariable int sku, HttpServletResponse response) {
		SKUDTO dto = service.getSKU(sku);

		if (dto == null) {
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		}

		return dto;
	}

	/**
	 * 
	 * @param skuDTO
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(path = "/belezanaweb", method = RequestMethod.PUT)
	public @ResponseBody SKUDTO includeSKU(@RequestBody SKUDTO skuDTO, HttpServletResponse response)
			throws Exception {
		try {

			SKUDTO pd = service.createSKU(skuDTO);
			response.setStatus(HttpServletResponse.SC_CREATED);

		} catch (Exception e) {
			// Caso um produto já existente em memória tente ser criado com o mesmo sku uma
			// exceção deverá ser lançada
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			throw e;
		}
		return null;
	}

	/**
	 * 
	 * @param skuDTO
	 * @param response
	 * @return
	 */
	@RequestMapping(path = "/belezanaweb", method = RequestMethod.POST)
	public @ResponseBody SKUDTO updateSKU(@RequestBody SKUDTO skuDTO, HttpServletResponse response) {
		SKUDTO pd = service.updateSKU(skuDTO);
		if (pd == null) {
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		}
		return pd;
	}

	/**
	 * 
	 * @param sku
	 * @return
	 */
	@RequestMapping(path = "/belezanaweb/{sku}", method = RequestMethod.DELETE)
	public @ResponseBody SKUDTO deleteSKU(@PathVariable int sku) {
		return service.removeSKU(sku);
	}

}
