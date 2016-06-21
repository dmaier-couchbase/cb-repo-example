package com.couchbase.demo.repo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Enty point of the application
 * 
 * 
 * @author David Maier <david.maier at couchbase.com>
 */
public class Main {
 
    public static void main(String[] args) {
        
        //Prepare the data service
        CouchbaseConnector con = new CouchbaseConnector();
        AccountRepository repo = new AccountRepository(con);
        DataService service  = new DataService(repo);
        
        //Prepare some accounts
        Account dmaier = new Account("dmaier");
        dmaier.setFirstName("David");
        dmaier.setLastName("Maier");
        dmaier.setEmailAddr("david.maier@couchbase.com");
        
        
        Account ohuges = new Account("ohuges");
        ohuges.setFirstName("Owen");
        ohuges.setLastName("Huges");
        ohuges.setEmailAddr("owen@couchbase.com");
        
        
        Account jakub = new Account("davido");
        jakub.setFirstName("David");
        jakub.setLastName("Ostrovsky");
        jakub.setEmailAddr("davido@couchbase.com");
        
        
        Account jzak = new Account("sleigh");
        jzak.setFirstName("Simon");
        jzak.setLastName("Leigh");
        jzak.setEmailAddr("simon.leigh@couchbase.com");
        
        
        List<Account> accounts = new ArrayList<>();
        accounts.add(dmaier);
        accounts.add(ohuges);
        accounts.add(jakub);
        accounts.add(jzak);
        
        //Store the accounts
        service.storeAccounts(accounts);
        
        /**
         * Get all accounts
         */
        for (Iterator<Account> iterator = service.getAllAccounts().iterator(); iterator.hasNext();) {
            Account next = iterator.next();
            
            System.out.println(next.getAid());
            
        }

    }
    
    
}
