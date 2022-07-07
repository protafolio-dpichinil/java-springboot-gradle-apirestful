package cl.dpichinil.portafolio.javaspringbootgradleapirestful.config.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class CustomException extends  RuntimeException{
    private int code;
    private HttpStatus status;
    private String module;

    public CustomException(int code, HttpStatus status, String module) {
        this.code = code;
        this.status = status;
        this.module = module;
    }
}
