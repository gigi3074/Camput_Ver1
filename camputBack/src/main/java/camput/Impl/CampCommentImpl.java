package camput.Impl;

import camput.Dto.CampCommentDto;
import camput.Service.CampCommentService;
import camput.domain.*;
import camput.repository.CommentedImageFileRepository;
import camput.repository.CommentedRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class CampCommentImpl implements CampCommentService {
    private final CommentedRepository commentedRepository;
    private final CommentedImageFileRepository commentedImageFileRepository;


    @Override
    public List<Commented> findByCampName(Camput campName) {
        return commentedRepository.findAllByCamput(campName);
    }

    // 새글
    @Override
    @Transactional
    public void save(CampCommentDto commentDto) {
//        CommentedImageFile img = CommentedImageFile.builder()
//                .saveImageUrl(commentDto.getSaveImageUrl())
//                .imageOriginalUrl(commentDto.getImageOriginalUrl())
//                .imageFilename(commentDto.getImageFilename())
//                .imageDate(commentDto.getImageDate())
//                .build();
//        CommentedImageFile save = commentedImageFileRepository.save(img);

        Commented commented = Commented.builder()
                .commentedContent(commentDto.getComment())
                .commentedDate(LocalDateTime.now())
                .commentedMemberName(commentDto.getMemberName())
                .stars((Integer) commentDto.getStars())
//                .commentedImageFiles(save.getCommented().getCommentedImageFiles())
                .build();
        commentedRepository.save(commented);
    }

    @Override
    public Optional<Commented> findById(Long id) {
        Optional<Commented> commentedList = commentedRepository.findById(id);
        return commentedList;
    }

//    @Override
//    public double ReviewService(int stars) {
//        return commentedRepository.getAvgRating();
//    }
//

//    @Override
//    public CampCommentDto findByNickNameAndMakedDate(String nickName, String makedDate) {
//        return commentedRepository.findByNickNameAndMakedDate(nickName, makedDate);
//    }
//    @Override
//    public void deleteByNickNameAndMakedDate(String nickName, String makedDate) {
//
//    }

}
