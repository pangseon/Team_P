package com.example.team_p.service;

import com.example.team_p.entity.PostEntity;
import com.example.team_p.dto.PostRequestDto;
import com.example.team_p.dto.PostResponseDto;
import com.example.team_p.repository.PostJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.team_p.dto.PostRequestDto;
import com.example.team_p.dto.PostResponseDto;
import com.example.team_p.entity.Post;
import com.example.team_p.entity.User;
import com.example.team_p.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.RejectedExecutionException;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostJpaRepository postRepository;
    public PostResponseDto createPost(PostRequestDto requestDto) {
        PostEntity postEntity = new PostEntity(requestDto);
        PostEntity savePost = postRepository.save(postEntity);
        return new PostResponseDto(savePost);
    }

    public PostResponseDto getPost(Long postId){
        PostEntity postEntity = postRepository.findById(postId)
                .orElseThrow(() -> new NullPointerException("해당 게시글을 찾을 수 없습니다."));

        return new PostResponseDto(postEntity);
    }

    public List<PostResponseDto> getPosts(){
        return postRepository.findAll().stream().
                map(PostResponseDto::new).collect(Collectors.toList());
/////////////////////////////////////////////////////////////////////////////////////////////////////////
    private final PostRepository postRepository;

    // 메인 페이지 조회(게시물 전체 조회)
    public List<PostResponseDto> getPostList() {
        return postRepository.findAllByOrderByCreateDateDesc().stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList());
    }

    // 생성

    // 조회

    // 수정
    @Transactional
    public PostResponseDto update(User user, Long postId, PostRequestDto postRequestDto) {
        Post post = getPost(postId, user);

        post.setTitle(postRequestDto.getTitle());
        post.setContent(postRequestDto.getContent());

        return new PostResponseDto(post);
    }

    // 삭제
    public void deletePost(User user, Long postId) {
        Post post = getPost(postId, user);

        postRepository.delete(post);
    }


    // 검증
    public Post getPost(Long postId, User user) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));

        if(!user.getId().equals(post.getUser().getId())) {
            throw new RejectedExecutionException("작성자만 수정할 수 있습니다.");
        }

        return post;
    }
}
