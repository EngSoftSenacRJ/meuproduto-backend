package senac.edu.engsoft.meuproduto;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.subsectionWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import senac.edu.engsoft.meuproduto.model.Loja;
import senac.edu.engsoft.meuproduto.service.LojaService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiDocumentation {

	@Rule
	public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();

	private RestDocumentationResultHandler documentationHandler;

	@Autowired
	private LojaService lojaService;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;
	
	private Long lojaID;

	@Before
	public void setUp() {
		this.documentationHandler = document("{method-name}",
			preprocessRequest(prettyPrint()),
			preprocessResponse(prettyPrint()));

		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
			.apply(documentationConfiguration(this.restDocumentation))
			.alwaysDo(this.documentationHandler)
			.build();
		
		lojaID = null;
	}

	@Test
	public void headersExample() throws Exception {
		this.mockMvc
			.perform(get("/"))
			.andExpect(status().isOk())
			.andDo(this.documentationHandler.document(
				responseHeaders(
					headerWithName("Content-Type").description("The Content-Type of the payload, e.g. `application/hal+json`"))));
	}

	@Test
	public void indexExample() throws Exception {
		this.mockMvc.perform(get("/"))
			.andExpect(status().isOk())
			.andDo(this.documentationHandler.document(
				links(
					linkWithRel("lojas").description("The <<resources-lojas,Lojas resource>>")),
				responseFields(
					subsectionWithPath("_links").description("<<resources-index-links,Links>> to other resources"))));
	}
	
	@Test
	public void adicionarLoja() throws Exception {
		lojaID = createLoja("Loja do Romulo", "00.000.000/0000-00");
	
        ResultActions resultActions = this.mockMvc
		.perform(get("/lojas/"+lojaID))
		.andExpect(status().isOk())
//		.andDo(this.documentationHandler.document(
//			responseFields(
//				subsectionWithPath("_embedded.lojaResources").description("An array of <<resources-note, Note resources>>"))))
		;
        
        /*
         * HATEOAS
         * 
         */
        
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Loja do Romulo"));
        
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        
        System.out.println(contentAsString);
	}
	
//	@Test
//	public void buscarLoja() throws Exception {
//			ResultActions resultActions = this.mockMvc
//				.perform(get("/lojas"))
//				.andExpect(status().isOk());
//		
//            ResultActions resultActions = this.mockMvc
//			.perform(MockMvcRequestBuilders.delete("")
//			.andExpect(status().isOk())
//			.andDo(this.documentationHandler.document(
//				responseFields(
//					subsectionWithPath("_embedded.lojaResources").description("An array of <<resources-note, Note resources>>"))));
//            
//            resultActions.andExpect(jsonPath("").value("Loja do Romulo"));
//            
//            MvcResult result = resultActions.andReturn();
//            String contentAsString = result.getResponse().getContentAsString();
//            
//            System.out.println(contentAsString);
//	}
//	
//	@Test
//	public void removerLoja() throws Exception {
//			ResultActions resultActions = this.mockMvc
//				.perform(get("/lojas"))
//				.andExpect(status().isOk());
//		
//            ResultActions resultActions = this.mockMvc
//			.perform(MockMvcRequestBuilders.delete("")
//			.andExpect(status().isOk())
//			.andDo(this.documentationHandler.document(
//				responseFields(
//					subsectionWithPath("_embedded.lojaResources").description("An array of <<resources-note, Note resources>>"))));
//            
//            resultActions.andExpect(jsonPath("").value("Loja do Romulo"));
//            
//            MvcResult result = resultActions.andReturn();
//            String contentAsString = result.getResponse().getContentAsString();
//            
//            System.out.println(contentAsString);
//	}
	
	@After
	public void after() throws Exception {
		
	}

//	@Test
//	public void notesCreateExample() throws Exception {
//		Map<String, String> tag = new HashMap<String, String>();
//		tag.put("name", "REST");
//
//		String tagLocation = this.mockMvc
//			.perform(post("/tags")
//				.contentType(MediaTypes.HAL_JSON)
//				.content(this.objectMapper.writeValueAsString(tag)))
//			.andExpect(status().isCreated())
//			.andReturn().getResponse().getHeader("Location");
//
//		Map<String, Object> note = new HashMap<String, Object>();
//		note.put("title", "REST maturity model");
//		note.put("body", "https://martinfowler.com/articles/richardsonMaturityModel.html");
//		note.put("tags", Arrays.asList(tagLocation));
//
//		ConstrainedFields fields = new ConstrainedFields(NoteInput.class);
//
//		this.mockMvc
//			.perform(post("/notes")
//				.contentType(MediaTypes.HAL_JSON)
//				.content(this.objectMapper.writeValueAsString(note)))
//			.andExpect(
//				status().isCreated())
//			.andDo(this.documentationHandler.document(
//				requestFields(
//					fields.withPath("title").description("The title of the note"),
//					fields.withPath("body").description("The body of the note"),
//					fields.withPath("tags").description("An array of tag resource URIs"))));
//	}

//	@Test
//	public void noteGetExample() throws Exception {
//		Map<String, String> tag = new HashMap<String, String>();
//		tag.put("name", "REST");
//
//		String tagLocation = this.mockMvc
//			.perform(post("/tags")
//				.contentType(MediaTypes.HAL_JSON)
//				.content(this.objectMapper.writeValueAsString(tag)))
//			.andExpect(status().isCreated())
//			.andReturn().getResponse().getHeader("Location");
//
//		Map<String, Object> note = new HashMap<String, Object>();
//		note.put("title", "REST maturity model");
//		note.put("body", "https://martinfowler.com/articles/richardsonMaturityModel.html");
//		note.put("tags", Arrays.asList(tagLocation));
//
//		String noteLocation = this.mockMvc
//			.perform(post("/notes")
//				.contentType(MediaTypes.HAL_JSON)
//				.content(this.objectMapper.writeValueAsString(note)))
//			.andExpect(status().isCreated())
//			.andReturn().getResponse().getHeader("Location");
//
//		this.mockMvc
//			.perform(get(noteLocation))
//			.andExpect(status().isOk())
//			.andExpect(jsonPath("title", is(note.get("title"))))
//			.andExpect(jsonPath("body", is(note.get("body"))))
//			.andExpect(jsonPath("_links.self.href", is(noteLocation)))
//			.andExpect(jsonPath("_links.note-tags", is(notNullValue())))
//			.andDo(this.documentationHandler.document(
//				links(
//					linkWithRel("self").description("This <<resources-note,note>>"),
//					linkWithRel("note-tags").description("This note's <<resources-note-tags,tags>>")),
//				responseFields(
//					fieldWithPath("title").description("The title of the note"),
//					fieldWithPath("body").description("The body of the note"),
//					subsectionWithPath("_links").description("<<resources-note-links,Links>> to other resources"))));
//
//	}

//	@Test
//	public void tagsListExample() throws Exception {
//		this.noteRepository.deleteAll();
//		this.tagRepository.deleteAll();
//
//		createTag("REST");
//		createTag("Hypermedia");
//		createTag("HTTP");
//
//		this.mockMvc
//			.perform(get("/tags"))
//			.andExpect(status().isOk())
//			.andDo(this.documentationHandler.document(
//				responseFields(
//					subsectionWithPath("_embedded.tags").description("An array of <<resources-tag,Tag resources>>"))));
//	}

//	@Test
//	public void tagsCreateExample() throws Exception {
//		Map<String, String> tag = new HashMap<String, String>();
//		tag.put("name", "REST");
//
//		ConstrainedFields fields = new ConstrainedFields(TagInput.class);
//
//		this.mockMvc
//			.perform(post("/tags")
//				.contentType(MediaTypes.HAL_JSON)
//				.content(this.objectMapper.writeValueAsString(tag)))
//			.andExpect(status().isCreated())
//			.andDo(this.documentationHandler.document(
//				requestFields(
//					fields.withPath("name").description("The name of the tag"))));
//	}

//	@Test
//	public void noteUpdateExample() throws Exception {
//		Map<String, Object> note = new HashMap<String, Object>();
//		note.put("title", "REST maturity model");
//		note.put("body", "https://martinfowler.com/articles/richardsonMaturityModel.html");
//
//		String noteLocation = this.mockMvc
//			.perform(post("/notes")
//				.contentType(MediaTypes.HAL_JSON)
//				.content(this.objectMapper.writeValueAsString(note)))
//			.andExpect(status().isCreated())
//			.andReturn().getResponse().getHeader("Location");
//
//		this.mockMvc
//			.perform(get(noteLocation))
//			.andExpect(status().isOk())
//			.andExpect(jsonPath("title", is(note.get("title"))))
//			.andExpect(jsonPath("body", is(note.get("body"))))
//			.andExpect(jsonPath("_links.self.href", is(noteLocation)))
//			.andExpect(jsonPath("_links.note-tags", is(notNullValue())));
//
//		Map<String, String> tag = new HashMap<String, String>();
//		tag.put("name", "REST");
//
//		String tagLocation = this.mockMvc
//			.perform(post("/tags")
//				.contentType(MediaTypes.HAL_JSON)
//				.content(this.objectMapper.writeValueAsString(tag)))
//			.andExpect(status().isCreated())
//			.andReturn().getResponse().getHeader("Location");
//
//		Map<String, Object> noteUpdate = new HashMap<String, Object>();
//		noteUpdate.put("tags", Arrays.asList(tagLocation));
//
//		ConstrainedFields fields = new ConstrainedFields(NotePatchInput.class);
//
//		this.mockMvc
//			.perform(patch(noteLocation)
//				.contentType(MediaTypes.HAL_JSON)
//				.content(this.objectMapper.writeValueAsString(noteUpdate)))
//			.andExpect(status().isNoContent())
//			.andDo(this.documentationHandler.document(
//				requestFields(
//					fields.withPath("title")
//						.description("The title of the note")
//						.type(JsonFieldType.STRING)
//						.optional(),
//					fields.withPath("body")
//						.description("The body of the note")
//						.type(JsonFieldType.STRING)
//						.optional(),
//					fields.withPath("tags")
//						.description("An array of tag resource URIs"))));
//	}

//	@Test
//	public void tagGetExample() throws Exception {
//		Map<String, String> tag = new HashMap<String, String>();
//		tag.put("name", "REST");
//
//		String tagLocation = this.mockMvc
//			.perform(post("/tags")
//				.contentType(MediaTypes.HAL_JSON)
//				.content(this.objectMapper.writeValueAsString(tag)))
//			.andExpect(status().isCreated())
//			.andReturn().getResponse().getHeader("Location");
//
//		this.mockMvc
//			.perform(get(tagLocation))
//			.andExpect(status().isOk())
//			.andExpect(jsonPath("name", is(tag.get("name"))))
//			.andDo(this.documentationHandler.document(
//				links(
//					linkWithRel("self").description("This <<resources-tag,tag>>"),
//					linkWithRel("tagged-notes").description("The <<resources-tagged-notes,notes>> that have this tag")),
//				responseFields(
//					fieldWithPath("name").description("The name of the tag"),
//					subsectionWithPath("_links").description("<<resources-tag-links,Links>> to other resources"))));
//	}

//	@Test
//	public void tagUpdateExample() throws Exception {
//		Map<String, String> tag = new HashMap<String, String>();
//		tag.put("name", "REST");
//
//		String tagLocation = this.mockMvc
//			.perform(post("/tags")
//				.contentType(MediaTypes.HAL_JSON)
//				.content(this.objectMapper.writeValueAsString(tag)))
//			.andExpect(status().isCreated())
//			.andReturn().getResponse().getHeader("Location");
//
//		Map<String, Object> tagUpdate = new HashMap<String, Object>();
//		tagUpdate.put("name", "RESTful");
//
//		ConstrainedFields fields = new ConstrainedFields(TagPatchInput.class);
//
//		this.mockMvc
//			.perform(patch(tagLocation)
//				.contentType(MediaTypes.HAL_JSON)
//				.content(this.objectMapper.writeValueAsString(tagUpdate)))
//			.andExpect(status().isNoContent())
//			.andDo(this.documentationHandler.document(
//				requestFields(
//					fields.withPath("name").description("The name of the tag"))));
//	}

	private Long createLoja(String nome, String cnpj) {
		Loja loja = new Loja();
		loja.setNome(nome);
		loja.setDataCriacao(Calendar.getInstance().getTime());
		loja.setCnpj(cnpj);

		Loja lojaPersisted = this.lojaService.saveOrUpdate(loja);
		if(lojaPersisted != null)
			return lojaPersisted.getId();
		return null;
	}

//	private static class ConstrainedFields {
//
//		private final ConstraintDescriptions constraintDescriptions;
//
//		ConstrainedFields(Class<?> input) {
//			this.constraintDescriptions = new ConstraintDescriptions(input);
//		}
//
//		private FieldDescriptor withPath(String path) {
//			return fieldWithPath(path).attributes(key("constraints").value(StringUtils
//					.collectionToDelimitedString(this.constraintDescriptions
//							.descriptionsForProperty(path), ". ")));
//		}
//	}

}
