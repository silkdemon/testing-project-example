package testingprojectexample.back.citizen;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitizenRepository extends JpaRepository<CitizenModel, Long> {
}
