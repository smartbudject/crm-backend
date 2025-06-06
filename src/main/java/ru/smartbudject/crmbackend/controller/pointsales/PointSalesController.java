package ru.smartbudject.crmbackend.controller.pointsales;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.smartbudject.crmbackend.model.dto.pointsales.AddPointSalesRequest;
import ru.smartbudject.crmbackend.service.PointSalesService;

import lombok.RequiredArgsConstructor;


/**
 * Контроллер для взаимодействия торговыми точками.
 */
@RestController
@RequestMapping("/api/point-sales")
@RequiredArgsConstructor
public class PointSalesController {

    private final PointSalesService pointSalesService;

    /**
     * Метод добавление торговых точек.
     *
     * @param addPointSalesRequest
     * @return id
     */
    @PostMapping()
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Long> addPointSales(
            @RequestBody final AddPointSalesRequest addPointSalesRequest
    ) {
        return ResponseEntity.ok(
                pointSalesService.addPointSales(addPointSalesRequest)
        );
    }


    /**
     * Метод изменения торговых точек.
     *
     * @param addPointSalesRequest - дто
     * @param id                   - торговой точки
     * @return id
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Long> updatePointSales(
            @RequestBody final AddPointSalesRequest addPointSalesRequest,
            @PathVariable final Long id) {
        return ResponseEntity.ok(
                pointSalesService.updatePointSales(addPointSalesRequest, id)
        );
    }
}
