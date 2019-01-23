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

    public void saveHistory(HistoryDbEntity historyDbEntity){
        historyRepository.save(historyDbEntity);
    }

    @Transactional
    @Override
    public List<HistoryDto> getSeveralLastHistory(String ipAddress){
        IdIpEntity idIpEntity = ipRepository.findByIp(ipAddress);
        if(idIpEntity == null){
            idIpEntity = new IdIpEntity();
            idIpEntity.setIp(ipAddress);
            ipRepository.save(idIpEntity);
        }
        List<HistoryDbEntity> historyDbEntities = historyRepository.findAllByIpEqualsOrderById(ipRepository.findByIp(ipAddress).getId());
        List<HistoryDto> historyDtos = new ArrayList<>();
        for (HistoryDbEntity history:
             historyDbEntities) {
            HistoryDto dto = new HistoryDto(history.getBet(), history.getRange(), Integer.parseInt(history.getResult()), history.isWin());
            historyDtos.add(dto);
        }
        return historyDtos;
    }
}
