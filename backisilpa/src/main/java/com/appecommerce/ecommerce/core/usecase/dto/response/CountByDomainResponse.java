package com.appecommerce.ecommerce.core.usecase.dto.response;

import lombok.Data;


@Data
public class CountByDomainResponse {
    private String domain;
    private long count;

    public CountByDomainResponse(String domain, long count){
        this.domain = domain;
        this.count = count;
    }
}
