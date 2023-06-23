package com.tukorea.itwiki.admin.controller;

import com.tukorea.itwiki.admin.service.AdminService;
import com.tukorea.itwiki.board.domain.Board;
import com.tukorea.itwiki.board.dto.BoardModifyForm;
import com.tukorea.itwiki.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class AdminController {

	private final BoardService service;
	private final AdminService adservice;

	@Autowired
	public AdminController(BoardService service, AdminService adservice) {
		this.service = service;
		this.adservice = adservice;
	}

	@RequestMapping("/board/list/admin")
	public String getRevisionList(@RequestParam(required = false, defaultValue = "1") int pageNum,
							   @RequestParam(required = false, defaultValue = "category") String category,
							   @ModelAttribute("sorting") String sort,
							   @ModelAttribute("keyword") String keyword,
							   Model model) {
		// 카테고리, 키워드 및 정렬 상태를 확인하여 리스트 받아오기
		Map<String, Object> result = adservice.getRevisionList(pageNum, category, sort, keyword);
		// 카테고리를 가져오기 위한 메소드 호출
		List<String> categories = service.getAllCategory();

		model.addAttribute("pageNum", pageNum);
		model.addAttribute("categoryList", categories);

		model.addAllAttributes(result);

		return "admin/boardList";
	}
	

	@GetMapping("/board/detail/admin")
	public String getBoardDetail(@RequestParam int pageId, Model model) {
		Board board = service.getBoardDetail(pageId);
		model.addAttribute("board", board);
		
		return "admin/boardDetail";
	}

	@GetMapping("/board/modifyForm/admin")
	public String getBoardModifyForm1(@RequestParam("pageId") int pageId, Model model){
		Board board = service.getBoardDetailForModify(pageId);
		model.addAttribute("board", board);

		return "admin/modifyBoardForm";
	}
	@PostMapping("/board/modifyForm/admin")
	public String getBoardModifyForm(@RequestParam int pageId, Model model) {
		Board board = service.getBoardDetailForModify(pageId);
		model.addAttribute("board", board);
		return "admin/modifyBoardForm";
	}
	
	@PostMapping("/board/modify/admin")
	public String modifyBoard(BoardModifyForm boardForm) {
		service.updateBoard(boardForm);
		return "redirect:/board/list";
	}

	@GetMapping("/board/modify/admin")
	public String modifyRevision(BoardModifyForm boardForm) {
		service.updateBoard(boardForm);
		return "redirect:/board/list/admin";
	}

	@GetMapping("/board/delete/admin")
	public String deleteRevision(@RequestParam int revisionId) {
		adservice.deleteRevision(revisionId);
		return "redirect:/board/list/admin";
	}

}