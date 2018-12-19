package sample.backend.service;

import sample.backend.entity.HistoryDto;
import sample.backend.entity.RangeLuckEntity;

public interface RandomService {
    HistoryDto getLuckyTry(String ipAddress, RangeLuckEntity entity);
}
