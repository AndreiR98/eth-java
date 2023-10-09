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
public class IBillingPayment extends Contract {
    public static final String BINARY = "";

    public static final String FUNC_CREATEBILL = "createBill";

    public static final String FUNC_GETBILLSFORMERCHANT = "getBillsForMerchant";

    public static final String FUNC_GETRECEIPTS = "getReceipts";

    public static final String FUNC_PAY = "pay";

    public static final Event BILLGENERATED_EVENT = new Event("BillGenerated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<BillModel>() {}));
    ;

    public static final Event BILLPROCESSED_EVENT = new Event("BillProcessed", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
    ;

    public static final Event PAYMENTRECEIVED_EVENT = new Event("PaymentReceived", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
    ;

    public static final Event RECEIPTGENERATED_EVENT = new Event("ReceiptGenerated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
    ;

    @Deprecated
    protected IBillingPayment(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected IBillingPayment(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected IBillingPayment(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected IBillingPayment(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
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

    public RemoteFunctionCall<TransactionReceipt> pay(String _billUniqueId, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_PAY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_billUniqueId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    @Deprecated
    public static IBillingPayment load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new IBillingPayment(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static IBillingPayment load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new IBillingPayment(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static IBillingPayment load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new IBillingPayment(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static IBillingPayment load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new IBillingPayment(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<IBillingPayment> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(IBillingPayment.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<IBillingPayment> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(IBillingPayment.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<IBillingPayment> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(IBillingPayment.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<IBillingPayment> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(IBillingPayment.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
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

    public static class PaymentReceivedEventResponse extends BaseEventResponse {
        public String _paymentUniqueId;
    }

    public static class ReceiptGeneratedEventResponse extends BaseEventResponse {
        public String _receiptUniqueId;
    }
}
