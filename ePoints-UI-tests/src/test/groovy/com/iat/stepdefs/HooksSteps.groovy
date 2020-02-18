package com.iat.stepdefs

import com.iat.Config
import com.iat.repository.UserRepository
import com.iat.repository.impl.UserRepositoryImpl
import com.iat.stepdefs.utils.DataExchanger

import static cucumber.api.groovy.Hooks.After
import static cucumber.api.groovy.Hooks.Before

UserRepository userRepository = new UserRepositoryImpl()
DataExchanger dataExchanger = DataExchanger.getInstance()

After("@deleteUserAfterTest") {
    String userEmail = dataExchanger.getEmail()
    String userUuid = userRepository.findByEmail(userEmail).getUuid()
    println "Deleting user: $userEmail $userUuid"
    userRepository.deletUserFromDynamoAndPointsManager(userUuid)
}

Before("@deleteFacebookUserBeforeTest") {
    String userEmail = Config.facebookUser
    String userUuid = userRepository.findByEmail(userEmail).getUuid()
    println "Deleting user: $userEmail $userUuid"
    if (userUuid != null)
        new UserRepositoryImpl().deletUserFromDynamoAndPointsManager(new UserRepositoryImpl().findByEmail(userEmail).getUuid())
}