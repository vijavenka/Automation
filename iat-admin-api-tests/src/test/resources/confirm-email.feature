Feature: ePoints - Extend confirm-email API to include GDPR values
API will update based on EMail
Password, First name, Last name and Gender are mandatory
tncAccepted and privacyAccepted both should be True then only API will update
marketingPref_SMS and marketingPref_EMAIL can be either True or False even then API will update


@CompleteYourAccount
Scenario Outline: ePoints will extend confirm-email API to include GDPR values

Given ePoints user is logged in with credentials '<EMail>'
When confirm-email PUT API is called it has to validate GDPR with existing values '<Password>' '<Confirm Password>' '<First name>' '<Last name>' '<Gender>' '<tncAccepted>' '<privacyAccepted>' '<marketingPref_SMS>' '<marketingPref_EMAIL>' '<Status>'


@qa
  Examples:
    | 	EMail			|	Password	|	Confirm Password	|	First name	|	Last name	|	Gender	|	tncAccepted		|	privacyAccepted 	|	marketingPref_SMS	| marketingPref_EMAIL	|	Status	|
	|	abc@gmail.com	|	Password	|	Password			|	Alex		|	John		|	Male	|	False			|	False				|	False				|	False				|	400		|
	|	abc@gmail.com	|	Password	|	Password			|	Alex		|	John		|	Male	|	True			|	False				|	False				|	False				|	400		|
	|	abc@gmail.com	|	Password	|	Password			|	Alex		|	John		|	Male	|	False			|	True				|	False				|	False				|	400		|
	|	abc@gmail.com	|	Password	|	Password			|	Alex		|	John		|	Male	|	True			|	True				|	False				|	False				|	201		|
	|	def@gmail.com	|	Password	|	Password			|	Bobb		|	John		|	Male	|	True			|	True				|	True				|	False				|	201		|
	|	ghi@gmail.com	|	Password	|	Password			|	Cobb		|	John		|	Male	|	True			|	True				|	False				|	True				|	201		|
	