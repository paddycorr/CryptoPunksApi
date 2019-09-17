package dev.corr.CryptoPunksApi.core;

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
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple4;
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
 * <p>Generated with web3j version 4.5.0.
 */
public class CryptoPunksMarket extends Contract {
    private static final String BINARY = "60e0604090815260808190527f616333396166343739333131396565343662626666333531643863623662356660a09081527f323364613630323232313236616464343236386532363131393961323932316260c05262000064916000919062000176565b5060408051808201909152600b8082527f43727970746f50756e6b730000000000000000000000000000000000000000006020909201918252620000ab9160029162000176565b5060006007556008805460ff1916905560018054600160a060020a03191633179055612710600681905560095560408051808201909152600b8082527f43525950544f50554e4b5300000000000000000000000000000000000000000060209092019182526200011e9160039162000176565b506040805180820190915260028082527fcfbe0000000000000000000000000000000000000000000000000000000000006020909201918252620001659160049162000176565b506005805460ff191690556200021b565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10620001b957805160ff1916838001178555620001e9565b82800160010185558215620001e9579182015b82811115620001e9578251825591602001919060010190620001cc565b50620001f7929150620001fb565b5090565b6200021891905b80821115620001f7576000815560010162000202565b90565b611896806200022b6000396000f30060806040526004361061015e5763ffffffff7c010000000000000000000000000000000000000000000000000000000060003504166306fdde038114610163578063088f11f3146101ed578063091dbfd21461023e57806318160ddd1461024b57806323165b7514610272578063313ce5671461028d57806339c5dde6146102b85780633ccfd60b1461034657806351605d801461035b57806352f29a251461037057806358178168146103855780635a3b7e42146103b95780636e743fa9146103ce57806370a08231146104165780637ecedac9146104375780638126c38a1461044c5780638264fe98146104755780638b72a2ec1461048057806395d89b41146104a4578063979bc638146104b9578063a75a9049146104d1578063bf31196f146104f5578063c0d6ce631461051c578063c44193c314610531578063c81d1d5b1461054c578063f3f4370314610564578063f6eeff1e14610585575b600080fd5b34801561016f57600080fd5b5061017861059d565b6040805160208082528351818301528351919283929083019185019080838360005b838110156101b257818101518382015260200161019a565b50505050905090810190601f1680156101df5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b3480156101f957600080fd5b5061020560043561062b565b6040805195151586526020860194909452600160a060020a03928316858501526060850191909152166080830152519081900360a00190f35b610249600435610669565b005b34801561025757600080fd5b506102606107e1565b60408051918252519081900360200190f35b34801561027e57600080fd5b506102496004356024356107e7565b34801561029957600080fd5b506102a2610b3d565b6040805160ff9092168252519081900360200190f35b3480156102c457600080fd5b506040805160206004803580820135838102808601850190965280855261024995369593946024949385019291829185019084908082843750506040805187358901803560208181028481018201909552818452989b9a998901989297509082019550935083925085019084908082843750949750610b469650505050505050565b34801561035257600080fd5b50610249610bb7565b34801561036757600080fd5b50610178610c11565b34801561037c57600080fd5b50610260610c6c565b34801561039157600080fd5b5061039d600435610c72565b60408051600160a060020a039092168252519081900360200190f35b3480156103c557600080fd5b50610178610c8d565b3480156103da57600080fd5b506103e6600435610ce5565b6040805194151585526020850193909352600160a060020a03909116838301526060830152519081900360800190f35b34801561042257600080fd5b50610260600160a060020a0360043516610d1c565b34801561044357600080fd5b50610249610d2e565b34801561045857600080fd5b50610461610d54565b604080519115158252519081900360200190f35b610249600435610d5d565b34801561048c57600080fd5b50610249600160a060020a0360043516602435610fb5565b3480156104b057600080fd5b50610178611192565b3480156104c557600080fd5b506102496004356111ed565b3480156104dd57600080fd5b50610249600160a060020a036004351660243561135a565b34801561050157600080fd5b50610249600435602435600160a060020a036044351661147e565b34801561052857600080fd5b5061026061158c565b34801561053d57600080fd5b50610249600435602435611592565b34801561055857600080fd5b5061024960043561169c565b34801561057057600080fd5b50610260600160a060020a0360043516611760565b34801561059157600080fd5b50610249600435611772565b6003805460408051602060026001851615610100026000190190941693909304601f810184900484028201840190925281815292918301828280156106235780601f106105f857610100808354040283529160200191610623565b820191906000526020600020905b81548152906001019060200180831161060657829003601f168201915b505050505081565b600c602052600090815260409020805460018201546002830154600384015460049094015460ff909316939192600160a060020a0391821692911685565b6000612710821061067957600080fd5b60085460ff16151561068a57600080fd5b6000828152600a6020526040902054600160a060020a031615156106ad57600080fd5b6000828152600a6020526040902054600160a060020a03163314156106d157600080fd5b3415156106dd57600080fd5b506000818152600d60205260409020600381015434116106fc57600080fd5b6000816003015411156107325760038101546002820154600160a060020a03166000908152600e60205260409020805490910190555b604080516080810182526001808252602080830186815233848601818152346060870181815260008b8152600d87528990209751885460ff191690151517885593519587019590955551600286018054600160a060020a031916600160a060020a03909216919091179055905160039094019390935583519182529251919285927f5b859394fabae0c1ba88baffe67e751ab5248d2e879028b8c8d6897b0519f56a9281900390910190a35050565b60065481565b6000808061271085106107f957600080fd5b60085460ff16151561080a57600080fd5b6000858152600a6020526040902054600160a060020a0316331461082d57600080fd5b6000858152600d602052604090206003810154339450909250151561085157600080fd5b838260030154101561086257600080fd5b6002820180546000878152600a602090815260408083208054600160a060020a031916600160a060020a03958616179055878416808452600b83528184208054600019019055855485168452928190208054600190810190915594548151958652905193169391927fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef9281900390910190a360a0604051908101604052806000151581526020018681526020018360020160009054906101000a9004600160a060020a0316600160a060020a03168152602001600081526020016000600160a060020a0316815250600c600087815260200190815260200160002060008201518160000160006101000a81548160ff0219169083151502179055506020820151816001015560408201518160020160006101000a815481600160a060020a030219169083600160a060020a031602179055506060820151816003015560808201518160040160006101000a815481600160a060020a030219169083600160a060020a03160217905550905050816003015490506080604051908101604052806000151581526020018681526020016000600160a060020a031681526020016000815250600d600087815260200190815260200160002060008201518160000160006101000a81548160ff0219169083151502179055506020820151816001015560408201518160020160006101000a815481600160a060020a030219169083600160a060020a031602179055506060820151816003015590505080600e600085600160a060020a0316600160a060020a03168152602001908152602001600020600082825401925050819055508160020160009054906101000a9004600160a060020a0316600160a060020a031683600160a060020a0316867f58e5d5a525e3b40bc15abaa38b5882678db1ee68befd2f60bafe3a7fd06db9e385600301546040518082815260200191505060405180910390a45050505050565b60055460ff1681565b6001546000908190600160a060020a03163314610b6257600080fd5b5050815160005b81811015610bb157610ba98482815181101515610b8257fe5b906020019060200201518483815181101515610b9a57fe5b9060200190602002015161135a565b600101610b69565b50505050565b60085460009060ff161515610bcb57600080fd5b50336000818152600e6020526040808220805490839055905190929183156108fc02918491818181858888f19350505050158015610c0d573d6000803e3d6000fd5b5050565b6000805460408051602060026001851615610100026000190190941693909304601f810184900484028201840190925281815292918301828280156106235780601f106105f857610100808354040283529160200191610623565b60075481565b600a60205260009081526040902054600160a060020a031681565b6002805460408051602060018416156101000260001901909316849004601f810184900484028201840190925281815292918301828280156106235780601f106105f857610100808354040283529160200191610623565b600d60205260009081526040902080546001820154600283015460039093015460ff909216929091600160a060020a039091169084565b600b6020526000908152604090205481565b600154600160a060020a03163314610d4557600080fd5b6008805460ff19166001179055565b60085460ff1681565b6008546000908190819060ff161515610d7557600080fd5b6000848152600c6020526040902092506127108410610d9357600080fd5b825460ff161515610da357600080fd5b6004830154600160a060020a031615801590610dcc57506004830154600160a060020a03163314155b15610dd657600080fd5b8260030154341015610de757600080fd5b6000848152600a60205260409020546002840154600160a060020a03908116911614610e1257600080fd5b60028301546000858152600a602090815260408083208054600160a060020a03191633908117909155600160a060020a03909416808452600b8352818420805460001901905584845292819020805460019081019091558151908152905192955085927fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef929181900390910190a3610ea984611772565b600160a060020a0382166000818152600e60209081526040918290208054349081019091558251908152915133939288927f58e5d5a525e3b40bc15abaa38b5882678db1ee68befd2f60bafe3a7fd06db9e392918290030190a4506000838152600d602052604090206002810154600160a060020a0316331415610bb157600390810154336000908152600e602090815260408083208054909401909355825160808101845282815280820188815281850184815260608301858152998552600d909352939092209151825460ff1916901515178255915160018201559051600282018054600160a060020a031916600160a060020a0390921691909117905593519301929092555050565b60085460009060ff161515610fc957600080fd5b6000828152600a6020526040902054600160a060020a03163314610fec57600080fd5b6127108210610ffa57600080fd5b6000828152600c602052604090205460ff161561101a5761101a82611772565b6000828152600a602090815260408083208054600160a060020a031916600160a060020a03881690811790915533808552600b845282852080546000190190558185529382902080546001908101909155825190815291519093927fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef928290030190a3604080518381529051600160a060020a0385169133917f05af636b70da6819000c49f85b21fa82081c632069bb626f30932034099107d89181900360200190a3506000818152600d602052604090206002810154600160a060020a038481169116141561118d57600381810154600160a060020a038581166000908152600e6020908152604080832080549095019094558351608081018552828152808201888152818601848152606083018581528a8652600d909452959093209051815460ff1916901515178155915160018301559251600282018054600160a060020a031916919093161790915590519101555b505050565b6004805460408051602060026001851615610100026000190190941693909304601f810184900484028201840190925281815292918301828280156106235780601f106105f857610100808354040283529160200191610623565b60008061271083106111fe57600080fd5b60085460ff16151561120f57600080fd5b6000838152600a6020526040902054600160a060020a0316151561123257600080fd5b6000838152600a6020526040902054600160a060020a031633141561125657600080fd5b6000838152600d602052604090206002810154909250600160a060020a0316331461128057600080fd5b60038201546040805191825251339185917f6f30e1ee4d81dcc7a8a478577f65d2ed2edb120565960ac45fe7c50551c879329181900360200190a3506003818101546040805160808101825260008082526020808301888152838501838152606085018481528a8552600d9093528584209451855460ff19169015151785559051600185015551600284018054600160a060020a031916600160a060020a0390921691909117905551919094015551909133916108fc84150291849190818181858888f19350505050158015610bb1573d6000803e3d6000fd5b600154600160a060020a0316331461137157600080fd5b60085460ff161561138157600080fd5b612710811061138f57600080fd5b6000818152600a6020526040902054600160a060020a03838116911614610c0d576000818152600a6020526040902054600160a060020a0316156113fe576000818152600a6020908152604080832054600160a060020a03168352600b90915290208054600019019055611409565b600980546000190190555b6000818152600a602090815260408083208054600160a060020a031916600160a060020a038716908117909155808452600b83529281902080546001019055805184815290517f8a0e37b73a0d9c82e205d4d1a3ff3d0b57ce5f4d7bccf6bac03336dc101cb7ba929181900390910190a25050565b60085460ff16151561148f57600080fd5b6000838152600a6020526040902054600160a060020a031633146114b257600080fd5b61271083106114c057600080fd5b6040805160a081018252600180825260208083018781523384860190815260608501888152600160a060020a038881166080880181815260008d8152600c88528a90209851895460ff19169015151789559451968801969096559151600287018054600160a060020a03199081169285169290921790559051600387015591516004909501805490921694169390931790925582518581529251909286927f3c7b682d5da98001a9b8cbda6c647d2c63d698a4184fd1d55e2ce7b66f5d21eb92918290030190a3505050565b60095481565b60085460ff1615156115a357600080fd5b6000828152600a6020526040902054600160a060020a031633146115c657600080fd5b61271082106115d457600080fd5b6040805160a0810182526001808252602080830186815233848601908152606085018781526000608087018181528a8252600c86528882209751885460ff19169015151788559351958701959095559051600286018054600160a060020a0319908116600160a060020a039384161790915591516003870155915160049095018054909116949091169390931790925582518481529251909285927f3c7b682d5da98001a9b8cbda6c647d2c63d698a4184fd1d55e2ce7b66f5d21eb92918290030190a35050565b60085460ff1615156116ad57600080fd5b60095415156116bb57600080fd5b6000818152600a6020526040902054600160a060020a0316156116dd57600080fd5b61271081106116eb57600080fd5b6000818152600a602090815260408083208054600160a060020a03191633908117909155808452600b8352928190208054600101905560098054600019019055805184815290517f8a0e37b73a0d9c82e205d4d1a3ff3d0b57ce5f4d7bccf6bac03336dc101cb7ba929181900390910190a250565b600e6020526000908152604090205481565b60085460ff16151561178357600080fd5b6000818152600a6020526040902054600160a060020a031633146117a657600080fd5b61271081106117b457600080fd5b6040805160a08101825260008082526020808301858152338486019081526060850184815260808601858152888652600c9094528685209551865460ff19169015151786559151600186015551600285018054600160a060020a0319908116600160a060020a0393841617909155915160038601559151600490940180549091169390911692909217909155905182917fb0e0a660b4e50f26f0b7ce75c24655fc76cc66e3334a54ff410277229fa10bd491a2505600a165627a7a72305820b9163109330f1adae6eb5f3cbde93309666aed982ff3706f0e0bb0c7fbb7f0830029";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_PUNKSOFFEREDFORSALE = "punksOfferedForSale";

