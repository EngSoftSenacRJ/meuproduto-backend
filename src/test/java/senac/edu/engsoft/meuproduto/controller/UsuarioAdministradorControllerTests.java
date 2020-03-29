
package senac.edu.engsoft.meuproduto.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import senac.edu.engsoft.meuproduto.model.Usuario;
import senac.edu.engsoft.meuproduto.model.UsuarioType;
import senac.edu.engsoft.meuproduto.service.UsuarioAdministradorService;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UsuarioAdministradorControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UsuarioAdministradorService service;

	@BeforeAll
	public void iniciarTestes() {

	}

	@Test
	public void deveRegistrarUmNovoUsuarioAdministrador() throws Exception {

		Usuario usuario = new Usuario();
		usuario.setUsername("romulo@gmail.com");
		usuario.setPassword("123");
		usuario.setBairroEnderecoPessoal("Olaria");
		usuario.setCepEnderecoPessoal("21021021");
		usuario.setCidadeEnderecoPessoal("Rio de Janeiro");
		usuario.setCpf(31577278003L);
		usuario.setDataAniversario(LocalDate.now());
		usuario.setEstadoEnderecoPessoal("Rio de Janeiro");
		usuario.setNome("Rômulo Rocha");
		usuario.setNumeroEnderecoPessoal("99");
		usuario.setRuaEnderecoPessoal("Rua Abc de Def");
		usuario.setUsuarioType(UsuarioType.ADMINISTRADOR);
		usuario.setId(1L);

		mockMvc.perform(MockMvcRequestBuilders.post("/register")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(usuario))
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
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
