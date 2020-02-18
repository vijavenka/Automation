package com.iat.utils;

import com.iat.validators.ContractValidator;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

import static org.codehaus.groovy.runtime.StringGroovyMethods.isInteger;

@Slf4j
public class ResponseContainer {

    private ValidatableResponse response;
    private ContentType contentType;

    public ResponseContainer(ValidatableResponse response) {
        this.response = response;

        String contentType = response.extract().contentType();
          if (contentType.toUpperCase().contains("HTML"))
            this.contentType = ContentType.HTML;
          else if(contentType.toUpperCase().contains("JSON"))
              this.contentType = ContentType.JSON;
          else
              this.contentType = ContentType.TEXT;
    }

    public ResponseContainer(ValidatableResponse response, ContentType contentType) {
        this.response = response;
        this.contentType = contentType;
    }

     public static ResponseContainer initResponseContainer(ValidatableResponse validatableResponse, String message) {
        ResponseContainer response = new ResponseContainer(validatableResponse);
        if (message != null && !message.isEmpty()) {
			if(isInteger(response.toString()))
			    log.info("{}: {}",message, response.toString());
			else
            {
            log.info(message);
            response.prettyPrint();
			}
        }
        return response;
    }

    static public ResponseContainer initResponseContainerLanding(ValidatableResponse validatableResponse, String message, String ToValidate) {
        ResponseContainer response = new ResponseContainer(validatableResponse);
        if (message != null && !message.isEmpty()) {
            log.info(ToValidate);
            log.info("{}", response);
        }
        return response;
    }

    static public ResponseContainer initResponseContainer(ValidatableResponse validatableResponse) {
        if (validatableResponse == null) return null;
        return initResponseContainer(validatableResponse, "RESPONSE:");
    }

    static public ResponseContainer initResponseContainerLanding(ValidatableResponse validatableResponse, String ToValidate) {
        if (validatableResponse == null) return null;
        return initResponseContainerLanding(validatableResponse, "RESPONSE:", ToValidate);
    }

    public ValidatableResponse getValidatableResponse() {
        return response;
    }

    public <T> List<T> getList(String path) {
        switch (contentType) {
            case HTML:
                return response.extract().response().getBody().htmlPath().getList(path);
            case JSON:
            default:
                return response.extract().response().getBody().jsonPath().getList(path);
        }
    }

    //by default the retrieved list's type is defined by the data parser is seeing at the moment. In case we want to "force" the type
    //e.g. we do not want the list of numbers but strings. The second parameter may be used.
    public <T> List<T> getList(String path, Class<T> object) {
        return response.extract().response().getBody().jsonPath().getList(path, object);
    }

    public <T> T get(String path) {
        return response.extract().response().getBody().jsonPath().get(path);
    }

    public Float getFloat(String path) {
        switch (contentType) {
            case HTML:
                return response.extract().response().getBody().htmlPath().getFloat(path);
            case JSON:
            default:
                return response.extract().response().getBody().jsonPath().getFloat(path);
        }
    }

    public String getString(String path) {
        return getString(path, false);
    }

    public String getString(String path, boolean fakeNull) {
        String string;
        switch (contentType) {
            case HTML:
                string = response.extract().response().getBody().htmlPath().getString(path);
                break;
            case JSON:
            default:
                string = response.extract().response().getBody().jsonPath().getString(path);
        }

        if (!fakeNull || string != null) return string;
        return "null";
    }

    public int getInt(String path) {
        switch (contentType) {
            case HTML:
                return response.extract().response().getBody().htmlPath().getInt(path);
            case JSON:
            default:
                return response.extract().response().getBody().jsonPath().getInt(path);
        }
    }

    public double getDouble(String path) {
        switch (contentType) {
            case HTML:
                return response.extract().response().getBody().htmlPath().getDouble(path);
            case JSON:
            default:
                return response.extract().response().getBody().jsonPath().getDouble(path);
        }
    }

    public boolean getBoolean(String path) {
        switch (contentType) {
            case HTML:
                return response.extract().response().getBody().htmlPath().getBoolean(path);
            case JSON:
            default:
                return response.extract().response().getBody().jsonPath().getBoolean(path);
        }
    }

    public long getLong(String path) {
        switch (contentType) {
            case HTML:
                return response.extract().response().getBody().htmlPath().getLong(path);
            case JSON:
            default:
                return response.extract().response().getBody().jsonPath().getLong(path);
        }
    }

    public <T> T getAsObject(Class<T> object) {
        return response.extract().response().as(object);

    }

    public <T> T getAsObject(Class<T> object, String path) {
        switch (contentType) {
            case HTML:
                return response.extract().response().getBody().htmlPath().getObject(path, object);
            case JSON:
            default:
                return response.extract().response().getBody().jsonPath().getObject(path, object);
        }
    }

    public void validateContract(String contract, int status) {
        new ContractValidator().validateResponseWithContract(contract, response, status);
    }

    public String getCookie(String cookieName) {
        return response.extract().response().getCookie(cookieName);
    }

    public Map<String, String> getCookies() {
        return response.extract().response().getCookies();
    }

    public void prettyPrint() {
        response.log().body(true);
    }

    public int getStatusCode() {
        return response.extract().response().getStatusCode();
    }

    @Override
    public String toString() {
        return response.extract().response().asString();
    }
}

