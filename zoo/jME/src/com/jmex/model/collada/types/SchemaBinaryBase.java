/**
 * SchemaBinaryBase.java This file was generated by XMLSpy 2006sp2 Enterprise
 * Edition. YOU SHOULD NOT MODIFY THIS FILE, BECAUSE IT WILL BE OVERWRITTEN WHEN
 * YOU RE-RUN CODE GENERATION. Refer to the XMLSpy Documentation for further
 * details. http://www.altova.com/xmlspy
 */

package com.jmex.model.collada.types;


public abstract class SchemaBinaryBase implements SchemaTypeBinary {
    protected byte[] value;
    protected boolean isempty;
    protected boolean isnull;

    public SchemaBinaryBase() {
        setEmpty();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SchemaBinaryBase))
            return false;
        SchemaBinaryBase bin = (SchemaBinaryBase) obj;
        if (value.length != bin.value.length)
            return false;
        for (int i = 0; i < value.length; i++)
            if (value[i] != bin.value[i])
                return false;
        return true;
    }

    public void assign(SchemaType newvalue) {
        if (newvalue == null || newvalue.isNull())
            setNull();
        else if (newvalue.isEmpty())
            setEmpty();
        else if (newvalue instanceof SchemaBinaryBase) {
            value = ((SchemaBinaryBase) newvalue).value;
            isempty = ((SchemaBinaryBase) newvalue).isempty;
        } else if (newvalue instanceof SchemaString)
            parse(newvalue.toString());
        else
            throw new TypesIncompatibleException(newvalue, this);
    }

    // getValue, setValue
    public byte[] getValue() {
        return value;
    }

    public void setValue(byte[] newvalue) {
        value = newvalue;
        isnull = (newvalue == null) ? true : false;
        isempty = (newvalue.length == 0) ? true : false;
    }

    public abstract void parse(String s);

    public void setNull() {
        isnull = true;
        isempty = true;
        value = null;
    }

    public void setEmpty() {
        isnull = false;
        isempty = true;
        value = null;
    }

    // other interfaces
    public int hashCode() {
        if (value == null)
            return 0;
        return value[0];
    }

    public int length() {
        return value.length;
    }

    public boolean booleanValue() {
        return true;
    }

    public boolean isEmpty() {
        return isempty;
    }

    public boolean isNull() {
        return isnull;
    }

    public int compareTo(Object obj) {
        return compareTo((SchemaBinaryBase) obj);
    }

    public int compareTo(SchemaBinaryBase obj) {
        if (value.length != obj.value.length)
            return new Integer(value.length).compareTo(new Integer(
                    obj.value.length));
        for (int i = 0; i < value.length; i++)
            if (value[i] != obj.value[i])
                return new Integer(value[i])
                        .compareTo(new Integer(obj.value[i]));
        return 0;
    }
}
