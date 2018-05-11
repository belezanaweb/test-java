package br.com.blz.testjava;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ResponseCuston {
	private String status;
	private String msg;
	private String value;

	public ResponseCuston() {
	}

	public static ResponseCuston ok(String msg) {
		ResponseCuston r = new ResponseCuston();
		r.setStatus("Ok");
		r.setMsg(msg);
		return r;
	}
	public static ResponseCuston ok(String msg, String value) {
		ResponseCuston r = new ResponseCuston();
		r.setStatus("Ok");
		r.setMsg(msg);
		r.setValue(value);
		return r;
	}
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public static ResponseCuston fail(String msg) {
		ResponseCuston r = new ResponseCuston();
		r.setStatus("NOK");
		r.setMsg(msg);
		return r;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}


}
