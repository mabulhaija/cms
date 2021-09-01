package com.pwc.assignment.authentication;

import com.pwc.assignment.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtAuthenticationEntryPoint authenticationEntryPoint;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(encoder());
    }

    @Bean
    public JwtAuthenticationFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().
                authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/actuator/**").permitAll()

                .antMatchers(HttpMethod.GET, "/departments/**").hasAnyAuthority(Role.EMPLOYEE.name())
                .antMatchers(HttpMethod.POST, "/departments/**").hasAnyAuthority(Role.MANAGER.name())
                .antMatchers(HttpMethod.PUT, "/departments/**").hasAnyAuthority(Role.MANAGER.name())
                .antMatchers(HttpMethod.DELETE, "/departments/**").hasAnyAuthority(Role.MANAGER.name())


                .antMatchers(HttpMethod.GET, "/users/**").hasAnyAuthority(Role.EMPLOYEE.name())
                .antMatchers(HttpMethod.POST, "/users/**").hasAnyAuthority(Role.MANAGER.name())
                .antMatchers(HttpMethod.PUT, "/users/**").hasAnyAuthority(Role.MANAGER.name())
                .antMatchers(HttpMethod.DELETE, "/users/**").hasAnyAuthority(Role.MANAGER.name())

                .antMatchers(HttpMethod.GET, "/projects/**").hasAnyAuthority(Role.EMPLOYEE.name())
                .antMatchers(HttpMethod.POST, "/projects/**").hasAnyAuthority(Role.MANAGER.name())
                .antMatchers(HttpMethod.PUT, "/projects/**").hasAnyAuthority(Role.MANAGER.name())
                .antMatchers(HttpMethod.DELETE, "/projects/**").hasAnyAuthority(Role.MANAGER.name())

                .antMatchers(HttpMethod.GET, "/project-users/**").hasAnyAuthority(Role.EMPLOYEE.name())
                .antMatchers(HttpMethod.POST, "/project-users/**").hasAnyAuthority(Role.MANAGER.name())
                .antMatchers(HttpMethod.DELETE, "/project-users/**").hasAnyAuthority(Role.MANAGER.name())


                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
