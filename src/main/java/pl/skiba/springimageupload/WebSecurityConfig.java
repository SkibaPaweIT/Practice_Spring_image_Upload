package pl.skiba.springimageupload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.skiba.springimageupload.model.AppUser;
import pl.skiba.springimageupload.repo.AppUserRepo;


@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    private final UserDetailsServiceImpl userDetailsService;
    private final AppUserRepo appUserRepo;

    //alt+insert here
    @Autowired
    public WebSecurityConfig(UserDetailsServiceImpl userDetailsService , AppUserRepo appUserRepo) {
        this.userDetailsService = userDetailsService;
        this.appUserRepo = appUserRepo;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser(
//                new User("Jan", passwordEncoder().encode("jan123") , Collections.singleton(new SimpleGrantedAuthority("user"))));
            auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/test1").hasRole("USER")
                .antMatchers("/test2").hasRole("ADMIN")
                .antMatchers("/upload").hasRole("ADMIN")
                .antMatchers("/gallery").hasRole("USER")
                .and()
                .formLogin().permitAll()
                .and().csrf().disable();
    }


    //Szyfrowanie haseł
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDB() {
        AppUser appUser = new AppUser("jan" , passwordEncoder().encode("nowak")  , "ROLE_USER");
        AppUser appUserAdmin = new AppUser("admin" , passwordEncoder().encode("admin")  , "ROLE_ADMIN");
        appUserRepo.save(appUser);
        appUserRepo.save(appUserAdmin);

    }

}
