package com.home.knowbaseservice.model.mapper;

public interface BaseMapper<E, D> {

    D toDTO(E entity);
}
