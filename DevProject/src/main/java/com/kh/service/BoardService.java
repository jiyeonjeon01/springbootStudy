package com.kh.service;

import java.util.ArrayList;
import java.util.List;

import com.kh.domain.Board;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BoardService {
	private List<Board> boardList;

	public BoardService() {
		boardList = new ArrayList<>();
	}

	public void create(Board board) {
		boardList.add(board);
		log.info("create: {}", board);
	}

	public void readByBoardNo(Integer boardNo) {
		Board board = null;
		for (Board data : boardList) {
			if (data.getBoardNo() == boardNo) {
				board = data;
				break;
			}
		}
		log.info("readByBoardNo: {}", board);
	}

	public void delete(Board board) {
		boardList.remove(board);
		log.info("delete: {}", board);
	}
}
