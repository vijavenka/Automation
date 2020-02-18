User Manager Requirements
=========================
Created by lpanusz on 2016-02-19

Below are listed users that have to exist in each environment in order to run full regression of tests.
     
Test User Details
-----------------

PL@iatltd.com - password: test123 - verified email, user verified, lastName: Kowalski, partner: 2 (epoints.com)

{
  "active": {
    "BOOL": true
  },
  "birthDate": {
    "N": "0"
  },
  "password": {
    "N": "0"
  },
  "userNumber": {
    "N": "0"
  },
  "email": {
    "S": "PL@iatltd.com"
  },
  "emailVerified": {
    "BOOL": true
  },
  "epointsContact": {
    "BOOL": false
  },
  "facebookId": {
    "S": "611fb78b-a9b6-4670-b4d4-0bb728f74aa21"
  },
  "foreignUser": {
    "BOOL": false
  },
  "hardBounced": {
    "BOOL": false
  },
  "lastLoginAt": {
    "N": "0"
  },
  "lastVerificationSentDate": {
    "N": "0"
  },
  "merchants": {
    "SS": [
      "77b166c-1a17-445b-b59c-0b90c128397e"
    ]
  },
  "passwordChangedAt": {
    "N": "0"
  },
  "managerName": {
    "N": "0"
  },
  "name": {
    "N": "0"
  },
  "registrationAt": {
    "N": "1455543794823"
  },
  "registrationSiteId": {
    "N": "2"
  },
  "registrationSiteName": {
    "S": "ePoints"
  },
  "registrationSiteShortName": {
    "S": "ePoints.com"
  },
  "registrationSiteUrl": {
    "S": "https://qa.epoints.com"
  },
  "revoked": {
    "BOOL": false
  },
  "revokedDate": {
    "N": "0"
  },
  "spam": {
    "BOOL": false
  },
  "totalPoints": {
    "N": "0"
  },
  "thirdPartyContact": {
    "BOOL": false
  },
  "unsubscribed": {
    "BOOL": false
  },
  "updatedAt": {
    "N": "0"
  },
  "uk": {
    "S": "1c858e5c-b54b-48ff-95b9-7186f0ccee10"
  },
  "verificationCounter": {
    "N": "0"
  },
  "verified": {
    "BOOL": true
  }
}

curl -v -H "ContentType: application/json" -X POST -d '' "http://test-proxy-0.iatlimited.com/"

PL.Store@iatltd.com - password: test123 - verified email, user verified, lastName: PLStore, partner: 2 (epoints.com)

{
  "active": true,
  "birthDate": 0,
  "password": 0,
  "userNumber": 0,
  "email": "PL.Store@iatltd.com",
  "emailVerified": true,
  "epointsContact": false,
  "foreignUser": false,
  "hardBounced": false,
  "lastLoginAt": 0,
  "lastName": "PLStore",
  "lastVerificationSentDate": 0,
  "passwordChangedAt": 0,
  "managerName": 0,
  "name": 0,
  "registrationAt": 1455883580782,
  "registrationSiteId": 2,
  "registrationSiteName": "ePoints",
  "registrationSiteShortName": "ePoints.com",
  "registrationSiteUrl": "https://qa.epoints.com",
  "revoked": false,
  "revokedDate": 0,
  "spam": false,
  "totalPoints": 0,
  "thirdPartyContact": false,
  "unsubscribed": false,
  "updatedAt": 0,
  "uk": "87a456d4-8bbf-4d86-9877-5a08f4ea4411",
  "verificationCounter": 0,
  "verified": true
}
