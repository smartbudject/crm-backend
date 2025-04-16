package ru.smartbudject.crmbackend.service.pointsales;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.smartbudject.crmbackend.mapper.pointsales.PointSalesMapper;
import ru.smartbudject.crmbackend.model.dto.pointsales.AddPointSalesRequest;
import ru.smartbudject.crmbackend.model.entity.PointSales;
import ru.smartbudject.crmbackend.repository.PointSalesRepository;
import ru.smartbudject.crmbackend.service.PointSalesService;
import ru.smartbudject.crmbackend.service.UserService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
@RequiredArgsConstructor
public class PointSalesServiceImpl implements PointSalesService {

    private final PointSalesRepository pointSalesRepository;
    private final UserService userService;
    private final PointSalesMapper pointSalesMapper;


    @Override
    @Transactional
    public Long addPointSales(final AddPointSalesRequest addPointSalesRequest) {
        final PointSales pointSales = pointSalesMapper.mapAdd(addPointSalesRequest);
        pointSales.setAccount(userService.tryGetAuthenticated()
                .orElseThrow(EntityNotFoundException::new));
        return pointSalesRepository.save(pointSales).getId();
    }


    @Override
    @Transactional
    public Long updatePointSales(final AddPointSalesRequest addPointSalesRequest, final Long id) {
        final PointSales pointSales = pointSalesRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        if (!pointSales.getAccount().equals(userService.tryGetAuthenticated().get())) {
            throw new EntityNotFoundException();
        }

        pointSales.setName(addPointSalesRequest.getName());
        pointSales.setAddress(addPointSalesRequest.getAddress());
        return pointSalesRepository.save(pointSales).getId();
    }

}
