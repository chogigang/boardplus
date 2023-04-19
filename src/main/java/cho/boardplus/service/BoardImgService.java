package cho.boardplus.service;

import cho.boardplus.entity.BoardFileEntity;
import cho.boardplus.repository.BoardFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardImgService {

    @Value("{image_fileUploadPath}")
    private String image_fileUploadPath;
    private final BoardFileRepository boardFileRepository;
    private  final BoardFileService boardFileService;


    public void saveBoardImg(BoardFileEntity boardFileEntity )throws Exception{

            String oriImgName = boardFileEntity.getOriginalFileName();

    }

}
