package sv.edu.udb.www.Entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sv.edu.udb.www.Entities.CandidatesForCities;
import sv.edu.udb.www.Entities.Citizens;
import sv.edu.udb.www.Entities.PoliticGroups;
import sv.edu.udb.www.Entities.PresidencialCandidates;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-09-22T16:31:24")
@StaticMetamodel(Candidates.class)
public class Candidates_ { 

    public static volatile CollectionAttribute<Candidates, PresidencialCandidates> presidencialCandidatesCollection;
    public static volatile SingularAttribute<Candidates, PoliticGroups> politicGroupId;
    public static volatile SingularAttribute<Candidates, String> photo;
    public static volatile SingularAttribute<Candidates, Integer> id;
    public static volatile SingularAttribute<Candidates, Citizens> citizenId;
    public static volatile CollectionAttribute<Candidates, CandidatesForCities> candidatesForCitiesCollection;

}