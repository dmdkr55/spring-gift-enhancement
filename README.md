# spring-gift-enhancement

---
## 1단계-엔티티 매핑
- 지금까지 작성한 JdbcTemplate 기반 코드를 JPA로 리팩터링
- 객체의 참조와 테이블의 외래 키를 매핑해서 객체에서는 참조를 사용하고 테이블에서는 외래 키를 사용할 수 있도록 한다.
- @DataJpaTest를 사용하여 학습 테스트를 해 본다.
---