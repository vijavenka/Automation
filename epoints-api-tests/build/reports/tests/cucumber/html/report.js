$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("rewards/search/Search.feature");
formatter.feature({
  "line": 1,
  "name": "Epoints API: Rewards -\u003e search",
  "description": "As ePoints user\nI want to be able to search within redemption items products",
  "id": "epoints-api:-rewards--\u003e-search",
  "keyword": "Feature"
});
formatter.scenarioOutline({
  "line": 43,
  "name": "Facets - check facets returned for different business - multival facets call",
  "description": "",
  "id": "epoints-api:-rewards--\u003e-search;facets---check-facets-returned-for-different-business---multival-facets-call",
  "type": "scenario_outline",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 42,
      "name": "@Regression"
    },
    {
      "line": 42,
      "name": "@PositiveCase"
    },
    {
      "line": 42,
      "name": "@Facets"
    },
    {
      "line": 42,
      "name": "@vijaytest"
    }
  ]
});
formatter.step({
  "line": 44,
  "name": "Epoints API is responding to requests",
  "keyword": "Given "
});
formatter.step({
  "line": 45,
  "name": "Multival facets will be requested for \u0027\u003cbusinessId\u003e\u0027, \u0027\u003cversion\u003e\u0027 for specified filter option \u0027\u003cmultivalFilterType\u003e\u0027",
  "keyword": "When "
});
formatter.step({
  "line": 46,
  "name": "Correct facet options \u0027\u003cmultivalFilterType\u003e\u0027 value are returned according to solr for business \u0027\u003cbusinessId\u003e\u0027",
  "keyword": "Then "
});
formatter.examples({
  "line": 49,
  "name": "",
  "description": "",
  "id": "epoints-api:-rewards--\u003e-search;facets---check-facets-returned-for-different-business---multival-facets-call;",
  "rows": [
    {
      "cells": [
        "businessId",
        "version",
        "multivalFilterType"
      ],
      "line": 50,
      "id": "epoints-api:-rewards--\u003e-search;facets---check-facets-returned-for-different-business---multival-facets-call;;1"
    },
    {
      "cells": [
        "null",
        "v2",
        "s_brandName"
      ],
      "line": 52,
      "id": "epoints-api:-rewards--\u003e-search;facets---check-facets-returned-for-different-business---multival-facets-call;;2"
    }
  ],
  "keyword": "Examples"
});
formatter.scenario({
  "line": 52,
  "name": "Facets - check facets returned for different business - multival facets call",
  "description": "",
  "id": "epoints-api:-rewards--\u003e-search;facets---check-facets-returned-for-different-business---multival-facets-call;;2",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 42,
      "name": "@PositiveCase"
    },
    {
      "line": 42,
      "name": "@Regression"
    },
    {
      "line": 42,
      "name": "@Facets"
    },
    {
      "line": 42,
      "name": "@vijaytest"
    }
  ]
});
formatter.step({
  "line": 44,
  "name": "Epoints API is responding to requests",
  "keyword": "Given "
});
formatter.step({
  "line": 45,
  "name": "Multival facets will be requested for \u0027null\u0027, \u0027v2\u0027 for specified filter option \u0027s_brandName\u0027",
  "matchedColumns": [
    0,
    1,
    2
  ],
  "keyword": "When "
});
formatter.step({
  "line": 46,
  "name": "Correct facet options \u0027s_brandName\u0027 value are returned according to solr for business \u0027null\u0027",
  "matchedColumns": [
    0,
    2
  ],
  "keyword": "Then "
});
formatter.match({
  "location": "HealthCheckSteps.epointsIsResponding()"
});
formatter.result({
  "duration": 2744118513,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "null",
      "offset": 39
    },
    {
      "val": "v2",
      "offset": 47
    },
    {
      "val": "s_brandName",
      "offset": 80
    }
  ],
  "location": "SearchSteps.requestFactesForSelectedMultivalFilter(String,String,String)"
});
