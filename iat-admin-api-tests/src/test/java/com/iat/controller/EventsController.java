package com.iat.controller;

import com.iat.contracts.EventsContract;
import com.iat.utils.DataExchanger;
import com.iat.validators.EventsValidator;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class EventsController {

    private EventsContract eventsContract = new EventsContract();
    private DataExchanger sessionId = DataExchanger.getInstance();
    private EventsValidator eventsValidator = new EventsValidator();


    public ValidatableResponse getEventsAllocations(String name, String year, String month, String allEvents,
                                                    String limit, String offSet, int status){

        ValidatableResponse validatableResponse = getEventsAllocationsRequest(name, year, month, allEvents, limit, offSet, status);

        if(status == 400)
            eventsValidator.validateResponseWithContract("ErrorResponse-schema-fieldsValidation.json", validatableResponse);
        else
            eventsValidator.validateResponseWithContract("GET-events-list-birthday-anniversary.json", validatableResponse, status);

        return validatableResponse;
    }


    private ValidatableResponse getEventsAllocationsRequest(String name, String year, String month, String allEvents,
                                                            String limit, String offSet, int status){

        String path = eventsContract.getEventsPath(name, year, month, allEvents, limit, offSet);

        log.info("Path: GET {}", path);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.getSessionId())
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }


    public ValidatableResponse getEventsAllocationsCount(String name, String allEvents, int status){

        ValidatableResponse validatableResponse = getEventsAllocationsRequestCount(name, allEvents, status);

        if(status == 400)
            eventsValidator.validateResponseWithContract("ErrorResponse-schema-fieldsValidation.json", validatableResponse);
        else
            eventsValidator.validateResponseWithContract("GET-events-count-birthday-anniversary.json", validatableResponse, status);

        return validatableResponse;
    }


    private ValidatableResponse getEventsAllocationsRequestCount(String name, String allEvents, int status){

        String path = eventsContract.getEventsPath(name, allEvents);

        log.info("Path: GET {}", path);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.getSessionId())
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }
}
