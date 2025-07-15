package gift.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import gift.model.Product;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository.deleteAll(); // 테스트마다 DB 초기화
    }

    @Test
    void save() {
        // given
        Product expected = new Product("coffee", 2500, "https://coffee.jpg");

        // when
        Product actual = productRepository.save(expected);

        // then
        assertAll(
            () -> assertThat(actual.getId()).isNotNull(),
            () -> assertThat(actual.getName()).isEqualTo(expected.getName()),
            () -> assertThat(actual.getPrice()).isEqualTo(expected.getPrice()),
            () -> assertThat(actual.getImageUrl()).isEqualTo(expected.getImageUrl())
        );
    }

    @Test
    void findById() {
        // given
        Product expected = new Product("coffee", 2500, "https://coffee.jpg");
        Product saved = productRepository.save(expected);

        // when
        Product actual = productRepository.findById(saved.getId())
            .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        // then
        assertAll(
            () -> assertThat(actual.getName()).isEqualTo(expected.getName()),
            () -> assertThat(actual.getPrice()).isEqualTo(expected.getPrice()),
            () -> assertThat(actual.getImageUrl()).isEqualTo(expected.getImageUrl())
        );
    }

    @Test
    void findAll() {
        // given
        Product product1 = new Product("coffee", 2500, "https://coffee.jpg");
        Product product2 = new Product("tea", 2000, "https://tea.jpg");
        productRepository.save(product1);
        productRepository.save(product2);

        // when
        List<Product> members = productRepository.findAll();

        // then
        assertAll(
            () -> assertThat(members).hasSize(2),
            () -> assertThat(members)
                .extracting(Product::getName)
                .containsExactlyInAnyOrder("coffee", "tea")
        );
    }

    @Test
    void update() {
        // given
        Product product = new Product("coffee", 2500, "https://coffee.jpg");
        Product savedProduct = productRepository.save(product);

        // when
        savedProduct.update("latte", 3500, "https://latte.jpg", false);

        // then
        Product foundProduct = productRepository.findById(savedProduct.getId())
            .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        assertAll(
            () -> assertThat(foundProduct.getName()).isEqualTo("latte"),
            () -> assertThat(foundProduct.getPrice()).isEqualTo(3500),
            () -> assertThat(foundProduct.getImageUrl()).isEqualTo("https://latte.jpg")
        );
    }


    @Test
    void deleteById() {
        // given
        Product product = new Product("coffee", 2500, "https://coffee.jpg");
        Product savedProduct = productRepository.save(product);

        // when
        productRepository.deleteById(savedProduct.getId());

        // then
        Optional<Product> deletedProduct = productRepository.findById(savedProduct.getId());
        assertThat(deletedProduct).isEmpty();
    }

}
