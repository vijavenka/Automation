package com.iat.stepdefs

import com.iat.Config
import com.iat.domain.createNewUser
import com.iat.domain.ecardAward
import com.iat.repository.impl.CreateUserRepositoryImpl
import com.iat.repository.impl.EcardsAwardRepositoryImpl
import com.iat.repository.impl.UserRepositoryImpl
import com.iat.stepdefs.utils.JdbcDatabaseConnector

import static cucumber.api.groovy.EN.Given

def mySQLConnectorIA = new JdbcDatabaseConnector(Config.mysqlConnectionPoolIatAdmin)
def mySQLConnectorPM = new JdbcDatabaseConnector(Config.mysqlConnectionPoolPointsManager)

Given(~/^(\d+) Ecards requests is created$/) { int newEcardsNumber ->
    for (int i = 0; i < newEcardsNumber; i++) {
        System.out.println(i)
        body = new ecardAward(Config.reason1Id, Config.template1Id, "UI_automated_tests_message2", "55", [new UserRepositoryImpl().findByEmail(Config.associatedUser1).uuid], ["emailwybitnietestowy@gmail.com"])
        pointsAwardId = new EcardsAwardRepositoryImpl().setEcardsAward(body, Config.superAdminLogin, Config.superAdminPassword)
    }
}

Given(~/^All previous data are removed$/) { ->
    //remove points allocations
    mySQLConnectorIA.execute("DELETE FROM ECardsPartnerBucket WHERE externalId ='22102016'")
    mySQLConnectorIA.execute("DELETE FROM ECardsDepartmentBucket  WHERE partnerId = '22102016'")
    mySQLConnectorIA.execute("DELETE FROM ECardsPointsAllocation WHERE partnerId = '22102016'")

    //remove authorities
    mySQLConnectorIA.execute("DELETE FROM UserAuthority WHERE userId IN (SELECT id FROM User WHERE partnerId ='22102016')")
    mySQLConnectorIA.execute("DELETE FROM UserAuthority WHERE userId IN (SELECT id FROM User WHERE partnerId ='22102017')")

    //remove users
    mySQLConnectorIA.execute("DELETE FROM User WHERE partnerId = '22102016'")
    mySQLConnectorIA.execute("DELETE FROM User WHERE partnerId = '22102017'") //wizard admin, separate company

    new UserRepositoryImpl().deletUserFromDynamoAndPointsManager(new UserRepositoryImpl().findByEmail('ui_automated_tests_admin@wp.pl').uuid)
    new UserRepositoryImpl().deletUserFromDynamoAndPointsManager(new UserRepositoryImpl().findByEmail('ui_automated_tests_manager@wp.pl').uuid)
    new UserRepositoryImpl().deletUserFromDynamoAndPointsManager(new UserRepositoryImpl().findByEmail('ui_automated_tests_wizard_test@wp.pl').uuid)
    new UserRepositoryImpl().deletUserFromDynamoAndPointsManager(new UserRepositoryImpl().findByEmail('profiletestsdmin@wp.pl').uuid)
    new UserRepositoryImpl().deletUserFromDynamoAndPointsManager(new UserRepositoryImpl().findByEmail('emailfortest@wp.pl').uuid)
    new UserRepositoryImpl().deletUserFromDynamoAndPointsManager(new UserRepositoryImpl().findByEmail('emailfortest1@wp.pl').uuid)
    new UserRepositoryImpl().deletUserFromDynamoAndPointsManager(new UserRepositoryImpl().findByEmail('emailfortest2@wp.pl').uuid)
    new UserRepositoryImpl().deletUserFromDynamoAndPointsManager(new UserRepositoryImpl().findByEmail('emailfortest3@wp.pl').uuid)
    new UserRepositoryImpl().deletUserFromDynamoAndPointsManager(new UserRepositoryImpl().findByEmail('emailfortest4@wp.pl').uuid)
    new UserRepositoryImpl().deletUserFromDynamoAndPointsManager(new UserRepositoryImpl().findByEmail('emailfortest5@wp.pl').uuid)
    new UserRepositoryImpl().deletUserFromDynamoAndPointsManager(new UserRepositoryImpl().findByEmail('emailfortest6@wp.pl').uuid)
    new UserRepositoryImpl().deletUserFromDynamoAndPointsManager(new UserRepositoryImpl().findByEmail('notVerified@wp.pl').uuid)
    new UserRepositoryImpl().deletUserFromDynamoAndPointsManager(new UserRepositoryImpl().findByEmail('emailfortest8@wp.pl').uuid)
    new UserRepositoryImpl().deletUserFromDynamoAndPointsManager(new UserRepositoryImpl().findByEmail('emailfortest9@wp.pl').uuid)
    new UserRepositoryImpl().deletUserFromDynamoAndPointsManager(new UserRepositoryImpl().findByEmail('emailfortest10@wp.pl').uuid)
    new UserRepositoryImpl().deletUserFromDynamoAndPointsManager(new UserRepositoryImpl().findByEmail('emailfortest11@wp.pl').uuid)

    //remove settings
    mySQLConnectorIA.execute("DELETE FROM ECardsSettings WHERE partnerId = '22102016'")
    mySQLConnectorIA.execute("DELETE FROM ECardsSettings WHERE partnerId = '22102017'")

    //remove reasons
    mySQLConnectorIA.execute("DELETE FROM ECardsReason WHERE partnerId = '22102016'")
    mySQLConnectorIA.execute("DELETE FROM ECardsReason WHERE partnerId = '22102017'")

    //remove reasons tags
    mySQLConnectorPM.execute("DELETE FROM Tag WHERE partnerId = '22102016'")
    mySQLConnectorPM.execute("DELETE FROM Tag WHERE partnerId = '22102017'")

    //remove templates
    mySQLConnectorIA.execute("DELETE FROM ECardsTemplate WHERE partnerId = '22102016'")
    mySQLConnectorIA.execute("DELETE FROM ECardsTemplate WHERE partnerId = '22102017'")

    //remove departments
    mySQLConnectorPM.execute("DELETE FROM Department WHERE partnerId = '22102016'")
    mySQLConnectorPM.execute("DELETE FROM Department WHERE partnerId = '22102017'")

    //remove virtual groups
    mySQLConnectorPM.execute("DELETE FROM VirtualGroup WHERE partnerId = '22102016'")
    mySQLConnectorPM.execute("DELETE FROM VirtualGroup WHERE partnerId = '22102017'")

    //remove partners
    mySQLConnectorPM.execute("DELETE FROM Partner WHERE id = '22102016'")
    mySQLConnectorPM.execute("DELETE FROM Partner WHERE id = '22102017'")

    //remove groups
    mySQLConnectorPM.execute("DELETE FROM PartnersGroup WHERE id = '7777'")
    mySQLConnectorPM.execute("DELETE FROM PartnersGroup WHERE id = '7778'")
}

