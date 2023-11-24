package com.sparta.memo2.controller;

import com.sparta.memo2.dto.MemoRequestDto;
import com.sparta.memo2.dto.MemoResponseDto;
import com.sparta.memo2.entity.Memo;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")

public class MemoController {
  private final Map<Long, Memo> memoList = new HashMap<>();

  @PostMapping("/memos")
  public MemoResponseDto CreateMemo(@RequestBody MemoRequestDto requestDto) {
//RequestDto -> Entity
    Memo memo = new Memo(requestDto);
    //Memo Maax Id Check
    Long maxId = memoList.size() > 0 ? Collections.max(memoList.keySet()) + 1 : 1;
    memo.setId(maxId);

    //DB 저장
    memoList.put(memo.getId(), memo);

    //Entity -> ReponseDto
    MemoResponseDto memoResponseDto = new MemoResponseDto(memo);

    return memoResponseDto;

  }

  @GetMapping("/memos")
  public List<MemoResponseDto> getMemos() { // 메모가 하나 일 리는 없다.
    //Map To List
    List<MemoResponseDto> responseList = memoList.values().stream()
        .map(MemoResponseDto::new).toList();

    return responseList;
  }

  @PutMapping("/memos/{id}")
  public Long updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) {
    // 해당 메모가 DB 에 존재하는지 확인
    if (memoList.containsKey(id)) {
      //해당 메모 가져오기
      Memo memo = memoList.get(id);

      //memo수정
      memo.update(requestDto);
      return memo.getId();

    } else {
      throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
    }
    }
    @DeleteMapping("/memos/{id}")
    public Long deleteMemo (@PathVariable Long id){
      //해당 메모가 DB 에 존재하는지 확인
      if (memoList.containsKey(id)) {
        memoList.remove(id);
        return id;
      } else {
        throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
      }
    }
  }

