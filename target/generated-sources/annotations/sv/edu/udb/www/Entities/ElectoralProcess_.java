package sv.edu.udb.www.Entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sv.edu.udb.www.Entities.ElectoralProcessStatus;
import sv.edu.udb.www.Entities.ElectoralProcessTypes;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-09-15T09:44:28")
@StaticMetamodel(ElectoralProcess.class)
public class ElectoralProcess_ { 

    public static volatile SingularAttribute<ElectoralProcess, String> code;
    public static volatile SingularAttribute<ElectoralProcess, String> year;
    public static volatile SingularAttribute<ElectoralProcess, Date> endDate;
    public static volatile SingularAttribute<ElectoralProcess, Date> initDate;
    public static volatile SingularAttribute<ElectoralProcess, Date> processDate;
    public static volatile SingularAttribute<ElectoralProcess, ElectoralProcessStatus> electoralProcessStatusId;
    public static volatile SingularAttribute<ElectoralProcess, String> name;
    public static volatile SingularAttribute<ElectoralProcess, Integer> id;
    public static volatile SingularAttribute<ElectoralProcess, ElectoralProcessTypes> electoralProcessTypesId;

}