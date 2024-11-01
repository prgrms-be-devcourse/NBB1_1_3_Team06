package com.nbe2.security.config

import com.nbe2.security.constants.SecurityUrlEndPoint
import com.nbe2.security.utils.JwtProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@EnableWebSecurity
@Configuration
class SecurityConfig(
        private val jwtProvider: JwtProvider,
        private val jwtAuthenticationEntryPoint: JwtAuthenticationEntryPoint,
        private val customAccessDeniedHandler: CustomAccessDeniedHandler,
) {
    @Bean
    @Throws(Exception::class)
    fun filterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        httpSecurity // CSRF 비활성화: JWT를 사용할 경우 CSRF 공격을 방지할 필요가 없음
                .csrf { csrf -> csrf.disable() } // httpBasic 비활성화
                .httpBasic { httpbasic ->
                    httpbasic.disable()
                } // 세션 관리 설정: Stateless
                .sessionManagement { sessionManagement ->
                    sessionManagement.sessionCreationPolicy(
                            SessionCreationPolicy.STATELESS
                    )
                } // 필터 추가
                .addFilterBefore(
                        CustomSecurityFilter(jwtProvider),
                        UsernamePasswordAuthenticationFilter::class.java,
                ) // 접근 제어 설정
                .authorizeHttpRequests {
                        authorizationManagerRequestMatcherRegistry ->
                    // All
                    for (securityUrlEndPoint in SecurityUrlEndPoint.entries) {
                        if (securityUrlEndPoint.userRole == null) {
                            authorizationManagerRequestMatcherRegistry
                                    .requestMatchers(
                                            securityUrlEndPoint.method,
                                            securityUrlEndPoint.url,
                                    )
                                    .permitAll()
                        }
                    }
                    for (securityUrlEndPoint in SecurityUrlEndPoint.entries) {
                        if (securityUrlEndPoint.userRole != null) {
                            authorizationManagerRequestMatcherRegistry
                                    .requestMatchers(
                                            securityUrlEndPoint.method,
                                            securityUrlEndPoint.url,
                                    )
                                    .hasRole(securityUrlEndPoint.userRole.name)
                        }
                    }
                    authorizationManagerRequestMatcherRegistry
                            .anyRequest()
                            .authenticated()
                }
        httpSecurity.exceptionHandling { httpSecurityExceptionHandlingConfigurer
            ->
            httpSecurityExceptionHandlingConfigurer // 토큰
                    .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                    .accessDeniedHandler(customAccessDeniedHandler)
        }
        return httpSecurity.build()
    }

    // 특정 URI 필터 제외
    @Bean
    fun webSecurityCustomizer(): WebSecurityCustomizer {
        return WebSecurityCustomizer { web ->
            web.ignoring()
                    .requestMatchers("/api/v1/oauth/**")
                    .requestMatchers("/api/v1/health/**")
                    .requestMatchers(HttpMethod.POST, "/api/v1/auth/**")
                    .requestMatchers(HttpMethod.GET, "/api/v1/notices/**")
                    .requestMatchers(HttpMethod.GET, "/api/v1/reviews/**")
                    .requestMatchers(HttpMethod.GET, "/api/v1/directions")
                    .requestMatchers(
                            HttpMethod.GET,
                            "/api/v1/emergency-rooms/**",
                    )
                    .requestMatchers(HttpMethod.POST, "/api/v1/auth/reissue")
                    .requestMatchers(
                            HttpMethod.POST,
                            "/api/v1/auth/admin/reissue",
                    )
        }
    }

    @Bean(name = ["customBcryptPasswordEncoder"])
    fun bcryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }
}
