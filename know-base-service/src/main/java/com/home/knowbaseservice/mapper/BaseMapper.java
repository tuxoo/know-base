package com.home.knowbaseservice.mapper;

public interface BaseMapper<E, D> {

    D toDTO(E entity);
}
