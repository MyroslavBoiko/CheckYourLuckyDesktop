package sample.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sample.backend.entity.Statistic;

import java.util.List;

public interface StatisticRepository extends JpaRepository<Statistic, Long> {
    @Query(value = "SELECT * FROM statistic s where s.statistic_range_id = :id",
            nativeQuery = true)
    List<Statistic> findAllById(@Param("id") Long id);
}
