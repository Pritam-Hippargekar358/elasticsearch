package tech.elastic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
public class ElasticsearchApplication {

	public static void main(String[] args) {

//		ConfigurableApplicationContext context =
		SpringApplication.run(ElasticsearchApplication.class, args);
//		ConfigurableEnvironment environment = context.getEnvironment();
//		if (environment.getActiveProfiles().length == 0) {
//			//Profile is missing
//			environment.addActiveProfile("dev");
//		}
	}

}
