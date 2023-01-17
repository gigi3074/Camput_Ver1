package camput.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CampReservationDays {
    @Id@GeneratedValue
    @Column(name = "campbookedDays_id")
    private Long id;
    private String memberName;
    private LocalDate reservationDays;
    private int count;
    private String campName;
    @ManyToOne(fetch = FetchType.LAZY)
    private CampBooked campBooked;
    @Builder
    public CampReservationDays(String memberName,String campName ,LocalDate reservationDays, int count, CampBooked campBooked) {
        this.memberName = memberName;
        this.reservationDays = reservationDays;
        this.count = count;
        this.campBooked = campBooked;
        this.campName = campName;
    }

    public CampReservationDays addCount(){
        this.count+=1;
        return this;
    }
}
