package sample.backend.service;

import sample.backend.entity.HistoryDto;
import sample.backend.entity.RangeLuckEntity;
import sample.backend.entity.TryLuckEntity;

public interface RandomService {
    HistoryDto getLuckyTry(String ipAddress, TryLuckEntity entity);
    HistoryDto getLuckyTry(String ipAddress, RangeLuckEntity entity);
}
