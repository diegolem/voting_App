package sv.edu.udb.www.Entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sv.edu.udb.www.Entities.Cities;
import sv.edu.udb.www.Entities.CitiesAdmins;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-06T20:53:43")
=======
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-07T19:47:42")
>>>>>>> 5f350ef6ea942948af5da831bea996ea78c81597
@StaticMetamodel(Departments.class)
public class Departments_ { 

    public static volatile CollectionAttribute<Departments, CitiesAdmins> citiesAdminsCollection;
    public static volatile SingularAttribute<Departments, String> name;
    public static volatile CollectionAttribute<Departments, Cities> citiesCollection;
    public static volatile SingularAttribute<Departments, Integer> id;

}