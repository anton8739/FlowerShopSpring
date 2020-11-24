package by.yurovski.applicationConf.springConfig;


import by.yurovski.applicationConf.SecurityConfig.SpringSecurityConfig;
import by.yurovski.applicationConf.hibernateConfig.HibernateConfig;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;


import org.springframework.security.web.session.HttpSessionEventPublisher;

import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import java.util.Locale;


@Configuration
@ComponentScan("by.yurovski")
@PropertySource("classpath:config.properties")

@EnableWebMvc
@Import({ HibernateConfig.class, SpringSecurityConfig.class})
public class SpringConfig implements WebMvcConfigurer {

    private final ApplicationContext applicationContext;

    @Autowired
    public SpringConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;

    }


    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/views/");
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setCacheable(false);


        return templateResolver;
    }
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**")
                .addResourceLocations("classpath:/css/");
    }
    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.addDialect(springSecurityDialect());
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }


    public void configureViewResolvers(ViewResolverRegistry registry) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();

        resolver.setTemplateEngine(templateEngine());
        resolver.setCharacterEncoding("UTF-8");
        registry.viewResolver(resolver);
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();

        messageSource.setBasename("classpath:pageContent");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(Locale.US);
        return sessionLocaleResolver;
    }
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("language");
        return localeChangeInterceptor;
    }
    @Bean
    public SpringSecurityDialect springSecurityDialect(){
        return new SpringSecurityDialect();
    }

    @Bean
    public MultipartResolver multipartResolver()
    {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
         resolver.setDefaultEncoding("UTF-8");
         resolver.setResolveLazily(true);// The resolveLazily property is enabled to defer file parsing to catch file size exceptions in UploadAction
         resolver.setMaxInMemorySize(40960);
        resolver.setMaxUploadSize(10 * 1024 * 1024);// Upload file size 5M 5*1024*1024
        return resolver;
    }



    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

}
