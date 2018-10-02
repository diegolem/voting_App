package sv.edu.udb.www.Entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sv.edu.udb.www.Entities.Cities;
import sv.edu.udb.www.Entities.CitiesAdmins;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-02T17:56:42")
=======
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-02T15:59:00")
>>>>>>> f5ff2b56d07a64c76812838f4524f1393015b928
@StaticMetamodel(Departments.class)
public class Departments_ { 

    public static volatile CollectionAttribute<Departments, CitiesAdmins> citiesAdminsCollection;
    public static volatile SingularAttribute<Departments, String> name;
    public static volatile CollectionAttribute<Departments, Cities> citiesCollection;
    public static volatile SingularAttribute<Departments, Integer> id;

}