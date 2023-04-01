package cho.boardplus.service;

import cho.boardplus.entity.MemberEntity;
import cho.boardplus.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberEntity saveMember(MemberEntity memberEntity){
        validateDuplicateMember(memberEntity);
        return memberRepository.save(memberEntity);

    }

    private void validateDuplicateMember(MemberEntity memberEntity) {
        MemberEntity findMember = memberRepository.findByEmail(memberEntity.getEmail());
        if (findMember != null){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

}
