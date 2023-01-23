package camput.repository;

import camput.Dto.CampCommentDto;
import camput.domain.Camput;
import camput.domain.Commented;
import camput.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentedRepository extends JpaRepository<Commented,Long> {
    List<Commented> findAllByCamput(Camput camp);
//    CampCommentDto findByNickNameAndMakedDate(String nickName, String makedDate);

    CampCommentDto save(CampCommentDto commentDto);

//    @Query("SELECT AVG(com.rating) FROM Commented com")
//    double getAvgRating();

}
