package camput.controller;

import camput.Dto.CategoryDto;
import camput.Dto.MapCampDto;
import camput.Dto.MemberInfoDto;
import camput.Dto.SearchDto;
import camput.Service.MapCampService;
import camput.repository.CamputRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@RequiredArgsConstructor
@RequestMapping("/camput")
@Slf4j
public class HomeController {
    private final MapCampService mapCampService;
    @GetMapping("/home")
    public String homeForm(Model model){
        List<MapCampDto> mapCampDtoList = mapCampService.bestFiveCamp();
        List<MapCampDto> camps = mapCampService.findCamps();
        model.addAttribute("campList",camps);
        model.addAttribute("topFive",mapCampDtoList);
        return "home";
    }

    @GetMapping("/home/category")
    @ResponseBody
    public List<MapCampDto> categoryCamp(@ModelAttribute CategoryDto categoryDto){
        log.info(categoryDto.getAnimalCheck());
        List<MapCampDto> mapCampDtoList = mapCampService.categoryCamps(categoryDto);

        return mapCampDtoList;
    }

    @GetMapping("/home/search")
    @ResponseBody
    public List<MapCampDto> searchCamps(@RequestParam String searchInputText){
        log.info("실행");
        log.info("searchInputText={}",searchInputText);
      List<MapCampDto> mapCampDtoList = mapCampService.searchCamp(searchInputText);
        if(mapCampDtoList==null){
         return null;
        }else {
            return mapCampDtoList;
        }
    }

    @GetMapping("/home/reset")
    @ResponseBody
    public List<MapCampDto> reset(){
        List<MapCampDto> all = mapCampService.findAll();
        return all;
    }
}

