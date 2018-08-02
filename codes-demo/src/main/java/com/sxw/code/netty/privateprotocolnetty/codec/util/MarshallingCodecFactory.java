package com.sxw.code.netty.privateprotocolnetty.codec.util;

import com.sxw.code.netty.privateprotocolnetty.codec.NettyMarshallingDecoder;
import com.sxw.code.netty.privateprotocolnetty.codec.NettyMarshallingEncoder;
import io.netty.handler.codec.marshalling.DefaultMarshallerProvider;
import io.netty.handler.codec.marshalling.DefaultUnmarshallerProvider;
import io.netty.handler.codec.marshalling.MarshallerProvider;
import io.netty.handler.codec.marshalling.UnmarshallerProvider;
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
 * <p>
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


    public static NettyMarshallingDecoder buildMarshallingDecoder() {
        MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
        MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        UnmarshallerProvider provider = new DefaultUnmarshallerProvider(marshallerFactory, configuration);
        return new NettyMarshallingDecoder(provider, 1024);
    }

    public static NettyMarshallingEncoder buildMarshallingEncoder() {
        MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
        MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        MarshallerProvider provider = new DefaultMarshallerProvider(marshallerFactory, configuration);
        return new NettyMarshallingEncoder(provider);

    }

}