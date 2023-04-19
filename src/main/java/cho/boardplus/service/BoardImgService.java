package cho.boardplus.service;

import cho.boardplus.entity.BoardFileEntity;
import cho.boardplus.repository.BoardFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardImgService {

    @Value("${image_fileUploadPath}")
    private String image_fileUploadPath;
    private final BoardFileRepository boardFileRepository;
    private  final BoardFileService boardFileService;


    public void saveBoardImg(BoardFileEntity boardFileEntity,MultipartFile boardImgFile )throws Exception{

            String oriImgName = boardImgFile.getOriginalFilename();
            String imgName= "";
            String imgUrl = "";


            //이미지,파일  업로드
        if(!StringUtils.isEmpty(oriImgName)){
            imgName=boardFileService.uploadFile(image_fileUploadPath,oriImgName,
                    boardImgFile.getBytes());
            imgUrl= "/images/board/" +imgName;
        }
        // 정보 저장
        boardFileEntity.updateBoardFile(oriImgName,imgName,imgUrl);
        boardFileRepository.save(boardFileEntity);

    }

 }
