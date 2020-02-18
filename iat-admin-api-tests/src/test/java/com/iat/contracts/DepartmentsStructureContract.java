package com.iat.contracts;

public class DepartmentsStructureContract {

    public String getDepartmentsCPath() {
        return "/api/departments";
    }

    public String getDepartmentsUDPath(String departmentId) {
        return "/api/departments/" + departmentId;
    }

    public String getMoveDepartmentsPath(String departmentId) {
        return "/api/departments/" + departmentId + "/move";
    }

    public String getDepartmentsStructurePath(String withRoot) {
        String path = "/api/departments";
        if (!withRoot.equals("null"))
            path += "?withRoot=" + withRoot;
        return path;
    }

    public String getDepartmentsStructureValidPath() {
        return "/api/departments/valid/structure";
    }
}
