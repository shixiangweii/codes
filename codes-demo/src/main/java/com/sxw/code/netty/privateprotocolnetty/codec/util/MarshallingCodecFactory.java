package com.sxw.code.netty.privateprotocolnetty.codec.util;

import org.jboss.marshalling.*;

import java.io.IOException;

/**
 * jboss marshalling 生成工厂
 * <p>
 * <p>
 * <dependency>
 * <groupId>org.jboss.marshalling</groupId>
 * <artifactId>jboss-marshalling</artifactId>
 * <version>1.3.0.CR9</version>
 * </dependency>
 * <p>
 * <dependency>
 * <groupId>org.jboss.marshalling</groupId>
 * <artifactId>jboss-marshalling-serial</artifactId>
 * <version>1.3.0.CR9</version>
 * </dependency>
 *
 * 这个demo要使用1.3.0.CR9
 *
 * @author shixiangweii
 */
public class MarshallingCodecFactory {
    /**
     * 获取marshaller
     *
     * @return
     * @throws IOException
     */
    public static Marshaller buildMarshalling() throws IOException {
        final MarshallerFactory factory = Marshalling.getProvidedMarshallerFactory("serial");
        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        return factory.createMarshaller(configuration);
    }

    /**
     * 获取unmarshaller
     *
     * @return
     * @throws IOException
     */
    public static Unmarshaller buildUnMarshalling() throws IOException {
        final MarshallerFactory factory = Marshalling.getProvidedMarshallerFactory("serial");
        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        return factory.createUnmarshaller(configuration);
    }
}