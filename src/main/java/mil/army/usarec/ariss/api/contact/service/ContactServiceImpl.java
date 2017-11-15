package mil.army.usarec.ariss.api.contact.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mil.army.usarec.ariss.api.contact.dao.ContactRepository;
import mil.army.usarec.ariss.api.contact.domain.Contact;

@Service("contactService")
public class ContactServiceImpl implements ContactService {

	@Autowired
	ContactRepository contactRepository;
	
	@Override
	public void saveContact(Contact contact) {
		
		contactRepository.save(contact);
	}

	@Override
	public List<Contact> getContactsForPrId(long prId) {
		// TODO Auto-generated method stub
		return contactRepository.findByPrId(prId);
	}

}
