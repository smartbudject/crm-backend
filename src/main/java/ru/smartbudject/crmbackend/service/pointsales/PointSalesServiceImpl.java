package ru.smartbudject.crmbackend.service.pointsales;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.smartbudject.crmbackend.mapper.pointsales.PointSalesMapper;
import ru.smartbudject.crmbackend.model.dto.pointsales.AddPointSalesRequest;
import ru.smartbudject.crmbackend.model.entity.PointSales;
import ru.smartbudject.crmbackend.repository.PointSalesRepository;
import ru.smartbudject.crmbackend.service.PointSalesService;
import ru.smartbudject.crmbackend.service.AccountService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * Сервис для взаимодействия с торговыми точками.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class PointSalesServiceImpl implements PointSalesService {

    private final PointSalesRepository pointSalesRepository;
    private final AccountService accountService;
    private final PointSalesMapper pointSalesMapper;


    /**
     * Метод добавления торговой точки.
     * @param addPointSalesRequest
     * @return id
     */
    @Override
    @Transactional
    public Long addPointSales(final AddPointSalesRequest addPointSalesRequest) {
        final PointSales pointSales = pointSalesMapper.mapAdd(addPointSalesRequest);
        pointSales.setAccount(accountService.tryGetAuthenticated()
                .orElseThrow(EntityNotFoundException::new));
        return pointSalesRepository.save(pointSales).getId();
    }


    /**
     * Метод обновление торговой точки.
     * @param addPointSalesRequest
     * @param id
     * @return id
     */
    @Override
    @Transactional
    public Long updatePointSales(final AddPointSalesRequest addPointSalesRequest, final Long id) {
        final PointSales pointSales = pointSalesRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        if (!pointSales.getAccount().equals(accountService.tryGetAuthenticated().get())) {
            throw new EntityNotFoundException();
        }

        pointSales.setName(addPointSalesRequest.getName());
        pointSales.setAddress(addPointSalesRequest.getAddress());
        return pointSalesRepository.save(pointSales).getId();
    }

}
