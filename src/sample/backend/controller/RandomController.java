package sample.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sample.backend.entity.HistoryDto;
import sample.backend.entity.RangeLuckEntity;
import sample.backend.entity.RangeStringEntity;
import sample.backend.entity.Statistic;
import sample.backend.service.HistoryService;
import sample.backend.service.RandomService;
import sample.backend.service.impl.IpService;
import sample.backend.service.impl.StatisticService;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

@Component
public class RandomController {

    @Autowired
    private RandomService randomService;

    @Autowired
    private StatisticService statisticService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private IpService ipService;

    public List<HistoryDto> getHistory() throws UnknownHostException {
        ipService.saveIp(InetAddress.getLocalHost().toString());
        return historyService.getSeveralLastHistory(InetAddress.getLocalHost().toString());
    }

    public HistoryDto getRangeNumber(RangeLuckEntity rangeLuckEntity) throws UnknownHostException {
        return randomService.getLuckyTry(InetAddress.getLocalHost().toString(), rangeLuckEntity);
    }

    public List<Statistic> getStatistic(RangeStringEntity rangeStringEntity) throws UnknownHostException {
        return statisticService.getStatistic(rangeStringEntity, InetAddress.getLocalHost().toString());
    }

}
