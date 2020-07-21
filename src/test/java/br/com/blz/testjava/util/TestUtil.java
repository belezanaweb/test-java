package br.com.blz.testjava.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtil {

	public static String parseJson(final Object obj) {
	    try {
	        final ObjectMapper mapper = new ObjectMapper();
	        final String jsonContent = mapper.writeValueAsString(obj);
	        return jsonContent;
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}
