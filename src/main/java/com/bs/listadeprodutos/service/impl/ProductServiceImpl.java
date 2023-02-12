package com.bs.listadeprodutos.service.impl;

import com.bs.listadeprodutos.dto.ProductDto;
import com.bs.listadeprodutos.dto.StandardReturn;
import com.bs.listadeprodutos.persistence.entity.ProductEntity;
import com.bs.listadeprodutos.persistence.repository.ProductRepository;
import com.bs.listadeprodutos.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.bs.listadeprodutos.catalog.ErrorCatalog.*;
import static com.bs.listadeprodutos.catalog.WarningsCatalog.SALVO;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public StandardReturn save(ProductDto productDto) throws Exception {
        this.fieldValidation(productDto);

        ProductEntity productEntity = productRepository
                .save(modelMapper.map(productDto, ProductEntity.class));

        return new StandardReturn(SALVO, modelMapper.map(productEntity, ProductDto.class));
    }

    @Override
    public StandardReturn update(ProductDto productDto) throws Exception {
        this.fieldValidation(productDto);

        if(productDto.getIdProduct().isEmpty()){
           throw new Exception();
        }

        ProductEntity productEntity = modelMapper.map(productDto, ProductEntity.class);

        productEntity = productRepository.findById(productEntity.getIdProduct()).get();
        productEntity.setDescription(productEntity.getDescription());
        productEntity = productRepository.save(productEntity);

        return new StandardReturn(SALVO, modelMapper.map(productEntity, ProductDto.class));
    }

    @Override
    public StandardReturn findById(String idProduct) throws Exception {

        return null;
    }

    @Override
    public StandardReturn findAll(String idCompany) throws Exception {
        return null;
    }

    @Override
    public StandardReturn delete(String idProduct, String idCompany) throws Exception {
        return null;
    }

    private void fieldValidation(ProductDto productDto)throws Exception{
        if(productDto.getIdCompany() == null || productDto.getIdCompany() .isEmpty()){
            throw new Exception(ID_COMPANY);
        }

        if(productDto.getDescription() == null || productDto.getDescription().isEmpty()){
            throw new Exception(DESCRIPTION);
        }
    }
}
