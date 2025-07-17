package gift.repository;

import gift.model.Member;
import gift.model.Product;
import gift.model.Wishlist;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

    Optional<Wishlist> findByMemberAndProduct(Member member, Product product);

    boolean existsByMemberAndProduct(Member member, Product product);

    @Query("SELECT w FROM Wishlist w JOIN FETCH w.member JOIN FETCH w.product WHERE w.member = :member")
    Page<Wishlist> findAllByMember(Pageable pageable, Member member);

    void deleteByMemberAndProduct(Member member, Product product);

}
