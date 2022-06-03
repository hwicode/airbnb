package com.example.airbnb.business.core.domain.member;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Entity
public class Member {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    private String profileImage;

    private String name;

    private String githubId;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Builder
    public Member(String githubId, String name, String profileImage) {
        this.githubId = githubId;
        this.name = name;
        this.profileImage = profileImage;
        this.role = createNormalRole();
    }

    public Member(String profileImage, String name, String githubId, Gender gender) {
        this.profileImage = profileImage;
        this.name = name;
        this.githubId = githubId;
        this.gender = gender;
        this.role = createHostRole();
    }

    public static Member createMember(String githubId, String profileImage) {
        return new Member(githubId, null, profileImage);
    }

    protected Member() {}

    private Role createNormalRole() {
        return Role.NORMAL;
    }

    private Role createHostRole() {
        return Role.HOST;
    }

    public boolean exists() {
        return this.memberId == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(memberId, member.memberId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId);
    }

    public void updateProfile(String profileImage) {
        this.profileImage = profileImage;
    }
}


