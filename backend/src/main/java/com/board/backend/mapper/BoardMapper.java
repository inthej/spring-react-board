package com.board.backend.mapper;

import com.board.backend.model.BoardDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {

    List<BoardDto.Response> selectBoardsWithKeyword(@Param("keyword") String keyword);
    BoardDto.Response selectBoardById(long id);

    @Options(useGeneratedKeys = true, keyProperty = "form.id")
    void insertBoard(@Param("form") BoardDto.Create form);

    void updateBoardById(@Param("id") long id, @Param("form") BoardDto.Update form);

    void deleteBoardById(@Param("id") long id);
}
