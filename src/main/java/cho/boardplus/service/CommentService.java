package cho.boardplus.service;

import cho.boardplus.dto.CommentDTO;
import cho.boardplus.entity.BoardEntity;
import cho.boardplus.entity.CommentEntity;
import cho.boardplus.repository.BoardRepository;
import cho.boardplus.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
        private final CommentRepository commentRepository;
        private final BoardRepository boardRepository;


        public Long save(CommentDTO commentDTO){
            Optional<BoardEntity> optionalBoardEntity= boardRepository.findById(commentDTO.getBoardId());
            if (optionalBoardEntity.isPresent()){
                BoardEntity boardEntity =optionalBoardEntity.get();
                CommentEntity commentEntity =CommentEntity.toSaveEntity(commentDTO,boardEntity);
                return commentRepository.save(commentEntity).getId();

            }else {
                return null;
            }

        }

public List<CommentDTO>findAll(Long boardId){
        BoardEntity boardEntity = boardRepository.findById(boardId).get();
        List<CommentEntity> commentEntityList = commentRepository.findAllByBoardEntityOrderByIdDesc(boardEntity);
        List<CommentDTO> commentDTOList= new ArrayList<>();
        for (CommentEntity commentEntity: commentEntityList){
            CommentDTO commentDTO = CommentDTO.toCommentDTO(commentEntity,boardId);
            commentDTOList.add(commentDTO);

        }
        return commentDTOList;

}




}
