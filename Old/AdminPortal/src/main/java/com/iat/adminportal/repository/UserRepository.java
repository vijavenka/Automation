package com.iat.adminportal.repository;


import com.iat.adminportal.domain.UserDetails;

public interface UserRepository {
    UserDetails findByEmail(String email);

    UserDetails findById(String id);
}
