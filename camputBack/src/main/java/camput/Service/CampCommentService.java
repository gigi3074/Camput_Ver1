package camput.Service;

import camput.Dto.CampCommentDto;
import camput.domain.Camput;
import camput.domain.Commented;

import java.util.List;
import java.util.Optional;

public interface CampCommentService {
    List<Commented> findByCampName(Camput campName);
    void save(CampCommentDto commentDto); // 리뷰 저장
    Optional<Commented> findById(Long id);

//    void deleteByNickNameAndMakedDate(String nickName, String makedDate); // 삭제

}
