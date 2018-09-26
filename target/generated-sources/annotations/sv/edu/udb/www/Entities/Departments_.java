package sv.edu.udb.www.Entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sv.edu.udb.www.Entities.Cities;
import sv.edu.udb.www.Entities.CitiesAdmins;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-09-25T19:24:15")
@StaticMetamodel(Departments.class)
public class Departments_ { 

    public static volatile CollectionAttribute<Departments, CitiesAdmins> citiesAdminsCollection;
    public static volatile SingularAttribute<Departments, String> name;
    public static volatile CollectionAttribute<Departments, Cities> citiesCollection;
    public static volatile SingularAttribute<Departments, Integer> id;

}