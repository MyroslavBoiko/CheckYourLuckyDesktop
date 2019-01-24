package sample.backend.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import sample.backend.entity.HistoryDbEntity;
import sample.backend.entity.HistoryDto;
import sample.backend.entity.IdIpEntity;
import sample.backend.repository.HistoryRepository;
import sample.backend.repository.IpRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class HistoryServiceImplTest {

    private static final String TEST_IP = "000.000.000.000";
    private static final String NOT_EXIST_IP = "777";
    private static final Long TEST_ID = 99999L;
    private static final IdIpEntity ID_IP_ENTITY = new IdIpEntity();
    private static final HistoryDbEntity historyEntity = new HistoryDbEntity();

    @InjectMocks
    private HistoryServiceImpl historyService;

    @Mock
    private IpRepository ipRepository;

    @Mock
    private HistoryRepository historyRepository;

    @Before
    public void setUp() {
        ID_IP_ENTITY.setIp(TEST_IP);
        ID_IP_ENTITY.setId(TEST_ID);
        historyEntity.setResult("0");
        List<HistoryDbEntity> history = new ArrayList<>();
        history.add(historyEntity);

        when(ipRepository.findByIp(TEST_IP)).thenReturn(ID_IP_ENTITY);
        when(ipRepository.save(any(IdIpEntity.class))).thenReturn(ID_IP_ENTITY);
        when(historyRepository.findAllByIpEqualsOrderById(ID_IP_ENTITY.getId())).thenReturn(history);
    }

    @Test
    public void getSeveralLastHistoryWithExistedIp() {
        List<HistoryDto> result = historyService.getSeveralLastHistory(TEST_IP);

        assertEquals(Integer.parseInt(historyEntity.getResult()), result.get(0).getChoice());

        verify(ipRepository, times(2)).findByIp(TEST_IP);
        verify(ipRepository, never()).save(any(IdIpEntity.class));
        verify(historyRepository).findAllByIpEqualsOrderById(ID_IP_ENTITY.getId());
    }

    @Test
    public void getSeveralLastHistoryWithNotExistedIp() {
        when(ipRepository.findByIp(NOT_EXIST_IP)).thenReturn(null);
        when(ipRepository.findByIp(TEST_IP)).thenReturn(ID_IP_ENTITY);

        List<HistoryDto> result = historyService.getSeveralLastHistory(NOT_EXIST_IP);

        assertEquals(Integer.parseInt(historyEntity.getResult()), result.get(0).getChoice());

        verify(ipRepository).findByIp(NOT_EXIST_IP);
        verify(ipRepository).findByIp(TEST_IP);
        verify(ipRepository).save(any(IdIpEntity.class));
        verify(historyRepository).findAllByIpEqualsOrderById(ID_IP_ENTITY.getId());
    }
}
