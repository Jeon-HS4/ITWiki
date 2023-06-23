package com.tukorea.itwiki.board.dao;

import com.tukorea.itwiki.admin.domain.Revision;
import com.tukorea.itwiki.board.domain.Board;
import com.tukorea.itwiki.board.dto.RevisionForm;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface BoardDao {
	
	// 게시판 글 등록
	public int insertBoard(Board board);
	
	// 게시판 총 건수 조회
	public int selectBoardListTotalCount(Map<String, Object> paramMap);
	
	// 게시판 목록 조회
	public List<Board> selectBoardList(Map<String, Object> paramMap);
	
	// 게시판 상세 정보 조회
	public Board selectBoardInfo(int pageId);
	
	// 게시물 조회수 증가
	public int updateBoardViewCount(int pageId);
	// 게시물 추천
	public int updateBoardVote(int pageId, String userId);
	
	// 게시물 글 수정
	public int updateBoard(Board board);
	
	// 게시물 글 삭제
	public int deleteBoard(int pageId);

	public int insertRevision(RevisionForm revisionForm);

	public List<String> getAllCategory();

	public int selectRevisionListTotalCount(Map<String, Object> paramMap);

	public List<Revision> selectRevisionList(Map<String, Object> paramMap);

	public void deleteRevision(int revisionId);
}
