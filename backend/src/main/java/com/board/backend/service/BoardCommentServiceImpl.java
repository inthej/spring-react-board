package com.board.backend.service;

import com.board.backend.common.SqlOrderBuilder;
import com.board.backend.exception.PasswordMismatchException;
import com.board.backend.mapper.BoardCommentMapper;
import com.board.backend.model.BoardCommentDto;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardCommentServiceImpl implements BoardCommentService {

    @Autowired
    private BoardCommentMapper boardCommentMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BoardCommentDto.ResponseList list(long bid, BoardCommentDto.RequestList form) {
        PageHelper.startPage(form.getPage(), form.getSize());

        final SqlOrderBuilder order = SqlOrderBuilder.createOrder(form.getOrder(), form.getDirection());
        PageHelper.orderBy(order.getSql());

        final List<BoardCommentDto.ListItem> list = boardCommentMapper.selectBoardComments(bid);
        final PageInfo<BoardCommentDto.ListItem> pageInfo = new PageInfo<>(list);
        return new BoardCommentDto.ResponseList(pageInfo.getTotal(), pageInfo.getPages(), list);
    }

    @Override
    public BoardCommentDto.Response get(long id) {
        final BoardCommentDto.Response response = boardCommentMapper.selectBoardCommentById(id);
        if (response == null) {
            return null;
        }
        return response;
    }

    @Override
    public BoardCommentDto.Response insert(long bid, BoardCommentDto.Create form) {
        boardCommentMapper.insertBoardComment(bid, form);
        return modelMapper.map(form, BoardCommentDto.Response.class);
    }

    @Override
    public BoardCommentDto.Response update(long id, BoardCommentDto.Update form) {
        final BoardCommentDto.Detail detail = boardCommentMapper.selectBoardCommentDetailById(id);
        if (detail == null) {
            return null;
        }

        if (!form.getPassword().equals(detail.getPassword())) {
            throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
        }

        boardCommentMapper.updateBoardCommentById(id, form);
        modelMapper.map(form, detail);
        return modelMapper.map(detail, BoardCommentDto.Response.class);
    }

    @Override
    public BoardCommentDto.Response delete(long id) {
        final BoardCommentDto.Response response = boardCommentMapper.selectBoardCommentById(id);
        boardCommentMapper.deleteBoardCommentById(id);
        return response;
    }

    @Override
    public void deleteAll(long bid) {
        boardCommentMapper.deleteAllByBoardId(bid);
    }
}
