package com.company.firesale.data.entity;


import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"auction"})
@Builder
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filepath;

    @ManyToOne
    @JoinColumn(name = "auction_id")
    private Auction auction;

}
