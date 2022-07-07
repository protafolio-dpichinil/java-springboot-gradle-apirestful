package cl.dpichinil.portafolio.javaspringbootgradleapirestful.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private int id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String email;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String password;
    private String createdDate;
    private String modifiedDate;
    private String lastLogin;
    private String token;
    private boolean active;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<PhoneDto> phones;
}