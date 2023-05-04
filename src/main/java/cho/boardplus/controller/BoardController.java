package cho.boardplus.controller;

import cho.boardplus.dto.BoardDTO;
import cho.boardplus.entity.Member;
import cho.boardplus.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


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

    //글작성 페이지
    @GetMapping("/save")
    public String saveForm() {
        return "board/save";
    }


    // 글작성
    @PostMapping("/save")
    public String save(@ModelAttribute BoardDTO boardDTO) throws Exception {
        System.out.println("boardDTO = " + boardDTO);
        boardService.save(boardDTO);
        return "index";
    }


    // 게시글 목록
//    @GetMapping("/")
//    public String findAll(Model model) {
//        // DB에서 전체 게시글 데이터를 가져와서 list.html에 보여준다.
//        List<BoardDTO> boardDTOList = boardService.findAll();
//
//        model.addAttribute("boardList", boardDTOList);
//        return "list";
//    }
    //게시글 조회

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model,
                           @PageableDefault(page =1) Pageable pageable){
        boardService.updateHits(id);
        BoardDTO boardDTO =boardService.findById(id);
        model.addAttribute("board",boardDTO);
        model.addAttribute("page",pageable.getPageNumber());
        return "board/detail";
    }


    //게시글 수정
    @GetMapping(value = "/update/{id}")
    public String updateForm(@PathVariable Long id,Model model) {
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("boardUpdate",boardDTO);
        return "board/update";

    }
    //게시글 수정
    @PostMapping(value = "/update")//admin 추가
    public String update(@ModelAttribute BoardDTO boardDTO, Model model){
        BoardDTO board = boardService.update(boardDTO);
        model.addAttribute("board", board);
        return "board/detail";
        //  return "redirect:/board/"+ boardDTO.getId(); // 이것도 가능
    }
    //게시글 삭제
    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id){
        boardService.delete(id);
        return "redirect:/board/paging";
    }

    //게시글 페이징
    // /board/paging?page=1
    @GetMapping("/paging")
    public String paging(@PageableDefault(page = 1) Pageable pageable, Model model) {
        //pageable.getPageNumber();
        Page<BoardDTO> boardList = boardService.paging(pageable);
        List<BoardDTO> boardDTOList = boardService.findAll(); //추가
        int blockLimit = 3;
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1; // 1 4 7 10 ~~
        int endPage = ((startPage + blockLimit - 1) < boardList.getTotalPages()) ? startPage + blockLimit - 1 : boardList.getTotalPages();

//        page 갯수 20개
//         현재 사용자가 3페이지
//         1 2 3
//         현재 사용자가 7페이지
//         7 8 9
//         보여지는 페이지 갯수 는 3
        // 총 페이지 갯수 8개
        model.addAttribute("boardList", boardDTOList);
        model.addAttribute("boardList", boardList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "board/paging";
    }


}