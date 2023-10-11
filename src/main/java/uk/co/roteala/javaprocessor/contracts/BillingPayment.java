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
    public static final String BINARY = "608060405234801561000f575f80fd5b5061001933610034565b60018055600680546001600160a01b03191633179055610083565b5f80546001600160a01b038381166001600160a01b0319831681178455604051919092169283917f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e09190a35050565b6122f2806100905f395ff3fe60806040526004361061009a575f3560e01c80638d474e5d116100625780638d474e5d1461016e5780638da5cb5b14610181578063de920759146101a7578063f2702127146101d3578063f2fde38b146101f3578063fa4b893114610212575f80fd5b8063139478321461009e57806313e9df96146100d3578063317ef8c5146101005780636885bcd31461012c578063715018a614610158575b5f80fd5b3480156100a9575f80fd5b506100bd6100b836600461189a565b610231565b6040516100ca9190611991565b60405180910390f35b3480156100de575f80fd5b506100f26100ed36600461189a565b61046c565b6040519081526020016100ca565b34801561010b575f80fd5b5061011f61011a36600461189a565b610502565b6040516100ca91906119a3565b348015610137575f80fd5b5061014b610146366004611a35565b61072e565b6040516100ca9190611a50565b348015610163575f80fd5b5061016c610963565b005b61016c61017c366004611b05565b610976565b34801561018c575f80fd5b505f546040516001600160a01b0390911681526020016100ca565b3480156101b2575f80fd5b506101c66101c1366004611a35565b610e2c565b6040516100ca9190611d15565b3480156101de575f80fd5b5061014b6101ed366004611a35565b50606090565b3480156101fe575f80fd5b5061016c61020d366004611a35565b6113d6565b34801561021d575f80fd5b5061016c61022c366004611d85565b61144f565b61026a6040518060a00160405280606081526020015f815260200160608152602001606081526020015f6001600160a01b031681525090565b5f60028360405161027b9190611e72565b90815260200160405180910390209050806040518060a00160405290815f820180546102a690611e8d565b80601f01602080910402602001604051908101604052809291908181526020018280546102d290611e8d565b801561031d5780601f106102f45761010080835404028352916020019161031d565b820191905f5260205f20905b81548152906001019060200180831161030057829003601f168201915b505050505081526020016001820154815260200160028201805461034090611e8d565b80601f016020809104026020016040519081016040528092919081815260200182805461036c90611e8d565b80156103b75780601f1061038e576101008083540402835291602001916103b7565b820191905f5260205f20905b81548152906001019060200180831161039a57829003601f168201915b505050505081526020016003820180546103d090611e8d565b80601f01602080910402602001604051908101604052809291908181526020018280546103fc90611e8d565b80156104475780601f1061041e57610100808354040283529160200191610447565b820191905f5260205f20905b81548152906001019060200180831161042a57829003601f168201915b5050509183525050600491909101546001600160a01b03166020909101529392505050565b5f8060038360405161047e9190611e72565b908152602001604051809103902090505f815f01805461049d90611e8d565b9050116104e75760405162461bcd60e51b8152602060048201526013602482015272109a5b1b08191bd95cc81b9bdd08195e1a5cdd606a1b60448201526064015b60405180910390fd5b806005015481600101546104fb9190611ed9565b9392505050565b6105386040518060c00160405280606081526020015f81526020015f81526020015f815260200160608152602001606081525090565b5f6003836040516105499190611e72565b908152602001604051809103902090505f815f01805461056890611e8d565b9050116105ad5760405162461bcd60e51b8152602060048201526013602482015272109a5b1b08191bd95cc81b9bdd08195e1a5cdd606a1b60448201526064016104de565b6105e36040518060c00160405280606081526020015f81526020015f81526020015f815260200160608152602001606081525090565b83815260018201546020820152600282015460408201526003820154606082015260098201805461061390611e8d565b80601f016020809104026020016040519081016040528092919081815260200182805461063f90611e8d565b801561068a5780601f106106615761010080835404028352916020019161068a565b820191905f5260205f20905b81548152906001019060200180831161066d57829003601f168201915b5050505050816080018190525081600c0180546106a690611e8d565b80601f01602080910402602001604051908101604052809291908181526020018280546106d290611e8d565b801561071d5780601f106106f45761010080835404028352916020019161071d565b820191905f5260205f20905b81548152906001019060200180831161070057829003601f168201915b505050505060a08201529392505050565b6001600160a01b0381165f9081526004602090815260408083208054825181850281018501909352808352606094919384929084015b82821015610957578382905f5260205f2090600502016040518060a00160405290815f8201805461079490611e8d565b80601f01602080910402602001604051908101604052809291908181526020018280546107c090611e8d565b801561080b5780601f106107e25761010080835404028352916020019161080b565b820191905f5260205f20905b8154815290600101906020018083116107ee57829003601f168201915b5050505050815260200160018201805461082490611e8d565b80601f016020809104026020016040519081016040528092919081815260200182805461085090611e8d565b801561089b5780601f106108725761010080835404028352916020019161089b565b820191905f5260205f20905b81548152906001019060200180831161087e57829003601f168201915b50505050508152602001600282015481526020016003820180546108be90611e8d565b80601f01602080910402602001604051908101604052809291908181526020018280546108ea90611e8d565b80156109355780601f1061090c57610100808354040283529160200191610935565b820191905f5260205f20905b81548152906001019060200180831161091857829003601f168201915b5050505050815260200160048201548152505081526020019060010190610764565b50505050915050919050565b61096b61169f565b6109745f6116f8565b565b5f6003876040516109879190611e72565b908152602001604051809103902090505f34116109e65760405162461bcd60e51b815260206004820181905260248201527f5061796d656e742073686f756c642062652067726561746572207468616e203060448201526064016104de565b8060010154816005015410610a3d5760405162461bcd60e51b815260206004820152601a60248201527f42696c6c20697320616c72656164792066756c6c79207061696400000000000060448201526064016104de565b8060010154348260050154610a529190611ef2565b1115610aa05760405162461bcd60e51b815260206004820152601b60248201527f5061796d656e7420657863656564732062696c6c20616d6f756e74000000000060448201526064016104de565b5f805b6008830154811015610b395787604051602001610ac09190611e72565b60405160208183030381529060405280519060200120836008018281548110610aeb57610aeb611f05565b905f5260205f2001604051602001610b039190611f19565b6040516020818303038152906040528051906020012003610b275760019150610b39565b80610b3181611f8b565b915050610aa3565b508015610b885760405162461bcd60e51b815260206004820152601760248201527f5061796d656e7420494420616c7265616479207573656400000000000000000060448201526064016104de565b34826005015f828254610b9b9190611ef2565b90915550506008820180546001810182555f918252602090912001610bc08882611ff1565b506040805160a08101825288815260208101889052808201859052606081018a905233608082015290518190600290610bfa908b90611e72565b90815260405190819003602001902081518190610c179082611ff1565b506020820151600182015560408201516002820190610c369082611ff1565b5060608201516003820190610c4b9082611ff1565b50608091820151600491820180546001600160a01b0319166001600160a01b039092169190911790556040805160a0810182528b8152602080820189905260018881015483850152606083018f90529482018c9052335f90815293815291832080549485018155835291208151919283926005909102909101908190610cd19082611ff1565b5060208201516001820190610ce69082611ff1565b506040820151600282015560608201516003820190610d059082611ff1565b506080820151816004015550507f0e2d8deeb062bd8f6912d7853de21de08fe7b044f05fbff57535e92e08c6edc18983604051610d439291906120ad565b60405180910390a18360010154846005015403610e205760068401805460ff191660011790556040517fb8d25d5b7c55a99377b2dd44d275b6f9f5c304df625b253613672082b227ef3890610d9b908c908790612195565b60405180910390a1600a84015460048501546040516001600160a01b039092169181156108fc0291905f818181858888f19350505050158015610de0573d5f803e3d5ffd5b50600b84015460028501546040516001600160a01b039092169181156108fc0291905f818181858888f19350505050158015610e1e573d5f803e3d5ffd5b505b50505050505050505050565b6001600160a01b0381165f908152600560209081526040808320805482518185028101850190935280835260609493849084015b82821015610f08578382905f5260205f20018054610e7d90611e8d565b80601f0160208091040260200160405190810160405280929190818152602001828054610ea990611e8d565b8015610ef45780601f10610ecb57610100808354040283529160200191610ef4565b820191905f5260205f20905b815481529060010190602001808311610ed757829003601f168201915b505050505081526020019060010190610e60565b5050505090505f815167ffffffffffffffff811115610f2957610f296117fd565b604051908082528060200260200182016040528015610fd157816020015b610fbe604051806101a00160405280606081526020015f81526020015f81526020015f81526020015f81526020015f81526020015f151581526020016060815260200160608152602001606081526020015f6001600160a01b031681526020015f6001600160a01b03168152602001606081525090565b815260200190600190039081610f475790505b5090505f5b82518110156113ce576003838281518110610ff357610ff3611f05565b60200260200101516040516110089190611e72565b9081526020016040518091039020604051806101a00160405290815f8201805461103190611e8d565b80601f016020809104026020016040519081016040528092919081815260200182805461105d90611e8d565b80156110a85780601f1061107f576101008083540402835291602001916110a8565b820191905f5260205f20905b81548152906001019060200180831161108b57829003601f168201915b505050918352505060018201546020820152600282015460408201526003820154606082015260048201546080820152600582015460a0820152600682015460ff16151560c082015260078201805460e09092019161110690611e8d565b80601f016020809104026020016040519081016040528092919081815260200182805461113290611e8d565b801561117d5780601f106111545761010080835404028352916020019161117d565b820191905f5260205f20905b81548152906001019060200180831161116057829003601f168201915b5050505050815260200160088201805480602002602001604051908101604052809291908181526020015f905b82821015611252578382905f5260205f200180546111c790611e8d565b80601f01602080910402602001604051908101604052809291908181526020018280546111f390611e8d565b801561123e5780601f106112155761010080835404028352916020019161123e565b820191905f5260205f20905b81548152906001019060200180831161122157829003601f168201915b5050505050815260200190600101906111aa565b50505050815260200160098201805461126a90611e8d565b80601f016020809104026020016040519081016040528092919081815260200182805461129690611e8d565b80156112e15780601f106112b8576101008083540402835291602001916112e1565b820191905f5260205f20905b8154815290600101906020018083116112c457829003601f168201915b5050509183525050600a8201546001600160a01b039081166020830152600b830154166040820152600c8201805460609092019161131e90611e8d565b80601f016020809104026020016040519081016040528092919081815260200182805461134a90611e8d565b80156113955780601f1061136c57610100808354040283529160200191611395565b820191905f5260205f20905b81548152906001019060200180831161137857829003601f168201915b5050505050815250508282815181106113b0576113b0611f05565b602002602001018190525080806113c690611f8b565b915050610fd6565b509392505050565b6113de61169f565b6001600160a01b0381166114435760405162461bcd60e51b815260206004820152602660248201527f4f776e61626c653a206e6577206f776e657220697320746865207a65726f206160448201526564647265737360d01b60648201526084016104de565b61144c816116f8565b50565b61145761169f565b5f604051806101a001604052808c81526020018b81526020018a81526020018981526020018881526020015f81526020015f151581526020018781526020015f67ffffffffffffffff8111156114af576114af6117fd565b6040519080825280602002602001820160405280156114e257816020015b60608152602001906001900390816114cd5790505b508152602001868152602001856001600160a01b03168152602001846001600160a01b031681526020018381525090508060038c6040516115239190611e72565b908152604051908190036020019020815181906115409082611ff1565b506020820151600182015560408201516002820155606082015160038201556080820151600482015560a0820151600582015560c082015160068201805460ff191691151591909117905560e0820151600782019061159f9082611ff1565b5061010082015180516115bc916008840191602090910190611747565b5061012082015160098201906115d29082611ff1565b50610140820151600a820180546001600160a01b039283166001600160a01b031991821617909155610160840151600b84018054919093169116179055610180820151600c8201906116249082611ff1565b5050506001600160a01b0384165f908152600560209081526040822080546001810182559083529120016116588c82611ff1565b507ff4f83392ce24d1079fca1d78c2e991b3e74cbb17db7db34efd0361138a0935cd8b8260405161168a929190612298565b60405180910390a15050505050505050505050565b5f546001600160a01b031633146109745760405162461bcd60e51b815260206004820181905260248201527f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e657260448201526064016104de565b5f80546001600160a01b038381166001600160a01b0319831681178455604051919092169283917f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e09190a35050565b828054828255905f5260205f2090810192821561178b579160200282015b8281111561178b578251829061177b9082611ff1565b5091602001919060010190611765565b5061179792915061179b565b5090565b80821115611797575f6117ae82826117b7565b5060010161179b565b5080546117c390611e8d565b5f825580601f106117d2575050565b601f0160209004905f5260205f209081019061144c91905b80821115611797575f81556001016117ea565b634e487b7160e01b5f52604160045260245ffd5b5f82601f830112611820575f80fd5b813567ffffffffffffffff8082111561183b5761183b6117fd565b604051601f8301601f19908116603f01168101908282118183101715611863576118636117fd565b8160405283815286602085880101111561187b575f80fd5b836020870160208301375f602085830101528094505050505092915050565b5f602082840312156118aa575f80fd5b813567ffffffffffffffff8111156118c0575f80fd5b6118cc84828501611811565b949350505050565b5f5b838110156118ee5781810151838201526020016118d6565b50505f910152565b5f815180845261190d8160208601602086016118d4565b601f01601f19169290920160200192915050565b5f815160a0845261193560a08501826118f6565b9050602083015160208501526040830151848203604086015261195882826118f6565b9150506060830151848203606086015261197282826118f6565b6080948501516001600160a01b03169590940194909452509092915050565b602081525f6104fb6020830184611921565b602081525f825160c060208401526119be60e08401826118f6565b90506020840151604084015260408401516060840152606084015160808401526080840151601f19808584030160a08601526119fa83836118f6565b925060a08601519150808584030160c086015250611a1882826118f6565b95945050505050565b6001600160a01b038116811461144c575f80fd5b5f60208284031215611a45575f80fd5b81356104fb81611a21565b5f6020808301818452808551808352604092508286019150828160051b8701018488015f5b83811015611af757603f19898403018552815160a08151818652611a9b828701826118f6565b915050888201518582038a870152611ab382826118f6565b915050878201518886015260608083015186830382880152611ad583826118f6565b6080948501519790940196909652505094870194925090860190600101611a75565b509098975050505050505050565b5f805f805f8060c08789031215611b1a575f80fd5b863567ffffffffffffffff80821115611b31575f80fd5b611b3d8a838b01611811565b97506020890135915080821115611b52575f80fd5b611b5e8a838b01611811565b965060408901359550606089013594506080890135915080821115611b81575f80fd5b611b8d8a838b01611811565b935060a0890135915080821115611ba2575f80fd5b50611baf89828a01611811565b9150509295509295509295565b5f82825180855260208086019550808260051b8401018186015f5b84811015611c0557601f19868403018952611bf38383516118f6565b98840198925090830190600101611bd7565b5090979650505050505050565b5f6101a08251818552611c27828601826118f6565b9150506020830151602085015260408301516040850152606083015160608501526080830151608085015260a083015160a085015260c0830151611c6f60c086018215159052565b5060e083015184820360e0860152611c8782826118f6565b9150506101008084015185830382870152611ca28382611bbc565b925050506101208084015185830382870152611cbe83826118f6565b9250505061014080840151611cdd828701826001600160a01b03169052565b5050610160838101516001600160a01b0316908501526101808084015185830382870152611d0b83826118f6565b9695505050505050565b5f602080830181845280855180835260408601915060408160051b87010192508387015f5b82811015611d6857603f19888603018452611d56858351611c12565b94509285019290850190600101611d3a565b5092979650505050505050565b8035611d8081611a21565b919050565b5f805f805f805f805f806101408b8d031215611d9f575f80fd5b8a3567ffffffffffffffff80821115611db6575f80fd5b611dc28e838f01611811565b9b5060208d01359a5060408d0135995060608d0135985060808d0135975060a08d0135915080821115611df3575f80fd5b611dff8e838f01611811565b965060c08d0135915080821115611e14575f80fd5b611e208e838f01611811565b9550611e2e60e08e01611d75565b9450611e3d6101008e01611d75565b93506101208d0135915080821115611e53575f80fd5b50611e608d828e01611811565b9150509295989b9194979a5092959850565b5f8251611e838184602087016118d4565b9190910192915050565b600181811c90821680611ea157607f821691505b602082108103611ebf57634e487b7160e01b5f52602260045260245ffd5b50919050565b634e487b7160e01b5f52601160045260245ffd5b81810381811115611eec57611eec611ec5565b92915050565b80820180821115611eec57611eec611ec5565b634e487b7160e01b5f52603260045260245ffd5b5f808354611f2681611e8d565b60018281168015611f3e5760018114611f5357611f7f565b60ff1984168752821515830287019450611f7f565b875f526020805f205f5b85811015611f765781548a820152908401908201611f5d565b50505082870194505b50929695505050505050565b5f60018201611f9c57611f9c611ec5565b5060010190565b601f821115611fec575f81815260208120601f850160051c81016020861015611fc95750805b601f850160051c820191505b81811015611fe857828155600101611fd5565b5050505b505050565b815167ffffffffffffffff81111561200b5761200b6117fd565b61201f816120198454611e8d565b84611fa3565b602080601f831160018114612052575f841561203b5750858301515b5f19600386901b1c1916600185901b178555611fe8565b5f85815260208120601f198616915b8281101561208057888601518255948401946001909101908401612061565b508582101561209d57878501515f19600388901b60f8161c191681555b5050505050600190811b01905550565b604081525f6120bf60408301856118f6565b8281036020840152611a188185611921565b5f81546120dd81611e8d565b8085526020600183811680156120fa57600181146121145761213f565b60ff1985168884015283151560051b88018301955061213f565b865f52825f205f5b858110156121375781548a820186015290830190840161211c565b890184019650505b505050505092915050565b5f82825480855260208086019550808260051b840101855f52815f205f5b84811015611c0557858303601f1901895261218383836120d1565b98840198925060019182019101612168565b604081525f6121a760408301856118f6565b82810360208401526101a08082526121c1818301866120d1565b905060018501546020830152600285015460408301526003850154606083015260048501546080830152600585015460a0830152612203600686015460ff1690565b151560c083015281810360e083015261221f81600787016120d1565b9050818103610100830152612237816008870161214a565b905081810361012083015261224f81600987016120d1565b9050612265600a8601546001600160a01b031690565b6001600160a01b03908116610140840152600b86015416610160830152818103610180830152611d0b81600c87016120d1565b604081525f6122aa60408301856118f6565b8281036020840152611a188185611c1256fea2646970667358221220705d4cc15ec540740d75f1d28af17f835ff75bbc2e4dc600d78017fe810da83a64736f6c63430008140033";

    public static final String FUNC_CREATEBILL = "createBill";

    public static final String FUNC_GETBILLDETAILS = "getBillDetails";

    public static final String FUNC_GETBILLSFORMERCHANT = "getBillsForMerchant";

    public static final String FUNC_GETMISSINGAMOUNT = "getMissingAmount";

    public static final String FUNC_GETPAYMENTDETAILS = "getPaymentDetails";

    public static final String FUNC_GETRECEIPTS = "getReceipts";

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

    public RemoteFunctionCall<List> getReceiptsPerAddress(String _address) {
        final Function function = new Function(FUNC_GETRECEIPTSPERADDRESS, 
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

    public static class ReceiptModel extends DynamicStruct {
        public String uniquePaymentId;

        public String timeStamp;

        public BigInteger billAmount;

        public String billUniqueId;

        public BigInteger amount;

        public ReceiptModel(String uniquePaymentId, String timeStamp, BigInteger billAmount, String billUniqueId, BigInteger amount) {
            super(new org.web3j.abi.datatypes.Utf8String(uniquePaymentId), 
                    new org.web3j.abi.datatypes.Utf8String(timeStamp), 
                    new org.web3j.abi.datatypes.generated.Uint256(billAmount), 
                    new org.web3j.abi.datatypes.Utf8String(billUniqueId), 
                    new org.web3j.abi.datatypes.generated.Uint256(amount));
            this.uniquePaymentId = uniquePaymentId;
            this.timeStamp = timeStamp;
            this.billAmount = billAmount;
            this.billUniqueId = billUniqueId;
            this.amount = amount;
        }

        public ReceiptModel(Utf8String uniquePaymentId, Utf8String timeStamp, Uint256 billAmount, Utf8String billUniqueId, Uint256 amount) {
            super(uniquePaymentId, timeStamp, billAmount, billUniqueId, amount);
            this.uniquePaymentId = uniquePaymentId.getValue();
            this.timeStamp = timeStamp.getValue();
            this.billAmount = billAmount.getValue();
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
