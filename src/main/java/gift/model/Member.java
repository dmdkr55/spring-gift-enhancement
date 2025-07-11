package gift.model;

<<<<<<< HEAD
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
=======
import jakarta.validation.constraints.NotBlank;

public class Member {

    private Long id;

    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
>>>>>>> ade5d96 (충남대학교 BE 김재혁 위시리스트 2단계 (#246))
    private String password;

    public Member() {

    }

    public Member(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public Member(String email, String password) {
<<<<<<< HEAD
        this(null, email, password);
=======
        this.email = email;
        this.password = password;
>>>>>>> ade5d96 (충남대학교 BE 김재혁 위시리스트 2단계 (#246))
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
