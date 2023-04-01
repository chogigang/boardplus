package cho.boardplus.controller;

import cho.boardplus.dto.MemberFormDTO;
import cho.boardplus.entity.MemberEntity;
import cho.boardplus.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("/members")
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    @GetMapping(value = "/new")
    public String memberForm(Model model){
        model.addAttribute("memberFormDto",new MemberFormDTO());
        return "member/memberForm"; //뷰단으로 리턴
    }

    @PostMapping(value = "/new") //추가
    public String newMember(@Valid MemberFormDTO memberFormDTO, BindingResult bindingResult , Model model){
        if (bindingResult.hasErrors()){
            return "member/memberForm";
        }
        try{
            MemberEntity memberEntity = MemberEntity.createMember(memberFormDTO,passwordEncoder);
            memberService.saveMember(memberEntity);
        }catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "member/memberForm";
        }
        return "redirect:/";
    }

}
