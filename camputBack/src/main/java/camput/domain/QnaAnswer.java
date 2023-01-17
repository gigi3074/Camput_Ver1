package camput.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class QnaAnswer {

    @Id
    @GeneratedValue
    @Column(name = "qnaanswer_id")
    private Long id;
    private String qAnswer;
    @Builder
    public QnaAnswer(String qAnswer) {
        this.qAnswer = qAnswer;
    }
}
