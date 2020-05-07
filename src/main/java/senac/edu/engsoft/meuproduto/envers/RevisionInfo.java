package senac.edu.engsoft.meuproduto.envers;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "REVISION_INFO")
@Component
@RevisionEntity(RevisionInfoListener.class)
@Getter
@Setter
@EqualsAndHashCode
public class RevisionInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @RevisionNumber
    private Long id;

    @RevisionTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TIMESTAMP", length = 19, nullable = false)
    private Date timestamp;

}
