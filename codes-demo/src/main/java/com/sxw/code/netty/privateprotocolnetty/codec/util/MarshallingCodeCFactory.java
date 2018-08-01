package com.sxw.code.netty.privateprotocolnetty.codec.util;

import org.jboss.marshalling.*;

import java.io.IOException;

/**
 * jboss marshalling 生成工厂
 *
 * @author shixiangweii
 */
public class MarshallingCodeCFactory {

    public static Marshaller buildMarshalling() throws IOException {
        final MarshallerFactory factory = Marshalling.getProvidedMarshallerFactory("serial");
        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        return factory.createMarshaller(configuration);
    }

    public static Unmarshaller buildUnMarshalling() throws IOException {
        final MarshallerFactory factory = Marshalling.getProvidedMarshallerFactory("serial");
        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        return factory.createUnmarshaller(configuration);
    }
}