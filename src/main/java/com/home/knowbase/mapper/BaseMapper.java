package com.home.knowbase.mapper;

public interface BaseMapper<E, D> {

    D toDTO(E entity);
}
