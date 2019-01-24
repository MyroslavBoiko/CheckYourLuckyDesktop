package sample.backend.service;

import sample.backend.entity.HistoryDto;

import java.util.List;

public interface HistoryService {
    List<HistoryDto> getSeveralLastHistory(String ipAdress);
}
