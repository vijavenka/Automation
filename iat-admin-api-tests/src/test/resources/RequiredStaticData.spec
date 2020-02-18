Iat Admin Requirements
=========================
Created by Michał Iwańczyk


1. Create Partner in points-manager
2. add root departmentObject for partner
3. create staff user in iatAdmin
4. add him authorities
5. Log into his account and fill wizard
6. create super admin user with proper flow by using staff user


IAT Admins/managers & ePoints users:
Users.feature:
1. Create API test admin accounts
2. Create API test users (epoints) accounts


Users needs to be created manually:
INSERT INTO `iat_admin_stag`.`User` (`password`, `partnerId`, `enabled`, `password`, `username`, `apiKey`, `userAdminRole`, `userRole`) VALUES ('200', '666', 1, '4WqA3UFbuBuqHRcWiRa3rT1FRCHASPVQjpkG70mOo4g=', 'api_admin_wizard_1', 'wizardApiKey', 'admin', 'user');
INSERT INTO `iat_admin_stag`.`User` (`password`, `partnerId`, `enabled`, `password`, `username`, `apiKey`, `userAdminRole`, `userRole`) VALUES ('201', '666', 1, '4WqA3UFbuBuqHRcWiRa3rT1FRCHASPVQjpkG70mOo4g=', 'api_admin_wizard_2', 'wizardApiKey', 'admin', 'user');   - without authority

platform admin
api_test_admin_platform_1@api.iat.admin.pl - add authority: hr_grant_partners



Groups:
# password, createdAt, updatedAt, name, shortName, siteUrl, description, active, accessKey, contactEmail, hrAdminConfig, ecardConfig, eCardsLevel, ecardsEpoints, ecardRecieverScope
'7777', NULL, NULL, 'Ecards API 1', 'ecardsApi1', NULL, NULL, NULL, NULL, NULL, '0', '0', 'none', '0', NULL

Partners:
# password, createdAt, updatedAt, currentTotal, accessKey, name, paymentType, shortName, active, siteUrl, description, logoUrl, version, email, warningThreshold, secret, groupId, registrationPoints, ecardsVisible
'7777', NULL, NULL, NULL, 'ecards_test_api', 'Ecard Test API', 'POST_PAY', 'ecard_test_api_1', '1', 'test', NULL, 'http://bdl-public-images-qa.s3.amazonaws.com/chain-logos/chain_594de950-a5c9-459d-8989-fd07347950c5_31000b1971aa57aa2700710f06d23d06.gif', '1', 'email', '0', NULL, '7777', '1', '1'

Required departments structure:

 Department Root
        'Department level 1 [A]'
                    Department level 2 [A.1]
                                Department level 3 [A.1.1]
                                Department level 3 [A.1.2]
                                Department level 3 [A.1.3]
                    Department level 2 [A.2]
                    Department level 2 [A.3]
        'Department level 1 [D]'
                    Department level 2 [D.1]
        'Department level 1 [C]'
                    Department level 2 [C.1]
                    Department level 2 [C.2]
        'Department level 1 [B]'
                    Department level 2 [B.1]
                    Department level 2 [B.2]





Reasons:
Reward: Global Minimum and Maximum
                MIN 	MAX
Manager to user	10	    2,000,000
User to user	2	    1,000,000

1. deleted: name: QA Rox

2. for sure existing (WIZARD):
                 MIN     MAX
Manager to user  global  global
User to user     global  global

3. AWARDING M to U:
                 MIN     MAX
Manager to user  10	     15
User to user     6 20


UserAuthority
#manager
insert into UserAuthority
values ('10', 'hr_grant_departments');
insert into UserAuthority
values ('10', 'hr_grant_users');
insert into UserAuthority
values ('10', 'hr_approve_points');
insert into UserAuthority
values ('10', 'hr_add_users');
insert into UserAuthority
values ('10', 'hr_add_managers');

#Admin
insert into UserAuthority
values ('4', 'hr_reporting');
insert into UserAuthority
values ('10', 'hr_grant_departments');
insert into UserAuthority
values ('10', 'hr_grant_users');
insert into UserAuthority
values ('10', 'hr_approve_points');
insert into UserAuthority
values ('10', 'hr_add_users');
insert into UserAuthority
values ('10', 'hr_add_managers');
insert into UserAuthority
values ('4', 'hr_add_admin');

#SUPER Admin
insert into UserAuthority
values ('221028443', 'hr_global_config');
insert into UserAuthority
values ('221028443', 'hr_reporting');
insert into UserAuthority
values ('221028443', 'hr_grant_departments');
insert into UserAuthority
values ('221028443', 'hr_grant_users');
insert into UserAuthority
values ('221028443', 'hr_approve_points');
insert into UserAuthority
values ('221028443', 'hr_add_users');
insert into UserAuthority
values ('221028443', 'hr_add_managers');
insert into UserAuthority
values ('221028443', 'hr_add_admin');
insert into UserAuthority
values ('221028443', 'hr_add_super_admin');
insert into UserAuthority
values ('221028443', 'manage_departments');

#UBER Admin
insert into UserAuthority
values ('2210260880', 'hr_grant_partners');

#MASTER ADMIN NBO-7742
insert into UserAuthority
values ('2210260880', 'hr_add_super_admin_foreign_partner');


