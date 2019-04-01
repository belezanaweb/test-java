package br.com.blz.testjava.config;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessagesUtils {
	
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("messages", new Locale("pt","BR"));
	
	public static String getMessage(String key) {
		return BUNDLE.getString(key);
	}

}
