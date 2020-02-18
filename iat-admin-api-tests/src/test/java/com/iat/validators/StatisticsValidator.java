package com.iat.validators;

import com.iat.Config;
import com.iat.domain.UserDetailsDoorman;
import com.iat.domain.UserGroupDoorman;
import com.iat.domain.statistics.Statistics;
import com.iat.repository.UserRepository;
import com.iat.repository.impl.UserRepositoryImpl;
import com.iat.utils.JdbcDatabaseConnector;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;


public class StatisticsValidator {

    private JdbcDatabaseConnector mySQLConnector_iatAdmin = new JdbcDatabaseConnector(Config.mysqlConnectionPoolIatAdmin);
    private UserRepository userRepository = new UserRepositoryImpl();

    private String departmentName, managerName, partnerId;
    private int departmentId;

    public void checkIfCorrectManagerDetailsAssignedToDepartmentNodes(Statistics statistics) {
        for (int i = 0; i < statistics.getItemCount() - 1; i++) {

            departmentName = statistics.getItems().get(i).getDepartment();
            departmentId = statistics.getItems().get(i).getId();
            partnerId = Config.getTestPartnerId();


            //check manager correctness according to manager name email/first and last name
            managerName = statistics.getItems().get(i).getManagerName();

            String managerUuid = mySQLConnector_iatAdmin.getSingleResult("SELECT epointsUuid FROM User WHERE departmentId = \"" + departmentId + "\" AND userRole = \"manager\" AND enabled = 1 order by id asc LIMIT 1");

            if (managerUuid == null)
                //both should be null
                assertThat("managerName in response is incorrect", managerName, is(isEmptyOrNullString()));
            else {
                UserDetailsDoorman userDetailsDoorman = userRepository.findById(managerUuid);
                for (UserGroupDoorman userGroup : userDetailsDoorman.getUserGroupDoormen()) {
                    if (userGroup.getPartnerId() == Long.parseLong(partnerId)) {
                        if (managerName != null) {
                            if (managerName.contains("@"))
                                assertThat("Wrong manager email was returned for department " + departmentName, managerName, is(userGroup.getEmail()));
                            else {
                                if (managerName.split(" ").length == 1)
                                    assertThat("Wrong manager manage first name for department " + departmentName, managerName.split(" ")[0], is(userGroup.getFirstName()));
                                else {
                                    assertThat("Wrong manager manage first name for department " + departmentName, managerName.split(" ")[0], is(userGroup.getFirstName()));
                                    assertThat("Wrong manager manage last name for department " + departmentName, managerName.substring(managerName.indexOf(" ") + 1), is(userGroup.getLastName()));
                                }
                            }

                            break;
                        } else
                            assertThat("managerName in response is null but there is one in database", managerName, is(userGroup.getFirstName() + userGroup.getLastName() + userGroup.getEmail()));
                    }
                }
            }
        }
    }
}