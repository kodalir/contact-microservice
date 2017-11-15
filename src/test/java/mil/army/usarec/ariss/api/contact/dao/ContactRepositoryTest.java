package mil.army.usarec.ariss.api.contact.dao;

import static org.junit.Assert.assertThat;

import java.text.SimpleDateFormat;
import java.util.List;

import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import mil.army.usarec.ariss.api.contact.domain.Contact;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ContactRepositoryTest {

	@Autowired
	ContactRepository contactRepository;
	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	
	
	@Test
	public void shouldSaveAndFetchContact() throws Exception {
		Contact contact = new Contact(101200L,sdf.parse("11/8/2017"),"TC","CN","AU","This is a good lead need to follow up",201200L);
		contactRepository.save(contact);
		
		List<Contact> savedContact = contactRepository.findByPrId(101200L);
		
		assertThat(contact, Is.is(savedContact.get(0)));
	}
	
	
}
