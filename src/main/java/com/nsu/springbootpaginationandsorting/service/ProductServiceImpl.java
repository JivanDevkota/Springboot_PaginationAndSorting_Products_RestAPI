package com.nsu.springbootpaginationandsorting.service;

import com.nsu.springbootpaginationandsorting.dto.ProductResponse;
import com.nsu.springbootpaginationandsorting.entity.Product;
import com.nsu.springbootpaginationandsorting.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ProductResponse getProductsWithPagination(int pageNo, int pageSize, String sortBy, String sortDir) {
//        Sort ascending = Sort.by(sortBy).ascending();
//        Sort descending = Sort.by(sortBy).descending();

       Sort sort= sortDir.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize,sort);
        Page<Product> page = productRepository.findAll(pageable);

        List<Product> products = page.getContent();
        products.stream().map(product -> modelMapper.map(product, Product.class)).toList();
        long totalElements = page.getTotalElements();
        long totalPages = page.getTotalPages();
        boolean first = page.isFirst();
        boolean last = page.isLast();

        ProductResponse productResponse = ProductResponse.builder().products(products).totalElements(totalElements)
                .totalPages(totalPages).isFirst(first).isLast(last).pageNo(pageNo).pageSize(pageSize)
                .build();

        return productResponse;
    }
}
