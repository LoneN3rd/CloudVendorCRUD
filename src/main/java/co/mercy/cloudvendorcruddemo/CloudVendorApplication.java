package co.mercy.cloudvendorcruddemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@SpringBootApplication
public class CloudVendorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudVendorApplication.class, args);
	}

	// swagger documentation
	@Bean
	public Docket swaggerConfiguration(){
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.ant("/cloudvendor/v1/*"))
				.apis(RequestHandlerSelectors.basePackage("co.mercy.cloudvendorcruddemo"))
				.build()
				.apiInfo(apiCustomData());
	}

	private ApiInfo apiCustomData(){
		return new ApiInfo(
				"Cloud Vendor API",
				"Cloud Vendor Documentation",
				"1.0",
				"Cloud Vendor Service Terms",
				new Contact("Mercy Mutuku", "https://github.com/lonen3rd", "contact@lonen3rd.com"),
				"Cloud Vendor API License",
				"https://github.com/lonen3rd",
				Collections.emptyList()
		);
	}
}