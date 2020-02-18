package com.iat.adminportal.repository.impl;


import com.iat.adminportal.EnvironmentVariables;
import com.iat.adminportal.domain.UserDetails;
import com.iat.adminportal.repository.UserRepository;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.get;

public class UserRepositoryImpl implements UserRepository {

    private Response getUserById(String uuid) {
        //by default db=false this mean search in dynamo db
        return get(EnvironmentVariables.getDoormanUrl() + "/users/" + uuid);
    }

    public UserDetails findByEmail(String email) {
        ValidatableResponse validatableResponse = get(EnvironmentVariables.getDoormanUrl() + "/users?email=" + email)
                .then()
                .statusCode(200);
        return validatableResponse.extract().response().as(UserDetails.class);
    }

    @Override
    public UserDetails findById(String uuid) {
        return get(EnvironmentVariables.getDoormanUrl() + "/users/" + uuid).getBody().as(UserDetails.class);
    }
}