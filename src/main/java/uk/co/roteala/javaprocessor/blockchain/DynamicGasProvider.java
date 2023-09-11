package uk.co.roteala.javaprocessor.blockchain;

import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.web3j.protocol.Web3j;
import org.web3j.tx.gas.ContractGasProvider;

import java.math.BigInteger;


public class DynamicGasProvider implements ContractGasProvider {
    private BigInteger gasLimit;
    private final Web3j web3j;

    public DynamicGasProvider(BigInteger gasLimit, Web3j web3j) {
        this.gasLimit = gasLimit;
        this.web3j = web3j;
    }

    @Override
    public BigInteger getGasPrice(String s) {
        try {
            return this.web3j.ethGasPrice().send().getGasPrice();
        } catch (Exception e) {
            return BigInteger.ZERO;
        }
    }

    @Override
    public BigInteger getGasPrice() {
        return getGasPrice(null);
    }

    @Override
    public BigInteger getGasLimit(String s) {
        return this.gasLimit;
    }

    @Override
    public BigInteger getGasLimit() {
        return getGasLimit(null);
    }
}
