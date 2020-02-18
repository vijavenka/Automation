package com.iat.restful_services.jdbc;

/**
 * Created with IntelliJ IDEA.
 * User: LPanusz
 * Date: 31.05.13
 * Time: 15:17
 * To change this template use File | Settings | File Templates.
 */
public class WSUserTable {

    private int id;
    private String activationAt;
    private String activationLink;
    private int active;
    private String birthDate;
    private String country;
    private String county;
    private String createdAt;
    private String email;
    private String firstName;
    private String gender;
    private String houseNumberOrName;
    private String lastLoginAt;
    private String lastName;
    private String mobileNumber;
    private String nationality;
    private int optInContactByOthers;
    private int optInContactByUs;
    private String password;
    private String postcode;
    private String region;
    private String registerPartnerShortName;
    private String registrationAt;
    private String street;
    private int testUser;
    private String title;
    private String townOrCity;
    private String updatedAt;
    private String originalEmail;
    private String passwordChangedAt;
    private int emailVerified;

    public WSUserTable(int id, String activationAt, String activationLink, int active, String birthDate, String country, String county, String createdAt, String email, String firstName,
                       String gender, String houseNumberOrName, String lastLoginAt, String lastName, String mobileNumber, String nationality, int optInContactByOthers, int optInContactByUs,
                       String password, String postcode, String region, String registerPartnerShortName, String registrationAt, String street, int testUser, String title, String townOrCity,
                       String updatedAt, String originalEmail, String passwordChangedAt, int emailVerified) {
        this.id = id;
        this.activationAt = activationAt;
        this.activationLink = activationLink;
        this.active = active;
        this.birthDate = birthDate;
        this.country = country;
        this.county = county;
        this.createdAt = createdAt;
        this.email = email;
        this.firstName = firstName;
        this.gender = gender;
        this.houseNumberOrName = houseNumberOrName;
        this.lastLoginAt = lastLoginAt;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.nationality = nationality;
        this.optInContactByOthers = optInContactByOthers;
        this.optInContactByUs = optInContactByUs;
        this.password = password;
        this.postcode = postcode;
        this.region = region;
        this.registerPartnerShortName = registerPartnerShortName;
        this.registrationAt = registrationAt;
        this.street = street;
        this.testUser = testUser;
        this.title = title;
        this.townOrCity = townOrCity;
        this.updatedAt = updatedAt;
        this.originalEmail = originalEmail;
        this.passwordChangedAt = passwordChangedAt;
        this.emailVerified = emailVerified;
    }

    public WSUserTable() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getActivationAt() {
        return activationAt;
    }

    public void setActivationAt(String activationAt) {
        this.activationAt = activationAt;
    }

    public String getActivationLink() {
        return activationLink;
    }

