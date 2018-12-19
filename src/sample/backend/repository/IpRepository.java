package sample.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sample.backend.entity.IdIpEntity;

public interface IpRepository extends JpaRepository<IdIpEntity, Long> {
    IdIpEntity findByIp(String ip);
}
