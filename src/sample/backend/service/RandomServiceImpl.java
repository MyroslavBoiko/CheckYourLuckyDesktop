package sample.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sample.backend.algorithm.Operator;
import sample.backend.entity.*;
import sample.backend.repository.HistoryRepository;
import sample.backend.repository.IpRepository;

@Service
public class RandomServiceImpl implements RandomService {

    @Autowired
    private IpRepository ipRepository;

    @Autowired
    private HistoryRepository historyRepository;

    @Transactional
    @Override
    public HistoryDto getLuckyTry(String ipAddress, TryLuckEntity entity) {
        if(ipAddress != null){
                IdIpEntity idIpEntity = ipRepository.findByIp(ipAddress);
                if(idIpEntity == null){
                    idIpEntity = new IdIpEntity();
                    idIpEntity.setIp(ipAddress);
                    ipRepository.save(idIpEntity);
                }
            HistoryDto historyDto = Operator.checkWinReturnHistoryRoulette(entity);

            HistoryDbEntity historyDbEntity = new HistoryDbEntity();

            historyDbEntity.setIp(idIpEntity);
            historyDbEntity.setRange("Roulette");
            historyDbEntity.setWin(historyDto.isGame());
            historyDbEntity.setType(entity.getType().toString());
            historyDbEntity.setResult(Integer.toString(historyDto.getChoice()));
            historyDbEntity.setBet(historyDto.getBet());
            historyDto.setRange("Roulette");

            historyRepository.save(historyDbEntity);
            return historyDto;
        }
        return null;
    }

    @Transactional
    @Override
    public HistoryDto getLuckyTry(String ipAddress, RangeLuckEntity rangeLuckEntity) {
        if(ipAddress != null){
            IdIpEntity idIpEntity = ipRepository.findByIp(ipAddress);
            if(idIpEntity == null){
                idIpEntity = new IdIpEntity();
                idIpEntity.setIp(ipAddress);
                ipRepository.save(idIpEntity);
            }
            rangeLuckEntity.setBet();
            rangeLuckEntity.setRange();
            HistoryDto historyDto = Operator.checkWinReturnHistoryRange(rangeLuckEntity);

            HistoryDbEntity historyDbEntity = new HistoryDbEntity();

            historyDbEntity.setIp(idIpEntity);
            historyDbEntity.setRange(historyDto.getRange());
            historyDbEntity.setWin(historyDto.isGame());
            historyDbEntity.setType(Type.RANGE.toString());
            historyDbEntity.setResult(Integer.toString(historyDto.getChoice()));
            historyDbEntity.setBet(historyDto.getBet());

            historyRepository.save(historyDbEntity);

            return historyDto;
        }
        return null;
    }
}
