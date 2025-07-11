package gift.filter;

<<<<<<< HEAD
import gift.util.JwtUtil;
import io.jsonwebtoken.Claims;
=======
>>>>>>> ade5d96 (충남대학교 BE 김재혁 위시리스트 2단계 (#246))
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class JwtAuthenticationFilter implements Filter {

<<<<<<< HEAD
    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }
=======
    private static final String secretKey = "Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=";
>>>>>>> ade5d96 (충남대학교 BE 김재혁 위시리스트 2단계 (#246))

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
        throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String uri = request.getRequestURI();

<<<<<<< HEAD
        // wishlist 경로 토큰 검사. 로그인이 되어야 이용 가능한 기능
        if (uri.startsWith("/api/wishes")) {

            String token = request.getHeader("Authorization");

            if (token == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Token is missing");
                return;
            }

            Claims claims = jwtUtil.parseToken(token);
            if (claims == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Invalid Token");
                return;
            }

            request.setAttribute("loginMemberEmail", claims.get("email", String.class));

            chain.doFilter(req, res);
        } else {
            // wishlist 경로가 아니면 그냥 통과
=======
        // admin 경로만 토큰 검사
        if (uri.startsWith("/admin/members")) {
            String token = request.getHeader("Authorization");
            if (token == null || !isValidToken(token)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Unauthorized Token");
                return;
            }

            chain.doFilter(req, res);
        } else {
            // admin 경로가 아니면 그냥 통과
>>>>>>> ade5d96 (충남대학교 BE 김재혁 위시리스트 2단계 (#246))
            chain.doFilter(req, res);
        }
    }

<<<<<<< HEAD
=======
    private boolean isValidToken(String token) {
        try {
            Jwts.parser()
                .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .build()
                .parseClaimsJws(token); // 파싱되면 유효한 토큰
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
>>>>>>> ade5d96 (충남대학교 BE 김재혁 위시리스트 2단계 (#246))

}
