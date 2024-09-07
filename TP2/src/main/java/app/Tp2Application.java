package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Se deben agregar estas anotaciones para que la app reconozca mis packages
 */
@SpringBootApplication()
@EnableJpaRepositories(basePackages = "repositories")
@EntityScan(basePackages = "entities")
@ComponentScan(basePackages={"controllers","services"})

public class Tp2Application {

	public static void main(String[] args) {
		SpringApplication.run(Tp2Application.class, args);
	}
 
}
