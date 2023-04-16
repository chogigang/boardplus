package cho.boardplus.service;

import cho.boardplus.repository.BoardRepository;
import cho.boardplus.dto.BoardDTO;
import cho.boardplus.entity.BoardEntity;
import cho.boardplus.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


//DTO -> Entity  (Entity Class)
//Entity -> DTO (DTO Class)

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    //작성
    public void save(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
        if(boardDTO.getBoardFile().isEmpty()) {
            boardRepository.save(boardEntity);
       }else {
            Long savedId = boardRepository.save(boardEntity).getId();
            BoardEntity board = boardRepository.findById(savedId).get();
            for(MultipartFile boardFile: boardDTO.getBoardFile()){

                String originalFilename = boardFile.getOriginalFilename();//  2
                String storedFileName =System.currentTimeMillis() +"_"+originalFilename; // 3

            }

            }
    }




    public List<BoardDTO> findAll() {
        List<BoardEntity> boardEntityList = boardRepository.findAll();
        List<BoardDTO> boardDTOList = new ArrayList<>();
        for (BoardEntity boardEntity : boardEntityList) {
            boardDTOList.add(BoardDTO.toBoardDTO(boardEntity));
        }
        return boardDTOList;

    }

    // 게시글 조회수
    @Transactional
    public void updateHits(Long id) {
        boardRepository.updateHits(id);
    }


    //게시글 상세조회
    @Transactional
    public BoardDTO findById(Long id) {
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);
        if (optionalBoardEntity.isPresent()) {
            BoardEntity boardEntity = optionalBoardEntity.get();
            BoardDTO boardDTO = BoardDTO.toBoardDTO(boardEntity);
            return boardDTO;
        } else {
            return null;
        }
    }

    //게시글 수정
    public BoardDTO update(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toUpdateEntity(boardDTO);
        boardRepository.save(boardEntity);
        return findById(boardDTO.getId());
    }

    //게시글 삭제
    public void delete(Long id) { //추가
        boardRepository.deleteById(id);

    }
    //페이징 처리
    public Page<BoardDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber() -1;
        int pageLinit = 8; // 한페이지 보여줄 글 갯수

        //한페이지당 n 개글씩 보여주고 정렬기준은 id 기준으로 내림차순  정렬
        Page<BoardEntity> boardEntities= //          몇페이지, 한페이지 여줄 글 갯수,어떻게 정렬해서 가져올거냐 전체를 여기 기준으로  해당페이지값을 가저온다
                boardRepository.findAll(PageRequest.of(page, pageLinit, Sort.by(Sort.Direction.DESC,"id")));// Sort.Direction 내림차순 id 는 엔티티 의 작성한 이름 기준



        System.out.println("boardEntities.getContent() = " + boardEntities.getContent()); // 요청 페이지에 해당하는 글
        System.out.println("boardEntities.getTotalElements() = " + boardEntities.getTotalElements()); // 전체 글갯수
        System.out.println("boardEntities.getNumber() = " + boardEntities.getNumber()); // DB로 요청한 페이지 번호
        System.out.println("boardEntities.getTotalPages() = " + boardEntities.getTotalPages()); // 전체 페이지 갯수
        System.out.println("boardEntities.getSize() = " + boardEntities.getSize()); // 한 페이지에 보여지는 글 갯수
        System.out.println("boardEntities.hasPrevious() = " + boardEntities.hasPrevious()); // 이전 페이지 존재 여부
        System.out.println("boardEntities.isFirst() = " + boardEntities.isFirst()); // 첫 페이지 여부
        System.out.println("boardEntities.isLast() = " + boardEntities.isLast()); // 마지막 페이지 여부


        //엔티티 객체를 옮겨서 DTO 로 변환하면서 위 객체들을 옮겨주는 것
        //목록 id, writer, title, hist,createdTime
        Page<BoardDTO> boardDTOS = boardEntities.map(board -> new BoardDTO(board.getId(), board.getBoardWriter(), board.getBoardTitle(), board.getBoardHits(),board.getCreatedTime()));
        return boardDTOS;
    }



}