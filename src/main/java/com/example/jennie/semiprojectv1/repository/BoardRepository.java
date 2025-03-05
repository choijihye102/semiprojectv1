package com.example.jennie.semiprojectv1.repository;

import com.example.jennie.semiprojectv1.domain.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardRepository {

    @Select("select bno, title, userid, regdate, thumbs, views  from boards order by bno desc limit #{stnum}, #{pageSize}")  //이러면, pageSize-1 범위까지 가져옴.
    List<BoardDTO> selectBoard(int stnum, int pageSize);

    @Select("select ceil(count(bno) / #{pageSize}) cntpg from boards")
    int countPagesBoard(int pageSize);

    List<BoardDTO> selelctFindBoard(Map<String, Object> params);

    int countFindBoard(Map<String, Object> params);

    @Select("select * from boards where bno = #{bno}")
    Board selectOneBoard(int bno );

    @Update("update boards set views = views + 1 where  bno = #{bno}")
    void updateViewOne(int bno);

    @Insert("insert into boards (title, userid, contents) values (#{title}, #{userid}, #{contents})")
    int insertBoard(NewBoardDTO newBoardDTO);

    @Insert("insert into replys (userid, comments,  ref, pno) values (#{userid}, #{comments}, last_insert_id()+1, #{pno})")
    int insertReply(NewReplyDTO newReplyDTO);

    @Select("select  * from replys where pno = #{pno} order by ref")
    List<Reply> selectReply(int pno);
}
