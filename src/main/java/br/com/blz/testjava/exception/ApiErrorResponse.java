package br.com.blz.testjava.exception;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class ApiErrorResponse implements Serializable {

	private static final long serialVersionUID = -887511740619921720L;
	
	private String code;
	
	private String message;
	
	private String description;
 
}
