package net.xi.news.service;

import net.xi.news.pojo.Tag;
import net.xi.news.pojo.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagService {


    //保存地点
    Tag saveTag(Tag tag);

    //根据id得到地点
    Tag getTag(Long id);

    //根据地点名得到地点
    Tag getTagByName(String name);

    //分页，列出地点
    Page<Tag> listTag(Pageable pageable);

    List<Tag> getAllTag();

    //找到前？地点
    List<Tag> tagTop(Integer size);

    //列出地点，news里面没有建立关联
    List<Tag> ListTag(String ids);

    //更新地点
    Tag updateTag(Long id,Tag tag);

    //删除地点
    void deleteTag(Long id);
}
