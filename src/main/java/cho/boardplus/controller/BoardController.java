package cho.boardplus.controller;

import cho.boardplus.dto.BoardDTO;
import cho.boardplus.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/board")
@Controller
public class BoardController {

    private  final BoardService boardService;


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

// 글작성 컨트롤러
@PostMapping("write")
    public String save(@ModelAttribute BoardDTO boardDTO){
    System.out.println("boardDTO = "+boardDTO);
    boardService.save(boardDTO);
        return "index"; // <index 로 변경

    }
}
