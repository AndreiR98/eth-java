package uk.co.roteala.javaprocessor.contracts;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Array;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
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
public class ERC1155 extends Contract {
    public static final String BINARY = "608060405234801562000010575f80fd5b50604051620015be380380620015be83398101604081905262000033916200006b565b6200003e8162000045565b506200028d565b6002620000538282620001c5565b5050565b634e487b7160e01b5f52604160045260245ffd5b5f60208083850312156200007d575f80fd5b82516001600160401b038082111562000094575f80fd5b818501915085601f830112620000a8575f80fd5b815181811115620000bd57620000bd62000057565b604051601f8201601f19908116603f01168101908382118183101715620000e857620000e862000057565b81604052828152888684870101111562000100575f80fd5b5f93505b8284101562000123578484018601518185018701529285019262000104565b5f86848301015280965050505050505092915050565b600181811c908216806200014e57607f821691505b6020821081036200016d57634e487b7160e01b5f52602260045260245ffd5b50919050565b601f821115620001c0575f81815260208120601f850160051c810160208610156200019b5750805b601f850160051c820191505b81811015620001bc57828155600101620001a7565b5050505b505050565b81516001600160401b03811115620001e157620001e162000057565b620001f981620001f2845462000139565b8462000173565b602080601f8311600181146200022f575f8415620002175750858301515b5f19600386901b1c1916600185901b178555620001bc565b5f85815260208120601f198616915b828110156200025f578886015182559484019460019091019084016200023e565b50858210156200027d57878501515f19600388901b60f8161c191681555b5050505050600190811b01905550565b611323806200029b5f395ff3fe608060405234801561000f575f80fd5b5060043610610084575f3560e01c80634e1273f4116100585780634e1273f414610106578063a22cb46514610126578063e985e9c514610139578063f242432a14610174575f80fd5b8062fdd58e1461008857806301ffc9a7146100ae5780630e89341c146100d15780632eb2c2d6146100f1575b5f80fd5b61009b610096366004610b25565b610187565b6040519081526020015b60405180910390f35b6100c16100bc366004610b65565b61021e565b60405190151581526020016100a5565b6100e46100df366004610b87565b61026d565b6040516100a59190610be1565b6101046100ff366004610d35565b6102ff565b005b610119610114366004610dd8565b61034b565b6040516100a59190610ed6565b610104610134366004610ee8565b610473565b6100c1610147366004610f21565b6001600160a01b039182165f90815260016020908152604080832093909416825291909152205460ff1690565b610104610182366004610f52565b610482565b5f6001600160a01b0383166101f65760405162461bcd60e51b815260206004820152602a60248201527f455243313135353a2061646472657373207a65726f206973206e6f742061207660448201526930b634b21037bbb732b960b11b60648201526084015b60405180910390fd5b505f818152602081815260408083206001600160a01b03861684529091529020545b92915050565b5f6001600160e01b03198216636cdb3d1360e11b148061024e57506001600160e01b031982166303a24d0760e21b145b8061021857506301ffc9a760e01b6001600160e01b0319831614610218565b60606002805461027c90610fb2565b80601f01602080910402602001604051908101604052809291908181526020018280546102a890610fb2565b80156102f35780601f106102ca576101008083540402835291602001916102f3565b820191905f5260205f20905b8154815290600101906020018083116102d657829003601f168201915b50505050509050919050565b6001600160a01b03851633148061031b575061031b8533610147565b6103375760405162461bcd60e51b81526004016101ed90610fea565b61034485858585856104c7565b5050505050565b606081518351146103b05760405162461bcd60e51b815260206004820152602960248201527f455243313135353a206163636f756e747320616e6420696473206c656e677468604482015268040dad2e6dac2e8c6d60bb1b60648201526084016101ed565b5f835167ffffffffffffffff8111156103cb576103cb610bf3565b6040519080825280602002602001820160405280156103f4578160200160208202803683370190505b5090505f5b845181101561046b5761043e85828151811061041757610417611038565b602002602001015185838151811061043157610431611038565b6020026020010151610187565b82828151811061045057610450611038565b602090810291909101015261046481611060565b90506103f9565b509392505050565b61047e33838361069f565b5050565b6001600160a01b03851633148061049e575061049e8533610147565b6104ba5760405162461bcd60e51b81526004016101ed90610fea565b610344858585858561077e565b81518351146105295760405162461bcd60e51b815260206004820152602860248201527f455243313135353a2069647320616e6420616d6f756e7473206c656e677468206044820152670dad2e6dac2e8c6d60c31b60648201526084016101ed565b6001600160a01b03841661054f5760405162461bcd60e51b81526004016101ed90611078565b335f5b8451811015610631575f85828151811061056e5761056e611038565b602002602001015190505f85838151811061058b5761058b611038565b6020908102919091018101515f84815280835260408082206001600160a01b038e1683529093529190912054909150818110156105da5760405162461bcd60e51b81526004016101ed906110bd565b5f838152602081815260408083206001600160a01b038e8116855292528083208585039055908b16825281208054849290610616908490611107565b925050819055505050508061062a90611060565b9050610552565b50846001600160a01b0316866001600160a01b0316826001600160a01b03167f4a39dc06d4c0dbc64b70af90fd698a233a518aa5d07e595d983b8c0526c8f7fb878760405161068192919061111a565b60405180910390a46106978187878787876108a4565b505050505050565b816001600160a01b0316836001600160a01b0316036107125760405162461bcd60e51b815260206004820152602960248201527f455243313135353a2073657474696e6720617070726f76616c20737461747573604482015268103337b91039b2b63360b91b60648201526084016101ed565b6001600160a01b038381165f81815260016020908152604080832094871680845294825291829020805460ff191686151590811790915591519182527f17307eab39ab6107e8899845ad3d59bd9653f200f220920489ca2b5937696c31910160405180910390a3505050565b6001600160a01b0384166107a45760405162461bcd60e51b81526004016101ed90611078565b335f6107af85610a07565b90505f6107bb85610a07565b90505f868152602081815260408083206001600160a01b038c168452909152902054858110156107fd5760405162461bcd60e51b81526004016101ed906110bd565b5f878152602081815260408083206001600160a01b038d8116855292528083208985039055908a16825281208054889290610839908490611107565b909155505060408051888152602081018890526001600160a01b03808b16928c821692918816917fc3d58168c5ae7397731d063d5bbf3d657854427343f4c083240f7aacaa2d0f62910160405180910390a4610899848a8a8a8a8a610a50565b505050505050505050565b6001600160a01b0384163b156106975760405163bc197c8160e01b81526001600160a01b0385169063bc197c81906108e89089908990889088908890600401611147565b6020604051808303815f875af1925050508015610922575060408051601f3d908101601f1916820190925261091f918101906111a4565b60015b6109ce5761092e6111bf565b806308c379a00361096757506109426111d8565b8061094d5750610969565b8060405162461bcd60e51b81526004016101ed9190610be1565b505b60405162461bcd60e51b815260206004820152603460248201527f455243313135353a207472616e7366657220746f206e6f6e2d455243313135356044820152732932b1b2b4bb32b91034b6b83632b6b2b73a32b960611b60648201526084016101ed565b6001600160e01b0319811663bc197c8160e01b146109fe5760405162461bcd60e51b81526004016101ed90611261565b50505050505050565b6040805160018082528183019092526060915f91906020808301908036833701905050905082815f81518110610a3f57610a3f611038565b602090810291909101015292915050565b6001600160a01b0384163b156106975760405163f23a6e6160e01b81526001600160a01b0385169063f23a6e6190610a9490899089908890889088906004016112a9565b6020604051808303815f875af1925050508015610ace575060408051601f3d908101601f19168201909252610acb918101906111a4565b60015b610ada5761092e6111bf565b6001600160e01b0319811663f23a6e6160e01b146109fe5760405162461bcd60e51b81526004016101ed90611261565b80356001600160a01b0381168114610b20575f80fd5b919050565b5f8060408385031215610b36575f80fd5b610b3f83610b0a565b946020939093013593505050565b6001600160e01b031981168114610b62575f80fd5b50565b5f60208284031215610b75575f80fd5b8135610b8081610b4d565b9392505050565b5f60208284031215610b97575f80fd5b5035919050565b5f81518084525f5b81811015610bc257602081850181015186830182015201610ba6565b505f602082860101526020601f19601f83011685010191505092915050565b602081525f610b806020830184610b9e565b634e487b7160e01b5f52604160045260245ffd5b601f8201601f1916810167ffffffffffffffff81118282101715610c2d57610c2d610bf3565b6040525050565b5f67ffffffffffffffff821115610c4d57610c4d610bf3565b5060051b60200190565b5f82601f830112610c66575f80fd5b81356020610c7382610c34565b604051610c808282610c07565b83815260059390931b8501820192828101915086841115610c9f575f80fd5b8286015b84811015610cba5780358352918301918301610ca3565b509695505050505050565b5f82601f830112610cd4575f80fd5b813567ffffffffffffffff811115610cee57610cee610bf3565b604051610d05601f8301601f191660200182610c07565b818152846020838601011115610d19575f80fd5b816020850160208301375f918101602001919091529392505050565b5f805f805f60a08688031215610d49575f80fd5b610d5286610b0a565b9450610d6060208701610b0a565b9350604086013567ffffffffffffffff80821115610d7c575f80fd5b610d8889838a01610c57565b94506060880135915080821115610d9d575f80fd5b610da989838a01610c57565b93506080880135915080821115610dbe575f80fd5b50610dcb88828901610cc5565b9150509295509295909350565b5f8060408385031215610de9575f80fd5b823567ffffffffffffffff80821115610e00575f80fd5b818501915085601f830112610e13575f80fd5b81356020610e2082610c34565b604051610e2d8282610c07565b83815260059390931b8501820192828101915089841115610e4c575f80fd5b948201945b83861015610e7157610e6286610b0a565b82529482019490820190610e51565b96505086013592505080821115610e86575f80fd5b50610e9385828601610c57565b9150509250929050565b5f8151808452602080850194508084015f5b83811015610ecb57815187529582019590820190600101610eaf565b509495945050505050565b602081525f610b806020830184610e9d565b5f8060408385031215610ef9575f80fd5b610f0283610b0a565b915060208301358015158114610f16575f80fd5b809150509250929050565b5f8060408385031215610f32575f80fd5b610f3b83610b0a565b9150610f4960208401610b0a565b90509250929050565b5f805f805f60a08688031215610f66575f80fd5b610f6f86610b0a565b9450610f7d60208701610b0a565b93506040860135925060608601359150608086013567ffffffffffffffff811115610fa6575f80fd5b610dcb88828901610cc5565b600181811c90821680610fc657607f821691505b602082108103610fe457634e487b7160e01b5f52602260045260245ffd5b50919050565b6020808252602e908201527f455243313135353a2063616c6c6572206973206e6f7420746f6b656e206f776e60408201526d195c881bdc88185c1c1c9bdd995960921b606082015260800190565b634e487b7160e01b5f52603260045260245ffd5b634e487b7160e01b5f52601160045260245ffd5b5f600182016110715761107161104c565b5060010190565b60208082526025908201527f455243313135353a207472616e7366657220746f20746865207a65726f206164604082015264647265737360d81b606082015260800190565b6020808252602a908201527f455243313135353a20696e73756666696369656e742062616c616e636520666f60408201526939103a3930b739b332b960b11b606082015260800190565b808201808211156102185761021861104c565b604081525f61112c6040830185610e9d565b828103602084015261113e8185610e9d565b95945050505050565b6001600160a01b0386811682528516602082015260a0604082018190525f9061117290830186610e9d565b82810360608401526111848186610e9d565b905082810360808401526111988185610b9e565b98975050505050505050565b5f602082840312156111b4575f80fd5b8151610b8081610b4d565b5f60033d11156111d55760045f803e505f5160e01c5b90565b5f60443d10156111e55790565b6040516003193d81016004833e81513d67ffffffffffffffff816024840111818411171561121557505050505090565b828501915081518181111561122d5750505050505090565b843d87010160208285010111156112475750505050505090565b61125660208286010187610c07565b509095945050505050565b60208082526028908201527f455243313135353a204552433131353552656365697665722072656a656374656040820152676420746f6b656e7360c01b606082015260800190565b6001600160a01b03868116825285166020820152604081018490526060810183905260a0608082018190525f906112e290830184610b9e565b97965050505050505056fea26469706673582212204de62ca423e42fa7d0c67aec2824d79c1af2c6bce778f06292884fd22e45cd6864736f6c63430008140033";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_BALANCEOFBATCH = "balanceOfBatch";

