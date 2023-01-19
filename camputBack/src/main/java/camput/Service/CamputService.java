package camput.Service;

import camput.Dto.DetailPageDto;
import camput.Dto.FinalReservationDto;
import camput.Dto.LikeDto;
import camput.Dto.ReservationDto;
import org.json.simple.parser.ParseException;

public interface CamputService {
    DetailPageDto show(String campName,String memberLoginId);

    String bookedCamp(String loginId, String reservationInfoList) throws ParseException;

}
