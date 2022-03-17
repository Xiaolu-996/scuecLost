package net.xi.news.service;

import net.xi.news.NotFoundException;
import net.xi.news.dao.NewsRespository;
import net.xi.news.pojo.News;
import net.xi.news.pojo.NewsQuery;
import net.xi.news.util.MarkdownUtils;
import net.xi.news.util.MyBeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.*;

@Service
public class NewsServiceImpl implements NewsService {


	@Autowired
	NewsRespository newsRespository;

	@Transactional
	@Override
	//根据id得到loss
	public News getNews(Long id) {
		return newsRespository.getOne(id);
	}

	@Override
	//分页，列出所有loss
	public Page<News> listNews(Pageable pageable) {
		return newsRespository.findAll(pageable);
	}

	@Override
	//根据id得到loss，进行模糊查询
	public Page<News> listNews(Pageable pageable, NewsQuery news) {
		return newsRespository.findAll(new Specification<News>() {
			@Override
			public Predicate toPredicate(Root<News> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();
				if (!"".equals(news.getTitle()) && news.getTitle() != null) {
					predicates.add(cb.like(root.<String>get("title"), "%" + news.getTitle() + "%"));

				}
				if (news.getTypeId() != null) {
					predicates.add(cb.equal(root.<String>get("type").get("id"), news.getTypeId()));

				}
				if (news.isRecommend()) {
					predicates.add(cb.equal(root.<Boolean>get("recommend"), news.isRecommend()));
				}
				cq.where(predicates.toArray(new Predicate[predicates.size()]));
				return null;
			}
		}, pageable);
	}

	@Transactional
	@Override
	//保存loss
	public News saveNews(News news) {
		if (news.getId() == null) {
			news.setCreateTime(new Date());
			news.setUpdateTime(new Date());
			news.setViews(0);
		} else {
			news.setUpdateTime(new Date());
		}


		return newsRespository.save(news);
	}


	@Override
	//分页列出查询得到的loss
	public Page<News> listsearchNews(String query, Pageable pageable) {
		return newsRespository.findByQuery(query, pageable);
	}

	@Override
	//列出前？loss
	public List<News> newsTop(Integer size) {
		Sort.Order order = new Sort.Order(Sort.Direction.DESC, "updateTime");
		Pageable pageable = PageRequest.of(0, size, Sort.by(order));
		return newsRespository.findTop(pageable);
	}

	@Transactional
	@Override
	//更新loss
	public News updateNews(Long id, News news) {
		News news1 = newsRespository.getOne(id);
		if (news1 == null) {
			throw new NotFoundException("该loss不存在");
		}
		BeanUtils.copyProperties(news, news1, MyBeanUtils.getNullPropertyNames(news));
		news1.setUpdateTime(new Date());
		return newsRespository.save(news1);
	}

	@Transactional
	@Override
	//删除loss
	public void deleteNews(Long id) {
		newsRespository.deleteById(id);
	}


	@Override
	//得到loss详情内容
	public News getConvert(Long id) {
		News news = newsRespository.getOne(id);
		if (news == null) {
			throw new NotFoundException("该loss不存在");
		}
		News n = new News();
		BeanUtils.copyProperties(news, n);
		String content = n.getContent();
		n.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
		newsRespository.updateViews(id);
		return n;
	}

	@Override
	//根据标签列出loss
	public Page<News> listNewsByTag(Long tagId, Pageable pageable) {
		return newsRespository.findAll(new Specification<News>() {
			@Override
			public Predicate toPredicate(Root<News> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				Join join = root.join("tags");
				return cb.equal(join.get("id"), tagId);
			}
		}, pageable);
	}


	@Override
	//根据月份进行分类，列出loss
	public Map<String, List<News>> holeNews() {
		//查询所有月份
		List<String> months = newsRespository.findGroupBy();
		Map<String, List<News>> map = new HashMap<>();
		for (String month : months) {
			map.put(month, newsRespository.findByMonth(month));
		}
		return map;
	}

	@Override
	//统计loss数量
	public Long countNews() {
		return newsRespository.count();
	}

	@Override
	public int deleteNewsByTypeId(Long id) {
		return newsRespository.deleteNewsByTypeId(id);
	}
}
