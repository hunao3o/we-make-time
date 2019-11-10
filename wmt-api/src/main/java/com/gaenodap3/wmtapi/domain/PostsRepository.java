package com.gaenodap3.wmtapi.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA Repository (DAO 역할을 하는 DB Layer)
 * 
 * @extends JpaRepository<사용할 Entity, Primitiver type>
 * extends 적용시 기본적인 CRUD 메소드가 자동생성되며
 * @Repository를 하지 않아도 사용 가능
 */
public interface PostsRepository extends JpaRepository<Posts, Long> {

}