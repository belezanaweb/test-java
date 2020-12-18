package br.com.blz.testjava.api.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(Include.NON_NULL)
@Getter
@Builder
public class ProblemDTO {

	private LocalDateTime dateTime;
	private Integer status;
	private String type;
	private String title;
	private String details;
	private List<Field> fields;

	@Getter
	@Builder
	public static class Field{
		private String name;
		private String message;
	}
}
