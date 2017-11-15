package mil.army.usarec.ariss;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import mil.army.usarec.ariss.api.contact.ContactAPIApplication;
import mil.army.usarec.ariss.api.contact.domain.Contact;


/**
 * This class performs the integration test on the ContactAPI microservice. There is no mocking here it 
 * does the entire end to end testing on an embedded tomcat
 * 
 * @author kodalir
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=ContactAPIApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ContactAPIApplicationTests {

	TestRestTemplate restTemplate = new TestRestTemplate();
	
	@LocalServerPort
	int port;
	
	String baseURL;
	Contact contact;
	HttpHeaders httpHeaders = new HttpHeaders();
	
	@Before
	public void setup() throws Exception {
		baseURL = "http://localhost:"+port+"/api/v1";
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		contact = new Contact(101200L,sdf.parse("11/8/2017"),"TC","CN","AU","This is a good lead need to follow up",201200L);
		httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
	}
	
	
	
	@Test
	public void shouldAddContact() {
		HttpEntity<Contact> entity = new HttpEntity<Contact>(contact, httpHeaders);
		ResponseEntity<String> response = restTemplate.exchange(baseURL+"/contacts",HttpMethod.POST,entity,String.class);
		assertThat(response.getStatusCode(), is(HttpStatus.OK));
	}
	
	@Test
	public void shouldRetriveContact() throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		ResponseEntity<String> response = restTemplate.getForEntity(baseURL+"/contacts/101200", String.class);
		assertThat(response.getStatusCode(), is(HttpStatus.OK));
		
		JsonNode root = mapper.readTree(response.getBody());
		List<String> actionCdList = root.findValuesAsText("actionCd");
		List<String> recruiterIdList = root.findValuesAsText("recruiterId");
		assertThat(actionCdList.get(0),is("TC"));
		assertThat(recruiterIdList.get(0),is("201200"));
	}

}
