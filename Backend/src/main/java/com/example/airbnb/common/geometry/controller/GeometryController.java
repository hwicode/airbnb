package com.example.airbnb.common.geometry.controller;

import com.example.airbnb.common.geometry.objects.MapDataProvider;
import com.example.airbnb.common.geometry.objects.MapDataRegistration;
import com.example.airbnb.common.geometry.objects.Position;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/geometry")
@RequiredArgsConstructor
public class GeometryController {

    private final MapDataRegistration mapDataRegistration;
    private final MapDataProvider mapDataProvider;

    @GetMapping("/{address}")
    public Position getGeometryData(@PathVariable("address") String address) {
        return mapDataProvider.getGeometryData(address, mapDataRegistration);
    }

}
