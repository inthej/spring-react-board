package com.board.backend.service;

import com.board.backend.mapper.BoardMapper;
import com.board.backend.model.BoardDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        final BoardDto.Response response = boardMapper.selectById(id);
        if (response == null) {
            return response;
        }
        boardMapper.update(id, form);
        modelMapper.map(form, response);
        return response;
    }

    @Override
    public BoardDto.Response delete(long id) {
        final BoardDto.Response response = boardMapper.selectById(id);
        boardMapper.deleteById(id);
        return response;
    }
}
