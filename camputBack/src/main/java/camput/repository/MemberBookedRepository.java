package camput.repository;

import camput.domain.MemberBooked;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberBookedRepository extends JpaRepository<MemberBooked,Long> {
    Page<MemberBooked> findByMember_Id(Long memberId, Pageable pageable);

}
