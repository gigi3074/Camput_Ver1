package camput.repository;

import camput.domain.CampBooked;
import camput.domain.CampReservationDays;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CampReservationDaysRepository extends JpaRepository<CampReservationDays,Long> {
    List<CampReservationDays> findAllByReservationDaysAndCampName(LocalDate reservationDay,String campBooked);
    List<CampReservationDays> findByCampNameAndAndCount(String campName,int count);
}
