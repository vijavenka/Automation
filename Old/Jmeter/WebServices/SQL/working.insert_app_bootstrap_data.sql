insert into Partner(id, createdAt, currentTotal, accessKey, name, paymentType, shortName, active, siteUrl) values(2, now(), 10000, 'accessKey', 'Partner 1', 'PRE_PAY', 'P1', true, 'http://partner-2.com');			
insert into Partner(id, createdAt, currentTotal, accessKey, name, paymentType, shortName, active, siteUrl) values(3, now(), 0, 'accessKey1', 'Partner 2', 'POST_PAY', 'P2', true, 'http://partner-3.com');
insert into Partner(id, createdAt, currentTotal, accessKey, name, paymentType, shortName, active, siteUrl) values(4, now(), 0, 'accessKey4', 'Partner 4', 'POST_PAY', 'P4', true, 'http://partner-4.com');
insert into Partner(id, createdAt, currentTotal, accessKey, name, paymentType, shortName, active, siteUrl) values(5, now(), 0, 'accessKey5', 'Partner 5', 'POST_PAY', 'P5', true, 'http://partner-5.com');
insert into Partner(id, createdAt, currentTotal, accessKey, name, paymentType, shortName, active, siteUrl) values(6, now(), 0, 'accessKey6', 'Partner 6', 'POST_PAY', 'P6', true, 'http://partner-6.com');


insert into User(id, active, birthDate, country, county, email, firstName, gender, houseNumberOrName, lastName, mobileNumber, nationality, optInContactByUs, password, postcode, 
registrationAt, street, testUser, title, townOrCity, emailVerified,registrationSiteId) 
values(1, true, '1980-05-07 15:41:56', 'Poland', 'malopolska', 'email@domain.com', 'Adam', 'MALE', '25', 'Abakanowicz', '+48925661772', 'polish', true, 
'Id3AV3MV0yMSsimaAl6f1EyJO+ujiy5GnSL6P1MPXTY=', '23-202', '2013-05-10 15:41:56', 'Jasna', false, 'Mr', 'Warszawa', true, 2);
insert into User(id, active, birthDate, country, county, email, firstName, gender, houseNumberOrName, lastName, mobileNumber, nationality, optInContactByUs, password, postcode, 
registrationAt, street, testUser, title, townOrCity, emailVerified,registrationSiteId) 
values(2, true, '1980-05-05 15:41:56', 'Poland', 'malopolska', 'abc@domain.com', 'Jan', 'MALE', '31', 'Nowak', '+48925661881', 'polish', true, 
'Id3AV3MV0yMSsimaAl6f1EyJO+ujiy5GnSL6P1MPXTY=', '23-300', '2013-05-10 15:31:56', 'Cicha', false, 'Mr', 'Warszawa', true, 2);


insert into UserId(id, createdAt, userId, userIdType, user_id) values(id, '2013-05-10 15:41:56', 'email@domain.com', 'EMAIL', 1);
insert into UserId(id, createdAt, userId, userIdType, user_id) values(id, '2013-05-10 15:31:56', 'abc@domain.com', 'EMAIL', 1);


insert into Tag(id, createdAt, cap, frequency, tagKey, tagStatus, partnerId, imageUrl) values(2, '2013-05-01 15:41:56', 50, 'MONTHLY', 'TAG1', 'ACTIVE', 2, 'http://lorempixel.com/24/24/');
insert into Tag(id, createdAt, cap, frequency, tagKey, tagStatus, partnerId, imageUrl) values(3, '2013-05-01 15:41:56', 100, 'MONTHLY', 'WATCH_2', 'ACTIVE', 2, 'http://lorempixel.com/24/24/');
insert into Tag(id, createdAt, cap, frequency, tagKey, tagStatus, partnerId, imageUrl) values(4, '2013-05-02 15:41:56', 100, 'MONTHLY', 'TAG_PR2', 'ACTIVE', 3, 'http://lorempixel.com/24/24/');
insert into Tag(id, createdAt, cap, frequency, tagKey, tagStatus, partnerId, imageUrl) values(5, '2013-05-02 15:41:56', 10000, 'ONCE', 'FILL_PERSONAL_SECTION', 'ACTIVE', 2, 'http://lorempixel.com/24/24/');
insert into Tag(id, createdAt, cap, frequency, tagKey, tagStatus, partnerId, imageUrl) values(6, '2013-05-02 15:41:56', 10000, 'ONCE', 'FILL_CONTACT_SECTION', 'ACTIVE', 2, 'http://lorempixel.com/24/24/');

