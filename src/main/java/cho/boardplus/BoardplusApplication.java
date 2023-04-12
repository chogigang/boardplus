package cho.boardplus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class BoardplusApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoardplusApplication.class, args);

	}

}

