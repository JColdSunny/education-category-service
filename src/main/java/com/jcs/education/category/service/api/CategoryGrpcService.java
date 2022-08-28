package com.jcs.education.category.service.api;

import com.jcs.education.category.service.proto.v1.EducationCategoryServiceGrpc;
import com.jcs.education.category.service.proto.v1.GetCategoriesRequest;
import com.jcs.education.category.service.proto.v1.GetCategoriesResponse;
import com.jcs.education.category.service.proto.v1.GetCategoryDetailsRequest;
import com.jcs.education.category.service.proto.v1.GetCategoryDetailsResponse;
import com.jcs.education.category.service.service.CategoryService;
import io.grpc.stub.StreamObserver;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryGrpcService extends EducationCategoryServiceGrpc.EducationCategoryServiceImplBase {
    CategoryService categoryService;

    @Override
    public void getCategories(GetCategoriesRequest request, StreamObserver<GetCategoriesResponse> responseObserver) {
        GetCategoriesResponse response = categoryService.getCategories(request);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getCategoryDetails(GetCategoryDetailsRequest request, StreamObserver<GetCategoryDetailsResponse> responseObserver) {
        GetCategoryDetailsResponse response = categoryService.getCategoryDetails(request);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
