package camput.repository;

import camput.domain.Qna;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QnaRepository  extends JpaRepository<Qna,Long> {
}