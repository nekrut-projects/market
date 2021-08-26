package gb.market.services;

import gb.market.model.Authority;
import gb.market.model.Role;
import gb.market.model.User;
import gb.market.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    public Optional<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = findByName(name).orElseThrow(()->new UsernameNotFoundException("User " + name + " not found at DB"));
        return new org.springframework.security.core.userdetails.User(user.getName(),
                                                                      user.getPassword(),
                                                                      getGrantedAuthority(getPrivileges(user.getRoles())));
    }

    public List<String> getPrivileges(Collection<Role> roles) {
        List<String> privileges = new LinkedList<>();
        List<Authority> authorities = new LinkedList<>();
        for (Role r : roles) {
            privileges.add(r.getTitle());
            authorities.addAll(r.getAuthorities());
        }
        for (Authority a : authorities) {
            privileges.add(a.getTitle());
        }
        return privileges;
    }

    public Collection<? extends GrantedAuthority> getGrantedAuthority(Collection<String> privileges) {
        return privileges.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
}
