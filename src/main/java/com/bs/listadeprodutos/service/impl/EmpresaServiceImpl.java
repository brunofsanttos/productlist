package com.bs.listadeprodutos.service.impl;

import com.bs.listadeprodutos.dto.EmpresaDto;
import com.bs.listadeprodutos.dto.RetornoPadraoDto;
import com.bs.listadeprodutos.persistence.entity.EmpresaEntity;
import com.bs.listadeprodutos.persistence.repository.EmpresaRepository;
import com.bs.listadeprodutos.service.EmpresaService;
import com.bs.listadeprodutos.util.TratamentoDeTexto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.bs.listadeprodutos.catalog.ErrorCatalog.*;
import static com.bs.listadeprodutos.catalog.WarningsCatalog.RETORNO_DA_CONSULTA;
import static com.bs.listadeprodutos.catalog.WarningsCatalog.SALVO;


@Service
public class EmpresaServiceImpl implements EmpresaService {
    @Autowired
    private EmpresaRepository empresaRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private TratamentoDeTexto tratamentoDeTexto;

    @Override
    public RetornoPadraoDto save(EmpresaDto empresaDto)throws Exception {
        this.validacaoCampos(empresaDto);

        empresaDto.setCnpj(tratamentoDeTexto.RemoveCaracterEspecial(empresaDto.getCnpj()));

        EmpresaEntity empresaEntity = modelMapper.map(empresaDto, EmpresaEntity.class);
        empresaEntity = empresaRepository.save(empresaEntity);

        return new RetornoPadraoDto(SALVO, modelMapper.map(empresaEntity, EmpresaDto.class));
    }

    @Override
    public RetornoPadraoDto consultaPorId(String id) throws Exception {
        if (id == null || id.isEmpty()) {
            throw new Exception(ERRO_ID);
        }

        EmpresaEntity empresaEntity = empresaRepository
                .findById(UUID
                        .fromString(id)).get();

        return new RetornoPadraoDto(RETORNO_DA_CONSULTA, modelMapper.map(empresaEntity, EmpresaDto.class));
    }

    private void validacaoCampos(EmpresaDto empresaDto)throws Exception{
        if(empresaDto.getCnpj() == null || empresaDto.getCnpj().isEmpty()){
            throw new Exception(ERRO_NO_CNPJ);
        }

        if(empresaDto.getRazaoSocial() == null || empresaDto.getRazaoSocial().isEmpty()){
            throw new Exception(ERRO_RAZAO_SOCIAL);
        }

        if(empresaDto.getEmail() == null || empresaDto.getEmail().isEmpty()){
            throw new Exception(ERRO_EMAIL);
        }

        if(empresaRepository.existsByCnpj(empresaDto.getCnpj()) || empresaRepository.existsByEmail(empresaDto.getEmail()) || empresaRepository.existsByRazaoSocial(empresaDto.getRazaoSocial())){
            throw new Exception(CADASTRO_EXISTENTE);
        }
    }
}
