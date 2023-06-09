package com.tukorea.itwiki.admin.service;

import com.tukorea.itwiki.admin.domain.Revision;
import com.tukorea.itwiki.board.dao.BoardDao;
import com.tukorea.itwiki.board.domain.Board;
import com.tukorea.itwiki.board.dto.BoardForm;
import com.tukorea.itwiki.board.dto.BoardList;
import com.tukorea.itwiki.board.dto.BoardModifyForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminService {

	private final BoardDao dao;

	@Autowired
	public AdminService(BoardDao dao) {
		this.dao = dao;
	}

	public Map<String, Object> getBoardList(int pageNum, String category, String sort, String keyword) {
		Map<String, Object> result = new HashMap<String, Object>();

		try {
			int listNum = 7; // 페이지 당 표시할 목록
			int startNum = (pageNum - 1) * listNum;
			String categorySQL;
			String sortSQL;
			String keywordSQL;
			
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("startNum", startNum);
			paramMap.put("listNum", listNum);

			// 검색 조건에 따른 쿼리 작업
			if(category.equals("category")){
				categorySQL = "1";
			}else{
				categorySQL = "category = '" + category + "'";
			}
			if(!keyword.equals("")){
				keywordSQL = keyword;
			}else{
				keywordSQL = "";
			}
			if(sort == null || sort.isEmpty()){
				sort = "pageId";
			}
			sortSQL = sort;

			paramMap.put("category", categorySQL);
			paramMap.put("keyword", keywordSQL);
			paramMap.put("sort", sortSQL);
			
			// 게시물 총 건수 조회
			int totalCount = dao.selectBoardListTotalCount(paramMap);
			result.put("totalCount", totalCount);
			
			// 게시판 목록 조회
			List<Board> dbBoardList = dao.selectBoardList(paramMap);
			
			// 도메인에서 게시판 목록용 DTO로 전환
			List<BoardList> boardList = new ArrayList<BoardList>();
			int listStartNum = ((pageNum - 1) * listNum) + 1;
			
			for (Board board : dbBoardList) {
				BoardList listObj = new BoardList();
				
				listObj.setBoardNo(listStartNum++);
				listObj.setPageId(board.getPageId());
				listObj.setTitle(board.getTitle());
				listObj.setCategory(board.getCategory());
				listObj.setTag(board.getTag());
				listObj.setViewCount(board.getViewCount());
				listObj.setPageUpdate(board.getPageUpdate());
				
				boardList.add(listObj);
			}
			
			result.put("boardList", boardList);
			
			// 게시판 페이징 생성
			int pageUnitNum = 5;
			int totalPagingNum = (totalCount / listNum) + (totalCount % listNum == 0 ? 0 : 1);
			
			int totalPagingUnitNum = (totalPagingNum / pageUnitNum) + (totalPagingNum % pageUnitNum == 0 ? 0 : 1);
			for (int i=0; i<totalPagingUnitNum; i++) {
				int startUnitNum = (i * pageUnitNum) + 1;
				int endUnitNum = (i + 1) * pageUnitNum;
				
				if (endUnitNum > totalPagingNum) {
					endUnitNum = totalPagingNum;
				}
				
				if (pageNum >= startUnitNum && pageNum <= endUnitNum) {
					result.put("startUnitNum", startUnitNum);
					result.put("endUnitNum", endUnitNum);
					result.put("totalPagingNum", totalPagingNum);
					break;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public Map<String, Object> getRevisionList(int pageNum, String category, String sort, String keyword) {
		Map<String, Object> result = new HashMap<String, Object>();

		try {
			int listNum = 7; // 페이지 당 표시할 목록
			int startNum = (pageNum - 1) * listNum;
			String categorySQL;
			String sortSQL;
			String keywordSQL;

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("startNum", startNum);
			paramMap.put("listNum", listNum);

			// 검색 조건에 따른 쿼리 작업
			if(category.equals("category")){
				categorySQL = "1";
			}else{
				categorySQL = "category = '" + category + "'";
			}
			if(!keyword.equals("")){
				keywordSQL = keyword;
			}else{
				keywordSQL = "";
			}
			if(sort == null || sort.isEmpty()){
				sort = "pageId";
			}
			sortSQL = sort;

			paramMap.put("category", categorySQL);
			paramMap.put("keyword", keywordSQL);
			paramMap.put("sort", sortSQL);

			// 게시물 총 건수 조회
			int totalCount = dao.selectRevisionListTotalCount(paramMap);
			result.put("totalCount", totalCount);

			// 게시판 목록 조회
			List<Revision> dbBoardList = dao.selectRevisionList(paramMap);

			result.put("boardList", dbBoardList);

			// 게시판 페이징 생성
			int pageUnitNum = 5;
			int totalPagingNum = (totalCount / listNum) + (totalCount % listNum == 0 ? 0 : 1);

			int totalPagingUnitNum = (totalPagingNum / pageUnitNum) + (totalPagingNum % pageUnitNum == 0 ? 0 : 1);
			for (int i=0; i<totalPagingUnitNum; i++) {
				int startUnitNum = (i * pageUnitNum) + 1;
				int endUnitNum = (i + 1) * pageUnitNum;

				if (endUnitNum > totalPagingNum) {
					endUnitNum = totalPagingNum;
				}

				if (pageNum >= startUnitNum && pageNum <= endUnitNum) {
					result.put("startUnitNum", startUnitNum);
					result.put("endUnitNum", endUnitNum);
					result.put("totalPagingNum", totalPagingNum);
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public Board getBoardDetail(int pageId) {
		Board board = null;
		
		try {
			// 상세 정보 조회 메소드
			board = dao.selectBoardInfo(pageId);

			// 조회수 증가 메소드
			dao.updateBoardViewCount(pageId);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return board;
	}

	public Board getBoardDetailForModify(int pageId) {
		Board board = null;
		
		try {
			// 게시판 정보 조회
			board = dao.selectBoardInfo(pageId);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return board;
	}

	public void updateBoard(BoardModifyForm boardForm) {
		try {
			// 수정용 파라미터 정제(DTO -> Domain)
			Board board = new Board();
			board.setPageId(boardForm.getPageId());
			board.setTitle(boardForm.getTitle());
			board.setCategory(boardForm.getCategory());
			board.setTag(boardForm.getTag());
			board.setContent(boardForm.getContent());

			int resultCount = dao.updateBoard(board);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteBoard(int pageId) {
		try {
			// 게시물 삭제
			dao.deleteBoard(pageId);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<String> getAllCategory() {
		// 카테고리 선택을 위한 모든 목록 가져오기
		List<String> categories = dao.getAllCategory();
		return categories;
	}

	public void deleteRevision(int revisionId) {
		try {
			dao.deleteRevision(revisionId);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}