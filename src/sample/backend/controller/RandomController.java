package sample.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sample.backend.entity.*;
import sample.backend.service.HistoryServiceImpl;
import sample.backend.service.IpServiceImpl;
import sample.backend.service.RandomService;
import sample.backend.service.StatisticServiceImpl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

@Component
public class RandomController {

    @Autowired
    private RandomService randomService;

    @Autowired
    private StatisticServiceImpl statisticService;

    @Autowired
    private HistoryServiceImpl historyService;

    @Autowired
    private IpServiceImpl ipService;

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
