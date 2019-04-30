package com.company.firesale.json_classes;

import com.company.firesale.data.entity.Category;
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
public class CategoryJsonClass {
    private Long id;
    private String name;

    public CategoryJsonClass(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }
}
