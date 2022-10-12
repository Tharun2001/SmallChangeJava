package com.smallChange.integration;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.smallChange.security.Security;
//import com.smallChange.integration.SimpleDataSource;

class SecurityDaoImplTest {

	private SecurityDao SecurityDao;
    private SimpleDataSource dataSource;
    private Connection connection;
    private TransactionManager transactionManager; 
//    private Security Security2;
//    private Security Security3;
    
    @BeforeEach
    void setUp() {
    	 dataSource = new SimpleDataSource();
         connection = dataSource.getConnection();
         transactionManager = new TransactionManager(dataSource);
         transactionManager.startTransaction();
         SecurityDao = new SecurityDaoImpl(dataSource);
         
//         Security2= new Security(1, "Apple Inc", "AAPL",154.29,"Main Index");
//         Security3= new Security(2, "Tesla Inc", "TSLA",733.8,"Main Index");
    }
    @AfterEach
    void tearDown() throws SQLException {
        transactionManager.rollbackTransaction();
        dataSource.shutdown();
    }
    
    @Test
    void testGetSecuritysSuccess()
    {
    	List<Security> allSecurity= SecurityDao.getSecurities();
    	assertEquals(10 ,allSecurity.size());

    }
//    
    @Test
    void testInsertSecurityWithPhoneNumber() {
        Security newSecurity = new Security(12, "Spacex", "TSLA",733.8,"Main Index");
        SecurityDao.insertSecurity(newSecurity);
        List<Security> allSecurity= SecurityDao.getSecurities();
    	assertEquals(11 ,allSecurity.size());
}

//    
//    
    @Test
    void testInsertNullSecurityId() throws Exception
    {
        assertThrows(NullPointerException.class, () -> {
            SecurityDao.insertSecurity(null);
        });
    }
//    
    @Test
    void testInsertSecurityDuplicate() {
        
        Security newSecurity =new Security(2, "Tesla Inc", "TSLA",733.8,"Main Index");
        assertThrows(DatabaseException.class, () -> {
            SecurityDao.insertSecurity(newSecurity);
        });
 
    }
//

    
    @Test
    void testDeleteSecurity() {
        int id = 3;
        SecurityDao.deleteSecurity(id);
    	List<Security> allSecurity= SecurityDao.getSecurities();
    	assertEquals(9 ,allSecurity.size());
    }
//    


//    
//
    @Test
    void testDeleteNegativeSecurityId() throws Exception
    {
    	int id =-1;
        assertThrows(IllegalArgumentException.class, () -> {
            SecurityDao.deleteSecurity(id);
        });
       
    }
//    
    @Test
    void testDeleteZeroSecurityId() throws Exception
    {
    	int id =0;
    	
        assertThrows(IllegalArgumentException.class, () -> {
            SecurityDao.deleteSecurity(id);
        });
      
    }
    
//    @Test
//	void testUpdateSecurity() {
//		int id = 1;
//		Security updatedSecurity = new Security(2, "Tesla Inc", "TSLA",733.8,"Nifty Index");
//		SecurityDao.updateSecurity(updatedSecurity);
//    	List<Security> allSecurity= SecurityDao.getSecurities();
//    	assertEquals(9 ,allSecurity.size());
//
//	}
//

//    
    @Test
	void testUpdateSecurityNotPresentThrowsException() {
		int id = 120;
		Security updatedSecurity = new Security(id, "Tesla Inc", "TSLA",733.8,"Nifty Index");
		assertThrows(DatabaseException.class, () -> {
			SecurityDao.updateSecurity(updatedSecurity);
		});
		
		
    }

}
