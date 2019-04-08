package br.com.blz.testjava.gateways.http.converter;

import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ErrorResponse implements Serializable {

  private static final long serialVersionUID = 4435959686991135330L;

  private String error;

  public ErrorResponse(String error) {
    this.error = error;
  }
}
