package sv.edu.udb.www.Entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sv.edu.udb.www.Entities.CandidatesForCities;
import sv.edu.udb.www.Entities.Citizens;
import sv.edu.udb.www.Entities.PoliticGroups;
import sv.edu.udb.www.Entities.PresidencialCandidates;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-06T20:53:43")
=======
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-07T19:47:42")
>>>>>>> 5f350ef6ea942948af5da831bea996ea78c81597
@StaticMetamodel(Candidates.class)
public class Candidates_ { 

    public static volatile CollectionAttribute<Candidates, PresidencialCandidates> presidencialCandidatesCollection;
    public static volatile SingularAttribute<Candidates, PoliticGroups> politicGroupId;
    public static volatile SingularAttribute<Candidates, String> photo;
    public static volatile SingularAttribute<Candidates, Integer> id;
    public static volatile SingularAttribute<Candidates, Citizens> citizenId;
    public static volatile CollectionAttribute<Candidates, CandidatesForCities> candidatesForCitiesCollection;

}