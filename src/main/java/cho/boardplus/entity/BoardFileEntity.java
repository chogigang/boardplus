package cho.boardplus.entity;

import cho.boardplus.constant.AttachmentType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="board_file_table")
public class BoardFileEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;  //파일번호


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="board_id")
    private BoardEntity boardEntity;   //게시판 번호

    @Column
    private String originalFileName;  //원본 파일 이름
    @Column
    private String storedFileName;  //변경된 파일 이름

    @Enumerated(EnumType.STRING)
    private AttachmentType attachmentType; // Enum 타입의 필드를 매핑


    public static BoardEntity toBoardFileEntity(BoardEntity boardEntity, String originalFileName, String storedFileName,AttachmentType attachmentType){
        BoardFileEntity boardFileEntity = new BoardFileEntity();
        boardFileEntity.setOriginalFileName(originalFileName);
        boardFileEntity.setStoredFileName(storedFileName);
        boardFileEntity.setAttachmentType(attachmentType.IMAGE); //파일 업로드 타입
        boardFileEntity.setBoardEntity(boardEntity);
        return boardEntity;

    }
}
