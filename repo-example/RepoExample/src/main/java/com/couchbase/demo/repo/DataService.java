package com.couchbase.demo.repo;

import java.util.List;
import rx.Observable;



/**
 * A data service might use one or multiple repositories
 * 
 * We decided in this example to synchronize the database access on
 * this level. Please take into account that you might want to even
 * pass the Observables further in order to process us much as 
 * possible asynchronously.
 * 
 * @author David Maier <david.maier at couchbase.com>
 */
public class DataService {
    
    /**
     * The repository which is used by this data service
     */
    private AccountRepository repo;

    /**
     * Gets passed one or more repos
     * 
     * @param repo 
     */
    public DataService(AccountRepository repo) {
        
        this.repo = repo;
    }
    
    
    /**
     * Get an account by his id
     * 
     * @param id
     * @return 
     */
    public Account getAccount(String id) {
        
       return repo.getAccount(id).toBlocking().single();
     
    }
    
    /**
     * Store an account
     * 
     * @param account
     * @return 
     */
    public Account storeAccount(Account account) {
        
       return repo.storeAccount(account).toBlocking().single();
     
    }
    
    /**
     * Store multiple accounts in parallel
     * 
     * @param accounts
     * @return 
     */
    public Iterable<Account> storeAccounts(List<Account> accounts) {
    
        
        Observable.from(accounts)
                .flatMap(a -> repo.storeAccount(a))
                .toBlocking()
                .last();
        
        return accounts;
        
    }
    
    /**
     * Get multiple accounts in parallel
     * 
     * @return 
     */
    public Iterable<Account> getAllAccounts() {
        
   
        return repo.getAllAccounts().toBlocking().toIterable();
        
    }
   
    
}
