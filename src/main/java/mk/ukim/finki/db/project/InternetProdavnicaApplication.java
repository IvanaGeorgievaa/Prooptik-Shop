package mk.ukim.finki.db.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//
//@EntityScan(
//        basePackageClasses = { InternetProdavnicaApplication.class, Jsr310JpaConverters.class }
//)
@SpringBootApplication
public class InternetProdavnicaApplication {

    public static void main(String[] args) {
        SpringApplication.run(InternetProdavnicaApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }
}
