package senac.edu.engsoft.meuproduto.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import senac.edu.engsoft.meuproduto.model.UsuarioType;

import java.util.Collection;

@Getter
@Setter
public class UserDetailsCustom extends User {

    private String nome;
    private UsuarioType usuarioType;
    private boolean emailConfirmado;
    private boolean habilitado;

    public UserDetailsCustom(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public UserDetailsCustom(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }
}
