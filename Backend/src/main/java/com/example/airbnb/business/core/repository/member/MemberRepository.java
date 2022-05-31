package com.example.airbnb.business.core.repository.member;

import com.example.airbnb.business.core.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    Optional<Member> findByGithubId(String githubId);
}

