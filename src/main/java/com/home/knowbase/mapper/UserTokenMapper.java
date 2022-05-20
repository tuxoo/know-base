package com.home.knowbase.mapper;

import com.home.knowbase.dto.UserTokenDTO;
import com.home.knowbase.entity.UserToken;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserTokenMapper {

    UserTokenDTO toDTO(UserToken userToken);
}
