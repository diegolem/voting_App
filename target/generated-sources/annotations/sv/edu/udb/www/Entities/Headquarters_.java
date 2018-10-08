package sv.edu.udb.www.Entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sv.edu.udb.www.Entities.Cities;
import sv.edu.udb.www.Entities.Citizens;
import sv.edu.udb.www.Entities.Jrv;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-06T20:53:43")
=======
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-07T19:47:42")
>>>>>>> 5f350ef6ea942948af5da831bea996ea78c81597
@StaticMetamodel(Headquarters.class)
public class Headquarters_ { 

    public static volatile CollectionAttribute<Headquarters, Jrv> jrvCollection;
    public static volatile CollectionAttribute<Headquarters, Citizens> citizensCollection;
    public static volatile SingularAttribute<Headquarters, String> name;
    public static volatile SingularAttribute<Headquarters, String> x;
    public static volatile SingularAttribute<Headquarters, String> y;
    public static volatile SingularAttribute<Headquarters, Integer> id;
    public static volatile SingularAttribute<Headquarters, Cities> cityId;

}