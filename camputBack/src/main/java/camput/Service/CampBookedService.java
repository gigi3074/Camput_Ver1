package camput.Service;

import camput.Dto.DetailPageDto;
import camput.Dto.ReservationDto;
import camput.domain.CampBooked;

public interface CampBookedService {

    CampBooked campBooking(String loginId, String campName, ReservationDto reservationInfo);

}
