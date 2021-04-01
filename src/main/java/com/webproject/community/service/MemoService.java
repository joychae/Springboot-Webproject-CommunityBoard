package com.webproject.community.service;

import com.webproject.community.exception.InvalidMemoIdException;
import com.webproject.community.model.dto.MemoRequestDto;
import com.webproject.community.model.entity.Memo;
import com.webproject.community.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.omg.CORBA.DynAnyPackage.Invalid;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final UserService userService;
    private final MemoRepository memoRepository;

    // Memo 객체를 Comment 객체에 연결시키기 위한 사전 작업 처리, Service Layer 에 구현!
    public Memo findById(Long id) {
        return memoRepository.findById(id).orElseThrow(()-> new InvalidMemoIdException());
    }

    // 게시글 생성 기능
    public Memo createMemo(MemoRequestDto requestDto) {
        // 요청받은 DTO 로 DB에 저장할 객체 만들기
        Memo memo = new Memo(requestDto, userService);
        memoRepository.save(memo);
        return memo;
    }

    // 게시글 전체 조회 기능
    public List<Memo> getAllMemos() {
        return memoRepository.findAllByOrderByModifiedAtDesc();
    }

    // 게시글 상세 조회 기능
    public Memo getEachMemo(Long id) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("찾는 포스팅이 존재하지 않습니다")
        );
        return memo;
    }

    // 게시글 작성자가 쓴 다른 게시글 모아보기 기능
    public List<Memo> getMemoByUser(Long accountId) {
        return memoRepository.findByUserIdOrderByModifiedAtDesc(accountId);
    }

}
