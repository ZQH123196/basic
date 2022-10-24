package springsecuritydemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.servlet.handler.MatchableHandlerMapping;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;

import javax.servlet.http.HttpServlet;

@SpringBootApplication
public class Application {


    public static void main(String[] args) {
        SpringApplication app = new MyApp(Application.class);

        ConfigurableApplicationContext context = app.run(args);
    }

}


class MyApp extends SpringApplication {
    public MyApp(Class<Application> applicationClass) {
        super(applicationClass);
    }

    @Override
    protected void afterRefresh(ConfigurableApplicationContext context, ApplicationArguments args) {
        super.afterRefresh(context, args);
    }
}