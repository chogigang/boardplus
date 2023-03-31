package cho.boardplus.entity;

import cho.boardplus.constant.Role;
import cho.boardplus.dto.MemberFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name ="member")
@Getter
@Setter
@ToString
public class MemberEntity {

    @Id
    @Column(name ="member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(unique = true)
    private String password;

    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;




}
