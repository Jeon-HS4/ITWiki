package com.tukorea.itwiki.board.controller;

import com.tukorea.itwiki.board.domain.Board;
import com.tukorea.itwiki.board.dto.BoardForm;
import com.tukorea.itwiki.board.dto.BoardModifyForm;
import com.tukorea.itwiki.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class BoardController {
	
	private final BoardService service;
	
	@Autowired
	public BoardController(BoardService service) {
		this.service = service;
	}

	@GetMapping("/board/form")
	public String addBoardForm(Model model) {
		List<String> categories = service.getAllCategory();
		model.addAttribute("categoryList", categories);
		return "board/addBoardForm";
	}

	@PostMapping("/board/new")
	public String addBoard(BoardForm boardForm) {
		service.addBoard(boardForm);
		return "redirect:/board/list";
	}



	@RequestMapping("/board/list")
	public String getBoardList(@RequestParam(required = false, defaultValue = "1") int pageNum,
							   @RequestParam(required = false, defaultValue = "category") String category,
							   @ModelAttribute("sorting") String sort,
							   @ModelAttribute("keyword") String keyword,
							   Model model) {
		// 카테고리, 키워드 및 정렬 상태를 확인하여 리스트 받아오기
		Map<String, Object> result = service.getBoardList(pageNum, category, sort, keyword);
		// 카테고리를 가져오기 위한 메소드 호출
		List<String> categories = service.getAllCategory();

		model.addAttribute("pageNum", pageNum);
		model.addAttribute("categoryList", categories);

		model.addAllAttributes(result);

		return "board/boardList";
	}
	

	@GetMapping("/board/detail")
	public String getBoardDetail(@RequestParam int pageId, Model model) {
		Board board = service.getBoardDetail(pageId);
		model.addAttribute("board", board);
		
		return "board/boardDetail";
	}

	@GetMapping("/board/modifyForm")
	public String getBoardModifyForm1(@RequestParam("pageId") int pageId, Model model){
		Board board = service.getBoardDetailForModify(pageId);
		model.addAttribute("board", board);

		return "board/modifyBoardForm";
	}
	@PostMapping("/board/modifyForm")
	public String getBoardModifyForm(@RequestParam int pageId, Model model) {
		Board board = service.getBoardDetailForModify(pageId);
		model.addAttribute("board", board);
		return "board/modifyBoardForm";
	}
	
	@PostMapping("/board/modify")
	public String modifyBoard(BoardModifyForm boardForm) {
		service.updateBoard(boardForm);
		return "redirect:/board/list";
	}

	@GetMapping("/board/delete")
	public String deleteBoard(@RequestParam int pageId) {
		service.deleteBoard(pageId);
		return "redirect:/board/list";
	}

	@GetMapping("/board/vote")
	public String voteBoard(@RequestParam int pageId, String userId) {
		service.voteBoard(pageId, userId);
		return "redirect:/board/list";
	}

/*
	@PostMapping("/board/newRev")
	public String addNewRevision(BoardForm boardForm) {
		service.addRevision(boardForm);

		return "redirect:/board/list";
	}
*/


}