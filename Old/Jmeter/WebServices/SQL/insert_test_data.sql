insert into Partner(id, createdAt, currentTotal, accessKey, name, paymentType, pendingAutoConfirmTimeOut, shortName, active) values(2, now(), 10000, 'accessKey', 'Partner 1', 'PRE_PAY', 2592000000, 'P1', true);			
insert into Partner(id, createdAt, currentTotal, accessKey, name, paymentType, pendingAutoConfirmTimeOut, shortName, active) values(3, now(), 0, 'accessKey1', 'Partner 1', 'POST_PAY', 2592000000, 'P2', true);
insert into Partner(id, createdAt, currentTotal, accessKey, name, paymentType, pendingAutoConfirmTimeOut, shortName, active) values(4, now(), 500, '1234', 'ActivePartner', 'PRE_PAY', 2592000000, 'AP1', true);
insert into Partner(id, createdAt, currentTotal, accessKey, name, paymentType, pendingAutoConfirmTimeOut, shortName, active) values(5, now(), 10000, '4321', 'InactivePartner', 'PRE_PAY', 2592000000, 'I', false);

-- user password is: 1qazxsw2, sha-256 encrypted, 1024 iterations, base64 encoded
insert into User(id, active, birthDate, country, county, email, firstName, gender, houseNumberOrName, lastName, mobileNumber, nationality, optInContactByUs, password, postcode, registerPartnerShortName, 
registrationAt, street, testUser, title, townOrCity, emailVerified) 
values(1, true, '1980-05-07 15:41:56', 'Poland', 'malopolska', 'john.doe@iatlimited.com', 'John', 'MALE', '25', 'Doe', '+48925661772', 'polish', true, 
'Id3AV3MV0yMSsimaAl6f1EyJO+ujiy5GnSL6P1MPXTY=', '23-202', 'P1', '2013-05-10 15:41:56', 'Jasna', false, 'Mr', 'Warszawa', true);
insert into User(id, active, birthDate, country, county, email, firstName, gender, houseNumberOrName, lastName, mobileNumber, nationality, optInContactByUs, password, postcode, registerPartnerShortName, 
registrationAt, street, testUser, title, townOrCity, emailVerified) 
values(2, false, '1980-05-05 15:41:56', 'Poland', 'malopolska', 'eddie.doe@domain.com', 'Eddie', 'MALE', '31', 'Doe', '+48925661881', 'polish', true, 
'Id3AV3MV0yMSsimaAl6f1EyJO+ujiy5GnSL6P1MPXTY=', '23-300', 'P1', '2013-05-10 15:31:56', 'Cicha', false, 'Mr', 'Warszawa', true);
insert into User(id, active, createdAt, email) values(3, true, '2013-05-05 15:41:56', 'email100@domain.com');
insert into User(id, active, createdAt, email) values(4, false, '2013-05-05 15:41:56', 'email200@domain.com');
insert into User(id, active, createdAt, email) values(5, false, '2013-05-05 15:41:56', 'email300@domain.com');
insert into User(id, active, createdAt, email) values(6, true, '2013-05-05 15:41:56', 'email400@domain.com');
insert into User(id, active, createdAt, email) values(7, true, '2013-05-05 15:41:56', 'email500@domain.com');


insert into UserId(id, createdAt, userId, userIdType, user_id) values(id, '2013-05-10 15:41:56', 'john.doe@iatlimited.com', 'EMAIL', 1);
insert into UserId(id, createdAt, userId, userIdType, user_id) values(id, '2013-05-10 15:31:56', 'eddie@domain.com', 'EMAIL', 1);
insert into UserId(id, createdAt, userId, userIdType, user_id) values(id, '2013-05-10 15:31:56', 'email100@domain.com', 'EMAIL', 3);
insert into UserId(id, createdAt, userId, userIdType, user_id) values(id, '2013-05-10 15:31:56', 'email200@domain.com', 'EMAIL', 4);
insert into UserId(id, createdAt, userId, userIdType, user_id) values(id, '2013-05-10 15:31:56', 'email300@domain.com', 'EMAIL', 5);
insert into UserId(id, createdAt, userId, userIdType, user_id) values(id, '2013-05-10 15:31:56', 'email400@domain.com', 'EMAIL', 6);
insert into UserId(id, createdAt, userId, userIdType, user_id) values(id, '2013-05-10 15:31:56', 'email500@domain.com', 'EMAIL', 7);


