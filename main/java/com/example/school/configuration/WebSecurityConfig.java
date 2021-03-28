package com.example.school.configuration;
import com.example.school.authentication.MyUserDetailsService;
import com.example.school.utilities.PasswordManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Order(SecurityProperties.BASIC_AUTH_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder(11));
		provider.setAuthoritiesMapper(authoritiesMapper());
		return provider;
	}
	
	@Bean
	public GrantedAuthoritiesMapper authoritiesMapper() {
		SimpleAuthorityMapper authorityMapper = new SimpleAuthorityMapper();
		authorityMapper.setConvertToUpperCase(true);
		authorityMapper.setDefaultAuthority("STUDENT");
		return authorityMapper;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		System.out.println("Hello from this config");
		auth.authenticationProvider(authenticationProvider());
	}

    @Override
    protected void configure (HttpSecurity http) throws Exception{
    	System.out.println("configure motherfuckers");
    	http
		.authorizeRequests().antMatchers("/register").permitAll().and().authorizeRequests()
		.antMatchers("/courseCreate/**").hasRole("TEACHER")
		.antMatchers("/teacher/**").hasRole("TEACHER")
		.antMatchers("/grade/**").hasRole("TEACHER")
		.antMatchers("/css/**").permitAll()
    	.anyRequest().authenticated()
    	.and()
    	.formLogin().loginPage("/login")
		.defaultSuccessUrl("/profile").permitAll()
		.and()
		.logout()
		.logoutUrl("/profile/logout")
		.logoutSuccessUrl("/login")
		.invalidateHttpSession(true)
		.and();
		

    	
    	
    	/*http.csrf().disable().authorizeRequests()
          .antMatchers("/admin/**").hasRole("ADMIN")
		  .anyRequest().permitAll().and().formLogin().loginProcessingUrl("/login")
		  .usernameParameter("email").passwordParameter("password")
		  .defaultSuccessUrl("/profile").failureUrl("/login")
		  .and().logout().and().exceptionHandling()
		  .accessDeniedPage("/error/403");*/
 
	
//	    	 http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN")
//             .anyRequest().permitAll().and().formLogin().loginPage("/login")
//             .usernameParameter("email").passwordParameter("password")
//             //.defaultSuccessUrl("/profile").failureUrl("/login")
//             .and().logout()
//             .logoutSuccessUrl("/")  .and().exceptionHandling()
//             .accessDeniedPage("/error/403").and().csrf();

		  }
	    
	    

}


/*@Autowired
private UserDetailsService userDetailsService;

@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(authProvider());
}

 
@Bean
public DaoAuthenticationProvider authProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService);
    authProvider.setPasswordEncoder();
    return authProvider;
}*/