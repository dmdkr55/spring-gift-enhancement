package gift.service;

import gift.dto.LoginRequest;
import gift.exception.EmailAlreadyExistsException;
import gift.exception.InvalidPasswordException;
import gift.exception.MemberNotFoundException;
<<<<<<< HEAD
import gift.util.JwtUtil;
=======
>>>>>>> ade5d96 (충남대학교 BE 김재혁 위시리스트 2단계 (#246))
import gift.util.PasswordUtil;
import gift.dto.RegisterRequest;
import gift.dto.TokenResponse;
import gift.model.Member;
import gift.repository.MemberRepository;
<<<<<<< HEAD
import java.util.List;
import java.util.Optional;
=======
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.List;
>>>>>>> ade5d96 (충남대학교 BE 김재혁 위시리스트 2단계 (#246))
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
<<<<<<< HEAD
    private final JwtUtil jwtUtil;

    public MemberService(MemberRepository memberRepository, JwtUtil jwtUtil) {
        this.memberRepository = memberRepository;
        this.jwtUtil = jwtUtil;
=======
    final String secretKey = "Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=";

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
>>>>>>> ade5d96 (충남대학교 BE 김재혁 위시리스트 2단계 (#246))
    }

    public TokenResponse save(RegisterRequest request) {
        if (memberRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException(request.getEmail());
        }

        String encryptedPassword = PasswordUtil.encode(request.getPassword());

        Member member = new Member(request.getEmail(), encryptedPassword);
        Member savedMember = memberRepository.save(member);

<<<<<<< HEAD
        String accessToken = jwtUtil.generateAccessToken(savedMember);
=======
        String accessToken = generateAccessToken(savedMember);
>>>>>>> ade5d96 (충남대학교 BE 김재혁 위시리스트 2단계 (#246))

        return new TokenResponse(accessToken);
    }

    public TokenResponse login(LoginRequest request) {
        Member member = memberRepository.findByEmail(request.getEmail()).orElseThrow(() ->
            new MemberNotFoundException(request.getEmail()));
<<<<<<< HEAD
=======
        ;
>>>>>>> ade5d96 (충남대학교 BE 김재혁 위시리스트 2단계 (#246))

        if (!PasswordUtil.matches(request.getPassword(), member.getPassword())) {
            throw new InvalidPasswordException();
        }

<<<<<<< HEAD
        String accessToken = jwtUtil.generateAccessToken(member);
=======
        String accessToken = generateAccessToken(member);
>>>>>>> ade5d96 (충남대학교 BE 김재혁 위시리스트 2단계 (#246))

        return new TokenResponse(accessToken);
    }

<<<<<<< HEAD
=======
    public String generateAccessToken(Member member) {
        return Jwts.builder()
            .setSubject(member.getId().toString())
            .claim("email", member.getEmail())
            .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
            .compact();
    }

>>>>>>> ade5d96 (충남대학교 BE 김재혁 위시리스트 2단계 (#246))
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

<<<<<<< HEAD
    public Member findByEmail(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        if (member.isEmpty()) {
            throw new MemberNotFoundException(email);
        }
        return member.get();
    }

=======
>>>>>>> ade5d96 (충남대학교 BE 김재혁 위시리스트 2단계 (#246))
}
