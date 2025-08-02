package com.distsys.climateaction;

import io.grpc.Metadata;
import io.grpc.Metadata.Key;

/**
 *
 * @author xinminghui
 */
public class CAMetaData {

    public static Metadata buildTokenMetadata() {
        Metadata metaData = new Metadata();
        Key<String> tokenKey = Key.of("token", Metadata.ASCII_STRING_MARSHALLER);
        metaData.put(tokenKey, "distributedsystem");
        return metaData;
    }
    
    // for test, build invalid token key
    public static Metadata buildInvalidTokenMetadata() {
        Metadata metaData = new Metadata();
        Key<String> tokenKey = Key.of("token", Metadata.ASCII_STRING_MARSHALLER);
        metaData.put(tokenKey, "invaliddistributedsystem");
        return metaData;
    }
}
