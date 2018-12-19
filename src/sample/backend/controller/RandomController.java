package sample.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sample.backend.entity.*;
import sample.backend.service.HistoryService;
import sample.backend.service.IpService;
import sample.backend.service.RandomService;
import sample.backend.service.StatisticService;

import javax.servlet.http.HttpServletRequest;
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

    public HistoryDto getRouletteNumber(TryLuckEntity tryLuckEntity) throws UnknownHostException {
        return randomService.getLuckyTry(InetAddress.getLocalHost().toString(), tryLuckEntity);
    }

    public List<Statistic> getStatistic(RangeStringEntity rangeStringEntity) throws UnknownHostException {
        return statisticService.getStatistic(rangeStringEntity, InetAddress.getLocalHost().toString());
    }

}
