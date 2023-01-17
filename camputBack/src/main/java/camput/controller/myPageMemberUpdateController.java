package camput.controller;

import camput.Dto.MemberJoinDto;
import camput.Impl.MemberUpdateImpl;
import camput.Service.JoinService;
import camput.Service.MemberUpdateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Slf4j
@Controller
@RequiredArgsConstructor
public class myPageMemberUpdateController {

    private final MemberUpdateService memberUpdateService;
    private final JoinService joinService;


    @GetMapping("/member/update")
    public String intoPage(){
        return "myPageMemberUpdate";
    }

    @PostMapping("/member/view") // myPageMemberUpdate 페이지를 열면 function이 실행.
    @ResponseBody // 응답 선언.
    public MemberJoinDto view(@ModelAttribute MemberJoinDto memberJoinDto){
        MemberJoinDto result = memberUpdateService.view(memberJoinDto);
        return result;
    }

    @PostMapping("/member/update")
    @ResponseBody
    public String update(@ModelAttribute MemberJoinDto memberJoinDto){
        System.out.println("memberJoinDto = " + memberJoinDto);
        String MemberUpdate = memberUpdateService.update(memberJoinDto);
        //return "/camput/myPage/information";
        return "";
    }

}
