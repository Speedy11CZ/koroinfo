package cz.speedy.koroinfo.security;

import cz.speedy.koroinfo.data.entities.User;
import cz.speedy.koroinfo.data.repositories.UserRepository;
import cz.speedy.koroinfo.enums.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RichUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public RichUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void createAdminAccount(PasswordEncoder passwordEncoder) {
        if(userRepository.count() < 1) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("admin@example.com");
            admin.setName("Name");
            admin.setSurname("Surname");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setRole(Role.ADMIN);
            admin.setShadow(false);
            userRepository.save(admin);
            log.info("Created new administrator account {}", admin.toString());
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user != null) {
            if(!user.isShadow() && user.getEmail() != null && user.getPassword() != null && user.getRole() != null) {
                return new RichUserPrincipal(user);
            }
        }
        throw new UsernameNotFoundException(username);
    }
}
