package net.xi.news.service;

import net.xi.news.NotFoundException;
import net.xi.news.dao.TypeRespository;
import net.xi.news.pojo.Type;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TypeServiceImpl implements TypeService{


    @Autowired
    private TypeRespository typeRespository;

    @Transactional
    @Override
    //保存分类
    public Type saveType(Type type) {
        return typeRespository.save(type);
    }
    @Transactional
    @Override
    //根据id得到分类
    public Type getType(Long id) {
        return typeRespository.getOne(id);
    }

    @Override
    //根据分类名得到分类
    public Type getTypeByName(String name) {
        return typeRespository.findByName(name);
    }

    @Override
    //找出所有分类
    public List<Type> listType() {

        return typeRespository.findAll();
    }

    @Transactional
    @Override
    //分页，找出全部分类
    public Page<Type> listType(Pageable pageable) {
        return typeRespository.findAll(pageable);
    }

    @Transactional
    @Override
    //更新分类
    public Type updateType(Long id, Type type) {
        Type type1 = typeRespository.getOne(id);
        if (type1 == null){
            throw new NotFoundException("不存在该类型");
        }
        BeanUtils.copyProperties(type,type1);
        return typeRespository.save(type1);
    }

    @Override
    //列出前？分类
    public List<Type> typeTop(Integer size) {
        Sort.Order order=new Sort.Order(Sort.Direction.DESC, "news.size");
        Pageable pageable =  PageRequest.of(0,size,Sort.by(order));
        return typeRespository.findTop(pageable);
    }

    @Transactional
    @Override
    //删除分类
    public void deleteType(Long id) {
        typeRespository.deleteById(id);
    }
}
