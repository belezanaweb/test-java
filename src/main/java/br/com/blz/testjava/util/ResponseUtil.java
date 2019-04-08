package br.com.blz.testjava.util;

import javax.ws.rs.core.Response;

import org.json.JSONException;

public class ResponseUtil {

	public static String getMessage(Response response) throws JSONException {

		return JsonUtil.getField("message", response.readEntity(String.class));
	}
}