    public static final String FUNC_ISAPPROVEDFORALL = "isApprovedForAll";

    public static final String FUNC_SAFEBATCHTRANSFERFROM = "safeBatchTransferFrom";

    public static final String FUNC_SAFETRANSFERFROM = "safeTransferFrom";

    public static final String FUNC_SETAPPROVALFORALL = "setApprovalForAll";

    public static final String FUNC_SUPPORTSINTERFACE = "supportsInterface";

    public static final String FUNC_URI = "uri";

    public static final Event APPROVALFORALL_EVENT = new Event("ApprovalForAll", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Bool>() {}));
    ;

    public static final Event TRANSFERBATCH_EVENT = new Event("TransferBatch", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<Uint256>>() {}));
    ;

    public static final Event TRANSFERSINGLE_EVENT = new Event("TransferSingle", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event URI_EVENT = new Event("URI", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Uint256>(true) {}));
    ;

    @Deprecated
    protected ERC1155(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected ERC1155(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected ERC1155(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected ERC1155(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<ApprovalForAllEventResponse> getApprovalForAllEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(APPROVALFORALL_EVENT, transactionReceipt);
        ArrayList<ApprovalForAllEventResponse> responses = new ArrayList<ApprovalForAllEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ApprovalForAllEventResponse typedResponse = new ApprovalForAllEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.account = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.operator = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.approved = (Boolean) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static ApprovalForAllEventResponse getApprovalForAllEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(APPROVALFORALL_EVENT, log);
        ApprovalForAllEventResponse typedResponse = new ApprovalForAllEventResponse();
        typedResponse.log = log;
        typedResponse.account = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.operator = (String) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.approved = (Boolean) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<ApprovalForAllEventResponse> approvalForAllEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getApprovalForAllEventFromLog(log));
    }

    public Flowable<ApprovalForAllEventResponse> approvalForAllEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPROVALFORALL_EVENT));
        return approvalForAllEventFlowable(filter);
    }

    public static List<TransferBatchEventResponse> getTransferBatchEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(TRANSFERBATCH_EVENT, transactionReceipt);
        ArrayList<TransferBatchEventResponse> responses = new ArrayList<TransferBatchEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TransferBatchEventResponse typedResponse = new TransferBatchEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.operator = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.from = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.to = (String) eventValues.getIndexedValues().get(2).getValue();
            typedResponse.ids = (List<BigInteger>) ((Array) eventValues.getNonIndexedValues().get(0)).getNativeValueCopy();
            typedResponse.values = (List<BigInteger>) ((Array) eventValues.getNonIndexedValues().get(1)).getNativeValueCopy();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static TransferBatchEventResponse getTransferBatchEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(TRANSFERBATCH_EVENT, log);
        TransferBatchEventResponse typedResponse = new TransferBatchEventResponse();
        typedResponse.log = log;
        typedResponse.operator = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.from = (String) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.to = (String) eventValues.getIndexedValues().get(2).getValue();
        typedResponse.ids = (List<BigInteger>) ((Array) eventValues.getNonIndexedValues().get(0)).getNativeValueCopy();
        typedResponse.values = (List<BigInteger>) ((Array) eventValues.getNonIndexedValues().get(1)).getNativeValueCopy();
        return typedResponse;
    }

    public Flowable<TransferBatchEventResponse> transferBatchEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getTransferBatchEventFromLog(log));
    }

    public Flowable<TransferBatchEventResponse> transferBatchEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRANSFERBATCH_EVENT));
        return transferBatchEventFlowable(filter);
    }

    public static List<TransferSingleEventResponse> getTransferSingleEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(TRANSFERSINGLE_EVENT, transactionReceipt);
        ArrayList<TransferSingleEventResponse> responses = new ArrayList<TransferSingleEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TransferSingleEventResponse typedResponse = new TransferSingleEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.operator = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.from = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.to = (String) eventValues.getIndexedValues().get(2).getValue();
            typedResponse.id = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static TransferSingleEventResponse getTransferSingleEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(TRANSFERSINGLE_EVENT, log);
        TransferSingleEventResponse typedResponse = new TransferSingleEventResponse();
        typedResponse.log = log;
        typedResponse.operator = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.from = (String) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.to = (String) eventValues.getIndexedValues().get(2).getValue();
        typedResponse.id = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<TransferSingleEventResponse> transferSingleEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getTransferSingleEventFromLog(log));
    }

    public Flowable<TransferSingleEventResponse> transferSingleEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRANSFERSINGLE_EVENT));
        return transferSingleEventFlowable(filter);
    }

    public static List<URIEventResponse> getURIEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(URI_EVENT, transactionReceipt);
        ArrayList<URIEventResponse> responses = new ArrayList<URIEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            URIEventResponse typedResponse = new URIEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.id = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.value = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static URIEventResponse getURIEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(URI_EVENT, log);
        URIEventResponse typedResponse = new URIEventResponse();
        typedResponse.log = log;
        typedResponse.id = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.value = (String) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<URIEventResponse> uRIEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getURIEventFromLog(log));
    }

    public Flowable<URIEventResponse> uRIEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(URI_EVENT));
        return uRIEventFlowable(filter);
    }

    public RemoteFunctionCall<BigInteger> balanceOf(String account, BigInteger id) {
        final Function function = new Function(FUNC_BALANCEOF, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account), 
                new org.web3j.abi.datatypes.generated.Uint256(id)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<List> balanceOfBatch(List<String> accounts, List<BigInteger> ids) {
        final Function function = new Function(FUNC_BALANCEOFBATCH, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(accounts, org.web3j.abi.datatypes.Address.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Uint256>(
                        org.web3j.abi.datatypes.generated.Uint256.class,
                        org.web3j.abi.Utils.typeMap(ids, org.web3j.abi.datatypes.generated.Uint256.class))), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Uint256>>() {}));
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

    public RemoteFunctionCall<Boolean> isApprovedForAll(String account, String operator) {
        final Function function = new Function(FUNC_ISAPPROVEDFORALL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account), 
                new org.web3j.abi.datatypes.Address(160, operator)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> safeBatchTransferFrom(String from, String to, List<BigInteger> ids, List<BigInteger> amounts, byte[] data) {
        final Function function = new Function(
                FUNC_SAFEBATCHTRANSFERFROM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, from), 
                new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Uint256>(
                        org.web3j.abi.datatypes.generated.Uint256.class,
                        org.web3j.abi.Utils.typeMap(ids, org.web3j.abi.datatypes.generated.Uint256.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Uint256>(
                        org.web3j.abi.datatypes.generated.Uint256.class,
                        org.web3j.abi.Utils.typeMap(amounts, org.web3j.abi.datatypes.generated.Uint256.class)), 
                new org.web3j.abi.datatypes.DynamicBytes(data)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> safeTransferFrom(String from, String to, BigInteger id, BigInteger amount, byte[] data) {
        final Function function = new Function(
                FUNC_SAFETRANSFERFROM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, from), 
                new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.generated.Uint256(id), 
                new org.web3j.abi.datatypes.generated.Uint256(amount), 
                new org.web3j.abi.datatypes.DynamicBytes(data)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setApprovalForAll(String operator, Boolean approved) {
        final Function function = new Function(
                FUNC_SETAPPROVALFORALL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, operator), 
                new org.web3j.abi.datatypes.Bool(approved)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> supportsInterface(byte[] interfaceId) {
        final Function function = new Function(FUNC_SUPPORTSINTERFACE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes4(interfaceId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<String> uri(BigInteger param0) {
        final Function function = new Function(FUNC_URI, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    @Deprecated
    public static ERC1155 load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new ERC1155(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static ERC1155 load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new ERC1155(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static ERC1155 load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new ERC1155(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static ERC1155 load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new ERC1155(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<ERC1155> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String uri_) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(uri_)));
        return deployRemoteCall(ERC1155.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<ERC1155> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String uri_) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(uri_)));
        return deployRemoteCall(ERC1155.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<ERC1155> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String uri_) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(uri_)));
        return deployRemoteCall(ERC1155.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<ERC1155> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String uri_) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(uri_)));
        return deployRemoteCall(ERC1155.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class ApprovalForAllEventResponse extends BaseEventResponse {
        public String account;

        public String operator;

        public Boolean approved;
    }

    public static class TransferBatchEventResponse extends BaseEventResponse {
        public String operator;

        public String from;

        public String to;

        public List<BigInteger> ids;

        public List<BigInteger> values;
    }

    public static class TransferSingleEventResponse extends BaseEventResponse {
        public String operator;

        public String from;

        public String to;

        public BigInteger id;

        public BigInteger value;
    }

    public static class URIEventResponse extends BaseEventResponse {
        public BigInteger id;

        public String value;
    }
}
