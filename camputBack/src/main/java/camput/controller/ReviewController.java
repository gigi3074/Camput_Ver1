package camput.controller;

import camput.Dto.CampCommentDto;
import camput.Dto.MemberInfoDto;
import camput.Dto.MemberJoinDto;
import camput.Service.CampCommentService;
<<<<<<< HEAD
import camput.Service.CamputService;
import camput.Service.LoginCheckService;
import camput.Service.MyPageBookInfoService;
import camput.domain.Camput;
import camput.domain.Commented;
=======
import camput.Service.LoginCheckService;
import camput.Service.MemberService;
import camput.Service.MyPageBookInfoService;
import camput.domain.Camput;
>>>>>>> myBranch
import camput.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
<<<<<<< HEAD
import javax.servlet.http.HttpSession;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;
=======
>>>>>>> myBranch

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/camput")
public class ReviewController {
    private final CampCommentService campCommentService;
<<<<<<< HEAD
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
=======
    private final LoginCheckService loginCheckService;
    private final MemberService memberService;

    @PostMapping("/reviews/insert") // 새글
    @ResponseBody
    public Boolean createReview(@ModelAttribute CampCommentDto commentDto, HttpServletRequest request) {
        String loginId = loginCheckService.checkLogin(request);
        MemberInfoDto memberInfoDto = memberService.findMemberInfo(loginId);
        Member member = Member.builder()
                .memberLoginId(memberInfoDto.getMemberLoginId())
                .nickName(memberInfoDto.getNickName())
                .id(memberInfoDto.getId())
                .build();
        commentDto.setMember(member);
        campCommentService.save(commentDto);
        return true;
    }
   @PostMapping("/reviews/update") // 수정 - 아이디로 불러와서 수정
   @ResponseBody
    public Boolean updateReview(@ModelAttribute CampCommentDto commentDto, HttpServletRequest request) {
       String loginId = loginCheckService.checkLogin(request);
       MemberInfoDto memberInfoDto = memberService.findMemberInfo(loginId);
       Member member = Member.builder()
               .memberLoginId(memberInfoDto.getMemberLoginId())
               .nickName(memberInfoDto.getNickName())
               .id(memberInfoDto.getId())
               .build();
       commentDto.setMember(member);
       campCommentService.update(commentDto);
       return true;
   }

    @GetMapping("/reviews/delete") // 삭제        /   @PathVariable    @RequestParam
    @ResponseBody
    public Boolean deleteReview(@RequestParam("id") Long id) {
        campCommentService.deleteById(id);
        return true;
    }
>>>>>>> myBranch
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
