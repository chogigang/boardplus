package cho.boardplus.dto;

import cho.boardplus.entity.BoardFileEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.modelmapper.ModelMapper;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@ToString

public class BoardFileDTO {

    private  Long id;
    private String fileName; //서버 저장용 파일 이름
    private String originalFileName; //원본 파일 이름    ,나머지는 보드 서비스에 적용
    private String fileUrl;

    /*해당 코드는 스프링에서 제공하는 MultipartFile 인터페이스를 사용하여 업로드된 파일 정보를 담는 리스트 변수입니다.
     save.html과 같은 뷰 페이지에서 파일을 선택하여 업로드하면, 해당 파일 정보를 컨트롤러로 전달하기 위해 사용됩니다.
      따라서 이 코드를 사용함으로써 업로드된 파일의 정보를 컨트롤러에서 쉽게 다룰 수 있습니다.*/

    private List<MultipartFile> boardFile; //첨부파일 스프링 인터페이스. save.html - > Controller 파일을 담는 용도  , 보드 컨트롤러 만 적용

    private int fileAttached; //파일 첨부 여부(첨부 1 , 미첨부 0) 이거 안하면 나중에 귀찮게 많이 설정들을 해줘야함

private static ModelMapper modelMapper = new ModelMapper();

        public static BoardFileDTO of(BoardFileEntity boardFileEntity){
            return modelMapper.map(boardFileEntity,BoardFileDTO.class);
        }

}
