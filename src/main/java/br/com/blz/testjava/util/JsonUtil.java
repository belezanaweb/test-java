package br.com.blz.testjava.util;

import javax.ws.rs.client.Entity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.blz.testjava.dto.AbstractDTO;

public class JsonUtil {

	public static String dtoToJson(AbstractDTO dto) {

		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
		return gson.toJson(dto);
	}

	public static String toJson(Object object) {

		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
		return gson.toJson(object);
	}

	public static Object fromJson(String json) {

		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
		return gson.fromJson(json, Object.class);
	}

	public static Entity<String> EntityToJsonEntity(Object object) {

		return Entity.json(toJson(object));
	}
	
	public static Entity<String> dtoToJsonEntity(AbstractDTO dto) {

		return Entity.json(dtoToJson(dto));
	}

	public static String getField(String fieldName, String object) throws JSONException {

		JSONObject jsonObj = new JSONObject(object);
		return jsonObj.getString(fieldName);
	}

	public static JSONArray getArray(String fieldName, String object) throws JSONException {

		JSONObject jsonObj = new JSONObject(object);
		return jsonObj.getJSONArray(fieldName);
	}
}
