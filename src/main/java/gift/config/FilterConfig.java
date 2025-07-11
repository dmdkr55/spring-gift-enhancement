package gift.config;

import gift.filter.JwtAuthenticationFilter;

<<<<<<< HEAD
import gift.util.JwtUtil;
=======
>>>>>>> ade5d96 (충남대학교 BE 김재혁 위시리스트 2단계 (#246))
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

<<<<<<< HEAD
    private final JwtUtil jwtUtil;

    public FilterConfig(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Bean
    public FilterRegistrationBean<Filter> jwtFilter() {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new JwtAuthenticationFilter(jwtUtil));
=======
    @Bean
    public FilterRegistrationBean<Filter> jwtFilter() {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new JwtAuthenticationFilter());
>>>>>>> ade5d96 (충남대학교 BE 김재혁 위시리스트 2단계 (#246))
        registration.addUrlPatterns("/*"); // 전체 경로 감시
        registration.setOrder(1);
        return registration;
    }
}
