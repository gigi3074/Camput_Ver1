package camput.controller;

import camput.Dto.FinalReservationDto;
import camput.Dto.MemberPointDto;
import camput.Dto.ReservationDto;
import camput.Service.CamputService;
import camput.Service.MemberService;
import camput.repository.CamputRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/camput")
@Slf4j
public class reservationPageController {

    private final CamputService camputService;
    private final MemberService memberService;
    @GetMapping("/reservationPage")
    public String reservationPageForm(@Valid@ModelAttribute FinalReservationDto reserInfo, Model model, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "redirect:/camput/detail"+reserInfo.getCampName();
        }
        MemberPointDto point = memberService.memberPoint("asd123", reserInfo.getPrice());
        if(Integer.parseInt(point.getAfterMemberPoint())<0){
            model.addAttribute("noPoint","cant");
        }
        model.addAttribute("memberInfo",point);
        model.addAttribute("finalReservationInfo",reserInfo);
        return "reservation";
    }

    @PostMapping("/reservationPage/init")
    @ResponseBody
    public String booking(@RequestBody String reservationInfoList) throws ParseException {
        log.info(reservationInfoList);
        String result = camputService.bookedCamp("asd123", reservationInfoList);
    return result;
    }
}
