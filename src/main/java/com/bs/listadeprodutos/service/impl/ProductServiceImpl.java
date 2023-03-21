package com.bs.listadeprodutos.service.impl;

import com.bs.listadeprodutos.dto.ProductDto;
import com.bs.listadeprodutos.dto.StandardReturn;
import com.bs.listadeprodutos.persistence.entity.CompanyEntity;
import com.bs.listadeprodutos.persistence.entity.ProductEntity;
import com.bs.listadeprodutos.persistence.repository.CompanyRepository;
import com.bs.listadeprodutos.persistence.repository.ProductRepository;
import com.bs.listadeprodutos.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.bs.listadeprodutos.catalog.ErrorCatalog.*;
import static com.bs.listadeprodutos.catalog.WarningsCatalog.*;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public StandardReturn save(ProductDto productDto) throws Exception {
        this.fieldValidation(productDto);

        Optional<CompanyEntity> companyEntity = companyRepository.findById(UUID.fromString(productDto.getIdCompany()));

        if (companyEntity.isEmpty()) {
            throw new Exception(EMPRESA_NAO_EXISTE);
        }

        ProductEntity product = new ProductEntity(null, companyEntity.get(), productDto.getDescription(), productDto.getUnitPrice());

        ProductEntity productEntity = productRepository
                .save(product);


        return new StandardReturn(SALVO, modelMapper.map(productEntity, ProductDto.class));
    }

    @Override
    public StandardReturn update(ProductDto productDto) throws Exception {
        this.fieldValidation(productDto);

        if (productDto.getIdProduct().isEmpty()) {
            throw new Exception();
        }

        ProductEntity productEntity = modelMapper.map(productDto, ProductEntity.class);

        productEntity = productRepository.findById(productEntity.getIdProduct()).get();
        productEntity.setDescription(productEntity.getDescription());
        productEntity = productRepository.save(productEntity);

        return new StandardReturn(SALVO, modelMapper.map(productEntity, ProductDto.class));
    }

    @Override
    public StandardReturn findById(String idProduct, String idCompany) throws Exception {

        Optional<ProductEntity> productEntity = productRepository
                .findById(UUID.fromString(idProduct));

        if (productEntity.isEmpty()) {
            throw new Exception(NAO_ENCONTRADO);
        }


        return new StandardReturn(RETORNO_DA_CONSULTA, modelMapper.map(productEntity.get(), ProductDto.class));
    }

    @Override
    public StandardReturn findAll(String idCompany) throws Exception {
        if (idCompany.isEmpty()) {
            throw new Exception(ERRO_ID);
        }

        Optional<CompanyEntity> company = companyRepository
                .findById(UUID.fromString(idCompany));

        if (company.isEmpty()) {
            throw new Exception(EMPRESA_NAO_EXISTE);
        }

        List<ProductEntity> productEntityList = productRepository.findAllByCompany(company.get());

        if (productEntityList.isEmpty()) {
            throw new Exception(NAO_ENCONTRADO);
        }

        return new StandardReturn(RETORNO_DA_CONSULTA, productEntityList
                .stream()
                .map(element -> modelMapper.map(element, ProductDto.class))
                .collect(Collectors.toList()));
    }

    @Override
    public StandardReturn delete(String idProduct, String idCompany) throws Exception {
        if (!productRepository.existsById(UUID.fromString(idProduct))) {
            throw new Exception(ERRO_ID);
        }

        if (!companyRepository.existsById(UUID.fromString(idCompany))) {
            throw new Exception(ERRO_ID);
        }

        Optional<ProductEntity> productEntityOptional = productRepository.findById(UUID.fromString(idProduct));

        if (productEntityOptional.isEmpty()) {
            throw new Exception(NAO_ENCONTRADO);
        }


        productRepository.delete(productEntityOptional.get());

        return new StandardReturn(SUCESSO, DELETE);
    }

    private void fieldValidation(ProductDto productDto) throws Exception {
        if (productDto.getIdCompany() == null || productDto.getIdCompany().isEmpty()) {
            throw new Exception(ID_COMPANY);
        }

        if (productDto.getDescription() == null || productDto.getDescription().isEmpty()) {
            throw new Exception(DESCRIPTION);
        }
    }
}
