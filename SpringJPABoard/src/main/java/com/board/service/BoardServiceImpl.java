package com.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.board.domain.Board;
import com.board.repository.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardRepository repository;

	
	@Override
	@Transactional
	public void register(Board board) throws Exception {
		 repository.save(board); 
	}

	@Override
	@Transactional(readOnly = true) 
	public Board read(Long boardNo) throws Exception {
		return  repository.getOne(boardNo);
	}

	@Override
	@Transactional
	public void modify(Board board) throws Exception {
		//데이타베이스에서 Board 객체를 가져온다.
		Board  boardEntity = repository.getOne(board.getBoardNo());
		boardEntity.setTitle(board.getTitle());
		boardEntity.setContent(board.getContent());
	}

	@Override
	@Transactional
	public void remove(Long boardNo) throws Exception {
		repository.deleteById(boardNo);

	}

	@Override
	public List<Board> list() throws Exception {
		// TODO Auto-generated method stub
		return repository.findAll(Sort.by(Direction.DESC, "boardNo"));
	}

}