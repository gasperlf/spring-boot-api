package co.com.javadevelopercolombia.api.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.http.ResponseEntity;
import org.springframework.plugin.core.SimplePluginRegistry;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private final Optional<BuildProperties> buildProperties;
    private final Optional<GitProperties> gitProperties;
    private String version;

    public SwaggerConfig(Optional<BuildProperties> buildProperties, Optional<GitProperties> gitProperties) {
        this.buildProperties = buildProperties;
        this.gitProperties = gitProperties;
    }

    @PostConstruct
    public void version() {
        this.version = "default";
        if (buildProperties.isPresent() && gitProperties.isPresent()) {
            BuildProperties buildInfo = buildProperties.get();
            GitProperties gitInfo = gitProperties.get();

            log.info("version={}", buildInfo.getVersion());
            log.info("shortCommitId={}", gitInfo.getShortCommitId());
            log.info("branch={}", gitInfo.getBranch());

            this.version = String.format("%s-%s-%s",buildInfo.getVersion(),gitInfo.getShortCommitId(),gitInfo.getBranch());
        }
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Spring Boot API")
                .apiInfo(apiInfo(this.version))
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .directModelSubstitute(LocalDate.class, String.class)
                .directModelSubstitute(LocalDateTime.class, String.class)
                .genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(false)
                .enableUrlTemplating(false);
    }

    @Bean
    public UiConfiguration beanUiConfiguration() {
        return UiConfigurationBuilder.builder()
                .deepLinking(true)
                .displayOperationId(false)
                .defaultModelsExpandDepth(1)
                .defaultModelExpandDepth(1)
                .defaultModelRendering(ModelRendering.EXAMPLE)
                .displayRequestDuration(false)
                .docExpansion(DocExpansion.NONE)
                .filter(false)
                .maxDisplayedTags(null)
                .operationsSorter(OperationsSorter.ALPHA)
                .showExtensions(false)
                .tagsSorter(TagsSorter.ALPHA)
                .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
                .validatorUrl(null)
                .build();
    }

    private ApiInfo apiInfo(String version) {
        return new ApiInfoBuilder()
                .title("Spring Boot Api")
                .description("Api")
                .version(version)
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
                .contact(new Contact("Lewis", "https://github.com/gasperlf", "gasper_lf@hotmail.com "))
                .build();
    }

    @Bean
    public LinkDiscoverers discovers() {
        List<LinkDiscoverer> plugins = new ArrayList<>();
        plugins.add(new CollectionJsonLinkDiscoverer());
        return new LinkDiscoverers(SimplePluginRegistry.create(plugins));

    }

}
