package com.couchbase.demo.repo;

import com.couchbase.demo.repo.conn.BucketFactory;
import com.couchbase.client.java.AsyncBucket;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.ParameterizedN1qlQuery;
import java.util.logging.Level;
import java.util.logging.Logger;
import rx.Observable;
import static com.couchbase.client.java.query.Select.select;
import com.couchbase.client.java.query.Statement;
import static com.couchbase.client.java.query.dsl.Expression.x;


/**
 * Just wraps the bucket access by adding addtional error handling or logging 
 * 
 * 
 * @author David Maier <david.maier at couchbase.com>
 */
public class CouchbaseConnector {

    //Logger
    private static final Logger LOG = Logger.getLogger(CouchbaseConnector.class.getName());

    /**
     * The bucket used by this connector
     */
    private AsyncBucket bucket = BucketFactory.getAsyncBucket();
    
    /**
     * Get a document async. 
     * 
     * @param key
     * @return 
     */
    public Observable<JsonDocument> get(String key) {
        
        return bucket.get(key).doOnNext(d -> LOG.log(Level.INFO, "Got doc with key {0}", d.id()));
    }
    
    /**
     * Set a document async.
     * 
     * @param key
     * @param value
     * @return 
     */
    public Observable<JsonDocument> set(String key, JsonObject value) {
               
        JsonDocument doc = JsonDocument.create(key, value);
        
        return bucket.upsert(doc)
                .doOnNext(
                        d -> {
                            LOG.log(Level.INFO, "Set the doc with key {0}", d.id());
                             }
                );
    }
    
    
    /**
     * Async. get all documents with a specific type attribute
     * 
     * @param type
     * @return 
     */
    public Observable<JsonObject> getAll(String type) {
    
        //The fluent interface helps you to get the syntax right
        Statement stmt = select("*")
                .from(bucket.name())
                .where(
                        x("type").eq("$type")
                );
        
        //The query params can be set by using a parameterized query    
        JsonObject params = JsonObject.empty();
        params.put("type", type);
        ParameterizedN1qlQuery query = N1qlQuery.parameterized(stmt, params);
       
        
        return bucket.query(query)
                .flatMap(r -> r.rows())
                .doOnNext(row -> LOG.info(row.toString()))
                .map(row -> row.value());
      
    }
    
}


