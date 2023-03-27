package cho.boardplus.service;

import cho.boardplus.repository.BoardRepository;
import cho.boardplus.dto.BoardDTO;
import cho.boardplus.entity.BoardEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


//DTO -> Entity  (Entity Class)
//Entity -> DTO (DTO Class)

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    //작성
    public void save(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO); //추가
        boardRepository.save(boardEntity); //추가

    }
}
