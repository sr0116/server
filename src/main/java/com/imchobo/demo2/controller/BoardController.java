package com.imchobo.demo2.controller;

import com.imchobo.demo2.domain.Board;
import com.imchobo.demo2.domain.Member;
import com.imchobo.demo2.domain.dto.Criteria;
import com.imchobo.demo2.domain.dto.PageDto;
import com.imchobo.demo2.service.BoardService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller// 컨트롤러 기능
@RequestMapping("board")// 보드 아래
@AllArgsConstructor// 생성자 생성
@Slf4j
public class BoardController {
  private BoardService boardService;

  //
  @GetMapping("list")
  public void list(Criteria criteria, Model model){
    model.addAttribute("boards", boardService.list(criteria));
    model.addAttribute("pageDto", new PageDto(criteria, boardService.getCount(criteria)));

  }

  @GetMapping({"view", "modify"})
  public void view(@ModelAttribute("cri") Criteria cri, Long bno,  Model model){
    model.addAttribute("board", boardService.findBy(bno));
//    model.addAttribute("cri", criteria); 생략 가능
  }

  @GetMapping("write")
  public String write(@ModelAttribute("cri")Criteria cri, @SessionAttribute(value = "member", required = false) Member member){
    if(member == null){
      return "redirect:/member/login";
    }
    return "board/write";
  }

  @PostMapping("write")
  public String write(Criteria cri, Board board, String encodeStr){
    log.info("board : {}", board);
    boardService.write(board);
    return "redirect:/board/list?" + cri.getQs2();
  }

  @PostMapping("modify")
  public String modify(Criteria cri, Board board, String encodeStr){
    log.info("board : {}", board);
    boardService.modify(board);
    return "redirect:/board/list?" + cri.getQs2();
  }

  @RequestMapping("remove")
  public String remove(Criteria cri,Long bno){
    boardService.remove(bno);
    return "redirect:/board/list?" + cri.getQs2();
  }



}




