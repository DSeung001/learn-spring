package hello.helleSpring.controller;

import hello.helleSpring.domain.Member;
import hello.helleSpring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


/*
* @Controller를 선언해두면 시작시 객체 생성해서 지니고 있음
*
* */
@Controller
public class MemberController {

    private MemberService memberService;

    // Authwired로 선언하면 MemberService를 여러번 만들 지 않고 한번만 선언해서 사용
    @Autowired
    public void setMemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    /* 만든 MemberForm form에 자동 매핑*/
    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return  "members/memberList";
    }
}
