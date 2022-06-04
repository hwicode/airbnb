package com.example.airbnb.business.core.repository.accommodation;

import com.example.airbnb.business.core.domain.accommodation.Accommodation;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {


    @EntityGraph(attributePaths = {"member", "images"})
    Optional<Accommodation> findByAccommodationId(Long id);

    @Query("select a from Accommodation a where FUNCTION('ST_Distance_Sphere', a.location.point, function('ST_GeomFromText', :point)) <= :searchRange")
    List<Accommodation> findAccommodationByPoint(@Param("point") String point, @Param("searchRange") String searchRange);

}
