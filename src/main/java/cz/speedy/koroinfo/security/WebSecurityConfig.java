package cz.speedy.koroinfo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final RichUserDetailsService userDetailsService;

    public WebSecurityConfig(RichUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return userDetailsService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(new GenericFilter() {
            @Override
            public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
                if(SecurityContextHolder.getContext().getAuthentication() != null) {
                    if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
                        if(((HttpServletRequest) request).getRequestURI().equals("/admin/login"))  {
                            ((HttpServletResponse) response).sendRedirect("/admin/panel");
                        }
                    }
                }
                chain.doFilter(request, response);
            }
        }, UsernamePasswordAuthenticationFilter.class);
        http.authorizeRequests()
                .antMatchers("/resources/**")
                .permitAll()
                .and()
                .formLogin()
                .loginPage("/admin/login")
                .loginProcessingUrl("/admin/perform_login")
                .defaultSuccessUrl("/admin/panel",true)
                .failureUrl("/admin/login?error=true")
                .and()
                .logout().permitAll().logoutSuccessUrl("/admin/login")
                .and()
                .csrf().disable();
    }

}