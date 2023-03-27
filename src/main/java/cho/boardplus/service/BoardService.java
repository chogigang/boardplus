package cho.boardplus.service;

import cho.boardplus.boardrepository.BoardRepository;
import cho.boardplus.dto.BoardDTO;
import cho.boardplus.entity.BoardEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


//DTO -> Entity  (Entity Class)
//Entity -> DTO (DTO Class)

@Service
@RequiredArgsConstructor
public class BoardService {

    private BoardRepository boardRepository;

    //작성
    public void save(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
        boardRepository.save(boardEntity);

    }
}
