package cz.tut.crm.personmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PersonManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonManagerApplication.class, args);
    }

}
