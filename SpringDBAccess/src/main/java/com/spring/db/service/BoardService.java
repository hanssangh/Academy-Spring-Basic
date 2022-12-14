package com.spring.db.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.spring.db.model.BoardVO;
import com.spring.db.repository.IBoardDAO;

@Service
public class BoardService implements IBoardService {
	
	@Autowired
	@Qualifier("boardDAO")
	private IBoardDAO dao;

	@Override
	public void insertArticle(BoardVO vo) {
		dao.insertArticle(vo);
	}

	@Override
	public List<BoardVO> getArticles() {
		return dao.getArticles();
	}

	@Override
	public BoardVO getArticle(int bId) {
		return dao.getArticle(bId);
	}

	@Override
	public void deleteArticle(int bId) {
		dao.deleteArticle(bId);
	}

	@Override
	public void updateArticle(BoardVO vo) {
		dao.updateArticle(vo);
	}
	
	@Override
	public List<BoardVO> searchList(String keyword) {
		return dao.searchList("%" + keyword + "%");
	}

}










