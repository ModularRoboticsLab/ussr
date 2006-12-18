/**
 * SchemaHexBinary.java This file was generated by XMLSpy 2006sp2 Enterprise
 * Edition. YOU SHOULD NOT MODIFY THIS FILE, BECAUSE IT WILL BE OVERWRITTEN WHEN
 * YOU RE-RUN CODE GENERATION. Refer to the XMLSpy Documentation for further
 * details. http://www.altova.com/xmlspy
 */

package com.jmex.model.collada.types;

public class SchemaHexBinary extends SchemaBinaryBase {

    private static final long serialVersionUID = 1L;
    protected final char[] FINAL_ENCODE = { '0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
    protected final byte[] FINAL_DECODE = { -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, -1, -1, -1, -1,
            -1, -1, -1, 10, 11, 12, 13, 14, 15, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, 10, 11, 12, 13, 14, 15, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1 };

    // construction
    public SchemaHexBinary() {
        super();
    }

    public SchemaHexBinary(SchemaHexBinary newvalue) {
        value = newvalue.value;
        isempty = newvalue.isempty;
        isnull = newvalue.isnull;
    }

    public SchemaHexBinary(byte[] newvalue) {
        setValue(newvalue);
    }

    public SchemaHexBinary(String newvalue) {
        parse(newvalue);
    }

    public SchemaHexBinary(SchemaType newvalue) {
        assign(newvalue);
    }

    public SchemaHexBinary(SchemaTypeBinary newvalue) {
        assign(newvalue);
    }

    // getValue, setValue
    public void parse(String newvalue) {
        if (newvalue == null)
            setNull();
        else if (newvalue.length() == 0)
            setEmpty();
        else {
            char[] cSrc = newvalue.toCharArray();
            value = new byte[cSrc.length >> 1];
            int nSrcIndex = 0;
            int nTarIndex = 0;
            while (nSrcIndex < cSrc.length) {
                byte c = FINAL_DECODE[cSrc[nSrcIndex++]];
                if (c != -1) {
                    value[nTarIndex >> 1] |= (byte) ((nTarIndex & 1) == 1 ? c
                            : (c << 4));
                    nTarIndex++;
                }
            }
            isnull = false;
            isempty = (value.length == 0) ? true : false;
        }
    }

    // further
    public int hashCode() {
        return value.hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SchemaHexBinary))
            return false;
        return value == ((SchemaHexBinary) obj).value;
    }

    public Object clone() {
        return new SchemaHexBinary(this);
    }

    public String toString() {
        if (isempty || isnull || value == null)
            return "";

        char[] cResult = new char[value.length << 1];
        for (int i = 0; i < value.length; i++) {
            cResult[i << 1] = FINAL_ENCODE[(value[i] >> 4) & 15];
            cResult[(i << 1) + 1] = FINAL_ENCODE[value[i] & 15];
        }
        return new String(cResult);
    }

    // ---------- interface SchemaTypeBinary ----------
    public int binaryType() {
        return BINARY_VALUE_HEX;
    }
}
