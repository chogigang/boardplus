package cho.boardplus.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name= "board")
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 20, nullable = false)//크기 20, not null
    private String bardWriter;// 글 작성자

    @Column// 크기 255 ,null 가능
    private String bardPass;




}
