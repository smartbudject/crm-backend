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


@RestController
@RequestMapping("/api/point-sales")
@RequiredArgsConstructor
public class PointSalesController {

    private final PointSalesService pointSalesService;

    @PostMapping()
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Long> addPointSales(@RequestBody AddPointSalesRequest addPointSalesRequest) {
        return ResponseEntity.ok(
                pointSalesService.addPointSales(addPointSalesRequest)
        );
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Long> updatePointSales(@RequestBody AddPointSalesRequest addPointSalesRequest, @PathVariable Long id) {
        return ResponseEntity.ok(
                pointSalesService.updatePointSales(addPointSalesRequest, id)
        );
    }
}
