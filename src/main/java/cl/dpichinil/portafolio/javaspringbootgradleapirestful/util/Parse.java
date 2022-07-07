package cl.dpichinil.portafolio.javaspringbootgradleapirestful.util;

import cl.dpichinil.portafolio.javaspringbootgradleapirestful.config.properties.MessageProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Parse {
    private static ObjectMapper mapper = new ObjectMapper();

    public static String generateMessageProperties(MessageProperties messageProperties, int code, String module) {
        String response = "";
        switch (module){
            case "insertUser":
                response = messageProperties.getInsertUser().get(code);
                break;
            default:
                break;
        }
        return response;
    }

    public static <T> T JsonToObject(String json, Class<T> classType){
        try {
            return (T) Parse.mapper.readValue(json, classType);
        }catch(Exception e){
            return null;
        }
    }

    public static String ObjectToJson(Object object) {
        try {
            return Parse.mapper.writeValueAsString(object);
        }catch(Exception e){
            return null;
        }
    }
}
