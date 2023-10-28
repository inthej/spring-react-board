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
    public BoardCommentDto.ResponseList list(long bno, BoardCommentDto.RequestList form) {
        PageHelper.startPage(form.getPage(), form.getSize());

        final SqlOrderBuilder order = SqlOrderBuilder.create(form.getOrder(), form.getDirection());
        PageHelper.orderBy(order.getSql());

        final List<BoardCommentDto.ListItem> list = boardCommentMapper.selectBoardComments(bno);
        final PageInfo<BoardCommentDto.ListItem> pageInfo = new PageInfo<>(list);
        return new BoardCommentDto.ResponseList(pageInfo.getTotal(), pageInfo.getPages(), list);
    }

    @Override
    public BoardCommentDto.Response get(long no) {
        return boardCommentMapper.selectBoardCommentById(no);
    }

    @Override
    public BoardCommentDto.Response insert(long bno, BoardCommentDto.Create form) {
        boardCommentMapper.insertBoardComment(bno, form);
        return modelMapper.map(form, BoardCommentDto.Response.class);
    }

    @Override
    public BoardCommentDto.Response update(long no, BoardCommentDto.Update form) {
        final BoardCommentDto.Detail detail = boardCommentMapper.selectBoardCommentDetailById(no);
        if (detail == null) {
            return null;
        }

        if (!form.getPassword().equals(detail.getPassword())) {
            throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
        }

        boardCommentMapper.updateBoardCommentById(no, form);
        modelMapper.map(form, detail);
        return modelMapper.map(detail, BoardCommentDto.Response.class);
    }

    @Override
    public BoardCommentDto.Response delete(long no) {
        final BoardCommentDto.Response response = boardCommentMapper.selectBoardCommentById(no);
        boardCommentMapper.deleteBoardCommentById(no);
        return response;
    }

    @Override
    public void deleteAll(long bno) {
        boardCommentMapper.deleteAllByBoardId(bno);
    }
}
