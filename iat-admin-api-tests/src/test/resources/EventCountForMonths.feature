Feature: IAT ADMIN - birthday events - API to return list of selected months birthdays
When User logged in with Manager Super admin role then birthday count will return company level
When User logged in with User Super admin role then birthday count will return company level
When User logged in with Manager None role then birthday count is limited to department and sub-department if any
When allEvents=false then Birthday API will retrieve records only if the age is 18/21/30/40/50/60
For allEvents=false API will retrieve LeapYear records too. Example User birthday 29/02/1976 then API year should also contain leap year ie 2016 Feb
For logged in user return list of records for selected month with data 20 per call, date order from 1st to 31st


  @Events_birthdate_workAnniversary_count
  Scenario Outline: API to return next 12 months birthday counts

    Given IAT Admin user is logged in with credentials '<login_password>'
    When Get count of birthday or aniversary '<name>' '<allEvents>'


@qa
  Examples:
    | login_password                        					|    name    | allEvents    |
#birthdate	
  #Manager with None
    | testdept6_1@gmail.com,password      						|    birthdate   | true     |
	| testdept6_1@gmail.com,password      						|    birthdate   | true     |
    | testdept6_1@gmail.com,password      						|    birthdate   | true		|
    | testdept6_1@gmail.com,password      						|    birthdate   | true		|	
    | testdept6_1@gmail.com,password      						|    birthdate   | true		|
    | testdept6_1@gmail.com,password      						|    birthdate   | true		|
    | testdept6_1@gmail.com,password      						|    birthdate   | true 	|
    | testdept6_1@gmail.com,password      						|    birthdate   | true 	|
    | testdept6_1@gmail.com,password      						|    birthdate   | true 	|
	| testdept6_1@gmail.com,password      						|    birthdate   | true 	|
    | testdept6_1@gmail.com,password      						|    birthdate  | true 		|
    | testdept6_1@gmail.com,password      						|    birthdate  | true 		|
    | testdept6_1@gmail.com,password      						|    birthdate  | true 		|
  #Manager with SuperAdmin
    | manager-superadmin@gmail.com,password 					|    birthdate   | true     |
    | manager-superadmin@gmail.com,password 					|    birthdate   | true     |
    | manager-superadmin@gmail.com,password 					|    birthdate   | true 	|
    | manager-superadmin@gmail.com,password 					|    birthdate   | true 	|	
    | manager-superadmin@gmail.com,password 					|    birthdate   | true 	|
    | manager-superadmin@gmail.com,password 					|    birthdate   | true 	|
    | manager-superadmin@gmail.com,password 					|    birthdate   | true 	|
    | manager-superadmin@gmail.com,password 					|    birthdate   | true 	|
	| manager-superadmin@gmail.com,password 					|    birthdate   | true 	|	
    | manager-superadmin@gmail.com,password 					|    birthdate   | true 	|
    | manager-superadmin@gmail.com,password 					|    birthdate  | true 		|
    | manager-superadmin@gmail.com,password 					|    birthdate  | true 		|
    | manager-superadmin@gmail.com,password 					|    birthdate  | true 		|
  #User with SuperAdmin
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|  	 birthdate   | true     |
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    birthdate   | true     |
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    birthdate   | true     | 
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    birthdate   | true     |
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    birthdate   | true     |
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    birthdate   | true     | 
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    birthdate   | true     |
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    birthdate   | true     |
	| andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    birthdate   | true     |
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    birthdate   | true     |
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    birthdate  | true      | 
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    birthdate  | true      |
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    birthdate  | true      |
#allEvents=false
  #Manager with None
    | testdept6_1@gmail.com,password      						|    birthdate   | false    |
	| testdept6_1@gmail.com,password      						|    birthdate   | false    |
    | testdept6_1@gmail.com,password      						|    birthdate   | false 	|
    | testdept6_1@gmail.com,password      						|    birthdate   | false 	|	
    | testdept6_1@gmail.com,password      						|    birthdate   | false 	|
    | testdept6_1@gmail.com,password      						|    birthdate   | false 	|
    | testdept6_1@gmail.com,password      						|    birthdate   | false 	|
    | testdept6_1@gmail.com,password      						|    birthdate   | false 	|
    | testdept6_1@gmail.com,password      						|    birthdate   | false 	|
	| testdept6_1@gmail.com,password      						|    birthdate   | false 	|
    | testdept6_1@gmail.com,password      						|    birthdate  | false 	|
    | testdept6_1@gmail.com,password      						|    birthdate  | false 	|
    | testdept6_1@gmail.com,password      						|    birthdate  | false 	|
  #Manager with SuperAdmin
    | manager-superadmin@gmail.com,password 					|    birthdate   | false    |
    | manager-superadmin@gmail.com,password 					|    birthdate   | false    |
    | manager-superadmin@gmail.com,password 					|    birthdate   | false 	|
    | manager-superadmin@gmail.com,password 					|    birthdate   | false 	|	
    | manager-superadmin@gmail.com,password 					|    birthdate   | false 	|
    | manager-superadmin@gmail.com,password 					|    birthdate   | false 	|
    | manager-superadmin@gmail.com,password 					|    birthdate   | false 	|
    | manager-superadmin@gmail.com,password 					|    birthdate   | false 	|
	| manager-superadmin@gmail.com,password 					|    birthdate   | false 	|	
    | manager-superadmin@gmail.com,password 					|    birthdate   | false 	|
    | manager-superadmin@gmail.com,password 					|    birthdate  | false 	|
    | manager-superadmin@gmail.com,password 					|    birthdate  | false 	|
    | manager-superadmin@gmail.com,password 					|    birthdate  | false 	|
  #User with SuperAdmin
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|  	 birthdate   | false    |
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    birthdate   | false    |
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    birthdate   | false    | 
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    birthdate   | false    |
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    birthdate   | false    |
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    birthdate   | false    | 
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    birthdate   | false    |
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    birthdate   | false    |
	| andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    birthdate   | false    |
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    birthdate   | false    |
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    birthdate  | false     | 
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    birthdate  | false     |
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    birthdate  | false     |	  
#workAnniversary
  #Manager with None
    | testdept6_1@gmail.com,password      						|    workAnniversary   | true       |
	| testdept6_1@gmail.com,password      						|    workAnniversary   | true       |
    | testdept6_1@gmail.com,password      						|    workAnniversary   | true 		|
    | testdept6_1@gmail.com,password      						|    workAnniversary   | true 		|	
    | testdept6_1@gmail.com,password      						|    workAnniversary   | true 		|
    | testdept6_1@gmail.com,password      						|    workAnniversary   | true 		|
    | testdept6_1@gmail.com,password      						|    workAnniversary   | true 		|
    | testdept6_1@gmail.com,password      						|    workAnniversary   | true 		|
    | testdept6_1@gmail.com,password      						|    workAnniversary   | true 		|
	| testdept6_1@gmail.com,password      						|    workAnniversary   | true 		|
    | testdept6_1@gmail.com,password      						|    workAnniversary  | true 		|
    | testdept6_1@gmail.com,password      						|    workAnniversary  | true 		|
    | testdept6_1@gmail.com,password      						|    workAnniversary  | true 		|
  #Manager with SuperAdmin
    | manager-superadmin@gmail.com,password 					|    workAnniversary   | true       |
    | manager-superadmin@gmail.com,password 					|    workAnniversary   | true       |
    | manager-superadmin@gmail.com,password 					|    workAnniversary   | true 		|
    | manager-superadmin@gmail.com,password 					|    workAnniversary   | true 		|	
    | manager-superadmin@gmail.com,password 					|    workAnniversary   | true 		|
    | manager-superadmin@gmail.com,password 					|    workAnniversary   | true 		|
    | manager-superadmin@gmail.com,password 					|    workAnniversary   | true 		|
    | manager-superadmin@gmail.com,password 					|    workAnniversary   | true 		|
	| manager-superadmin@gmail.com,password 					|    workAnniversary   | true 		|	
    | manager-superadmin@gmail.com,password 					|    workAnniversary   | true 		|
    | manager-superadmin@gmail.com,password 					|    workAnniversary  | true 		|
    | manager-superadmin@gmail.com,password 					|    workAnniversary  | true 		|
    | manager-superadmin@gmail.com,password 					|    workAnniversary  | true 		|
  #User with SuperAdmin
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|  	 workAnniversary   | true       |
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    workAnniversary   | true       |
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    workAnniversary   | true       | 
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    workAnniversary   | true       |
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    workAnniversary   | true       |
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    workAnniversary   | true       | 
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    workAnniversary   | true       |
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    workAnniversary   | true       |
	| andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    workAnniversary   | true       |
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    workAnniversary   | true       |
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    workAnniversary  | true        | 
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    workAnniversary  | true        |
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    workAnniversary  | true        |
#allEvents=false
  #Manager with None
    | testdept6_1@gmail.com,password      						|    workAnniversary   | false      |
	| testdept6_1@gmail.com,password      						|    workAnniversary   | false      |
    | testdept6_1@gmail.com,password      						|    workAnniversary   | false 		|
    | testdept6_1@gmail.com,password      						|    workAnniversary   | false 		|	
    | testdept6_1@gmail.com,password      						|    workAnniversary   | false 		|
    | testdept6_1@gmail.com,password      						|    workAnniversary   | false 		|
    | testdept6_1@gmail.com,password      						|    workAnniversary   | false 		|
    | testdept6_1@gmail.com,password      						|    workAnniversary   | false 		|
    | testdept6_1@gmail.com,password      						|    workAnniversary   | false 		|
	| testdept6_1@gmail.com,password      						|    workAnniversary   | false 		|
    | testdept6_1@gmail.com,password      						|    workAnniversary  | false 		|
    | testdept6_1@gmail.com,password      						|    workAnniversary  | false 		|
    | testdept6_1@gmail.com,password      						|    workAnniversary  | false 		|
  #Manager with SuperAdmin
    | manager-superadmin@gmail.com,password 					|    workAnniversary   | false      |
    | manager-superadmin@gmail.com,password 					|    workAnniversary   | false      |
    | manager-superadmin@gmail.com,password 					|    workAnniversary   | false 		|
    | manager-superadmin@gmail.com,password 					|    workAnniversary   | false 		|	
    | manager-superadmin@gmail.com,password 					|    workAnniversary   | false 		|
    | manager-superadmin@gmail.com,password 					|    workAnniversary   | false 		|
    | manager-superadmin@gmail.com,password 					|    workAnniversary   | false 		|
    | manager-superadmin@gmail.com,password 					|    workAnniversary   | false 		|
	| manager-superadmin@gmail.com,password 					|    workAnniversary   | false 		|	
    | manager-superadmin@gmail.com,password 					|    workAnniversary   | false 		|
    | manager-superadmin@gmail.com,password 					|    workAnniversary  | false 		|
    | manager-superadmin@gmail.com,password 					|    workAnniversary  | false 		|
    | manager-superadmin@gmail.com,password 					|    workAnniversary  | false 		|
  #User with SuperAdmin
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|  	 workAnniversary   | false      |
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    workAnniversary   | false      |
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    workAnniversary   | false      | 
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    workAnniversary   | false      |
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    workAnniversary   | false      |
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    workAnniversary   | false      | 
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    workAnniversary   | false      |
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    workAnniversary   | false      |
	| andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    workAnniversary   | false      |
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    workAnniversary   | false      |
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    workAnniversary  | false       | 
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    workAnniversary  | false       |
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    workAnniversary  | false       |
