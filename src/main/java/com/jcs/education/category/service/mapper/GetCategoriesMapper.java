package com.jcs.education.category.service.mapper;

import com.jcs.education.category.service.entity.CategoryEntity;
import com.jcs.education.category.service.proto.v1.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface GetCategoriesMapper {

    @Mapping(target = "categoryId", source = "id")
    Category toCategory(CategoryEntity entity);

}
