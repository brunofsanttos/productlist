package com.bs.listadeprodutos.service.impl;

import com.bs.listadeprodutos.dto.CompanyDto;

import com.bs.listadeprodutos.dto.StandardReturn;
import com.bs.listadeprodutos.persistence.entity.CompanyEntity;
import com.bs.listadeprodutos.persistence.repository.CompanyRepository;
import com.bs.listadeprodutos.service.CompanyService;
import com.bs.listadeprodutos.util.TextTreatment;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.bs.listadeprodutos.catalog.ErrorCatalog.*;
import static com.bs.listadeprodutos.catalog.WarningsCatalog.RETORNO_DA_CONSULTA;
import static com.bs.listadeprodutos.catalog.WarningsCatalog.SALVO;


@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private TextTreatment textTreatment;

    @Override
    public StandardReturn save(CompanyDto companyDto)throws Exception {
        companyDto.setCnpj(textTreatment.CpfCnpjTreatment(companyDto.getCnpj()));
        CompanyEntity companyEntity = modelMapper.map(companyDto, CompanyEntity.class);

        if(companyEntity.getIdCompany() == null){
            if(companyRepository.existsByCorporateName(companyEntity.getCorporateName())){
                throw new Exception(DADO_INVALIDO);
            }

            if(companyRepository.existsByEmail(companyEntity.getEmail())){
                throw new Exception(ERRO_EMAIL);
            }

            if(companyRepository.existsByCnpj(companyEntity.getCnpj())){
                throw new Exception(CNPJ_CADASTRADO);
            }
        }

        companyEntity = companyRepository.save(companyEntity);

        return new StandardReturn(SALVO, modelMapper.map(companyEntity, CompanyDto.class));
    }

    @Override
    public StandardReturn findById(String id) throws Exception {
        if (id == null || id.isEmpty()) {
            throw new Exception(ERRO_ID);
        }

        CompanyEntity empresaEntity = companyRepository
                .findById(UUID
                        .fromString(id)).get();

        return new StandardReturn(RETORNO_DA_CONSULTA, modelMapper.map(empresaEntity, CompanyDto.class));
    }

    @Override
    public StandardReturn findByCnpj(String cnpj) throws Exception {
        if(!companyRepository.existsByCnpj(cnpj)){
            throw new Exception(ERRO_NO_CNPJ);
        }

        CompanyEntity companyEntity = companyRepository
                .findByCnpj(cnpj);

        return new StandardReturn(
                RETORNO_DA_CONSULTA,
                modelMapper.map(companyEntity, CompanyDto.class));
    }
}
