package mil.army.usarec.ariss.api.contact.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mil.army.usarec.ariss.api.contact.domain.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
	List<Contact> findByPrId(long prId);
}