insert into Tag(id, createdAt, cap, frequency, tagKey, tagStatus, partnerId, imageUrl) values(7, '2013-05-01 15:41:56', 100, 'MONTHLY', 'ACTION_P4', 'ACTIVE', 4, 'http://lorempixel.com/24/24/');
insert into Tag(id, createdAt, cap, frequency, tagKey, tagStatus, partnerId, imageUrl) values(8, '2013-05-01 15:41:56', 100, 'MONTHLY', 'WATCH_P4', 'ACTIVE', 4, 'http://lorempixel.com/24/24/');
insert into Tag(id, createdAt, cap, frequency, tagKey, tagStatus, partnerId, imageUrl) values(9, '2013-05-01 15:41:56', 100, 'MONTHLY', 'LOGIN_P4', 'ACTIVE', 4, 'http://lorempixel.com/24/24/');

insert into Tag(id, createdAt, cap, frequency, tagKey, tagStatus, partnerId, imageUrl) values(10, '2013-05-01 15:41:56', 100, 'MONTHLY', 'COMMENT_P5', 'ACTIVE', 5, 'http://lorempixel.com/24/24/');
insert into Tag(id, createdAt, cap, frequency, tagKey, tagStatus, partnerId, imageUrl) values(11, '2013-05-01 15:41:56', 100, 'MONTHLY', 'VIDEO_P5', 'ACTIVE', 5, 'http://lorempixel.com/24/24/');

insert into Tag(id, createdAt, cap, frequency, tagKey, tagStatus, partnerId, imageUrl) values(12, '2013-05-01 15:41:56', 100, 'MONTHLY', 'ACTION_P6', 'ACTIVE', 6, 'http://lorempixel.com/24/24/');
insert into Tag(id, createdAt, cap, frequency, tagKey, tagStatus, partnerId, imageUrl) values(13, '2013-05-02 15:41:56', 10000, 'ONCE', 'REGISTRATION', 'ACTIVE', 2, 'http://lorempixel.com/24/24/');



insert into Points(id, createdAt, activityInfo, delta, externalTransactionId, status, tagId, partnerId, userId, balance, sourceURI) values 
(1, '2013-05-13 15:41:56', 'Comment on Page', 1, 'cmm1','CONFIRMED', 2, 2, 1, 1, 'http://niceweb.com/article.html');
insert into Points(id, createdAt, activityInfo, delta, externalTransactionId, status, tagId, partnerId, userId, balance, sourceURI) values 
(2, '2013-05-14 11:12:50', 'Comment on Page', 1, 'cmm2','CONFIRMED', 2, 2, 1, 2, 'http://niceweb.com/article-2.html');
insert into Points(id, createdAt, activityInfo, delta, externalTransactionId, status, tagId, partnerId, userId, balance, sourceURI) values 
(3, '2013-05-14 11:30:50', 'Comment on Page', 1, 'cmm3','CONFIRMED', 2, 2, 1, 3, 'http://niceweb.com/article-25.html');
insert into Points(id, createdAt, activityInfo, delta, externalTransactionId, status, tagId, partnerId, userId, balance, sourceURI) values 
(4, '2013-05-15 14:12:50', 'Watch the video', 5, 'wm1','CONFIRMED', 3, 2, 1, 8, 'http://niceweb.com/video-1.html');
insert into Points(id, createdAt, activityInfo, delta, externalTransactionId, status, tagId, partnerId, userId, balance, sourceURI) values 
(5, '2013-05-15 16:10:50', 'Comment on Page', 1, 'cmm4','CONFIRMED', 2, 2, 1, 9, 'http://niceweb.com/article-10.html');
insert into Points(id, createdAt, activityInfo, delta, externalTransactionId, status, tagId, partnerId, userId, balance, sourceURI) values 
(6, '2013-05-16 11:02:50', 'Comment on Page', 1, 'cmm5','CONFIRMED', 2, 2, 1, 10, 'http://niceweb.com/article-216.html');

insert into Points(id, createdAt, activityInfo, delta, externalTransactionId, status, partnerId, userId, balance, sourceURI, autoConfirm, autoConfirmDate) values 
(7, '2013-05-16 11:12:50', 'Bought a DVD', 40, 'tx1000','CONFIRMED', 1, 1, 50, 'http://shopdvd.com/abc-movie.html', 2, '2013-05-16 00:00:00');

