package uk.co.roteala.javaprocessor.contracts;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;
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
public class BillingPayment extends Contract {
    public static final String BINARY = "608060405234801561000f575f80fd5b5061001933610030565b600580546001600160a01b0319163317905561007f565b5f80546001600160a01b038381166001600160a01b0319831681178455604051919092169283917f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e09190a35050565b6111668061008c5f395ff3fe60806040526004361061006e575f3560e01c8063903bcf151161004c578063903bcf15146100c4578063de920759146100e3578063f27021271461010f578063f2fde38b1461013c575f80fd5b80632b66d72e14610072578063715018a6146100855780638da5cb5b14610099575b5f80fd5b610083610080366004610b9d565b50565b005b348015610090575f80fd5b5061008361015b565b3480156100a4575f80fd5b505f546040516001600160a01b0390911681526020015b60405180910390f35b3480156100cf575f80fd5b506100836100de366004610bf2565b61016e565b3480156100ee575f80fd5b506101026100fd366004610ccd565b610399565b6040516100bb9190610e7f565b34801561011a575f80fd5b5061012f610129366004610ccd565b50606090565b6040516100bb9190610edf565b348015610147575f80fd5b50610083610156366004610ccd565b610927565b6101636109a2565b61016c5f6109fb565b565b6101766109a2565b5f6040518061018001604052808b81526020018a81526020018981526020018881526020018781526020015f81526020015f151581526020018681526020015f67ffffffffffffffff8111156101ce576101ce610b00565b60405190808252806020026020018201604052801561020157816020015b60608152602001906001900390816101ec5790505b508152602001858152602001846001600160a01b031681526020018381525090508060028b6040516102339190610f6e565b90815260405190819003602001902081518190610250908261100f565b506020820151600182015560408201516002820155606082015160038201556080820151600482015560a0820151600582015560c082015160068201805460ff191691151591909117905560e082015160078201906102af908261100f565b5061010082015180516102cc916008840191602090910190610a4a565b5061012082015160098201906102e2908261100f565b50610140820151600a820180546001600160a01b0319166001600160a01b03909216919091179055610160820151600b82019061031f908261100f565b5050506001600160a01b0383165f908152600460209081526040822080546001810182559083529120016103538b8261100f565b507f136a72457804b46ae585d823401c770ac04029f14b544a2dae819f63520d467b8a826040516103859291906110cb565b60405180910390a150505050505050505050565b6001600160a01b0381165f908152600460209081526040808320805482518185028101850190935280835260609493849084015b82821015610475578382905f5260205f200180546103ea90610f89565b80601f016020809104026020016040519081016040528092919081815260200182805461041690610f89565b80156104615780601f1061043857610100808354040283529160200191610461565b820191905f5260205f20905b81548152906001019060200180831161044457829003601f168201915b5050505050815260200190600101906103cd565b5050505090505f815167ffffffffffffffff81111561049657610496610b00565b60405190808252806020026020018201604052801561052f57816020015b61051c604051806101800160405280606081526020015f81526020015f81526020015f81526020015f81526020015f81526020015f151581526020016060815260200160608152602001606081526020015f6001600160a01b03168152602001606081525090565b8152602001906001900390816104b45790505b5090505f5b825181101561091f576002838281518110610551576105516110f8565b60200260200101516040516105669190610f6e565b9081526020016040518091039020604051806101800160405290815f8201805461058f90610f89565b80601f01602080910402602001604051908101604052809291908181526020018280546105bb90610f89565b80156106065780601f106105dd57610100808354040283529160200191610606565b820191905f5260205f20905b8154815290600101906020018083116105e957829003601f168201915b505050918352505060018201546020820152600282015460408201526003820154606082015260048201546080820152600582015460a0820152600682015460ff16151560c082015260078201805460e09092019161066490610f89565b80601f016020809104026020016040519081016040528092919081815260200182805461069090610f89565b80156106db5780601f106106b2576101008083540402835291602001916106db565b820191905f5260205f20905b8154815290600101906020018083116106be57829003601f168201915b5050505050815260200160088201805480602002602001604051908101604052809291908181526020015f905b828210156107b0578382905f5260205f2001805461072590610f89565b80601f016020809104026020016040519081016040528092919081815260200182805461075190610f89565b801561079c5780601f106107735761010080835404028352916020019161079c565b820191905f5260205f20905b81548152906001019060200180831161077f57829003601f168201915b505050505081526020019060010190610708565b5050505081526020016009820180546107c890610f89565b80601f01602080910402602001604051908101604052809291908181526020018280546107f490610f89565b801561083f5780601f106108165761010080835404028352916020019161083f565b820191905f5260205f20905b81548152906001019060200180831161082257829003601f168201915b5050509183525050600a8201546001600160a01b03166020820152600b8201805460409092019161086f90610f89565b80601f016020809104026020016040519081016040528092919081815260200182805461089b90610f89565b80156108e65780601f106108bd576101008083540402835291602001916108e6565b820191905f5260205f20905b8154815290600101906020018083116108c957829003601f168201915b505050505081525050828281518110610901576109016110f8565b602002602001018190525080806109179061110c565b915050610534565b509392505050565b61092f6109a2565b6001600160a01b0381166109995760405162461bcd60e51b815260206004820152602660248201527f4f776e61626c653a206e6577206f776e657220697320746865207a65726f206160448201526564647265737360d01b60648201526084015b60405180910390fd5b610080816109fb565b5f546001600160a01b0316331461016c5760405162461bcd60e51b815260206004820181905260248201527f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e65726044820152606401610990565b5f80546001600160a01b038381166001600160a01b0319831681178455604051919092169283917f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e09190a35050565b828054828255905f5260205f20908101928215610a8e579160200282015b82811115610a8e5782518290610a7e908261100f565b5091602001919060010190610a68565b50610a9a929150610a9e565b5090565b80821115610a9a575f610ab18282610aba565b50600101610a9e565b508054610ac690610f89565b5f825580601f10610ad5575050565b601f0160209004905f5260205f209081019061008091905b80821115610a9a575f8155600101610aed565b634e487b7160e01b5f52604160045260245ffd5b5f82601f830112610b23575f80fd5b813567ffffffffffffffff80821115610b3e57610b3e610b00565b604051601f8301601f19908116603f01168101908282118183101715610b6657610b66610b00565b81604052838152866020858801011115610b7e575f80fd5b836020870160208301375f602085830101528094505050505092915050565b5f60208284031215610bad575f80fd5b813567ffffffffffffffff811115610bc3575f80fd5b610bcf84828501610b14565b949350505050565b80356001600160a01b0381168114610bed575f80fd5b919050565b5f805f805f805f805f6101208a8c031215610c0b575f80fd5b893567ffffffffffffffff80821115610c22575f80fd5b610c2e8d838e01610b14565b9a5060208c0135995060408c0135985060608c0135975060808c0135965060a08c0135915080821115610c5f575f80fd5b610c6b8d838e01610b14565b955060c08c0135915080821115610c80575f80fd5b610c8c8d838e01610b14565b9450610c9a60e08d01610bd7565b93506101008c0135915080821115610cb0575f80fd5b50610cbd8c828d01610b14565b9150509295985092959850929598565b5f60208284031215610cdd575f80fd5b610ce682610bd7565b9392505050565b5f5b83811015610d07578181015183820152602001610cef565b50505f910152565b5f8151808452610d26816020860160208601610ced565b601f01601f19169290920160200192915050565b5f82825180855260208086019550808260051b8401018186015f5b84811015610d8357601f19868403018952610d71838351610d0f565b98840198925090830190600101610d55565b5090979650505050505050565b5f6101808251818552610da582860182610d0f565b9150506020830151602085015260408301516040850152606083015160608501526080830151608085015260a083015160a085015260c0830151610ded60c086018215159052565b5060e083015184820360e0860152610e058282610d0f565b9150506101008084015185830382870152610e208382610d3a565b925050506101208084015185830382870152610e3c8382610d0f565b9250505061014080840151610e5b828701826001600160a01b03169052565b50506101608084015185830382870152610e758382610d0f565b9695505050505050565b5f602080830181845280855180835260408601915060408160051b87010192508387015f5b82811015610ed257603f19888603018452610ec0858351610d90565b94509285019290850190600101610ea4565b5092979650505050505050565b5f6020808301818452808551808352604092508286019150828160051b8701018488015f5b83811015610f6057603f19898403018552815160608151818652610f2a82870182610d0f565b915050888201518582038a870152610f428282610d0f565b92890151958901959095525094870194925090860190600101610f04565b509098975050505050505050565b5f8251610f7f818460208701610ced565b9190910192915050565b600181811c90821680610f9d57607f821691505b602082108103610fbb57634e487b7160e01b5f52602260045260245ffd5b50919050565b601f82111561100a575f81815260208120601f850160051c81016020861015610fe75750805b601f850160051c820191505b8181101561100657828155600101610ff3565b5050505b505050565b815167ffffffffffffffff81111561102957611029610b00565b61103d816110378454610f89565b84610fc1565b602080601f831160018114611070575f84156110595750858301515b5f19600386901b1c1916600185901b178555611006565b5f85815260208120601f198616915b8281101561109e5788860151825594840194600190910190840161107f565b50858210156110bb57878501515f19600388901b60f8161c191681555b5050505050600190811b01905550565b604081525f6110dd6040830185610d0f565b82810360208401526110ef8185610d90565b95945050505050565b634e487b7160e01b5f52603260045260245ffd5b5f6001820161112957634e487b7160e01b5f52601160045260245ffd5b506001019056fea2646970667358221220d77ca10c67d4d843b11ab7ea430c8c148cfab92a8508fc00fe1ef74ce5ba020a64736f6c63430008140033";

