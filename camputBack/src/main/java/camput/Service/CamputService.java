package camput.Service;

import camput.Dto.DetailPageDto;
<<<<<<< Updated upstream
import camput.Dto.ReservationDto;

public interface CamputService {
    DetailPageDto show(String campName);

    ReservationDto bookedCamp(String loginId, String campName, ReservationDto reservationDto);
=======
import camput.domain.Camput;
import org.json.simple.parser.ParseException;

public interface CamputService {
    DetailPageDto show(String campName,String memberLoginId);
    String bookedCamp(String loginId, String reservationInfoList) throws ParseException;

    Camput findByCampName(String campName);

>>>>>>> Stashed changes
}
