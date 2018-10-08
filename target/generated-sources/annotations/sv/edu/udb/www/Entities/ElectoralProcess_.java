package sv.edu.udb.www.Entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sv.edu.udb.www.Entities.CandidatesForCities;
import sv.edu.udb.www.Entities.CitizenVotes;
import sv.edu.udb.www.Entities.ElectoralProcessStatus;
import sv.edu.udb.www.Entities.ElectoralProcessTypes;
import sv.edu.udb.www.Entities.Jrv;
import sv.edu.udb.www.Entities.PoliticGroupVotes;
import sv.edu.udb.www.Entities.PresidencialCandidates;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-06T20:53:43")
=======
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-07T19:47:42")
>>>>>>> 5f350ef6ea942948af5da831bea996ea78c81597
@StaticMetamodel(ElectoralProcess.class)
public class ElectoralProcess_ { 

    public static volatile CollectionAttribute<ElectoralProcess, PresidencialCandidates> presidencialCandidatesCollection;
    public static volatile CollectionAttribute<ElectoralProcess, Jrv> jrvCollection;
    public static volatile SingularAttribute<ElectoralProcess, String> code;
    public static volatile CollectionAttribute<ElectoralProcess, CitizenVotes> citizenVotesCollection;
    public static volatile SingularAttribute<ElectoralProcess, String> year;
    public static volatile SingularAttribute<ElectoralProcess, Date> endDate;
    public static volatile SingularAttribute<ElectoralProcess, Date> initDate;
    public static volatile CollectionAttribute<ElectoralProcess, CandidatesForCities> candidatesForCitiesCollection;
    public static volatile SingularAttribute<ElectoralProcess, Date> processDate;
    public static volatile SingularAttribute<ElectoralProcess, ElectoralProcessStatus> electoralProcessStatusId;
    public static volatile SingularAttribute<ElectoralProcess, String> name;
    public static volatile CollectionAttribute<ElectoralProcess, PoliticGroupVotes> politicGroupVotesCollection;
    public static volatile SingularAttribute<ElectoralProcess, Integer> id;
    public static volatile SingularAttribute<ElectoralProcess, ElectoralProcessTypes> electoralProcessTypesId;

}