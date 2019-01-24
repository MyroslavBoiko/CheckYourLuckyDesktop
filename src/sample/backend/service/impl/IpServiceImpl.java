package sample.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sample.backend.entity.IdIpEntity;
import sample.backend.repository.IpRepository;

@Service
public class IpServiceImpl implements IpService {

    @Autowired
    private IpRepository ipRepository;

    public void saveIp(String ipAddress){
        IdIpEntity idIpEntity = ipRepository.findByIp(ipAddress);
        if(idIpEntity == null){
            idIpEntity = new IdIpEntity();
            idIpEntity.setIp(ipAddress);
            ipRepository.save(idIpEntity);
        }
    }
}
