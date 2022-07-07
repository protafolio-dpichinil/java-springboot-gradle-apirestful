package cl.dpichinil.portafolio.javaspringbootgradleapirestful.dto.mapper;

import cl.dpichinil.portafolio.javaspringbootgradleapirestful.dto.PhoneDto;
import cl.dpichinil.portafolio.javaspringbootgradleapirestful.persistence.entity.Phone;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class PhoneMapper {

    public static List<Phone> listPhoneDtoToListPhone(List<PhoneDto> list) {
        List<Phone> response = new ArrayList<>();
        if(list != null){
            for (PhoneDto dto: list ) {
                Phone e = phoneDtoToPhone(dto);
                response.add(e);
            }
        }
        return response;
    }

    public static Phone phoneDtoToPhone(PhoneDto dto) {
        Phone e = new Phone();
        e.setNumber(dto.getNumber());
        e.setCityCode(dto.getCityCode());
        e.setCountryCode(dto.getCountryCode());
        return e;
    }

    public static List<PhoneDto> listPhoneToListPhoneDto(List<Phone> phones) {
        List<PhoneDto> response = new ArrayList<>();
        if(phones != null){
            for (Phone e: phones ) {
                PhoneDto dto = phoneToPhoneDto(e);
                response.add(dto);
            }
        }
        return response;
    }

    private static PhoneDto phoneToPhoneDto(Phone e) {
        PhoneDto dto = new PhoneDto();
        dto.setNumber(e.getNumber());
        dto.setCityCode(e.getCityCode());
        dto.setCountryCode(e.getCountryCode());
        return dto;
    }
}
