package com.example.jennie.semiprojectv1.repository;

import com.example.jennie.semiprojectv1.domain.Board;
import com.example.jennie.semiprojectv1.domain.BoardDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;
import java.util.Map;

@Mapper
public interface BoardRepository {

    @Select("select bno, title, userid, regdate, thumbs, views  from boards order by bno desc limit #{stnum}, #{pageSize}")
    List<BoardDTO> selectBoard(int stnum, int pageSize);

    @Select("select ceil(count(bno) / #{pageSize}) cntpg from boards")
    int countPagesBoard(int pageSize);

    List<BoardDTO> selelctFindBoard(Map<String, Object> params);

    int countFindBoard(Map<String, Object> params);

    @Select("select * from boards where bno = #{bno}")
    Board selectOneBoard(String bno );
}
