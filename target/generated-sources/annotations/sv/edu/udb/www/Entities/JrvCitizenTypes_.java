package sv.edu.udb.www.Entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sv.edu.udb.www.Entities.JrvCitizen;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-09-16T20:06:00")
@StaticMetamodel(JrvCitizenTypes.class)
public class JrvCitizenTypes_ { 

    public static volatile SingularAttribute<JrvCitizenTypes, String> name;
    public static volatile CollectionAttribute<JrvCitizenTypes, JrvCitizen> jrvCitizenCollection;
    public static volatile SingularAttribute<JrvCitizenTypes, String> description;
    public static volatile SingularAttribute<JrvCitizenTypes, Integer> id;

}