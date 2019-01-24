package sample.backend.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import sample.backend.entity.*;
import sample.backend.repository.HistoryRepository;
import sample.backend.repository.IpRepository;
import sample.backend.repository.StatisticRepository;
import sample.backend.repository.StatisticRequestRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RandomServiceImplTest {

    private static final String BET_INPUT = "23";
    private static final String BET_RANGE = "0-36";
    private static final String TEST_IP = "000.000.000.000";
    private static final Long TEST_ID = 99999L;
    private static final IdIpEntity ID_IP_ENTITY = new IdIpEntity();
    private static final IdIpEntity NEW_IP_ENTITY = new IdIpEntity();
    private static final RangeLuckEntity RANGE_LUCK_ENTITY = new RangeLuckEntity(BET_RANGE,BET_INPUT);
    private static final HistoryDbEntity HISTORY_DB_ENTITY = new HistoryDbEntity();
    private static final StatisticRequest STATISTIC_REQUEST = new StatisticRequest();
    private static final double DEGREE = 22.2;
    private static final int[] VALUES = new int[]{1,5,8,2};
    private static final String VALUES_STRING = "1, 5, 8, 2";
    private static final Statistic MAX_STATISTIC = new Statistic();
    private static final Statistic MIN_STATISTIC = new Statistic();
    private static List<Statistic> resultList = new ArrayList<>();

    @InjectMocks
    private RandomServiceImpl randomService;

    @Mock
    private IpRepository ipRepository;

    @Mock
    private HistoryRepository historyRepository;

    @Mock
    private StatisticRepository statisticRepository;

    @Mock
    private StatisticRequestRepository statisticRequestRepository;

    @Before
    public void setUp() {
        ID_IP_ENTITY.setIp(TEST_IP);
        ID_IP_ENTITY.setId(TEST_ID);
        RANGE_LUCK_ENTITY.setRange();
        RANGE_LUCK_ENTITY.setBet();
        STATISTIC_REQUEST.setIp(ID_IP_ENTITY);
        STATISTIC_REQUEST.setCount(5);
        MAX_STATISTIC.setValue(100);
        MAX_STATISTIC.setCount(100);
        MIN_STATISTIC.setValue(1);
        MIN_STATISTIC.setCount(1);
        resultList.add(MAX_STATISTIC);
        resultList.add(MIN_STATISTIC);

        when(ipRepository.findByIp(TEST_IP)).thenReturn(ID_IP_ENTITY);
        when(ipRepository.save(any(IdIpEntity.class))).thenReturn(ID_IP_ENTITY);
        when(historyRepository.save(any(HistoryDbEntity.class))).thenReturn(HISTORY_DB_ENTITY);
        when(statisticRequestRepository.findByIpEquals(ID_IP_ENTITY.getId(),BET_RANGE)).thenReturn(STATISTIC_REQUEST);
        when(statisticRequestRepository.save(STATISTIC_REQUEST)).thenReturn(STATISTIC_REQUEST);
        when(statisticRepository.findAllById(STATISTIC_REQUEST.getId())).thenReturn(resultList);
        when(statisticRepository.save(MIN_STATISTIC)).thenReturn(MIN_STATISTIC);
        when(statisticRepository.save(MAX_STATISTIC)).thenReturn(MAX_STATISTIC);
    }

    @Test
    public void getLuckyTryWithEmptyIp() {
        HistoryDto result = randomService.getLuckyTry(null, null);

        assertNull(result);
    }

    @Test
    public void getLuckyTryWithExistedIp() {
        HistoryDto resultDto = randomService.getLuckyTry(TEST_IP, RANGE_LUCK_ENTITY);

        assertEquals(BET_INPUT, resultDto.getBet());
        verify(ipRepository, never()).save(ID_IP_ENTITY);
    }

    @Test
    public void getLuckyTryWithNotExistedIp() {
        NEW_IP_ENTITY.setIp(TEST_IP);
        NEW_IP_ENTITY.setId(TEST_ID);

        when(ipRepository.findByIp(TEST_IP)).thenReturn(null);
        when(ipRepository.save(any(IdIpEntity.class))).thenReturn(NEW_IP_ENTITY);

        HistoryDto resultDto = randomService.getLuckyTry(TEST_IP, RANGE_LUCK_ENTITY);

        assertEquals(BET_INPUT, resultDto.getBet());

        verify(ipRepository).save(any(IdIpEntity.class));
        verify(ipRepository).findByIp(anyString());
    }

    @Test
    public void getLuckyTryWithExistedIpAndNotExistedStatisticRequestForRoulette() {
        STATISTIC_REQUEST.setRange(BET_RANGE);
        when(statisticRequestRepository.findByIpEquals(ID_IP_ENTITY.getId(),BET_RANGE)).thenReturn(null);
        when(statisticRequestRepository.save(any(StatisticRequest.class))).thenReturn(STATISTIC_REQUEST);

        HistoryDto resultDto = randomService.getLuckyTry(TEST_IP, RANGE_LUCK_ENTITY);

        assertEquals(BET_INPUT, resultDto.getBet());
        verify(ipRepository, never()).save(ID_IP_ENTITY);
    }

    @Test
    public void getLuckyTryWithExistedIpAndNotExistedStatisticRequestForRange() {
        STATISTIC_REQUEST.setRange(BET_RANGE);
        when(statisticRequestRepository.findByIpEquals(ID_IP_ENTITY.getId(),BET_RANGE)).thenReturn(null);
        when(statisticRequestRepository.save(any(StatisticRequest.class))).thenReturn(STATISTIC_REQUEST);

        HistoryDto resultDto = randomService.getLuckyTry(TEST_IP, RANGE_LUCK_ENTITY);

        assertEquals(BET_INPUT, resultDto.getBet());
        verify(ipRepository, never()).save(ID_IP_ENTITY);
        verify(statisticRequestRepository).findByIpEquals(ID_IP_ENTITY.getId(), BET_RANGE);
        verify(statisticRequestRepository).save(any(StatisticRequest.class));
    }

    @Test
    public void getLuckyTryRangeWithEmptyIp() {
        HistoryDto result = randomService.getLuckyTry(null, null);

        assertNull(result);

        verify(ipRepository, never()).save(any(IdIpEntity.class));
        verify(ipRepository, never()).findByIp(anyString());
    }

    @Test
    public void getLuckyTryRangeWithExistedIp() {
        HistoryDto resultDto = randomService.getLuckyTry(TEST_IP, RANGE_LUCK_ENTITY);

        assertEquals(BET_INPUT, resultDto.getBet());
        verify(ipRepository, never()).save(ID_IP_ENTITY);
    }

}
