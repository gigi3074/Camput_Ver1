package camput.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Qna {

    @Id
    @GeneratedValue
    @Column(name = "qna_id")
    private Long id;
    private String qnaTitle;
    private String qnaContent;
    private LocalDateTime qnaUpdateDate;
    @OneToOne
    private QnaAnswer qnaAnswer;

    @Builder
    public Qna(String qnaTitle, String qnaContent, LocalDateTime qnaUpdateDate, QnaAnswer qnaAnswer) {
        this.qnaTitle = qnaTitle;
        this.qnaContent = qnaContent;
        this.qnaUpdateDate = qnaUpdateDate;
        this.qnaAnswer = qnaAnswer;
    }
}
