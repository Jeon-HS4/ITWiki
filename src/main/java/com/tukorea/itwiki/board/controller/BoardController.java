package com.tukorea.itwiki.board.controller;

import com.tukorea.itwiki.board.domain.Board;
import com.tukorea.itwiki.board.dto.BoardForm;
import com.tukorea.itwiki.board.dto.BoardModifyForm;
import com.tukorea.itwiki.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class BoardController {
	
	private final BoardService service;
	
	@Autowired
	public BoardController(BoardService service) {
		this.service = service;
	}
	

	@GetMapping("/board/form")
	public String addBoardForm() {
		return "board/addBoardForm";
	}
	

	@PostMapping("/board/new")
	public String addBoard(BoardForm boardForm) {
		// 게시판 등록 메서드 호출
		service.addBoard(boardForm);
		
		return "redirect:/board/list";
	}
	

	@GetMapping("/board/list")
	public String getBoardList(@RequestParam(required = false, defaultValue = "1") int pageNum, Model model) {
		// 게시판 목록 조회 메서드 호출
		Map<String, Object> result = service.getBoardList(pageNum);
		
		model.addAttribute("pageNum", pageNum);
		
		// Service 결과 모두 model에 설정
		model.addAllAttributes(result);
		
		return "board/boardList";
	}
	

	@GetMapping("/board/detail")
	public String getBoardDetail(@RequestParam int boardSeq, Model model) {
		// 게시판 상세정보 조회 메서드 호출
		Board board = service.getBoardDetail(boardSeq);
		model.addAttribute("board", board);
		
		return "board/boardDetail";
	}
	
	@ResponseBody
	@PostMapping("/board/checkPassword")
	public boolean checkPassword(@RequestParam int boardSeq, @RequestParam String password, Model model) {
		// 게시판 등록 메서드 호출
		boolean result = service.checkBoardOwner(boardSeq, password);
		
		return result;
	}
	
	@PostMapping("/board/modifyForm")
	public String getBoardModifyForm(@RequestParam int boardSeq, Model model) {
		// 게시판 상세정보 조회 메서드 호출
		Board board = service.getBoardDetailForModify(boardSeq);
		model.addAttribute("board", board);
		
		return "board/modifyBoardForm";
	}
	
	@PostMapping("/board/modify")
	public String modifyBoard(BoardModifyForm boardForm) {
		// 게시판 등록 메서드 호출
		service.updateBoard(boardForm);
		
		return "redirect:/board/list";
	}
	
	@PostMapping("/board/delete")
	public String deleteBoard(@RequestParam int boardSeq) {
		// 게시판 등록 메서드 호출
		service.deleteBoard(boardSeq);
		
		return "redirect:/board/list";
	}
}