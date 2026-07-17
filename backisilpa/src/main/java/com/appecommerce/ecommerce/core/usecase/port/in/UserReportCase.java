package com.appecommerce.ecommerce.core.usecase.port.in;

import com.appecommerce.ecommerce.core.usecase.dto.response.UserReportResponse;

public interface UserReportCase {
    UserReportResponse getReport(String emailType, String role, int page, int size);
}
