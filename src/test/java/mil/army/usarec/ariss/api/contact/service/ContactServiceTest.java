package mil.army.usarec.ariss.api.contact.service;


import static org.junit.Assert.assertThat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import mil.army.usarec.ariss.api.contact.dao.ContactRepository;
import mil.army.usarec.ariss.api.contact.domain.Contact;

@RunWith(SpringRunner.class)
@ContextConfiguration
public class ContactServiceTest {
	
	//Configuration class for injecting the ContactService
	@Configuration
	static class ContactServiceTestContextConfiguration{
		
		@Bean
		public ContactService contactService(){
			return new ContactServiceImpl();
		}
	}

	@Autowired
	ContactService contactService;
	
	@MockBean
	ContactRepository contactRepository;
	
	Contact contact;
	@Before
	public void setUp() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		contact = new Contact(101200L,sdf.parse("11/8/2017"),
							"TC","CN","AU","This is a good lead need to follow up",201200L);
		List<Contact> contacts = new ArrayList<Contact>();
		contacts.add(contact);
		
		Mockito.when(contactRepository.save(contact)).thenReturn(contact);
		Mockito.when(contactRepository.findByPrId(101200L)).thenReturn(contacts);
		
	}
	
	@Test
	public void shouldSaveandReturnContactsForPrId() {
		//save the contact *** need to expand on how to test the save 
		contactService.saveContact(contact);
		
		//retrieve the contact
		Contact retrivedContact = contactService.getContactsForPrId(101200L).get(0);
		assertThat(contact, Is.is(retrivedContact));
	}
	
}
