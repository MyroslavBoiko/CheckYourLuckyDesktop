package sample.backend.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import sample.backend.entity.IdIpEntity;
import sample.backend.repository.IpRepository;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class IpServiceImplTest {

    private static final String TEST_IP = "000.000.000.000";
    private static final Long TEST_ID = 99999L;
    private static final IdIpEntity ID_IP_ENTITY = new IdIpEntity();

    @InjectMocks
    private IpServiceImpl ipService;

    @Mock
    private IpRepository ipRepository;

    @Before
    public void setUp() throws Exception {
        ID_IP_ENTITY.setIp(TEST_IP);
        ID_IP_ENTITY.setId(TEST_ID);

        when(ipRepository.save(any(IdIpEntity.class))).thenReturn(ID_IP_ENTITY);
    }

    @Test
    public void saveNotExistedIp() {
        when(ipRepository.findByIp(TEST_IP)).thenReturn(null);
        ipService.saveIp(TEST_IP);

        verify(ipRepository).findByIp(TEST_IP);
        verify(ipRepository).save(any(IdIpEntity.class));
    }

    @Test
    public void saveExistedIp() {
        when(ipRepository.findByIp(TEST_IP)).thenReturn(ID_IP_ENTITY);
        ipService.saveIp(TEST_IP);

        verify(ipRepository).findByIp(TEST_IP);
        verify(ipRepository, never()).save(any(IdIpEntity.class));
    }

}
