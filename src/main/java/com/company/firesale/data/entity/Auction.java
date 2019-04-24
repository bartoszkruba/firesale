package com.company.firesale.data.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"category", "bids", "user", "images"})
@Builder
@Entity
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    @CreationTimestamp
    private LocalDateTime openedAt;
    @UpdateTimestamp
    private LocalDateTime setLastUppdate;
    private LocalDateTime closingTime;
    private Double startUpPrice;
    private Double buyOutPrice;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "auction")
    private Set<Image> images = new HashSet<>();

    @Enumerated(value = EnumType.STRING)
    private AuctionStatus status;

    @ManyToOne
    private Category category;

    @OneToMany(mappedBy = "auction")
    @Builder.Default
    private Set<Bid> bids = new HashSet<>();

    public void addImage(Image image) {
        this.images.add(image);
        image.setAuction(this);
    }

}
