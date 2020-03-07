package senac.edu.engsoft.meuproduto.controller;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(value="/")
public class IndexController {
	
	@SuppressWarnings("rawtypes")
	@GetMapping
	public RepresentationModel index() {
		RepresentationModel index = new RepresentationModel();
		index.add(WebMvcLinkBuilder.linkTo(LojaController.class).withRel("lojas"));
		//TODO:
//		index.add(WebMvcLinkBuilder.linkTo(LojaController.class).withRel("lojistas"));
//		index.add(WebMvcLinkBuilder.linkTo(LojaController.class).withRel("produtos"));
		return index;
	}

}
