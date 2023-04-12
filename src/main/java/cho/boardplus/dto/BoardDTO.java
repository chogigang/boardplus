package cho.boardplus.dto;

import cho.boardplus.entity.BoardEntity;
import cho.boardplus.entity.Member;
import lombok.*;

import java.time.LocalDateTime;

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



}