    public void setActivationLink(String activationLink) {
        this.activationLink = activationLink;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHouseNumberOrName() {
        return houseNumberOrName;
    }

    public void setHouseNumberOrName(String houseNumberOrName) {
        this.houseNumberOrName = houseNumberOrName;
    }

    public String getLastLoginAt() {
        return lastLoginAt;
    }

    public void setLastLoginAt(String lastLoginAt) {
        this.lastLoginAt = lastLoginAt;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public int getOptInContactByOthers() {
        return optInContactByOthers;
    }

    public void setOptInContactByOthers(int optInContactByOthers) {
        this.optInContactByOthers = optInContactByOthers;
    }

    public int getOptInContactByUs() {
        return optInContactByUs;
    }

    public void setOptInContactByUs(int optInContactByUs) {
        this.optInContactByUs = optInContactByUs;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegisterPartnerShortName() {
        return registerPartnerShortName;
    }

    public void setRegisterPartnerShortName(String registerPartnerShortName) {
        this.registerPartnerShortName = registerPartnerShortName;
    }

    public String getRegistrationAt() {
        return registrationAt;
    }

    public void setRegistrationAt(String registrationAt) {
        this.registrationAt = registrationAt;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getTestUser() {
        return testUser;
    }

    public void setTestUser(int testUser) {
        this.testUser = testUser;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTownOrCity() {
        return townOrCity;
    }

    public void setTownOrCity(String townOrCity) {
        this.townOrCity = townOrCity;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getOriginalEmail() {
        return originalEmail;
    }

    public void setOriginalEmail(String originalEmail) {
        this.originalEmail = originalEmail;
    }

    public String getPasswordChangedAt() {
        return passwordChangedAt;
    }

    public void setPasswordChangedAt(String passwordChangedAt) {
        this.passwordChangedAt = passwordChangedAt;
    }

    public int getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(int emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String buildWSUserTableInsertQuery() {
        String query = "INSERT INTO `points_manager`.`User` (`id`,`activationAt`,`activationLink`,`active`,`birthDate`,`country`,`county`,`createdAt`,`email`,`firstName`,`gender`,`houseNumberOrName`,`lastLoginAt`," +
                "`lastName`,`mobileNumber`,`nationality`,`optInContactByOthers`,`optInContactByUs`,`password`,`postcode`,`region`,`registerPartnerShortName`,`registrationAt`,`street`,`testUser`,`title`," +
                "`townOrCity`,`updatedAt`,`originalEmail`,`passwordChangedAt`,`emailVerified`)" + "VALUES" + "(" + this.id + ",'" + this.activationAt + "','" +
                this.activationLink  + "'," + this.active + ",'" + this.birthDate + "','" + this.country + "','" + this.county + "','" + this.createdAt + "','" + this.email + "','" + this.firstName + "','" + this.gender + "','" +
                this.houseNumberOrName + "','" + this.lastLoginAt + "','" + this.lastName + "','" + this.mobileNumber + "','" + this.nationality + "'," + this.optInContactByOthers + "," + this.optInContactByUs + ",'" +
                this.password + "','" + this.postcode + "','" + this.region + "','" + this.registerPartnerShortName + "','" + this.registrationAt + "','" + this.street + "'," + this.testUser + ",'" + this.title + "','" +
                this.townOrCity + "','" + this.updatedAt + "','" + this.originalEmail + "','" + this.passwordChangedAt + "'," + this.emailVerified + ");";

        return query;
    }

    public String buildWSUserTableStaticInsertQuery() {
        //-- user password is: 1qazxsw2, sha-256 encrypted, 1024 iterations, base64 encoded
        String query ="insert into User(id, active, birthDate, country, county, email, firstName, gender, houseNumberOrName, lastName, mobileNumber, nationality, optInContactByUs, password, postcode, registerPartnerShortName,\n" +
                "registrationAt, street, testUser, title, townOrCity, emailVerified)\n" +
                "values(1, true, '1980-05-07 15:41:56', 'Poland', 'malopolska', 'email@domain.com', 'Adam', 'MALE', '25', 'Abakanowicz', '+48925661772', 'polish', true,\n" +
                "'Id3AV3MV0yMSsimaAl6f1EyJO+ujiy5GnSL6P1MPXTY=', '23-202', 'P1', '2013-05-10 15:41:56', 'Jasna', false, 'Mr', 'Warszawa', true);\n" +
                "insert into User(id, active, birthDate, country, county, email, firstName, gender, houseNumberOrName, lastName, mobileNumber, nationality, optInContactByUs, password, postcode, registerPartnerShortName,\n" +
                "registrationAt, street, testUser, title, townOrCity, emailVerified)\n" +
                "values(2, true, '1980-05-05 15:41:56', 'Poland', 'malopolska', 'abc@domain.com', 'Jan', 'MALE', '31', 'Nowak', '+48925661881', 'polish', true,\n" +
                "'Id3AV3MV0yMSsimaAl6f1EyJO+ujiy5GnSL6P1MPXTY=', '23-300', 'P1', '2013-05-10 15:31:56', 'Cicha', false, 'Mr', 'Warszawa', true);\n" +
                "insert into User(id, active, createdAt, email) values(3, true, '2013-05-05 15:41:56', 'email100@domain.com');\n" +
                "insert into User(id, active, createdAt, email) values(4, true, '2013-05-05 15:41:56', 'email200@domain.com');\n" +
                "insert into User(id, active, createdAt, email) values(5, true, '2013-05-05 15:41:56', 'email300@domain.com');\n" +
                "insert into User(id, active, createdAt, email) values(6, true, '2013-05-05 15:41:56', 'email400@domain.com');\n" +
                "insert into User(id, active, createdAt, email) values(7, true, '2013-05-05 15:41:56', 'email500@domain.com');";

        return query;
    }

    public String buildWSUserTableSelectQuery(String fields) {
        String query = "SELECT " + fields + " FROM points_manager.User;";
        return query;
    }

    public String buildWSUserTableSelectFilterQuery(String fields, String filter) {
        String query = "SELECT " + fields + " FROM points_manager.User WHERE firstName='" + filter + "';";
        return query;
    }

    public String buildWSDeleteAllUsersQuery() {
        String query = "DELETE FROM points_manager.User;";
        return query;
    }
}
