package com.eor.proto;

import com.eor.proto.vo.Tags;
import com.eor.proto.vo.User;
import com.sun.tools.javac.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.text.html.HTML;

@Slf4j
@SpringBootApplication
public class App implements ApplicationRunner {


    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        User user = User.newBuilder()
                .setId(1)
                .addTags(Tags.newBuilder()
                        .addTag("s")
                        .addTag(1)
                        .build())
                .build();
    }
}