insert into Points(id, createdAt, activityInfo, delta, externalTransactionId, status, tagId, partnerId, userId, balance, sourceURI) values 
(8, '2013-05-16 12:30:50', 'Vote on site', 3, 'v1','CONFIRMED', 7, 4, 1, 53, 'http://mydotcom.com/pull-25.html');

insert into Points(id, createdAt, activityInfo, delta, externalTransactionId, status, tagId, partnerId, userId, balance, sourceURI) values 
(9, '2013-05-16 14:30:50', 'Comment on site', 2, 'c2','CONFIRMED', 10, 5, 1, 55, 'http://xyzsites.com/page,15.html');

insert into Points(id, createdAt, activityInfo, delta, externalTransactionId, status, tagId, partnerId, userId, balance, sourceURI) values 
(10, '2013-05-16 19:12:50', 'Action on page', 5, 'a1','CONFIRMED', 12, 6, 1, 60, 'http;//partner-6.com/landing,page,1.html');

insert into Points(id, createdAt, updatedAt, activityInfo, delta, externalTransactionId, status, partnerId, userId, balance, sourceURI, autoConfirm, autoConfirmDate, reasonText) values 
(11, '2013-05-17 11:12:50', '2013-05-18 10:10:50', 'Bought a furniture', 100, 'tx1001','DECLINED', 1, 1, 60, 'http://shopfurniture.com/coffee-table.html', 2, '2013-05-16 00:00:00', 'Product returned to shop');

insert into Points(id, createdAt, activityInfo, delta, externalTransactionId, status, partnerId, userId, balance, sourceURI, autoConfirm, autoConfirmDate) values 
(12, '2013-05-17 11:20:50', 'Bought a furniture', 150, 'tx1003','PENDING', 1, 1, 60, 'http://shopfurniture.com/oak-coffee-table.html', 60, '2013-07-17 00:00:00');

insert into Points(id, createdAt, activityInfo, delta, externalTransactionId, status, tagId, partnerId, userId, balance, sourceURI) values 
(13, '2013-05-18 14:12:50', 'Watch the video', 5, 'wm2','CONFIRMED', 3, 2, 1, 65, 'http://niceweb.com/video-17.html');
insert into Points(id, createdAt, activityInfo, delta, externalTransactionId, status, tagId, partnerId, userId, balance, sourceURI) values 
(14, '2013-05-18 14:20:50', 'Watch the video', 5, 'wm3','CONFIRMED', 3, 2, 1, 70, 'http://niceweb.com/video-18.html');
insert into Points(id, createdAt, activityInfo, delta, externalTransactionId, status, tagId, partnerId, userId, balance, sourceURI) values 
(15, '2013-05-18 14:30:50', 'Watch the video', 5, 'wm4','CONFIRMED', 3, 2, 1, 75, 'http://niceweb.com/video-19.html');

insert into Points(id, createdAt, activityInfo, delta, externalTransactionId, status, partnerId, userId, balance, sourceURI, autoConfirm, autoConfirmDate) values 
(16, '2013-05-19 11:20:50', 'Bought a furniture', 225, 'tx1005','PENDING', 1, 1, 75, 'http://my-nice-furnitureshop.com/living-room-table.html', 30, '2013-06-19 00:00:00');

insert into Points(id, createdAt, updatedAt, activityInfo, delta, externalTransactionId, status, partnerId, userId, balance, sourceURI, autoConfirm, autoConfirmDate, reasonText) values 
(17, '2013-05-19 11:51:50', '2013-05-21 10:18:50', 'Bought a shoes', 120, 'tx1018','DECLINED', 1, 1, 75, 'http://shoesshop.com/model,19,size,38.html', 10, '2013-05-29 00:00:00', 'Product returned to shop');


insert into Points(id, createdAt, activityInfo, delta, externalTransactionId, status, tagId, partnerId, userId, balance, sourceURI) values 
(18, '2013-05-20 11:30:50', 'Comment on Page', 1, 'cmm10','CONFIRMED', 2, 2, 1, 76, 'http://niceweb.com/article-27.html');
insert into Points(id, createdAt, activityInfo, delta, externalTransactionId, status, tagId, partnerId, userId, balance, sourceURI) values 
(19, '2013-05-21 11:30:50', 'Comment on Page', 1, 'cmm19','CONFIRMED', 2, 2, 1, 77, 'http://niceweb.com/article-28.html');

insert into Points(id, createdAt, activityInfo, delta, externalTransactionId, status, tagId, partnerId, userId, balance, sourceURI) values 
(20, '2013-05-21 14:30:50', 'Watch the video', 5, 'wm48','CONFIRMED', 3, 2, 1, 82, 'http://niceweb.com/video-119.html');

