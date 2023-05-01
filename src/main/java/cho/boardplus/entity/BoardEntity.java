package cho.boardplus.entity;

import cho.boardplus.dto.BoardDTO;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name= "board")
public class BoardEntity  extends BaseEntity {

    @Id //pk 컬럼 지정. 필수
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;  //id 값


    @Column(nullable = false)
    private String boardTitle; //제목

    @Column(length = 20) // 크기 20
    private String boardWriter; //작성자
    @Column(length = 500)
    private String boardContents; //내용

    @Column
    private int boardHits;//조회수

    @Column
    private int fileAttached; //  파일 1 or 0

    @OneToMany(mappedBy = "boardEntity",cascade = CascadeType.REMOVE, orphanRemoval = true,fetch = FetchType.LAZY)
    private List<BoardFileEntity> boardFileEntityList =new ArrayList<>();



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; //  회원 추가



    //entity 를 DTO 로 변환하는 작업

    public static BoardEntity toSaveEntity(BoardDTO boardDTO) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setBoardHits(0);
       boardEntity.setFileAttached(1);//파일 없음
        return boardEntity;

    }

    //게시글 수정 엔티티 DTO 변환
    public static BoardEntity toUpdateEntity(BoardDTO boardDTO) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setId(boardDTO.getId());
        boardEntity.setBoardWriter(boardDTO.getBoardWriter()); //작성자
        boardEntity.setMember(boardDTO.getMember()); //멤버 추가
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setBoardHits(boardDTO.getBoardHits());
        return boardEntity;

    }


}



