
package com.couchbase.demo.repo.conn;

import com.couchbase.client.java.AsyncBucket;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;


/**
 * Provides singleton access to a bucket by creating the bucket
 * instance if not yet there
 * 
 * @author David Maier <david.maier at couchbase.com>
 */
public class BucketFactory {

    //TODO: Change for demo purposes
    public static final String BUCKET_NAME="accounts";
    public static final String BUCKET_PWD="test";
        
    /**
     * The one and only bucket
     */
    private static Bucket bucket;
    
    
    /**
     * Get the sync. bucket. This one is just a wrapper around 
     * the async. bucket
     * 
     * @return 
     */
    public static Bucket getBucket()
    {
    
        if (bucket == null)
            createBucketCon();
        
        return bucket;
    }
    
    /**
     * Get the async. bucket. It is wrapped by the sync one.
     * @return 
     */
    public static AsyncBucket getAsyncBucket()
    {
    
        if (bucket == null)
            createBucketCon();
        
        return bucket.async();
    }
    
    
    /**
     * Create the bucket connection
     * 
     * @return 
     */
    public static Bucket createBucketCon()
    {
        
        
        Cluster cluster = ClusterFactory.getCluster();
       
        
        bucket = cluster.openBucket(BUCKET_NAME, BUCKET_PWD);
       
       
        return bucket;
    }
}
