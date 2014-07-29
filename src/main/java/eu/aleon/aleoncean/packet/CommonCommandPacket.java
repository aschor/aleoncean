/*
 * Copyright (c) 2014 aleon GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Markus Rathgeb - initial API and implementation and/or initial documentation
 */
package eu.aleon.aleoncean.packet;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 *
 * @author Markus Rathgeb <maggu2810@gmail.com>
 */
public class CommonCommandPacket extends ESP3Packet {

    private static final int POS_CODE = DATA_START;

    private byte commonCommandCode;

    private byte[] commonCommandData;

    public CommonCommandPacket() {
        this(CommonCommandCode.UNDEF);
    }

    public CommonCommandPacket(final byte commonCommandCode) {
        super(PacketType.COMMON_COMMAND);
        setCommonCommandCode(commonCommandCode);
    }

    @Override
    public void setData(byte[] data) {
        int pos = 0;
        byte[] tmp;

        setCommonCommandCode(data[pos++]);

        tmp = new byte[data.length - pos];
        System.arraycopy(data, pos, tmp, 0, tmp.length);
        setCommonCommandData(tmp);
        //pos += tmp.length;
    }

    @Override
    public byte[] getData() {
        byte[] rawCommonCommandData = getCommonCommandData();
        if (rawCommonCommandData == null) {
            rawCommonCommandData = new byte[0];
        }

        final int rawDataLength = 1 /* common command code */
                                  + rawCommonCommandData.length;

        final byte[] rawData = new byte[rawDataLength];
        ByteBuffer bb = ByteBuffer.wrap(rawData);
        bb.order(ByteOrder.BIG_ENDIAN);

        bb.put(getCommonCommandCode());
        bb.put(rawCommonCommandData);

        return rawData;
    }

    public final byte getCommonCommandCode() {
        return commonCommandCode;
    }

    public final void setCommonCommandCode(byte commonCommandCode) {
        this.commonCommandCode = commonCommandCode;
    }

    public byte[] getCommonCommandData() {
        return commonCommandData;
    }

    public void setCommonCommandData(byte[] commonCommandData) {
        this.commonCommandData = commonCommandData;
    }

    public static byte getCommonCommandCode(final byte[] raw) {
        return raw[POS_CODE];
    }

}
