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
package eu.aleon.aleoncean.packet.radio.userdata.eepd201;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Markus Rathgeb <maggu2810@gmail.com>
 */
public enum TaughtInDevices {

    DISABLE((byte) 0x00),
    ENABLE((byte) 0x01);

    private static final Logger LOGGER = LoggerFactory.getLogger(TaughtInDevices.class);

    public static TaughtInDevices fromByte(final byte value) {
        switch (value) {
            case 0x00:
                return TaughtInDevices.DISABLE;
            case 0x01:
                return TaughtInDevices.ENABLE;
            default:
                LOGGER.warn("Invalid TaughtInDevices: {}", value);
                return TaughtInDevices.ENABLE;
        }
    }

    final byte value;

    private TaughtInDevices(final byte value) {
        this.value = value;
    }

    public byte toByte() {
        return value;
    }
}
