package com.board.backend.service;

import com.board.backend.mapper.BoardMapper;
import com.board.backend.model.BoardDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardMapper boardMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BoardDto.Response get(long id) {
        return boardMapper.selectById(id);
    }

    @Override
    public BoardDto.Response insert(BoardDto.Create form) {
        boardMapper.insert(form);
        return modelMapper.map(form, BoardDto.Response.class);
    }

    @Override
    public BoardDto.Response update(long id, BoardDto.Update form) {
        boardMapper.update(id, form);
        return boardMapper.selectById(id);
    }

    @Override
    public BoardDto.Response delete(long id) {
        final BoardDto.Response response = boardMapper.selectById(id);
        if (ObjectUtils.isEmpty(response)) {
            return response;
        }
        boardMapper.delete(id);
        return response;
    }
}
