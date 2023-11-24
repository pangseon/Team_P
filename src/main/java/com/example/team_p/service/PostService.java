package com.example.team_p.service;

import com.example.team_p.dto.PostRequestDto;
import com.example.team_p.dto.PostResponseDto;
import com.example.team_p.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import com.example.team_p.entity.Post;
import com.example.team_p.repository.PostRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.RejectedExecutionException;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;


    // 메인 페이지 조회(게시물 전체 조회)
    public List<PostResponseDto> getPostList() {
        return postRepository.findAllByOrderByCreateDateDesc().stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList());
    }

    // 생성
    public PostResponseDto createPost(PostRequestDto requestDto) {
        Post post = new Post(requestDto);
        Post savePost = postRepository.save(post);
        return new PostResponseDto(savePost);
    }

    // 단건 조회
    public PostResponseDto getPost(Long postId){
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NullPointerException("해당 게시글을 찾을 수 없습니다."));

        return new PostResponseDto(post);
    }
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
