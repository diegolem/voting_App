package sv.edu.udb.www.Entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sv.edu.udb.www.Entities.Cities;
import sv.edu.udb.www.Entities.Citizen;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-09-15T09:44:28")
@StaticMetamodel(Headquarters.class)
public class Headquarters_ { 

    public static volatile SingularAttribute<Headquarters, String> name;
    public static volatile SingularAttribute<Headquarters, String> x;
    public static volatile SingularAttribute<Headquarters, String> y;
    public static volatile CollectionAttribute<Headquarters, Citizen> citizenCollection;
    public static volatile SingularAttribute<Headquarters, Integer> id;
    public static volatile SingularAttribute<Headquarters, Cities> cityId;

}