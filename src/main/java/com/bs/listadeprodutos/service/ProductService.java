package com.bs.listadeprodutos.service;

import com.bs.listadeprodutos.dto.ProductDto;
import com.bs.listadeprodutos.dto.StandardReturn;

public interface ProductService {
    StandardReturn save(ProductDto productDto) throws Exception;
    StandardReturn update(ProductDto productDto)throws Exception;
    StandardReturn findById(String idProduct)throws Exception;
    StandardReturn findAll(String idCompany)throws Exception;
    StandardReturn delete(String idProduct, String idCompany)throws Exception;
}
