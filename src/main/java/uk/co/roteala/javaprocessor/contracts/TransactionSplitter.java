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
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.StaticStruct;
import org.web3j.abi.datatypes.Type;
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
import org.web3j.tuples.generated.Tuple2;
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
public class TransactionSplitter extends Contract {
    public static final String BINARY = "608060405234801561000f575f80fd5b505f80546001600160a01b031916339081178255808252600160208190526040808420805460ff19169092179091555190917f994a936646fe87ffe4f1e469d3d6aa417d6b855598397f323de5b449f765f0c391a2610a43806100715f395ff3fe6080604052600436106100a5575f3560e01c80638da5cb5b116100625780638da5cb5b146101d7578063c33fb8771461020d578063d648d61e14610221578063f45ddab514610240578063f8b2cb4f14610253578063fc7e286d14610287575f80fd5b8063173825d9146100a957806329eecc7b146100ca5780632f54bf6e146101215780637065cb481461015f5780637ebeaa621461017e5780638cec0c40146101aa575b5f80fd5b3480156100b4575f80fd5b506100c86100c33660046108a4565b6102b2565b005b3480156100d5575f80fd5b506101076100e43660046108c4565b600360209081525f92835260408084209091529082529020805460019091015482565b604080519283526020830191909152015b60405180910390f35b34801561012c575f80fd5b5061014f61013b3660046108a4565b60016020525f908152604090205460ff1681565b6040519015158152602001610118565b34801561016a575f80fd5b506100c86101793660046108a4565b6103e1565b348015610189575f80fd5b5061019d6101983660046108a4565b61050e565b60405161011891906108ec565b3480156101b5575f80fd5b506101c96101c43660046108c4565b61063f565b604051908152602001610118565b3480156101e2575f80fd5b505f546101f5906001600160a01b031681565b6040516001600160a01b039091168152602001610118565b348015610218575f80fd5b506100c861066a565b34801561022c575f80fd5b5061010761023b3660046108c4565b61069a565b6100c861024e36600461093a565b610729565b34801561025e575f80fd5b506101c961026d3660046108a4565b6001600160a01b03165f9081526002602052604090205490565b348015610292575f80fd5b506101c96102a13660046108a4565b60026020525f908152604090205481565b335f9081526001602052604090205460ff166102e95760405162461bcd60e51b81526004016102e090610951565b60405180910390fd5b6001600160a01b0381166103325760405162461bcd60e51b815260206004820152601060248201526f496e76616c696420616464726573732160801b60448201526064016102e0565b6001600160a01b0381165f9081526001602052604090205460ff166103995760405162461bcd60e51b815260206004820152601760248201527f41646472657373206973206e6f7420616e206f776e657200000000000000000060448201526064016102e0565b6001600160a01b0381165f81815260016020526040808220805460ff19169055517f58619076adf5bb0943d100ef88d52d7c3fd691b19d3a9071b555b651fbf418da9190a250565b335f9081526001602052604090205460ff1661040f5760405162461bcd60e51b81526004016102e090610951565b6001600160a01b0381166104585760405162461bcd60e51b815260206004820152601060248201526f496e76616c696420616464726573732160801b60448201526064016102e0565b6001600160a01b0381165f9081526001602052604090205460ff16156104c05760405162461bcd60e51b815260206004820152601c60248201527f4164647265737320697320616c726561647920616e206f776e6572210000000060448201526064016102e0565b6001600160a01b0381165f818152600160208190526040808320805460ff1916909217909155517f994a936646fe87ffe4f1e469d3d6aa417d6b855598397f323de5b449f765f0c39190a250565b6001600160a01b0381165f908152600460205260408120546060918167ffffffffffffffff811115610542576105426109a0565b60405190808252806020026020018201604052801561058657816020015b604080518082019091525f80825260208201528152602001906001900390816105605790505b5090505f5b82811015610637576001600160a01b0385165f9081526004602052604081208054839081106105bc576105bc6109b4565b5f9182526020808320909101546001600160a01b0389168352600382526040808420828552835292839020835180850190945280548452600101549183019190915284519092508190859085908110610617576106176109b4565b60200260200101819052505050808061062f906109dc565b91505061058b565b509392505050565b6004602052815f5260405f208181548110610658575f80fd5b905f5260205f20015f91509150505481565b335f9081526001602052604090205460ff166106985760405162461bcd60e51b81526004016102e090610951565b565b6001600160a01b0382165f908152600360209081526040808320848452825280832081518083019092528054825260010154918101829052829182036107165760405162461bcd60e51b815260206004820152601160248201527044657461696c206e6f7420666f756e642160781b60448201526064016102e0565b8051602090910151909590945092505050565b5f34116107785760405162461bcd60e51b815260206004820152601c60248201527f4d7573742073656e64206120706f73697469766520616d6f756e74210000000060448201526064016102e0565b335f908152600360209081526040808320848452909152902060010154156107d95760405162461bcd60e51b815260206004820152601460248201527348617368206d75737420626520756e697175652160601b60448201526064016102e0565b335f90815260026020526040812080543492906107f79084906109f4565b9091555050604080518082018252348082526020808301858152335f81815260038452868120888252845286812086518155925160019384015581815260048452868120805493840181558152839020909101869055845192835290820185905291927fd7eda976cf4919f13172eb15bb39c64cecb7172258c741dab31d14c855eff402910160405180910390a25050565b80356001600160a01b038116811461089f575f80fd5b919050565b5f602082840312156108b4575f80fd5b6108bd82610889565b9392505050565b5f80604083850312156108d5575f80fd5b6108de83610889565b946020939093013593505050565b602080825282518282018190525f919060409081850190868401855b8281101561092d57815180518552860151868501529284019290850190600101610908565b5091979650505050505050565b5f6020828403121561094a575f80fd5b5035919050565b6020808252602f908201527f526573657276656420666f7220746865207472616e73616374696f6e2070726f60408201526e63657373696e6720656e746974792160881b606082015260800190565b634e487b7160e01b5f52604160045260245ffd5b634e487b7160e01b5f52603260045260245ffd5b634e487b7160e01b5f52601160045260245ffd5b5f600182016109ed576109ed6109c8565b5060010190565b80820180821115610a0757610a076109c8565b9291505056fea2646970667358221220268e3d4334fc94d8a2433e63ea9d371fe5c6f9cefa901f6f19530b9a092a081864736f6c63430008140033";