Given(~/^Groups are created$/) { ->
    mySQLConnectorPM.execute("INSERT INTO PartnersGroup (`id`, `name`, `shortName`, `hrAdminConfig`, `ecardConfig`, `eCardsLevel`, `ecardsEpoints`) VALUES ('7777', 'Ecards UI Tests', 'ecardsuitests', 1, 1, 'none', 1);")
    mySQLConnectorPM.execute("INSERT INTO PartnersGroup (`id`, `name`, `shortName`, `hrAdminConfig`, `ecardConfig`, `eCardsLevel`, `ecardsEpoints`) VALUES ('7778', 'Ecards UI Tests 2', 'ecardsuitests2', 1, 1, 'none', 1);")
}

Given(~/^Partners are created$/) { ->
    mySQLConnectorPM.execute("INSERT INTO Partner (`id`, `createdAt`, `updatedAt`, `currentTotal`, `accessKey`, `name`, `paymentType`, `shortName`, `active`, `siteUrl`, `logoUrl`, `version`, `email`, `warningThreshold`, `groupId`, `registrationPoints`, `ecardsVisible`) VALUES ('22102016', '2014-07-25 11:47:07', '2014-07-25 11:47:07', '0', 'kb_ui_automated_tests', 'KB Ui Automated Tests', 'POST_PAY', 'kb_ui_automated_tests_1', 1, 'http://NIE.USUWAJ.MNIE.NASTEPNYM.RAZEM.COM', 'http://bdl-public-images-qa.s3.amazonaws.com/chain-logos/chain_594de950-a5c9-459d-8989-fd07347950c5_31000b1971aa57aa2700710f06d23d06.gif', '1', 'NIE@USUWAC', '0', '7777', 1, 1);")
    mySQLConnectorPM.execute("INSERT INTO Partner (`id`, `createdAt`, `updatedAt`, `currentTotal`, `accessKey`, `name`, `paymentType`, `shortName`, `active`, `siteUrl`, `logoUrl`, `version`, `email`, `warningThreshold`, `groupId`, `registrationPoints`, `ecardsVisible`) VALUES ('22102017', '2014-07-25 11:47:07', '2014-07-25 11:47:07', '0', 'kb_ui_automated_tests2', 'KB Ui Automated Tests2', 'POST_PAY', 'kb_ui_automated_tests_2', 1, 'http://NIE.USUWAJ.MNIE.NASTEPNYM.RAZEM.COM', 'http://bdl-public-images-qa.s3.amazonaws.com/chain-logos/chain_594de950-a5c9-459d-8989-fd07347950c5_31000b1971aa57aa2700710f06d23d06.gif', '1', 'NIE@USUWAC', '0', '7778', 1, 1);")
}

Given(~/^Virtual groups are created$/) { ->
    mySQLConnectorPM.execute("INSERT INTO VirtualGroup (`id`, `createdAt`, `name`, `partnerId`, `ownerType`) VALUES ('1550', '2016-09-05 13:26:19', 'KB Ui Automated Tests', '22102016', 'partner');")
}

Given(~/^Departments are created$/) { ->
    mySQLConnectorPM.execute("INSERT INTO Department (`id`, `createdAt`, `name`, `partnerId`, `level`) VALUES ('221512', '2016-03-24 12:00:40', 'UIAutomationDepartmentLevel1', '22102016', '1');")
    mySQLConnectorPM.execute("INSERT INTO Department (`id`, `createdAt`, `name`, `partnerId`, `parentDepartmentId`, `level`) VALUES ('221513', '2016-03-24 12:00:40', 'UIAutomationDepartmentLevel2', '22102016', '221512', '2');")
    mySQLConnectorPM.execute("INSERT INTO Department (`id`, `createdAt`, `name`, `partnerId`, `parentDepartmentId`, `level`) VALUES ('221514', '2016-03-24 12:00:40', 'UIAutomationDepartmentLevel3', '22102016', '221513', '3');")
    mySQLConnectorPM.execute("INSERT INTO Department (`id`, `createdAt`, `name`, `partnerId`, `parentDepartmentId`, `level`) VALUES ('221515', '2016-03-24 12:00:40', 'UIAutomationDepartmentLevel4', '22102016', '221514', '4');")
    mySQLConnectorPM.execute("INSERT INTO Department (`id`, `createdAt`, `name`, `partnerId`, `parentDepartmentId`, `level`) VALUES ('221516', '2016-03-24 12:00:40', 'UIAutomationDepartmentLevel5', '22102016', '221515', '5');")
    mySQLConnectorPM.execute("INSERT INTO Department (`id`, `createdAt`, `name`, `partnerId`, `level`) VALUES ('221517', '2016-03-24 12:00:40', 'UIAutomationDepartmentLevel1', '22102017', '1');")
}

