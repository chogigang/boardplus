package cho.boardplus.service;

import cho.boardplus.boardrepository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private BoardRepository boardRepository;

}
