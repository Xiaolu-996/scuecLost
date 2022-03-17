package net.xi.news.service;

import net.xi.news.NotFoundException;
import net.xi.news.dao.TagRespository;
import net.xi.news.pojo.Tag;
import net.xi.news.pojo.Type;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
public class TagServiceImpl implements TagService{

    @Autowired
    private TagRespository tagRespository;

    @Transactional
    @Override
    //保存地点
    public Tag saveTag(Tag tag) {
        return tagRespository.save(tag);
    }

    @Transactional
    @Override
    //根据id得到地点
    public Tag getTag(Long id) {
        return tagRespository.getOne(id);
    }

    @Override
    //根据地点名得到地点
    public Tag getTagByName(String name) {
       return tagRespository.findByName(name);
    }

    @Override
    //找到前？地点
    public List<Tag> tagTop(Integer size) {
        Sort.Order order=new Sort.Order(Sort.Direction.DESC, "news.size");
        Pageable pageable =  PageRequest.of(0,size,Sort.by(order));
        return tagRespository.findTop(pageable);
    }

    @Override
    public List<Tag> ListTag(String ids) {
        return tagRespository.findAllById(convertToList(ids));
    }

    private List<Long> convertToList(String ids){
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids!=null){
            String[] idarray = ids.split(",");
            for (int i=0;i < idarray.length;i++){
                list.add(new Long(idarray[i]));
            }
        }
        return list;
    }

    @Override
    public List<Tag> getAllTag() {
        return tagRespository.findAll();
    }

    @Transactional
    @Override
    //分页，列出地点
    public Page<Tag> listTag(Pageable pageable) {
        return tagRespository.findAll(pageable);
    }

    @Transactional
    @Override
    //更新地点
    public Tag updateTag(Long id, Tag tag) {
        Tag tag1 = tagRespository.getOne(id);
        if (tag1 == null){
            throw new NotFoundException("不存在该地点");
        }
        BeanUtils.copyProperties(tag,tag1);
        return tagRespository.save(tag1);
    }

    @Transactional
    @Override
    //删除地点
    public void deleteTag(Long id) {
        tagRespository.deleteById(id);
    }
}
