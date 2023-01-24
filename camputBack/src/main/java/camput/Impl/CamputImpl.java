package camput.Impl;

import camput.Dto.CampCommentDto;
import camput.Dto.DetailPageDto;
import camput.Dto.ReservationDto;
import camput.Service.CamputService;
import camput.domain.*;
import camput.repository.CamputRepository;
import camput.repository.CommentedRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class CamputImpl implements CamputService {

    private final CamputRepository camputRepository;
    private final CommentedRepository commentedRepository;
    private final CampBookedImpl campBooked;
    private final MemberBookedImpl memberBooked;

    @Override
    public DetailPageDto show(String campName){
        Camput camp = camputRepository.findByCampName(campName);
        List<CampCommentDto> campCommentDtos = getCampCommentDtos(camp);
        List<String> prices = getPrices(camp);
        String address = camp.getCampAddress().getSimpleAddr();
        DetailPageDto campInfo = makeDetailPage(camp, campCommentDtos, prices, address);
        return campInfo;
    }

    @Override
    public ReservationDto bookedCamp(String memberId,String campName,ReservationDto reservationDto) {
        CampBooked cmBooked= campBooked.campBooking(memberId, campName, reservationDto);
        log.info("cmBooked={}",cmBooked.getId());
        if(cmBooked==null){
            return null;
        }
        MemberBooked booked = memberBooked.makeMemberReservation(cmBooked.getId(), memberId);

        ReservationDto reservation = ReservationDto.builder()
                .choicePrice(cmBooked.getCampPrice())
                .startDate(booked.getMStartDay())
                .endDate(booked.getMEndDay())
                .campName(booked.getBookedCampName())
                .memberName(booked.getMember().getMemberName())
                .build();
        return reservation;
    }

    private static DetailPageDto makeDetailPage(Camput camp, List<CampCommentDto> campCommentDtos, List<String> prices, String address) {
        DetailPageDto campInfo = DetailPageDto.builder()
                .campAddress(address)
                .simpleIntro(camp.getLineIntro())
                .detailIntro(camp.getIntro())
                .campContents(campCommentDtos)
                .image(camp.getCampImageFilesList().get(0).getCampOriginalUrl())
                .campTotalAvg(camp.getTotalStarAvg())
                .prices(prices)
                .build();
        return campInfo;
    }

    private static List<String> getPrices(Camput camp) {
        List<CampPrice> campPrices = camp.getCampPrices();
        List<String> prices = new ArrayList<>();

        for (CampPrice campPrice : campPrices) {
            prices.add(Integer.toString(campPrice.getPrice())) ;
        }
        return prices;
    }

    private List<CampCommentDto> getCampCommentDtos(Camput camp) {
        List<Commented> campComments = commentedRepository.findAllByCamput(camp);
        List<CampCommentDto> campCommentDtos = new ArrayList<>();
        if(campComments!=null){
            for (Commented campComment : campComments) {
                CampCommentDto comment = CampCommentDto.builder()
                        .comment(campComment.getCommentedContent())
                        .makedDate(campComment.getCommentedDate())
                        .stars(campComment.getStars())
                        .memberName(campComment.getMember().getMemberName())
                        .build();
                campCommentDtos.add(comment);
            }
        }
        return campCommentDtos;
    }

    @Override
    public Camput findByCampName(String campName) {
        return camputRepository.findByCampName(campName);
    }

}
