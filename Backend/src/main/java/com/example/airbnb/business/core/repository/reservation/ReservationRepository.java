package com.example.airbnb.business.core.repository.reservation;

import com.example.airbnb.business.core.domain.reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
