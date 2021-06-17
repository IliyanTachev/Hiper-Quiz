package hiperquiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class HiperQuizSpringApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(HiperQuizSpringApplication.class, args);
    }
}
