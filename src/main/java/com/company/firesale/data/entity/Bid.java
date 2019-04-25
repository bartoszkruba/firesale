package com.company.firesale.data.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"user", "auction"})
@Builder
@Entity
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double value;

    @CreationTimestamp
    private LocalDateTime creationTime;

    @ManyToOne
    private Auction auction;

    @ManyToOne
    private User user;

}
