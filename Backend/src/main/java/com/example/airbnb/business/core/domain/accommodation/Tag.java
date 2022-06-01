package com.example.airbnb.business.core.domain.accommodation;

import lombok.Getter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
public class Tag {

    @Id
    @Column(name = "tag_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;
    private String name;

    public Tag(String name) {
        validateTag(name);
        this.name = name;
    }

    private void validateTag(String name) {
        if (name == null || name.length() > 8) {
            throw new IllegalArgumentException("잘못된 태그입니다.");
        }
    }

    protected Tag() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return Objects.equals(tagId, tag.tagId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tagId);
    }
}
