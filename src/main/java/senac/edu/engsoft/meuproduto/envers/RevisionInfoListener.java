package senac.edu.engsoft.meuproduto.envers;

import org.hibernate.envers.EntityTrackingRevisionListener;
import org.hibernate.envers.RevisionType;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class RevisionInfoListener implements EntityTrackingRevisionListener {

    @Override
    public void entityChanged(Class aClass, String s, Serializable serializable, RevisionType revisionType, Object o) {
        //logstash auditoria
    }

    @Override
    public void newRevision(Object revisionEntity) {
        RevisionInfo revisionInfo = (RevisionInfo) revisionEntity;
        //adicionar informaçoes adicionais
    }
}
