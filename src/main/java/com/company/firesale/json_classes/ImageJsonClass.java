package com.company.firesale.json_classes;

import com.company.firesale.data.entity.Image;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ImageJsonClass {

    private Long id;
    private String filepath;
    private Long auctionId;

    public ImageJsonClass(Image image) {
        this.id = image.getId();
        this.filepath = image.getFilepath();
        this.auctionId = image.getAuction().getId();
    }

}
