package com.iat.domain.EcardsConfig;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iat.domain.AbstractDomain;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class Template extends AbstractDomain {

    private String id;
    private String createdAt;
    private String name;
    private String imageUrl;
    private String thumbnailUrl;

    public void setName(String name) {
        if (name != null)
            name = name.replace("RANDOM_NAME", " " + new Date().getTime());
        this.name = name;
    }

}