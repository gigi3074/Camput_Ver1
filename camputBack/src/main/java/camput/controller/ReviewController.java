package camput.controller;

import camput.Dto.CampCommentDto;
import camput.Service.CampCommentService;
import camput.Service.MyPageBookInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/camput")
public class ReviewController {

    private final CampCommentService campCommentService;

    @PostMapping("/reviews") // 새글
    public String createReview(@ModelAttribute CampCommentDto commentDto) {
        campCommentService.save(commentDto);
        return "redirect:/reviews";
    }
//    @PutMapping("/reviews/id") // 수정 - 아이디로 불러와서 수정
//    public String updateReview(@PathVariable Long id, @ModelAttribute CampCommentDto commentDto) {
//        commentDto.setId(id);
//        campCommentService.save(commentDto);
//        return "redirect:/reviews";
//    }
//
//    @DeleteMapping("/reviews/id") // 삭제 - 닉네임, 날짜로
//    public String deleteReview(@PathVariable String nickName, String makedDate) {
//        campCommentService.deleteByNickNameAndMakedDate(nickName, makedDate);
//        return "redirect:/reviews";
//    }
//
//    @GetMapping("/reviews") // 리스트 아이디로 불러오기
//    public String getReviews(Model model) {
//        model.addAttribute("reviews", campCommentService.findAllByCamput());
//        return "reviews";
//    }
//    @GetMapping("/reviews")
//    public String getReviews(Model model) {
//        model.addAttribute("reviews", campCommentService.findAllByCamput());
//        model.addAttribute("avgRating", campCommentService.ReviewService(getReviews()));
//        return "reviews";
//    }
}
