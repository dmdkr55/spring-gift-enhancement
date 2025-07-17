package gift.repository;

import gift.model.Member;
import gift.model.Product;
import gift.model.Wishlist;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

    Optional<Wishlist> findByMemberAndProduct(Member member, Product product);

    boolean existsByMemberAndProduct(Member member, Product product);

    List<Wishlist> findAllByMember(Member member);

    void deleteByMemberAndProduct(Member member, Product product);

}
