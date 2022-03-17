package net.xi.news.dao;

import net.xi.news.pojo.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface NewsRespository extends JpaRepository<News, Long>, JpaSpecificationExecutor<News> {

	//紧急失物
	@Query("select n from News n where n.recommend = true")
	List<News> findTop(Pageable pageable);

	//根据title or content 进行模糊查询
	@Query("select n from News n where n.title like ?1 or n.content like ?1")
	Page<News> findByQuery(String query, Pageable pageable);

	//查询所有月份
	@Query("select function('date_format',n.updateTime,'%M') as month from News n group by function('date_format',n.updateTime,'%M') order by month DESC ")
	List<String> findGroupBy();

	//根据ID更新losses
	@Transactional
	@Modifying
	@Query("update News n set n.views = n.views+1 where n.id = ?1")
	int updateViews(Long id);

	//根据月份查询loss(查询该月的losses)
	@Query("select n from News n where function('date_format',n.updateTime,'%M') = ?1")
	List<News> findByMonth(String month);

	//根据类别删除losses
	@Transactional
	@Modifying
	@Query("delete from News n where n.type = ?1")
	int deleteNewsByTypeId(Long id);
}
