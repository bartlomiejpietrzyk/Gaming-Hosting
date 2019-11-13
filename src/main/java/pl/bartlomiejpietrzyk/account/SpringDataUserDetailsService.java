package pl.bartlomiejpietrzyk.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.bartlomiejpietrzyk.entity.User;

import java.util.HashSet;
import java.util.Set;

public class SpringDataUserDetailsService implements UserDetailsService {
    private IUserRegistrationService IUserRegistrationService;

    @Autowired
    public void setUserRepository(IUserRegistrationService IUserRegistrationService) {
        this.IUserRegistrationService = IUserRegistrationService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = IUserRegistrationService.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(email);
        }
        if (!user.getEnabled()) {
            throw new DisabledException("Account not active!");
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        user.getRoles().forEach(r ->
                grantedAuthorities.add(new SimpleGrantedAuthority(r.getName())));
        return new CurrentUser(user.getEmail(), user.getPassword(),
                grantedAuthorities, user, user.getEnabled());
    }
}