package dataaccess;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBUtil {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("cprg352_lab09PU");
    
    public static EntityManagerFactory getFactory() {
        return emf;
    }
}
