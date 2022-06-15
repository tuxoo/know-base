package com.home.knowbaseservice.cache;

import com.home.knowbaseservice.model.entity.UserDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDTOCache extends CrudRepository<UserDTO, String> {
}
