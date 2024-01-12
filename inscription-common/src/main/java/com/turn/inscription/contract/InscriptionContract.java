package com.turn.inscription.contract;

import com.bubble.abi.solidity.FunctionEncoder;
import com.bubble.abi.solidity.TypeReference;
import com.bubble.abi.solidity.Utils;
import com.bubble.abi.solidity.datatypes.*;
import com.bubble.abi.solidity.datatypes.generated.Bytes32;
import com.bubble.abi.solidity.datatypes.generated.Uint256;
import com.bubble.crypto.Credentials;
import com.bubble.protocol.Web3j;
import com.bubble.protocol.core.RemoteCall;
import com.bubble.protocol.core.methods.response.TransactionReceipt;
import com.bubble.tuples.generated.Tuple2;
import com.bubble.tuples.generated.Tuple9;
import com.bubble.tx.Contract;
import com.bubble.tx.TransactionManager;
import com.bubble.tx.gas.ContractGasProvider;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.4.1.
 */
@SuppressWarnings("rawtypes")
public class InscriptionContract extends Contract {
    public static final String BINARY = "0x60806040523480156200001157600080fd5b5060405162001858380380620018588339810160408190526200003491620002af565b81600010620000b05760405162461bcd60e51b815260206004820152603b60248201527f546865206e756d626572206f6620636f696e73206d696e74656420617420612060448201527f74696d65206d7573742062652067726561746572207468616e2030000000000060648201526084015b60405180910390fd5b826000108015620000c15750818110155b8015620000ce5750808310155b6200011c5760405162461bcd60e51b815260206004820152601a60248201527f457863656564206d6178696d756d2063697263756c6174696f6e0000000000006044820152606401620000a7565b620001288282620003b4565b15620001b45760405162461bcd60e51b815260206004820152604e60248201527f416e20696e7465676572206d756c7469706c65206f6620746865206d6178696d60448201527f756d20636f696e2073697a652074686174206973206e6f7420746865206d696e60648201526d696d756d20636f696e2073697a6560901b608482015260a401620000a7565b620001c08282620003cb565b600755600080546001600160a01b03191633179055426001556002620001e7858262000471565b506040516001600160601b03193060601b16602082015260340160408051601f1981840301815290829052805160209091012060038190556004859055600584905560068390556000600881905560098190556001600a8190559054905491927f4d90f2d18691a1aebfa9fce156cdeee8fbe31d948730893073bcc330f2a431489262000287926001600160a01b0316916002908990899089906200053d565b60405180910390a250505050620005fb565b634e487b7160e01b600052604160045260246000fd5b60008060008060808587031215620002c657600080fd5b84516001600160401b0380821115620002de57600080fd5b818701915087601f830112620002f357600080fd5b81518181111562000308576200030862000299565b604051601f8201601f19908116603f0116810190838211818310171562000333576200033362000299565b81604052828152602093508a848487010111156200035057600080fd5b600091505b8282101562000374578482018401518183018501529083019062000355565b60009281018401929092525090870151604088015160609098015191999098509095509350505050565b634e487b7160e01b600052601260045260246000fd5b600082620003c657620003c66200039e565b500690565b600082620003dd57620003dd6200039e565b500490565b600181811c90821680620003f757607f821691505b6020821081036200041857634e487b7160e01b600052602260045260246000fd5b50919050565b601f8211156200046c57600081815260208120601f850160051c81016020861015620004475750805b601f850160051c820191505b81811015620004685782815560010162000453565b5050505b505050565b81516001600160401b038111156200048d576200048d62000299565b620004a5816200049e8454620003e2565b846200041e565b602080601f831160018114620004dd5760008415620004c45750858301515b600019600386901b1c1916600185901b17855562000468565b600085815260208120601f198616915b828110156200050e57888601518255948401946001909101908401620004ed565b50858210156200052d5787850151600019600388901b60f8161c191681555b5050505050600190811b01905550565b60018060a01b038716815260006020878184015260c06040840152600087546200056781620003e2565b8060c087015260e06001808416600081146200058c5760018114620005a757620005d7565b60ff1985168984015283151560051b890183019550620005d7565b8c6000528660002060005b85811015620005cf5781548b8201860152908301908801620005b2565b8a0184019650505b505050505060608401969096525050608081019290925260a0909101529392505050565b61124d806200060b6000396000f3fe608060405234801561001057600080fd5b50600436106100575760003560e01c806333a949a91461005c5780638c4a76b61461008f578063ac3c9952146100ac578063d85d3d27146100c1578063ed626788146100d4575b600080fd5b6040517ffb69af88c98f006e0a73f84cb295f4b9008a4feb2770084e18acbf12041a00fd81526020015b60405180910390f35b6100976100f5565b60405161008699989796959493929190610c96565b6100bf6100ba366004610d29565b6101d4565b005b6100bf6100cf366004610dfa565b6105a8565b6100e76100e2366004610e6c565b6107d2565b604051610086929190610ec9565b600080606060008060008060008060008054906101000a90046001600160a01b0316985060015497506002805461012b90610f3b565b80601f016020809104026020016040519081016040528092919081815260200182805461015790610f3b565b80156101a45780601f10610179576101008083540402835291602001916101a4565b820191906000526020600020905b81548152906001019060200180831161018757829003601f168201915b50505050509650600354955060045494506005549350600654925060085491506009549050909192939495969798565b336001600160a01b038316036102415760405162461bcd60e51b815260206004820152602760248201527f4572726f7220696e207472616e7366657272696e67206d6f6e657920746f207960448201526637bab939b2b63360c91b60648201526084015b60405180910390fd5b60008151116102a35760405162461bcd60e51b815260206004820152602860248201527f546865207472616e736665727265642070617065722069642063616e6e6f7420604482015267626520656d70747960c01b6064820152608401610238565b336000908152600b60209081526040808320805482518185028101850190935280835291929091908301828280156102fa57602002820191906000526020600020905b8154815260200190600101908083116102e6575b5050505050905081518151101561036e5760405162461bcd60e51b815260206004820152603260248201527f546865207472616e73666572726564207061706572206964206c6973742068616044820152710e640e8d0ca40eee4dedcce40d8cadccee8d60731b6064820152608401610238565b6001600160a01b0383166000908152600b60205260408120805490915b84518110156104db5760008582815181106103a8576103a8610f75565b602002602001015190506000600c600083815260200190815260200160002060000154116104285760405162461bcd60e51b815260206004820152602760248201527f546865207472616e7366657272656420706170657220696420646f6573206e6f6044820152661d08195e1a5cdd60ca1b6064820152608401610238565b6000806104348361099b565b91509150816104ab5760405162461bcd60e51b815260206004820152603a60248201527f706964206973206f776e6564206279206e6f6e2d73656e64657220616e64206360448201527f616e6e6f74206265207573656420666f72207472616e736665720000000000006064820152608401610238565b6104b481610a52565b505083546001810185556000858152602090200155806104d381610fa1565b91505061038b565b50801580156104f85750336000908152600b602052604090205415155b1561051b576001600960008282546105109190610fba565b909155506105569050565b6000811180156105385750336000908152600b6020526040902054155b15610556576001600960008282546105509190610fd3565b90915550505b846001600160a01b0316336001600160a01b03167f4187831f3a75da298b400e245a588d6a6d6193fe794473a7129647592324faa4866040516105999190610fe6565b60405180910390a35050505050565b6005546008546105b89190610fba565b600454101561063b5760405162461bcd60e51b815260206004820152604360248201527f4265796f6e6420746865206d6178696d756d2063697263756c6174696f6e2c2060448201527f6e6f206d6f726520696e736372697074696f6e732063616e206265207072696e6064820152621d195960ea1b608482015260a401610238565b336000908152600b60205260409020805460075481106106c35760405162461bcd60e51b815260206004820152603c60248201527f546865206164647265737320686173207265616368656420746865206d61786960448201527f6d756d206e756d626572206f6620656e67726176656420636f696e73000000006064820152608401610238565b806000036106e4576001600960008282546106de9190610fba565b90915550505b600a546000818152600c6020526040812091825561070142610b3d565b905085858260405160200161071893929190610ff9565b604051602081830303815290604052826001019081610737919061106f565b50600a548454600181018655600086815260208120909101919091556005546008805491929091610769908490610fba565b925050819055506001600a60008282546107839190610fba565b9091555050815460085460405133917f704496b72f6d3fd6e462fa31e27faddedde127bc9942bc94320e0c115b00cedf916107c291600188019161112f565b60405180910390a3505050505050565b606080600b6000846001600160a01b03166001600160a01b0316815260200190815260200160002080548060200260200160405190810160405280929190818152602001828054801561084457602002820191906000526020600020905b815481526020019060010190808311610830575b50505050509150815167ffffffffffffffff81111561086557610865610d13565b60405190808252806020026020018201604052801561089857816020015b60608152602001906001900390816108835790505b50905060005b82518110156109955760008382815181106108bb576108bb610f75565b60200260200101519050600c600082815260200190815260200160002060010180546108e690610f3b565b80601f016020809104026020016040519081016040528092919081815260200182805461091290610f3b565b801561095f5780601f106109345761010080835404028352916020019161095f565b820191906000526020600020905b81548152906001019060200180831161094257829003601f168201915b505050505083838151811061097657610976610f75565b602002602001018190525050808061098d90610fa1565b91505061089e565b50915091565b336000908152600b602090815260408083208054825181850281018501909352808352849384939291908301828280156109f457602002820191906000526020600020905b8154815260200190600101908083116109e0575b5050505050905060005b8151811015610a4557818181518110610a1957610a19610f75565b60200260200101518503610a335760019590945092505050565b80610a3d81610fa1565b9150506109fe565b5060009485945092505050565b336000908152600b6020526040902080548210610aa45760405162461bcd60e51b815260206004820152601060248201526f1a5b99195e081a5cc818dbdc9c9958dd60821b6044820152606401610238565b815b8154610ab490600190610fd3565b811015610b125781610ac7826001610fba565b81548110610ad757610ad7610f75565b9060005260206000200154828281548110610af457610af4610f75565b60009182526020909120015580610b0a81610fa1565b915050610aa6565b5080805480610b2357610b236111c3565b600190038181906000526020600020016000905590555050565b606081600003610b645750506040805180820190915260018152600360fc1b602082015290565b8160005b8115610b8e5780610b7881610fa1565b9150610b879050600a836111ef565b9150610b68565b60008167ffffffffffffffff811115610ba957610ba9610d13565b6040519080825280601f01601f191660200182016040528015610bd3576020820181803683370190505b5090505b8415610c3e57610be8600183610fd3565b9150610bf5600a86611203565b610c00906030610fba565b60f81b818381518110610c1557610c15610f75565b60200101906001600160f81b031916908160001a905350610c37600a866111ef565b9450610bd7565b949350505050565b60005b83811015610c61578181015183820152602001610c49565b50506000910152565b60008151808452610c82816020860160208601610c46565b601f01601f19169290920160200192915050565b6001600160a01b038a1681526020810189905261012060408201819052600090610cc28382018b610c6a565b60608401999099525050608081019590955260a085019390935260c084019190915260e0830152610100909101529392505050565b80356001600160a01b0381168114610d0e57600080fd5b919050565b634e487b7160e01b600052604160045260246000fd5b60008060408385031215610d3c57600080fd5b610d4583610cf7565b915060208084013567ffffffffffffffff80821115610d6357600080fd5b818601915086601f830112610d7757600080fd5b813581811115610d8957610d89610d13565b8060051b604051601f19603f83011681018181108582111715610dae57610dae610d13565b604052918252848201925083810185019189831115610dcc57600080fd5b938501935b82851015610dea57843584529385019392850192610dd1565b8096505050505050509250929050565b60008060208385031215610e0d57600080fd5b823567ffffffffffffffff80821115610e2557600080fd5b818501915085601f830112610e3957600080fd5b813581811115610e4857600080fd5b866020828501011115610e5a57600080fd5b60209290920196919550909350505050565b600060208284031215610e7e57600080fd5b610e8782610cf7565b9392505050565b600081518084526020808501945080840160005b83811015610ebe57815187529582019590820190600101610ea2565b509495945050505050565b604081526000610edc6040830185610e8e565b6020838203818501528185518084528284019150828160051b85010183880160005b83811015610f2c57601f19878403018552610f1a838351610c6a565b94860194925090850190600101610efe565b50909998505050505050505050565b600181811c90821680610f4f57607f821691505b602082108103610f6f57634e487b7160e01b600052602260045260246000fd5b50919050565b634e487b7160e01b600052603260045260246000fd5b634e487b7160e01b600052601160045260246000fd5b600060018201610fb357610fb3610f8b565b5060010190565b80820180821115610fcd57610fcd610f8b565b92915050565b81810381811115610fcd57610fcd610f8b565b602081526000610e876020830184610e8e565b828482376000838201600081528351611016818360208801610c46565b0195945050505050565b601f82111561106a57600081815260208120601f850160051c810160208610156110475750805b601f850160051c820191505b8181101561106657828155600101611053565b5050505b505050565b815167ffffffffffffffff81111561108957611089610d13565b61109d816110978454610f3b565b84611020565b602080601f8311600181146110d257600084156110ba5750858301515b600019600386901b1c1916600185901b178555611066565b600085815260208120601f198616915b82811015611101578886015182559484019460019091019084016110e2565b508582101561111f5787850151600019600388901b60f8161c191681555b5050505050600190811b01905550565b60408152600080845461114181610f3b565b8060408601526060600180841660008114611163576001811461117d576111ae565b60ff1985168884015283151560051b8801830195506111ae565b8960005260208060002060005b868110156111a55781548b820187015290840190820161118a565b8a018501975050505b50505050506020929092019290925292915050565b634e487b7160e01b600052603160045260246000fd5b634e487b7160e01b600052601260045260246000fd5b6000826111fe576111fe6111d9565b500490565b600082611212576112126111d9565b50069056fea26469706673582212200bcaa437b8cb1327c320fa2118bf7dfbc7c04457ee72154c7f71b20a2fde9e2f64736f6c63430008110033";

