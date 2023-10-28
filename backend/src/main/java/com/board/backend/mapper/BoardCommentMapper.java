package com.board.backend.mapper;

import com.board.backend.model.BoardCommentDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardCommentMapper {

    List<BoardCommentDto.ListItem> selectBoardComments(@Param("bno") long bno);

    BoardCommentDto.Response selectBoardCommentById(@Param("no") long no);

    BoardCommentDto.Detail selectBoardCommentDetailById(@Param("no") long no);

    @Options(useGeneratedKeys = true, keyProperty = "form.no")
    void insertBoardComment(@Param("bno") long bno, @Param("form") BoardCommentDto.Create form);

    void updateBoardCommentById(@Param("no") long no, @Param("form") BoardCommentDto.Update form);

    void deleteBoardCommentById(@Param("no") long no);

    void deleteAllByBoardId(@Param("bno") long bno);
}
