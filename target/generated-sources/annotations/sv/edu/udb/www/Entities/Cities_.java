package sv.edu.udb.www.Entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sv.edu.udb.www.Entities.CandidatesForCities;
import sv.edu.udb.www.Entities.Departments;
import sv.edu.udb.www.Entities.Headquarters;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-05T23:33:56")
@StaticMetamodel(Cities.class)
public class Cities_ { 

    public static volatile SingularAttribute<Cities, Departments> deparmentId;
    public static volatile SingularAttribute<Cities, String> name;
    public static volatile CollectionAttribute<Cities, Headquarters> headquartersCollection;
    public static volatile SingularAttribute<Cities, Integer> id;
    public static volatile CollectionAttribute<Cities, CandidatesForCities> candidatesForCitiesCollection;

}