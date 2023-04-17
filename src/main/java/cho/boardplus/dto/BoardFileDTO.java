package cho.boardplus.dto;

import cho.boardplus.entity.BoardFileEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.modelmapper.ModelMapper;

@Getter
@Setter
@ToString

public class BoardFileDTO {

    private  Long id;
    private String fileName;
    private String originalFileName;
    private String fileUrl;
    private int fileAttached;

private static ModelMapper modelMapper = new ModelMapper();
        public static BoardFileDTO of(BoardFileEntity boardFileEntity){
            return modelMapper.map(boardFileEntity,BoardFileDTO.class);
        }

}
