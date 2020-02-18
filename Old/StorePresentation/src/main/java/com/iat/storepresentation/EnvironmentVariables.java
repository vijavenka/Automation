package com.iat.storepresentation;

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
        //public String baseUrl = "http://10.10.30.141:8080/";
        //local mapped for fb
        //public String baseUrl = "http://10.10.30.141:8080/";
    //Prod /////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //public String baseUrl = "";
    //QA ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        public String baseUrl = "http://store-presentation-web-qa.iatlimited.com/";
    //Stag /////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //public String baseUrl = "http://store-presentation-web-stag.iatlimited.com/";
    //Live /////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //public String baseUrl = "";

    //Database connection variables - uncomment proper according to tested environment
    //QA ePoints ///////////////////////////////////////////////////////////////////////////////////////////////////////
        public String dbUrlEP = "jdbc:mysql://test-proxy-qa-01.iatlimited.com:3308";
        public String userEP = "admin";
        public String passEP = "qGZ7zmhsu8Eb";
    //QA admin ///////////////////////////////////////////////////////////////////////
        public String dbUrlAD = "jdbc:mysql://test-proxy-qa-01.iatlimited.com:3307";
        public String userAD = "admin";
        public String passAD = "U70FXi1wdDgip3m";
    //Stag epoints /////////////////////////////////////////////////////////////////////////////////////////////////////
        //public String dbUrlEP = "jdbc:mysql://test-proxy-stag-01.iatlimited.com:3308";
        //public String userEP = "admin";
        //public String passEP = "JLNp5SbtaGkHOVS";
    //Stag admin ////////////////////////////////////////////////////////////////////
        //public String dbUrlAD = "jdbc:mysql://test-proxy-stag-01.iatlimited.com:3307";
        //public String userAD = "admin";
        //public String passAD = "V2fPqKvDED0AHwt";
    //Local epoints ////////////////////////////////////////////////////////////////////////////////////////////////////
        //public String dbUrlEP = "jdbc:mysql://10.10.30.141:3306";
        //public String userEP = "dev";
        //public String passEP = "muppet";
    //Local admin //////////////////////////////////////////////////////////////////////////////////////////////////////
        //public String dbUrlAD = "jdbc:mysql://10.10.30.141:3306";
        //public String userAD = "dev";
        //public String passAD = "muppet";


    // Store name
    //local
    //public String StoreName = "shopshop";
    //QA
    public String StoreName = "onedoo.com";
    //stag
    //public String StoreName = "Store presentation";
}
