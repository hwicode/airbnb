package com.example.airbnb.business.core.domain.member;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Member {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer memberId;

    private String profileImage;

    private String name;

    private String githubId;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Bio bio;

    public Member(String profileImage, String name, String githubId) {
        this.profileImage = profileImage;
        this.name = name;
        this.githubId = githubId;
        this.role = createNormalRole();
    }

    public Member(String profileImage, String name, String githubId, Bio bio) {
        this.profileImage = profileImage;
        this.name = name;
        this.githubId = githubId;
        this.bio = bio;
        this.role = createHostRole();
    }

    protected Member() {};

    private Role createNormalRole() {
        return Role.NORMAL;
    }

    private Role createHostRole() {
        return Role.NORMAL;
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
}


