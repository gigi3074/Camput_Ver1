package camput.controller;

import camput.Dto.DetailPageDto;
import camput.Service.CampCalenderService;
import camput.Service.CamputService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/camput")
public class CampDetailPageController {

    private final CamputService camputService;
    private final CampCalenderService campCalenderService;
    @GetMapping("/detail/{name}")
    public String detailPageForm(@PathVariable String name, Model model){

        DetailPageDto camp = camputService.show(name);
        List<LocalDate> localDates = campCalenderService.campBookedCalender(name);

        model.addAttribute("camp",camp);
        model.addAttribute("localDates",localDates);

        return "campDetail";
    }
}
