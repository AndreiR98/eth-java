package uk.co.roteala.javaprocessor.contracts;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple5;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.9.8.
 */
@SuppressWarnings("rawtypes")
public class Confirmation extends Contract {
    public static final String BINARY = "608060405234801561000f575f80fd5b506103068061001d5f395ff3fe608060405234801561000f575f80fd5b506004361061004a575f3560e01c8063081bf64a1461004e5780631e96917d1461007b57806339adad5414610092578063b72ef4c8146100a5575b5f80fd5b61006161005c3660046101d1565b6100e5565b6040516100729594939291906101e8565b60405180910390f35b61008460035481565b604051908152602001610072565b6100846100a0366004610263565b6101a6565b6100cd6100b33660046101d1565b60016020525f90815260409020546001600160a01b031681565b6040516001600160a01b039091168152602001610072565b5f602081905290815260409020805460018201805491929161010690610298565b80601f016020809104026020016040519081016040528092919081815260200182805461013290610298565b801561017d5780601f106101545761010080835404028352916020019161017d565b820191905f5260205f20905b81548152906001019060200180831161016057829003601f168201915b505050506002830154600384015460049094015492936001600160a01b03918216939116915085565b6002602052815f5260405f2081815481106101bf575f80fd5b905f5260205f20015f91509150505481565b5f602082840312156101e1575f80fd5b5035919050565b8581525f602060a08184015286518060a08501525f5b8181101561021a5788810183015185820160c0015282016101fe565b505f60c08286018101919091526001600160a01b03888116604087015287166060860152601f909101601f191684010191506102539050565b8260808301529695505050505050565b5f8060408385031215610274575f80fd5b82356001600160a01b038116811461028a575f80fd5b946020939093013593505050565b600181811c908216806102ac57607f821691505b6020821081036102ca57634e487b7160e01b5f52602260045260245ffd5b5091905056fea26469706673582212206157edf9621e2bde0d5600b378560a9eba7d040f0f7caa0ef070a244f6da3e4164736f6c63430008140033";

    public static final String FUNC_CONFIRMATIONDETAILS = "confirmationDetails";

    public static final String FUNC_CONFIRMATIONOWNERS = "confirmationOwners";

    public static final String FUNC_CONFIRMATIONSOFOWNER = "confirmationsOfOwner";

    public static final String FUNC_NEXTID = "nextID";

    public static final Event SPLITCONFIRMED_EVENT = new Event("SplitConfirmed", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}, new TypeReference<Utf8String>() {}));
    ;

    @Deprecated
    protected Confirmation(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Confirmation(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Confirmation(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Confirmation(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<SplitConfirmedEventResponse> getSplitConfirmedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(SPLITCONFIRMED_EVENT, transactionReceipt);
        ArrayList<SplitConfirmedEventResponse> responses = new ArrayList<SplitConfirmedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            SplitConfirmedEventResponse typedResponse = new SplitConfirmedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.client = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
            typedResponse.metadataJSON = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static SplitConfirmedEventResponse getSplitConfirmedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(SPLITCONFIRMED_EVENT, log);
        SplitConfirmedEventResponse typedResponse = new SplitConfirmedEventResponse();
        typedResponse.log = log;
        typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.client = (String) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
        typedResponse.metadataJSON = (String) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<SplitConfirmedEventResponse> splitConfirmedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getSplitConfirmedEventFromLog(log));
    }

    public Flowable<SplitConfirmedEventResponse> splitConfirmedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(SPLITCONFIRMED_EVENT));
        return splitConfirmedEventFlowable(filter);
    }

    public RemoteFunctionCall<Tuple5<byte[], String, String, String, BigInteger>> confirmationDetails(BigInteger param0) {
        final Function function = new Function(FUNC_CONFIRMATIONDETAILS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple5<byte[], String, String, String, BigInteger>>(function,
                new Callable<Tuple5<byte[], String, String, String, BigInteger>>() {
                    @Override
                    public Tuple5<byte[], String, String, String, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple5<byte[], String, String, String, BigInteger>(
                                (byte[]) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (String) results.get(3).getValue(), 
                                (BigInteger) results.get(4).getValue());
                    }
                });
    }

    public RemoteFunctionCall<String> confirmationOwners(BigInteger param0) {
        final Function function = new Function(FUNC_CONFIRMATIONOWNERS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> confirmationsOfOwner(String param0, BigInteger param1) {
        final Function function = new Function(FUNC_CONFIRMATIONSOFOWNER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0), 
                new org.web3j.abi.datatypes.generated.Uint256(param1)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> nextID() {
        final Function function = new Function(FUNC_NEXTID, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    @Deprecated
    public static Confirmation load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Confirmation(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Confirmation load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Confirmation(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Confirmation load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Confirmation(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Confirmation load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Confirmation(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Confirmation> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Confirmation.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Confirmation> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Confirmation.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<Confirmation> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Confirmation.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Confirmation> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Confirmation.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class SplitConfirmedEventResponse extends BaseEventResponse {
        public String owner;

        public String client;

        public BigInteger tokenId;

        public String metadataJSON;
    }
}
