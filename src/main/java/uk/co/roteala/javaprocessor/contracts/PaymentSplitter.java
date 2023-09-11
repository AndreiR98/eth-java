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
import org.web3j.tuples.generated.Tuple5;
import org.web3j.tuples.generated.Tuple6;
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
public class PaymentSplitter extends Contract {
    public static final String BINARY = "608060405234801561000f575f80fd5b5061001933610071565b600980546001600160a01b031916339081179091555f818152600a6020526040808220805460ff19166001179055517f994a936646fe87ffe4f1e469d3d6aa417d6b855598397f323de5b449f765f0c39190a26100c2565b600880546001600160a01b038381166001600160a01b0319831681179093556040519116919082907f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e0905f90a35050565b61199f806100cf5f395ff3fe608060405260043610610131575f3560e01c80637ebeaa62116100a8578063b72ef4c81161006d578063b72ef4c814610377578063d648d61e146103ab578063f2fde38b146103d9578063f8a14f46146103f8578063f8b2cb4f1461042c578063fc7e286d14610460575f80fd5b80637ebeaa62146102ba5780638cec0c40146102e65780638da5cb5b146103055780638f6ec04414610336578063a6d4c53214610364575f80fd5b80632f54bf6e116100f95780632f54bf6e146101f657806339adad541461023457806368c179d0146102535780637065cb4814610272578063715018a61461029157806375794a3c146102a5575f80fd5b8063081bf64a14610135578063173825d91461016e5780631e96917d1461018f57806329eecc7b146101b25780632f412f98146101e3575b5f80fd5b348015610140575f80fd5b5061015461014f366004611432565b61048b565b60405161016595949392919061148c565b60405180910390f35b348015610179575f80fd5b5061018d6101883660046114e3565b61054c565b005b34801561019a575f80fd5b506101a460075481565b604051908152602001610165565b3480156101bd575f80fd5b506101d16101cc366004611503565b610651565b6040516101659695949392919061152b565b61018d6101f1366004611604565b61071c565b348015610201575f80fd5b506102246102103660046114e3565b600a6020525f908152604090205460ff1681565b6040519015158152602001610165565b34801561023f575f80fd5b506101a461024e366004611503565b6108f1565b34801561025e575f80fd5b506101a461026d366004611503565b61091c565b34801561027d575f80fd5b5061018d61028c3660046114e3565b610935565b34801561029c575f80fd5b5061018d610a39565b3480156102b0575f80fd5b506101a460035481565b3480156102c5575f80fd5b506102d96102d43660046114e3565b610a4c565b6040516101659190611648565b3480156102f1575f80fd5b506101a4610300366004611503565b610c5f565b348015610310575f80fd5b506008546001600160a01b03165b6040516001600160a01b039091168152602001610165565b348015610341575f80fd5b50610355610350366004611432565b610c78565b604051610165939291906116e7565b61018d610372366004611717565b610d29565b348015610382575f80fd5b5061031e610391366004611432565b60056020525f90815260409020546001600160a01b031681565b3480156103b6575f80fd5b506103ca6103c5366004611503565b610f69565b60405161016593929190611782565b3480156103e4575f80fd5b5061018d6103f33660046114e3565b6110d8565b348015610403575f80fd5b5061031e610412366004611432565b60016020525f90815260409020546001600160a01b031681565b348015610437575f80fd5b506101a46104463660046114e3565b6001600160a01b03165f908152600b602052604090205490565b34801561046b575f80fd5b506101a461047a3660046114e3565b600b6020525f908152604090205481565b60046020525f9081526040902080546001820180549192916104ac906117a9565b80601f01602080910402602001604051908101604052809291908181526020018280546104d8906117a9565b80156105235780601f106104fa57610100808354040283529160200191610523565b820191905f5260205f20905b81548152906001019060200180831161050657829003601f168201915b505050506002830154600384015460049094015492936001600160a01b03918216939116915085565b610554611151565b6001600160a01b0381166105a25760405162461bcd60e51b815260206004820152601060248201526f496e76616c696420616464726573732160801b60448201526064015b60405180910390fd5b6001600160a01b0381165f908152600a602052604090205460ff166106095760405162461bcd60e51b815260206004820152601760248201527f41646472657373206973206e6f7420616e206f776e65720000000000000000006044820152606401610599565b6001600160a01b0381165f818152600a6020526040808220805460ff19169055517f58619076adf5bb0943d100ef88d52d7c3fd691b19d3a9071b555b651fbf418da9190a250565b600c60209081525f928352604080842090915290825290208054600182015460028301805492939192610683906117a9565b80601f01602080910402602001604051908101604052809291908181526020018280546106af906117a9565b80156106fa5780601f106106d1576101008083540402835291602001916106fa565b820191905f5260205f20905b8154815290600101906020018083116106dd57829003601f168201915b5050505060038301546004840154600590940154929360ff9091169290915086565b5f341161076b5760405162461bcd60e51b815260206004820152601c60248201527f4d7573742073656e64206120706f73697469766520616d6f756e7421000000006044820152606401610599565b335f908152600c60209081526040808320858452909152902060010154156107cc5760405162461bcd60e51b815260206004820152601460248201527348617368206d75737420626520756e697175652160601b6044820152606401610599565b335f908152600b6020526040812080543492906107ea9084906117f5565b90915550506040805160c08101825234815260208082018581528284018581525f606085018190526080850181905260a08501819052338152600c84528581208882529093529390912082518155905160018201559151849282916002820190610854908261185c565b50606082015160038201805460ff19169115159190911790556080820151600482015560a090910151600590910155335f818152600d6020908152604080832080546001810182559084529190922001869055517f68d57ed495c4d1f4aed8ccdf7b8c5fc230b8dd3d0e8a59d59afaaef9c964749a906108d990349088908890611782565b60405180910390a26108eb84846111ab565b50505050565b6006602052815f5260405f20818154811061090a575f80fd5b905f5260205f20015f91509150505481565b6002602052815f5260405f20818154811061090a575f80fd5b61093d611151565b6001600160a01b0381166109865760405162461bcd60e51b815260206004820152601060248201526f496e76616c696420616464726573732160801b6044820152606401610599565b6001600160a01b0381165f908152600a602052604090205460ff16156109ee5760405162461bcd60e51b815260206004820152601c60248201527f4164647265737320697320616c726561647920616e206f776e657221000000006044820152606401610599565b6001600160a01b0381165f818152600a6020526040808220805460ff19166001179055517f994a936646fe87ffe4f1e469d3d6aa417d6b855598397f323de5b449f765f0c39190a250565b610a41611151565b610a4a5f6112a2565b565b6001600160a01b0381165f908152600d60205260408120546060918167ffffffffffffffff811115610a8057610a80611567565b604051908082528060200260200182016040528015610aea57816020015b610ad76040518060c001604052805f81526020015f8019168152602001606081526020015f151581526020015f81526020015f81525090565b815260200190600190039081610a9e5790505b5090505f5b82811015610c57576001600160a01b0385165f908152600d60205260408120805483908110610b2057610b20611918565b5f9182526020808320909101546001600160a01b0389168352600c825260408084208285528352808420815160c08101835281548152600182015494810194909452600281018054939650909291840191610b7a906117a9565b80601f0160208091040260200160405190810160405280929190818152602001828054610ba6906117a9565b8015610bf15780601f10610bc857610100808354040283529160200191610bf1565b820191905f5260205f20905b815481529060010190602001808311610bd457829003601f168201915b5050509183525050600382015460ff16151560208201526004820154604082015260059091015460609091015284519091508190859085908110610c3757610c37611918565b602002602001018190525050508080610c4f9061192c565b915050610aef565b509392505050565b600d602052815f5260405f20818154811061090a575f80fd5b5f6020819052908152604090208054600182018054919291610c99906117a9565b80601f0160208091040260200160405190810160405280929190818152602001828054610cc5906117a9565b8015610d105780601f10610ce757610100808354040283529160200191610d10565b820191905f5260205f20905b815481529060010190602001808311610cf357829003601f168201915b505050600290930154919250506001600160a01b031683565b6001600160a01b0384165f908152600c602090815260408083208684528252808320815160c081018352815481526001820154938101939093526002810180549192840191610d77906117a9565b80601f0160208091040260200160405190810160405280929190818152602001828054610da3906117a9565b8015610dee5780601f10610dc557610100808354040283529160200191610dee565b820191905f5260205f20905b815481529060010190602001808311610dd157829003601f168201915b5050509183525050600382015460ff16151560208201526004820154604082015260059091015460609091015260a0810180519192505f9190610e308261192c565b81525090505f806040518060c0016040528086865f0151610e519190611944565b815260208082018a905260408083018a905285151560608401526080888101519084015260a09092018690526001600160a01b038b165f908152600c82528281208b82528252829020835181559083015160018201559082015191925082916002820190610ebf908261185c565b50606082015160038201805460ff19169115159190911790556080820151600482015560a0909101516005909101556001600160a01b0388165f908152600b602052604081208054879290610f15908490611944565b909155505060405189906001600160a01b0382169087156108fc029088905f818181858888f19350505050158015610f4f573d5f803e3d5ffd5b50610f5d8a8a8a8a8a6112f3565b50505050505050505050565b5f8060605f600c5f876001600160a01b03166001600160a01b031681526020019081526020015f205f8681526020019081526020015f206040518060c00160405290815f820154815260200160018201548152602001600282018054610fce906117a9565b80601f0160208091040260200160405190810160405280929190818152602001828054610ffa906117a9565b80156110455780601f1061101c57610100808354040283529160200191611045565b820191905f5260205f20905b81548152906001019060200180831161102857829003601f168201915b5050509183525050600382015460ff161515602080830191909152600483015460408301526005909201546060909101528101519091505f036110be5760405162461bcd60e51b815260206004820152601160248201527044657461696c206e6f7420666f756e642160781b6044820152606401610599565b805160208201516040909201519097919650945092505050565b6110e0611151565b6001600160a01b0381166111455760405162461bcd60e51b815260206004820152602660248201527f4f776e61626c653a206e6577206f776e657220697320746865207a65726f206160448201526564647265737360d01b6064820152608401610599565b61114e816112a2565b50565b6008546001600160a01b03163314610a4a5760405162461bcd60e51b815260206004820181905260248201527f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e65726044820152606401610599565b60408051606081018252838152602080820184815233838501526003545f908152918290529290208151815591519091829160018201906111ec908261185c565b50604091820151600291820180546001600160a01b039092166001600160a01b0319928316179055600380545f90815260016020818152868320805490951633908117909555848352948552858220835481549283018255908352949091200192909255905491517fe7cd4ce7f2a465edc730269a1305e8a48bad821e8fb7e152ec413829c01a53c490611281908690611957565b60405180910390a360038054905f6112988361192c565b9190505550505050565b600880546001600160a01b038381166001600160a01b0319831681179093556040519116919082907f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e0905f90a35050565b6040805160a08101825284815260208082018581526001600160a01b038089168486015289166060840152608083018590526007545f908152600490925292902081518155915190918291600182019061134d908261185c565b506040828101516002830180546001600160a01b039283166001600160a01b0319918216179091556060850151600385018054918416918316919091179055608090940151600490930192909255600780545f9081526005602090815283822080548d871697168717905585825260068152838220835481546001810183559184529190922090910155549051909291881691907f629e1c4861fb601dea732ea90d1eb23dcf0de18f00803de3819b0dc2d69c675c9061140e908890611957565b60405180910390a460078054905f6114258361192c565b9190505550505050505050565b5f60208284031215611442575f80fd5b5035919050565b5f81518084525f5b8181101561146d57602081850181015186830182015201611451565b505f602082860101526020601f19601f83011685010191505092915050565b85815260a060208201525f6114a460a0830187611449565b6001600160a01b039586166040840152939094166060820152608001529392505050565b80356001600160a01b03811681146114de575f80fd5b919050565b5f602082840312156114f3575f80fd5b6114fc826114c8565b9392505050565b5f8060408385031215611514575f80fd5b61151d836114c8565b946020939093013593505050565b86815285602082015260c060408201525f61154960c0830187611449565b941515606083015250608081019290925260a0909101529392505050565b634e487b7160e01b5f52604160045260245ffd5b5f82601f83011261158a575f80fd5b813567ffffffffffffffff808211156115a5576115a5611567565b604051601f8301601f19908116603f011681019082821181831017156115cd576115cd611567565b816040528381528660208588010111156115e5575f80fd5b836020870160208301375f602085830101528094505050505092915050565b5f8060408385031215611615575f80fd5b82359150602083013567ffffffffffffffff811115611632575f80fd5b61163e8582860161157b565b9150509250929050565b5f6020808301818452808551808352604092508286019150828160051b8701018488015f5b838110156116d957603f19898403018552815160c08151855288820151898601528782015181898701526116a382870182611449565b6060848101511515908801526080808501519088015260a09384015193909601929092525050938601939086019060010161166d565b509098975050505050505050565b838152606060208201525f6116ff6060830185611449565b905060018060a01b0383166040830152949350505050565b5f805f805f60a0868803121561172b575f80fd5b611734866114c8565b9450611742602087016114c8565b935060408601359250606086013567ffffffffffffffff811115611764575f80fd5b6117708882890161157b565b95989497509295608001359392505050565b838152826020820152606060408201525f6117a06060830184611449565b95945050505050565b600181811c908216806117bd57607f821691505b6020821081036117db57634e487b7160e01b5f52602260045260245ffd5b50919050565b634e487b7160e01b5f52601160045260245ffd5b80820180821115611808576118086117e1565b92915050565b601f821115611857575f81815260208120601f850160051c810160208610156118345750805b601f850160051c820191505b8181101561185357828155600101611840565b5050505b505050565b815167ffffffffffffffff81111561187657611876611567565b61188a8161188484546117a9565b8461180e565b602080601f8311600181146118bd575f84156118a65750858301515b5f19600386901b1c1916600185901b178555611853565b5f85815260208120601f198616915b828110156118eb578886015182559484019460019091019084016118cc565b508582101561190857878501515f19600388901b60f8161c191681555b5050505050600190811b01905550565b634e487b7160e01b5f52603260045260245ffd5b5f6001820161193d5761193d6117e1565b5060010190565b81810381811115611808576118086117e1565b602081525f6114fc602083018461144956fea26469706673582212201b8610f601583cb4ec86f3bc5dc731f40020ab865ab808427cfc185ddb16e95164736f6c63430008140033";

