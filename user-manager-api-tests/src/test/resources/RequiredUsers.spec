User Manager Requirements
=========================
Created by lpanusz on 2016-02-19

Below are listed users that have to exist in each environment in order to run full regression of tests.
     
Test User Details
-----------------
To Create new users via REST API call:

curl -v -H "ContentType: application/json" -X POST -d '' "http://test-proxy-0.iatlimited.com/"

Required users for regression:

PL.NotActive@iatltd.com - joined epoints user, not active in DB, not verified, partner: 2 (epoints.com)

PL.Unverified@iatltd.com - joined epoints user, not activated by fieldName, not verified, partner: 2 (epoints.com)

PL@iatltd.com - password: test - verified email, user verified, lastName: Kowalski, partner: 2 (epoints.com)

PL.Store@iatltd.com - password: test123 (Ejxxe6HGBGhvLGFJv8rSuTzGnkrP64H3LeIQpa/Ji08=) - verified email, user verified, lastName: PLStore, partner: 2 (epoints.com)

United:
user with:
 - "externalIdUnited": "TEST_EXTERNAL_UNITED_ID_ACTIVE_VERIFIED",
 - "externalIdUnited": "TEST_EXTERNAL_UNITED_ID_ACTIVE_NOT_VERIFIED",
 - "externalIdUnited": "TEST_EXTERNAL_UNITED_ID_INACTIVE",
 - "externalIdUnited": "TEST_EXTERNAL_UNITED_ID_ACTIVE_DUPLICATE_1",