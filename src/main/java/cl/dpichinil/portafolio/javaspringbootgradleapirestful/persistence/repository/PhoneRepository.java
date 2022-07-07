package cl.dpichinil.portafolio.javaspringbootgradleapirestful.persistence.repository;

import cl.dpichinil.portafolio.javaspringbootgradleapirestful.persistence.entity.Phone;
import org.springframework.data.repository.CrudRepository;

public interface PhoneRepository extends CrudRepository<Phone,Integer> {
}
