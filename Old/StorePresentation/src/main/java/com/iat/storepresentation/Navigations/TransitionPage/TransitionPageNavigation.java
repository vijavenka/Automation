package com.iat.storepresentation.Navigations.TransitionPage;

import com.iat.storepresentation.Database.JDBC;
import com.iat.storepresentation.EnvironmentVariables;
import com.iat.storepresentation.Executors.IExecutor;
import com.iat.storepresentation.Locators.TransitionPage.TransitionPageLocators;
import com.iat.storepresentation.Navigations.AbstractPage;

import java.sql.SQLException;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 06.03.14
 * Time: 16:26
 * To change this template use File | Settings | File Templates.
 */
public class TransitionPageNavigation extends AbstractPage{

    private TransitionPageLocators locators_transition = new TransitionPageLocators();
    private EnvironmentVariables locator_environment = new EnvironmentVariables();

    public TransitionPageNavigation(IExecutor executor) {
        super(executor, "");
    }

    public void open() {
        super.open();
    }

    public void checkIfTransitionPageWasOpened() throws InterruptedException {
        Thread.sleep(4000);
        executor.handleMultipleWindows("Purchase from");
        assertTrue("Transition page was not opened", executor.return_driver().getCurrentUrl().contains("transition"));
    }

    public void clickContinueAnywayButton(){
        executor.click(locators_transition.continueAnywayButton);
    }

    public void checkIfTransitionPageIsInvisible() throws InterruptedException {
        //wait some time for transition page
        Thread.sleep(2000);
        Set<String> windows = executor.return_driver().getWindowHandles();
        boolean isTransitionAvaliable = false;
        for (String window : windows) {
            if (executor.return_driver().getTitle().contains("Purchase from")) {
                isTransitionAvaliable = true;
            }
        }
        assertFalse("Retailer page was no opened", isTransitionAvaliable);
    }

    // AFFILIATE MANAGER - cross channel sales tracking logic (RD-2837) - both normal transition
    String p1;
    String p2;
    String p3;
    String p4;
    public void addPParametersToUrl(){
        p1 = Integer.toString(executor.return_random_value(1000000));
        p2 = Integer.toString(executor.return_random_value(1000000));
        p3 = Integer.toString(executor.return_random_value(1000000));
        p4 = Integer.toString(executor.return_random_value(1000000));
        String basePageUrl = executor.getUrl();
        executor.return_driver().get(basePageUrl+"&p1="+p1+"%21%23%24%26%27"+p1+"&p2="+p2+"%28%29%2A%2B%2C"+p2+"&p3="+p3+"%2F%3A%3B%3D"+p3+"&p4="+p4+"%3F%40%5B%5D"+p4);
    }

    String CurrentDate;
    public void checkIfPParametersWereCorrectlyStoredInDB() throws SQLException {
        CurrentDate = executor.returnCurrentDate();
        JDBC jdbc = new JDBC("affiliate_manager");
        assertEquals("Parameter p1 is incorrect", p1+"!#$&'"+p1, jdbc.return_proper_db_value("SELECT p1Parameter FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC",1));
        assertEquals("Parameter p2 is incorrect", p2+"()*+,"+p2, jdbc.return_proper_db_value("SELECT p2Parameter FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC",1));
        assertEquals("Parameter p3 is incorrect", p3+"/:;="+p3, jdbc.return_proper_db_value("SELECT p3Parameter FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC",1));
        assertEquals("Parameter p4 is incorrect", p4+"?@[]"+p4, jdbc.return_proper_db_value("SELECT p4Parameter FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC",1));
        jdbc.close();
    }
}
