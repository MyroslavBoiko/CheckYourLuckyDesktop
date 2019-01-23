package sample.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sample.backend.entity.*;
import sample.backend.repository.IpRepository;
import sample.backend.repository.StatisticRepository;
import sample.backend.repository.StatisticRequestRepository;
import sample.backend.utils.ParseRange;

import java.util.List;

@Service
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    private IpRepository ipRepository;

    @Autowired
    private StatisticRepository statisticRepository;

    @Autowired
    private StatisticRequestRepository statisticRequestRepository;

    public List<Statistic> getStatistic(RangeStringEntity request, String ipAddress) {
        IdIpEntity idIpEntity = ipRepository.findByIp(ipAddress);
        if (idIpEntity == null) {
            idIpEntity = new IdIpEntity();
            idIpEntity.setIp(ipAddress);
            ipRepository.save(idIpEntity);
        }
        StatisticRequest statisticRequest = statisticRequestRepository.findByIpEquals(idIpEntity.getId(), request.getRange());
        if (statisticRequest == null) {
            statisticRequest = new StatisticRequest();
            statisticRequest.setIp(idIpEntity);
            statisticRequest.setCount(0);
            statisticRequest.setRange(request.getRange());
            statisticRequestRepository.save(statisticRequest);
            List<Statistic> statistics = ParseRange.fillStatistic(ParseRange.parseRange(statisticRequest.getRange()), statisticRequest);
            for (Statistic statistic:
                 statistics) {
                statisticRepository.save(statistic);
            }
        }
        return statisticRepository.findAllById(statisticRequest.getId());
    }

    public List<Statistic> getStatistic(FileData fileData, String ipAddress) {
        IdIpEntity idIpEntity = ipRepository.findByIp(ipAddress);
        if (idIpEntity == null) {
            idIpEntity = new IdIpEntity();
            idIpEntity.setIp(ipAddress);
            ipRepository.save(idIpEntity);
        }
        StatisticRequest statisticRequest = statisticRequestRepository.findByIpEquals(idIpEntity.getId(), fileData.getRange());
        List<Statistic> statistics = null;
        if (statisticRequest == null) {
            statisticRequest = new StatisticRequest();
            statisticRequest.setIp(idIpEntity);
            statisticRequest.setCount(0);
            statisticRequest.setRange(fileData.getRange());
            statisticRequestRepository.save(statisticRequest);
            statistics = ParseRange.fillStatistic(ParseRange.parseRange(statisticRequest.getRange()), statisticRequest);
        }
        if(statistics == null)
            statistics = statisticRepository.findAllById(statisticRequest.getId());
        for (int i = 0; i < fileData.getValues().length; i++) {
            statisticRequest.setCount(statisticRequest.getCount() + 1);
            for (Statistic statistic:
                    statistics) {
                if (statistic.getValue().compareTo(fileData.getValues()[i]) == 0)
                    statistic.setCount(statistic.getCount() + 1);
            }
        }
        for (Statistic statistic:
                statistics) {
            statistic.setPercent(statistic.getCount().doubleValue()/statisticRequest.getCount());
        }
        for (Statistic statistic:
                statistics) {
            statisticRepository.save(statistic);
        }
        return statisticRepository.findAllById(statisticRequest.getId());
    }

}
