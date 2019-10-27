/**
 * 
 */
package br.com.blz.testjava.enums;

/**
 * @author ocean
 *
 */
public enum ProductEnum {
	
	PRODUCT_ALREADY_EXIST(1, "Produto já existe! "),
	PRODUCT_NOT_EXISTS(2, "Produto não existe!");
	
	private Integer cd;
	private String message;
	
	ProductEnum(final Integer codigo, final String message) {
		this.message = message;
		this.cd = codigo;		
	}	

	/**
	 * Gets the msg.
	 *
	 * @return the msg
	 */
	public String getMessage() {
		return message;
	}
	
	public Integer getCd() {
		return cd;
	}

}
