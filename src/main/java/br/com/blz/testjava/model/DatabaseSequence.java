package br.com.blz.testjava.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
@Document(collection = "database_sequences")
public class DatabaseSequence {

	@Id
	private String id;
	
	private long sequence;
	
	public DatabaseSequence() {}
	
}
