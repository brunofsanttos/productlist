package com.bs.listadeprodutos.service.impl;

import com.bs.listadeprodutos.dto.PermissionDto;
import com.bs.listadeprodutos.dto.StandardReturn;
import com.bs.listadeprodutos.exceptions.InformetionExecption;
import com.bs.listadeprodutos.persistence.entity.CompanyEntity;
import com.bs.listadeprodutos.persistence.entity.PermissionEntity;
import com.bs.listadeprodutos.persistence.repository.CompanyRepository;
import com.bs.listadeprodutos.persistence.repository.PermissionRepository;
import com.bs.listadeprodutos.service.PermissionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.bs.listadeprodutos.catalog.WarningsCatalog.RETORNO_DA_CONSULTA;
import static com.bs.listadeprodutos.catalog.WarningsCatalog.SALVO;
import static com.bs.listadeprodutos.catalog.ErrorCatalog.NAO_ENCONTRADO;
import static com.bs.listadeprodutos.catalog.ErrorCatalog.OBRIGATORIO;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public StandardReturn save(PermissionDto permissionDto) throws InformetionExecption {

        Optional<CompanyEntity> company = companyRepository
                .findById(UUID.fromString(permissionDto.getIdCompany()));

        if(company.isEmpty()){
            throw new InformetionExecption(OBRIGATORIO);
        }

        PermissionEntity permissionEntity = new PermissionEntity(null, company.get() , permissionDto.getDescription(), permissionDto.getStatus());

        permissionEntity = permissionRepository.save(permissionEntity);

        return new StandardReturn(SALVO, modelMapper.map(permissionEntity, PermissionDto.class));
    }

    @Override
    public StandardReturn findByIdPermission(String idPermission) throws InformetionExecption {
        if(idPermission == null || idPermission.isEmpty()){
            throw new InformetionExecption(OBRIGATORIO);
        }

        Optional<PermissionEntity> permissionEntity = permissionRepository
                .findById(UUID.fromString(idPermission));

        if(permissionEntity.isEmpty()){
            throw new InformetionExecption(NAO_ENCONTRADO);
        }

        return new StandardReturn(RETORNO_DA_CONSULTA, modelMapper.map(permissionEntity.get(), PermissionDto.class));
    }

    @Override
    public StandardReturn findByIdCompany(String idCompany) throws InformetionExecption {
        Optional<PermissionEntity> permissionEntityList = permissionRepository
                .findByCompany(UUID.fromString(idCompany));

        if(permissionEntityList.isEmpty()){
            throw new InformetionExecption(NAO_ENCONTRADO);
        }

        return new StandardReturn(RETORNO_DA_CONSULTA, permissionEntityList
                .stream()
                .map(permissionEntity -> modelMapper
                        .map(permissionEntity, PermissionDto.class))
                .collect(Collectors.toList()));
    }

    @Override
    public StandardReturn enableDesable() throws InformetionExecption {
        return null;
    }
}
