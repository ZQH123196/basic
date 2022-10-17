package com.example.ConfigEncryptedByJasypt;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEncryptableProperties
public class ConfigEncryptedByJasyptApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(ConfigEncryptedByJasyptApplication.class, args);
	}



	@Override
	public void run(ApplicationArguments args) throws Exception {
		
	}
}