Given(~/^Ecards reasons tags are created$/) { ->
    mySQLConnectorPM.execute("INSERT INTO Tag (`id`, `createdAt`, `cap`, `frequency`, `tagKey`, `tagStatus`, `partnerId`, `description`) VALUES ('10013099750', '2016-03-11 12:54:11', '2147483647', 'NONE', 'reason1kb', 'ACTIVE', '22102016', 'reason1');")
    mySQLConnectorPM.execute("INSERT INTO Tag (`id`, `createdAt`, `cap`, `frequency`, `tagKey`, `tagStatus`, `partnerId`, `description`) VALUES ('10013099751', '2016-03-11 12:54:45', '2147483647', 'NONE', 'reason2kb', 'ACTIVE', '22102016', 'reason2');")
}

Given(~/^Ecards reasons are created$/) { ->
    mySQLConnectorIA.execute("INSERT INTO ECardsReason (`id`, `createdAt`, `updatedAt`, `managerToUserMax`, `managerToUserMin`, `managerToUserReasonRange`, `name`, `userToUserMax`, `userToUserMin`, `userToUserReasonRange`, `deleted`, `partnerId`, `tagName`) VALUES ('22102016111', '2016-02-17 09:45:36', '2016-02-17 09:45:36', '100', '10', 'DEFINE', 'reason1', '100', '10', 'DEFINE', 0, '22102016', 'reason1kb');")
    mySQLConnectorIA.execute("INSERT INTO ECardsReason (`id`, `createdAt`, `updatedAt`, `managerToUserMax`, `managerToUserMin`, `managerToUserReasonRange`, `name`, `userToUserMax`, `userToUserMin`, `userToUserReasonRange`, `deleted`, `partnerId`, `tagName`) VALUES ('22102016112', '2016-02-17 09:45:36', '2016-02-17 09:45:36', '100', '10', 'DEFINE', 'reason2', '100', '10', 'DEFINE', 0, '22102016', 'reason2kb');")
}

Given(~/^Ecards templates are created$/) { ->
    mySQLConnectorIA.execute("INSERT INTO ECardsTemplate (`id`, `createdAt`, `updatedAt`, `imageUrl`, `isDefault`, `name`, `thumbnailUrl`, `deleted`, `partnerId`) VALUES ('22102016111', '2016-02-17 12:49:55', '2016-02-17 12:51:54', 'https://iat-admin-public-images-qa.s3.amazonaws.com/reasons-images/reason_11d1cff9-2f2e-4ae5-b716-c1ab3f26b833_8ffbf48722c0c9850c50453d9cdf5446.JPEG', 0, 'customTemplate1', 'https://iat-admin-public-images-qa.s3.amazonaws.com/reasons-thumbnails/reason_thumbnail_2901fa32-d102-4ee3-9854-24d6eaf985aa_8ffbf48722c0c9850c50453d9cdf5446.JPEG', 0, '22102016');")
    mySQLConnectorIA.execute("INSERT INTO ECardsTemplate (`id`, `createdAt`, `updatedAt`, `imageUrl`, `isDefault`, `name`, `thumbnailUrl`, `deleted`, `partnerId`) VALUES ('22102016112', '2016-02-17 12:49:55', '2016-02-17 12:51:54', 'https://iat-admin-public-images-qa.s3.amazonaws.com/reasons-images/reason_11d1cff9-2f2e-4ae5-b716-c1ab3f26b833_8ffbf48722c0c9850c50453d9cdf5446.JPEG', 0, 'customTemplate3', 'https://iat-admin-public-images-qa.s3.amazonaws.com/reasons-thumbnails/reason_thumbnail_2901fa32-d102-4ee3-9854-24d6eaf985aa_8ffbf48722c0c9850c50453d9cdf5446.JPEG', 0, '22102016');")
    mySQLConnectorIA.execute("INSERT INTO ECardsTemplate (`id`, `createdAt`, `updatedAt`, `imageUrl`, `isDefault`, `name`, `thumbnailUrl`, `deleted`, `partnerId`) VALUES ('22102016113', '2016-02-17 12:49:55', '2016-02-17 12:51:54', 'https://iat-admin-public-images-qa.s3.amazonaws.com/reasons-images/reason_11d1cff9-2f2e-4ae5-b716-c1ab3f26b833_8ffbf48722c0c9850c50453d9cdf5446.JPEG', 0, 'customTemplate2', 'https://iat-admin-public-images-qa.s3.amazonaws.com/reasons-thumbnails/reason_thumbnail_2901fa32-d102-4ee3-9854-24d6eaf985aa_8ffbf48722c0c9850c50453d9cdf5446.JPEG', 0, '22102016');")
    mySQLConnectorIA.execute("INSERT INTO ECardsTemplate (`id`, `createdAt`, `updatedAt`, `imageUrl`, `isDefault`, `name`, `thumbnailUrl`, `deleted`, `partnerId`) VALUES ('22102016114', '2016-02-17 12:49:55', '2016-02-17 12:51:54', 'https://iat-admin-public-images-qa.s3.amazonaws.com/reasons-images/reason_11d1cff9-2f2e-4ae5-b716-c1ab3f26b833_8ffbf48722c0c9850c50453d9cdf5446.JPEG', 1, 'defaultTemplate1', 'https://iat-admin-public-images-qa.s3.amazonaws.com/reasons-thumbnails/reason_thumbnail_2901fa32-d102-4ee3-9854-24d6eaf985aa_8ffbf48722c0c9850c50453d9cdf5446.JPEG', 0, '22102016');")
    mySQLConnectorIA.execute("INSERT INTO ECardsTemplate (`id`, `createdAt`, `updatedAt`, `imageUrl`, `isDefault`, `name`, `thumbnailUrl`, `deleted`, `partnerId`) VALUES ('22102016115', '2016-02-17 12:49:55', '2016-02-17 12:51:54', 'https://iat-admin-public-images-qa.s3.amazonaws.com/reasons-images/reason_11d1cff9-2f2e-4ae5-b716-c1ab3f26b833_8ffbf48722c0c9850c50453d9cdf5446.JPEG', 1, 'defaultTemplate2', 'https://iat-admin-public-images-qa.s3.amazonaws.com/reasons-thumbnails/reason_thumbnail_2901fa32-d102-4ee3-9854-24d6eaf985aa_8ffbf48722c0c9850c50453d9cdf5446.JPEG', 0, '22102016');")
}

