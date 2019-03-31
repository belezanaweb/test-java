package br.com.blz.testjava.util;

public class ResponseDefault <T> {
	
	/** Responsta do tipo do objeto */
	private Object response;
	/** mensagem de retorno do serviço */
	private String msg;
	/** total de Registros */
	private Long totalRegistros;
	
	/**
	 *  Construtor para objeto de resposta de serviço
	 * @param objeto
	 * @param totalRegistros
	 * @param msg
	 */
	public ResponseDefault(T objeto, Long totalRegistros, String msg) {
		this.response = objeto;
		this.totalRegistros = totalRegistros;
		this.msg = msg;
		
	}
	
	public ResponseDefault() {
	
	}
	
	/**
	 * Construtor para objeto de resposta de serviço
	 * @param objeto
	 * @param msg
	 */
	public ResponseDefault(T objeto, String msg) {
		this.totalRegistros = 0L;
		this.response = objeto;
		this.msg = msg;
	}
	
	/**
	 * Construtor para objeto de resposta de serviço
	 * 
	 * @param objeto
	 */
	public ResponseDefault(T objeto) {
		this.totalRegistros = 0L;
		this.response = objeto;
		this.msg = objeto.toString();
	}


	/**
	 * Retorna o objeto de resposta do serviço
	 * @return Object
	 */
	public Object getResponse() {
		return response;
	}

	/**
	 * Obtem a mensagem resposta de serviço podendo ser SUCESSO OU ERRO
	 * @return msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * Obtem o total de registros retornado pelo serviço
	 * @return totalRegistros
	 */
	public Long getTotalRegistros() {
		return totalRegistros;
	}

	/**
	 * Atribui o total de registros do serviço
	 * @param totalRegistros
	 */
	public void setTotalRegistros(Long totalRegistros) {
		this.totalRegistros = totalRegistros;
	}


}
