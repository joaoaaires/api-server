package br.com.jaa.server.core.security;

import br.com.jaa.server.core.util.ConvertUtil;
import br.com.jaa.server.features.usuario.entities.Usuario;
import br.com.jaa.server.features.usuario.repositories.UsuarioCrudRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SecurityUserDetailsService implements UserDetailsService {

    private UsuarioCrudRepository usuarioCrudRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Long idLong = ConvertUtil.toLong(id);
        Optional<Usuario> optional = usuarioCrudRepository.findById(idLong);
        if (optional.isEmpty()) {
            throw new UsernameNotFoundException("Usuario não encontrado");
        }
        return new SecurityUserDetails(optional.get());
    }
}
