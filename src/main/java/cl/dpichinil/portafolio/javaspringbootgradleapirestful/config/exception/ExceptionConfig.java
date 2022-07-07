package cl.dpichinil.portafolio.javaspringbootgradleapirestful.config.exception;

import cl.dpichinil.portafolio.javaspringbootgradleapirestful.config.properties.MessageProperties;
import cl.dpichinil.portafolio.javaspringbootgradleapirestful.dto.ResponseDto;
import cl.dpichinil.portafolio.javaspringbootgradleapirestful.util.Parse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class ExceptionConfig {
    private final MessageProperties messageProperties;

    @ExceptionHandler(value=Exception.class)
    private ResponseEntity<ResponseDto> ExceptionHandler(Exception e){
        log.error(e.getMessage(), e);
        int code = 1000;
        String message = messageProperties.getGeneralMessage().get(code);
        ResponseDto response = new ResponseDto(message);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = CustomException.class)
    private ResponseEntity<ResponseDto> customExceptionHandler(CustomException e){
        log.error(e.getMessage());
        String message = Parse.generateMessageProperties(messageProperties, e.getCode(), e.getModule());
        ResponseDto response = new ResponseDto(message);
        return new ResponseEntity<>(response, e.getStatus());
    }
}
