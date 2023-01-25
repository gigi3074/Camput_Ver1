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
<<<<<<< HEAD

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
=======
        Camput camput = Camput.builder()
                .id(commentDto.getCamputId())
                .build();
        Commented commented = Commented.builder()
                .commentedContent(commentDto.getComment())
                .commentedDate(commentDto.getMakedDate())
                .stars(commentDto.getStars())
                .commentedMemberName(commentDto.getMember().getNickName())
                //.commentedImageFiles(save.getCommented().getCommentedImageFiles())
                .member(commentDto.getMember())
                .camput(camput)
                .build();
        commentedRepository.save(commented);
>>>>>>> myBranch
    }

//    @Override
//    public double ReviewService(int stars) {
//        return commentedRepository.getAvgRating();
//    }
//
<<<<<<< HEAD
=======
    @Override
    public List<Commented> findAllByCamput(Camput camput) {
        return commentedRepository.findAllByCamput(camput);
    }
>>>>>>> myBranch

//    @Override
//    public CampCommentDto findByNickNameAndMakedDate(String nickName, String makedDate) {
//        return commentedRepository.findByNickNameAndMakedDate(nickName, makedDate);
//    }
    @Override
    @Transactional
    public void deleteById(Long id) {
        commentedRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void update(CampCommentDto commentDto) {
        Commented commented = commentedRepository.findById(commentDto.getId()).orElseThrow(() ->
                new IllegalArgumentException("commented 없다"));
        Camput camput = Camput.builder()
                .id(commentDto.getCamputId())
                .build();
        commented.commentedUpdate(
                commentDto.getId(),
                commentDto.getComment(),
                commentDto.getMember(),
                camput
        );

        commentedRepository.save(commented);
    }
}
