package camput.controller;

import camput.Dto.CampCommentDto;
import camput.Service.CampCommentService;
import camput.Service.CamputService;
import camput.Service.LoginCheckService;
import camput.Service.MyPageBookInfoService;
import camput.domain.Camput;
import camput.domain.Commented;
import camput.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/camput")
public class ReviewController {
    private final CampCommentService campCommentService;
    private final CamputService camputService;
    private final LoginCheckService loginCheckService;

    @GetMapping("/reviews")
    public String getReviewsByCamput(@PathVariable Camput campName, Model model) {
        model.addAttribute("reviews", campCommentService.findByCampName(campName));
        model.addAttribute("product", camputService.findByCampName(String.valueOf(campName)));
        return "redirect:/camput/campDetail";
    }
    @PostMapping("/reviews/new")
    public void saveReview(@ModelAttribute CampCommentDto commentDto, HttpServletRequest request) {
        System.out.println(commentDto.getComment());
        System.out.println(commentDto.getStars());
        System.out.println(commentDto.getCampName());

        String loginId = loginCheckService.checkLogin(request);
        String memberName = String.valueOf(Member.builder().memberName(loginId));
        commentDto.setMemberName(memberName);
        campCommentService.save(commentDto);
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
    @GetMapping("/campDetail/{name}") // 리스트 아이디로 불러오기
    public String getReviews(@PathVariable Long Id, Model model) {
        model.addAttribute("Commented");
        return "reviews";
    }
//    @GetMapping("/reviews")
//    public String getReviews(Model model) {
//        model.addAttribute("reviews", campCommentService.findAllByCamput());
//        model.addAttribute("avgRating", campCommentService.ReviewService(getReviews()));
//        return "reviews";
//    }
}
