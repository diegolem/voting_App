package sv.edu.udb.www.Entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sv.edu.udb.www.Entities.ElectoralProcess;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-08T18:26:11")
@StaticMetamodel(ElectoralProcessStatus.class)
public class ElectoralProcessStatus_ { 

    public static volatile CollectionAttribute<ElectoralProcessStatus, ElectoralProcess> electoralProcessCollection;
    public static volatile SingularAttribute<ElectoralProcessStatus, String> description;
    public static volatile SingularAttribute<ElectoralProcessStatus, Integer> id;

}