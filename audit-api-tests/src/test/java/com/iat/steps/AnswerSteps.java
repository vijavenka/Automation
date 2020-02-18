package com.iat.steps;

import com.iat.utils.DataExchanger;
import com.iat.utils.ExcelUtilities;
import com.iat.validators.AnswersValidator;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class AnswerSteps {

    private AnswersValidator answersValidator = new AnswersValidator();
    DataExchanger dataExchanger = DataExchanger.getInstance();
    String response;


    @When("^Extract answers from '(.+?)' auditResults file '(.+?)'$")
    public void extractAnswersFromResultsFile(String partnerShortName, String fileName) throws Throwable {
        //save answers from file
        System.out.println("\nExtract answers from " + partnerShortName + " auditResults file " + fileName + "");
        ExcelUtilities excelUtilities = new ExcelUtilities();
        excelUtilities.openHeadless(fileName, "src//bulkUploadFiles//");

        if (partnerShortName.toLowerCase().contains("todays"))
            dataExchanger.setAnswers(excelUtilities.getAnswersCallsValuesForTodays());
        else
            dataExchanger.setAnswers(excelUtilities.getAnswersCallsValuesForPremier());
        excelUtilities.close();
        System.out.println("Extracted answers count: " + dataExchanger.getAnswers().size());
    }


    @Then("^Answers were properly stored for audit for '(.+?)'$")
    public void validateIfAnswersProperlyStored(String partnerShortName) throws Throwable {
        answersValidator.validateProperAnswersRecorded(partnerShortName);
    }


}