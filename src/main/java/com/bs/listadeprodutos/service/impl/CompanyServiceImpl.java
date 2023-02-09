package com.bs.listadeprodutos.service.impl;

import com.bs.listadeprodutos.dto.CompanyDto;
import com.bs.listadeprodutos.dto.RetornoPadraoDto;
import com.bs.listadeprodutos.persistence.entity.CompanyEntity;
import com.bs.listadeprodutos.persistence.repository.CompanyRepository;
import com.bs.listadeprodutos.service.CompanyService;
import com.bs.listadeprodutos.util.TratamentoDeTexto;
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
    private TratamentoDeTexto tratamentoDeTexto;

    @Override
    public RetornoPadraoDto save(CompanyDto companyDto)throws Exception {
        this.fieldValidation(companyDto);

        companyDto.setCnpj(tratamentoDeTexto.RemoveCaracterEspecial(companyDto.getCnpj()));

        CompanyEntity companyEntity = modelMapper.map(companyDto, CompanyEntity.class);
        companyEntity = companyRepository.save(companyEntity);

        return new RetornoPadraoDto(SALVO, modelMapper.map(companyEntity, CompanyDto.class));
    }

    @Override
    public RetornoPadraoDto findById(String id) throws Exception {
        if (id == null || id.isEmpty()) {
            throw new Exception(ERRO_ID);
        }

        CompanyEntity empresaEntity = companyRepository
                .findById(UUID
                        .fromString(id)).get();

        return new RetornoPadraoDto(RETORNO_DA_CONSULTA, modelMapper.map(empresaEntity, CompanyDto.class));
    }

    private void fieldValidation(CompanyDto companyDto)throws Exception{
        if(companyDto.getCnpj() == null || companyDto.getCnpj().isEmpty()){
            throw new Exception(ERRO_NO_CNPJ);
        }

        if(companyDto.getRazaoSocial() == null || companyDto.getRazaoSocial().isEmpty()){
            throw new Exception(ERRO_RAZAO_SOCIAL);
        }

        if(companyDto.getEmail() == null || companyDto.getEmail().isEmpty()){
            throw new Exception(ERRO_EMAIL);
        }

        if(companyRepository.existsByCnpj(companyDto.getCnpj()) || companyRepository.existsByEmail(companyDto.getEmail()) || companyRepository.existsByRazaoSocial(companyDto.getRazaoSocial())){
            throw new Exception(CADASTRO_EXISTENTE);
        }
    }
}