    public static final String FUNC_ADDOWNER = "addOwner";

    public static final String FUNC_DEPOSITS = "deposits";

    public static final String FUNC_GETALLDETAILSFORUSER = "getAllDetailsForUser";

    public static final String FUNC_GETBALANCE = "getBalance";

    public static final String FUNC_GETDETAIL = "getDetail";

    public static final String FUNC_ISOWNER = "isOwner";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_PROCESS = "process";

    public static final String FUNC_REMOVEOWNER = "removeOwner";

    public static final String FUNC_SENDTOCONTRACT = "sendToContract";

    public static final String FUNC_USERDETAILS = "userDetails";

    public static final String FUNC_USERUNIQUEHASHES = "userUniqueHashes";

    public static final Event DEPOSITED_EVENT = new Event("Deposited", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Bytes32>() {}));
    ;

    public static final Event OWNERADDED_EVENT = new Event("OwnerAdded", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}));
    ;

    public static final Event OWNERREMOVED_EVENT = new Event("OwnerRemoved", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}));
    ;

    @Deprecated
    protected TransactionSplitter(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected TransactionSplitter(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected TransactionSplitter(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected TransactionSplitter(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
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

    public RemoteFunctionCall<BigInteger> deposits(String param0) {
        final Function function = new Function(FUNC_DEPOSITS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
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

    public RemoteFunctionCall<Tuple2<BigInteger, byte[]>> getDetail(String _user, byte[] _uniqueHash) {
        final Function function = new Function(FUNC_GETDETAIL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _user), 
                new org.web3j.abi.datatypes.generated.Bytes32(_uniqueHash)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Bytes32>() {}));
        return new RemoteFunctionCall<Tuple2<BigInteger, byte[]>>(function,
                new Callable<Tuple2<BigInteger, byte[]>>() {
                    @Override
                    public Tuple2<BigInteger, byte[]> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<BigInteger, byte[]>(
                                (BigInteger) results.get(0).getValue(), 
                                (byte[]) results.get(1).getValue());
                    }
                });
    }

    public RemoteFunctionCall<Boolean> isOwner(String param0) {
        final Function function = new Function(FUNC_ISOWNER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<String> owner() {
        final Function function = new Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> process() {
        final Function function = new Function(
                FUNC_PROCESS, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> removeOwner(String ownerToRemove) {
        final Function function = new Function(
                FUNC_REMOVEOWNER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, ownerToRemove)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> sendToContract(byte[] _uniqueHash, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_SENDTOCONTRACT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(_uniqueHash)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<Tuple2<BigInteger, byte[]>> userDetails(String param0, byte[] param1) {
        final Function function = new Function(FUNC_USERDETAILS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0), 
                new org.web3j.abi.datatypes.generated.Bytes32(param1)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Bytes32>() {}));
        return new RemoteFunctionCall<Tuple2<BigInteger, byte[]>>(function,
                new Callable<Tuple2<BigInteger, byte[]>>() {
                    @Override
                    public Tuple2<BigInteger, byte[]> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<BigInteger, byte[]>(
                                (BigInteger) results.get(0).getValue(), 
                                (byte[]) results.get(1).getValue());
                    }
                });
    }

    public RemoteFunctionCall<byte[]> userUniqueHashes(String param0, BigInteger param1) {
        final Function function = new Function(FUNC_USERUNIQUEHASHES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0), 
                new org.web3j.abi.datatypes.generated.Uint256(param1)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    @Deprecated
    public static TransactionSplitter load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new TransactionSplitter(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static TransactionSplitter load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new TransactionSplitter(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static TransactionSplitter load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new TransactionSplitter(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static TransactionSplitter load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new TransactionSplitter(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<TransactionSplitter> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(TransactionSplitter.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<TransactionSplitter> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(TransactionSplitter.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<TransactionSplitter> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(TransactionSplitter.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<TransactionSplitter> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(TransactionSplitter.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class Details extends StaticStruct {
        public BigInteger amount;

        public byte[] uniqueHash;

        public Details(BigInteger amount, byte[] uniqueHash) {
            super(new org.web3j.abi.datatypes.generated.Uint256(amount), 
                    new org.web3j.abi.datatypes.generated.Bytes32(uniqueHash));
            this.amount = amount;
            this.uniqueHash = uniqueHash;
        }

        public Details(Uint256 amount, Bytes32 uniqueHash) {
            super(amount, uniqueHash);
            this.amount = amount.getValue();
            this.uniqueHash = uniqueHash.getValue();
        }
    }

    public static class DepositedEventResponse extends BaseEventResponse {
        public String from;

        public BigInteger value;

        public byte[] uniqueHash;
    }

    public static class OwnerAddedEventResponse extends BaseEventResponse {
        public String newOwner;
    }

    public static class OwnerRemovedEventResponse extends BaseEventResponse {
        public String removedOwner;
    }
}
