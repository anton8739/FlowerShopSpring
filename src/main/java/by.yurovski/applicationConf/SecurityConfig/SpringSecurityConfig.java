package by.yurovski.applicationConf.SecurityConfig;

import by.yurovski.filter.EncodingFilter;
import by.yurovski.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@Configuration
@ComponentScan
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/app/main").permitAll()
                .antMatchers("/app/forgot").permitAll()
                .antMatchers("/app/forgot/change").permitAll()
                .antMatchers("/app/category/*").permitAll()
                .antMatchers("/app/product/*").permitAll()
                .antMatchers("/files/**").permitAll()
                .antMatchers("/app/contacts").permitAll()
                .antMatchers("/app/basket").permitAll()
                .antMatchers("/app/basket/add").permitAll()
                .antMatchers("/app/basket/delete").permitAll()
                .antMatchers("/app/basket/edit").permitAll()
                .antMatchers("/app/basket/order/confirm").permitAll()
                .antMatchers("/app/basket/order").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/img/**").permitAll()
                .antMatchers("/fonts/**").permitAll()
                .antMatchers("/app/login").permitAll()
                .antMatchers("/app/registration").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/app/login")
                .loginProcessingUrl("/app/login")
                .defaultSuccessUrl("/app/main",true)
                .and()
                .logout()
                .logoutUrl("/app/logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/app/login");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    protected DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(customPasswordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        return daoAuthenticationProvider;
    }
    @Bean
    protected PasswordEncoder customPasswordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    protected UserDetailsService userDetailsService(){
        return new UserDetailsServiceImpl();
    }
    @Bean
    public ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
        return new ServletListenerRegistrationBean<HttpSessionEventPublisher>(new HttpSessionEventPublisher());
    }
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
