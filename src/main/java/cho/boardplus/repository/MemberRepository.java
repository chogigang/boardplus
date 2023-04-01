package cho.boardplus.repository;

import cho.boardplus.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity,Long> {

    MemberEntity findByEmail(String email);



}
