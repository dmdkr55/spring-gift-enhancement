package gift.dto;

<<<<<<< HEAD
import jakarta.validation.constraints.Email;
=======
>>>>>>> ade5d96 (충남대학교 BE 김재혁 위시리스트 2단계 (#246))
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class RegisterRequest {

<<<<<<< HEAD
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "유효한 이메일 형식이 아닙니다.")
=======
    @NotBlank(message = "이메일는 필수 입력 값입니다.")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "유효한 이메일 형식이 아닙니다.")
>>>>>>> ade5d96 (충남대학교 BE 김재혁 위시리스트 2단계 (#246))
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    private String password;

    public RegisterRequest() {
    }

<<<<<<< HEAD
    public RegisterRequest(String email, String password) {
        this.email = email;
=======
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
>>>>>>> ade5d96 (충남대학교 BE 김재혁 위시리스트 2단계 (#246))
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
