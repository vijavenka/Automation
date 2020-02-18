package com.iat.steps;


import com.iat.Config;
import com.iat.actions.EcardsConfigActions;
import com.iat.actions.UsersActions;
import com.iat.actions.ecardsManagement.ECardsTemplates.ECardsTemplatesActions;
import com.iat.repository.UserRepository;
import com.iat.repository.impl.UserRepositoryImpl;
import com.iat.utils.DataExchanger;
import com.iat.utils.JdbcDatabaseConnector;
import cucumber.api.java.After;
import cucumber.api.java.Before;


public class HooksSteps {

    private EcardsConfigActions ecardsConfigActions = new EcardsConfigActions();
    private UsersActions usersActions = new UsersActions();
    private DataExchanger dataExchanger = DataExchanger.getInstance();
    private UserRepository userRepository = new UserRepositoryImpl();
    private JdbcDatabaseConnector mySQLConnector_iatAdmin = new JdbcDatabaseConnector(Config.mysqlConnectionPoolIatAdmin);
    private JdbcDatabaseConnector mySQLConnector_pointsManager = new JdbcDatabaseConnector(Config.mysqlConnectionPoolPointsManager);

    @After("@removeGlobalPasswordSetting")
    public void removeGlobalPasswordSetting() {
        String query = "Update ECardsSettings Set globalPassword = null, allowGlobalPassword = 0 WHERE partnerId =  " + Config.getTestPartnerId();
        mySQLConnector_iatAdmin.execute(query);
    }

    @After("@deleteUserAfterTest")
    public void deleteUserById() throws Throwable {
        if (dataExchanger.getUserObject() != null) {

            System.out.println("DELETE FROM UserToken WHERE user_id in (SELECT id FROM User WHERE epointsUuid = '" + dataExchanger.getUserObject().getId() + "')");
            mySQLConnector_iatAdmin.execute("DELETE FROM UserToken WHERE user_id in (SELECT id FROM User WHERE epointsUuid = '" + dataExchanger.getUserObject().getId() + "')");
            mySQLConnector_iatAdmin.execute("DELETE FROM UserAuthority WHERE userId in (SELECT id FROM User WHERE epointsUuid = '" + dataExchanger.getUserObject().getId() + "')");
            mySQLConnector_iatAdmin.execute("DELETE FROM User WHERE epointsUuid = '" + dataExchanger.getUserObject().getId() + "'");
            userRepository.removeUserById(dataExchanger.getUserObject().getId());
            System.out.println(userRepository.findById(dataExchanger.getUserObject().getId()));
            if (dataExchanger.getUserObject().getId() != null)
                usersActions.deleteUserByIdIgnoreStatus(dataExchanger.getUserObject().getId());
        } else
            System.out.println("@deleteUserAfterTest: There was no user to DELETE");
    }

    //This hook base on static department structure described in config, single department is added always to the same parent department.
    @After("@RemoveCreatedDepartment")
    public void deleteCreatedDepartment() throws Throwable {
        if (dataExchanger.getDepartmentObjectBackup() != null)
            mySQLConnector_pointsManager.execute("DELETE FROM Department WHERE id = " + dataExchanger.getDepartmentObjectBackup().getId());
        mySQLConnector_pointsManager.execute("DELETE FROM Department WHERE id = " + dataExchanger.getDepartmentObject().getId());
        String id;
        do {
            id = mySQLConnector_pointsManager.getSingleResult("SELECT id FROM Department where name like 'randomDepartmentName%' LIMIT 1");
            deleteDepartmentsBelow(id);
        } while (id != null);
        //or more safe
        mySQLConnector_pointsManager.execute("DELETE FROM Department WHERE parentDepartmentId = " + Config.getDepartmentIdForName("Department level 2 [D.1]"));
        mySQLConnector_pointsManager.execute("DELETE FROM Department WHERE parentDepartmentId = " + Config.getDepartmentIdForName("Department level 2 [C.1]"));
        mySQLConnector_pointsManager.execute("DELETE FROM Department WHERE parentDepartmentId = " + Config.getDepartmentIdForName("Department level 2 [C.2]"));
    }

    private void deleteDepartmentsBelow(String parentId) {
        String id = mySQLConnector_pointsManager.getSingleResult("SELECT id FROM Department where parentDepartmentId = " + parentId + " LIMIT 1");
        if (id == null)
            mySQLConnector_pointsManager.execute("DELETE FROM Department WHERE id = " + parentId);
        else
            deleteDepartmentsBelow(id);
    }

    @After("@removeCreatedReasons")
    public void deleteReasons() throws Throwable {
        System.out.println("DELETE FROM ECardsReason where partnerId = " + Config.getTestPartnerId() + " AND name like 'QA Rox %'");
        mySQLConnector_iatAdmin.execute("DELETE FROM ECardsReason where partnerId = " + Config.getTestPartnerId() + " AND name like 'QA Rox %'");
        System.out.println("DELETE FROM Tag where partnerId = " + Config.getTestPartnerId() + " AND description like 'QA Rox %'");
        mySQLConnector_pointsManager.execute("DELETE FROM Tag where partnerId = " + Config.getTestPartnerId() + " AND description like 'QA Rox %'");
    }

    @After("@setDefaultReasonGlobalLimits")
    public void setDefaultReasonGlobalLimit() throws Throwable {
        System.out.println("AFTER: Reason global limits restored!");
        ecardsConfigActions.setDefaultGlobalReasonLimits();
    }

    @After("@DeleteTemplateAfterTest")
    public void deleteTemplateAfterTest() throws Throwable {
        if (dataExchanger.getTemplate().getId() != null)
            new ECardsTemplatesActions().deleteTemplate(dataExchanger.getTemplate().getId(), 200);
    }

    @Before("@RestoreMilestonesListForDefaultPartner")
    public void restoreMilestonesListForDefaultPartner() throws Throwable {
        mySQLConnector_iatAdmin.execute("DELETE FROM Milestone where partnerId = '" + Config.getTestPartnerId() + "'");
        for (int i = 0; i < Config.getMilestonesDefaultList("workAnniversary").size(); i++)
            mySQLConnector_iatAdmin.execute("INSERT INTO Milestone (id, createdAt,partnerId,name,value) VALUES ('" + (1000 + i) + "','2017-11-13 14:52:00', '" + Config.getTestPartnerId() + "','workAnniversary','" + Config.getMilestonesDefaultList("workAnniversary").get(i) + "')");

        for (int i = 0; i < Config.getMilestonesDefaultList("birthdate").size(); i++)
            mySQLConnector_iatAdmin.execute("INSERT INTO Milestone (id, createdAt,partnerId,name,value) VALUES ('" + (1020 + i) + "','2017-11-13 14:52:00', '" + Config.getTestPartnerId() + "','birthdate','" + Config.getMilestonesDefaultList("birthdate").get(i) + "')");
    }
}
