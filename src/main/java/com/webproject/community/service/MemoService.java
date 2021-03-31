package com.webproject.community.service;

import com.webproject.community.model.dto.MemoRequestDto;
import com.webproject.community.model.entity.Memo;
import com.webproject.community.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;

    // DB에 접근해서 모든 게시글 정보 가져오기 (get)
    public List<Memo> getAllMemos() {
        return memoRepository.findAllByOrderByModifiedAtDesc();
    }

    // 클라이언트에서 보낸 게시글 정보들을 MemoRequestDto 로 객체를 만들어 저장하기
    public Memo createMemo(MemoRequestDto requestDto, Long userId) {

        // 요청받은 DTO 로 DB에 저장할 객체 만들기
        Memo memo = new Memo(requestDto, userId);
        memoRepository.save(memo);
        return memo;
    }

    // 게시글 자세히 보기
    public Memo getEachMemo(Long id) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("찾는 포스팅이 존재하지 않습니다")
        );
        return memo;
    }

    public List<Memo> getMemoByUser(Long accountId) {
        return memoRepository.findByUserIdOrderByModifiedAtDesc(accountId);
    }

    @Transactional
    public List<Memo> memoSearch(String keyword) {
        return memoRepository.findByTitleContaining(keyword);
    }
}
