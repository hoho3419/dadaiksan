package com.eanswer.dadaiksan.Repository;

import com.eanswer.dadaiksan.Entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article,Long> {
}