insert into Tag(id, createdAt, cap, frequency, tagKey, tagStatus, partnerId) values(2, '2013-05-01 15:41:56', 50, 'MONTHLY', 'TAG1', 'ACTIVE', 2);
insert into Tag(id, createdAt, cap, frequency, tagKey, tagStatus, partnerId) values(3, '2013-05-01 15:41:56', 100, 'MONTHLY', 'TAG2', 'ACTIVE', 2);
insert into Tag(id, createdAt, cap, frequency, tagKey, tagStatus, partnerId) values(4, '2013-05-02 15:41:56', 100, 'MONTHLY', 'TAG_PR2', 'ACTIVE', 3);
insert into Tag(id, createdAt, cap, frequency, tagKey, tagStatus, partnerId) values(5, '2013-05-02 15:41:56', 10000, 'ONCE', 'FILL_PERSONAL_SECTION', 'ACTIVE', 2);
insert into Tag(id, createdAt, cap, frequency, tagKey, tagStatus, partnerId) values(6, '2013-05-02 15:41:56', 10000, 'ONCE', 'FILL_CONTACT_SECTION', 'ACTIVE', 2);


insert into Points(id, createdAt, activityInfo, delta, status, tagId, partnerId, userId, balance) values(1, '2013-05-11 15:41:56', 'Comment on page', 3, 'CONFIRMED', 1, 2, 1, 2);
insert into Points(id, createdAt, activityInfo, delta, status, tagId, partnerId, userId, balance) values(2, '2013-05-12 15:41:56', 'Comment on page', 3, 'CONFIRMED', 1, 2, 1, 4);
insert into Points(id, createdAt, activityInfo, delta, externalTransactionId, status, partnerId, userId, balance) values(3, '2013-05-13 15:41:56', 'Buought a DVD', 26, '123456','PENDING', 2, 1, 4);
insert into Points(id, createdAt, updatedAt, activityInfo, delta, externalTransactionId, status, partnerId, userId, balance) values(4, '2013-05-13 16:41:56', '2013-05-23 16:41:56',
'Buought a Book', 16, '123456','CONFIRMED', 2, 1, 20);

insert into User_pointsMap(User_id, pointsMap, pointsMap_KEY) values(1, 20, 'CONFIRMED');
insert into User_pointsMap(User_id, pointsMap, pointsMap_KEY) values(1, 26, 'PENDING');

insert into Request(id, createdAt, apiAccessKey, requestType, success, pointsId, userId) values(1, '2013-05-11 15:41:56', 'accessKey', 'ADD_POINTS', true, 1, 'email@domain.com');
insert into Request(id, createdAt, apiAccessKey, requestType, success, pointsId, userId) values(2, '2013-05-12 15:41:56', 'accessKey', 'ADD_POINTS', true, 2, 'email@domain.com');
insert into Request(id, createdAt, apiAccessKey, requestType, success, pointsId, userId, message) values(3, '2013-05-13 15:41:56', 'invalidKey', 'ADD_POINTS', false, null, 'email@domain.com', 'Partner with apiKey invalidKey not exists');
insert into Request(id, createdAt, apiAccessKey, requestType, success, pointsId, userId) values(4, '2013-05-13 15:41:56', 'accessKey', 'ADD_POINTS', true, 3, 'email@domain.com');
insert into Request(id, createdAt, apiAccessKey, requestType, success, pointsId, userId) values(5, '2013-05-13 16:41:56', 'accessKey', 'ADD_POINTS', true, 4, 'email@domain.com');
insert into Request(id, createdAt, apiAccessKey, requestType, success, pointsId, userId) values(6, '2013-05-23 16:41:56', 'accessKey', 'CHANGE_PENDING_TO_CONFIRMED', true, 4, 'email@domain.com');