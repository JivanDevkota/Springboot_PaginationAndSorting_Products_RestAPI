package com.nsu.springbootpaginationandsorting.service;

import com.nsu.springbootpaginationandsorting.dto.ProductResponse;

public interface ProductService {
    ProductResponse getProductsWithPagination(int pageNo, int pageSize, String sortBy, String sortDir);
}
