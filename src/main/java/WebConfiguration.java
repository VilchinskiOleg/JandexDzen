import by.tms.interceptor.HomeInterсeptor;
import by.tms.interceptor.PostInterceptor;
import by.tms.interceptor.PostLikeInterceptor;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import javax.sql.DataSource;
import java.util.*;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = "by.tms")
public class WebConfiguration implements WebMvcConfigurer {

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public SpringResourceTemplateResolver templateResolver(){
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(this.applicationContext);
        templateResolver.setPrefix("/pages/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCacheable(true);
        return templateResolver;
    }
    @Bean
    public SpringTemplateEngine templateEngine(){
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }
    @Bean
    public ThymeleafViewResolver viewResolver(){
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        return viewResolver;
    }



    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
        driverManagerDataSource.setUsername("postgres");
        driverManagerDataSource.setPassword("postgres");
        driverManagerDataSource.setUrl("jdbc:postgresql://localhost:5432/test");
        return driverManagerDataSource;
    }
    @Bean
    public LocalContainerEntityManagerFactoryBean containerForEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean containerForEntityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        containerForEntityManagerFactory.setDataSource(dataSource());
        containerForEntityManagerFactory.setPackagesToScan("by.tms.entity");
        containerForEntityManagerFactory.setPersistenceProvider(new HibernatePersistenceProvider());
        containerForEntityManagerFactory.setJpaProperties(jpaProperties());
        return containerForEntityManagerFactory;
    }
    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(containerForEntityManagerFactory().getObject());
        return jpaTransactionManager;
    }
    @Bean
    public Properties jpaProperties() {
        Properties jpaProperties = new Properties();
        jpaProperties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        jpaProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL10Dialect");
        jpaProperties.setProperty("hibernate.show_sql", "true");
        return jpaProperties;
    }



    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new PostInterceptor())
                .addPathPatterns("/post/create");
        registry.addInterceptor(new HomeInterсeptor())
                .addPathPatterns("/home/reg")
                .addPathPatterns("/home/auth");
        registry.addInterceptor(new PostLikeInterceptor())
                .addPathPatterns("/post/set-like")
                .addPathPatterns("/post/like");
    }
}
