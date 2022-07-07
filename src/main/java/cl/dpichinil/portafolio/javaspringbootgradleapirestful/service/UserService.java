package cl.dpichinil.portafolio.javaspringbootgradleapirestful.service;

import cl.dpichinil.portafolio.javaspringbootgradleapirestful.dto.UserDto;

public interface UserService {
    public UserDto insertUser(UserDto dto);

    public UserDto getUserById(int id);
}
