package sample.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sample.backend.entity.Statistic;

public interface StatisticRepository extends JpaRepository<Statistic, Long> {
    @Query(value = "SELECT * FROM statistic s where s.statistic_range_id = :id ORDER BY s.percent desc LIMIT 1",
            nativeQuery = true)
    Statistic findAllByIdMax(@Param("id") Long id);

    @Query(value = "SELECT * FROM statistic s where s.statistic_range_id = :id ORDER BY s.percent asc LIMIT 1",
            nativeQuery = true)
    Statistic findAllByIdMin(@Param("id") Long id);
}
