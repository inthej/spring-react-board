package com.board.backend.mapper;

import com.board.backend.model.BoardCommentDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardCommentMapper {

    List<BoardCommentDto.ListItem> selectBoardComments(@Param("bid") long bid);

    BoardCommentDto.Response selectBoardCommentById(@Param("id") long id);

    BoardCommentDto.Detail selectBoardCommentDetailById(@Param("id") long id);

    @Options(useGeneratedKeys = true, keyProperty = "form.id")
    void insertBoardComment(@Param("bid") long bid, @Param("form") BoardCommentDto.Create form);

    void updateBoardCommentById(@Param("id") long id, @Param("form") BoardCommentDto.Update form);

    void deleteBoardCommentById(@Param("id") long id);

    void deleteAllByBoardId(@Param("bid") long bid);
}
