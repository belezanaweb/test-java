package br.com.blz.util;

import java.io.StringReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.stream.JsonReader;

import br.com.blz.exception.BlzJsonParseException;
import br.com.blz.request.EntradaPadrao;
import br.com.blz.request.SaidaPadrao;

/**
 * Esta classe deve ser colocada em um jar separado o jar ser uma dependencia desse projeto
 * 
 * Classe auxiliar para transformar Json em Object e Object em Json.
 *
 * @author tiago
 */
public class JsonUtil {

	/** The gson. */
	private static Gson gson = new Gson();

	/**
	 * Construtor privado.
	 */
	private JsonUtil() {
	}

	/**
	 * To object.
	 *
	 * @param json
	 *            the json
	 * @param clazz
	 *            the clazz
	 * @return the object
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Object toObject(final String json, final Class clazz,
			final boolean limpaJson)  {

		final String jsonIntern = json;
		Object fromJson = null;
		try {
			fromJson = gson.fromJson(jsonIntern, clazz);
		} catch (final JsonParseException e) {
			throw new BlzJsonParseException(e);
		}

		return fromJson;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static EntradaPadrao toObj(final Object entrada, final Class clazz) {
		final EntradaPadrao entradaPadrao = new EntradaPadrao();
		
		LinkedHashMap<String, Object> hashMap = (LinkedHashMap<String, Object>) entrada;
		
		try {
			Set set = hashMap.entrySet();
			Iterator iterator = set.iterator();
			
			while(iterator.hasNext()) {
				Map.Entry item = (Map.Entry) iterator.next();
				
				System.out.println("Key = " + item.getKey() + " Value = " + item.getValue());
				
				if(item.getValue().getClass().getName().equals("java.lang.String")) {
					System.out.println("Key = " + item.getKey() + " Value = " + item.getValue());
				}
				
				Field field = clazz.getDeclaredField((String) item.getKey());
				field.getClass();
			}
		} catch (final JsonParseException e) {
			throw new BlzJsonParseException(e);
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return entradaPadrao;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> EntradaPadrao toObject(final Object entrada, final Class clazz) 
			throws BlzJsonParseException {
		final EntradaPadrao entradaPadrao = new EntradaPadrao();

		try {
			if ("br.com.blz.request.EntradaPadrao".equals(entrada.getClass().getName())) {
				return (EntradaPadrao) entrada;
			}
			final Object token = ((LinkedHashMap) entrada).get("token");

			if (token != null) {
				entradaPadrao.setToken(token.toString());
			}
			
			Gson gs1 = new Gson();
			LinkedHashMap<String, Object> hashMap = (LinkedHashMap<String, Object>) entrada;
			/*
			String res = gs1.toJson(hashMap.get("objeto"), LinkedHashMap.class);
			
			Gson gs = new Gson();
			Object obj = gs.fromJson(res, clazz);
			entradaPadrao.setObjeto(obj);
			*/
			
			Set set = hashMap.entrySet();
			Iterator iterator = set.iterator();
			
			while(iterator.hasNext()) {
				Map.Entry item = (Map.Entry) iterator.next();
				
				System.out.println("Key = " + item.getKey() + " Value = " + item.getValue());
				
				if(item.getValue().getClass().getName().equals("java.lang.String")) {
					System.out.println("Key = " + item.getKey() + " Value = " + item.getValue());
				}
				
				Field field = clazz.getDeclaredField((String) item.getKey());
				field.getClass();
			}
			

		} catch (final JsonParseException e) {
			throw new BlzJsonParseException(e);
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return entradaPadrao;
	}

	/**
	 * Transforma um Json no formato SaidaPadrao para objeto.
	 *
	 * @param json
	 *            O Json a ser transformado.
	 * @param clazz
	 *            O tipo do atributo objeto do Json informado.
	 * @return O Json convertido.
	 * @throws BlzJsonParseException
	 *             Quando ocorrer um erro na conversão.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static SaidaPadrao toObjectSaidaPadrao(final String json, final Class clazz) 
			throws BlzJsonParseException {
		SaidaPadrao saidaPadrao = null;

		try {

			saidaPadrao = (SaidaPadrao) JsonUtil.toObject(json, SaidaPadrao.class, false);

			Object objetoSaida = null;

			if (saidaPadrao.getObjeto() instanceof ArrayList) {
				final List listaEntrada = (ArrayList) saidaPadrao.getObjeto();
				final List listaSaida = new ArrayList();

				for (final Object itemLista : listaEntrada) {
					listaSaida.add(getObjetoInterno(itemLista, clazz));
				}

				objetoSaida = listaSaida;

			} else {
				objetoSaida = getObjetoInterno(saidaPadrao.getObjeto(), clazz);
			}

			saidaPadrao.setObjeto(objetoSaida);

		} catch (final JsonParseException e) {
//			throw new BlzJsonParseException(e);
		}

		return saidaPadrao;
	}

	private static Object getObjetoInterno(final Object objeto, final Class<?> clazz) {
		final String jsonObjeto = toJson(objeto);

		final JsonReader reader = new JsonReader(new StringReader(jsonObjeto));
		reader.setLenient(true);

		return gson.fromJson(reader, clazz);
	}

	/**
	 * From linked hash map to object <T>.
	 *
	 * @param <T>
	 *            the generic type
	 * @param objeto
	 *            the objeto
	 * @param classOfT
	 *            the class of t
	 * @return the t
	 * @throws BlzJsonParseException
	 *             the alelo json parse exception
	 */
	public static <T> T fromLinkedHashMapToObject(final Object objeto, final Class<T> classOfT) { 
//			throws BlzJsonParseException {
		try {
			return new Gson().fromJson(new Gson().toJson(objeto, LinkedHashMap.class), classOfT);
		} catch (final JsonParseException e) {
//			throw new BlzJsonParseException(e);
			return null;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static EntradaPadrao toObjectWithList(final Object entrada, final Class clazz) { 
//			throws BlzJsonParseException {
		final EntradaPadrao entradaPadrao = new EntradaPadrao();

		try {
			final Object token = ((java.util.LinkedHashMap) entrada).get("token");

			if (token != null) {
				entradaPadrao.setToken(token.toString());
			}

			entradaPadrao.setObjeto(new Gson().fromJson(
					new Gson().toJson(((java.util.LinkedHashMap<String, Object>) entrada).get("objeto"), java.util.ArrayList.class), clazz));

		} catch (final JsonParseException e) {
//			throw new BlzJsonParseException(e);
			return null;
		}

		return entradaPadrao;
	}

	/**
	 * To object.
	 *
	 * @param json
	 *            the json
	 * @param clazz
	 *            the clazz
	 * @return the object
	 */
	@SuppressWarnings("rawtypes")
	public static Object toObject(final String json, final Class clazz) {
//			throws BlzJsonParseException {
		return toObject(json, clazz, true);
	}

	/**
	 * To json.
	 *
	 * @param object
	 *            the object
	 * @return the string
	 */
	public static String toJson(final Object object) {
		return gson.toJson(object);
	}

}