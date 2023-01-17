package camput.repository;

import camput.domain.Commented;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentedRepository extends JpaRepository<Commented,Long> {
    List<Commented> findAllByCamput(Long id);
}