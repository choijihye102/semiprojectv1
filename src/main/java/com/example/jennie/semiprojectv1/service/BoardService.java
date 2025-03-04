package com.example.jennie.semiprojectv1.service;

import com.example.jennie.semiprojectv1.domain.Board;
import com.example.jennie.semiprojectv1.domain.BoardDTO;
import java.util.List;

public interface BoardService {

    List<BoardDTO> readBoard(int cpg, int pageSize);

    int countBoard(int pageSize );

    List<BoardDTO> findBoard(int cpg, String findtype, String findkey);

    int countfindBoard( String findtype, String findkey);

    Board readOndBoard(int bno);

    void readOneView(int bno);

}
