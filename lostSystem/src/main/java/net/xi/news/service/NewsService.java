package net.xi.news.service;

import net.xi.news.pojo.News;
import net.xi.news.pojo.NewsQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface NewsService {

	//根据id得到loss
	News getNews(Long id);

	//根据id得到lost，进行模糊查询
	Page<News> listNews(Pageable pageable, NewsQuery news);

	//分页，列出所有losses
	Page<News> listNews(Pageable pageable);

	//列出前？loss
	List<News> newsTop(Integer size);

	//根据地点列出loss
	Page<News> listNewsByTag(Long tagId, Pageable pageable);

	//得到loss详情内容
	News getConvert(Long id);

	//分页列出查询得到的loss
	Page<News> listsearchNews(String query, Pageable pageable);

	//保存loss
	News saveNews(News news);

	//更新loss
	News updateNews(Long id, News news);

	//删除loss
	void deleteNews(Long id);

	//根据月份进行分类，列出loss
	Map<String, List<News>> holeNews();

	//统计loss数量
	Long countNews();

	//根据typeid删除与该分类相关的所有文章
	int deleteNewsByTypeId(Long id);
}
