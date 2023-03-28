package cho.boardplus.controller;

import cho.boardplus.dto.BoardDTO;
import cho.boardplus.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/board")
@Controller
public class BoardController {

    private final BoardService boardService;


    //메인페이지
    @GetMapping("/index")
    public String index() {

        return "index";
    }

    //글 작성 페이지
    @GetMapping("/save")
    public String writeForm() {
        return "save";
    }


    // 글작성 컨트롤러
    @PostMapping("save")
    public String save(@ModelAttribute BoardDTO boardDTO) {
        System.out.println("boardDTO = " + boardDTO);
        boardService.save(boardDTO);
        return "index"; //index 로 변경

    }


    // 게시글 목록
    @GetMapping("/")
    public String findAll(Model model) {
        // DB에서 전체 게시글 데이터를 가져와서 list.html에 보여준다.
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList", boardDTOList);
        return "list";
    }
//게시글 조회
        @GetMapping("/{id}")
        public String findById(@PathVariable Long id, Model model){
            boardService.updateHits(id);
            BoardDTO boardDTO =boardService.findById(id);
            model.addAttribute("board",boardDTO);
            return "detail";
        }



}
