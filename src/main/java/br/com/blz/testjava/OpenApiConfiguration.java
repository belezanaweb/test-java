package br.com.blz.testjava;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.responses.ApiResponse;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI cacheSyncOpenAPI() {
        return new OpenAPI()
            .components(new Components()
                .addResponses("error-400", getCustomApiResponse("Bad Request"))
                .addResponses("error-401", getCustomApiResponse("Unauthorized"))
                .addResponses("error-403", getCustomApiResponse("Forbidden"))
                .addResponses("error-404", getCustomApiResponse("Not Found"))
                .addResponses("error-409", getCustomApiResponse("Resource Already Exists"))
                .addResponses("error-500", getCustomApiResponse("Server Error"))
            )
            .info(new Info()
                .title("Beleza na Web REST API")
                .description("Beleza na Web REST API"));
    }

    private ApiResponse getCustomApiResponse(final String description) {
        return new ApiResponse()
            .description(description)
            .content(new Content()
                .addMediaType(APPLICATION_JSON_VALUE, new MediaType())
            );
    }

    @Bean
    public GroupedOpenApi restApi() {
        return GroupedOpenApi.builder()
            .group("rest-api")
            .pathsToMatch("/api/**")
            .build();
    }
}
