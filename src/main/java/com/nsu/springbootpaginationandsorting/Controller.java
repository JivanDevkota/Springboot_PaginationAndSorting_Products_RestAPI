package com.nsu.springbootpaginationandsorting;

import com.nsu.springbootpaginationandsorting.dto.ProductResponse;
import com.nsu.springbootpaginationandsorting.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class Controller {

    @Autowired
    private ProductService productService;

    @GetMapping("page-products")
    public ResponseEntity<?>getProductPaginate(@RequestParam(name="pageNo",defaultValue = "0") int pageNo,
                                               @RequestParam(name = "pageSize",defaultValue = "10") int pageSize,
                                               @RequestParam(name = "sortBy" ,defaultValue = "id")String sortBy,
                                               @RequestParam(name = "sortDir",defaultValue = "asc")String sortDir
    ){
        ProductResponse productResponse=null;
        try {
        productResponse=productService.getProductsWithPagination(pageNo,pageSize,sortBy,sortDir);
        if(ObjectUtils.isEmpty(productResponse)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }
}
