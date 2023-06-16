package com.tukorea.itwiki.board.service;

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
public class BoardService {
	
	private final BoardDao dao;
	
	@Autowired
	public BoardService(BoardDao dao) {
		this.dao = dao;
	}

	public void addBoard(BoardForm boardForm) {
		// 전역변수
		
		try {
			// 파라미터 검증
			if (!StringUtils.hasText(boardForm.getTitle())) {
				throw new Exception("제목 입력값이 없습니다.");
			}
			
			if (!StringUtils.hasText(boardForm.getWriter())) {
				throw new Exception("작성자 입력값이 없습니다.");
			}
			
			if (!StringUtils.hasText(boardForm.getPassword())) {
				throw new Exception("비밀번호 입력값이 없습니다.");
			}
			
			// 등록용 파라미터 정제(DTO -> Domain)
			Board board = new Board();
			board.setTitle(boardForm.getTitle());
			board.setUserId(boardForm.getWriter());
			board.setCategory(boardForm.getPassword());
			board.setContent(boardForm.getContents());
			
			// 게시판 등록
			int resultCount = dao.insertBoard(board);
			System.out.println("게시판 등록 완료 (건수 : " + resultCount + "건)");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// return;
	}


	public Map<String, Object> getBoardList(int pageNum) {
		// 전역변수
		Map<String, Object> result = new HashMap<String, Object>();

		try {
			// 목록 조회용 파라미터 설정
			int listNum = 7; // 게시판 페이지 별 건수 설정
			int startNum = (pageNum - 1) * listNum; // 게시판 목록 조회 시작점 설정
			
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("startNum", startNum);
			paramMap.put("listNum", listNum);
			
			// 게시물 총 건수 조회
			int totalCount = dao.selectBoardListTotalCount(paramMap);
			result.put("totalCount", totalCount);
			
			System.out.println("게시물 총 건수 조회 완료(" + totalCount + "건)");
			
			// 게시판 목록 조회
			List<Board> dbBoardList = dao.selectBoardList(paramMap);
			
			// 도메인에서 게시판 목록용 DTO로 전환
			List<BoardList> boardList = new ArrayList<BoardList>();
			int listStartNum = ((pageNum - 1) * listNum) + 1;
			
			for (Board board : dbBoardList) {
				BoardList listObj = new BoardList();
				
				listObj.setBoardNo(listStartNum++);// NO
				listObj.setPageId(board.getPageId()); // 게시물 시퀀스
				listObj.setTitle(board.getTitle()); // 제목
				listObj.setCategory(board.getCategory()); // 작성자
				listObj.setTag(board.getTag());
				listObj.setViewCount(board.getViewCount()); // 조회수
				listObj.setPageUpdate(board.getPageUpdate()); // 최종 수정
				
				boardList.add(listObj);
			}
			
			result.put("boardList", boardList);
			
			System.out.println("게시물 목록 조회 완료");
			
			// 게시판 페이징 생성
			// 1. 페이징 계산용 변수 설정
			int pageUnitNum = 5;
			
			// 2. 총 페이징 계산
			int totalPagingNum = (totalCount / listNum) + (totalCount % listNum == 0 ? 0 : 1);
			
			// 3. 배열 값 비교하여 페이징 시작 번호 return
			int totalPagingUnitNum = (totalPagingNum / pageUnitNum) + (totalPagingNum % pageUnitNum == 0 ? 0 : 1);
			for (int i=0; i<totalPagingUnitNum; i++) {
				// 단위 별 시작 페이지 번호와 종료 페이지 번호를 구한 뒤 비교하여 포함되는 페이징 그룹 return
				int startUnitNum = (i * pageUnitNum) + 1;
				int endUnitNum = (i + 1) * pageUnitNum;
				
				// 페이징 단위 종료 번호가 총 페이징 번호보다 클 경우 총 페이징 번호가 마지막이 됨
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
			
			System.out.println("게시판 페이징 설정 완료");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	

	public Board getBoardDetail(int boardSeq) {
		// 전역변수
		Board board = null;
		
		try {
			// 게시판 상세 정보 조회
			board = dao.selectBoardInfo(boardSeq);
			
			System.out.println("게시판 상세 조회 완료");
			
			// 해당 게시물 조회수 1 증가
			dao.updateBoardHits(boardSeq);
			
			System.out.println("게시물 조회수 증가 완료");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return board;
	}

	public boolean checkBoardOwner(int boardSeq, String password) {
		boolean result = false;
		
		try {
			// 게시물 패스워드 일치 여부 확인
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("boardSeq", boardSeq);
			paramMap.put("password", password);
			
			// 일치하는 게시물 있는지 확인
			int totalCount = dao.selectBoardPasswordForCheck(paramMap);
			System.out.println("게시물 총 건수 조회 완료(" + totalCount + "건)");
			
			if (totalCount > 0) {
				result = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public Board getBoardDetailForModify(int boardSeq) {
		// 전역변수
		Board board = null;
		
		try {
			// 게시판 상세 정보 조회
			board = dao.selectBoardInfo(boardSeq);
			
			System.out.println("게시판 상세 조회 완료");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return board;
	}

	public void updateBoard(BoardModifyForm boardForm) {
		try {
			// 수정용 파라미터 정제(DTO -> Domain)
			Board board = new Board();
			board.setBoardId(boardForm.getBoardSeq());
			board.setTitle(boardForm.getTitle());
			board.setUserId(boardForm.getWriter());
			board.setContent(boardForm.getContents());
			
			// 게시판 수정
			int resultCount = dao.updateBoard(board);
			System.out.println("게시판 수정 완료 (건수 : " + resultCount + "건)");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteBoard(int boardSeq) {
		try {
			// 게시물 삭제
			dao.deleteBoard(boardSeq);
			
			System.out.println("게시물 삭제 완료");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}