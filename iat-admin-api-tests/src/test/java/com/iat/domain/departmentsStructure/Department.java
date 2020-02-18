package com.iat.domain.departmentsStructure;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iat.Config;
import com.iat.domain.AbstractDomain;
import com.iat.utils.DataExchanger;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class Department extends AbstractDomain {

    private String id;
    private String name;
    private String userNumber;
    private String managerName;
    private List<Department> subdepartments;
    private Boolean active;

    public Department() {
    }

    public Department(String name) {
        this.setName(name);
    }

    public Department(String id, String name, String userNumber, String managerName, List<Department> subdepartments, Boolean active) {
        this.setId(id);
        this.setName(name);
        this.setUserNumber(userNumber);
        this.setUserNumber(managerName);
        this.setSubdepartments(subdepartments);
        this.setActive(active);
    }

    public void setId(String id) {
        if (id != null && id.equals("previously_created_department"))
            id = DataExchanger.getInstance().getDepartmentObject().getId();
        this.id = id;
    }

    public void setName(String name) {
        String id = this.getId();
        if (id == null) {
            if (name != null && name.contains("Department level"))
                id = Config.getDepartmentIdForName(name);
            else
                id = name;
            this.setId(id);
        }
        this.name = name;
    }

}
