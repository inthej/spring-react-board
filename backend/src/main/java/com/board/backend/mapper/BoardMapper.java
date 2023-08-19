package com.board.backend.mapper;

import com.board.backend.model.BoardDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {

    List<BoardDto.Response> list();
    BoardDto.Response selectById(long id);

    @Options(useGeneratedKeys = true, keyProperty = "form.id")
    void insert(@Param("form") BoardDto.Create form);

    void update(@Param("id") long id, @Param("form") BoardDto.Update form);

    void deleteById(@Param("id") long id);
}
