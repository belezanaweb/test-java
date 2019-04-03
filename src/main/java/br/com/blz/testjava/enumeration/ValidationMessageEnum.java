package br.com.blz.testjava.enumeration;

public enum ValidationMessageEnum {

	DEFAULT_MESSAGE(Constants.DEFAULT_MESSAGE),
	REQUIRED_FIELD(Constants.REQUIRED_FIELD),
	PRODUCT_ALREADY_EXISTS(Constants.PRODUCT_ALREADY_EXISTS),
	NUMERIC_FIELD(Constants.NUMERIC_FIELD), 
	INVALID_WHAREHOUSE_TYPE(Constants.INVALID_WHAREHOUSE_TYPE)
	;
	
	private String key;
	
	private ValidationMessageEnum(String key) {		
		this.key = key;
	}
	
	public String getKey() {
		return key;
	}

	public static class Constants {		
		public static final String DEFAULT_MESSAGE = "default.message";
		public static final String REQUIRED_FIELD = "required.field";
		public static final String PRODUCT_ALREADY_EXISTS = "product.already.exists";
		public static final String NUMERIC_FIELD = "numeric.field";
		public static final String INVALID_WHAREHOUSE_TYPE = "invalid.wharehousetype";
	}
	
}