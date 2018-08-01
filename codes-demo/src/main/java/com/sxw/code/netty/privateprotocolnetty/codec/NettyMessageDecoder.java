package com.sxw.code.netty.privateprotocolnetty.codec;

import com.sxw.code.netty.privateprotocolnetty.protocal.Header;
import com.sxw.code.netty.privateprotocolnetty.protocal.NettyMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-08-01
 * Time: 14:46
 *
 * @author shixiangweii
 */
public class NettyMessageDecoder extends LengthFieldBasedFrameDecoder {
    private MarshallingDecoder marshallingDecoder;

    public NettyMessageDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength) throws Exception {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
        marshallingDecoder = new MarshallingDecoder();
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        ByteBuf frame = (ByteBuf) super.decode(ctx, in);
        if (frame == null) {
            return null;
        }
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setCrcCode(in.readInt());
        header.setLength(in.readInt());
        header.setSessionID(in.readLong());
        header.setType(in.readByte());
        header.setPriority(in.readByte());
        int size = in.readInt();
        if (size > 0) {
            Map<String, Object> attach = new HashMap<>(size);
            int keySize = 0;
            byte[] keyArray = null;
            String key = null;
            for (int i = 0; i < size; i++) {
                keySize = in.readInt();
                keyArray = new byte[keySize];
                in.readBytes(keyArray);
                key = new String(keyArray, "UTF-8");
                attach.put(key, marshallingDecoder.decode(in));
            }
            keyArray = null;
            key = null;
            header.setAttachment(attach);
        }
        if (in.readableBytes() > 4) {
            message.setBody(marshallingDecoder.decode(in));
        }
        message.setHeader(header);
        return message;
    }
}
