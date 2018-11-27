package br.com.blz.testjava;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true, value = {"cause", "stackTrace", "localizedMessage", "message", "suppressed"})
public class MsgException extends RuntimeException {
  
  @JsonProperty
  private int code = 0;
  
  @JsonProperty
  private Object data = null;
  
  @JsonProperty
  private String msg = "";
  
  @JsonIgnore
  private int httpStatus = 400;
  
  // -----------------------------
  
  public MsgException(Throwable ex){ 
    super(ex.getMessage(), ex); this.msg = ex.getMessage(); 
  }
  public MsgException(String msg, Throwable ex){ 
    super(msg, ex); this.msg = msg; 
  }
  public MsgException(int code, String msg, Throwable ex){ 
    super(msg, ex); this.code = code; this.msg = msg; 
  }
  public MsgException(Object data, String msg, Throwable ex){ 
    super(msg, ex); this.data = data; this.msg = msg; 
  }
  public MsgException(int code, Object data, String msg, Throwable ex){ 
    super(msg, ex); this.code = code; this.data = data; this.msg = msg; 
  }
  public MsgException(int code, Object data, int httpStatus, String msg, Throwable ex){ 
    super(msg, ex); this.code = code; this.data = data; this.httpStatus = httpStatus; this.msg = msg; 
  }
  
  // ----------------------------------------------
  
  public MsgException code(int code){ this.code = code; return this; }
  public MsgException msg(String msg){ this.msg = msg; return this; }
  public MsgException data(Object data){ this.data = data; return this; }
  public MsgException httpStatus(int httpStatus){ this.httpStatus = httpStatus; return this; }
  
  
}
