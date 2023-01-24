package camput.controller;
import camput.Dto.FindIdDto;
import camput.Dto.FindPwDto;
import camput.Dto.LoginDto;
<<<<<<< Updated upstream
=======
<<<<<<< HEAD
import camput.Dto.loginApiDto.LoginSessionDto;
import camput.Service.LoginApiService;
import camput.Service.LoginCheckService;
=======
>>>>>>> parent of cb9ccbe (0124)
>>>>>>> Stashed changes
import camput.Service.MemberService;
import camput.domain.Member;
import camput.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static camput.session.SessionManager.SESSION_COOKIE_NAME;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/camput")
public class MemberController {

    private final MemberService memberService;

    // 로그인
    @GetMapping("/login") // 로그인 창 보여줌
    public String loginPage(Model model) {
        model.addAttribute("LoginDto", LoginDto.builder().build());
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("LoginDto") LoginDto loginDto, BindingResult bindingResult, HttpServletRequest request, HttpSession session) {
        if (bindingResult.hasErrors()) { // 빈값이면..!
            log.info("errors={}", bindingResult);
            return "login";
        }
        boolean isValid = memberService.loginIsValid(loginDto.getMemberLoginId(), loginDto.getMemberPassword());
            String loginMember = loginDto.getMemberLoginId();
        if (isValid) {  // 참이면
//            String loginMember = loginDto.getMemberLoginId();
            session = request.getSession();
            session.setAttribute(memberService.LOGIN_MEMBER, loginMember);  // 아이디 담아서
            return "redirect:/camput/main"; // 보냄
        } else {
            return "login";
        }

    }
    // 로그아웃
    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // 이거면 세션이랑 데이터 다 날라간다.
        }
        return "redirect:/camput/main";
    }

    // 아이디 찾기
    @GetMapping("/findId")
    public String findIdPage(Model model) {
        model.addAttribute("model", FindIdDto.builder().build());
        return "findId";
    }

    // 비밀번호 찾기
    // 보여주기
    @GetMapping("/findPw")
    public String checkId(Model model) {
        model.addAttribute("LoginDto", LoginDto.builder().build());
        return "findPw";
    }

    // 패스워드 변경
    @PostMapping(value = "/findId/modal")
    @ResponseBody   // 포스트를 어디론가로 보내줄꺼니까 애가 붙어있어야됨
    public FindIdDto findId(@ModelAttribute FindIdDto findIdDto) {
        FindIdDto memberLoginId = memberService.findByNickNameAndPhoneNumber(findIdDto);
        return memberLoginId;
    }

    @PostMapping("/findPw/id")
    @ResponseBody
    public boolean checkLoginId(@ModelAttribute("memberLoginId") String memberLoginId, HttpServletRequest request) {
        System.out.println("들어온다"+memberLoginId);
        log.info(String.valueOf(memberService.checkMemberLoginId(memberLoginId)));
        return memberService.checkMemberLoginId(memberLoginId);
    }
}

