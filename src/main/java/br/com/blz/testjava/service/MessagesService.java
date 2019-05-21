package br.com.blz.testjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

/**
 * Service para buscar mensagens no messages.properties
 */
@Service
public class MessagesService {
	
	@Autowired
	private MessageSource messageSource;

	public String get(String key) {
		return messageSource.getMessage(key, null, LocaleContextHolder.getLocaleContext().getLocale());	}
}

