package example.oenPkg;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;

/**
 * @author ZQH12
 */
@SpringBootApplication
@Slf4j
public class Application implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        ConfigurableApplicationContext context = app.run(args);
    }

    @Value("${moduleOne}")
    String moduleOne;

//    @Value("${moduleTwo}")
//    String moduleTwo;

//    @Value("${a}")
//    String nameA;
//
//    @Value("${B}")
//    String nameB;

    @Override
    public void run(ApplicationArguments args) throws Exception {
      log.info(moduleOne);
//      log.info(moduleTwo);

//        log.info();
    }
}
