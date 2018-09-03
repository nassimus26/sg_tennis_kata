package sg.kata;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = {
                                    DataSourceAutoConfiguration.class,
                                    HibernateJpaAutoConfiguration.class})
public class TennisGameApp {
    public static void main(String[] args) {
        SpringApplication.run(TennisGameApp.class, args);
    }
    @Bean
    public ServletWebServerFactory servletContainer() {
        return new TomcatServletWebServerFactory();
    }
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
