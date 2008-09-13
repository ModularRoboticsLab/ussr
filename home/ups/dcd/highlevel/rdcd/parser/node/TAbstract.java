/* This file was generated by SableCC (http://www.sablecc.org/). */

package dcd.highlevel.rdcd.parser.node;

import dcd.highlevel.rdcd.parser.analysis.*;

@SuppressWarnings("nls")
public final class TAbstract extends Token
{
    public TAbstract()
    {
        super.setText("abstract");
    }

    public TAbstract(int line, int pos)
    {
        super.setText("abstract");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TAbstract(getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTAbstract(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TAbstract text.");
    }
}
