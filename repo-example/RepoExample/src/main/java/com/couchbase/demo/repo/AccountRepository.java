package com.couchbase.demo.repo;

import com.couchbase.client.java.document.json.JsonObject;
import rx.Observable;

/**
 * A simple account repository
 *  
 * 
 * @author David Maier <david.maier at couchbase.com>
 */
public class AccountRepository {

    /**
     * The prefix of an account
     */
    private static final String ACCOUNT_PREFIX = "account::";

    
    /**
     * The underlying Couchbase connection
     */
    private CouchbaseConnector cbCon;

    
    /**
     * The repo takes a connection as the only parameter
     * 
     * @param cbCon 
     */
    public AccountRepository(CouchbaseConnector cbCon) {
        
        this.cbCon = cbCon;
        
    }
    
    /**
     * Get a specific account async.
     * 
     * @param aId
     * @return 
     */
    public Observable<Account> getAccount(String aId) {
        
        return cbCon.get(ACCOUNT_PREFIX + aId).map(doc -> toAccount(doc.content()));
    }
    
    /**
     * Store an account async.
     * 
     * @param account
     * @return 
     */
    public Observable<Account> storeAccount(Account account) {
        
        
        return cbCon.set(ACCOUNT_PREFIX + account.getAid(), fromAccount(account))
                .map(d -> account);
        
    }
    
    /**
     * Get all accounts by executing a N1QL query
     * 
     * @return 
     */
    public Observable<Account> getAllAccounts() {
    
        return cbCon.getAll("account")
                .map(j -> toAccount(j));
        
        
    }
    
    /**
     * Convert JSON to an account entity
     * 
     * @param obj
     * @return 
     */
    private static Account toAccount(JsonObject obj) {
        

        String id = obj.getString("id");
          
        Account account = new Account(id);
        
        account.setFirstName(obj.getString("first_name"));
        account.setLastName(obj.getString("last_name"));
        account.setEmailAddr(obj.getString("email"));
        
        return account;
    }
    
    /**
     * Convert account entity into JSON
     * 
     * @param account
     * @return 
     */
    private static JsonObject fromAccount(Account account) {
        
        JsonObject obj = JsonObject.empty();
        
        obj.put("type", "account");
        obj.put("id", account.getAid());
        obj.put("first_name",account.getFirstName());
        obj.put("last_name", account.getLastName());
        obj.put("email", account.getEmailAddr());
        
        return obj;
    }
    
    
}

