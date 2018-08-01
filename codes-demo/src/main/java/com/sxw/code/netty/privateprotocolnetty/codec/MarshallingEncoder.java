package com.sxw.code.netty.privateprotocolnetty.codec;

import com.sxw.code.netty.privateprotocolnetty.codec.util.MarshallingCodeCFactory;
import io.netty.buffer.ByteBuf;
import org.jboss.marshalling.ByteOutput;
import org.jboss.marshalling.Marshaller;

import java.io.IOException;


/**
 * 使用jboss的序列化
 * Description: 消息编码工具类
 * User: shixiangweii
 * Date: 2018-08-01
 * Time: 11:30
 * Marshalling 分组编码
 *
 * @author shixiangweii
 */
public class MarshallingEncoder {
    private static final byte[] LENGTH_PLACEHOLDER = new byte[4];

    private Marshaller marshaller;

    public MarshallingEncoder() throws IOException {
        marshaller = MarshallingCodeCFactory.buildMarshalling();
    }

    protected void encode(Object msg, ByteBuf out) throws Exception {
        int lengthPos = out.writerIndex();
        out.writeBytes(LENGTH_PLACEHOLDER);
        ChannelBufferByteOutput output = new ChannelBufferByteOutput(out);
        try {
            marshaller.start(output);
            marshaller.writeObject(msg);
            marshaller.finish();
        } finally {
            marshaller.close();
        }
        out.setInt(lengthPos, out.writerIndex() - lengthPos - 4);
    }
}

class ChannelBufferByteOutput implements ByteOutput {

    private final ByteBuf buffer;

    /**
     * Create a new instance which use the given {@link ByteBuf}
     */
    public ChannelBufferByteOutput(ByteBuf buffer) {
        this.buffer = buffer;
    }

    @Override
    public void close() throws IOException {
        // Nothing to do
    }

    @Override
    public void flush() throws IOException {
        // nothing to do
    }

    @Override
    public void write(int b) throws IOException {
        buffer.writeByte(b);
    }

    @Override
    public void write(byte[] bytes) throws IOException {
        buffer.writeBytes(bytes);
    }

    @Override
    public void write(byte[] bytes, int srcIndex, int length) throws IOException {
        buffer.writeBytes(bytes, srcIndex, length);
    }

    /**
     * Return the {@link ByteBuf} which contains the written content
     */
    ByteBuf getBuffer() {
        return buffer;
    }
}
