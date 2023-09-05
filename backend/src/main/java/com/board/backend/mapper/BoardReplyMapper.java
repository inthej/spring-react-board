package com.board.backend.mapper;

import com.board.backend.model.BoardReplyDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardReplyMapper {

    List<BoardReplyDto.ListItem> selectBoardReplys(@Param("bid") long bid);

    BoardReplyDto.Response selectBoardReplyById(@Param("id") long id);

    BoardReplyDto.Detail selectBoardReplyDetailById(@Param("id") long id);

    @Options(useGeneratedKeys = true, keyProperty = "form.id")
    void insertBoardReply(@Param("form") BoardReplyDto.Create form);

    void updateBoardReplyById(@Param("id") long id, @Param("form") BoardReplyDto.Update form);

    void deleteBoardReplyById(@Param("id") long id);
}
