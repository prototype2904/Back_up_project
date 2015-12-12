package ru.backup.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import ru.backup.domain.user.Role;


/**
 * Spring Security 
 * 
 * класс для установления параметров безопасности сервера
 * 
 * @author Roman
 *
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	/**
	 * настройка прав доступа на URL
	 */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        	.antMatchers("/hello")
        	.hasAuthority(Role.USER.getName())
        	.antMatchers("/rest/tasks/all/")
        	.hasAnyAuthority(Role.USER.getName(), Role.ADMIN.getName())
        	.antMatchers("/rest/tasks/{id}")
        	.hasAuthority(Role.ADMIN.getName())
        	.antMatchers("/rest/files/upload/")
        	.hasAuthority(Role.USER.getName())
        	.antMatchers("/admin/*")
        	.hasAuthority(Role.ADMIN.getName())
        		.and()
        		.httpBasic().and()
            .formLogin()
            .loginPage("/login")
            .failureUrl("/login?error")
            .usernameParameter("username")
            .passwordParameter("password")
            .permitAll()
            	.and()
            .logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/")
            	.permitAll()
            	.and()
        	.exceptionHandling().accessDeniedPage("/403")
            	.and()
            .csrf().disable()
            	;
    }

}
