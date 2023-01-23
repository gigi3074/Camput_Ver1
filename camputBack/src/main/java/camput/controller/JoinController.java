package camput.controller;

import camput.Dto.MemberJoinDto;
import camput.Service.JoinService;
import camput.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
//@RequestMapping("/camput/join") // 부모라 생각하자
public class JoinController {

    private final JoinService joinService; // 생성자 주입, JoinService의 권한이 생긴다.
    @GetMapping("/member/join")
    public String intoPage(){
        return "join";
    }

    @PostMapping("/member/join")
    public String join(@ModelAttribute MemberJoinDto memberJoinDto){
        System.out.println("JoinController.join");
        System.out.println("memberJoinDto = " + memberJoinDto);
        joinService.join(memberJoinDto);
        return "redirect:/camput/login";
    }

    @PostMapping("/member/join2")
    public @ResponseBody String loginCheck(@RequestParam("memberLoginId") String memberLoginId){
        String checkResult = joinService.memberLoginCheck(memberLoginId);
        if(checkResult != null) {
            return "ok";
        } else{
            return "no";
        }
    }
}
