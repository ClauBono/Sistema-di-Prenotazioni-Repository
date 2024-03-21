package StagistiLinks.Sistema.di.Prenotazioni.Config;
import StagistiLinks.Sistema.di.Prenotazioni.Entities.ClienteEntity;
import StagistiLinks.Sistema.di.Prenotazioni.Repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final ClienteRepository clienteRepository;

    @Autowired
    public SecurityConfig(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }


    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                ClienteEntity user = clienteRepository.findByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User non trovato con questo username username: " + username));

                return org.springframework.security.core.userdetails.User.builder()
                        .username(user.getUsername())
                        .password(passwordEncoder.encode(user.getPassword()))
                        .roles("USER")
                        .build();
            }
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain2(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .authorizeRequests()
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html/**").permitAll()
                .requestMatchers("/stato-prenotazione-controller/**", "/prenotazioni-controller/**", "/cliente-controller/**", "/prenotazioni-per-utente/**").permitAll()
                .requestMatchers("/Stato Prenotazioni/**", "/Prenotazioni/**", "/Clienti/**").permitAll()
                .anyRequest().authenticated();

        httpSecurity
                .formLogin();
                return httpSecurity.build();

    }


}





/*
        */

