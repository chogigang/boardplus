package cho.boardplus.dto;

import cho.boardplus.entity.BoardEntity;
import cho.boardplus.entity.BoardFileEntity;
import cho.boardplus.entity.Member;
import lombok.*;
import org.modelmapper.ModelMapper;
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

    private List<MultipartFile> attachments; // 첨부 파일 리스트 필드

    private List<BoardFileDTO> boardFileDTOList = new ArrayList<>();
    private List<Long> boardImgIds= new ArrayList<>(); //미지 파일의 ID를 저장하는 List item 엔티티로 메핑될 때 사용될것 게시판 등록,수정 같이 이미지를 선택적으로 추가할 수 있는 경우 사용

    private static ModelMapper modelMapper = new ModelMapper();

//    //게시글 파일 첨부
//    private List<MultipartFile>boardFile; //첨부파일 스프링 인터페이스. save.html - > Controller 파일을 담는 용도  , 보드 컨트롤러 만 적용
//    private List<String> originalFileName; //원본 파일 이름     ,나머지는 보드 서비스에 적용
//    private List<String> storedFileName; // 서버 저장용 파일 이름




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

        // 게시글 작성시 사용하는 model Mapper


    // DTO를 엔티티로 변환 하는 것
public BoardEntity createBoard(){
        return modelMapper.map(this,BoardEntity.class);
}
 // Entity 를 DTO 객체로 변환
public static BoardDTO of(BoardEntity boardEntity){
    return modelMapper.map(boardEntity,BoardDTO.class);

}



}
