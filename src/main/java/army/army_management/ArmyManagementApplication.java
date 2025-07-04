package army.army_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class ArmyManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArmyManagementApplication.class, args);
	}

}
