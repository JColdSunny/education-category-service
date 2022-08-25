package com.jcs.education.category.service.service;

import com.jcs.education.category.service.mapper.GetCategoriesMapper;
import com.jcs.education.category.service.proto.v1.Category;
import com.jcs.education.category.service.proto.v1.GetCategoriesRequest;
import com.jcs.education.category.service.proto.v1.GetCategoriesResponse;
import com.jcs.education.category.service.repository.CategoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryService {
    CategoryRepository categoryRepository;
    GetCategoriesMapper getCategoriesMapper;

    public GetCategoriesResponse getCategories(GetCategoriesRequest request) {
        List<Category> categories = categoryRepository.findAll().stream()
                .map(getCategoriesMapper::toCategory)
                .collect(Collectors.toList());

        return GetCategoriesResponse.newBuilder()
                .addAllCategories(categories)
                .build();
    }
}