    public static final String FUNC_CREATEBILL = "createBill";

    public static final String FUNC_GETBILLSFORMERCHANT = "getBillsForMerchant";

    public static final String FUNC_GETRECEIPTS = "getReceipts";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_PAY = "pay";

    public static final String FUNC_RENOUNCEOWNERSHIP = "renounceOwnership";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final Event BILLGENERATED_EVENT = new Event("BillGenerated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<BillModel>() {}));
    ;

    public static final Event BILLPROCESSED_EVENT = new Event("BillProcessed", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
    ;

    public static final Event OWNERSHIPTRANSFERRED_EVENT = new Event("OwnershipTransferred", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event PAYMENTRECEIVED_EVENT = new Event("PaymentReceived", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
    ;

    public static final Event RECEIPTGENERATED_EVENT = new Event("ReceiptGenerated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
    ;

    @Deprecated
    protected BillingPayment(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected BillingPayment(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected BillingPayment(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected BillingPayment(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<BillGeneratedEventResponse> getBillGeneratedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(BILLGENERATED_EVENT, transactionReceipt);
        ArrayList<BillGeneratedEventResponse> responses = new ArrayList<BillGeneratedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            BillGeneratedEventResponse typedResponse = new BillGeneratedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._billUniqueId = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._bill = (BillModel) eventValues.getNonIndexedValues().get(1);
            responses.add(typedResponse);
        }
        return responses;
    }

    public static BillGeneratedEventResponse getBillGeneratedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(BILLGENERATED_EVENT, log);
        BillGeneratedEventResponse typedResponse = new BillGeneratedEventResponse();
        typedResponse.log = log;
        typedResponse._billUniqueId = (String) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse._bill = (BillModel) eventValues.getNonIndexedValues().get(1);
        return typedResponse;
    }

    public Flowable<BillGeneratedEventResponse> billGeneratedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getBillGeneratedEventFromLog(log));
    }

    public Flowable<BillGeneratedEventResponse> billGeneratedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(BILLGENERATED_EVENT));
        return billGeneratedEventFlowable(filter);
    }

    public static List<BillProcessedEventResponse> getBillProcessedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(BILLPROCESSED_EVENT, transactionReceipt);
        ArrayList<BillProcessedEventResponse> responses = new ArrayList<BillProcessedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            BillProcessedEventResponse typedResponse = new BillProcessedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._billUniqueId = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static BillProcessedEventResponse getBillProcessedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(BILLPROCESSED_EVENT, log);
        BillProcessedEventResponse typedResponse = new BillProcessedEventResponse();
        typedResponse.log = log;
        typedResponse._billUniqueId = (String) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<BillProcessedEventResponse> billProcessedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getBillProcessedEventFromLog(log));
    }

