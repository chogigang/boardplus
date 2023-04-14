package cho.boardplus.dto;

import cho.boardplus.entity.BoardEntity;
import cho.boardplus.entity.BoardFileEntity;
import cho.boardplus.entity.Member;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//DTO(Data Transfer Object) 데이터를 전송할때 쓰는 객체 , VO ,Bean         /Entity
@Getter
@Setter
@ToString//필드값 확인할때 사용
@NoArgsConstructor //기본 생성자
@AllArgsConstructor // 모든 필드를 매개변수로 하는 생성자.
public class BoardDTO {

    // CRUD 파트
    private Long id; //게시글 id

    private String boardTitle; // 게시글 이름

    private String boardWriter; //작성자
    private String boardContents; //게시글 내용
    private int boardHits; //조회수
    private LocalDateTime boardCreatedTime; //게시글 작성 시간
    private LocalDateTime boardUpdatedTime; // 게시글 수정시간
    private Member member;//회원



    //게시글 파일 첨부
    private List<MultipartFile>boardFile; //첨부파일 스프링 인터페이스. save.html - > Controller 파일을 담는 용도  , 보드 컨트롤러 만 적용
    private List<String> originalFileName; //원본 파일 이름     ,나머지는 보드 서비스에 적용
    private List<String> storedFileName; // 서버 저장용 파일 이름
    private int  fileAttached; //파일 첨부 여부(첨부 1 , 미첨부 0) 이거 안하면 나중에 귀찮게 많이 설정들을 해줘야함



    //DTO 를 엔티티로 변환
    public static BoardDTO toBoardDTO(BoardEntity boardEntity) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(boardEntity.getId());
        boardDTO.setBoardTitle(boardEntity.getBoardTitle());
        boardDTO.setMember(boardEntity.getMember());// 회원
        boardDTO.setBoardWriter(boardEntity.getBoardWriter()); //작성자
        boardDTO.setBoardContents(boardEntity.getBoardContents());
        boardDTO.setBoardHits(boardEntity.getBoardHits());
        boardDTO.setBoardCreatedTime(boardEntity.getCreatedTime());
        boardDTO.setBoardUpdatedTime(boardEntity.getUpdatedTime());

        if(boardEntity.getFileAttached()==0){//파일 없을때 조건
            boardDTO.setFileAttached(boardEntity.getFileAttached()); // 파일0
        }else{//파일이 있을때
            boardDTO.setFileAttached(boardEntity.getFileAttached()); //  파일 1
            List<String> originalFileNameList = new ArrayList<>();
            List<String> storedFileNameList = new ArrayList<>();


            for(BoardFileEntity boardFileEntity: boardEntity.getBoardFileEntityList()){
                originalFileNameList.add(boardFileEntity.getOriginalFileName());
                storedFileNameList.add(boardFileEntity.getStoredFileName());
        }
                boardDTO.setOriginalFileName(originalFileNameList);
                boardDTO.setStoredFileName(storedFileNameList);

        }

        return boardDTO;
        
    }

    //개시글 페이징용
    public BoardDTO(Long id, String boardWriter, String boardTitle, int boardHits, LocalDateTime boardCreatedTime) {
        this.id = id;
        this.boardWriter = boardWriter;
        this.boardTitle = boardTitle;
        this.boardHits = boardHits;
        this.boardCreatedTime = boardCreatedTime;


    }


}
