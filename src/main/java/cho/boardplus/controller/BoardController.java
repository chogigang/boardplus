package cho.boardplus.controller;

import cho.boardplus.dto.BoardDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/board")
@Controller
public class BoardController {

    //메인페이지
    @GetMapping("/")
    public String index(){


        return "index";
    }
    
    //글 작성 페이지
@GetMapping("/write")
    public  String writeForm(){
    return "write";
}

@PostMapping("write")
    public String save(@ModelAttribute BoardDTO boardDTO){
        return null;

    }
}
