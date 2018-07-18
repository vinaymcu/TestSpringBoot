/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.concretepage.repository;

import com.concretepage.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Vinay.Gupta
 */
@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

}
