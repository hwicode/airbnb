package com.example.airbnb.business.core.repository.member;

import com.example.airbnb.business.core.domain.member.Wish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishRepository extends JpaRepository<Wish, Long> {
}
