package com.zeus.service;

import java.util.List;

import com.zeus.domain.Board;

public interface BoardService {

	void register(Board board) throws Exception;

	Board read(Integer boardNo) throws Exception;

	void modify(Board board) throws Exception;

	void remove(Integer boardNo) throws Exception;

	List<Board> list() throws Exception;

}