Given(~/^Ecards Settings are created$/) { ->
    mySQLConnectorIA.execute("INSERT INTO ECardsSettings (`id`, `createdAt`, `updatedAt`, `partnerId`, `managerSharePointsRange`, `sharePointsWithManager`, `userSharePointsRange`, `managerToUserMax`, `managerToUserMin`, `userToUserMax`, `userToUserMin`, `useDefaultTemplatesSet`, `wizardLastStep`, `approvalProcess`) VALUES ('46170', '2016-03-31 13:02:19', '2016-10-11 18:04:18', '22102016', 'SAME_COMPANY', 1, 'ALL_USERS', '100', '10', '422', '112', 0, '4', 'NONE');")
}

Given(~/^Super admin and uber admin are created with all authorities$/) { ->

    mySQLConnectorIA.execute("INSERT INTO User (`id`, `createdAt`, `partnerId`, `enabled`, `password`, `username`, `departmentId`, `apiKey`, `userAdminRole`, `userRole`) VALUES ('22102061', '2016-04-26 09:45:29', '22102016', 1, 'T1U8KXcwPmcn5OZob8FpccztBqLMZmmnPZ84lCz0yu8=', 'ui_automated_tests_uberadmin@wp.pl', '221512', 'kb_ui_automated_tests', 'admin', 'user');")
    mySQLConnectorIA.execute("INSERT INTO UserAuthority (`userId`, `authorityId`) VALUES ('22102061', 'hr_grant_partners');")

    mySQLConnectorIA.execute("INSERT INTO User (`id`, `createdAt`, `partnerId`, `enabled`, `password`, `username`, `departmentId`, `apiKey`, `userAdminRole`, `userRole`) VALUES ('22102523', '2016-05-05 05:59:35', '22102016', 1, 'T1U8KXcwPmcn5OZob8FpccztBqLMZmmnPZ84lCz0yu8=', 'ui_automated_tests_superadmin@wp.pl', '221512', 'kb_ui_automated_tests', 'superAdmin', 'user');")
    mySQLConnectorIA.execute("INSERT INTO UserAuthority (`userId`, `authorityId`) VALUES ('22102523', 'hr_global_config');")
    mySQLConnectorIA.execute("INSERT INTO UserAuthority (`userId`, `authorityId`) VALUES ('22102523', 'hr_reporting');")
    mySQLConnectorIA.execute("INSERT INTO UserAuthority (`userId`, `authorityId`) VALUES ('22102523', 'hr_grant_users');")
    mySQLConnectorIA.execute("INSERT INTO UserAuthority (`userId`, `authorityId`) VALUES ('22102523', 'hr_grant_departments');")
    mySQLConnectorIA.execute("INSERT INTO UserAuthority (`userId`, `authorityId`) VALUES ('22102523', 'hr_add_super_admin');")
    mySQLConnectorIA.execute("INSERT INTO UserAuthority (`userId`, `authorityId`) VALUES ('22102523', 'hr_add_admin');")
    mySQLConnectorIA.execute("INSERT INTO UserAuthority (`userId`, `authorityId`) VALUES ('22102523', 'hr_add_managers');")
    mySQLConnectorIA.execute("INSERT INTO UserAuthority (`userId`, `authorityId`) VALUES ('22102523', 'hr_add_users');")

    mySQLConnectorIA.execute("INSERT INTO User (`id`, `createdAt`, `partnerId`, `enabled`, `password`, `username`, `departmentId`, `apiKey`, `userAdminRole`, `userRole`) VALUES ('221025231', '2016-05-05 05:59:35', '22102017', 1, 'T1U8KXcwPmcn5OZob8FpccztBqLMZmmnPZ84lCz0yu8=', 'ui_automated_tests_wizard_test@wp.pl', '221512', 'kb_ui_automated_tests2', 'superAdmin', 'user');")
    mySQLConnectorIA.execute("INSERT INTO UserAuthority (`userId`, `authorityId`) VALUES ('221025231', 'hr_global_config');")
    mySQLConnectorIA.execute("INSERT INTO UserAuthority (`userId`, `authorityId`) VALUES ('221025231', 'hr_reporting');")
    mySQLConnectorIA.execute("INSERT INTO UserAuthority (`userId`, `authorityId`) VALUES ('221025231', 'hr_grant_users');")
    mySQLConnectorIA.execute("INSERT INTO UserAuthority (`userId`, `authorityId`) VALUES ('221025231', 'hr_grant_departments');")
    mySQLConnectorIA.execute("INSERT INTO UserAuthority (`userId`, `authorityId`) VALUES ('221025231', 'hr_add_super_admin');")
    mySQLConnectorIA.execute("INSERT INTO UserAuthority (`userId`, `authorityId`) VALUES ('221025231', 'hr_add_admin');")
    mySQLConnectorIA.execute("INSERT INTO UserAuthority (`userId`, `authorityId`) VALUES ('221025231', 'hr_add_managers');")
    mySQLConnectorIA.execute("INSERT INTO UserAuthority (`userId`, `authorityId`) VALUES ('221025231', 'hr_add_users');")
}