    public Flowable<BillProcessedEventResponse> billProcessedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(BILLPROCESSED_EVENT));
        return billProcessedEventFlowable(filter);
    }

    public static List<OwnershipTransferredEventResponse> getOwnershipTransferredEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, transactionReceipt);
        ArrayList<OwnershipTransferredEventResponse> responses = new ArrayList<OwnershipTransferredEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static OwnershipTransferredEventResponse getOwnershipTransferredEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, log);
        OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
        typedResponse.log = log;
        typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getOwnershipTransferredEventFromLog(log));
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(OWNERSHIPTRANSFERRED_EVENT));
        return ownershipTransferredEventFlowable(filter);
    }

    public static List<PaymentReceivedEventResponse> getPaymentReceivedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(PAYMENTRECEIVED_EVENT, transactionReceipt);
        ArrayList<PaymentReceivedEventResponse> responses = new ArrayList<PaymentReceivedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            PaymentReceivedEventResponse typedResponse = new PaymentReceivedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._paymentUniqueId = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static PaymentReceivedEventResponse getPaymentReceivedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(PAYMENTRECEIVED_EVENT, log);
        PaymentReceivedEventResponse typedResponse = new PaymentReceivedEventResponse();
        typedResponse.log = log;
        typedResponse._paymentUniqueId = (String) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<PaymentReceivedEventResponse> paymentReceivedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getPaymentReceivedEventFromLog(log));
    }

    public Flowable<PaymentReceivedEventResponse> paymentReceivedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PAYMENTRECEIVED_EVENT));
        return paymentReceivedEventFlowable(filter);
    }

    public static List<ReceiptGeneratedEventResponse> getReceiptGeneratedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(RECEIPTGENERATED_EVENT, transactionReceipt);
        ArrayList<ReceiptGeneratedEventResponse> responses = new ArrayList<ReceiptGeneratedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ReceiptGeneratedEventResponse typedResponse = new ReceiptGeneratedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._receiptUniqueId = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static ReceiptGeneratedEventResponse getReceiptGeneratedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(RECEIPTGENERATED_EVENT, log);
        ReceiptGeneratedEventResponse typedResponse = new ReceiptGeneratedEventResponse();
        typedResponse.log = log;
        typedResponse._receiptUniqueId = (String) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<ReceiptGeneratedEventResponse> receiptGeneratedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getReceiptGeneratedEventFromLog(log));
    }

    public Flowable<ReceiptGeneratedEventResponse> receiptGeneratedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(RECEIPTGENERATED_EVENT));
        return receiptGeneratedEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> createBill(String _billUniqueId, BigInteger _billAmount, BigInteger _txAmount, BigInteger _tipAmount, BigInteger _revenueAmount, String _processDate, String _billTimeStamp, String _merchantAddress, String _metaData) {
        final Function function = new Function(
                FUNC_CREATEBILL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_billUniqueId), 
                new org.web3j.abi.datatypes.generated.Uint256(_billAmount), 
                new org.web3j.abi.datatypes.generated.Uint256(_txAmount), 
                new org.web3j.abi.datatypes.generated.Uint256(_tipAmount), 
                new org.web3j.abi.datatypes.generated.Uint256(_revenueAmount), 
                new org.web3j.abi.datatypes.Utf8String(_processDate), 
                new org.web3j.abi.datatypes.Utf8String(_billTimeStamp), 
                new org.web3j.abi.datatypes.Address(160, _merchantAddress), 
                new org.web3j.abi.datatypes.Utf8String(_metaData)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<List> getBillsForMerchant(String _merchantAddress) {
        final Function function = new Function(FUNC_GETBILLSFORMERCHANT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _merchantAddress)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<BillModel>>() {}));
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

    public RemoteFunctionCall<List> getReceipts(String _address) {
        final Function function = new Function(FUNC_GETRECEIPTS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _address)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<ReceiptModel>>() {}));
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

    public RemoteFunctionCall<String> owner() {
        final Function function = new Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> pay(String _billUniqueId, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_PAY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_billUniqueId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> renounceOwnership() {
        final Function function = new Function(
                FUNC_RENOUNCEOWNERSHIP, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transferOwnership(String newOwner) {
        final Function function = new Function(
                FUNC_TRANSFEROWNERSHIP, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, newOwner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static BillingPayment load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new BillingPayment(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static BillingPayment load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new BillingPayment(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static BillingPayment load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new BillingPayment(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static BillingPayment load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new BillingPayment(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<BillingPayment> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(BillingPayment.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<BillingPayment> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(BillingPayment.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<BillingPayment> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(BillingPayment.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<BillingPayment> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(BillingPayment.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class BillModel extends DynamicStruct {
        public String billUniqueId;

        public BigInteger billAmount;

        public BigInteger txAmount;

        public BigInteger tipAmount;

        public BigInteger revenueAmount;

        public BigInteger payedAmount;

        public Boolean processed;

        public String processDate;

        public List<String> payments;

        public String billTimeStamp;

        public String merchantAddress;

        public String metaData;

        public BillModel(String billUniqueId, BigInteger billAmount, BigInteger txAmount, BigInteger tipAmount, BigInteger revenueAmount, BigInteger payedAmount, Boolean processed, String processDate, List<String> payments, String billTimeStamp, String merchantAddress, String metaData) {
            super(new org.web3j.abi.datatypes.Utf8String(billUniqueId), 
                    new org.web3j.abi.datatypes.generated.Uint256(billAmount), 
                    new org.web3j.abi.datatypes.generated.Uint256(txAmount), 
                    new org.web3j.abi.datatypes.generated.Uint256(tipAmount), 
                    new org.web3j.abi.datatypes.generated.Uint256(revenueAmount), 
                    new org.web3j.abi.datatypes.generated.Uint256(payedAmount), 
                    new org.web3j.abi.datatypes.Bool(processed), 
                    new org.web3j.abi.datatypes.Utf8String(processDate), 
                    new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Utf8String>(
                            org.web3j.abi.datatypes.Utf8String.class,
                            org.web3j.abi.Utils.typeMap(payments, org.web3j.abi.datatypes.Utf8String.class)), 
                    new org.web3j.abi.datatypes.Utf8String(billTimeStamp), 
                    new org.web3j.abi.datatypes.Address(160, merchantAddress), 
                    new org.web3j.abi.datatypes.Utf8String(metaData));
            this.billUniqueId = billUniqueId;
            this.billAmount = billAmount;
            this.txAmount = txAmount;
            this.tipAmount = tipAmount;
            this.revenueAmount = revenueAmount;
            this.payedAmount = payedAmount;
            this.processed = processed;
            this.processDate = processDate;
            this.payments = payments;
            this.billTimeStamp = billTimeStamp;
            this.merchantAddress = merchantAddress;
            this.metaData = metaData;
        }

        public BillModel(Utf8String billUniqueId, Uint256 billAmount, Uint256 txAmount, Uint256 tipAmount, Uint256 revenueAmount, Uint256 payedAmount, Bool processed, Utf8String processDate, DynamicArray<Utf8String> payments, Utf8String billTimeStamp, Address merchantAddress, Utf8String metaData) {
            super(billUniqueId, billAmount, txAmount, tipAmount, revenueAmount, payedAmount, processed, processDate, payments, billTimeStamp, merchantAddress, metaData);
            this.billUniqueId = billUniqueId.getValue();
            this.billAmount = billAmount.getValue();
            this.txAmount = txAmount.getValue();
            this.tipAmount = tipAmount.getValue();
            this.revenueAmount = revenueAmount.getValue();
            this.payedAmount = payedAmount.getValue();
            this.processed = processed.getValue();
            this.processDate = processDate.getValue();
            this.payments = payments.getValue().stream().map(v -> v.getValue()).collect(Collectors.toList());
            this.billTimeStamp = billTimeStamp.getValue();
            this.merchantAddress = merchantAddress.getValue();
            this.metaData = metaData.getValue();
        }
    }

    public static class ReceiptModel extends DynamicStruct {
        public String uniqueReceiptId;

        public String billUniqueId;

        public BigInteger amount;

        public ReceiptModel(String uniqueReceiptId, String billUniqueId, BigInteger amount) {
            super(new org.web3j.abi.datatypes.Utf8String(uniqueReceiptId), 
                    new org.web3j.abi.datatypes.Utf8String(billUniqueId), 
                    new org.web3j.abi.datatypes.generated.Uint256(amount));
            this.uniqueReceiptId = uniqueReceiptId;
            this.billUniqueId = billUniqueId;
            this.amount = amount;
        }

        public ReceiptModel(Utf8String uniqueReceiptId, Utf8String billUniqueId, Uint256 amount) {
            super(uniqueReceiptId, billUniqueId, amount);
            this.uniqueReceiptId = uniqueReceiptId.getValue();
            this.billUniqueId = billUniqueId.getValue();
            this.amount = amount.getValue();
        }
    }

    public static class BillGeneratedEventResponse extends BaseEventResponse {
        public String _billUniqueId;

        public BillModel _bill;
    }

    public static class BillProcessedEventResponse extends BaseEventResponse {
        public String _billUniqueId;
    }

    public static class OwnershipTransferredEventResponse extends BaseEventResponse {
        public String previousOwner;

        public String newOwner;
    }

    public static class PaymentReceivedEventResponse extends BaseEventResponse {
        public String _paymentUniqueId;
    }

    public static class ReceiptGeneratedEventResponse extends BaseEventResponse {
        public String _receiptUniqueId;
    }
}