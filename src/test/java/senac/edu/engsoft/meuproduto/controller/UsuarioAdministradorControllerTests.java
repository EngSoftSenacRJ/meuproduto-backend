
package senac.edu.engsoft.meuproduto.controller;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import senac.edu.engsoft.meuproduto.model.UsuarioAdministrador;
import senac.edu.engsoft.meuproduto.service.UsuarioAdministradorService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
//@Import(UsuarioAdministradorService.class)
//@ContextConfiguration(classes = {TestSecurityConfiguration.class})
public class UsuarioAdministradorControllerTests {
	
	@Autowired 
	private MockMvc mockMvc;
	
	@Autowired
	private UsuarioAdministradorService service;
	
	private RestDocumentationResultHandler documentationHandler;
	@Autowired
	private WebApplicationContext context;
	@Rule
	public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();
	
	private static final String BASE_URL = "/administradores";

	@Before
	public void iniciarTestes() {
		this.documentationHandler = document("{method-name}",
				preprocessRequest(prettyPrint()),
				preprocessResponse(prettyPrint()));

			this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
				.apply(documentationConfiguration(this.restDocumentation))
				.alwaysDo(this.documentationHandler)
				.build();
		
		for(UsuarioAdministrador usuarioAdministrador : service.getAll()) {
			service.delete(usuarioAdministrador.getId());
		}
	}
	
//	 @Test
//    public void contextLoads() {
//        assertThat(mvc, IsNull.notNullValue());
//        assertThat(service, IsNull.notNullValue());
//    }
	
	@Test
	@WithMockUser(username = "admin", password = "admin", roles = {"ADMIN"})
	public void deveInserirUmNovoUsuarioAdministrador() throws Exception {
		
		UsuarioAdministrador usuarioAdministrador = new UsuarioAdministrador(
				"Rua abc 21", 
				"21", 
				"Centro", 
				"Rio de Janeiro", 
				"Rio de Janeiro", 
				"21021021", 
				"Rômulo Deroci", 
				21971036046L, 
				12952707731L, 
				"rderociml@gmail.com",
				LocalDate.now());
		
		mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL)
			.contentType(MediaType.APPLICATION_JSON)
			.content(asJsonString(usuarioAdministrador))
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.cpf").value(12952707731L));
		
	}
	
	public static String asJsonString(final Object obj) {
	    try {
	    	String json = new ObjectMapper().writeValueAsString(obj);
	        return json;
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}
