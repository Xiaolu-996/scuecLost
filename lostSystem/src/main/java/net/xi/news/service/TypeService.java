package net.xi.news.service;

import net.xi.news.pojo.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.lang.model.element.TypeElement;
import java.util.List;

public interface TypeService {

    //保存分类
    Type saveType(Type type);

    //根据id得到分类
    Type getType(Long id);

    //根据分类名得到分类
    Type getTypeByName(String name);

    //分页，找出全部分类
    Page<Type> listType(Pageable pageable);

    //更新分类
    Type updateType(Long id,Type type);

    //列出前？分类
    List<Type> typeTop(Integer size);

    //删除分类
    void deleteType(Long id);

    //找出所有分类
    List<Type> listType();
}
