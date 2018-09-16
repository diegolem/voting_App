package sv.edu.udb.www.Entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sv.edu.udb.www.Entities.Citizen;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-09-16T12:19:05")
@StaticMetamodel(CitizenTypes.class)
public class CitizenTypes_ { 

    public static volatile SingularAttribute<CitizenTypes, String> description;
    public static volatile CollectionAttribute<CitizenTypes, Citizen> citizenCollection;
    public static volatile SingularAttribute<CitizenTypes, String> id;

}