package com.example.springsecurity.model.dto;

import com.example.springsecurity.model.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private long id;
    private String categoryName;

    // Phương thức chuyển đổi từ Category entity sang CategoryDto
    public static CategoryDto from(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .categoryName(category.getCategoryName())
                .build();
    }

    // Phương thức chuyển đổi từ CategoryDto sang Category entity
    public static Category toEntity(CategoryDto dto) {
        return Category.builder()
                .categoryName(dto.getCategoryName())
                .build();
    }
}
