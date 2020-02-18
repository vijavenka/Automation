package com.iat.validators;

import com.iat.Config;
import com.iat.actions.MilestonesActions;
import com.iat.domain.EcardsConfig.milestones.MilestoneValue;
import com.iat.domain.EcardsConfig.milestones.MilestonesList;
import com.iat.domain.EcardsConfig.milestones.SingleMilestoneElement;
import com.iat.domain.EcardsConfig.milestones.usersList.UserForMilestone;
import com.iat.domain.EcardsConfig.milestones.usersList.UsersListForMilestoneDate;
import com.iat.domain.UserDetailsDoorman;
import com.iat.repository.impl.UserRepositoryImpl;
import com.iat.utils.DataExchanger;
import com.iat.utils.JdbcDatabaseConnector;
import com.iat.utils.ResponseContainer;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static com.iat.utils.DateTimeUtil.Format.yyyyMMdd;
import static com.iat.utils.DateTimeUtil.*;
import static java.util.Collections.singletonList;
import static org.exparity.hamcrest.date.DateMatchers.before;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;


public class MilestonesValidator {

    private DataExchanger dataExchanger = DataExchanger.getInstance();
    private MilestonesActions milestonesActions = new MilestonesActions();
    private JdbcDatabaseConnector mySQLConnector_iatAdmin = new JdbcDatabaseConnector(Config.mysqlConnectionPoolIatAdmin);

    public void checkIfMilestoneOnMilestonesList(ResponseContainer response, MilestoneValue milestoneValue, boolean available) {

        boolean milestoneOnList = false;
        for (SingleMilestoneElement milestone : response.getAsObject(MilestonesList.class).getValues()) {
            milestoneOnList = milestone.getValue() == milestoneValue.getValue();
            if (milestoneOnList) break;
        }

        assertThat("Milestone " + milestoneValue.getValue() + " availability on list is wrong", milestoneOnList, is(available));
    }

    public void checkIfUserOnMilestonesList(ResponseContainer response, int milestoneValue, int anniversaryDaysFromToday, boolean shouldBeReturned) {
        List<UsersListForMilestoneDate> usersForMilestone = response.getList("", UsersListForMilestoneDate.class);
        String userId = dataExchanger.getUserObject().getId();
        Date date = new Date();
        boolean userFound = false;

        for (int i = 0; i < usersForMilestone.size(); i++) {

            if (i < usersForMilestone.size() - 1)
                assertThat("Wrong order of events for milestones batches: " + usersForMilestone.get(i).getDay() + " and " + usersForMilestone.get(i + 1).getDay(), convertToDate(usersForMilestone.get(i).getDay(), yyyyMMdd), before(convertToDate(usersForMilestone.get(i + 1).getDay(), yyyyMMdd)));

            userFound = usersForMilestone.get(i).getDay().equals(convertToString(adjustDateByDays(date, anniversaryDaysFromToday), yyyyMMdd));
            if (!userFound) continue;

            userFound = false;
            for (UserForMilestone userForMilestone : usersForMilestone.get(i).getItems()) {
                userFound = userForMilestone.getUuid().equals(userId);
                if (!userFound) continue;

                UserDetailsDoorman doormanUser = new UserRepositoryImpl().findById(userId);

                assertThat("Wrong milestone value", userForMilestone.getValue(), is(milestoneValue));
                assertThat("Full name does not contains user name", userForMilestone.getFullName(), containsString(doormanUser.getFirstName()));
                assertThat("Full name does not contains user last name", userForMilestone.getFullName(), containsString(doormanUser.getLastName()));
                assertThat("Wrong user email", userForMilestone.getEmail(), is(doormanUser.getEmail()));
                assertThat("Wrong ecard awarded state", !userForMilestone.getAwarded());
                break;
            }
            break;
        }
        assertThat("User presence on users for milestone list is incorrect", userFound, is(shouldBeReturned));
    }

    public void validateDefaultMilestoneListsCreatedForPartner() {
        //DB
        String query = "SELECT * FROM iat_admin_qa.Milestone where partnerId = " + Config.getWizardTestPartnerId() + " and name = 'birthdate'";
        HashMap<Integer, List> toValidateDB = mySQLConnector_iatAdmin.getResult(query, singletonList("value"));

        List<Object> toValidateDBFlat = toValidateDB.values().stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());

        assertThat("Birthday List is incorrect in DB", toValidateDBFlat, is(Config.getMilestonesDefaultList("birthdate")));

        query = "SELECT * FROM iat_admin_qa.Milestone where partnerId = " + Config.getWizardTestPartnerId() + " and name = 'workAnniversary'";
        toValidateDB = mySQLConnector_iatAdmin.getResult(query, singletonList("value"));

        toValidateDBFlat = toValidateDB.values().stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());

        assertThat("workAnniversary List is incorrect in DB", toValidateDBFlat, is(Config.getMilestonesDefaultList("workAnniversary")));

        //API
        List<Integer> listToValidate = milestonesActions.getMilestonesForType("birthdate", 200).getList("values.value", Integer.class);
        assertThat("Birthday List is incorrect", listToValidate, is(Config.getMilestonesDefaultList("birthdate")));

        listToValidate = milestonesActions.getMilestonesForType("workAnniversary", 200).getList("values.value", Integer.class);
        assertThat("Birthday List is incorrect", listToValidate, is(Config.getMilestonesDefaultList("workAnniversary")));
    }

}