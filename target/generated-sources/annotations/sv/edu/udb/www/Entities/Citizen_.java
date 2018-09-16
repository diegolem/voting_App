package sv.edu.udb.www.Entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sv.edu.udb.www.Entities.Candidates;
import sv.edu.udb.www.Entities.CitizenTypes;
import sv.edu.udb.www.Entities.CitizenVotes;
import sv.edu.udb.www.Entities.Headquarters;
import sv.edu.udb.www.Entities.JrvCitizen;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-09-16T12:19:05")
@StaticMetamodel(Citizen.class)
public class Citizen_ { 

    public static volatile SingularAttribute<Citizen, Date> birthdate;
    public static volatile SingularAttribute<Citizen, CitizenTypes> citizenTypeId;
    public static volatile CollectionAttribute<Citizen, CitizenVotes> citizenVotesCollection;
    public static volatile CollectionAttribute<Citizen, Candidates> candidatesCollection;
    public static volatile SingularAttribute<Citizen, String> name;
    public static volatile CollectionAttribute<Citizen, JrvCitizen> jrvCitizenCollection;
    public static volatile SingularAttribute<Citizen, String> dui;
    public static volatile SingularAttribute<Citizen, Headquarters> headquarterId;
    public static volatile SingularAttribute<Citizen, String> adress;
    public static volatile SingularAttribute<Citizen, Integer> id;
    public static volatile SingularAttribute<Citizen, String> lastname;

}