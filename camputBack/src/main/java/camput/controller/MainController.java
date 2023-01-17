package camput.controller;

import camput.Service.MemberService;
import camput.domain.Member;
import camput.repository.MemberRepository;
import camput.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/camput")
public class MainController {
    private final MemberService memberService;
    @GetMapping("/main") // 쿠키를 받자
    public String homeLogin(Model model, HttpSession session) {
        String loginMember = (String) session.getAttribute("loginMember");
        System.out.println(loginMember);
        if (loginMember == null) { // 로그인 값이 없으면
            return "/main";
        }
        model.addAttribute("member", loginMember);
        log.info("loginMember", loginMember);
        return "/main";  // 로그인 값이 있으면
    }

}
