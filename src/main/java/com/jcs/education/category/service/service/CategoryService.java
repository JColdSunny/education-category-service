package com.jcs.education.category.service.service;

import com.jcs.education.category.service.exception.EntityNotFoundException;
import com.jcs.education.category.service.mapper.GetCategoriesMapper;
import com.jcs.education.category.service.proto.v1.Category;
import com.jcs.education.category.service.proto.v1.GetCategoriesRequest;
import com.jcs.education.category.service.proto.v1.GetCategoriesResponse;
import com.jcs.education.category.service.proto.v1.GetCategoryDetailsRequest;
import com.jcs.education.category.service.proto.v1.GetCategoryDetailsResponse;
import com.jcs.education.category.service.repository.CategoryRepository;
import com.jcs.education.course.service.proto.v1.EducationCourseServiceGrpc;
import com.jcs.education.course.service.proto.v1.GetCoursesRequest;
import com.jcs.education.course.service.proto.v1.GetCoursesResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryService {
    final CategoryRepository categoryRepository;
    final GetCategoriesMapper getCategoriesMapper;

    @GrpcClient("education-category-service")
    EducationCourseServiceGrpc.EducationCourseServiceBlockingStub courseService;

    public GetCategoriesResponse getCategories(GetCategoriesRequest request) {
        List<Category> categories = categoryRepository.findAll().stream()
                .map(getCategoriesMapper::toCategory)
                .collect(Collectors.toList());

        return GetCategoriesResponse.newBuilder()
                .addAllCategories(categories)
                .build();
    }

    public GetCategoryDetailsResponse getCategoryDetails(GetCategoryDetailsRequest request) {
        GetCategoryDetailsRequestValidator.validateRequest(request);

        if (!categoryRepository.existsById(request.getCategoryId())) {
            throw new EntityNotFoundException(String.format("Failed to found category by id: %d", request.getCategoryId()));
        }

        GetCoursesRequest getCoursesRequest = GetCoursesRequest.newBuilder()
                .setCategoryId(request.getCategoryId())
                .build();
        GetCoursesResponse getCoursesResponse = courseService.getCourses(getCoursesRequest);

        return GetCategoryDetailsResponse.newBuilder()
                .addAllCourses(getCoursesResponse.getCoursesList())
                .build();
    }

}
