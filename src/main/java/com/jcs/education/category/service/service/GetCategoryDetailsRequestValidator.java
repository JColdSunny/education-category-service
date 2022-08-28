package com.jcs.education.category.service.service;

import com.jcs.education.category.service.exception.RequestValidationException;
import com.jcs.education.category.service.proto.v1.GetCategoryDetailsRequest;

public class GetCategoryDetailsRequestValidator {

    private GetCategoryDetailsRequestValidator() {
        throw new UnsupportedOperationException("ValidateGetCategoryDetailsRequest is a static utility class");
    }

    static void validateRequest(GetCategoryDetailsRequest request) {
        if (request.getCategoryId() == 0) {
            throw new RequestValidationException("category_id must be specified");
        }
    }

}
