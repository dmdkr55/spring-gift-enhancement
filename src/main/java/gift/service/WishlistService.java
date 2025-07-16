package gift.service;

import gift.dto.LoginMemberDto;
import gift.dto.WishRequest;
import gift.dto.WishResponse;
import gift.model.Member;
import gift.model.Product;
import gift.model.Wishlist;
import gift.repository.WishlistRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class WishlistService {

    private final ProductService productService;
    private final MemberService memberService;
    private final WishlistRepository wishlistRepository;

    public WishlistService(ProductService productService, MemberService memberService,
        WishlistRepository wishlistRepository) {
        this.productService = productService;
        this.memberService = memberService;
        this.wishlistRepository = wishlistRepository;
    }

    public WishResponse create(WishRequest request, LoginMemberDto memberDto) {
        Member member = memberService.findByEmail(memberDto.getEmail());
        Product product = productService.getProduct(request.getProductId());

        Optional<Wishlist> existingWish = wishlistRepository.findByMemberAndProduct(member,
            product);
        if (existingWish.isPresent()) {
            throw new IllegalStateException("해당 상품은 이미 위시리스트에 존재합니다.");
        }

        Wishlist wishlist = new Wishlist(member, product, request.getQuantity());
        Wishlist saved = wishlistRepository.save(wishlist);

        return new WishResponse(saved.getMember().getId(), saved.getProduct().getId(), saved.getQuantity());
    }

    public List<WishResponse> findAllByMemberId(LoginMemberDto memberDto) {
        Member member = memberService.findByEmail(memberDto.getEmail());
        List<Wishlist> wishlists = wishlistRepository.findAllByMember(member);

        return wishlists.stream()
            .map(w -> new WishResponse(w.getMember().getId(), w.getProduct().getId(), w.getQuantity()))
            .collect(Collectors.toList());
    }

    public void deleteWishlist(LoginMemberDto memberDto, Long productId) {
        Member member = memberService.findByEmail(memberDto.getEmail());
        Product product = productService.getProduct(productId);

        if (wishlistRepository.findByMemberAndProduct(member, product).isEmpty()) {
            throw new IllegalArgumentException("삭제할 위시리스트가 존재하지 않습니다.");
        }
        wishlistRepository.deleteByMemberAndProduct(member, product);
    }
}
