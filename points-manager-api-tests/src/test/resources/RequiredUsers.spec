Points Manager Requirements
=========================
Below are listed users that have to exist in each environment in order to run full regression of tests.
     
Test User Details
-----------------
To Create new users via REST API call:

curl -v -H "ContentType: application/json" -X POST -d '' "http://test-proxy-0.iatlimited.com/"

Required users for regression:

pm.api.automation@gmail.com - proper account
pm.api.automation2@gmail.com - proper account
pm.api.automationnotverified@gmail.com - not verified account
pm.api.automationnotactive@gmail.com - not active account
pm.api.automationnotenoughpoints@gmail.com - proper account with small number of points

UNITED_TEST@iat.test - externalIdUnited: 99999 - newPassword:2948167738