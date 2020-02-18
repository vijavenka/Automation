package com.iat.repository.impl;

import com.iat.Config;
import com.iat.domain.Tag;
import com.iat.repository.TagRepository;

import static io.restassured.RestAssured.delete;
import static io.restassured.RestAssured.get;

public class TagRepositoryImpl implements TagRepository {
    @Override
    public Tag findByTagKey(String tagKey) {
        return get(Config.getDoormanUrl() + "/tags?tagKey=" + tagKey).getBody().as(Tag.class);
    }

    @Override
    public void deleteTagByTagKey(String tagKey) {
        delete(Config.getDoormanUrl() + "/tags?tagKey=" + tagKey);
    }

}