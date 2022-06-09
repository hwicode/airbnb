package com.example.airbnb.business.core.domain.accommodation;


import lombok.Getter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Entity
public class Image {

    public static int IMAGE_URL_NUMBER = 0;
    public static int IMAGE_FILE_NUMBER = 1;

    @Id
    @Column(name = "image_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    private String imageUrl;

    private String fileName;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "accommodation_id")
    private Accommodation accommodation;

    public Image(String imageUrl, String fileName, Accommodation accommodation) {
        this.imageUrl = imageUrl;
        this.fileName = fileName;
        this.accommodation = accommodation;
    }

    public Image(String imageUrl, String fileName) {
        this.imageUrl = imageUrl;
        this.fileName = fileName;
    }

    protected Image() {
    }

    public void registerAccommodation(Accommodation accommodation) {
        this.accommodation = accommodation;
        accommodation.getImages().add(this);
    }

    public static Image createImage(List<String> imageString) {
        return new Image(imageString.get(IMAGE_URL_NUMBER), imageString.get(IMAGE_FILE_NUMBER));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return Objects.equals(imageId, image.imageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageId);
    }
}

