package camput.Impl;

import camput.Dto.CampCommentDto;
import camput.Service.CampCommentService;
import camput.domain.Commented;
import camput.domain.CommentedImageFile;
import camput.domain.Member;
import camput.domain.MemberAddress;
import camput.repository.CommentedImageFileRepository;
import camput.repository.CommentedRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class CampCommentImpl implements CampCommentService {
    private final CommentedRepository commentedRepository;
    private final CommentedImageFileRepository commentedImageFileRepository;

    // 새글
    @Override
    @Transactional
    public void save(CampCommentDto commentDto) {
        CommentedImageFile img = CommentedImageFile.builder()
                .saveImageUrl(commentDto.getSaveImageUrl())
                .imageOriginalUrl(commentDto.getImageOriginalUrl())
                .imageFilename(commentDto.getImageFilename())
                .imageDate(commentDto.getImageDate())
                .build();
        CommentedImageFile save = commentedImageFileRepository.save(img);

        Commented commented = Commented.builder()
                .commentedContent(commentDto.getComment())
                .commentedDate(commentDto.getMakedDate())
                .stars(commentDto.getStars())
                .commentedMemberName(commentDto.getMemberName())
                .commentedImageFiles(save.getCommented().getCommentedImageFiles())
                .build();
        commentedRepository.save(commentDto);
    }

//    @Override
//    public double ReviewService(int stars) {
//        return commentedRepository.getAvgRating();
//    }
//
//    @Override
//    public List<Commented> findAllByCamput() {
//        return commentedRepository.findAll();
//    }

//    @Override
//    public CampCommentDto findByNickNameAndMakedDate(String nickName, String makedDate) {
//        return commentedRepository.findByNickNameAndMakedDate(nickName, makedDate);
//    }
//    @Override
//    public void deleteByNickNameAndMakedDate(String nickName, String makedDate) {
//
//    }

}
