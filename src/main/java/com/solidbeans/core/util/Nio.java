package com.solidbeans.core.util;

import com.google.common.collect.Maps;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Nio util
 *
 * @author magnus.wahlstrom@solidbeans.com
 */
public final class Nio {

    private final Map<String, Integer> powerMap;

    Nio() {
        powerMap = Maps.newHashMap();
        powerMap.put("YB", 8);
        powerMap.put("ZB", 7);
        powerMap.put("EB", 6);
        powerMap.put("PB", 5);
        powerMap.put("TB", 4);
        powerMap.put("GB", 3);
        powerMap.put("MB", 2);
        powerMap.put("KB", 1);
        powerMap.put("B", 0);
        powerMap.put("", 0);
    }

    /**
     * Transfers content from input stream to output stream using Java Nio.
     *
     * @param inputStream The source
     * @param outputStream The target
     * @throws IOException If transfer fails
     */
    public void transfer(InputStream inputStream, OutputStream outputStream) throws IOException {
        checkNotNull(inputStream, "Input stream is null");
        checkNotNull(outputStream, "Output stream is null");

        ReadableByteChannel inputChannel = null;
        WritableByteChannel outputChannel = null;

        try {
            inputChannel = Channels.newChannel(inputStream);
            outputChannel = Channels.newChannel(outputStream);
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            while(true) {
                buffer.clear();

                if(inputChannel.read(buffer) == -1) {
                    break;
                }

                buffer.flip();
                outputChannel.write(buffer);
            }
        }
        finally {
            silentClose(inputChannel);
            silentClose(outputChannel);
        }
    }

    /**
     * Closes a closable resource silently catching any exception thrown
     * @param closeable Resource to close
     */
    public void silentClose(AutoCloseable closeable) {
        try {
            closeable.close();
        }
        catch(Throwable e) {
        }
    }

    /**
     * Transforms a readable size into real bytes, 10GB will be 10737418240 bytes
     * @param readableSize Readable size to transform
     * @return Real byte length
     */
    public BigInteger toRealByteLength(String readableSize) {
        checkNotNull(readableSize, "Readable size is null");

        Pattern pattern = Pattern.compile("([\\d.]+)([YZEPTGMK]?[B]?)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(readableSize);

        if (matcher.find()) {
            String number = matcher.group(1);
            int pow = powerMap.get(matcher.group(2).toUpperCase());

            BigInteger bytes = new BigInteger(number);
            BigInteger factor = BigInteger.valueOf(1024).pow(pow);

            return bytes.multiply(factor);
        }

        return new BigInteger("0");
    }

    /**
     * Tells if supplied IP string is in IP4 or IP6 format
     *
     * @param ip The IP string to validate
     * @return If supplied IP is in IP4 or IP6 format or not
     */
    public boolean isValidIp(String ip) {
        checkNotNull(ip, "IP is null");
        return ip.matches("[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}") ||
                ip.matches("[0-9A-Za-z]{1,4}:[0-9A-Za-z]{1,4}:[0-9A-Za-z]{1,4}:[0-9A-Za-z]{1,4}:[0-9A-Za-z]{1,4}:[0-9A-Za-z]{1,4}:[0-9A-Za-z]{1,4}:[0-9A-Za-z]{1,4}");
    }
}
