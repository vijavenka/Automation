package com.iat.stepdefs

import com.iat.Config
import com.iat.domain.EcardAward
import com.iat.repository.UserRepository
import com.iat.repository.impl.EcardsAwardRepositoryImpl
import com.iat.repository.impl.UserRepositoryImpl

import static com.iat.Config.reason1Id
import static com.iat.Config.template1Id
import static cucumber.api.groovy.EN.Given

Given(~/^(\d+) Ecards requests is created to populate test user (.+?) history$/) { int newEcardsNumber, String whichHistoryToPopulate ->
    for (int i = 0; i < newEcardsNumber; i++) {
        System.out.println("Ecard $i created")
        UserRepository userRepository = new UserRepositoryImpl()
        if (whichHistoryToPopulate == 'sent') {
            def body = new EcardAward(reasonId: reason1Id, templateId: template1Id, message: "UI_automated_tests_message2",
                    pointsValue: 75, usersKey: [userRepository.findByEmail(Config.epointsUser2).getUuid()], cc: ["emailwybitnietestowy@gmail.com"])
            pointsAwardId = new EcardsAwardRepositoryImpl().setEcardsAward(body, Config.epointsUser, Config.epointsUserPassword)
        } else if (whichHistoryToPopulate == 'received') {
            def body = new EcardAward(reasonId: reason1Id, templateId: template1Id, message: "UI_automated_tests_message2",
                    pointsValue: 75, usersKey: [userRepository.findByEmail(Config.epointsUser).getUuid()], cc: ["emailwybitnietestowy@gmail.com"])
            pointsAwardId = new EcardsAwardRepositoryImpl().setEcardsAward(body, Config.epointsUser2, Config.epointsUser2Password)
        }
    }
}