package mil.army.usarec.ariss.api.contact.web;


import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.hasSize;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import mil.army.usarec.ariss.api.contact.domain.Contact;
import mil.army.usarec.ariss.api.contact.service.ContactService;

@RunWith(SpringRunner.class)
@WebMvcTest(ContactController.class)
public class ContactControllerTest {
	
	@MockBean
	ContactService contactService;
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper mapper;
	
	Contact contact;
	
	@Before
	public void setup() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		contact = new Contact(101200L,sdf.parse("11/8/2017"),"TC","CN","AU","This is a good lead need to follow up",201200L);
		
	}
	
	@Test
	public void shoudSaveContact() throws Exception{
		
		mockMvc.perform(post("/api/v1/contacts")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(contact))
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void shouldRetrieveContactsForPrid() throws Exception{
		List<Contact> contacts = new ArrayList<Contact>();
		contacts.add(contact);
		given(contactService.getContactsForPrId(101200L)).willReturn(contacts);
		
		MvcResult result = mockMvc.perform(get("/api/v1/contacts/101200"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].actionCd", is("TC")))
				.andExpect(jsonPath("$[0].resultCd", is("CN")))
				.andExpect(jsonPath("$[0].comment", is("This is a good lead need to follow up"))).andReturn();
	}

}
