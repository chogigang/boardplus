package cho.boardplus.dto;

import cho.boardplus.entity.CommentEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString

public class CommentDTO {
    private Long id;
    private String commentWriter;

    private  String commentContents;

    private  Long boardId;

    private LocalDateTime commentCreatedTime;


    public static CommentDTO toCommentDTO(CommentEntity commentEntity, Long boardId) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(commentEntity.getId());
        commentDTO.setCommentWriter(commentEntity.getCommentWriter());
        commentDTO.setCommentContents(commentEntity.getCommentContents());
        commentDTO.setCommentCreatedTime(commentEntity.getCreatedTime());
        //commentDTO.setBoardId(commentEntity.getBoardEntity().getId()); // Service 메소드에 @Transactional
        commentDTO.setBoardId(boardId);
        return commentDTO;
    }
}
