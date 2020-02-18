package com.iat.validators;

import com.iat.Config;
import com.iat.domain.EmailChange;
import com.iat.domain.User;
import com.iat.domain.UserDetailsDoorman;
import com.iat.domain.UserGroupDoorman;
import com.iat.domain.departmentsStructure.Department;
import com.iat.repository.PartnerRepository;
import com.iat.repository.UserRepository;
import com.iat.repository.impl.PartnerRepositoryImpl;
import com.iat.repository.impl.UserRepositoryImpl;
import com.iat.utils.DataExchanger;
import com.iat.utils.JdbcDatabaseConnector;
import com.iat.utils.ResponseContainer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static com.iat.utils.DateTimeUtil.Format;
import static com.iat.utils.DateTimeUtil.convertToDate;
import static com.iat.utils.matchers.CustomMatchers.containsStringIgnoringCase;
import static java.lang.Integer.parseInt;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.exparity.hamcrest.date.DateMatchers.sameOrAfter;
import static org.exparity.hamcrest.date.DateMatchers.sameOrBefore;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UsersValidator {

    private GenericValidator genericValidator = new GenericValidator();
    private DataExchanger dataExchanger = DataExchanger.getInstance();
    private JdbcDatabaseConnector mySQLConnector_iatAdmin = new JdbcDatabaseConnector(Config.mysqlConnectionPoolIatAdmin);
    private PartnerRepository partnerRepository = new PartnerRepositoryImpl();
    private UserRepository userRepository = new UserRepositoryImpl();

    public void validateUserDetails(ResponseContainer response) {
        String employeeNumber = dataExchanger.getUserObject().getEmployeeNumber();
        String email = dataExchanger.getUserObject().getEmail();
        String name = dataExchanger.getUserObject().getName();
        String gender = dataExchanger.getUserObject().getGender();
        String birthDate = dataExchanger.getUserObject().getBirthDate();
        Department department = dataExchanger.getUserObject().getDepartment();
        String role = dataExchanger.getUserObject().getRole();
        String adminRole = dataExchanger.getUserObject().getAdminRole();
        String status = dataExchanger.getUserObject().getStatus();
        String companyStartDate = dataExchanger.getUserObject().getCompanyStartDate();
        String companyUsername = dataExchanger.getUserObject().getCompanyUsername();

        if (employeeNumber != null) {
            String extracted = response.getString("employeeNumber");
            assertThat("employeeNumber field is invalid!", extracted, is(employeeNumber));
        }
        if (email != null) {
            String extracted = response.getString("email");
            assertThat("email field is invalid!", extracted, containsStringIgnoringCase(email));
        }
        if (name != null) {
            String extracted = response.getString("name");
            assertThat("name field is invalid!", extracted, is(name));
        }
        if (gender != null) {
            String extracted = response.getString("gender");
            assertThat("gender field is invalid!", extracted, is(equalToIgnoringCase(gender)));
        }
        if (birthDate != null) {
            String extracted = response.getString("birthDate");
            assertThat("birthDate field is invalid!", extracted, is(birthDate));
        }
        if (department != null) {
            String extracted = response.getString("department.name");
            assertThat("department.name field is invalid!", extracted, is(department.getName()));
        }
        if (role != null) {
            String extracted = response.getString("role");
            assertThat("role field is invalid!", extracted, is(equalToIgnoringCase(role)));
        }
        if (adminRole != null) {
            String extracted = response.getString("adminRole");
            assertThat("adminRole field is invalid!", extracted, is(equalToIgnoringCase(adminRole)));
        }
        if (status != null) {
            String extracted = response.getString("status");
            assertThat("status field is invalid!", extracted, is(equalToIgnoringCase(status)));
        }
        if (companyStartDate != null) {
            String extracted = response.getString("companyStartDate");
            assertThat("companyStartDate field is invalid!", extracted, is(equalToIgnoringCase(companyStartDate)));
        }
        if (companyUsername != "" && companyUsername != null) {
            String extracted = response.getString("companyUsername");
            assertThat("companyUsername field is invalid!", extracted, containsStringIgnoringCase(companyUsername));
        }
    }

    public void validateUserDetailsInDynamoAndMySql(UserGroupDoorman response, String statusToValidate) throws Throwable {

        String employeeNumber = dataExchanger.getUserObject().getEmployeeNumber();
        String email = dataExchanger.getUserObject().getEmail();
        String name = dataExchanger.getUserObject().getName();
        String gender = dataExchanger.getUserObject().getGender();
        String birthDate = dataExchanger.getUserObject().getBirthDate();
        Department department = dataExchanger.getUserObject().getDepartment();
        String role = dataExchanger.getUserObject().getRole();
        String adminRole = dataExchanger.getUserObject().getAdminRole();
        String status = dataExchanger.getUserObject().getStatus();
        String companyStartDate = dataExchanger.getUserObject().getCompanyStartDate();
        String companyUsername = dataExchanger.getUserObject().getCompanyUsername();

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd X");
        Date date;

        if (employeeNumber != null)
            assertThat("Incorrect value in empNumber field", response.getEmpNumber(), is(employeeNumber));

        if (email != null)
            assertThat("Incorrect value in email field", response.getEmail(), is(email));

        if (name != null)
            assertThat("Incorrect value in name field", (response.getFirstName() + " " + response.getLastName()).trim(), is(name));

        if (gender != null)
            assertThat("Incorrect value in gender field", response.getGender(), is(gender));

        if (birthDate != null) {
            date = format.parse(birthDate + " -00:00");
            assertThat("Incorrect value in birthDate field", response.getBirthDate().getTime(), is(date.getTime()));
        }
        if (department != null) {
            assertThat("Incorrect value in departmentId field", response.getDepartmentId(), is(Long.parseLong(department.getId())));
            assertThat("Incorrect value in departmentName field", response.getDepartmentName(), is(department.getName()));
        }
        if (role != null)
            assertThat("Incorrect value in role field", response.getRole(), equalToIgnoringCase(role));

        if (adminRole != null)
            assertThat("Incorrect value in adminRole field", response.getAdminRole(), equalToIgnoringCase(adminRole.equals("SUPER_ADMIN") ? "superAdmin" : adminRole));

        if (status != null) {
            assertThat("Incorrect value in status field", response.getStatus(), equalToIgnoringCase(status));
            assertThat("Incorrect value in status field", response.getStatus(), is(statusToValidate));
        }

        if (companyStartDate != null) {
            date = format.parse(companyStartDate + " -00:00");
            assertThat("Incorrect value in companyStartDate field", response.getCompanyStartDate().getTime(), is(date.getTime()));
        }

        if (companyUsername != "" && companyUsername != null)
            assertThat("Incorrect value in companyUsername field", response.getCompanyUsername(), is(companyUsername));

        HashMap<Integer, List> userMySqlFields = mySQLConnector_iatAdmin.getResult("SELECT * FROM User WHERE epointsUuid = '" + dataExchanger.getUserObject().getId() + "'", asList("enabled", "username", "departmentId", "userRole"));

        if (!dataExchanger.getUserObject().getAdminRole().equals("NONE") || dataExchanger.getUserObject().getRole().equals("MANAGER")) {
            System.out.println("MySQL account exist (Enabled = true)");
            String mySqlEnabled = userMySqlFields.get(0).get(0).toString();
            String mySqlUsername = userMySqlFields.get(0).get(1).toString();
            String mySqlDepartmentId = userMySqlFields.get(0).get(2).toString();
            String mySqlUserRole = userMySqlFields.get(0).get(3).toString();

            assertThat("Incorrect value in enabled field", mySqlEnabled, is(statusToValidate.equalsIgnoreCase("active") ? "true" : "false"));
            assertThat("Incorrect value in username field", mySqlUsername, is(dataExchanger.getUserObject().getEmail()));
            assertThat("Incorrect value in departmentId field", mySqlDepartmentId, is(dataExchanger.getUserObject().getDepartment().getId()));
            assertThat("Incorrect value in userRole field", mySqlUserRole, is(dataExchanger.getUserObject().getRole().equals("MANAGER") ? "manager" : "user"));


        } else {
            //in this case IAT admin account was created (manager, admin, super_admin) but user was edited to become just USER
            System.out.println("MySQL Account exist (Enabled = false)");

            if (userMySqlFields.size() > 0) {
                String mySqlEnabled = userMySqlFields.get(0).get(0).toString();
                String mySqlUserRole = userMySqlFields.get(0).get(3).toString();
                assertThat("Incorrect value in enabled field", mySqlEnabled, is("false"));
                assertThat("Incorrect value in userRole field", mySqlUserRole, is(dataExchanger.getUserObject().getRole().equals("MANAGER") ? "manager" : "user"));
            }

        }


    }

    public void validateUserProfile(String credentials, ResponseContainer response) {
        String adminLogin = credentials.split(",")[0];
        String definedRole = "";

        String extractedId = response.get("id");
        String extractedLogin = response.get("login");
        String extractedName = response.get("name");
        String extractedRole = response.get("role");
        List<String> authorities = response.getList("authorities");

        assertThat("User Id is not returned in profile response: ", extractedId, not(isEmptyOrNullString()));
        assertThat("User login is improper!", extractedLogin, is(adminLogin));
        assertThat("Authorities list is empty", authorities.size(), is(greaterThan(0)));

        //role validation
        if (adminLogin.equals("api_test_super_admin_1@api.iat.admin.pl"))
            definedRole = "superAdmin";
        if (adminLogin.equals("api_test_admin_1@api.iat.admin.pl"))
            definedRole = "admin";
        if (adminLogin.equals("api_test_manager_department_1@api.iat.admin.pl"))
            definedRole = "manager";
        if (adminLogin.equals("api_test_manager_admin_1@api.iat.admin.pl"))
            definedRole = "admin";
        if (adminLogin.equals("api_test_admin_platform_1@api.iat.admin.pl"))
            definedRole = "superAdmin";

        assertThat("Improper role: " + extractedRole + " for user: " + adminLogin, extractedRole, is(definedRole));
    }

    public void validateUserAuthorities(ResponseContainer response) {

        List<String> authorities = response.getList("authorities");
        String userRole = response.get("role");
        String userLogin = response.get("login");
        System.out.println("userRole: " + userRole);

        List<String> roles = new ArrayList<>();
        for (String authority : authorities) {

            System.out.println("\nAuthority: " + authority);

            switch (authority) {
                case "hr_global_config":
                    roles = singletonList("superAdmin");
                    break;
                case "hr_reporting":
                    roles = asList("superAdmin", "admin", "manager");
                    break;
                case "hr_grant_partners":
                    roles = asList("superAdmin", "TODO");
                    break;
                case "hr_grant_users":
                    roles = asList("superAdmin", "admin", "manager");
                    break;
                case "hr_grant_departments":
                    roles = asList("superAdmin", "admin", "manager");
                    break;
                case "hr_approve_points":
                    roles = singletonList("not used right now");
                    break;
                case "hr_add_super_admin":
                    roles = singletonList("superAdmin");
                    break;
                case "hr_add_admin":
                    roles = asList("superAdmin", "admin");
                    break;
                case "hr_add_managers":
                    roles = asList("superAdmin", "admin", "manager");
                    break;
                case "hr_add_users":
                    roles = asList("superAdmin", "admin", "manager");
                    break;
            }
            assertThat("Not allowed authority in user with Login: " + userLogin, roles, hasItem(userRole));
        }
    }

    public void validateGetUsersList(ResponseContainer response, String params, String credentials) throws Throwable {
        String[] params2 = params.split(",");
        String sortField = params2[0];
        String ascending = params2[1];
        String dateFrom = params2[2];
        String dateTo = params2[3];
        String departmentName = params2[4];
        String manager = params2[5];
        String user = params2[6];
        String status = params2[7];
        String page = params2[8];
        String maxResults = params2[9];

        String login = credentials.split(",")[0];


        List<User> usersList = response.getList("results", User.class);

        //items per page
        if (maxResults.equals("null"))
            maxResults = "10";
        assertThat("Incorrect results count - more than provided maxResults", usersList, hasSize(lessThanOrEqualTo(parseInt(maxResults))));
        assertThat("Incorrect searchResultsCount field value - more than provided maxResults", response.getInt("pageSize"), is(lessThanOrEqualTo(parseInt(maxResults))));


        //SORTING
        if (sortField.equals("createdAt")) {
            for (int i = 0; i < usersList.size() - 1; i++) {
                if (ascending.equals("false"))
                    assertThat("Results (createdAt) [" + i + "] are not sorted by desc!", usersList.get(i).getCreatedAt(), is(sameOrAfter(usersList.get(i + 1).getCreatedAt())));
                else
                    assertThat("Results (createdAt) [" + i + "] are not sorted by asc!", usersList.get(i).getCreatedAt(), is(sameOrBefore(usersList.get(i + 1).getCreatedAt())));
            }
        }

        if (sortField.equals("id")) {
            for (int i = 0; i < usersList.size() - 1; i++) {
                if (ascending.equals("false"))
                    assertThat("Results (id) [" + i + "] are not sorted by desc!", usersList.get(i).getId(), is(greaterThanOrEqualTo(usersList.get(i + 1).getId())));
                else
                    assertThat("Results (id) [" + i + "] are not sorted by asc!", usersList.get(i).getId(), is(lessThanOrEqualTo(usersList.get(i + 1).getId())));
            }

        }
        if (sortField.equals("departmentName")) {
            if (usersList.size() > 1)
                genericValidator.validateStringsOrder(response.getList("results.departmentName"), ascending, "departmentName");
        }
        if (sortField.equals("employeeNumber")) {
            if (usersList.size() > 1)
                genericValidator.validateStringsOrder(response.getList("results.employeeNumber"), ascending, "employeeNumber");
        }
        if (sortField.equals("role")) {
            if (usersList.size() > 1)
                genericValidator.validateStringsOrder(response.getList("results.role"), ascending, "role");
        }
        if (sortField.equals("status")) {
            if (usersList.size() > 1)
                genericValidator.validateStringsOrder(response.getList("results.status"), ascending, "status");
        }


        if (sortField.equals("user")) {
            List<String> extracted = new ArrayList<>();
            for (int i = 0; i < usersList.size() - 1; i++) {
                String text;
                text = usersList.get(i).getUser().getName() != null ? usersList.get(i).getUser().getName() + " " : "";
                text += usersList.get(i).getUser().getEmail() != null ? usersList.get(i).getUser().getEmail() : "";
                extracted.add(text);
            }
            if (extracted.size() > 1)
                genericValidator.validateStringsOrder(extracted, ascending, "user");
        }

        if (sortField.equals("manager")) {
            List<String> extracted = new ArrayList<>();
            for (int i = 0; i < usersList.size() - 1; i++) {
                String text;
                text = usersList.get(i).getManager().getName() != null ? usersList.get(i).getManager().getName() + " " : "";
                text += usersList.get(i).getManager().getEmail() != null ? usersList.get(i).getManager().getEmail() : "";
                extracted.add(text);
            }
            if (extracted.size() > 1)
                genericValidator.validateStringsOrder(extracted, ascending, "manager");
        }


        //FILTERING

        //startDate endDate validation
        if (!dateFrom.equals("null")) {
            Long providedStartDate;
            if (dateFrom.contains("T"))
                providedStartDate = convertToDate(dateFrom, Format.yyyyMMddTHHmmssXXX).getTime();
            else
                providedStartDate = Long.parseLong(dateFrom);
            for (User userObject : usersList)
                assertThat("User createdAt: " + new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").format(userObject.getCreatedAt()) + " date is below provided start date: " + dateFrom, userObject.getCreatedAt().getTime(), is(greaterThanOrEqualTo(providedStartDate)));
        }
        if (!dateTo.equals("null")) {
            Long providedEndDate;
            if (dateTo.contains("T"))
                providedEndDate = convertToDate(dateTo, Format.yyyyMMddTHHmmssXXX).getTime();
            else
                providedEndDate = Long.parseLong(dateTo);

            for (User userObject : usersList)
                assertThat("User createdAt: " + new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").format(userObject.getCreatedAt()) + " date is above provided start date: " + dateFrom, userObject.getCreatedAt().getTime(), is(lessThanOrEqualTo(providedEndDate)));
        }

        if (!departmentName.equals("null")) {
            List<String> extracted = response.getList("results.departmentName");
            for (int i = 0; i < extracted.size() - 1; i++)
                assertThat("departmentName [" + i + "] not contains keyword!", extracted.get(i), containsStringIgnoringCase(departmentName));
            assertThat("No results for search by departmentName", response.getInt("searchResultsCount"), is(greaterThan(0)));
        }

        if (!status.equals("null")) {
            List<String> extracted = response.getList("results.status");
            for (int i = 0; i < extracted.size() - 1; i++)
                assertThat("status [" + i + "] not contains keyword: " + status, extracted.get(i), containsStringIgnoringCase(status));
            assertThat("No results for search by status", response.getInt("searchResultsCount"), is(greaterThan(0)));
        }

        if (!user.equals("null")) {
            List<String> extracted = new ArrayList<>();
            for (int i = 0; i < usersList.size() - 1; i++) {
                String text;
                text = usersList.get(i).getUser().getName() != null ? usersList.get(i).getUser().getName() + " " : "";
                text += usersList.get(i).getUser().getEmail() != null ? usersList.get(i).getUser().getEmail() : "";
                extracted.add(text);
            }
            for (int i = 0; i < extracted.size() - 1; i++)
                assertThat("user [" + i + "] not contains keyword: " + user, extracted.get(i), containsStringIgnoringCase(user));
            if (login.equals("api_manager_department_1") && user.contains("browse"))
                assertThat("Results are returned for: " + login + "  for search by user: " + user, response.getInt("searchResultsCount"), is(0));
            else
                assertThat("No results for search by user: " + user, response.getInt("searchResultsCount"), is(greaterThan(0)));
        }

        if (!manager.equals("null")) {
            List<String> extracted = new ArrayList<>();
            for (int i = 0; i < usersList.size() - 1; i++) {
                String text;
                text = usersList.get(i).getManager().getName() != null ? usersList.get(i).getManager().getName() + " " : "";
                text += usersList.get(i).getManager().getEmail() != null ? usersList.get(i).getManager().getEmail() : "";
                extracted.add(text);
            }
            for (int i = 0; i < extracted.size() - 1; i++)
                assertThat("manager [" + i + "] not contains keyword: " + manager, extracted.get(i), containsStringIgnoringCase(manager));
            assertThat("No results for search by manager: " + manager, response.getInt("searchResultsCount"), is(greaterThan(0)));
        }

    }


    public void checkCorrectnessOfPromptToBeDisplayedResponse(ResponseContainer response, String verifiedOnEpointsSide, String globalPasswordSet) {
        assertThat("Popup should be/not be displayed during email change", response.toString(), is(String.valueOf(verifiedOnEpointsSide.equals("true") && !globalPasswordSet.equals("true"))));
    }

    public void validateEpointsEmailChangedIfNeededAfterEmailUpdateInIatAdmin(String emailUpdateType) {
        UserDetailsDoorman userDetailsDoorman = userRepository.findById(dataExchanger.getUserObject().getId());
        Long epointsPartnerId = Long.valueOf(partnerRepository.findByShortname(Config.epointsPartnerShortName).getId());
        String expectedEmail = dataExchanger.getUserObject().getEmail();

        if (emailUpdateType.equals("BOTH"))
            assertThat("Wrong email address in main user details json.", userDetailsDoorman.getEmail(), is(equalTo(expectedEmail)));
        else if (emailUpdateType.equals("ADMIN_ONLY"))
            assertThat("Wrong email address in main user details json.", userDetailsDoorman.getEmail(), is(not(expectedEmail)));

        for (UserGroupDoorman userGroupDoorman : userDetailsDoorman.getUserGroupDoormen()) {
            if (userGroupDoorman.getPartnerId().equals(epointsPartnerId)) {
                if (emailUpdateType.equals("BOTH"))
                    assertThat("Wrong email address in main user details json.", userGroupDoorman.getEmail(), is(equalTo(expectedEmail)));
                else if (emailUpdateType.equals("ADMIN_ONLY"))
                    assertThat("Wrong email address in main user details json.", userGroupDoorman.getEmail(), is(not(expectedEmail)));
            }
        }
    }


    public void checkUserEmailChangesHistoryCorrectness(User user, List emails) {
        int i = emails.size() - 1 - 1; //[i = emails.size() - 1 - 1] - because table is indexed from 0 and last element is current email which is not available in history
        for (EmailChange emailChange : user.getEmailChanges())
            assertThat("Wrong email in history changes on position " + i, emails.get(i--), is(emailChange.getEmail()));
    }
}