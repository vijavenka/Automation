package com.iat.repository;

public interface EpointsRepository {

    String confirmEmail(String token, String password, String firstName, String lastName, String gender, int status);

}