    public static final String FUNC_ADDOWNER = "addOwner";

    public static final String FUNC_CONFIRMATIONDETAILS = "confirmationDetails";

    public static final String FUNC_CONFIRMATIONOWNERS = "confirmationOwners";

    public static final String FUNC_CONFIRMATIONSOFOWNER = "confirmationsOfOwner";

    public static final String FUNC_DEPOSITS = "deposits";

    public static final String FUNC_GETALLDETAILSFORUSER = "getAllDetailsForUser";

    public static final String FUNC_GETBALANCE = "getBalance";

    public static final String FUNC_GETDETAIL = "getDetail";

    public static final String FUNC_ISOWNER = "isOwner";

    public static final String FUNC_NEXTID = "nextID";

    public static final String FUNC_NEXTTOKENID = "nextTokenId";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_PROCESSPAYMENT = "processPayment";

    public static final String FUNC_PROCESSSPLIT = "processSplit";

    public static final String FUNC_RECEIPTDETAILS = "receiptDetails";

    public static final String FUNC_REMOVEOWNER = "removeOwner";

    public static final String FUNC_RENOUNCEOWNERSHIP = "renounceOwnership";

    public static final String FUNC_TOKENOWNERS = "tokenOwners";

    public static final String FUNC_TOKENSOFOWNER = "tokensOfOwner";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final String FUNC_USERDETAILS = "userDetails";

