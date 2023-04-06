package com.bs.listadeprodutos.service;

import com.bs.listadeprodutos.dto.PermissionDto;
import com.bs.listadeprodutos.dto.StandardReturn;
import com.bs.listadeprodutos.exceptions.InformetionExecption;

public interface PermissionService {
    StandardReturn save(PermissionDto permissionDto) throws InformetionExecption;
    StandardReturn findByIdPermission(String idPermission) throws InformetionExecption;
    StandardReturn findByIdCompany(String idCompany) throws InformetionExecption;
    StandardReturn enableDesable() throws InformetionExecption;
}
