package com.example.jennie.semiprojectv1.service;

import com.example.jennie.semiprojectv1.domain.BoardDTO;
import com.example.jennie.semiprojectv1.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardMapper;

    @Override
    public List<BoardDTO> readBoard(int cpg) {
        int stnum = (cpg-1) * 25;

        return boardMapper.selectBoard(stnum);
    }

}
