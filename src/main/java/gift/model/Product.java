package gift.model;

import gift.validation.ValidProductName;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    @Size(max = 15, message = "이름은 15자까지 입력 가능합니다.")
    @ValidProductName
    @Column(name = "name", nullable = false, length = 15)
    private String name;

    @NotNull(message = "가격은 필수 입력 값입니다.")
    @PositiveOrZero(message = "가격은 음수가 될 수 없습니다.")
    @Column(name = "price", nullable = false)
    private Integer price;

    @NotBlank(message = "이미지 Url은 필수 입력 값입니다.")
    @Pattern(regexp = "^https?:\\/\\/.*\\.(jpg|jpeg|png|gif|bmp|tiff)$", message = "imageUrl의 형식이 잘못되었습니다.")
    @Column(name = "imageUrl", nullable = false)
    private String imageUrl;

    @Column(name = "needsMdApproval", nullable = false)
    private boolean needsMdApproval;

    public Product() {
    }

    public Product(Long id, String name, Integer price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public Product(String name, Integer price, String imageUrl) {
        this(null, name, price, imageUrl);
    }

    public void update(String name, int price, String imageUrl, boolean needsMdApproval) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.needsMdApproval = needsMdApproval;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public boolean getNeedsMdApproval() {
        return needsMdApproval;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setNeedsMdApproval(boolean needsMdApproval) {
        this.needsMdApproval = needsMdApproval;
    }

}
