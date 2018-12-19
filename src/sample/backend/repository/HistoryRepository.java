package sample.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sample.backend.entity.HistoryDbEntity;

import java.util.List;

public interface HistoryRepository extends JpaRepository<HistoryDbEntity, Long> {
    @Query(value = "SELECT * FROM history s where s.ip_address_id = :id ORDER BY s.id desc LIMIT 3",
            nativeQuery = true)
    List<HistoryDbEntity> findAllByIpEqualsOrderById(@Param("id") Long id);
}
