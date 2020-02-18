Feature: IAT ADMIN - birthday events - API to return list of selected months birthdays
When User logged in with Manager Super admin role then birthday count will return company level
When User logged in with User Super admin role then birthday count will return company level
When User logged in with Manager None role then birthday count is limited to department and sub-department if any
When allEvents=false then Birthday API will retrieve records only if the age is 18/21/30/40/50/60
For allEvents=false API will retrieve LeapYear records too. Example User birthday 29/02/1976 then API year should also contain leap year ie 2016 Feb
For logged in user return list of records for selected month with data 20 per call, date order from 1st to 31st


@Events_Birthdate_workAnniversary
Scenario Outline: API to return list of selected months birthday data.
Data will have FullName, ManagerName, DepartmentName, Date, Age

Given IAT Admin user is logged in with credentials '<login_password>'
When Get list of birthday or aniversary '<name_year_month>' '<allEvents_limit_offSet>'
When  

	  
@qa
  Examples:
    | login_password                        					|    name_year_month    | allEvents_limit_offSet    |
#birthdate	
  #Manager with None
    | testdept6_1@gmail.com,password      						|    birthdate,2018,1   | true,20,0                 |
	| testdept6_1@gmail.com,password      						|    birthdate,2018,1   | true,20,1                 |
    | testdept6_1@gmail.com,password      						|    birthdate,2018,2   | true,20,0 				|
    | testdept6_1@gmail.com,password      						|    birthdate,2018,3   | true,20,0 				|	
    | testdept6_1@gmail.com,password      						|    birthdate,2018,4   | true,20,0 				|
    | testdept6_1@gmail.com,password      						|    birthdate,2018,5   | true,20,0 				|
    | testdept6_1@gmail.com,password      						|    birthdate,2018,6   | true,20,0 				|
    | testdept6_1@gmail.com,password      						|    birthdate,2018,7   | true,20,0 				|
    | testdept6_1@gmail.com,password      						|    birthdate,2018,8   | true,20,0 				|
	| testdept6_1@gmail.com,password      						|    birthdate,2018,9   | true,20,0 				|
    | testdept6_1@gmail.com,password      						|    birthdate,2018,10  | true,20,0 				|
    | testdept6_1@gmail.com,password      						|    birthdate,2018,11  | true,20,0 				|
    | testdept6_1@gmail.com,password      						|    birthdate,2018,12  | true,20,0 				|
  #Manager with SuperAdmin
    | manager-superadmin@gmail.com,password 					|    birthdate,2018,1   | true,20,0                 |
    | manager-superadmin@gmail.com,password 					|    birthdate,2018,1   | true,20,1                 |
    | manager-superadmin@gmail.com,password 					|    birthdate,2018,2   | true,20,0 				|
    | manager-superadmin@gmail.com,password 					|    birthdate,2018,3   | true,20,0 				|	
    | manager-superadmin@gmail.com,password 					|    birthdate,2018,4   | true,20,0 				|
    | manager-superadmin@gmail.com,password 					|    birthdate,2018,5   | true,20,0 				|
    | manager-superadmin@gmail.com,password 					|    birthdate,2018,6   | true,20,0 				|
    | manager-superadmin@gmail.com,password 					|    birthdate,2018,7   | true,20,0 				|
	| manager-superadmin@gmail.com,password 					|    birthdate,2018,8   | true,20,0 				|	
    | manager-superadmin@gmail.com,password 					|    birthdate,2018,9   | true,20,0 				|
    | manager-superadmin@gmail.com,password 					|    birthdate,2018,10  | true,20,0 				|
    | manager-superadmin@gmail.com,password 					|    birthdate,2018,11  | true,20,0 				|
    | manager-superadmin@gmail.com,password 					|    birthdate,2018,12  | true,20,0 				|
  #User with SuperAdmin
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|  	 birthdate,2018,1   | true,20,0                 |
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    birthdate,2018,1   | true,20,0                 |
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    birthdate,2018,2   | true,20,0                 | 
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    birthdate,2018,3   | true,20,0                 |
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    birthdate,2018,4   | true,20,0                 |
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    birthdate,2018,5   | true,20,0                 | 
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    birthdate,2018,6   | true,20,0                 |
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    birthdate,2018,7   | true,20,0                 |
	| andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    birthdate,2018,8   | true,20,0                 |
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    birthdate,2018,9   | true,20,0                 |
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    birthdate,2018,10  | true,20,0                 | 
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    birthdate,2018,11  | true,20,0                 |
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    birthdate,2018,12  | true,20,0                 |
#allEvents=false
  #Manager with None
    | testdept6_1@gmail.com,password      						|    birthdate,2018,1   | false,20,0                 |
	| testdept6_1@gmail.com,password      						|    birthdate,2018,1   | false,20,1                 |
    | testdept6_1@gmail.com,password      						|    birthdate,2018,2   | false,20,0 				|
    | testdept6_1@gmail.com,password      						|    birthdate,2018,3   | false,20,0 				|	
    | testdept6_1@gmail.com,password      						|    birthdate,2018,4   | false,20,0 				|
    | testdept6_1@gmail.com,password      						|    birthdate,2018,5   | false,20,0 				|
    | testdept6_1@gmail.com,password      						|    birthdate,2018,6   | false,20,0 				|
    | testdept6_1@gmail.com,password      						|    birthdate,2018,7   | false,20,0 				|
    | testdept6_1@gmail.com,password      						|    birthdate,2018,8   | false,20,0 				|
	| testdept6_1@gmail.com,password      						|    birthdate,2018,9   | false,20,0 				|
    | testdept6_1@gmail.com,password      						|    birthdate,2018,10  | false,20,0 				|
    | testdept6_1@gmail.com,password      						|    birthdate,2018,11  | false,20,0 				|
    | testdept6_1@gmail.com,password      						|    birthdate,2018,12  | false,20,0 				|
  #Manager with SuperAdmin
    | manager-superadmin@gmail.com,password 					|    birthdate,2018,1   | false,20,0                |
    | manager-superadmin@gmail.com,password 					|    birthdate,2018,1   | false,20,1                |
    | manager-superadmin@gmail.com,password 					|    birthdate,2018,2   | false,20,0 				|
    | manager-superadmin@gmail.com,password 					|    birthdate,2018,3   | false,20,0 				|	
    | manager-superadmin@gmail.com,password 					|    birthdate,2018,4   | false,20,0 				|
    | manager-superadmin@gmail.com,password 					|    birthdate,2018,5   | false,20,0 				|
    | manager-superadmin@gmail.com,password 					|    birthdate,2018,6   | false,20,0 				|
    | manager-superadmin@gmail.com,password 					|    birthdate,2018,7   | false,20,0 				|
	| manager-superadmin@gmail.com,password 					|    birthdate,2018,8   | false,20,0 				|	
    | manager-superadmin@gmail.com,password 					|    birthdate,2018,9   | false,20,0 				|
    | manager-superadmin@gmail.com,password 					|    birthdate,2018,10  | false,20,0 				|
    | manager-superadmin@gmail.com,password 					|    birthdate,2018,11  | false,20,0 				|
    | manager-superadmin@gmail.com,password 					|    birthdate,2018,12  | false,20,0 				|
  #User with SuperAdmin
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|  	 birthdate,2018,1   | false,20,0                |
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    birthdate,2018,1   | false,20,0                |
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    birthdate,2018,2   | false,20,0                | 
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    birthdate,2018,3   | false,20,0                |
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    birthdate,2018,4   | false,20,0                |
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    birthdate,2018,5   | false,20,0                | 
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    birthdate,2018,6   | false,20,0                |
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    birthdate,2018,7   | false,20,0                |
	| andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    birthdate,2018,8   | false,20,0                |
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    birthdate,2018,9   | false,20,0                |
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    birthdate,2018,10  | false,20,0                | 
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    birthdate,2018,11  | false,20,0                |
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    birthdate,2018,12  | false,20,0                |	  
#workAnniversary
  #Manager with None
    | testdept6_1@gmail.com,password      						|    workAnniversary,2018,1   | true,20,0                 |
	| testdept6_1@gmail.com,password      						|    workAnniversary,2018,1   | true,20,1                 |
    | testdept6_1@gmail.com,password      						|    workAnniversary,2018,2   | true,20,0 				|
    | testdept6_1@gmail.com,password      						|    workAnniversary,2018,3   | true,20,0 				|	
    | testdept6_1@gmail.com,password      						|    workAnniversary,2018,4   | true,20,0 				|
    | testdept6_1@gmail.com,password      						|    workAnniversary,2018,5   | true,20,0 				|
    | testdept6_1@gmail.com,password      						|    workAnniversary,2018,6   | true,20,0 				|
    | testdept6_1@gmail.com,password      						|    workAnniversary,2018,7   | true,20,0 				|
    | testdept6_1@gmail.com,password      						|    workAnniversary,2018,8   | true,20,0 				|
	| testdept6_1@gmail.com,password      						|    workAnniversary,2018,9   | true,20,0 				|
    | testdept6_1@gmail.com,password      						|    workAnniversary,2018,10  | true,20,0 				|
    | testdept6_1@gmail.com,password      						|    workAnniversary,2018,11  | true,20,0 				|
    | testdept6_1@gmail.com,password      						|    workAnniversary,2018,12  | true,20,0 				|
  #Manager with SuperAdmin
    | manager-superadmin@gmail.com,password 					|    workAnniversary,2018,1   | true,20,0                 |
    | manager-superadmin@gmail.com,password 					|    workAnniversary,2018,1   | true,20,1                 |
    | manager-superadmin@gmail.com,password 					|    workAnniversary,2018,2   | true,20,0 				|
    | manager-superadmin@gmail.com,password 					|    workAnniversary,2018,3   | true,20,0 				|	
    | manager-superadmin@gmail.com,password 					|    workAnniversary,2018,4   | true,20,0 				|
    | manager-superadmin@gmail.com,password 					|    workAnniversary,2018,5   | true,20,0 				|
    | manager-superadmin@gmail.com,password 					|    workAnniversary,2018,6   | true,20,0 				|
    | manager-superadmin@gmail.com,password 					|    workAnniversary,2018,7   | true,20,0 				|
	| manager-superadmin@gmail.com,password 					|    workAnniversary,2018,8   | true,20,0 				|	
    | manager-superadmin@gmail.com,password 					|    workAnniversary,2018,9   | true,20,0 				|
    | manager-superadmin@gmail.com,password 					|    workAnniversary,2018,10  | true,20,0 				|
    | manager-superadmin@gmail.com,password 					|    workAnniversary,2018,11  | true,20,0 				|
    | manager-superadmin@gmail.com,password 					|    workAnniversary,2018,12  | true,20,0 				|
  #User with SuperAdmin
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|  	 workAnniversary,2018,1   | true,20,0                 |
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    workAnniversary,2018,1   | true,20,0                 |
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    workAnniversary,2018,2   | true,20,0                 | 
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    workAnniversary,2018,3   | true,20,0                 |
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    workAnniversary,2018,4   | true,20,0                 |
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    workAnniversary,2018,5   | true,20,0                 | 
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    workAnniversary,2018,6   | true,20,0                 |
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    workAnniversary,2018,7   | true,20,0                 |
	| andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    workAnniversary,2018,8   | true,20,0                 |
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    workAnniversary,2018,9   | true,20,0                 |
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    workAnniversary,2018,10  | true,20,0                 | 
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    workAnniversary,2018,11  | true,20,0                 |
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    workAnniversary,2018,12  | true,20,0                 |
#allEvents=false
  #Manager with None
    | testdept6_1@gmail.com,password      						|    workAnniversary,2018,1   | false,20,0                 |
	| testdept6_1@gmail.com,password      						|    workAnniversary,2018,1   | false,20,1                 |
    | testdept6_1@gmail.com,password      						|    workAnniversary,2018,2   | false,20,0 				|
    | testdept6_1@gmail.com,password      						|    workAnniversary,2018,3   | false,20,0 				|	
    | testdept6_1@gmail.com,password      						|    workAnniversary,2018,4   | false,20,0 				|
    | testdept6_1@gmail.com,password      						|    workAnniversary,2018,5   | false,20,0 				|
    | testdept6_1@gmail.com,password      						|    workAnniversary,2018,6   | false,20,0 				|
    | testdept6_1@gmail.com,password      						|    workAnniversary,2018,7   | false,20,0 				|
    | testdept6_1@gmail.com,password      						|    workAnniversary,2018,8   | false,20,0 				|
	| testdept6_1@gmail.com,password      						|    workAnniversary,2018,9   | false,20,0 				|
    | testdept6_1@gmail.com,password      						|    workAnniversary,2018,10  | false,20,0 				|
    | testdept6_1@gmail.com,password      						|    workAnniversary,2018,11  | false,20,0 				|
    | testdept6_1@gmail.com,password      						|    workAnniversary,2018,12  | false,20,0 				|
  #Manager with SuperAdmin
    | manager-superadmin@gmail.com,password 					|    workAnniversary,2018,1   | false,20,0                |
    | manager-superadmin@gmail.com,password 					|    workAnniversary,2018,1   | false,20,1                |
    | manager-superadmin@gmail.com,password 					|    workAnniversary,2018,2   | false,20,0 				|
    | manager-superadmin@gmail.com,password 					|    workAnniversary,2018,3   | false,20,0 				|	
    | manager-superadmin@gmail.com,password 					|    workAnniversary,2018,4   | false,20,0 				|
    | manager-superadmin@gmail.com,password 					|    workAnniversary,2018,5   | false,20,0 				|
    | manager-superadmin@gmail.com,password 					|    workAnniversary,2018,6   | false,20,0 				|
    | manager-superadmin@gmail.com,password 					|    workAnniversary,2018,7   | false,20,0 				|
	| manager-superadmin@gmail.com,password 					|    workAnniversary,2018,8   | false,20,0 				|	
    | manager-superadmin@gmail.com,password 					|    workAnniversary,2018,9   | false,20,0 				|
    | manager-superadmin@gmail.com,password 					|    workAnniversary,2018,10  | false,20,0 				|
    | manager-superadmin@gmail.com,password 					|    workAnniversary,2018,11  | false,20,0 				|
    | manager-superadmin@gmail.com,password 					|    workAnniversary,2018,12  | false,20,0 				|
  #User with SuperAdmin
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|  	 workAnniversary,2018,1   | false,20,0                |
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    workAnniversary,2018,1   | false,20,0                |
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    workAnniversary,2018,2   | false,20,0                | 
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    workAnniversary,2018,3   | false,20,0                |
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    workAnniversary,2018,4   | false,20,0                |
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    workAnniversary,2018,5   | false,20,0                | 
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    workAnniversary,2018,6   | false,20,0                |
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    workAnniversary,2018,7   | false,20,0                |
	| andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    workAnniversary,2018,8   | false,20,0                |
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    workAnniversary,2018,9   | false,20,0                |
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    workAnniversary,2018,10  | false,20,0                | 
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    workAnniversary,2018,11  | false,20,0                |
    | andy_test_super_admin_company2@iat.admin.pl,P@ssw0rd    	|    workAnniversary,2018,12  | false,20,0                |
	
	