package com.epam.msu.dao;

import com.epam.msu.entity.Tag;

import java.util.List;

public interface TagDao {
    Tag createTag(Tag tag);
    void updateTag(Tag tag);
    Tag getTagById(int id);
    Tag getTagByName(String name);

    Tag getTagByCertificateId(int certificateId);
    Tag getTagByTagName(String name);

    List<Tag> getAllTags();
}
