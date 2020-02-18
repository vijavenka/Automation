package com.iat.repository;

import com.iat.domain.Tag;

public interface TagRepository {
    Tag findByTagKey(String tagKey);

    void deleteTagByTagKey(String tagKey);
}