Given(~/^Other needed admins and users are created$/) { ->
    //create other admin/manager types
    new CreateUserRepositoryImpl().createNewUser(new createNewUser('UI3', 'ui_automated_tests_admin@wp.pl', "ui_automated_tests_admin", "MALE", "1989-06-08", "221512", "USER", "ADMIN", "ACTIVE"), Config.superAdminLogin, Config.superAdminPassword)
    new CreateUserRepositoryImpl().createNewUser(new createNewUser('UI4', 'ui_automated_tests_manager@wp.pl', "ui_automated_tests_manager", "MALE", "1989-06-08", "221512", "MANAGER", "NONE", "ACTIVE"), Config.superAdminLogin, Config.superAdminPassword)
    new CreateUserRepositoryImpl().createNewUser(new createNewUser('UI6', 'profiletestsdmin@wp.pl', "profiletestsdmin", "MALE", "1989-06-08", "221512", "USER", "ADMIN", "ACTIVE"), Config.superAdminLogin, Config.superAdminPassword)

    //add authorities to other admin/manager types
    def userId = mySQLConnectorIA.getSingleResult("SELECT id FROM USER WHERE username = 'ui_automated_tests_admin@wp.pl'")
    mySQLConnectorIA.execute("INSERT INTO UserAuthority (`userId`, `authorityId`) VALUES ('" + userId + "', 'hr_reporting');")
    mySQLConnectorIA.execute("INSERT INTO UserAuthority (`userId`, `authorityId`) VALUES ('" + userId + "', 'hr_grant_users');")
    mySQLConnectorIA.execute("INSERT INTO UserAuthority (`userId`, `authorityId`) VALUES ('" + userId + "', 'hr_grant_departments');")
    mySQLConnectorIA.execute("INSERT INTO UserAuthority (`userId`, `authorityId`) VALUES ('" + userId + "', 'hr_add_admin');")
    mySQLConnectorIA.execute("INSERT INTO UserAuthority (`userId`, `authorityId`) VALUES ('" + userId + "', 'hr_add_managers');")
    mySQLConnectorIA.execute("INSERT INTO UserAuthority (`userId`, `authorityId`) VALUES ('" + userId + "', 'hr_add_users');")

    userId = mySQLConnectorIA.getSingleResult("SELECT id FROM USER WHERE username = 'ui_automated_tests_manager@wp.pl'")
    mySQLConnectorIA.execute("INSERT INTO UserAuthority (`userId`, `authorityId`) VALUES ('" + userId + "', 'hr_grant_users');")
    mySQLConnectorIA.execute("INSERT INTO UserAuthority (`userId`, `authorityId`) VALUES ('" + userId + "', 'hr_grant_departments');")
    mySQLConnectorIA.execute("INSERT INTO UserAuthority (`userId`, `authorityId`) VALUES ('" + userId + "', 'hr_add_managers');")
    mySQLConnectorIA.execute("INSERT INTO UserAuthority (`userId`, `authorityId`) VALUES ('" + userId + "', 'hr_add_users');")

    userId = mySQLConnectorIA.getSingleResult("SELECT id FROM USER WHERE username = 'profiletestsdmin@wp.pl'")
    mySQLConnectorIA.execute("INSERT INTO UserAuthority (`userId`, `authorityId`) VALUES ('" + userId + "', 'hr_reporting');")
    mySQLConnectorIA.execute("INSERT INTO UserAuthority (`userId`, `authorityId`) VALUES ('" + userId + "', 'hr_grant_users');")
    mySQLConnectorIA.execute("INSERT INTO UserAuthority (`userId`, `authorityId`) VALUES ('" + userId + "', 'hr_grant_departments');")
    mySQLConnectorIA.execute("INSERT INTO UserAuthority` (`userId`, `authorityId`) VALUES ('" + userId + "', 'hr_add_admin');")
    mySQLConnectorIA.execute("INSERT INTO UserAuthority (`userId`, `authorityId`) VALUES ('" + userId + "', 'hr_add_managers');")
    mySQLConnectorIA.execute("INSERT INTO UserAuthority (`userId`, `authorityId`) VALUES ('" + userId + "', 'hr_add_users');")

    //update passwords for all admins
    mySQLConnectorIA.execute("UPDATE User SET password = 'T1U8KXcwPmcn5OZob8FpccztBqLMZmmnPZ84lCz0yu8=' WHERE partnerId = '22102016'")
    mySQLConnectorIA.execute("UPDATE User SET password = 'T1U8KXcwPmcn5OZob8FpccztBqLMZmmnPZ84lCz0yu8=' WHERE partnerId = '22102017'")

    //create simple users in proper departments
    new CreateUserRepositoryImpl().createNewUser(new createNewUser('UI00', 'emailfortest@wp.pl', "Kris Baranowski", "MALE", "1989-06-08", "221512", "USER", "NONE", "ACTIVE"), Config.superAdminLogin, Config.superAdminPassword)
    new CreateUserRepositoryImpl().createNewUser(new createNewUser('UI001', 'emailfortest1@wp.pl', "Kris1 Baranowski1", "MALE", "1989-06-08", "221512", "USER", "NONE", "ACTIVE"), Config.superAdminLogin, Config.superAdminPassword)
    new CreateUserRepositoryImpl().createNewUser(new createNewUser('UI002', 'emailfortest2@wp.pl', "Kris2 Baranowski2", "MALE", "1989-06-08", "221512", "USER", "NONE", "ACTIVE"), Config.superAdminLogin, Config.superAdminPassword)
    new CreateUserRepositoryImpl().createNewUser(new createNewUser('UI003', 'emailfortest3@wp.pl', "Kris3 Baranowski3", "MALE", "1989-06-08", "221512", "USER", "NONE", "ACTIVE"), Config.superAdminLogin, Config.superAdminPassword)
    new CreateUserRepositoryImpl().createNewUser(new createNewUser('UI004', 'emailfortest4@wp.pl', "Kris4 Baranowski4", "MALE", "1989-06-08", "221512", "USER", "NONE", "ACTIVE"), Config.superAdminLogin, Config.superAdminPassword)
    new CreateUserRepositoryImpl().createNewUser(new createNewUser('UI005', 'emailfortest5@wp.pl', "Kris5 Baranowski5", "MALE", "1989-06-08", "221512", "USER", "NONE", "ACTIVE"), Config.superAdminLogin, Config.superAdminPassword)
    new CreateUserRepositoryImpl().createNewUser(new createNewUser('UI006', 'emailfortest6@wp.pl', "Kris6 Baranowski6", "MALE", "1989-06-08", "221512", "USER", "NONE", "ACTIVE"), Config.superAdminLogin, Config.superAdminPassword)
    new CreateUserRepositoryImpl().createNewUser(new createNewUser('UI007', 'notVerified@wp.pl', "Kris7notverified Baranowski7", "MALE", "1989-06-08", "221512", "USER", "NONE", "ACTIVE"), Config.superAdminLogin, Config.superAdminPassword)
    new CreateUserRepositoryImpl().createNewUser(new createNewUser('UI008', 'emailfortest8@wp.pl', "Kris8notverified Baranowski8", "MALE", "1989-06-08", "221513", "USER", "NONE", "ACTIVE"), Config.superAdminLogin, Config.superAdminPassword)
    new CreateUserRepositoryImpl().createNewUser(new createNewUser('UI009', 'emailfortest9@wp.pl', "Kris9notverified Baranowski9", "MALE", "1989-06-08", "221514", "USER", "NONE", "ACTIVE"), Config.superAdminLogin, Config.superAdminPassword)
    new CreateUserRepositoryImpl().createNewUser(new createNewUser('UI0010', 'emailfortest10@wp.pl', "Kris10notverified Baranowski10", "MALE", "1989-06-08", "221515", "USER", "NONE", "ACTIVE"), Config.superAdminLogin, Config.superAdminPassword)
    new CreateUserRepositoryImpl().createNewUser(new createNewUser('UI0011', 'emailfortest11@wp.pl', "Kris11notverified Baranowski11", "MALE", "1989-06-08", "221516", "USER", "NONE", "ACTIVE"), Config.superAdminLogin, Config.superAdminPassword)

}

