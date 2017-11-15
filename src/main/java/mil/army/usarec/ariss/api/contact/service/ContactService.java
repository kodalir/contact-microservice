package mil.army.usarec.ariss.api.contact.service;

import java.util.List;

import org.springframework.stereotype.Service;

import mil.army.usarec.ariss.api.contact.domain.Contact;


public interface ContactService {
	public void saveContact(Contact contact);
	public List<Contact> getContactsForPrId(long PrId);
}
