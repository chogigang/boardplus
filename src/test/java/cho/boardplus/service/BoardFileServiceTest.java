package cho.boardplus.service;

import cho.boardplus.dto.BoardDTO;
import cho.boardplus.repository.BoardFileRepository;
import cho.boardplus.repository.BoardRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
public class BoardFileServiceTest {

    @Autowired
    BoardService boardService;

    BoardFileService boardFileService;

    @Autowired
    BoardRepository boardRepository;



    @Autowired
    BoardFileRepository boardFileRepository;
    List<MultipartFile> createMultipartFiles() throws Exception{
        List<MultipartFile> multipartFileList = new ArrayList<>();
        for(int i=0;i<5;i++){
            String path = "C:/board/item"; //경로 설정 확실히 잘합시다
            String imageName = "image" + i + ".jpg";
            MockMultipartFile multipartFile =
                    new MockMultipartFile(path, imageName, "image/jpg", new byte[]{1,2,3,4});
            multipartFileList.add(multipartFile);
        }
        return multipartFileList;
    }
    @Test
    @DisplayName("게시글 파일 등록 테스트")
    @WithMockUser(username = "admin",roles = "ADMIN")
    void saveBoardImg()throws Exception{
        BoardDTO boardDTO =new BoardDTO();
        boardDTO.setBoardTitle("파일 업로드테스트");
        boardDTO.setBoardContents("아무내용");
        boardDTO.setBoardWriter("ADMIN");





}
}
