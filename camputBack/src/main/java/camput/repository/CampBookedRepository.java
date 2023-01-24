package camput.repository;

import camput.domain.CampBooked;
import camput.domain.Camput;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CampBookedRepository extends JpaRepository<CampBooked,Long> {

    List<CampBooked> findAllByCamput(Camput camput);
}
