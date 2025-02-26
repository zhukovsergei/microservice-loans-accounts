package ch.fdsgn.accounts;

import ch.fdsgn.accounts.dto.MainInfoDto;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Accounts REST API",
				description = "Microservice accounts REST API documentation",
				version = "v1",
				contact = @Contact(
						name = "Me",
						email = "some@email.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "some url"
				)
		)
)
@EnableConfigurationProperties(MainInfoDto.class)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
