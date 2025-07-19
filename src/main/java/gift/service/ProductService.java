package gift.service;

import gift.model.Product;
import gift.repository.ProductRepository;
import jakarta.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final EntityManager em;

    public ProductService(ProductRepository productRepository, EntityManager em) {
        this.productRepository = productRepository;
        this.em = em;
    }

    // 상품 저장
    public Product addProduct(Product product) {
        // 카카오가 포함된 이름은 MD의 승인 필요
        boolean isContainedKakao = product.getName().contains("카카오");
        product.setNeedsMdApproval(isContainedKakao);

        return productRepository.save(product);
    }

    // 상품 전체 조회
    public Page<Product> getAllProducts(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.asc("id"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return productRepository.findAllByNeedsMdApprovalFalse(pageable);
    }

    // 상품 단건 조회
    public Product getProduct(Long id) {
        Optional<Product> product = productRepository.findByIdAndNeedsMdApprovalFalse(id);
        if (product.isEmpty()) {
            throw new IllegalArgumentException(
                "id: " + id + ". 해당 ID의 상품이 존재하지 않습니다.");
        }
        return product.get();
    }

    // 상품 수정
    public void updateProduct(Long id, Product product) {
        // 수정 전에 존재 여부 체크
        Product foundProduct = em.find(Product.class, id);
        if (foundProduct == null) {
            throw new IllegalArgumentException("id: " + id + ". 수정할 상품이 존재하지 않습니다.");
        }

        // 카카오가 포함된 이름은 MD의 승인 필요
        boolean isContainedKakao = product.getName().contains("카카오");
        product.setNeedsMdApproval(isContainedKakao);

        foundProduct.update(product.getName(), product.getPrice(), product.getImageUrl(),
            product.getNeedsMdApproval());
    }

    // 상품 삭제
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
