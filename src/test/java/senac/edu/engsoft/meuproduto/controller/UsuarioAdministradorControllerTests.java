
package senac.edu.engsoft.meuproduto.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import senac.edu.engsoft.meuproduto.model.Usuario;
import senac.edu.engsoft.meuproduto.model.UsuarioAdministrador;
import senac.edu.engsoft.meuproduto.model.UsuarioType;
import senac.edu.engsoft.meuproduto.service.UsuarioAdministradorService;

import java.time.LocalDate;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UsuarioAdministradorControllerTests {

	@Autowired
	private MockMvc mockMvc;

	private RestDocumentationResultHandler documentationHandler;
	@Autowired
	private WebApplicationContext context;

	@Rule
	public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();

	@Autowired
	private UsuarioAdministradorService service;

	@BeforeAll
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

	@Test
	public void deveRegistrarUmNovoUsuarioAdministrador() throws Exception {

		Usuario usuario = new Usuario();
		usuario.setUsername("rderociml@gmail.com");
		usuario.setPassword("123");
		usuario.setBairroEnderecoPessoal("a");
		usuario.setCepEnderecoPessoal("21021021");
		usuario.setCidadeEnderecoPessoal("a");
		usuario.setCpf(12952707731L);
		usuario.setDataAniversario(LocalDate.now());
		usuario.setEstadoEnderecoPessoal("a");
		usuario.setNome("a");
		usuario.setNumeroEnderecoPessoal("1");
		usuario.setRuaEnderecoPessoal("a");
		usuario.setUsuarioType(UsuarioType.ADMINISTRADOR);

		mockMvc.perform(MockMvcRequestBuilders.post("/register")
			.contentType(MediaType.APPLICATION_JSON)
			.content(asJsonString(usuario))
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated());

	}

	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}
