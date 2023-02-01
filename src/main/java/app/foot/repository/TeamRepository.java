package app.foot.repository;

import app.foot.repository.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<TeamEntity, Integer> {
    Optional<TeamEntity> findByName(String name);
}
