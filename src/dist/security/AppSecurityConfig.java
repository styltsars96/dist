package dist.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource dataSource;
	
	//Authentication method.
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	
		 auth.jdbcAuthentication().dataSource(dataSource)
	        .passwordEncoder(passwordEncoder())
		   .usersByUsernameQuery(
		    "select fullname as username,password, enabled from internal_user where fullname=?")
		   .authoritiesByUsernameQuery(
		    "select fullname, shop_role as authority from internal_user where fullname=?");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 CharacterEncodingFilter filter = new CharacterEncodingFilter();
		 filter.setEncoding("UTF-8");
		 filter.setForceEncoding(true);
		 http.addFilterBefore(filter,CsrfFilter.class); 
		 
		http.authorizeRequests()
		.anyRequest().authenticated()
		.and()
		.formLogin().loginPage("/")//login
		.loginProcessingUrl("/authUser")
		.defaultSuccessUrl("/login")//when succesful, go to the controller
		.permitAll()
		.and()
		.logout().permitAll()
		.and()
	    .exceptionHandling().accessDeniedPage("/403");
		  
	}

	//Configure exceptions.
	@Override
	public void configure(WebSecurity web) throws Exception {
		 web.ignoring()
	        .antMatchers("/resources/**");

		 web.ignoring()
		 	.antMatchers("/api/**");
		 
		 
		 	

	}	
	
	//Configure encryption
	@Bean
	public PasswordEncoder passwordEncoder(){
	    PasswordEncoder encoder = new BCryptPasswordEncoder();
	    return encoder;
	}
}
