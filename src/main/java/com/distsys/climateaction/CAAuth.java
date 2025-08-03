package com.distsys.climateaction;

import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import io.grpc.Status;


/**
 *
 * @author xinminghui
 */
/* Authetication - Middleware
* Parse the token value from the metadata 
* and then determine whether it is valid
*/
public class CAAuth implements ServerInterceptor {

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> sc, Metadata mtdt, ServerCallHandler<ReqT, RespT> sch) {
        String token = mtdt.get(Metadata.Key.of("token", Metadata.ASCII_STRING_MARSHALLER));
        if (!token.equals("distributedsystem")) {
            Status unauthenticated = Status.UNAUTHENTICATED
                    .withDescription("Invalid token")
                    .withCause(new RuntimeException("Auth failed"));
            sc.close(unauthenticated, mtdt);
            // empty listener means ignore this request
            return new ServerCall.Listener<ReqT>() {};
        }
        return sch.startCall(sc, mtdt);
    }
    
}