//INSERT INTO `points_manager`.`PartnersGroup` (`id`, `name`, `shortName`, `hrAdminConfig`, `ecardConfig`, `eCardsLevel`, `ecardsEpoints`) VALUES ('7777', 'Ecards UI Tests', 'ecardsuitests', 1, 1, 'none', 1);
//INSERT INTO `points_manager`.`PartnersGroup` (`id`, `name`, `shortName`, `hrAdminConfig`, `ecardConfig`, `eCardsLevel`, `ecardsEpoints`) VALUES ('7778', 'Ecards UI Tests 2', 'ecardsuitests2', 1, 1, 'none', 1);

//INSERT INTO `points_manager`.`Partner` (`id`, `createdAt`, `updatedAt`, `currentTotal`, `accessKey`, `name`, `paymentType`, `shortName`, `active`, `siteUrl`, `logoUrl`, `version`, `email`, `warningThreshold`, `groupId`, `registrationPoints`, `ecardsVisible`) VALUES ('22102016', '2014-07-25 11:47:07', '2014-07-25 11:47:07', '0', 'kb_ui_automated_tests', 'KB Ui Automated Tests', 'POST_PAY', 'kb_ui_automated_tests_1', 1, 'http://NIE.USUWAJ.MNIE.NASTEPNYM.RAZEM.COM', 'http://bdl-public-images-qa.s3.amazonaws.com/chain-logos/chain_594de950-a5c9-459d-8989-fd07347950c5_31000b1971aa57aa2700710f06d23d06.gif', '1', 'NIE@USUWAC', '0', '7777', 1, 1);
//INSERT INTO `points_manager`.`Partner` (`id`, `createdAt`, `updatedAt`, `currentTotal`, `accessKey`, `name`, `paymentType`, `shortName`, `active`, `siteUrl`, `logoUrl`, `version`, `email`, `warningThreshold`, `groupId`, `registrationPoints`, `ecardsVisible`) VALUES ('22102017', '2014-07-25 11:47:07', '2014-07-25 11:47:07', '0', 'kb_ui_automated_tests2', 'KB Ui Automated Tests2', 'POST_PAY', 'kb_ui_automated_tests_2', 1, 'http://NIE.USUWAJ.MNIE.NASTEPNYM.RAZEM.COM', 'http://bdl-public-images-qa.s3.amazonaws.com/chain-logos/chain_594de950-a5c9-459d-8989-fd07347950c5_31000b1971aa57aa2700710f06d23d06.gif', '1', 'NIE@USUWAC', '0', '7778', 1, 1);
//
//INSERT INTO `points_manager`.`VirtualGroup` (`id`, `createdAt`, `name`, `partnerId`, `ownerType`) VALUES ('1550', '2016-09-05 13:26:19', 'KB Ui Automated Tests', '22102016', 'partner');

