package com.example.jennie.semiprojectv1.service;

import com.example.jennie.semiprojectv1.domain.*;

import java.util.List;

public interface BoardService {

    BoardListDTO readBoard(int cpg);

 //   int countBoard(int pageSize );

    List<BoardDTO> findBoard(int cpg, String findtype, String findkey);

    int countfindBoard( String findtype, String findkey);

    Board readOndBoard(int bno);

    void readOneView(int bno);

    boolean newBoard(NewBoardDTO newboardDTO);

    boolean newReply(NewReplyDTO newReplyDTO);

    List<Reply> readReply(int pno);

    boolean newComment(NewReplyDTO newReplyDTO);
}
