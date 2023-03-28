package cho.boardplus.controller;

import cho.boardplus.dto.BoardDTO;
import cho.boardplus.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
@GetMapping("/index")
    public String finAll(Model model) {
        //DB 에서 전체 게시글 데이터를 가져와서 Index.html에 보여준다.
    List<BoardDTO> boardDTOList = boardService.findAll();
    model.addAttribute("boardList",boardDTOList);
    return "index";

    }
}
