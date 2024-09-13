package expense.manager.wallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableFeignClients
@EnableJpaAuditing
@ComponentScan(basePackages = {"expense.manager.common", "expense.manager.wallet"})
public class McroWalletServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(McroWalletServiceApplication.class, args);
	}

}