//INSERT INTO `points_manager`.`Department` (`id`, `createdAt`, `name`, `partnerId`, `level`) VALUES ('221512', '2016-03-24 12:00:40', 'UIAutomationDepartmentLevel1', '22102016', '1');
//INSERT INTO `points_manager`.`Department` (`id`, `createdAt`, `name`, `partnerId`, `parentDepartmentId`, `level`) VALUES ('221513', '2016-03-24 12:00:40', 'UIAutomationDepartmentLevel2', '22102016', '221512', '2');
//INSERT INTO `points_manager`.`Department` (`id`, `createdAt`, `name`, `partnerId`, `parentDepartmentId`, `level`) VALUES ('221514', '2016-03-24 12:00:40', 'UIAutomationDepartmentLevel3', '22102016', '221513', '3');
//INSERT INTO `points_manager`.`Department` (`id`, `createdAt`, `name`, `partnerId`, `parentDepartmentId`, `level`) VALUES ('221515', '2016-03-24 12:00:40', 'UIAutomationDepartmentLevel4', '22102016', '221514', '4');
//INSERT INTO `points_manager`.`Department` (`id`, `createdAt`, `name`, `partnerId`, `parentDepartmentId`, `level`) VALUES ('221516', '2016-03-24 12:00:40', 'UIAutomationDepartmentLevel5', '22102016', '221515', '5');
//INSERT INTO `points_manager`.`Department` (`id`, `createdAt`, `name`, `partnerId`, `level`) VALUES ('221517', '2016-03-24 12:00:40', 'UIAutomationDepartmentLevel1', '22102017', '1');")

//INSERT INTO `points_manager`.`Tag` (`id`, `createdAt`, `cap`, `frequency`, `tagKey`, `tagStatus`, `partnerId`, `description`) VALUES ('10013099750', '2016-03-11 12:54:11', '2147483647', 'NONE', 'reason1kb', 'ACTIVE', '22102016', 'reason1');
//INSERT INTO `points_manager`.`Tag` (`id`, `createdAt`, `cap`, `frequency`, `tagKey`, `tagStatus`, `partnerId`, `description`) VALUES ('10013099751', '2016-03-11 12:54:45', '2147483647', 'NONE', 'reason2kb', 'ACTIVE', '22102016', 'reason2');

//INSERT INTO `iat_admin_stag`.`ECardsReason` (`id`, `createdAt`, `updatedAt`, `managerToUserMax`, `managerToUserMin`, `managerToUserReasonRange`, `name`, `userToUserMax`, `userToUserMin`, `userToUserReasonRange`, `deleted`, `partnerId`, `tagName`) VALUES ('22102016111', '2016-02-17 09:45:36', '2016-02-17 09:45:36', '100', '10', 'DEFINE', 'reason1', '100', '10', 'DEFINE', 0, '22102016', 'reason1kb');
//INSERT INTO `iat_admin_stag`.`ECardsReason` (`id`, `createdAt`, `updatedAt`, `managerToUserMax`, `managerToUserMin`, `managerToUserReasonRange`, `name`, `userToUserMax`, `userToUserMin`, `userToUserReasonRange`, `deleted`, `partnerId`, `tagName`) VALUES ('22102016112', '2016-02-17 09:45:36', '2016-02-17 09:45:36', '100', '10', 'DEFINE', 'reason2', '100', '10', 'DEFINE', 0, '22102016', 'reason2kb');

//INSERT INTO `iat_admin_stag`.`ECardsTemplate` (`id`, `createdAt`, `updatedAt`, `imageUrl`, `isDefault`, `name`, `thumbnailUrl`, `deleted`, `partnerId`) VALUES ('22102016111', '2016-02-17 12:49:55', '2016-02-17 12:51:54', 'https://iat-admin-public-images-qa.s3.amazonaws.com/reasons-images/reason_11d1cff9-2f2e-4ae5-b716-c1ab3f26b833_8ffbf48722c0c9850c50453d9cdf5446.JPEG', 0, 'customTemplate1', 'https://iat-admin-public-images-qa.s3.amazonaws.com/reasons-thumbnails/reason_thumbnail_2901fa32-d102-4ee3-9854-24d6eaf985aa_8ffbf48722c0c9850c50453d9cdf5446.JPEG', 0, '22102016');
//INSERT INTO `iat_admin_stag`.`ECardsTemplate` (`id`, `createdAt`, `updatedAt`, `imageUrl`, `isDefault`, `name`, `thumbnailUrl`, `deleted`, `partnerId`) VALUES ('22102016112', '2016-02-17 12:49:55', '2016-02-17 12:51:54', 'https://iat-admin-public-images-qa.s3.amazonaws.com/reasons-images/reason_11d1cff9-2f2e-4ae5-b716-c1ab3f26b833_8ffbf48722c0c9850c50453d9cdf5446.JPEG', 0, 'customTemplate3', 'https://iat-admin-public-images-qa.s3.amazonaws.com/reasons-thumbnails/reason_thumbnail_2901fa32-d102-4ee3-9854-24d6eaf985aa_8ffbf48722c0c9850c50453d9cdf5446.JPEG', 0, '22102016');
//INSERT INTO `iat_admin_stag`.`ECardsTemplate` (`id`, `createdAt`, `updatedAt`, `imageUrl`, `isDefault`, `name`, `thumbnailUrl`, `deleted`, `partnerId`) VALUES ('22102016113', '2016-02-17 12:49:55', '2016-02-17 12:51:54', 'https://iat-admin-public-images-qa.s3.amazonaws.com/reasons-images/reason_11d1cff9-2f2e-4ae5-b716-c1ab3f26b833_8ffbf48722c0c9850c50453d9cdf5446.JPEG', 0, 'customTemplate2', 'https://iat-admin-public-images-qa.s3.amazonaws.com/reasons-thumbnails/reason_thumbnail_2901fa32-d102-4ee3-9854-24d6eaf985aa_8ffbf48722c0c9850c50453d9cdf5446.JPEG', 0, '22102016');
//INSERT INTO `iat_admin_stag`.`ECardsTemplate` (`id`, `createdAt`, `updatedAt`, `imageUrl`, `isDefault`, `name`, `thumbnailUrl`, `deleted`, `partnerId`) VALUES ('22102016114', '2016-02-17 12:49:55', '2016-02-17 12:51:54', 'https://iat-admin-public-images-qa.s3.amazonaws.com/reasons-images/reason_11d1cff9-2f2e-4ae5-b716-c1ab3f26b833_8ffbf48722c0c9850c50453d9cdf5446.JPEG', 1, 'defaultTemplate1', 'https://iat-admin-public-images-qa.s3.amazonaws.com/reasons-thumbnails/reason_thumbnail_2901fa32-d102-4ee3-9854-24d6eaf985aa_8ffbf48722c0c9850c50453d9cdf5446.JPEG', 0, '22102016');
//INSERT INTO `iat_admin_stag`.`ECardsTemplate` (`id`, `createdAt`, `updatedAt`, `imageUrl`, `isDefault`, `name`, `thumbnailUrl`, `deleted`, `partnerId`) VALUES ('22102016115', '2016-02-17 12:49:55', '2016-02-17 12:51:54', 'https://iat-admin-public-images-qa.s3.amazonaws.com/reasons-images/reason_11d1cff9-2f2e-4ae5-b716-c1ab3f26b833_8ffbf48722c0c9850c50453d9cdf5446.JPEG', 1, 'defaultTemplate2', 'https://iat-admin-public-images-qa.s3.amazonaws.com/reasons-thumbnails/reason_thumbnail_2901fa32-d102-4ee3-9854-24d6eaf985aa_8ffbf48722c0c9850c50453d9cdf5446.JPEG', 0, '22102016');

