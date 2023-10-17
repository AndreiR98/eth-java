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
    public static final String BINARY = "608060405234801561000f575f80fd5b5061001933610034565b60018055600680546001600160a01b03191633179055610083565b5f80546001600160a01b038381166001600160a01b0319831681178455604051919092169283917f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e09190a35050565b61255e806100905f395ff3fe60806040526004361061008f575f3560e01c80638d474e5d116100575780638d474e5d146101635780638da5cb5b14610176578063de9207591461019c578063f2fde38b146101c8578063fa4b8931146101e7575f80fd5b8063139478321461009357806313e9df96146100c8578063317ef8c5146100f55780636885bcd314610121578063715018a61461014d575b5f80fd5b34801561009e575f80fd5b506100b26100ad366004611a9d565b610206565b6040516100bf9190611b94565b60405180910390f35b3480156100d3575f80fd5b506100e76100e2366004611a9d565b610441565b6040519081526020016100bf565b348015610100575f80fd5b5061011461010f366004611a9d565b6104b5565b6040516100bf9190611ba6565b34801561012c575f80fd5b5061014061013b366004611c38565b6106bb565b6040516100bf9190611c53565b348015610158575f80fd5b50610161610b6e565b005b610161610171366004611d4a565b610b81565b348015610181575f80fd5b505f546040516001600160a01b0390911681526020016100bf565b3480156101a7575f80fd5b506101bb6101b6366004611c38565b611037565b6040516100bf9190611f57565b3480156101d3575f80fd5b506101616101e2366004611c38565b6115d9565b3480156101f2575f80fd5b50610161610201366004611fc7565b611652565b61023f6040518060a00160405280606081526020015f815260200160608152602001606081526020015f6001600160a01b031681525090565b5f60028360405161025091906120b4565b90815260200160405180910390209050806040518060a00160405290815f8201805461027b906120cf565b80601f01602080910402602001604051908101604052809291908181526020018280546102a7906120cf565b80156102f25780601f106102c9576101008083540402835291602001916102f2565b820191905f5260205f20905b8154815290600101906020018083116102d557829003601f168201915b5050505050815260200160018201548152602001600282018054610315906120cf565b80601f0160208091040260200160405190810160405280929190818152602001828054610341906120cf565b801561038c5780601f106103635761010080835404028352916020019161038c565b820191905f5260205f20905b81548152906001019060200180831161036f57829003601f168201915b505050505081526020016003820180546103a5906120cf565b80601f01602080910402602001604051908101604052809291908181526020018280546103d1906120cf565b801561041c5780601f106103f35761010080835404028352916020019161041c565b820191905f5260205f20905b8154815290600101906020018083116103ff57829003601f168201915b5050509183525050600491909101546001600160a01b03166020909101529392505050565b5f8060038360405161045391906120b4565b908152602001604051809103902090505f815f018054610472906120cf565b90501161049a5760405162461bcd60e51b815260040161049190612107565b60405180910390fd5b806005015481600101546104ae9190612148565b9392505050565b6104eb6040518060c00160405280606081526020015f81526020015f81526020015f815260200160608152602001606081525090565b5f6003836040516104fc91906120b4565b908152602001604051809103902090505f815f01805461051b906120cf565b90501161053a5760405162461bcd60e51b815260040161049190612107565b6105706040518060c00160405280606081526020015f81526020015f81526020015f815260200160608152602001606081525090565b8381526001820154602082015260028201546040820152600382015460608201526009820180546105a0906120cf565b80601f01602080910402602001604051908101604052809291908181526020018280546105cc906120cf565b80156106175780601f106105ee57610100808354040283529160200191610617565b820191905f5260205f20905b8154815290600101906020018083116105fa57829003601f168201915b5050505050816080018190525081600c018054610633906120cf565b80601f016020809104026020016040519081016040528092919081815260200182805461065f906120cf565b80156106aa5780601f10610681576101008083540402835291602001916106aa565b820191905f5260205f20905b81548152906001019060200180831161068d57829003601f168201915b505050505060a08201529392505050565b6001600160a01b0381165f90815260046020526040812080546060929067ffffffffffffffff8111156106f0576106f0611a00565b60405190808252806020026020018201604052801561076657816020015b61075360405180610100016040528060608152602001606081526020015f8152602001606081526020015f81526020015f815260200160608152602001606081525090565b81526020019060019003908161070e5790505b5090505f5b8254811015610b66575f600384838154811061078957610789612161565b905f5260205f2090600502016003016040516107a59190612175565b908152602001604051809103902090505f815f0180546107c4906120cf565b9050116107e35760405162461bcd60e51b815260040161049190612107565b5f60405180610100016040528086858154811061080257610802612161565b905f5260205f2090600502015f01805461081b906120cf565b80601f0160208091040260200160405190810160405280929190818152602001828054610847906120cf565b80156108925780601f1061086957610100808354040283529160200191610892565b820191905f5260205f20905b81548152906001019060200180831161087557829003601f168201915b505050505081526020018685815481106108ae576108ae612161565b905f5260205f20906005020160010180546108c8906120cf565b80601f01602080910402602001604051908101604052809291908181526020018280546108f4906120cf565b801561093f5780601f106109165761010080835404028352916020019161093f565b820191905f5260205f20905b81548152906001019060200180831161092257829003601f168201915b5050505050815260200183600101548152602001835f018054610961906120cf565b80601f016020809104026020016040519081016040528092919081815260200182805461098d906120cf565b80156109d85780601f106109af576101008083540402835291602001916109d8565b820191905f5260205f20905b8154815290600101906020018083116109bb57829003601f168201915b505050505081526020018685815481106109f4576109f4612161565b905f5260205f20906005020160040154815260200183600301548152602001836009018054610a22906120cf565b80601f0160208091040260200160405190810160405280929190818152602001828054610a4e906120cf565b8015610a995780601f10610a7057610100808354040283529160200191610a99565b820191905f5260205f20905b815481529060010190602001808311610a7c57829003601f168201915b5050505050815260200183600c018054610ab2906120cf565b80601f0160208091040260200160405190810160405280929190818152602001828054610ade906120cf565b8015610b295780601f10610b0057610100808354040283529160200191610b29565b820191905f5260205f20905b815481529060010190602001808311610b0c57829003601f168201915b5050505050815250905080848481518110610b4657610b46612161565b602002602001018190525050508080610b5e906121e7565b91505061076b565b509392505050565b610b766118a2565b610b7f5f6118fb565b565b5f600387604051610b9291906120b4565b908152602001604051809103902090505f3411610bf15760405162461bcd60e51b815260206004820181905260248201527f5061796d656e742073686f756c642062652067726561746572207468616e20306044820152606401610491565b8060010154816005015410610c485760405162461bcd60e51b815260206004820152601a60248201527f42696c6c20697320616c72656164792066756c6c7920706169640000000000006044820152606401610491565b8060010154348260050154610c5d91906121ff565b1115610cab5760405162461bcd60e51b815260206004820152601b60248201527f5061796d656e7420657863656564732062696c6c20616d6f756e7400000000006044820152606401610491565b5f805b6008830154811015610d445787604051602001610ccb91906120b4565b60405160208183030381529060405280519060200120836008018281548110610cf657610cf6612161565b905f5260205f2001604051602001610d0e9190612175565b6040516020818303038152906040528051906020012003610d325760019150610d44565b80610d3c816121e7565b915050610cae565b508015610d935760405162461bcd60e51b815260206004820152601760248201527f5061796d656e7420494420616c726561647920757365640000000000000000006044820152606401610491565b34826005015f828254610da691906121ff565b90915550506008820180546001810182555f918252602090912001610dcb8882612260565b506040805160a08101825288815260208101889052808201859052606081018a905233608082015290518190600290610e05908b906120b4565b90815260405190819003602001902081518190610e229082612260565b506020820151600182015560408201516002820190610e419082612260565b5060608201516003820190610e569082612260565b50608091820151600491820180546001600160a01b0319166001600160a01b039092169190911790556040805160a0810182528b8152602080820189905260018881015483850152606083018f90529482018c9052335f90815293815291832080549485018155835291208151919283926005909102909101908190610edc9082612260565b5060208201516001820190610ef19082612260565b506040820151600282015560608201516003820190610f109082612260565b506080820151816004015550507f0e2d8deeb062bd8f6912d7853de21de08fe7b044f05fbff57535e92e08c6edc18983604051610f4e92919061231c565b60405180910390a1836001015484600501540361102b5760068401805460ff191660011790556040517fb8d25d5b7c55a99377b2dd44d275b6f9f5c304df625b253613672082b227ef3890610fa6908c908790612401565b60405180910390a1600a84015460048501546040516001600160a01b039092169181156108fc0291905f818181858888f19350505050158015610feb573d5f803e3d5ffd5b50600b84015460028501546040516001600160a01b039092169181156108fc0291905f818181858888f19350505050158015611029573d5f803e3d5ffd5b505b50505050505050505050565b6001600160a01b0381165f908152600560209081526040808320805482518185028101850190935280835260609493849084015b82821015611113578382905f5260205f20018054611088906120cf565b80601f01602080910402602001604051908101604052809291908181526020018280546110b4906120cf565b80156110ff5780601f106110d6576101008083540402835291602001916110ff565b820191905f5260205f20905b8154815290600101906020018083116110e257829003601f168201915b50505050508152602001906001019061106b565b5050505090505f815167ffffffffffffffff81111561113457611134611a00565b6040519080825280602002602001820160405280156111dc57816020015b6111c9604051806101a00160405280606081526020015f81526020015f81526020015f81526020015f81526020015f81526020015f151581526020016060815260200160608152602001606081526020015f6001600160a01b031681526020015f6001600160a01b03168152602001606081525090565b8152602001906001900390816111525790505b5090505f5b8251811015610b665760038382815181106111fe576111fe612161565b602002602001015160405161121391906120b4565b9081526020016040518091039020604051806101a00160405290815f8201805461123c906120cf565b80601f0160208091040260200160405190810160405280929190818152602001828054611268906120cf565b80156112b35780601f1061128a576101008083540402835291602001916112b3565b820191905f5260205f20905b81548152906001019060200180831161129657829003601f168201915b505050918352505060018201546020820152600282015460408201526003820154606082015260048201546080820152600582015460a0820152600682015460ff16151560c082015260078201805460e090920191611311906120cf565b80601f016020809104026020016040519081016040528092919081815260200182805461133d906120cf565b80156113885780601f1061135f57610100808354040283529160200191611388565b820191905f5260205f20905b81548152906001019060200180831161136b57829003601f168201915b5050505050815260200160088201805480602002602001604051908101604052809291908181526020015f905b8282101561145d578382905f5260205f200180546113d2906120cf565b80601f01602080910402602001604051908101604052809291908181526020018280546113fe906120cf565b80156114495780601f1061142057610100808354040283529160200191611449565b820191905f5260205f20905b81548152906001019060200180831161142c57829003601f168201915b5050505050815260200190600101906113b5565b505050508152602001600982018054611475906120cf565b80601f01602080910402602001604051908101604052809291908181526020018280546114a1906120cf565b80156114ec5780601f106114c3576101008083540402835291602001916114ec565b820191905f5260205f20905b8154815290600101906020018083116114cf57829003601f168201915b5050509183525050600a8201546001600160a01b039081166020830152600b830154166040820152600c82018054606090920191611529906120cf565b80601f0160208091040260200160405190810160405280929190818152602001828054611555906120cf565b80156115a05780601f10611577576101008083540402835291602001916115a0565b820191905f5260205f20905b81548152906001019060200180831161158357829003601f168201915b5050505050815250508282815181106115bb576115bb612161565b602002602001018190525080806115d1906121e7565b9150506111e1565b6115e16118a2565b6001600160a01b0381166116465760405162461bcd60e51b815260206004820152602660248201527f4f776e61626c653a206e6577206f776e657220697320746865207a65726f206160448201526564647265737360d01b6064820152608401610491565b61164f816118fb565b50565b61165a6118a2565b5f604051806101a001604052808c81526020018b81526020018a81526020018981526020018881526020015f81526020015f151581526020018781526020015f67ffffffffffffffff8111156116b2576116b2611a00565b6040519080825280602002602001820160405280156116e557816020015b60608152602001906001900390816116d05790505b508152602001868152602001856001600160a01b03168152602001846001600160a01b031681526020018381525090508060038c60405161172691906120b4565b908152604051908190036020019020815181906117439082612260565b506020820151600182015560408201516002820155606082015160038201556080820151600482015560a0820151600582015560c082015160068201805460ff191691151591909117905560e082015160078201906117a29082612260565b5061010082015180516117bf91600884019160209091019061194a565b5061012082015160098201906117d59082612260565b50610140820151600a820180546001600160a01b039283166001600160a01b031991821617909155610160840151600b84018054919093169116179055610180820151600c8201906118279082612260565b5050506001600160a01b0384165f9081526005602090815260408220805460018101825590835291200161185b8c82612260565b507ff4f83392ce24d1079fca1d78c2e991b3e74cbb17db7db34efd0361138a0935cd8b8260405161188d929190612504565b60405180910390a15050505050505050505050565b5f546001600160a01b03163314610b7f5760405162461bcd60e51b815260206004820181905260248201527f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e65726044820152606401610491565b5f80546001600160a01b038381166001600160a01b0319831681178455604051919092169283917f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e09190a35050565b828054828255905f5260205f2090810192821561198e579160200282015b8281111561198e578251829061197e9082612260565b5091602001919060010190611968565b5061199a92915061199e565b5090565b8082111561199a575f6119b182826119ba565b5060010161199e565b5080546119c6906120cf565b5f825580601f106119d5575050565b601f0160209004905f5260205f209081019061164f91905b8082111561199a575f81556001016119ed565b634e487b7160e01b5f52604160045260245ffd5b5f82601f830112611a23575f80fd5b813567ffffffffffffffff80821115611a3e57611a3e611a00565b604051601f8301601f19908116603f01168101908282118183101715611a6657611a66611a00565b81604052838152866020858801011115611a7e575f80fd5b836020870160208301375f602085830101528094505050505092915050565b5f60208284031215611aad575f80fd5b813567ffffffffffffffff811115611ac3575f80fd5b611acf84828501611a14565b949350505050565b5f5b83811015611af1578181015183820152602001611ad9565b50505f910152565b5f8151808452611b10816020860160208601611ad7565b601f01601f19169290920160200192915050565b5f815160a08452611b3860a0850182611af9565b90506020830151602085015260408301518482036040860152611b5b8282611af9565b91505060608301518482036060860152611b758282611af9565b6080948501516001600160a01b03169590940194909452509092915050565b602081525f6104ae6020830184611b24565b602081525f825160c06020840152611bc160e0840182611af9565b90506020840151604084015260408401516060840152606084015160808401526080840151601f19808584030160a0860152611bfd8383611af9565b925060a08601519150808584030160c086015250611c1b8282611af9565b95945050505050565b6001600160a01b038116811461164f575f80fd5b5f60208284031215611c48575f80fd5b81356104ae81611c24565b5f6020808301818452808551808352604092508286019150828160051b8701018488015f5b83811015611d3c57603f1989840301855281516101008151818652611c9f82870182611af9565b915050888201518582038a870152611cb78282611af9565b915050878201518886015260608083015186830382880152611cd98382611af9565b92505050608080830151818701525060a080830151818701525060c08083015186830382880152611d0a8382611af9565b9250505060e08083015192508582038187015250611d288183611af9565b968901969450505090860190600101611c78565b509098975050505050505050565b5f805f805f8060c08789031215611d5f575f80fd5b863567ffffffffffffffff80821115611d76575f80fd5b611d828a838b01611a14565b97506020890135915080821115611d97575f80fd5b611da38a838b01611a14565b965060408901359550606089013594506080890135915080821115611dc6575f80fd5b611dd28a838b01611a14565b935060a0890135915080821115611de7575f80fd5b50611df489828a01611a14565b9150509295509295509295565b5f81518084526020808501808196508360051b810191508286015f5b85811015611e47578284038952611e35848351611af9565b98850198935090840190600101611e1d565b5091979650505050505050565b5f6101a08251818552611e6982860182611af9565b9150506020830151602085015260408301516040850152606083015160608501526080830151608085015260a083015160a085015260c0830151611eb160c086018215159052565b5060e083015184820360e0860152611ec98282611af9565b9150506101008084015185830382870152611ee48382611e01565b925050506101208084015185830382870152611f008382611af9565b9250505061014080840151611f1f828701826001600160a01b03169052565b5050610160838101516001600160a01b0316908501526101808084015185830382870152611f4d8382611af9565b9695505050505050565b5f602080830181845280855180835260408601915060408160051b87010192508387015f5b82811015611faa57603f19888603018452611f98858351611e54565b94509285019290850190600101611f7c565b5092979650505050505050565b8035611fc281611c24565b919050565b5f805f805f805f805f806101408b8d031215611fe1575f80fd5b8a3567ffffffffffffffff80821115611ff8575f80fd5b6120048e838f01611a14565b9b5060208d01359a5060408d0135995060608d0135985060808d0135975060a08d0135915080821115612035575f80fd5b6120418e838f01611a14565b965060c08d0135915080821115612056575f80fd5b6120628e838f01611a14565b955061207060e08e01611fb7565b945061207f6101008e01611fb7565b93506101208d0135915080821115612095575f80fd5b506120a28d828e01611a14565b9150509295989b9194979a5092959850565b5f82516120c5818460208701611ad7565b9190910192915050565b600181811c908216806120e357607f821691505b60208210810361210157634e487b7160e01b5f52602260045260245ffd5b50919050565b602080825260139082015272109a5b1b08191bd95cc81b9bdd08195e1a5cdd606a1b604082015260600190565b634e487b7160e01b5f52601160045260245ffd5b8181038181111561215b5761215b612134565b92915050565b634e487b7160e01b5f52603260045260245ffd5b5f808354612182816120cf565b6001828116801561219a57600181146121af576121db565b60ff19841687528215158302870194506121db565b875f526020805f205f5b858110156121d25781548a8201529084019082016121b9565b50505082870194505b50929695505050505050565b5f600182016121f8576121f8612134565b5060010190565b8082018082111561215b5761215b612134565b601f82111561225b575f81815260208120601f850160051c810160208610156122385750805b601f850160051c820191505b8181101561225757828155600101612244565b5050505b505050565b815167ffffffffffffffff81111561227a5761227a611a00565b61228e8161228884546120cf565b84612212565b602080601f8311600181146122c1575f84156122aa5750858301515b5f19600386901b1c1916600185901b178555612257565b5f85815260208120601f198616915b828110156122ef578886015182559484019460019091019084016122d0565b508582101561230c57878501515f19600388901b60f8161c191681555b5050505050600190811b01905550565b604081525f61232e6040830185611af9565b8281036020840152611c1b8185611b24565b5f815461234c816120cf565b8085526020600183811680156123695760018114612383576123ae565b60ff1985168884015283151560051b8801830195506123ae565b865f52825f205f5b858110156123a65781548a820186015290830190840161238b565b890184019650505b505050505092915050565b5f81548084526020808501808196508360051b81019150855f52825f205f5b85811015611e475782840389526123ef8483612340565b988501989350600191820191016123d8565b604081525f6124136040830185611af9565b82810360208401526101a080825261242d81830186612340565b905060018501546020830152600285015460408301526003850154606083015260048501546080830152600585015460a083015261246f600686015460ff1690565b151560c083015281810360e083015261248b8160078701612340565b90508181036101008301526124a381600887016123b9565b90508181036101208301526124bb8160098701612340565b90506124d1600a8601546001600160a01b031690565b6001600160a01b03908116610140840152600b86015416610160830152818103610180830152611f4d81600c8701612340565b604081525f6125166040830185611af9565b8281036020840152611c1b8185611e5456fea2646970667358221220c0dc01cf0ded87d68da6060789bfca86e65d50b314aeff279e9c21ed7cd25e3064736f6c63430008140033";

    public static final String FUNC_CREATEBILL = "createBill";

    public static final String FUNC_GETBILLDETAILS = "getBillDetails";

    public static final String FUNC_GETBILLSFORMERCHANT = "getBillsForMerchant";

    public static final String FUNC_GETMISSINGAMOUNT = "getMissingAmount";

    public static final String FUNC_GETPAYMENTDETAILS = "getPaymentDetails";

    public static final String FUNC_GETRECEIPTSPERADDRESS = "getReceiptsPerAddress";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_PAY = "pay";

    public static final String FUNC_RENOUNCEOWNERSHIP = "renounceOwnership";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final Event BILLGENERATED_EVENT = new Event("BillGenerated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<BillModel>() {}));
    ;

    public static final Event BILLPROCESSED_EVENT = new Event("BillProcessed", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<BillModel>() {}));
    ;

    public static final Event OWNERSHIPTRANSFERRED_EVENT = new Event("OwnershipTransferred", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event PAYMENTRECEIVED_EVENT = new Event("PaymentReceived", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Payment>() {}));
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
            typedResponse._bill = (BillModel) eventValues.getNonIndexedValues().get(1);
            responses.add(typedResponse);
        }
        return responses;
    }

    public static BillProcessedEventResponse getBillProcessedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(BILLPROCESSED_EVENT, log);
        BillProcessedEventResponse typedResponse = new BillProcessedEventResponse();
        typedResponse.log = log;
        typedResponse._billUniqueId = (String) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse._bill = (BillModel) eventValues.getNonIndexedValues().get(1);
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
            typedResponse._payment = (Payment) eventValues.getNonIndexedValues().get(1);
            responses.add(typedResponse);
        }
        return responses;
    }

    public static PaymentReceivedEventResponse getPaymentReceivedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(PAYMENTRECEIVED_EVENT, log);
        PaymentReceivedEventResponse typedResponse = new PaymentReceivedEventResponse();
        typedResponse.log = log;
        typedResponse._paymentUniqueId = (String) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse._payment = (Payment) eventValues.getNonIndexedValues().get(1);
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

    public RemoteFunctionCall<TransactionReceipt> createBill(String _billUniqueId, BigInteger _billAmount, BigInteger _txAmount, BigInteger _tipAmount, BigInteger _revenueAmount, String _processDate, String _billTimeStamp, String _merchantAddress, String _processorAddress, String _metaData) {
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
                new org.web3j.abi.datatypes.Address(160, _processorAddress), 
                new org.web3j.abi.datatypes.Utf8String(_metaData)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BillDetails> getBillDetails(String _billUniqueId) {
        final Function function = new Function(FUNC_GETBILLDETAILS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_billUniqueId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<BillDetails>() {}));
        return executeRemoteCallSingleValueReturn(function, BillDetails.class);
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

    public RemoteFunctionCall<BigInteger> getMissingAmount(String _billUniqueId) {
        final Function function = new Function(FUNC_GETMISSINGAMOUNT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_billUniqueId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Payment> getPaymentDetails(String _paymentUniqueId) {
        final Function function = new Function(FUNC_GETPAYMENTDETAILS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_paymentUniqueId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Payment>() {}));
        return executeRemoteCallSingleValueReturn(function, Payment.class);
    }

    public RemoteFunctionCall<List> getReceiptsPerAddress(String _address) {
        final Function function = new Function(FUNC_GETRECEIPTSPERADDRESS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _address)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<BillingDTO>>() {}));
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

    public RemoteFunctionCall<TransactionReceipt> pay(String _billUniqueId, String _uniquePaymentId, BigInteger _amount, BigInteger _splitNumber, String _splitType, String _timeStamp, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_PAY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_billUniqueId), 
                new org.web3j.abi.datatypes.Utf8String(_uniquePaymentId), 
                new org.web3j.abi.datatypes.generated.Uint256(_amount), 
                new org.web3j.abi.datatypes.generated.Uint256(_splitNumber), 
                new org.web3j.abi.datatypes.Utf8String(_splitType), 
                new org.web3j.abi.datatypes.Utf8String(_timeStamp)), 
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

        public String processorAddress;

        public String metaData;

        public BillModel(String billUniqueId, BigInteger billAmount, BigInteger txAmount, BigInteger tipAmount, BigInteger revenueAmount, BigInteger payedAmount, Boolean processed, String processDate, List<String> payments, String billTimeStamp, String merchantAddress, String processorAddress, String metaData) {
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
                    new org.web3j.abi.datatypes.Address(160, processorAddress), 
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
            this.processorAddress = processorAddress;
            this.metaData = metaData;
        }

        public BillModel(Utf8String billUniqueId, Uint256 billAmount, Uint256 txAmount, Uint256 tipAmount, Uint256 revenueAmount, Uint256 payedAmount, Bool processed, Utf8String processDate, DynamicArray<Utf8String> payments, Utf8String billTimeStamp, Address merchantAddress, Address processorAddress, Utf8String metaData) {
            super(billUniqueId, billAmount, txAmount, tipAmount, revenueAmount, payedAmount, processed, processDate, payments, billTimeStamp, merchantAddress, processorAddress, metaData);
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
            this.processorAddress = processorAddress.getValue();
            this.metaData = metaData.getValue();
        }
    }

    public static class Payment extends DynamicStruct {
        public String uniquePaymentId;

        public BigInteger amount;

        public String timeStamp;

        public String billUniqueId;

        public String clientAddress;

        public Payment(String uniquePaymentId, BigInteger amount, String timeStamp, String billUniqueId, String clientAddress) {
            super(new org.web3j.abi.datatypes.Utf8String(uniquePaymentId), 
                    new org.web3j.abi.datatypes.generated.Uint256(amount), 
                    new org.web3j.abi.datatypes.Utf8String(timeStamp), 
                    new org.web3j.abi.datatypes.Utf8String(billUniqueId), 
                    new org.web3j.abi.datatypes.Address(160, clientAddress));
            this.uniquePaymentId = uniquePaymentId;
            this.amount = amount;
            this.timeStamp = timeStamp;
            this.billUniqueId = billUniqueId;
            this.clientAddress = clientAddress;
        }

        public Payment(Utf8String uniquePaymentId, Uint256 amount, Utf8String timeStamp, Utf8String billUniqueId, Address clientAddress) {
            super(uniquePaymentId, amount, timeStamp, billUniqueId, clientAddress);
            this.uniquePaymentId = uniquePaymentId.getValue();
            this.amount = amount.getValue();
            this.timeStamp = timeStamp.getValue();
            this.billUniqueId = billUniqueId.getValue();
            this.clientAddress = clientAddress.getValue();
        }
    }

    public static class BillDetails extends DynamicStruct {
        public String billUniqueId;

        public BigInteger billAmount;

        public BigInteger txAmount;

        public BigInteger tipAmount;

        public String billTimeStamp;

        public String metaData;

        public BillDetails(String billUniqueId, BigInteger billAmount, BigInteger txAmount, BigInteger tipAmount, String billTimeStamp, String metaData) {
            super(new org.web3j.abi.datatypes.Utf8String(billUniqueId), 
                    new org.web3j.abi.datatypes.generated.Uint256(billAmount), 
                    new org.web3j.abi.datatypes.generated.Uint256(txAmount), 
                    new org.web3j.abi.datatypes.generated.Uint256(tipAmount), 
                    new org.web3j.abi.datatypes.Utf8String(billTimeStamp), 
                    new org.web3j.abi.datatypes.Utf8String(metaData));
            this.billUniqueId = billUniqueId;
            this.billAmount = billAmount;
            this.txAmount = txAmount;
            this.tipAmount = tipAmount;
            this.billTimeStamp = billTimeStamp;
            this.metaData = metaData;
        }

        public BillDetails(Utf8String billUniqueId, Uint256 billAmount, Uint256 txAmount, Uint256 tipAmount, Utf8String billTimeStamp, Utf8String metaData) {
            super(billUniqueId, billAmount, txAmount, tipAmount, billTimeStamp, metaData);
            this.billUniqueId = billUniqueId.getValue();
            this.billAmount = billAmount.getValue();
            this.txAmount = txAmount.getValue();
            this.tipAmount = tipAmount.getValue();
            this.billTimeStamp = billTimeStamp.getValue();
            this.metaData = metaData.getValue();
        }
    }

    public static class BillingDTO extends DynamicStruct {
        public String uniquePaymentId;

        public String timeStamp;

        public BigInteger billAmount;

        public String billUniqueId;

        public BigInteger amount;

        public BigInteger tipAmount;

        public String billTimeStamp;

        public String metaData;

        public BillingDTO(String uniquePaymentId, String timeStamp, BigInteger billAmount, String billUniqueId, BigInteger amount, BigInteger tipAmount, String billTimeStamp, String metaData) {
            super(new org.web3j.abi.datatypes.Utf8String(uniquePaymentId), 
                    new org.web3j.abi.datatypes.Utf8String(timeStamp), 
                    new org.web3j.abi.datatypes.generated.Uint256(billAmount), 
                    new org.web3j.abi.datatypes.Utf8String(billUniqueId), 
                    new org.web3j.abi.datatypes.generated.Uint256(amount), 
                    new org.web3j.abi.datatypes.generated.Uint256(tipAmount), 
                    new org.web3j.abi.datatypes.Utf8String(billTimeStamp), 
                    new org.web3j.abi.datatypes.Utf8String(metaData));
            this.uniquePaymentId = uniquePaymentId;
            this.timeStamp = timeStamp;
            this.billAmount = billAmount;
            this.billUniqueId = billUniqueId;
            this.amount = amount;
            this.tipAmount = tipAmount;
            this.billTimeStamp = billTimeStamp;
            this.metaData = metaData;
        }

        public BillingDTO(Utf8String uniquePaymentId, Utf8String timeStamp, Uint256 billAmount, Utf8String billUniqueId, Uint256 amount, Uint256 tipAmount, Utf8String billTimeStamp, Utf8String metaData) {
            super(uniquePaymentId, timeStamp, billAmount, billUniqueId, amount, tipAmount, billTimeStamp, metaData);
            this.uniquePaymentId = uniquePaymentId.getValue();
            this.timeStamp = timeStamp.getValue();
            this.billAmount = billAmount.getValue();
            this.billUniqueId = billUniqueId.getValue();
            this.amount = amount.getValue();
            this.tipAmount = tipAmount.getValue();
            this.billTimeStamp = billTimeStamp.getValue();
            this.metaData = metaData.getValue();
        }
    }

    public static class BillGeneratedEventResponse extends BaseEventResponse {
        public String _billUniqueId;

        public BillModel _bill;
    }

    public static class BillProcessedEventResponse extends BaseEventResponse {
        public String _billUniqueId;

        public BillModel _bill;
    }

    public static class OwnershipTransferredEventResponse extends BaseEventResponse {
        public String previousOwner;

        public String newOwner;
    }

    public static class PaymentReceivedEventResponse extends BaseEventResponse {
        public String _paymentUniqueId;

        public Payment _payment;
    }
}
