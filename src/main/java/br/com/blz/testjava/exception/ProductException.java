/**
 * 
 */
package br.com.blz.testjava.exception;

/**
 * @author ocean
 *
 */
public class ProductException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8962898200865870824L;

	private Integer codMsg;
	
	private String message;



	public ProductException() {
		super();
	}

	public ProductException(Integer codMsg, String message) {
		super();
		this.codMsg = codMsg;
		this.message = message;
	}

	/**
	 * @return the codMsg
	 */
	public Integer getCodMsg() {
		return codMsg;
	}

	/**
	 * @param codMsg the codMsg to set
	 */
	public void setCodMsg(Integer codMsg) {
		this.codMsg = codMsg;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
