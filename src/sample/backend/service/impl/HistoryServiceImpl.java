package sample.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sample.backend.entity.HistoryDbEntity;
import sample.backend.entity.HistoryDto;
import sample.backend.entity.IdIpEntity;
import sample.backend.repository.HistoryRepository;
import sample.backend.repository.IpRepository;
import sample.backend.service.HistoryService;

import java.util.ArrayList;
import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private IpRepository ipRepository;

    @Autowired
    private HistoryRepository historyRepository;

    @Transactional
    @Override
    public List<HistoryDto> getSeveralLastHistory(String ipAddress){
        IdIpEntity idIpEntity = findOrSaveIpEntity(ipAddress);
        List<HistoryDbEntity> historyDbEntities = historyRepository.findAllByIpEqualsOrderById(ipRepository.findByIp(idIpEntity.getIp()).getId());
        List<HistoryDto> historyDtos = new ArrayList<>();
        for (HistoryDbEntity history : historyDbEntities) {
            HistoryDto dto = new HistoryDto(history.getBet(), history.getRange(),
                    Integer.parseInt(history.getResult()), history.isWin() ? true : false);
            historyDtos.add(dto);
        }
        return historyDtos;
    }

    private IdIpEntity findOrSaveIpEntity(String ipAddress) {
        IdIpEntity idIpEntity = ipRepository.findByIp(ipAddress);
        if (idIpEntity == null) {
            idIpEntity = new IdIpEntity();
            idIpEntity.setIp(ipAddress);
            idIpEntity = ipRepository.save(idIpEntity);
        }
        return idIpEntity;
    }
}
