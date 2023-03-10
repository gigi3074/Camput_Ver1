package camput.Impl;

import camput.Dto.CategoryDto;
import camput.Dto.MapCampDto;
import camput.Dto.SearchDto;
import camput.Service.MapCampService;
import camput.domain.CampMap;
import camput.domain.Camput;
import camput.repository.CampMapRepository;
import camput.repository.CamputRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MapCampImpl implements MapCampService {

    private final CampMapRepository campMapRepository;
    private final CamputRepository camputRepository;

    @Override
    public List<MapCampDto> bestFiveCamp() {
       List<Camput> topfive = camputRepository.findTop5ByOrderByTotalStarAvgDesc();
       List<MapCampDto> topFiveDto= new ArrayList();
        for (Camput camput : topfive) {
            MapCampDto camp = MapCampDto.builder()
                    .imageUrl(camput.getCampImageFilesList().get(0).getCampOriginalUrl())
                    .simpleAddress(camput.getCampAddress().getDoNm() + camput.getCampAddress().getSigunNum())
                    .totalStars(camput.getTotalStarAvg())
                    .lineIntro(camput.getLineIntro())
                    .name(camput.getCampName())
                    .build();
            topFiveDto.add(camp);
        }
        return topFiveDto;
    }

    @Override
    public List<MapCampDto> findAll() {
        List<Camput> all = camputRepository.findAll();
        List<MapCampDto> resultAll= new ArrayList<>();
        for (Camput camput : all) {
            MapCampDto mapCampsDto = getMapCampsDto(camput);
            resultAll.add(mapCampsDto);
        }
        return resultAll;
    }

    @Override
    public List<MapCampDto> findCamps() {
     List<MapCampDto> mapCampDtoList = new ArrayList<>();
        List<Camput> allCamp = camputRepository.findAll();
        for (Camput camput : allCamp) {
            MapCampDto mapCampDto = getMapCampsDto(camput);
            mapCampDtoList.add(mapCampDto);
        }
        return mapCampDtoList;
    }

    @Override
    public List<MapCampDto> searchCamp(String searchText) {
        log.info("searchText={}",searchText);
        List<String> doList= new ArrayList<>();
        doList.add("?????????");doList.add("????????????");
        doList.add("?????????");doList.add("????????????");
        doList.add("?????????");doList.add("????????????");
        doList.add("?????????");doList.add("????????????");
        doList.add("????????????");doList.add("??????");doList.add("?????????");

        List<MapCampDto> resultSearch= new ArrayList<>();
        boolean check=false;
        for (String location: doList) {
            log.info("location={}",location);
            if(searchText.equals(location)){
                List<Camput> allByCheckDoNm = camputRepository.findAllByCheckDoNm(searchText);
                for (Camput camput : allByCheckDoNm) {
                    MapCampDto mapCampsDto = getMapCampsDto(camput);
                    log.info("mapCampsDto={}",mapCampsDto.getName());
                    resultSearch.add(mapCampsDto);
                    check=true;
                }
            }
            }
        if(searchText!=null&&check==false){
            Camput byCampName = camputRepository.findByCampName(searchText);
            if(byCampName==null){
                return null;
            }
            MapCampDto mapCampsDto = getMapCampsDto(byCampName);
            resultSearch.add(mapCampsDto);
            log.info("ONemapCampsDto={}",mapCampsDto.getName());
        } else if (searchText == null) {
            return null;
        }

        return resultSearch;
    }

    @Override
    public List<MapCampDto> categoryCamps( CategoryDto categoryDto) {
        List<MapCampDto> resultCategoryCamps= new ArrayList<>();
        List<String> locations = categoryDto.getLocation();
        String starAvg = categoryDto.getStar();
        String animalCheck = categoryDto.getAnimalCheck();

        if (categoryDto.getCampKinds()==null){
            List nullList=new ArrayList();
            nullList.add("");
            categoryDto.setCampKinds(nullList);
        }

        List<String> campKinds = categoryDto.getCampKinds();
        List<MapCampDto> mapCampDtoList = checkCategory(locations, starAvg, animalCheck , campKinds, resultCategoryCamps);
        return mapCampDtoList;
    }

    /**
     *
     * @param locations
     * @param starAvg
     * @param animalCheck
     * @param campKinds
     * @param resultCategoryCamps
     * @return resultCategoryResult
     */
    private List<MapCampDto> checkCategory(List<String> locations, String starAvg, String animalCheck, List<String> campKinds, List<MapCampDto> resultCategoryCamps) {

        if(animalCheck.equals("Yes")){
            if(starAvg.equals("1")){
                if (locations == null) {
                    return yesChecksNoLocation(campKinds, resultCategoryCamps,1L);
                }else{
                    return yesChecks(locations, campKinds, resultCategoryCamps,1L);
                }
            }
            else if (starAvg.equals("2")) {
                if (locations == null) {
                    return yesChecksNoLocation(campKinds, resultCategoryCamps,2L);
                }else{
                    return yesChecks(locations, campKinds, resultCategoryCamps,2L);
                }
            }
            else if (starAvg.equals("3")) {
                if (locations == null) {
                    return yesChecksNoLocation(campKinds, resultCategoryCamps,3L);
                }else{
                    return yesChecks(locations, campKinds, resultCategoryCamps,3L);
                }
            }
            else if (starAvg.equals("4"))  {
                if (locations == null) {
                    return yesChecksNoLocation(campKinds, resultCategoryCamps,4L);
                }else{
                    return yesChecks(locations, campKinds, resultCategoryCamps,4L);
                }
            }
            else{
                if (locations == null) {
                    return yesChecksNoLocation(campKinds, resultCategoryCamps,0L);
                }else{
                    return yesChecks(locations, campKinds, resultCategoryCamps,0L);
                }
            }
        } else if (animalCheck.equals("No")) {
            if(starAvg.equals("1")){
                if (locations == null) {
                    return noChecksNoLocation( campKinds, resultCategoryCamps,1L);
                }else{
                    return noChecks(locations, campKinds, resultCategoryCamps,1L);
                }
            }
            else if (starAvg.equals("2")) {
                if (locations == null) {
                    return noChecksNoLocation( campKinds, resultCategoryCamps,2L);
                }else{
                    return noChecks(locations, campKinds, resultCategoryCamps,2L);
                }
            }
            else if (starAvg.equals("3")) {
                if (locations == null) {
                    return noChecksNoLocation( campKinds, resultCategoryCamps,3L);
                }else{
                    return noChecks(locations, campKinds, resultCategoryCamps,3L);
                }
            } else if (starAvg.equals("4"))  {
                if (locations == null) {
                    return noChecksNoLocation( campKinds, resultCategoryCamps,4L);
                }else{
                    return noChecks(locations, campKinds, resultCategoryCamps,4L);
                }
            }  else{
                if (locations == null) {
                    return noChecksNoLocation( campKinds, resultCategoryCamps,0L);
                }else{
                    return noChecks(locations, campKinds, resultCategoryCamps,0L);
                }
            }
        }else {
            if(starAvg.equals("1")){
                if (locations == null) {
                    return nullChecksNoLocation( campKinds, resultCategoryCamps,1L);
                }else{
                    return nullChecks(locations, campKinds, resultCategoryCamps,1L);
                }
            }
            else if (starAvg.equals("2")) {
                if (locations == null) {
                    return nullChecksNoLocation( campKinds, resultCategoryCamps,2L);
                }else{
                    return nullChecks(locations, campKinds, resultCategoryCamps,2L);
                }
            }
            else if (starAvg.equals("3")) {
                if (locations == null) {
                    return nullChecksNoLocation( campKinds, resultCategoryCamps,3L);
                }else{
                    return nullChecks(locations, campKinds, resultCategoryCamps,3L);
                }
            }
            else if (starAvg.equals("4")) {
                if (locations == null) {
                    return nullChecksNoLocation( campKinds, resultCategoryCamps,4L);
                }else{
                    return nullChecks(locations, campKinds, resultCategoryCamps,4L);
                }
                }
            else{
                if (locations == null) {
                    return nullChecksNoLocation( campKinds, resultCategoryCamps,0L);
                }else{
                    return nullChecks(locations, campKinds, resultCategoryCamps,0L);
                }
            }
            }
        }

    private List<MapCampDto> yesChecksNoLocation(List<String> campKinds, List<MapCampDto> resultCategoryCamps, Long starAvg) {
        if(campKinds.size()==1&& campKinds.get(0).equals("?????????")){
            List<Camput> categoryCamps = camputRepository.findByCaravanAcceptNotContainsAndTotalStarAvgAfter("0", starAvg);
            log.info("yes1no??????={}");
            return getCategoryCamps(resultCategoryCamps, categoryCamps);
        }else if(campKinds.size()==1&& campKinds.get(0).equals("?????????")){
            List<Camput> categoryCamps =camputRepository.findByGlampingAcceptNotContainsAndTotalStarAvgAfter("0",starAvg);
            log.info("yes2no??????={}");
            return getCategoryCamps(resultCategoryCamps, categoryCamps);
        }else{
            List<Camput> categoryCamps =camputRepository.findByTotalStarAvgAfter(starAvg);
            log.info("yes3no??????={}");
            return getCategoryCamps(resultCategoryCamps, categoryCamps);
        }
    }

    private List<MapCampDto> nullChecks(List<String> locations, List<String> campKinds, List<MapCampDto> resultCategoryCamps, Long starAvg) {
        if(campKinds.size()==1&& campKinds.get(0).equals("?????????")){
            List<Camput> categoryCamps =camputRepository.findByCaravanAcceptNotContainsAndCheckDoNmInAndTotalStarAvgAfter("0", locations,starAvg);
            log.info("null1??????={}");
            return getCategoryCamps(resultCategoryCamps, categoryCamps);
        }else if(campKinds.size()==1&& campKinds.get(0).equals("?????????")){
            List<Camput> categoryCamps =camputRepository.findByGlampingAcceptNotContainsAndCheckDoNmInAndTotalStarAvgAfter("0", locations,starAvg);
            log.info("null2??????={}");
            return getCategoryCamps(resultCategoryCamps, categoryCamps);
        }else{
            List<Camput> categoryCamps =camputRepository.findByCheckDoNmInAndTotalStarAvgAfter(locations,starAvg);
            log.info("null3??????={}");
            return getCategoryCamps(resultCategoryCamps, categoryCamps);
        }
    }
    private List<MapCampDto> nullChecksNoLocation(List<String> campKinds, List<MapCampDto> resultCategoryCamps, Long starAvg) {
        if(campKinds.size()==1&& campKinds.get(0).equals("?????????")){
            List<Camput> categoryCamps =camputRepository.findByCaravanAcceptNotContainsAndTotalStarAvgAfter("0",starAvg);
            log.info("nullno1??????={}");
            return getCategoryCamps(resultCategoryCamps, categoryCamps);
        }else if(campKinds.size()==1&& campKinds.get(0).equals("?????????")){
            List<Camput> categoryCamps =camputRepository.findByGlampingAcceptNotContainsAndTotalStarAvgAfter("0",starAvg);
            log.info("nullnoy1??????={}");
            return getCategoryCamps(resultCategoryCamps, categoryCamps);
        }else{
            List<Camput> categoryCamps =camputRepository.findByTotalStarAvgAfter(starAvg);
            log.info("nullnoy1??????={}");
            return getCategoryCamps(resultCategoryCamps, categoryCamps);
        }
    }

    private List<MapCampDto> yesChecks(List<String> locations, List<String> campKinds, List<MapCampDto> resultCategoryCamps,Long star) {
        if(campKinds.size()==1&& campKinds.get(0).equals("?????????")){
            List<Camput> categoryCamps = camputRepository.findByCaravanAcceptNotContainsAndCheckDoNmInAndTotalStarAvgAfterAndAnimalCmClNotContains("0", locations, star, "?????????");
            log.info("y1??????={}");
            return getCategoryCamps(resultCategoryCamps, categoryCamps);
        }else if(campKinds.size()==1&& campKinds.get(0).equals("?????????")){
            List<Camput> categoryCamps = camputRepository.findByGlampingAcceptNotContainsAndCheckDoNmInAndTotalStarAvgAfterAndAnimalCmClNotContains("0", locations, star, "?????????");
            log.info("y2??????={}");
            return getCategoryCamps(resultCategoryCamps, categoryCamps);
        }else{
            for (String location : locations) {
                log.info("location={}",location);
            }
            List<Camput> categoryCamps = camputRepository.findByCheckDoNmInAndTotalStarAvgAfterAndAnimalCmClNotContains( locations, star, "?????????");
            log.info("y3???????????????");
            for (Camput categoryCamp : categoryCamps) {
                log.info("??????={}",categoryCamp.toString());
            }

            return getCategoryCamps(resultCategoryCamps, categoryCamps);
        }
    }
    private List<MapCampDto> noChecks(List<String> locations, List<String> campKinds, List<MapCampDto> resultCategoryCamps,Long star) {
        if(campKinds.size()==1&& campKinds.get(0).equals("?????????")){
            List<Camput> categoryCamps =camputRepository.findByCaravanAcceptNotContainsAndCheckDoNmInAndTotalStarAvgAfterAndAnimalCmClContains("0", locations,star,"?????????");
            log.info("n01??????={}");
            return getCategoryCamps(resultCategoryCamps, categoryCamps);
        }else if(campKinds.size()==1&& campKinds.get(0).equals("?????????")){
            List<Camput> categoryCamps =camputRepository.findByGlampingAcceptNotContainsAndCheckDoNmInAndTotalStarAvgAfterAndAnimalCmClContains("0", locations,star,"?????????");
            log.info("no2??????={}");
            return getCategoryCamps(resultCategoryCamps, categoryCamps);
        }else{
            List<Camput> categoryCamps =camputRepository.findByCheckDoNmInAndTotalStarAvgAfterAndAnimalCmClContains( locations,star,"?????????");
            log.info("no3??????={}");
            return getCategoryCamps(resultCategoryCamps, categoryCamps);
        }
    }
    private List<MapCampDto> noChecksNoLocation(List<String> campKinds, List<MapCampDto> resultCategoryCamps,Long star) {
        if(campKinds.size()==1&& campKinds.get(0).equals("?????????")){
            List<Camput> categoryCamps =camputRepository.findByCaravanAcceptNotContainsAndTotalStarAvgAfterAndAnimalCmClContains("0",star,"?????????");
            log.info("nlo01??????={}");
            return getCategoryCamps(resultCategoryCamps, categoryCamps);
        }else if(campKinds.size()==1&& campKinds.get(0).equals("?????????")){
            List<Camput> categoryCamps =camputRepository.findByGlampingAcceptNotContainsAndTotalStarAvgAfterAndAnimalCmClContains("0",star,"?????????");
            log.info("nlo2??????={}");
            return getCategoryCamps(resultCategoryCamps, categoryCamps);
        }else{
            List<Camput> categoryCamps =camputRepository.findByTotalStarAvgAfterAndAnimalCmClContains(star,"?????????");
            log.info("nlo3??????={}");
            return getCategoryCamps(resultCategoryCamps, categoryCamps);
        }
    }
    private static List<MapCampDto> getCategoryCamps(List<MapCampDto> resultCategoryCamps, List<Camput> categoryCamps) {
        for (Camput categoryCamp : categoryCamps) {
            MapCampDto mapCampsDto = getMapCampsDto(categoryCamp);
            log.info("mapCampsDto={}",mapCampsDto.toString());

            resultCategoryCamps.add(mapCampsDto);
        }
        return resultCategoryCamps;
    }

    private static MapCampDto getMapCampsDto(Camput camput) {
        return MapCampDto.builder()
                .name(camput.getCampName())
                .caraban(camput.getCaravanAccept())
                .gramping(camput.getGlampingAccept())
                .animalCheck(camput.getAnimalCmCl())
                .totalStars(camput.getTotalStarAvg())
                .doNum(camput.getCampAddress().getDoNm())
                .mapY(camput.getCampMap().getMapX())
                .mapX(camput.getCampMap().getMapY())
                .simpleAddress(camput.getCampAddress().getSimpleAddr())
                .lineIntro(camput.getLineIntro())
                .imageUrl(camput.getCampImageFilesList().get(0).getCampOriginalUrl())
                .homepageUrl(camput.getHomePage())
                .build();
    }
}

