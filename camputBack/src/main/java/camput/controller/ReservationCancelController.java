package camput.controller;

import camput.Dto.CancelShowInfoDto;
import camput.Service.CancelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/camput")
@Slf4j
public class ReservationCancelController {
    private final CancelService cancelService;
    @GetMapping("/reservation/cancel")
    public String reservationCancelForm(@RequestParam("campName")String campName,
                                        @RequestParam("bookedDay")String bookedDay, Model model){
        log.info("campName={}",campName);
        log.info("bookedDay={}",bookedDay);
        CancelShowInfoDto info = cancelService.showCancelInfo(campName, "asd123", bookedDay);
        model.addAttribute("info",info);
        return "cancel";
    }

    @PostMapping("/rservation/cancel/deleteReaervation")
    @ResponseBody
    public String deleteReservation(@RequestBody String cancelInfo) throws ParseException {
        log.info("cancelInfo={}",cancelInfo);
        String result = cancelService.cancel(cancelInfo, "asd123");
        return result;
    }
}
