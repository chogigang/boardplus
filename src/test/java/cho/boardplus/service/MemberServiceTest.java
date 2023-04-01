package cho.boardplus.service;

import cho.boardplus.dto.MemberFormDTO;
import cho.boardplus.entity.MemberEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    PasswordEncoder passwordEncoder;

    public MemberEntity createMember(){
        MemberFormDTO memberFormDto = new MemberFormDTO();

        memberFormDto.setEmail("test@email.com");
        memberFormDto.setName("홍길동");
        memberFormDto.setAddress("서울시 마포구 합정동");
        memberFormDto.setPassword("1234");
        return MemberEntity.createMember(memberFormDto, passwordEncoder);
    }


    @Test
    @DisplayName("회원가입 테스트")
    public void saveMemberTest(){

        MemberEntity memberEntity = createMember();
        MemberEntity savedMember = memberService.saveMember(memberEntity);

        assertEquals(memberEntity.getEmail(), savedMember.getEmail());
        assertEquals(memberEntity.getName(), savedMember.getName());
        assertEquals(memberEntity.getAddress(), savedMember.getAddress());
        assertEquals(memberEntity.getPassword(), savedMember.getPassword());
        assertEquals(memberEntity.getRole(), savedMember.getRole());
    }

    @Test
    @DisplayName("중복 회원 가입 테스트")  //추가
    public void saveDuplicateMemberTest(){
        MemberEntity member1 = createMember();
        MemberEntity member2 = createMember();
        memberService.saveMember(member1);

        Throwable e = assertThrows(IllegalStateException.class, () ->{
            memberService.saveMember(member2);});

        assertEquals("이미 가입된 회원입니다.",e.getMessage());
    }
}
