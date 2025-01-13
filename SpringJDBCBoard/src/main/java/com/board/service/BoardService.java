package com.board.service;

import java.util.ArrayList;
import java.util.List;

import com.board.domain.Board;

public interface BoardService {
	
	public void register(Board board) throws Exception; 
	
	public List<Board> list() throws Exception;

	public Board read(Board board) throws Exception;

	public boolean remove(Board board) throws Exception;

	public int modify(Board board) throws Exception; 
}