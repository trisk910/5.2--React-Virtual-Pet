package cat.itacademy.s05.t02.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI defineOpenApi() {
        Server server = new Server();
        server.setUrl("http://localhost:8080");
        server.setDescription("Robofighters API");
        Info information = new Info()
                .title("Robofighters API")
                .version("1.0")
                .description("This API exposes endpoints to manage a Virtual Pet game.");
        return new OpenAPI().info(information).servers(List.of(server))
                .addSecurityItem(new io.swagger.v3.oas.models.security.SecurityRequirement()
                        .addList("bearerAuth"))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("bearerAuth",
                                new io.swagger.v3.oas.models.security.SecurityScheme()
                                        .name("bearerAuth")
                                        .type(io.swagger.v3.oas.models.security.SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));
    }
}
