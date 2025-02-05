package com.sparta.myselectshop;

import com.sparta.myselectshop.user.adapter.in.security.JwtAuthenticationFilter;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components()
                        // Access Token (Header)
                        .addSecuritySchemes("accessToken",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .in(SecurityScheme.In.HEADER)
                                        .name("Authorization") // Access Token은 Authorization 헤더에 담김
                        )
                        // Refresh Token (Cookie)
                        .addSecuritySchemes("refreshToken",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.APIKEY) // Cookie 기반 인증
                                        .in(SecurityScheme.In.COOKIE)
                                        .name(JwtAuthenticationFilter.REFRESH_TOKEN_COOKIE_NAME) // Refresh Token은 Cookie에 저장
                        )
                )
                .addSecurityItem(new SecurityRequirement()
                        .addList("accessToken") // Access Token 인증
                        .addList("refreshToken") // Refresh Token 인증
                );
    }
}
