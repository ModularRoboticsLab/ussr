/* This file was generated by SableCC (http://www.sablecc.org/). */

package dcd.highlevel.rdcd.parser.node;

import dcd.highlevel.rdcd.parser.analysis.*;

@SuppressWarnings("nls")
public final class TLParenthese extends Token
{
    public TLParenthese()
    {
        super.setText("(");
    }

    public TLParenthese(int line, int pos)
    {
        super.setText("(");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TLParenthese(getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTLParenthese(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TLParenthese text.");
    }
}