insert into Points(id, createdAt, activityInfo, delta, externalTransactionId, status, tagId, partnerId, userId, balance, sourceURI) values 
(21, '2013-05-22 11:30:50', 'Comment on Page', 1, 'cmm20','CONFIRMED', 2, 2, 1, 83, 'http://niceweb.com/article-210.html');
insert into Points(id, createdAt, activityInfo, delta, externalTransactionId, status, tagId, partnerId, userId, balance, sourceURI) values 
(22, '2013-05-23 11:30:50', 'Comment on Page', 1, 'cmm22','CONFIRMED', 2, 2, 1, 84, 'http://niceweb.com/article-225.html');
insert into Points(id, createdAt, activityInfo, delta, externalTransactionId, status, tagId, partnerId, userId, balance, sourceURI) values 
(23, '2013-05-24 11:30:50', 'Comment on Page', 1, 'cmm18','CONFIRMED', 2, 2, 1, 85, 'http://niceweb.com/article-65.html');

insert into Points(id, createdAt, updatedAt, activityInfo, delta, externalTransactionId, status, partnerId, userId, balance, sourceURI, autoConfirm, autoConfirmDate) values 
(24, '2013-05-25 11:12:50', '2013-05-28 00:00:00', 'Bought a CD', 35, 'tx10142','CONFIRMED', 1, 1, 110, 'http://shopcd.com/my-best-cd.html', 3, '2013-05-28 00:00:00');




insert into User_pointsMap(User_id, pointsMap, pointsMap_KEY) values(1, 110, 'CONFIRMED');
insert into User_pointsMap(User_id, pointsMap, pointsMap_KEY) values(1, 375, 'PENDING');
insert into User_pointsMap(User_id, pointsMap, pointsMap_KEY) values(1, 220, 'DECLINED');


