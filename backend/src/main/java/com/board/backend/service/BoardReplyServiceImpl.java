package com.board.backend.service;

import com.board.backend.common.SqlOrderBuilder;
import com.board.backend.exception.PasswordMismatchException;
import com.board.backend.mapper.BoardReplyMapper;
import com.board.backend.model.BoardReplyDto;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardReplyServiceImpl implements BoardReplyService {

    @Autowired
    private BoardReplyMapper boardReplyMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BoardReplyDto.ResponseList list(long bid, BoardReplyDto.RequestList form) {
        PageHelper.startPage(form.getPage(), form.getSize());

        final SqlOrderBuilder order = SqlOrderBuilder.createOrder(form.getOrder(), form.getDirection());
        PageHelper.orderBy(order.getSql());

        final List<BoardReplyDto.ListItem> list = boardReplyMapper.selectBoardReplys(bid);
        final PageInfo<BoardReplyDto.ListItem> pageInfo = new PageInfo<>(list);
        return new BoardReplyDto.ResponseList(pageInfo.getTotal(), pageInfo.getPages(), list);
    }

    @Override
    public BoardReplyDto.Response get(long id) {
        final BoardReplyDto.Response response = boardReplyMapper.selectBoardReplyById(id);
        if (response == null) {
            return null;
        }
        return response;
    }

    @Override
    public BoardReplyDto.Response insert(long bid, BoardReplyDto.Create form) {
        boardReplyMapper.insertBoardReply(bid, form);
        return modelMapper.map(form, BoardReplyDto.Response.class);
    }

    @Override
    public BoardReplyDto.Response update(long id, BoardReplyDto.Update form) {
        final BoardReplyDto.Detail detail = boardReplyMapper.selectBoardReplyDetailById(id);
        if (detail == null) {
            return null;
        }

        if (!form.getPassword().equals(detail.getPassword())) {
            throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
        }

        boardReplyMapper.updateBoardReplyById(id, form);
        modelMapper.map(form, detail);
        return modelMapper.map(detail, BoardReplyDto.Response.class);
    }

    @Override
    public BoardReplyDto.Response delete(long id) {
        final BoardReplyDto.Response response = boardReplyMapper.selectBoardReplyById(id);
        boardReplyMapper.deleteBoardReplyById(id);
        return response;
    }
}
