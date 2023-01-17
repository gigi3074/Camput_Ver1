package camput.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class CampCommentDto {
    private LocalDateTime makedDate;
    private String comment;
    private String memberName;
    private int stars;
    @Builder
    public CampCommentDto(LocalDateTime makedDate, String comment, String memberName, int stars) {
        this.makedDate = makedDate;
        this.comment = comment;
        this.memberName = memberName;
        this.stars = stars;
    }
}