insert into Request(id, createdAt, apiAccessKey, requestType, success, pointsId, userId, idType) values(1, '2013-05-13 15:41:56', 'accessKey', 'ADD_POINTS', true, 1, 'email@domain.com', 'EMAIL');
insert into Request(id, createdAt, apiAccessKey, requestType, success, pointsId, userId, idType) values(2, '2013-05-14 11:12:50', 'accessKey', 'ADD_POINTS', true, 2, 'email@domain.com', 'EMAIL');
insert into Request(id, createdAt, apiAccessKey, requestType, success, pointsId, userId, idType) values(3, '2013-05-14 11:30:50', 'accessKey', 'ADD_POINTS', true, 3, 'email@domain.com', 'EMAIL');
insert into Request(id, createdAt, apiAccessKey, requestType, success, pointsId, userId, message, idType) values(4, '2013-05-13 15:41:56', 'invalidKey', 'ADD_POINTS', false, null, 'email@domain.com', 'Partner with apiKey invalidKey not exists', 'EMAIL');
insert into Request(id, createdAt, apiAccessKey, requestType, success, pointsId, userId, idType) values(5, '2013-05-15 14:12:50', 'accessKey', 'ADD_POINTS', true, 4, 'email@domain.com', 'EMAIL');
insert into Request(id, createdAt, apiAccessKey, requestType, success, pointsId, userId, idType) values(6, '2013-05-15 16:10:50', 'accessKey', 'ADD_POINTS', true, 5, 'email@domain.com', 'EMAIL');
insert into Request(id, createdAt, apiAccessKey, requestType, success, pointsId, userId, idType) values(7, '2013-05-16 11:02:50', 'accessKey', 'ADD_POINTS', true, 6, 'email@domain.com', 'EMAIL');
insert into Request(id, createdAt, apiAccessKey, requestType, success, pointsId, userId, idType) values(8, '2013-05-16 11:12:50', 'affiliateKey', 'ADD_POINTS', true, 7, 'email@domain.com', 'EMAIL');
insert into Request(id, createdAt, apiAccessKey, requestType, success, pointsId, userId, idType) values(9, '2013-05-16 12:30:50', 'accessKey4', 'ADD_POINTS', true, 8, 'email@domain.com', 'EMAIL');
insert into Request(id, createdAt, apiAccessKey, requestType, success, pointsId, userId, idType) values(10, '2013-05-16 14:30:50', 'accessKey5', 'ADD_POINTS', true, 9, 'email@domain.com', 'EMAIL');
insert into Request(id, createdAt, apiAccessKey, requestType, success, pointsId, userId, idType) values(11, '2013-05-16 19:12:50', 'accessKey6', 'ADD_POINTS', true, 10, 'email@domain.com', 'EMAIL');
insert into Request(id, createdAt, apiAccessKey, requestType, success, pointsId, userId, idType) values(12, '2013-05-17 11:12:50', 'affiliateKey', 'ADD_POINTS', true, 11, 'email@domain.com', 'EMAIL');
insert into Request(id, createdAt, apiAccessKey, requestType, success, pointsId, userId, idType) values(13, '2013-05-18 10:10:50', 'affiliateKey', 'CHANGE_PENDING_TO_DECLINED', true, 11, 'email@domain.com', 'EMAIL');
insert into Request(id, createdAt, apiAccessKey, requestType, success, pointsId, userId, idType) values(14, '2013-05-17 11:20:50', 'affiliateKey', 'ADD_POINTS', true, 12, 'email@domain.com', 'EMAIL');
insert into Request(id, createdAt, apiAccessKey, requestType, success, pointsId, userId, idType) values(15, '2013-05-18 14:12:50', 'accessKey', 'ADD_POINTS', true, 13, 'email@domain.com', 'EMAIL');
insert into Request(id, createdAt, apiAccessKey, requestType, success, pointsId, userId, idType) values(16, '2013-05-18 14:20:50', 'accessKey', 'ADD_POINTS', true, 14, 'email@domain.com', 'EMAIL');
insert into Request(id, createdAt, apiAccessKey, requestType, success, pointsId, userId, idType) values(17, '2013-05-18 14:30:50', 'accessKey', 'ADD_POINTS', true, 15, 'email@domain.com', 'EMAIL');
insert into Request(id, createdAt, apiAccessKey, requestType, success, pointsId, userId, idType) values(18, '2013-05-19 11:20:50', 'affiliateKey', 'ADD_POINTS', true, 16, 'email@domain.com', 'EMAIL');
insert into Request(id, createdAt, apiAccessKey, requestType, success, pointsId, userId, idType) values(19, '2013-05-19 11:51:50', 'affiliateKey', 'ADD_POINTS', true, 17, 'email@domain.com', 'EMAIL');
insert into Request(id, createdAt, apiAccessKey, requestType, success, pointsId, userId, idType) values(20, '2013-05-21 10:18:50', 'affiliateKey', 'CHANGE_PENDING_TO_DECLINED', true, 17, 'email@domain.com', 'EMAIL');
insert into Request(id, createdAt, apiAccessKey, requestType, success, pointsId, userId, idType) values(21, '2013-05-20 11:30:50', 'accessKey', 'ADD_POINTS', true, 18, 'email@domain.com', 'EMAIL');
insert into Request(id, createdAt, apiAccessKey, requestType, success, pointsId, userId, idType) values(22, '2013-05-21 11:30:50', 'accessKey', 'ADD_POINTS', true, 19, 'email@domain.com', 'EMAIL');
insert into Request(id, createdAt, apiAccessKey, requestType, success, pointsId, userId, idType) values(23, '2013-05-21 14:30:50', 'accessKey', 'ADD_POINTS', true, 20, 'email@domain.com', 'EMAIL');
insert into Request(id, createdAt, apiAccessKey, requestType, success, pointsId, userId, idType) values(24, '2013-05-22 11:30:50', 'accessKey', 'ADD_POINTS', true, 21, 'email@domain.com', 'EMAIL');
insert into Request(id, createdAt, apiAccessKey, requestType, success, pointsId, userId, idType) values(25, '2013-05-23 11:30:50', 'accessKey', 'ADD_POINTS', true, 22, 'email@domain.com', 'EMAIL');
insert into Request(id, createdAt, apiAccessKey, requestType, success, pointsId, userId, idType) values(26, '2013-05-24 11:30:50', 'accessKey', 'ADD_POINTS', true, 23, 'email@domain.com', 'EMAIL');
insert into Request(id, createdAt, apiAccessKey, requestType, success, pointsId, userId, idType) values(27, '2013-05-25 11:12:50', 'affiliateKey', 'ADD_POINTS', true, 24, 'email@domain.com', 'EMAIL');
insert into Request(id, createdAt, apiAccessKey, requestType, success, pointsId, userId, idType) values(28, '2013-05-28 00:00:00', 'affiliateKey', 'CHANGE_PENDING_TO_CONFIRMED', true, 24, 'email@domain.com', 'EMAIL');




