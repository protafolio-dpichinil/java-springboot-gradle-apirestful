package cl.dpichinil.portafolio.javaspringbootgradleapirestful.util;

import cl.dpichinil.portafolio.javaspringbootgradleapirestful.config.exception.CustomException;
import cl.dpichinil.portafolio.javaspringbootgradleapirestful.dto.UserDto;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class ValidatorFields {
    public static void validateUserDto(UserDto dto) {
        if(validatePassword(dto.getPassword()))
            throw new CustomException(1001, HttpStatus.INTERNAL_SERVER_ERROR,"insertUser");
        if(validateMail(dto.getEmail()))
            throw new CustomException(1002, HttpStatus.INTERNAL_SERVER_ERROR,"insertUser");

    }

    private static boolean validatePassword(String text){
        String regx = Constant.REX_PASSWORD;;
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher(text);
        if(!matcher.matches()){
            return true;
        }
        return false;
    }
    private static boolean validateMail(String text){
        String regx = Constant.REX_EMAIL;
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher(text);
        if(!matcher.matches()){
            return true;
        }
        return false;
    }
}
