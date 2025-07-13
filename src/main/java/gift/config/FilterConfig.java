package gift.config;

import gift.filter.JwtAuthenticationFilter;

<<<<<<< HEAD
<<<<<<< HEAD
import gift.util.JwtUtil;
=======
>>>>>>> ade5d96 (충남대학교 BE 김재혁 위시리스트 2단계 (#246))
=======
import gift.util.JwtUtil;
>>>>>>> 048ca94 (충남대 BE 김재혁 - Step3 (#328))
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    private final JwtUtil jwtUtil;

    public FilterConfig(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Bean
    public FilterRegistrationBean<Filter> jwtFilter() {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new JwtAuthenticationFilter(jwtUtil));
        registration.addUrlPatterns("/*"); // 전체 경로 감시
        registration.setOrder(1);
        return registration;
    }
}