    public static final String FUNC_BATCHTRANSFER = "batchTransfer";

    public static final String FUNC_FUNCSIGN = "funcSign";

    public static final String FUNC_GETINSCRIPTIONINFO = "getInScriptionInfo";

    public static final String FUNC_GETUSERPAPERINFOS = "getUserPaperInfos";

    public static final String FUNC_MINT = "mint";

    public static final Event BATCHTRANSFER_EVENT = new Event("BatchTransfer",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<DynamicArray<Uint256>>() {}));
    ;

    public static final Event DEPLOY_EVENT = new Event("Deploy",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Bytes32>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event MINT_EVENT = new Event("Mint",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));


    protected InscriptionContract(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    protected InscriptionContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<BatchTransferEventResponse> getBatchTransferEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(BATCHTRANSFER_EVENT, transactionReceipt);
        ArrayList<BatchTransferEventResponse> responses = new ArrayList<BatchTransferEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            BatchTransferEventResponse typedResponse = new BatchTransferEventResponse();
            typedResponse._from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse._to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse._pids = (List<BigInteger>) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public List<DeployEventResponse> getDeployEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(DEPLOY_EVENT, transactionReceipt);
        ArrayList<DeployEventResponse> responses = new ArrayList<DeployEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            DeployEventResponse typedResponse = new DeployEventResponse();
            typedResponse._inscriptionId = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse._deployer = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._deployTime = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse._name = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse._totalSupply = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse._limitPerMint = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
            typedResponse._maxPerWallet = (BigInteger) eventValues.getNonIndexedValues().get(5).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public List<MintEventResponse> getMintEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(MINT_EVENT, transactionReceipt);
        ArrayList<MintEventResponse> responses = new ArrayList<MintEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            MintEventResponse typedResponse = new MintEventResponse();
            typedResponse._user = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse._curPid = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
            typedResponse._randDesc = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._minted = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public RemoteCall<TransactionReceipt> batchTransfer(String _to, List<BigInteger> _pids) {
        final Function function = new Function(
                FUNC_BATCHTRANSFER,
                Arrays.<Type>asList(new Address(_to),
                new DynamicArray<Uint256>(
                        Uint256.class,
                        Utils.typeMap(_pids, Uint256.class))),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<byte[]> funcSign() {
        final Function function = new Function(FUNC_FUNCSIGN,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<Tuple9<String, BigInteger, String, byte[], BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>> getInScriptionInfo() {
        final Function function = new Function(FUNC_GETINSCRIPTIONINFO,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple9<String, BigInteger, String, byte[], BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>>(
                new Callable<Tuple9<String, BigInteger, String, byte[], BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple9<String, BigInteger, String, byte[], BigInteger, BigInteger, BigInteger, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple9<String, BigInteger, String, byte[], BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>(
                                (String) results.get(0).getValue(),
                                (BigInteger) results.get(1).getValue(),
                                (String) results.get(2).getValue(),
                                (byte[]) results.get(3).getValue(),
                                (BigInteger) results.get(4).getValue(),
                                (BigInteger) results.get(5).getValue(),
                                (BigInteger) results.get(6).getValue(),
                                (BigInteger) results.get(7).getValue(),
                                (BigInteger) results.get(8).getValue());
                    }
                });
    }

    public RemoteCall<Tuple2<List<BigInteger>, List<String>>> getUserPaperInfos(String _user) {
        final Function function = new Function(FUNC_GETUSERPAPERINFOS,
                Arrays.<Type>asList(new Address(_user)),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<Utf8String>>() {}));
        return new RemoteCall<Tuple2<List<BigInteger>, List<String>>>(
                new Callable<Tuple2<List<BigInteger>, List<String>>>() {
                    @Override
                    public Tuple2<List<BigInteger>, List<String>> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<List<BigInteger>, List<String>>(
                                convertToNative((List<Uint256>) results.get(0).getValue()),
                                convertToNative((List<Utf8String>) results.get(1).getValue()));
                    }
                });
    }

    public RemoteCall<TransactionReceipt> mint(String _randNum) {
        final Function function = new Function(
                FUNC_MINT,
                Arrays.<Type>asList(new Utf8String(_randNum)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static InscriptionContract load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new InscriptionContract(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static InscriptionContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new InscriptionContract(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<InscriptionContract> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String _name, BigInteger _totalSupply, BigInteger _limitPerMint, BigInteger _maxPerWallet) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new Utf8String(_name),
                new Uint256(_totalSupply),
                new Uint256(_limitPerMint),
                new Uint256(_maxPerWallet)));
        return deployRemoteCall(InscriptionContract.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<InscriptionContract> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String _name, BigInteger _totalSupply, BigInteger _limitPerMint, BigInteger _maxPerWallet) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new Utf8String(_name),
                new Uint256(_totalSupply),
                new Uint256(_limitPerMint),
                new Uint256(_maxPerWallet)));
        return deployRemoteCall(InscriptionContract.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }


    public static class BatchTransferEventResponse {
        public String _from;

        public String _to;

        public List<BigInteger> _pids;
    }

    public static class DeployEventResponse {
        public byte[] _inscriptionId;

        public String _deployer;

        public BigInteger _deployTime;

        public String _name;

        public BigInteger _totalSupply;

        public BigInteger _limitPerMint;

        public BigInteger _maxPerWallet;
    }

    public static class MintEventResponse {
        public String _user;

        public BigInteger _curPid;

        public String _randDesc;

        public BigInteger _minted;
    }
}