    public static final String FUNC_USERUNIQUEHASHES = "userUniqueHashes";

    public static final Event DEBUGMINT_EVENT = new Event("DebugMint", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event DEPOSITED_EVENT = new Event("Deposited", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event MINTED_EVENT = new Event("Minted", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event OWNERADDED_EVENT = new Event("OwnerAdded", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}));
    ;

    public static final Event OWNERREMOVED_EVENT = new Event("OwnerRemoved", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}));
    ;

    public static final Event OWNERSHIPTRANSFERRED_EVENT = new Event("OwnershipTransferred", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event SPLITCONFIRMED_EVENT = new Event("SplitConfirmed", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}, new TypeReference<Utf8String>() {}));
    ;

    @Deprecated
    protected PaymentSplitter(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected PaymentSplitter(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected PaymentSplitter(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected PaymentSplitter(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<DebugMintEventResponse> getDebugMintEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(DEBUGMINT_EVENT, transactionReceipt);
        ArrayList<DebugMintEventResponse> responses = new ArrayList<DebugMintEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            DebugMintEventResponse typedResponse = new DebugMintEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.uniqueHash = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.metadataJSON = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static DebugMintEventResponse getDebugMintEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(DEBUGMINT_EVENT, log);
        DebugMintEventResponse typedResponse = new DebugMintEventResponse();
        typedResponse.log = log;
        typedResponse.uniqueHash = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.metadataJSON = (String) eventValues.getNonIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<DebugMintEventResponse> debugMintEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getDebugMintEventFromLog(log));
    }

    public Flowable<DebugMintEventResponse> debugMintEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(DEBUGMINT_EVENT));
        return debugMintEventFlowable(filter);
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

    public static List<MintedEventResponse> getMintedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(MINTED_EVENT, transactionReceipt);
        ArrayList<MintedEventResponse> responses = new ArrayList<MintedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            MintedEventResponse typedResponse = new MintedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.metadataJSON = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static MintedEventResponse getMintedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(MINTED_EVENT, log);
        MintedEventResponse typedResponse = new MintedEventResponse();
        typedResponse.log = log;
        typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.metadataJSON = (String) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<MintedEventResponse> mintedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getMintedEventFromLog(log));
    }

    public Flowable<MintedEventResponse> mintedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(MINTED_EVENT));
        return mintedEventFlowable(filter);
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

    public RemoteFunctionCall<TransactionReceipt> addOwner(String newOwner) {
        final Function function = new Function(
                FUNC_ADDOWNER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, newOwner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
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

    public RemoteFunctionCall<Boolean> isOwner(String param0) {
        final Function function = new Function(FUNC_ISOWNER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<BigInteger> nextID() {
        final Function function = new Function(FUNC_NEXTID, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> nextTokenId() {
        final Function function = new Function(FUNC_NEXTTOKENID, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> owner() {
        final Function function = new Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> processPayment(byte[] _uniqueHash, String _dataJSON, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_PROCESSPAYMENT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(_uniqueHash), 
                new org.web3j.abi.datatypes.Utf8String(_dataJSON)), 
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

    public RemoteFunctionCall<Tuple3<byte[], String, String>> receiptDetails(BigInteger param0) {
        final Function function = new Function(FUNC_RECEIPTDETAILS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}));
        return new RemoteFunctionCall<Tuple3<byte[], String, String>>(function,
                new Callable<Tuple3<byte[], String, String>>() {
                    @Override
                    public Tuple3<byte[], String, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<byte[], String, String>(
                                (byte[]) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (String) results.get(2).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> removeOwner(String ownerToRemove) {
        final Function function = new Function(
                FUNC_REMOVEOWNER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, ownerToRemove)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> renounceOwnership() {
        final Function function = new Function(
                FUNC_RENOUNCEOWNERSHIP, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> tokenOwners(BigInteger param0) {
        final Function function = new Function(FUNC_TOKENOWNERS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> tokensOfOwner(String param0, BigInteger param1) {
        final Function function = new Function(FUNC_TOKENSOFOWNER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0), 
                new org.web3j.abi.datatypes.generated.Uint256(param1)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> transferOwnership(String newOwner) {
        final Function function = new Function(
                FUNC_TRANSFEROWNERSHIP, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, newOwner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple6<BigInteger, byte[], String, Boolean, BigInteger, BigInteger>> userDetails(String param0, byte[] param1) {
        final Function function = new Function(FUNC_USERDETAILS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0), 
                new org.web3j.abi.datatypes.generated.Bytes32(param1)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Bool>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple6<BigInteger, byte[], String, Boolean, BigInteger, BigInteger>>(function,
                new Callable<Tuple6<BigInteger, byte[], String, Boolean, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple6<BigInteger, byte[], String, Boolean, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple6<BigInteger, byte[], String, Boolean, BigInteger, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (byte[]) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (Boolean) results.get(3).getValue(), 
                                (BigInteger) results.get(4).getValue(), 
                                (BigInteger) results.get(5).getValue());
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
    public static PaymentSplitter load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new PaymentSplitter(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static PaymentSplitter load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new PaymentSplitter(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static PaymentSplitter load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new PaymentSplitter(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static PaymentSplitter load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new PaymentSplitter(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<PaymentSplitter> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(PaymentSplitter.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<PaymentSplitter> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(PaymentSplitter.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<PaymentSplitter> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(PaymentSplitter.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<PaymentSplitter> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(PaymentSplitter.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
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

    public static class DebugMintEventResponse extends BaseEventResponse {
        public byte[] uniqueHash;

        public String metadataJSON;
    }

    public static class DepositedEventResponse extends BaseEventResponse {
        public String from;

        public BigInteger value;

        public byte[] uniqueHash;

        public String data;
    }

    public static class MintedEventResponse extends BaseEventResponse {
        public String owner;

        public BigInteger tokenId;

        public String metadataJSON;
    }

    public static class OwnerAddedEventResponse extends BaseEventResponse {
        public String newOwner;
    }

    public static class OwnerRemovedEventResponse extends BaseEventResponse {
        public String removedOwner;
    }

    public static class OwnershipTransferredEventResponse extends BaseEventResponse {
        public String previousOwner;

        public String newOwner;
    }

    public static class SplitConfirmedEventResponse extends BaseEventResponse {
        public String owner;

        public String client;

        public BigInteger tokenId;

        public String metadataJSON;
    }
}
