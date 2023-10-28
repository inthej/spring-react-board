package com.board.backend.mapper;

import com.board.backend.model.BoardDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {

    List<BoardDto.ListItem> selectBoardsWithKeyword(@Param("keyword") String keyword);
    BoardDto.Response selectBoardById(long no);

    BoardDto.Detail selectBoardDetailById(long no);

    @Options(useGeneratedKeys = true, keyProperty = "form.no")
    void insertBoard(@Param("form") BoardDto.Create form);

    void updateBoardById(@Param("no") long no, @Param("form") BoardDto.Update form);

    void deleteBoardById(@Param("no") long no);

    void incrementViewCountById(@Param("no") long no);
}
