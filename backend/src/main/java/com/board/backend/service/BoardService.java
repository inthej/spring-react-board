package com.board.backend.service;

import com.board.backend.mapper.BoardMapper;
import com.board.backend.model.BoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    @Autowired
    private BoardMapper boardMapper;

    public BoardDto.Response get(long id) {
        return boardMapper.findById(id);
    }
}
