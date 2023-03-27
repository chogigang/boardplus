package cho.boardplus.dto;

import lombok.*;

import java.time.LocalDateTime;

//DTO(Data Transfer Object) 데이터를 전송할때 쓰는 객체 , VO ,Bean         /Entity
@Getter
@Setter
@ToString//필드값 확인할때 사용
@NoArgsConstructor //기본 생성자
@AllArgsConstructor // 모든 필드를 매개변수로 하는 생성자.
public class BoardDTO {

    private Long id;
    private String boardWriter;
    private String boardTitle;
    private  String boardContents;
    private int boardHits;  //조회수
    private LocalDateTime boardCreatedTime;
    private LocalDateTime boardUpdateTime;

  

}
