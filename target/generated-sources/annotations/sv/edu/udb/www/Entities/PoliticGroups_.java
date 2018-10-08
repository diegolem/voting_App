package sv.edu.udb.www.Entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sv.edu.udb.www.Entities.Candidates;
import sv.edu.udb.www.Entities.PoliticGroupVotes;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-06T20:53:43")
=======
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-07T19:47:42")
>>>>>>> 5f350ef6ea942948af5da831bea996ea78c81597
@StaticMetamodel(PoliticGroups.class)
public class PoliticGroups_ { 

    public static volatile CollectionAttribute<PoliticGroups, Candidates> candidatesCollection;
    public static volatile SingularAttribute<PoliticGroups, String> acronym;
    public static volatile SingularAttribute<PoliticGroups, String> name;
    public static volatile SingularAttribute<PoliticGroups, String> description;
    public static volatile SingularAttribute<PoliticGroups, String> photo;
    public static volatile CollectionAttribute<PoliticGroups, PoliticGroupVotes> politicGroupVotesCollection;
    public static volatile SingularAttribute<PoliticGroups, Integer> id;

}