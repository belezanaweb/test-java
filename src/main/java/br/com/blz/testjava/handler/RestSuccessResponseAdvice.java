package br.com.blz.testjava.handler;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice(basePackages = "br.com.blz.testjava.resource")
public class RestSuccessResponseAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
    	//return returnType.getParameterType().equals(Product.class);
    	return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
        ServerHttpResponse response) {
        final RestResponse<Object> output = new RestResponse<>();
        output.setData(body);
        output.setHttpcode(200);
        output.setStatus("Success");
        return output;
    }
    

    public static class RestResponse<T> {

        private String status;
        private int httpcode;
        private T data;
        
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public int getHttpcode() {
			return httpcode;
		}
		public void setHttpcode(int httpcode) {
			this.httpcode = httpcode;
		}
		public T getData() {
			return data;
		}
		public void setData(T data) {
			this.data = data;
		}
    }
}
