package senac.edu.engsoft.meuproduto.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class JwtRequest implements Serializable {
	
	private static final long serialVersionUID = 5926468583005150707L;
	@NotNull
	@Size(min = 3, max = 50, message = "Username deve ser um e-mail válido")
	private String username;

	@NotNull
	private String password;

//need default constructor for JSON Parsing
	public JwtRequest() {
	}

	public JwtRequest(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}