package sample.backend.service.impl;

import sample.backend.entity.FileData;
import sample.backend.entity.RangeStringEntity;
import sample.backend.entity.Statistic;

import java.util.List;

public interface StatisticService {
    List<Statistic> getStatistic(RangeStringEntity request, String ipAddress);

    List<Statistic> getStatistic(FileData fileData, String ipAddress);
}
