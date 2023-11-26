package com.example.team_p.controller;

import com.example.team_p.dto.CommonResponseDto;
import com.example.team_p.dto.PostRequestDto;
import com.example.team_p.dto.PostResponseDto;
import com.example.team_p.security.UserDetailsImpl;
import com.example.team_p.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.RejectedExecutionException;

@RequestMapping("/posts")
@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    //게시글 작성
    @PostMapping("/posts/create")
    @ResponseStatus(HttpStatus.CREATED)
    public PostResponseDto addPost(@RequestBody PostRequestDto postingRequestDto) {
        return postService.createPost(postingRequestDto);
    }
    //postId로 게시글 조회
    @GetMapping("/posts/{postId}")
    public PostResponseDto getPost(@PathVariable Long postId){
        return postService.getPost(postId);
    }
    //모든 게시글 조회
//     @GetMapping("/main")
//     public List<PostResponseDto> getPosts(){
//         return postService.getPosts();
//     }

    // 메인 페이지 조회(게시물 전체 조회)
    @GetMapping()
    public ResponseEntity<List<PostResponseDto>> getPostList() {
        List<PostResponseDto> responseDto = postService.getPostList();
        return ResponseEntity.ok().body(responseDto);
    }

    // 생성

    // 조회

    // 수정
    @PutMapping("/{postId}")
    public ResponseEntity<CommonResponseDto> putPost(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long postId, @RequestBody PostRequestDto postRequestDto) {
        try {
            PostResponseDto responseDto = postService.update(userDetails.getUser(), postId, postRequestDto);
            return ResponseEntity.ok().body(responseDto);
        } catch (RejectedExecutionException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new CommonResponseDto("작성자만 수정할 수 있습니다.", HttpStatus.BAD_REQUEST.value()));
        }
    }

    // 삭제
    @DeleteMapping("/{postId}")
    public ResponseEntity<CommonResponseDto> deletePost(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long postId) {
        try {
            postService.deletePost(userDetails.getUser(), postId);
            return ResponseEntity.ok().body(new CommonResponseDto("게시물 삭제가 완료되었습니다.", HttpStatus.OK.value()));
        } catch (RejectedExecutionException | IllegalArgumentException e){
            return ResponseEntity.badRequest().body(new CommonResponseDto("작성자만 삭제할 수 있습니다.", HttpStatus.BAD_REQUEST.value()));
        }
    }

}
