package com.iat.ePoints;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 25.01.14
 * Time: 19:52
 * To change this template use File | Settings | File Templates.
 */
public class EnvironmentVariables {
//Which browser you want to use
    public String usedBrowser = "firefox";
    //public String usedBrowser = "chrome";
    //public String usedBrowser = "ie";
//Base environment url
    //Local ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //public String baseUrl = "http://10.10.30.141:8911";
        //local mapped for fb
        //public String baseUrl = "http://dev.epoints.com:8911";
    //Prod /////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //public String baseUrl = "http://www.epoints.com";
    //QA ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //public String baseUrl = "https://qa.epoints.com";
    //Stag /////////////////////////////////////////////////////////////////////////////////////////////////////////////
        public String baseUrl = "http://stag.epoints.com";
    //Live /////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //public String baseUrl = "http://epoints-com-live-elb.iatlimited.com";

//Database connection variables - uncomment proper according to tested environment
    //QA ePoints ///////////////////////////////////////////////////////////////////////////////////////////////////////
        //public String dbUrlEP = "jdbc:mysql://test-proxy-qa-01.iatlimited.com:3308";
        //public String userEP = "admin";
        //public String passEP = "qGZ7zmhsu8Eb";
    //QA admin ///////////////////////////////////////////////////////////////////////
        //public String dbUrlAD = "jdbc:mysql://test-proxy-qa-01.iatlimited.com:3307";
        //public String userAD = "admin";
        //public String passAD = "U70FXi1wdDgip3m";
    //Stag epoints /////////////////////////////////////////////////////////////////////////////////////////////////////
        public String dbUrlEP = "jdbc:mysql://test-proxy-stag-01.iatlimited.com:3308";
        public String userEP = "admin";
        public String passEP = "JLNp5SbtaGkHOVS";
    //Stag admin ////////////////////////////////////////////////////////////////////
        public String dbUrlAD = "jdbc:mysql://test-proxy-stag-01.iatlimited.com:3307";
        public String userAD = "admin";
        public String passAD = "V2fPqKvDED0AHwt";
    //Local epoints ////////////////////////////////////////////////////////////////////////////////////////////////////
        //public String dbUrlEP = "jdbc:mysql://10.10.30.141:3306";
        //public String userEP = "dev";
        //public String passEP = "muppet";
    //Local admin //////////////////////////////////////////////////////////////////////////////////////////////////////
        //public String dbUrlAD = "jdbc:mysql://10.10.30.141:3306";
        //public String userAD = "dev";
        //public String passAD = "muppet";

    //used emails (only for information):
    // passwords are correct in gmail and epoints site as well
    // iat.epoints.test@gmail.com         / everest01  - facebook use only
    // emailwybitnietestowy@gmail.com     / Delete777  - main test email used frequently
    // emailwybitnietestowy3@gmail.com    / Delete777  - facebook use only (account creation)
    // emailwybitnietestowy12@gmail.com   / Delete777  - facebook use only (post on friend wall)
    // krzysztofbaranowski@gmail.com inactive but stored in Database

    //Assumptions
    //Retailer with special offers and categories which is used in test is ****************** John Lewis
    //Remember to check if product exist ***************************************************  manage item test
    //Product with redeem ******************************************************************* The Little Rascals

}
