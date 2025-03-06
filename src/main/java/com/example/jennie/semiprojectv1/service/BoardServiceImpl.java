package com.example.jennie.semiprojectv1.service;

import com.example.jennie.semiprojectv1.domain.*;
import com.example.jennie.semiprojectv1.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Size;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardMapper;
    @Value(  "${board.page-size}") private  int pageSize;

    @Transactional
    @Override
    public BoardListDTO readBoard(int cpg) {
        int stnum = (cpg-1) * pageSize;
        int totalItems = boardMapper.countBoard();
        List<BoardDTO> boards = boardMapper.selectBoard(stnum, pageSize);

        return new BoardListDTO(cpg, totalItems, pageSize, boards );
    }

   // @Override
  //  public int countBoard(int pageSize ) {

    //    return boardMapper.countPagesBoard(pageSize);
 //   }

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

    @Override
    public boolean newReply(NewReplyDTO newReplyDTO) {

        int result = boardMapper.insertReply(newReplyDTO);
        return result > 0;

    }

    @Override
    public List<Reply> readReply(int pno) {
        return boardMapper.selectReply(pno);
    }

    @Override
    public boolean newComment(NewReplyDTO newReplyDTO) {
       int result =  boardMapper.insertComment(newReplyDTO);
        return result > 0;
    }

}
