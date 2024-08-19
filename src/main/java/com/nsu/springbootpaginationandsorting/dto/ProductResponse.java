package com.nsu.springbootpaginationandsorting.dto;

import com.nsu.springbootpaginationandsorting.entity.Product;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductResponse {

    private List<Product> products;
    private long totalElements;
    private long totalPages;
    private int pageNo;
    private int pageSize;
    private Boolean isFirst;
    private Boolean isLast;

}
