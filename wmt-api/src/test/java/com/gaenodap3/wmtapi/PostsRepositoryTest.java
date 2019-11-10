package com.gaenodap3.wmtapi;

import java.util.List;

import com.gaenodap3.wmtapi.domain.Posts;
import com.gaenodap3.wmtapi.domain.PostsRepository;

import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootTest
@EnableJpaRepositories(basePackages = "com.gaenodap3.wmtapi.domain", entityManagerFactoryRef = "emf")
class PostsRepositoryTest {

	@Autowired
	private PostsRepository postsRepository;

	@Test
	public void content_save_test() {
		Posts posts = new Posts("타이틀", "본문", "hyeona");
		postsRepository.save(posts);
	}

	@Test
	public void content_load_test() {
		// given
		List<Posts> postsList = postsRepository.findAll();

		// when
		Posts post = postsList.get(0);
		
		// then
		System.out.println(post.getTitle());
		System.out.println(post.getContent());
	}

	@After(value = "")
	public void content_deleta_all_test(){
		postsRepository.deleteAll();
	}

}
