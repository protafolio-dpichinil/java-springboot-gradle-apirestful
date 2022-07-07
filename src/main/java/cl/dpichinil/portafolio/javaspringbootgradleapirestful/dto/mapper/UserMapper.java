package cl.dpichinil.portafolio.javaspringbootgradleapirestful.dto.mapper;

import cl.dpichinil.portafolio.javaspringbootgradleapirestful.dto.PhoneDto;
import cl.dpichinil.portafolio.javaspringbootgradleapirestful.dto.UserDto;
import cl.dpichinil.portafolio.javaspringbootgradleapirestful.persistence.entity.User;
import lombok.experimental.UtilityClass;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@UtilityClass
public class UserMapper {

    public static User parseUserDtoToUser(UserDto dto) {
        User e = new User();
        e.setName(dto.getName());
        e.setEmail(dto.getEmail());
        e.setPassword(dto.getPassword());
        if(dto.getCreatedDate() == null) e.setCreatedDate( new Date() );
        if(dto.getModifiedDate() == null) e.setModifiedDate( new Date() );
        if(dto.getLastLogin() == null) e.setLastLogin( new Date() );
        if( dto.isActive() ) e.setActive(true);
        e.setToken(dto.getToken());
        return e;
    }

    public static UserDto parseUserToUserDto(User e) {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        UserDto dto = new UserDto();
        dto.setId( e.getId() );
        dto.setName( e.getName() );
        dto.setEmail( e.getEmail() );
        dto.setPassword( e.getPassword() );
        dto.setCreatedDate(sdf.format( e.getCreatedDate() ));
        dto.setModifiedDate(sdf.format( e.getModifiedDate() ));
        dto.setLastLogin(sdf.format( e.getLastLogin() ));
        dto.setActive( e.isActive() );
        dto.setToken(e.getToken());
        List<PhoneDto> list = PhoneMapper.listPhoneToListPhoneDto(e.getListPhone());
        dto.setPhones(list);
        return dto;
    }
}
