package camput.repository;

import camput.domain.QnaAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QnaAnswerRepository  extends JpaRepository<QnaAnswer,Long> {
}
