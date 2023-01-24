package camput.Impl;

import camput.Dto.CampCommentDto;
import camput.Dto.DetailPageDto;
import camput.Dto.ReservationDto;
import camput.Service.CampBookedService;
import camput.domain.*;
import camput.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CampBookedImpl implements CampBookedService {

    private final CamputRepository camputRepository;
    private final CampBookedRepository campBookedRepository;
    private final MemberBookedRepository memberBookedRepository;
    private final MemberRepository memberRepository;
    private final CampReservationDaysRepository campReservationDaysRepository;

    public CampBooked campBooking(String loginId, String campName, ReservationDto reservationInfo) {
        Camput camp = camputRepository.findByCampName(campName);
        Member member = memberRepository.findByMemberLoginId(loginId);
        LocalDate endDate = reservationInfo.getEndDate();
        LocalDate startDate = reservationInfo.getStartDate();

        CampBooked campBooking = CampBooked.builder()
                .campPrice(reservationInfo.getChoicePrice())
                .cBookedDay(LocalDateTime.now())
                .cEndDay(endDate)
                .camput(camp)
                .cStartDay(startDate)
                .build();

        CampBooked campBooked = campBookedRepository.save(campBooking);


        while(!startDate.equals(endDate)){
            List<CampReservationDays> allByReservationDays = campReservationDaysRepository.findAllByReservationDaysAndCampName(startDate,campName);
            log.info("allByReservationDays={}",allByReservationDays.toString());
            if(allByReservationDays.isEmpty()){
                CampReservationDays campReservationDay = CampReservationDays.builder()
                        .reservationDays(startDate)
                        .memberName(member.getMemberName())
                        .count(1)
                        .campName(campName)
                        .campBooked(campBooked)
                        .build();
                campReservationDaysRepository.save(campReservationDay);
                log.info("calenderstart");
                log.info("startDate={}",startDate);
                log.info("endDate={}",endDate);
                startDate = startDate.plusDays(1);
            }else{
                for(CampReservationDays reservationDay :allByReservationDays){
                    if(reservationDay.getCount()!=3){
                        reservationDay.addCount();
                    }else if(reservationDay.getCount()==3){
                        log.info("count={}",reservationDay.getCount());
                        return null;
                    }
                }
                startDate = startDate.plusDays(1);
            }
        }
        return campBooked;
    }
}

