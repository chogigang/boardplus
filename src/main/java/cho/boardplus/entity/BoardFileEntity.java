package cho.boardplus.entity;

import cho.boardplus.constant.AttachmentType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="board_file_table")
public class BoardFileEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;  //파일번호

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private BoardEntity boardEntity;   //게시판 번호

    @Column(nullable = false)
    private String originalFileName;  //원본 파일 이름
    @Column(nullable = false)
    private String storedFileName;  //서버에 변경된 파일 이름

    private String imgUrl;// 이미지 조회 경로

    @Enumerated(EnumType.STRING)
    private AttachmentType attachmentType; // Enum 타입의 필드를 매핑 파일인지,이미지인지 구분하기 위한 맵핑


//    public static BoardFileEntity toBoardFileEntity(BoardEntity boardEntity, String originalFileName, String storedFileName) {
//        BoardFileEntity boardFileEntity = new BoardFileEntity();
//        boardFileEntity.setOriginalFileName(originalFileName);
//        boardFileEntity.setStoredFileName(storedFileName);
//        boardFileEntity.setBoardEntity(boardEntity);
//        return boardFileEntity;
//
//    }


    public void BoardFileUpload(String originalFileName, String storedFileName, String imgUrl) {
        this.originalFileName = originalFileName;
        this.storedFileName = storedFileName;
        this.imgUrl = imgUrl;

    }

}
