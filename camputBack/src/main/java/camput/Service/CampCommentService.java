package camput.Service;

import camput.Dto.CampCommentDto;
import camput.domain.Commented;

import java.util.List;

public interface CampCommentService {
//    List<Commented> findAllByCamput();
//    CampCommentDto findByNickNameAndMakedDate(String nickName, String makedDate);
    void save(CampCommentDto commentDto); // 리뷰 저장
//    void deleteByNickNameAndMakedDate(String nickName, String makedDate); // 삭제
//    double ReviewService(int stars);
}
