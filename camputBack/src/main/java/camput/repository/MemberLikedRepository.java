package camput.repository;

import camput.domain.MemberLiked;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberLikedRepository  extends JpaRepository<MemberLiked,Long> {

    Page<MemberLiked> findByMember_Id(Long memberId, Pageable pageable);
}
