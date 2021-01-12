package com.jojoldu.book.springboot.domain.posts;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @Test
    public void clanup(){
        postsRepository.deleteAll();
    }


    @Test
    public void 게시글_불러오기(){

        String title = "게시글 불러오기";
        String content = "게시글 본문";

        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("ooeeda@gmail.com")
                .build());
        postsRepository.save(Posts.builder()
                .title("게시글 불러오기")
                .content("게시글 본문")
                .author("ooeeda@gmail.com")
                .build());

        List<Posts> postsList = postsRepository.findAll();


        Posts posts = postsList.get(0);

        System.out.println(posts.getTitle());

        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);



    }
    @Test
    public void BaseTimeEntity_등록(){

        LocalDateTime now = LocalDateTime.of(2019,6,4,0,0,0);

        postsRepository.save(Posts.builder()
                .title("게시글 불러오기")
                .content("게시글 본문")
                .author("author")
                .build());

        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>>> createDate="+posts.getCreatedDate()+", modifiedDate="+posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);

    }

}
