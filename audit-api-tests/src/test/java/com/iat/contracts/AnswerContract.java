package com.iat.contracts;


public class AnswerContract {


    public String getAnswersPath() {
        return "/api/answers";
    }

    public String getAnswersPath(String params) {
        String[] param = params.split(";");

        String path = "/api/answers/";

        if (param.length > 1) {
            String page = param[0];
            String size = param[1];
            String sort = param[2];

            if (!page.equals("null"))
                path += "&page=" + page;

            if (!size.equals("null"))
                path += "&size=" + size;

            if (!sort.equals("null"))
                path += "&sort=" + sort;

        }
        return path.replace("/&", "?");
    }

    public String getAnswersByIdPath(String id) {
        return "/api/answers" + id;
    }
}
