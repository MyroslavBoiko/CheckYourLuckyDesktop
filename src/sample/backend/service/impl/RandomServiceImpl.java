package sample.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sample.backend.algorithm.Operator;
import sample.backend.entity.*;
import sample.backend.repository.HistoryRepository;
import sample.backend.repository.IpRepository;
import sample.backend.repository.StatisticRepository;
import sample.backend.repository.StatisticRequestRepository;
import sample.backend.service.RandomService;
import sample.backend.utils.ParseRange;

import java.util.List;

@Service
public class RandomServiceImpl implements RandomService {

    @Autowired
    private IpRepository ipRepository;

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private StatisticRepository statisticRepository;

    @Autowired
    private StatisticRequestRepository statisticRequestRepository;

    @Transactional
    @Override
    public HistoryDto getLuckyTry(String ipAddress, RangeLuckEntity rangeLuckEntity) {
        if (ipAddress != null) {

            rangeLuckEntity.setBet();
            rangeLuckEntity.setRange();

            HistoryDto historyDto = Operator.checkWinReturnHistoryRange(rangeLuckEntity);
            HistoryDbEntity historyDbEntity = new HistoryDbEntity();

            IdIpEntity idIpEntity = findOrSaveIpEntity(ipAddress, historyDto.getRange());
            historyDbEntity.setIp(idIpEntity);
            historyDbEntity.setRange(historyDto.getRange());
            historyDbEntity.setWin(historyDto.isGame());
            historyDbEntity.setType(Type.RANGE.toString());
            historyDbEntity.setResult(Integer.toString(historyDto.getChoice()));
            historyDbEntity.setBet(historyDto.getBet());

            return processHistoryAndStatistic(idIpEntity, historyDto, historyDbEntity);
        }
        return null;
    }

    private StatisticRequest createStatistic(IdIpEntity idIpEntity, RangeStringEntity request) {
        StatisticRequest statisticRequest = statisticRequestRepository.findByIpEquals(idIpEntity.getId(), request.getRange());
        if (statisticRequest == null) {
            List<Statistic> statistics;
            statisticRequest = new StatisticRequest();
            statisticRequest.setIp(idIpEntity);
            statisticRequest.setCount(0);
            statisticRequest.setRange(request.getRange());
            statisticRequest = statisticRequestRepository.save(statisticRequest);
            statistics = ParseRange.fillStatistic(ParseRange.parseRange(statisticRequest.getRange()), statisticRequest);
            statisticRepository.save(statistics);
        }
        return statisticRequest;
    }

    private void addRandomAndUpdateStatistic(Integer newNumber, StatisticRequest statisticRequest) {
        List<Statistic> statistics = statisticRepository.findAllById(statisticRequest.getId());
        statisticRequest.setCount(statisticRequest.getCount() + 1);
        for (int j = 0; j < statistics.size(); j++) {
            if (statistics.get(j).getValue().compareTo(newNumber) == 0) {
                statistics.get(j).setCount(statistics.get(j).getCount() + 1);
                j = statistics.size();
            }
            for (Statistic statistic : statistics) {
                statistic.calculatePercent(statisticRequest.getCount());
                statisticRepository.save(statistic);
            }
        }
    }

    private HistoryDto processHistoryAndStatistic(IdIpEntity idIpEntity, HistoryDto historyDto, HistoryDbEntity historyDbEntity) {
        RangeStringEntity rangeStringEntity = new RangeStringEntity(historyDto.getRange());

        historyRepository.save(historyDbEntity);
        StatisticRequest statisticRequest = createStatistic(idIpEntity,rangeStringEntity);
        addRandomAndUpdateStatistic(historyDto.getChoice(), statisticRequest);
        return historyDto;
    }

    private IdIpEntity findOrSaveIpEntity(String ipAddress, String range) {
        IdIpEntity idIpEntity = ipRepository.findByIp(ipAddress);
        if (idIpEntity == null) {
            idIpEntity = new IdIpEntity();
            idIpEntity.setIp(ipAddress);
            idIpEntity = ipRepository.save(idIpEntity);
        }
        return idIpEntity;
    }
}
