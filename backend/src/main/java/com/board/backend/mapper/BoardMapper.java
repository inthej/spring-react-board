package com.board.backend.mapper;

import com.board.backend.model.BoardDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BoardMapper {
    BoardDto.Response selectById(long id);

    @Options(useGeneratedKeys = true, keyProperty = "form.id")
    void insert(@Param("form") BoardDto.Create form);

    void update(@Param("id") long id, @Param("form") BoardDto.Update form);

    void deleteById(@Param("id") long id);
}
