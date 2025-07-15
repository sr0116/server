package com.imchobo.demo2.controller;

import com.imchobo.demo2.domain.Member;
import com.imchobo.demo2.domain.Reply;
import com.imchobo.demo2.service.MemberService;
import com.imchobo.demo2.service.ReplyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("reply")
@AllArgsConstructor
public class ReplyController {

  @GetMapping("test1")
  public String test1() {
    return "hello World";

  }
  @GetMapping("test2")
  public Member test2() {
    return Member.builder().build();
  }

  private ReplyService replyService;

  @GetMapping({"list/{bno}", "list/{bno}/{lastRno}"})
  public List<Reply> list(@PathVariable Long bno, @PathVariable(required = false) Long lastRno) {
    return replyService.list(bno, lastRno);
  }

  @PostMapping("/")
  public Map<String, Object> write (@RequestBody Reply reply) {
    replyService.register(reply);
    return Map.of("result", true, "reply", reply);
  }
@PutMapping("/")
public Map<String, Object>  modify(@RequestBody  Reply reply) {
  replyService.modify(reply);
  return Map.of("result", true, "reply", reply);
}
  @PutMapping("/{rno}")
  public ResponseEntity< Map<String, Object>> remove(@PathVariable Long rno) {
    replyService.remove(rno);
    return ResponseEntity.ok().body(Map.of("result",true));
  }






}




