package com.board.backend.service;

import com.board.backend.common.SqlOrderBuilder;
import com.board.backend.mapper.BoardMapper;
import com.board.backend.model.BoardDto;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardMapper boardMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BoardDto.ResponseList list(BoardDto.RequestList form) {
        PageHelper.startPage(form.getPage(), form.getSize());
        final SqlOrderBuilder order = SqlOrderBuilder.createOrder(form.getColumn(), form.getDirection());
        PageHelper.orderBy(order.getSql());
        final List<BoardDto.Response> list = boardMapper.selectBoardsWithKeyword(form.getKeyword());
        final PageInfo<BoardDto.Response> pageInfo = new PageInfo<>(list);
        return new BoardDto.ResponseList(pageInfo.getTotal(), pageInfo.getPages(), list);
    }

    @Override
    public BoardDto.Response get(long id) {
        return boardMapper.selectBoardById(id);
    }

    @Override
    public BoardDto.Response insert(BoardDto.Create form) {
        boardMapper.insertBoard(form);
        return modelMapper.map(form, BoardDto.Response.class);
    }

    @Override
    public BoardDto.Response update(long id, BoardDto.Update form) {
        final BoardDto.Response response = boardMapper.selectBoardById(id);
        if (response == null) {
            return response;
        }
        boardMapper.updateBoardById(id, form);
        modelMapper.map(form, response);
        return response;
    }

    @Override
    public BoardDto.Response delete(long id) {
        final BoardDto.Response response = boardMapper.selectBoardById(id);
        boardMapper.deleteBoardById(id);
        return response;
    }
}