    public static final String FUNC_ENTERBIDFORPUNK = "enterBidForPunk";

    public static final String FUNC_TOTALSUPPLY = "totalSupply";

    public static final String FUNC_ACCEPTBIDFORPUNK = "acceptBidForPunk";

    public static final String FUNC_DECIMALS = "decimals";

    public static final String FUNC_SETINITIALOWNERS = "setInitialOwners";

    public static final String FUNC_WITHDRAW = "withdraw";

    public static final String FUNC_IMAGEHASH = "imageHash";

    public static final String FUNC_NEXTPUNKINDEXTOASSIGN = "nextPunkIndexToAssign";

    public static final String FUNC_PUNKINDEXTOADDRESS = "punkIndexToAddress";

    public static final String FUNC_STANDARD = "standard";

    public static final String FUNC_PUNKBIDS = "punkBids";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_ALLINITIALOWNERSASSIGNED = "allInitialOwnersAssigned";

    public static final String FUNC_ALLPUNKSASSIGNED = "allPunksAssigned";

    public static final String FUNC_BUYPUNK = "buyPunk";

    public static final String FUNC_TRANSFERPUNK = "transferPunk";

    public static final String FUNC_SYMBOL = "symbol";

    public static final String FUNC_WITHDRAWBIDFORPUNK = "withdrawBidForPunk";

