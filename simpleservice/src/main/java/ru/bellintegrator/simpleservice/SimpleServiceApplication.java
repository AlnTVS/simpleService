package ru.bellintegrator.simpleservice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//@EnableSwagger2
//@ImportResource("spring_mvc_config.xml")
@SpringBootApplication
public class SimpleServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleServiceApplication.class, args);
    }

//    @Bean
//    public LinkDiscoverers discoverers() {
//        List<LinkDiscoverer> plugins = new ArrayList<>();
//        plugins.add(new CollectionJsonLinkDiscoverer());
//        return new LinkDiscoverers(SimplePluginRegistry.create(plugins));
//
//    }
//
//	@Bean
//	public Docket postApi() {
//		return new Docket(DocumentationType.SWAGGER_2)
//				.groupName("organizations")
//				.apiInfo(apiInfo())
//				.select()
//				.paths(regex("/person.*"))
//				.build();
//	}
//
//	private ApiInfo apiInfo() {
//		return new ApiInfoBuilder()
//				.title("Spring REST Sample with Swagger")
//				.description("Spring REST Sample with Swagger")
//				.contact("https://github.com/")
//				.version("1.0")
//				.build();
//	}
}
