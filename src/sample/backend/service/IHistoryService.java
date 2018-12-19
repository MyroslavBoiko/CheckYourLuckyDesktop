package sample.backend.service;

import sample.backend.entity.HistoryDto;

import java.util.List;

public interface IHistoryService {
    List<HistoryDto> getSeveralLastHistory(String ipAdress);
}