//INSERT INTO `iat_admin_stag`.`ECardsSettings` (`id`, `createdAt`, `updatedAt`, `partnerId`, `managerSharePointsRange`, `sharePointsWithManager`, `userSharePointsRange`, `managerToUserMax`, `managerToUserMin`, `userToUserMax`, `userToUserMin`, `useDefaultTemplatesSet`, `wizardLastStep`, `approvalProcess`) VALUES ('46170', '2016-03-31 13:02:19', '2016-10-11 18:04:18', '22102016', 'SAME_COMPANY', 1, 'ALL_USERS', '100', '10', '422', '112', 0, '4', 'NONE');

//INSERT INTO `iat_admin_stag`.`User` (`id`, `createdAt`, `partnerId`, `enabled`, `password`, `username`, `departmentId`, `apiKey`, `userAdminRole`, `userRole`) VALUES ('22102061', '2016-04-26 09:45:29', '22102016', 1, 'T1U8KXcwPmcn5OZob8FpccztBqLMZmmnPZ84lCz0yu8=', 'ui_automated_tests_uberadmin@wp.pl', '221512', 'kb_ui_automated_tests', 'admin', 'user');
//INSERT INTO `iat_admin_stag`.`User` (`id`, `createdAt`, `partnerId`, `enabled`, `password`, `username`, `departmentId`, `apiKey`, `userAdminRole`, `userRole`) VALUES ('22102523', '2016-05-05 05:59:35', '22102016', 1, 'T1U8KXcwPmcn5OZob8FpccztBqLMZmmnPZ84lCz0yu8=', 'ui_automated_tests_superadmin@wp.pl', '221512', 'kb_ui_automated_tests', 'superAdmin', 'user');

//INSERT INTO `iat_admin_qa`.`UserAuthority` (`userId`, `authorityId`) VALUES ('22102061', 'hr_grant_partners');
//INSERT INTO `iat_admin_qa`.`UserAuthority` (`userId`, `authorityId`) VALUES ('22102523', 'hr_global_config');
//INSERT INTO `iat_admin_qa`.`UserAuthority` (`userId`, `authorityId`) VALUES ('22102523', 'hr_reporting');
//INSERT INTO `iat_admin_qa`.`UserAuthority` (`userId`, `authorityId`) VALUES ('22102523', 'hr_grant_users');
//INSERT INTO `iat_admin_qa`.`UserAuthority` (`userId`, `authorityId`) VALUES ('22102523', 'hr_grant_departments');
//INSERT INTO `iat_admin_qa`.`UserAuthority` (`userId`, `authorityId`) VALUES ('22102523', 'hr_add_super_admin');
//INSERT INTO `iat_admin_qa`.`UserAuthority` (`userId`, `authorityId`) VALUES ('22102523', 'hr_add_admin');
//INSERT INTO `iat_admin_qa`.`UserAuthority` (`userId`, `authorityId`) VALUES ('22102523', 'hr_add_managers');
//INSERT INTO `iat_admin_qa`.`UserAuthority` (`userId`, `authorityId`) VALUES ('22102523', 'hr_add_users');
//INSERT INTO `iat_admin_qa`.`UserAuthority` (`userId`, `authorityId`) VALUES ('221025231', 'hr_global_config');
//INSERT INTO `iat_admin_qa`.`UserAuthority` (`userId`, `authorityId`) VALUES ('221025231', 'hr_reporting');
//INSERT INTO `iat_admin_qa`.`UserAuthority` (`userId`, `authorityId`) VALUES ('221025231', 'hr_grant_users');
//INSERT INTO `iat_admin_qa`.`UserAuthority` (`userId`, `authorityId`) VALUES ('221025231', 'hr_grant_departments');
//INSERT INTO `iat_admin_qa`.`UserAuthority` (`userId`, `authorityId`) VALUES ('221025231', 'hr_add_super_admin');
//INSERT INTO `iat_admin_qa`.`UserAuthority` (`userId`, `authorityId`) VALUES ('221025231', 'hr_add_admin');
//INSERT INTO `iat_admin_qa`.`UserAuthority` (`userId`, `authorityId`) VALUES ('221025231', 'hr_add_managers');
//INSERT INTO `iat_admin_qa`.`UserAuthority` (`userId`, `authorityId`) VALUES ('221025231', 'hr_add_users');

//At this point rest of users has to be added by api or manually by iat admin to create for them rows in dynamo db, and all authorities should be added to them, we can also run step Other needed admins and users are created
//emailfortest@wp.pl has also to be verified and user should have possibility to login
