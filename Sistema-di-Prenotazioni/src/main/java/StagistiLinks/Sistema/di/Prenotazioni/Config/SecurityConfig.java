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
                        .build();
            }
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }



    //Aggiunge api alle esclusioni, queste funzioneranno sempre, incluso swagger
    @Bean
    public SecurityFilterChain securityFilterChain2(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .authorizeRequests()
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html/**").permitAll()
                .requestMatchers("/stato-prenotazione-controller/**", "/prenotazioni-controller/**", "/cliente-controller/**", "/prenotazioni-per-utente-controller/**", "/cliente-per-utente-controller/**").permitAll()
                .requestMatchers("/Stato Prenotazioni/**", "/Prenotazioni/**", "/Clienti/**").permitAll()
                .requestMatchers("/Le mie Prenotazioni/**", "/I miei Clienti/**").permitAll()
                .requestMatchers("/Login", "/Registrazione").permitAll()
                .anyRequest().authenticated();

        httpSecurity
                .formLogin()
                .loginPage("/Login");
                return httpSecurity.build();

    }


    //Mi permette di tornare alla pagina scelta da me dopo il logout
    @Primary
    @Bean
    protected HttpSecurity configure(HttpSecurity http) throws Exception {
        http
                // altre configurazioni...
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/Login") // URL di reindirizzamento dopo il logout
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");

        return http;
    }


}

