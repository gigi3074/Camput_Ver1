package camput.Service;

import camput.Dto.DetailPageDto;
<<<<<<< Updated upstream
<<<<<<< Updated upstream
=======
<<<<<<< HEAD
import camput.Dto.FinalReservationDto;
import camput.Dto.LikeDto;
>>>>>>> Stashed changes
=======
<<<<<<< HEAD
import camput.Dto.FinalReservationDto;
import camput.Dto.LikeDto;
=======
<<<<<<< Updated upstream
>>>>>>> gigi
>>>>>>> Stashed changes
import camput.Dto.ReservationDto;

public interface CamputService {
    DetailPageDto show(String campName);

<<<<<<< Updated upstream
    ReservationDto bookedCamp(String loginId, String campName, ReservationDto reservationDto);
=======
=======
import camput.Dto.ReservationDto;

public interface CamputService {
    DetailPageDto show(String campName);

<<<<<<< Updated upstream
    ReservationDto bookedCamp(String loginId, String campName, ReservationDto reservationDto);
>>>>>>> parent of cb9ccbe (0124)
>>>>>>> Stashed changes
=======
<<<<<<< HEAD
=======
    ReservationDto bookedCamp(String loginId, String campName, ReservationDto reservationDto);
=======
import camput.domain.Camput;
import org.json.simple.parser.ParseException;

public interface CamputService {
    DetailPageDto show(String campName,String memberLoginId);
    String bookedCamp(String loginId, String reservationInfoList) throws ParseException;

    Camput findByCampName(String campName);

>>>>>>> Stashed changes
>>>>>>> gigi
>>>>>>> Stashed changes
}
