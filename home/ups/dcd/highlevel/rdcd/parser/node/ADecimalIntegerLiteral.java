/* This file was generated by SableCC (http://www.sablecc.org/). */

package dcd.highlevel.rdcd.parser.node;

import dcd.highlevel.rdcd.parser.analysis.*;

@SuppressWarnings("nls")
public final class ADecimalIntegerLiteral extends PIntegerLiteral
{
    private TDecimalIntegerLiteral _decimalIntegerLiteral_;

    public ADecimalIntegerLiteral()
    {
        // Constructor
    }

    public ADecimalIntegerLiteral(
        @SuppressWarnings("hiding") TDecimalIntegerLiteral _decimalIntegerLiteral_)
    {
        // Constructor
        setDecimalIntegerLiteral(_decimalIntegerLiteral_);

    }

    @Override
    public Object clone()
    {
        return new ADecimalIntegerLiteral(
            cloneNode(this._decimalIntegerLiteral_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseADecimalIntegerLiteral(this);
    }

    public TDecimalIntegerLiteral getDecimalIntegerLiteral()
    {
        return this._decimalIntegerLiteral_;
    }

    public void setDecimalIntegerLiteral(TDecimalIntegerLiteral node)
    {
        if(this._decimalIntegerLiteral_ != null)
        {
            this._decimalIntegerLiteral_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._decimalIntegerLiteral_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._decimalIntegerLiteral_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._decimalIntegerLiteral_ == child)
        {
            this._decimalIntegerLiteral_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._decimalIntegerLiteral_ == oldChild)
        {
            setDecimalIntegerLiteral((TDecimalIntegerLiteral) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
