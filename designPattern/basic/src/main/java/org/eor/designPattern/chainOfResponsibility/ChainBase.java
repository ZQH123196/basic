package org.eor.designPattern.chainOfResponsibility;

public abstract class ChainBase<T> {
    protected ChainBase<T> chain;

    public void setChain(ChainBase<T> chain) {
        this.chain = chain;
    }


}
