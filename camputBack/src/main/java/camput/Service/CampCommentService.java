package camput.Service;

import camput.Dto.CampCommentDto;
import camput.domain.Camput;
import camput.domain.Commented;

import java.util.List;
import java.util.Optional;

public interface CampCommentService {
<<<<<<< HEAD
    List<Commented> findByCampName(Camput campName);
    void save(CampCommentDto commentDto); // 리뷰 저장
    Optional<Commented> findById(Long id);

//    void deleteByNickNameAndMakedDate(String nickName, String makedDate); // 삭제

=======
    List<Commented> findAllByCamput(Camput camput);
//    CampCommentDto findByNickNameAndMakedDate(String nickName, String makedDate);
    void save(CampCommentDto commentDto); // 리뷰 저장

    void deleteById(Long id); // 삭제
//    double ReviewService(int stars);

    void update(CampCommentDto commentDto);
>>>>>>> myBranch
}
