package com.example.packetmanager.compression;

import org.springframework.stereotype.Component;

@Component
public class LzCompressionImpl implements LzCompression{

    public int uncompress(int[] in, int[] out) {
        int marker, symbol;
        int i, inPos , outPos;
        int[] length = new int[1];
        int[] offset = new int[1];

        if (in.length < 1) {
            return 0;
        }

        marker = in[0];
        inPos = 1;
        outPos = 0;

        do {
            symbol = in[inPos++];
            if (symbol == marker) {

                if (in[inPos] == 0) {

                    out[outPos++] = marker;
                    ++inPos;
                } else {

                    inPos += readVarSize(length, in, inPos);
                    inPos += readVarSize(offset, in, inPos);

                    for (i = 0; i < length[0]; ++i) {
                        out[outPos] = out[outPos - offset[0]];
                        ++outPos;
                    }
                }
            } else {
                out[outPos++] =  symbol;
            }

        } while (inPos < in.length);

        return outPos;
    }

    private int readVarSize(int[] x, int[] buf, int startIndex) {
        int b;
        int y = 0;
        int numBytes = 0;
        do {
            b = buf[startIndex++];
            y = (y << 7) | (b & 0x0000007F);
            ++numBytes;
        } while ((b & 0x00000080) != 0);
        x[0] = y;
        return  numBytes;
    }

}
