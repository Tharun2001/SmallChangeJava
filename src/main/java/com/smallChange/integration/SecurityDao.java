package com.smallChange.integration;

import java.util.List;

import com.smallChange.security.Security;

public interface SecurityDao {
	
	List<Security> getSecurities();
	void insertSecurity(Security security);
	void deleteSecurity(int sId);
	void updateSecurity(Security security);
}
