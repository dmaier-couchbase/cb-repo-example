package com.couchbase.demo.repo.conn;

import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.env.CouchbaseEnvironment;
import com.couchbase.client.java.env.DefaultCouchbaseEnvironment;
import java.util.Arrays;
import java.util.List;

/**
 * Provides singleton access to a cluster, by creating the cluster
 * reference if not yet there
 * 
 * 
 * @author David Maier <david.maier at couchbase.com>
 */
public class ClusterFactory {
    
    //TODO: Change this for demo purposes
    public static final String CB_HOST = "54.244.138.136";
    
    /**
     * The one and only cluster
     */
    private static Cluster cluster;
    
    /**
     * Get the cluster
     * @return 
     */
    public static Cluster getCluster()
    {
        if (cluster == null)
            createCluster();
       
        return cluster;
    }
    
    /**
     * Create the cluster reference by setting the envrionment
     * 
     * @return 
     */
    public static Cluster createCluster()
    {
       //Create the cluster reference
        String[] hosts = new String[]{CB_HOST};        
        List<String> nodes = Arrays.asList(hosts);
                          
        CouchbaseEnvironment env = DefaultCouchbaseEnvironment
                                .builder()
                                .kvTimeout(10000)
                                .build();
      
        cluster = CouchbaseCluster.create(env, nodes);
   
        
        return cluster;
    }
}
