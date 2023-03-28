package cho.boardplus.entity;

import cho.boardplus.dto.BoardDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name= "board")
public class BoardEntity  extends BaseEntity {

    @Id //pk 컬럼 지정. 필수
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;  //id 값

    @Column(length = 20, nullable = false)//크기 20, not null
    private String boardWriter;// 글 작성자

    @Column
    private String boardTitle; //제목

    @Column(length = 500)
    private String boardContents; //내용

    @Column
    private int boardHits;//조회수



  //entity 를 DTO 로 변환하는 작업

    public static BoardEntity toSaveEntity(BoardDTO boardDTO) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setBoardHits(0);
        return boardEntity;
    }
}
