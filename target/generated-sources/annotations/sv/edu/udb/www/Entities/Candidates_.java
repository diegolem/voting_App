package sv.edu.udb.www.Entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sv.edu.udb.www.Entities.CandidatesForCities;
import sv.edu.udb.www.Entities.Citizen;
import sv.edu.udb.www.Entities.PoliticGroups;
import sv.edu.udb.www.Entities.PresidencialCandidates;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-09-16T18:07:56")
@StaticMetamodel(Candidates.class)
public class Candidates_ { 

    public static volatile CollectionAttribute<Candidates, PresidencialCandidates> presidencialCandidatesCollection;
    public static volatile SingularAttribute<Candidates, PoliticGroups> politicGroupId;
    public static volatile SingularAttribute<Candidates, String> photo;
    public static volatile SingularAttribute<Candidates, Integer> id;
    public static volatile SingularAttribute<Candidates, Citizen> citizenId;
    public static volatile CollectionAttribute<Candidates, CandidatesForCities> candidatesForCitiesCollection;

}