package com.example.abbs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.abbs.dao.BoardDao;
import com.example.abbs.entity.Board;

@Service
public class BoardServiceImpl implements BoardService{
	@Autowired private BoardDao bDao;
	
	@Override
	public Board getBoard(int bid) {
		return bDao.getBoard(bid);
	}

	@Override
	public int getBoardCount(String field, String query) {
		query = "%" + query + "%";
		return bDao.getBoardCount(field, query);
	}

	@Override
	public List<Board> getBoardList(int page, String field,String query) {
		int offset = (page - 1) * COUNT_PER_PAGE;
		query = "%" + query + "%";
		return bDao.getBoardList(field, query, COUNT_PER_PAGE, offset);
	}

	@Override
	public void insertBoard(Board board) {
		bDao.insertBoard(board);
		
	}

	@Override
	public void updateBoard(Board board) {
		bDao.updateBoard(board);
		
	}

	@Override
	public void deleteBoard(int bid) {
		bDao.deleteBoard(bid);
		
	}

	@Override
	public void increaseViewCount(int bid) {
		String field = "viewCount";
		bDao.increaseCount(field, bid);
		
	}

	@Override
	public void increaseReplyCount(int bid) {
		String field = "replyCount";
		bDao.increaseCount(field, bid);
		
	}

	@Override
	public void increaseLikeCount(int bid) {
		String field = "likeCount";
		bDao.increaseCount(field, bid);
		
	}

}
