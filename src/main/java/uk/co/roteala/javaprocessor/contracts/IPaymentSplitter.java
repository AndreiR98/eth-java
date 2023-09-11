package uk.co.roteala.javaprocessor.contracts;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.DynamicStruct;
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
import org.web3j.tuples.generated.Tuple3;
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
public class IPaymentSplitter extends Contract {
    public static final String BINARY = "";

    public static final String FUNC_ADDOWNER = "addOwner";

    public static final String FUNC_GETALLDETAILSFORUSER = "getAllDetailsForUser";

    public static final String FUNC_GETBALANCE = "getBalance";

    public static final String FUNC_GETDETAIL = "getDetail";

    public static final String FUNC_PROCESSPAYMENT = "processPayment";

    public static final String FUNC_PROCESSSPLIT = "processSplit";

    public static final String FUNC_REMOVEOWNER = "removeOwner";

    public static final Event DEPOSITED_EVENT = new Event("Deposited", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event OWNERADDED_EVENT = new Event("OwnerAdded", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}));
    ;

    public static final Event OWNERREMOVED_EVENT = new Event("OwnerRemoved", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}));
    ;

    @Deprecated
    protected IPaymentSplitter(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected IPaymentSplitter(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected IPaymentSplitter(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected IPaymentSplitter(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<DepositedEventResponse> getDepositedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(DEPOSITED_EVENT, transactionReceipt);
        ArrayList<DepositedEventResponse> responses = new ArrayList<DepositedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            DepositedEventResponse typedResponse = new DepositedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.uniqueHash = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.data = (String) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static DepositedEventResponse getDepositedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(DEPOSITED_EVENT, log);
        DepositedEventResponse typedResponse = new DepositedEventResponse();
        typedResponse.log = log;
        typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.uniqueHash = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.data = (String) eventValues.getNonIndexedValues().get(2).getValue();
        return typedResponse;
    }

    public Flowable<DepositedEventResponse> depositedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getDepositedEventFromLog(log));
    }

    public Flowable<DepositedEventResponse> depositedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(DEPOSITED_EVENT));
        return depositedEventFlowable(filter);
    }

    public static List<OwnerAddedEventResponse> getOwnerAddedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(OWNERADDED_EVENT, transactionReceipt);
        ArrayList<OwnerAddedEventResponse> responses = new ArrayList<OwnerAddedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OwnerAddedEventResponse typedResponse = new OwnerAddedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.newOwner = (String) eventValues.getIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static OwnerAddedEventResponse getOwnerAddedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(OWNERADDED_EVENT, log);
        OwnerAddedEventResponse typedResponse = new OwnerAddedEventResponse();
        typedResponse.log = log;
        typedResponse.newOwner = (String) eventValues.getIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<OwnerAddedEventResponse> ownerAddedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getOwnerAddedEventFromLog(log));
    }

    public Flowable<OwnerAddedEventResponse> ownerAddedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(OWNERADDED_EVENT));
        return ownerAddedEventFlowable(filter);
    }

    public static List<OwnerRemovedEventResponse> getOwnerRemovedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(OWNERREMOVED_EVENT, transactionReceipt);
        ArrayList<OwnerRemovedEventResponse> responses = new ArrayList<OwnerRemovedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OwnerRemovedEventResponse typedResponse = new OwnerRemovedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.removedOwner = (String) eventValues.getIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static OwnerRemovedEventResponse getOwnerRemovedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(OWNERREMOVED_EVENT, log);
        OwnerRemovedEventResponse typedResponse = new OwnerRemovedEventResponse();
        typedResponse.log = log;
        typedResponse.removedOwner = (String) eventValues.getIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<OwnerRemovedEventResponse> ownerRemovedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getOwnerRemovedEventFromLog(log));
    }

    public Flowable<OwnerRemovedEventResponse> ownerRemovedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(OWNERREMOVED_EVENT));
        return ownerRemovedEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> addOwner(String newOwner) {
        final Function function = new Function(
                FUNC_ADDOWNER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, newOwner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<List> getAllDetailsForUser(String _user) {
        final Function function = new Function(FUNC_GETALLDETAILSFORUSER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _user)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Details>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteFunctionCall<BigInteger> getBalance(String account) {
        final Function function = new Function(FUNC_GETBALANCE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Tuple3<BigInteger, byte[], String>> getDetail(String _user, byte[] _uniqueHash) {
        final Function function = new Function(FUNC_GETDETAIL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _user), 
                new org.web3j.abi.datatypes.generated.Bytes32(_uniqueHash)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Utf8String>() {}));
        return new RemoteFunctionCall<Tuple3<BigInteger, byte[], String>>(function,
                new Callable<Tuple3<BigInteger, byte[], String>>() {
                    @Override
                    public Tuple3<BigInteger, byte[], String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<BigInteger, byte[], String>(
                                (BigInteger) results.get(0).getValue(), 
                                (byte[]) results.get(1).getValue(), 
                                (String) results.get(2).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> processPayment(byte[] _uniqueHash, String _data, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_PROCESSPAYMENT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(_uniqueHash), 
                new org.web3j.abi.datatypes.Utf8String(_data)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> processSplit(String _to, String _from, byte[] _uniqueHash, String _dataJSON, BigInteger _amount, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_PROCESSSPLIT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _to), 
                new org.web3j.abi.datatypes.Address(160, _from), 
                new org.web3j.abi.datatypes.generated.Bytes32(_uniqueHash), 
                new org.web3j.abi.datatypes.Utf8String(_dataJSON), 
                new org.web3j.abi.datatypes.generated.Uint256(_amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> removeOwner(String ownerToRemove) {
        final Function function = new Function(
                FUNC_REMOVEOWNER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, ownerToRemove)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static IPaymentSplitter load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new IPaymentSplitter(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static IPaymentSplitter load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new IPaymentSplitter(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static IPaymentSplitter load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new IPaymentSplitter(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static IPaymentSplitter load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new IPaymentSplitter(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<IPaymentSplitter> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(IPaymentSplitter.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<IPaymentSplitter> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(IPaymentSplitter.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<IPaymentSplitter> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(IPaymentSplitter.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<IPaymentSplitter> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(IPaymentSplitter.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class Details extends DynamicStruct {
        public BigInteger amount;

        public byte[] uniqueHash;

        public String data;

        public Boolean processed;

        public BigInteger splitsTotal;

        public BigInteger splitsProcessed;

        public Details(BigInteger amount, byte[] uniqueHash, String data, Boolean processed, BigInteger splitsTotal, BigInteger splitsProcessed) {
            super(new org.web3j.abi.datatypes.generated.Uint256(amount), 
                    new org.web3j.abi.datatypes.generated.Bytes32(uniqueHash), 
                    new org.web3j.abi.datatypes.Utf8String(data), 
                    new org.web3j.abi.datatypes.Bool(processed), 
                    new org.web3j.abi.datatypes.generated.Uint256(splitsTotal), 
                    new org.web3j.abi.datatypes.generated.Uint256(splitsProcessed));
            this.amount = amount;
            this.uniqueHash = uniqueHash;
            this.data = data;
            this.processed = processed;
            this.splitsTotal = splitsTotal;
            this.splitsProcessed = splitsProcessed;
        }

        public Details(Uint256 amount, Bytes32 uniqueHash, Utf8String data, Bool processed, Uint256 splitsTotal, Uint256 splitsProcessed) {
            super(amount, uniqueHash, data, processed, splitsTotal, splitsProcessed);
            this.amount = amount.getValue();
            this.uniqueHash = uniqueHash.getValue();
            this.data = data.getValue();
            this.processed = processed.getValue();
            this.splitsTotal = splitsTotal.getValue();
            this.splitsProcessed = splitsProcessed.getValue();
        }
    }

    public static class DepositedEventResponse extends BaseEventResponse {
        public String from;

        public BigInteger value;

        public byte[] uniqueHash;

        public String data;
    }

    public static class OwnerAddedEventResponse extends BaseEventResponse {
        public String newOwner;
    }

    public static class OwnerRemovedEventResponse extends BaseEventResponse {
        public String removedOwner;
    }
}
