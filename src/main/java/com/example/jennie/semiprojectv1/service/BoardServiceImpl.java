package com.example.jennie.semiprojectv1.service;

import com.example.jennie.semiprojectv1.domain.Board;
import com.example.jennie.semiprojectv1.domain.BoardDTO;
import com.example.jennie.semiprojectv1.domain.NewBoardDTO;
import com.example.jennie.semiprojectv1.repository.BoardRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardMapper;
    @Value(  "${board.page-size}") private  int pageSize;

    @Override
    public List<BoardDTO> readBoard(int cpg, int pageSize) {
        int stnum = (cpg-1) * pageSize;

        return boardMapper.selectBoard(stnum, pageSize);
    }

    @Override
    public int countBoard(int pageSize ) {

        return boardMapper.countPagesBoard(pageSize);
    }

    @Override
    public List<BoardDTO> findBoard(int cpg, String findtype, String findkey) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("stnum", (cpg-1) * pageSize);
        params.put("pageSize",  pageSize);
        params.put("findtype",  findtype);
        params.put("findkey",  findkey);

        return boardMapper.selelctFindBoard(params);
    }

    @Override
    public int countfindBoard(String findtype, String findkey) {
        Map<String, Object> params = new HashMap<>();
        params.put("pageSize",  pageSize);
        params.put("findtype",  findtype);
        params.put("findkey",  findkey);

        return boardMapper.countFindBoard(params);
    }

    @Override
    public Board readOndBoard(int bno) {
        return boardMapper.selectOneBoard(bno);
    }

    @Override
    public void readOneView(int bno) {
        boardMapper.updateViewOne(bno);
    }

    @Override
    public boolean newBoard(NewBoardDTO newboardDTO) {
        int result = boardMapper.insertBoard(newboardDTO);
        return result > 0;
    }

}
