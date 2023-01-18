package camput.Service;

import camput.Dto.DetailPageDto;
import camput.Dto.LikeDto;
import camput.Dto.ReservationDto;

public interface CamputService {
    DetailPageDto show(String campName,String memberLoginId);

    ReservationDto bookedCamp(String loginId, String campName, ReservationDto reservationDto);

}
