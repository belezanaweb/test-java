package br.com.blz.testjava.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * Classe auxiliar para injeção de dependências em classes não gerenciasdas
 * @author rick
 *
 */
@Service
public class BeanUtil implements ApplicationContextAware {
	
    private static ApplicationContext context;
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
    
    /**
     * Obtem um bean do contexto do spring para injeção de dependência
     * @param beanClass
     * @return managed bean
     */
    public static <T> T getBean(Class<T> beanClass) {
        return context.getBean(beanClass);
    }
    
}