    public static final String FUNC_SETINITIALOWNER = "setInitialOwner";

    public static final String FUNC_OFFERPUNKFORSALETOADDRESS = "offerPunkForSaleToAddress";

    public static final String FUNC_PUNKSREMAININGTOASSIGN = "punksRemainingToAssign";

    public static final String FUNC_OFFERPUNKFORSALE = "offerPunkForSale";

    public static final String FUNC_GETPUNK = "getPunk";

    public static final String FUNC_PENDINGWITHDRAWALS = "pendingWithdrawals";

    public static final String FUNC_PUNKNOLONGERFORSALE = "punkNoLongerForSale";

    public static final Event ASSIGN_EVENT = new Event("Assign", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event TRANSFER_EVENT = new Event("Transfer", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event PUNKTRANSFER_EVENT = new Event("PunkTransfer", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event PUNKOFFERED_EVENT = new Event("PunkOffered", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event PUNKBIDENTERED_EVENT = new Event("PunkBidEntered", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event PUNKBIDWITHDRAWN_EVENT = new Event("PunkBidWithdrawn", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event PUNKBOUGHT_EVENT = new Event("PunkBought", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event PUNKNOLONGERFORSALE_EVENT = new Event("PunkNoLongerForSale", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}));
    ;

    @Deprecated
    protected CryptoPunksMarket(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected CryptoPunksMarket(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected CryptoPunksMarket(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected CryptoPunksMarket(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<String> name() {
        final Function function = new Function(FUNC_NAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<Tuple5<Boolean, BigInteger, String, BigInteger, String>> punksOfferedForSale(BigInteger param0) {
        final Function function = new Function(FUNC_PUNKSOFFEREDFORSALE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}, new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Address>() {}));
        return new RemoteFunctionCall<Tuple5<Boolean, BigInteger, String, BigInteger, String>>(function,
                new Callable<Tuple5<Boolean, BigInteger, String, BigInteger, String>>() {
                    @Override
                    public Tuple5<Boolean, BigInteger, String, BigInteger, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple5<Boolean, BigInteger, String, BigInteger, String>(
                                (Boolean) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue(), 
                                (String) results.get(4).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> enterBidForPunk(BigInteger punkIndex, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_ENTERBIDFORPUNK, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(punkIndex)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<BigInteger> totalSupply() {
        final Function function = new Function(FUNC_TOTALSUPPLY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> acceptBidForPunk(BigInteger punkIndex, BigInteger minPrice) {
        final Function function = new Function(
                FUNC_ACCEPTBIDFORPUNK, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(punkIndex), 
                new org.web3j.abi.datatypes.generated.Uint256(minPrice)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> decimals() {
        final Function function = new Function(FUNC_DECIMALS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> setInitialOwners(List<String> addresses, List<BigInteger> indices) {
        final Function function = new Function(
                FUNC_SETINITIALOWNERS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(addresses, org.web3j.abi.datatypes.Address.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Uint256>(
                        org.web3j.abi.datatypes.generated.Uint256.class,
                        org.web3j.abi.Utils.typeMap(indices, org.web3j.abi.datatypes.generated.Uint256.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> withdraw() {
        final Function function = new Function(
                FUNC_WITHDRAW, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> imageHash() {
        final Function function = new Function(FUNC_IMAGEHASH, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> nextPunkIndexToAssign() {
        final Function function = new Function(FUNC_NEXTPUNKINDEXTOASSIGN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> punkIndexToAddress(BigInteger param0) {
        final Function function = new Function(FUNC_PUNKINDEXTOADDRESS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> standard() {
        final Function function = new Function(FUNC_STANDARD, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<Tuple4<Boolean, BigInteger, String, BigInteger>> punkBids(BigInteger param0) {
        final Function function = new Function(FUNC_PUNKBIDS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}, new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple4<Boolean, BigInteger, String, BigInteger>>(function,
                new Callable<Tuple4<Boolean, BigInteger, String, BigInteger>>() {
                    @Override
                    public Tuple4<Boolean, BigInteger, String, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple4<Boolean, BigInteger, String, BigInteger>(
                                (Boolean) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue());
                    }
                });
    }

    public RemoteFunctionCall<BigInteger> balanceOf(String param0) {
        final Function function = new Function(FUNC_BALANCEOF, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> allInitialOwnersAssigned() {
        final Function function = new Function(
                FUNC_ALLINITIALOWNERSASSIGNED, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> allPunksAssigned() {
        final Function function = new Function(FUNC_ALLPUNKSASSIGNED, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> buyPunk(BigInteger punkIndex, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_BUYPUNK, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(punkIndex)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> transferPunk(String to, BigInteger punkIndex) {
        final Function function = new Function(
                FUNC_TRANSFERPUNK, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.generated.Uint256(punkIndex)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> symbol() {
        final Function function = new Function(FUNC_SYMBOL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> withdrawBidForPunk(BigInteger punkIndex) {
        final Function function = new Function(
                FUNC_WITHDRAWBIDFORPUNK, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(punkIndex)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setInitialOwner(String to, BigInteger punkIndex) {
        final Function function = new Function(
                FUNC_SETINITIALOWNER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.generated.Uint256(punkIndex)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> offerPunkForSaleToAddress(BigInteger punkIndex, BigInteger minSalePriceInWei, String toAddress) {
        final Function function = new Function(
                FUNC_OFFERPUNKFORSALETOADDRESS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(punkIndex), 
                new org.web3j.abi.datatypes.generated.Uint256(minSalePriceInWei), 
                new org.web3j.abi.datatypes.Address(160, toAddress)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> punksRemainingToAssign() {
        final Function function = new Function(FUNC_PUNKSREMAININGTOASSIGN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> offerPunkForSale(BigInteger punkIndex, BigInteger minSalePriceInWei) {
        final Function function = new Function(
                FUNC_OFFERPUNKFORSALE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(punkIndex), 
                new org.web3j.abi.datatypes.generated.Uint256(minSalePriceInWei)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> getPunk(BigInteger punkIndex) {
        final Function function = new Function(
                FUNC_GETPUNK, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(punkIndex)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> pendingWithdrawals(String param0) {
        final Function function = new Function(FUNC_PENDINGWITHDRAWALS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> punkNoLongerForSale(BigInteger punkIndex) {
        final Function function = new Function(
                FUNC_PUNKNOLONGERFORSALE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(punkIndex)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public List<AssignEventResponse> getAssignEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ASSIGN_EVENT, transactionReceipt);
        ArrayList<AssignEventResponse> responses = new ArrayList<AssignEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            AssignEventResponse typedResponse = new AssignEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.to = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.punkIndex = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<AssignEventResponse> assignEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, AssignEventResponse>() {
            @Override
            public AssignEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(ASSIGN_EVENT, log);
                AssignEventResponse typedResponse = new AssignEventResponse();
                typedResponse.log = log;
                typedResponse.to = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.punkIndex = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<AssignEventResponse> assignEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ASSIGN_EVENT));
        return assignEventFlowable(filter);
    }

    public List<TransferEventResponse> getTransferEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(TRANSFER_EVENT, transactionReceipt);
        ArrayList<TransferEventResponse> responses = new ArrayList<TransferEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TransferEventResponse typedResponse = new TransferEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<TransferEventResponse> transferEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, TransferEventResponse>() {
            @Override
            public TransferEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(TRANSFER_EVENT, log);
                TransferEventResponse typedResponse = new TransferEventResponse();
                typedResponse.log = log;
                typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<TransferEventResponse> transferEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRANSFER_EVENT));
        return transferEventFlowable(filter);
    }

    public List<PunkTransferEventResponse> getPunkTransferEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(PUNKTRANSFER_EVENT, transactionReceipt);
        ArrayList<PunkTransferEventResponse> responses = new ArrayList<PunkTransferEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            PunkTransferEventResponse typedResponse = new PunkTransferEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.punkIndex = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<PunkTransferEventResponse> punkTransferEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, PunkTransferEventResponse>() {
            @Override
            public PunkTransferEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(PUNKTRANSFER_EVENT, log);
                PunkTransferEventResponse typedResponse = new PunkTransferEventResponse();
                typedResponse.log = log;
                typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.punkIndex = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<PunkTransferEventResponse> punkTransferEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PUNKTRANSFER_EVENT));
        return punkTransferEventFlowable(filter);
    }

    public List<PunkOfferedEventResponse> getPunkOfferedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(PUNKOFFERED_EVENT, transactionReceipt);
        ArrayList<PunkOfferedEventResponse> responses = new ArrayList<PunkOfferedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            PunkOfferedEventResponse typedResponse = new PunkOfferedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.punkIndex = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.toAddress = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.minValue = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<PunkOfferedEventResponse> punkOfferedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, PunkOfferedEventResponse>() {
            @Override
            public PunkOfferedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(PUNKOFFERED_EVENT, log);
                PunkOfferedEventResponse typedResponse = new PunkOfferedEventResponse();
                typedResponse.log = log;
                typedResponse.punkIndex = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.toAddress = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.minValue = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<PunkOfferedEventResponse> punkOfferedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PUNKOFFERED_EVENT));
        return punkOfferedEventFlowable(filter);
    }

    public List<PunkBidEnteredEventResponse> getPunkBidEnteredEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(PUNKBIDENTERED_EVENT, transactionReceipt);
        ArrayList<PunkBidEnteredEventResponse> responses = new ArrayList<PunkBidEnteredEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            PunkBidEnteredEventResponse typedResponse = new PunkBidEnteredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.punkIndex = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.fromAddress = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<PunkBidEnteredEventResponse> punkBidEnteredEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, PunkBidEnteredEventResponse>() {
            @Override
            public PunkBidEnteredEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(PUNKBIDENTERED_EVENT, log);
                PunkBidEnteredEventResponse typedResponse = new PunkBidEnteredEventResponse();
                typedResponse.log = log;
                typedResponse.punkIndex = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.fromAddress = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<PunkBidEnteredEventResponse> punkBidEnteredEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PUNKBIDENTERED_EVENT));
        return punkBidEnteredEventFlowable(filter);
    }

    public List<PunkBidWithdrawnEventResponse> getPunkBidWithdrawnEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(PUNKBIDWITHDRAWN_EVENT, transactionReceipt);
        ArrayList<PunkBidWithdrawnEventResponse> responses = new ArrayList<PunkBidWithdrawnEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            PunkBidWithdrawnEventResponse typedResponse = new PunkBidWithdrawnEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.punkIndex = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.fromAddress = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<PunkBidWithdrawnEventResponse> punkBidWithdrawnEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, PunkBidWithdrawnEventResponse>() {
            @Override
            public PunkBidWithdrawnEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(PUNKBIDWITHDRAWN_EVENT, log);
                PunkBidWithdrawnEventResponse typedResponse = new PunkBidWithdrawnEventResponse();
                typedResponse.log = log;
                typedResponse.punkIndex = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.fromAddress = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<PunkBidWithdrawnEventResponse> punkBidWithdrawnEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PUNKBIDWITHDRAWN_EVENT));
        return punkBidWithdrawnEventFlowable(filter);
    }

    public List<PunkBoughtEventResponse> getPunkBoughtEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(PUNKBOUGHT_EVENT, transactionReceipt);
        ArrayList<PunkBoughtEventResponse> responses = new ArrayList<PunkBoughtEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            PunkBoughtEventResponse typedResponse = new PunkBoughtEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.punkIndex = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.fromAddress = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.toAddress = (String) eventValues.getIndexedValues().get(2).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<PunkBoughtEventResponse> punkBoughtEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, PunkBoughtEventResponse>() {
            @Override
            public PunkBoughtEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(PUNKBOUGHT_EVENT, log);
                PunkBoughtEventResponse typedResponse = new PunkBoughtEventResponse();
                typedResponse.log = log;
                typedResponse.punkIndex = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.fromAddress = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.toAddress = (String) eventValues.getIndexedValues().get(2).getValue();
                typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<PunkBoughtEventResponse> punkBoughtEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PUNKBOUGHT_EVENT));
        return punkBoughtEventFlowable(filter);
    }

    public List<PunkNoLongerForSaleEventResponse> getPunkNoLongerForSaleEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(PUNKNOLONGERFORSALE_EVENT, transactionReceipt);
        ArrayList<PunkNoLongerForSaleEventResponse> responses = new ArrayList<PunkNoLongerForSaleEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            PunkNoLongerForSaleEventResponse typedResponse = new PunkNoLongerForSaleEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.punkIndex = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<PunkNoLongerForSaleEventResponse> punkNoLongerForSaleEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, PunkNoLongerForSaleEventResponse>() {
            @Override
            public PunkNoLongerForSaleEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(PUNKNOLONGERFORSALE_EVENT, log);
                PunkNoLongerForSaleEventResponse typedResponse = new PunkNoLongerForSaleEventResponse();
                typedResponse.log = log;
                typedResponse.punkIndex = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<PunkNoLongerForSaleEventResponse> punkNoLongerForSaleEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PUNKNOLONGERFORSALE_EVENT));
        return punkNoLongerForSaleEventFlowable(filter);
    }

    @Deprecated
    public static CryptoPunksMarket load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new CryptoPunksMarket(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static CryptoPunksMarket load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new CryptoPunksMarket(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static CryptoPunksMarket load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new CryptoPunksMarket(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static CryptoPunksMarket load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new CryptoPunksMarket(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<CryptoPunksMarket> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, BigInteger initialWeiValue) {
        return deployRemoteCall(CryptoPunksMarket.class, web3j, credentials, contractGasProvider, BINARY, "", initialWeiValue);
    }

    public static RemoteCall<CryptoPunksMarket> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, BigInteger initialWeiValue) {
        return deployRemoteCall(CryptoPunksMarket.class, web3j, transactionManager, contractGasProvider, BINARY, "", initialWeiValue);
    }

    @Deprecated
    public static RemoteCall<CryptoPunksMarket> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployRemoteCall(CryptoPunksMarket.class, web3j, credentials, gasPrice, gasLimit, BINARY, "", initialWeiValue);
    }

    @Deprecated
    public static RemoteCall<CryptoPunksMarket> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployRemoteCall(CryptoPunksMarket.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "", initialWeiValue);
    }

    public static class AssignEventResponse extends BaseEventResponse {
        public String to;

        public BigInteger punkIndex;
    }

    public static class TransferEventResponse extends BaseEventResponse {
        public String from;

        public String to;

        public BigInteger value;
    }

    public static class PunkTransferEventResponse extends BaseEventResponse {
        public String from;

        public String to;

        public BigInteger punkIndex;
    }

    public static class PunkOfferedEventResponse extends BaseEventResponse {
        public BigInteger punkIndex;

        public String toAddress;

        public BigInteger minValue;
    }

    public static class PunkBidEnteredEventResponse extends BaseEventResponse {
        public BigInteger punkIndex;

        public String fromAddress;

        public BigInteger value;
    }

    public static class PunkBidWithdrawnEventResponse extends BaseEventResponse {
        public BigInteger punkIndex;

        public String fromAddress;

        public BigInteger value;
    }

    public static class PunkBoughtEventResponse extends BaseEventResponse {
        public BigInteger punkIndex;

        public String fromAddress;

        public String toAddress;

        public BigInteger value;
    }

    public static class PunkNoLongerForSaleEventResponse extends BaseEventResponse {
        public BigInteger punkIndex;
    }
}
