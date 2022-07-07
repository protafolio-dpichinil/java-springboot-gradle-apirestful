package cl.dpichinil.portafolio.javaspringbootgradleapirestful.service.impl;

import cl.dpichinil.portafolio.javaspringbootgradleapirestful.config.properties.ApplicationProperties;
import cl.dpichinil.portafolio.javaspringbootgradleapirestful.config.exception.CustomException;
import cl.dpichinil.portafolio.javaspringbootgradleapirestful.dto.PhoneDto;
import cl.dpichinil.portafolio.javaspringbootgradleapirestful.dto.UserDto;
import cl.dpichinil.portafolio.javaspringbootgradleapirestful.dto.mapper.PhoneMapper;
import cl.dpichinil.portafolio.javaspringbootgradleapirestful.dto.mapper.UserMapper;
import cl.dpichinil.portafolio.javaspringbootgradleapirestful.persistence.entity.Phone;
import cl.dpichinil.portafolio.javaspringbootgradleapirestful.persistence.entity.User;
import cl.dpichinil.portafolio.javaspringbootgradleapirestful.persistence.repository.PhoneRepository;
import cl.dpichinil.portafolio.javaspringbootgradleapirestful.persistence.repository.UserRepository;
import cl.dpichinil.portafolio.javaspringbootgradleapirestful.service.UserService;
import cl.dpichinil.portafolio.javaspringbootgradleapirestful.util.TokenUtil;
import cl.dpichinil.portafolio.javaspringbootgradleapirestful.util.ValidatorFields;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PhoneRepository phoneRepository;
    private final ApplicationProperties applicationProperties;

    @Override
    public UserDto insertUser(UserDto dto) {
        ValidatorFields.validateUserDto(dto);

        int count = userRepository.getByEmail(dto.getEmail());
        if(count > 0) throw new CustomException(1003, HttpStatus.INTERNAL_SERVER_ERROR, "insertUser");
        String token = TokenUtil.generateToken(dto.getEmail(), applicationProperties);
        dto.setToken(token);

        User user = UserMapper.parseUserDtoToUser(dto);
        user.setActive(true);
        user = userRepository.save(user);

        List<Phone> listPhone = new ArrayList<>();
        for (PhoneDto phoneDto: dto.getPhones()) {
            Phone phone = PhoneMapper.phoneDtoToPhone(phoneDto);
            phone.setUser(user);
            phone = phoneRepository.save(phone);
            listPhone.add(phone);
        }
        user.setListPhone(listPhone);

        dto = UserMapper.parseUserToUserDto(user);
        dto.setEmail(null);
        dto.setPassword(null);
        dto.setPhones(null);
        dto.setName(null);
        return dto;
    }

    @Override
    public UserDto getUserById(int id) {
        UserDto dto = null;
        Optional<User> optional = userRepository.findById(id);
        if(optional.isPresent()){
            User user = optional.get();
            user.setLastLogin(new Date());
            user = userRepository.save(user);
            dto = UserMapper.parseUserToUserDto( user );
        }
        return dto;
    }

}
