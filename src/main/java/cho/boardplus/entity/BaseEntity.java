package cho.boardplus.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
//시간 정보를  다루는 엔티티
public abstract class BaseEntity  {


    @CreatedBy
    @Column(updatable = false)
    private String createdBy;

    @LastModifiedBy
    private String modifiedBy;


    @CreationTimestamp //생성했을때 시간 체크
    @Column(updatable = false) //수정시때는 발동하지 않는 옵션
    private LocalDateTime createdTime;

    @UpdateTimestamp //수정이 발생했을때 시간 체크
    @Column(insertable = false) //인설트를 할때는 관여를 안한다.
    private LocalDateTime updatedTime;


}
