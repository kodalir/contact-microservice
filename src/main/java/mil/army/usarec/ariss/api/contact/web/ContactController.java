package mil.army.usarec.ariss.api.contact.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;



import mil.army.usarec.ariss.api.contact.domain.Contact;
import mil.army.usarec.ariss.api.contact.service.ContactService;

@RestController
@RequestMapping(value="/api/v1/contacts")
public class ContactController {
	 private static final Logger log = LoggerFactory.getLogger(ContactController.class);
	@Autowired
	ContactService contactService;
	
	//get all contacts for a prid
	@RequestMapping(value="/{prId}",
					method= RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<Contact> getContactsForPerson(@PathVariable("prId") long prId){
		log.info("Getting all the contacts for "+prId);
		List<Contact> contacts = contactService.getContactsForPrId(prId);
		if(contacts != null) {
			log.info("Number fo contacts:"+contacts.size());
		}
		return contacts;
	}
	//add a contact for a prid
	@RequestMapping(value="",
					method=RequestMethod.POST)
	public void saveContact(@RequestBody Contact contact) {
		log.info("Saving a contact");
		log.info(contact.toString());
		contactService.saveContact(contact);
	}
}
