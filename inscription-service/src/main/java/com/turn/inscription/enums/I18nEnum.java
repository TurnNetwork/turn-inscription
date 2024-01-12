package com.turn.inscription.enums;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * 国际化枚举
 */
public enum I18nEnum {

    // common
    SUCCESS,
    PENDING,
    FAILURE,
    UNKNOWN_TYPE,
    UNKNOWN_STATUS,
    UNKNOWN_LOCATION,
    RECORD_NOT_EXIST,

    // search
    SEARCH_KEYWORD_TOO_SHORT,
    SEARCH_KEYWORD_NO_RESULT,
    CHAIN_ID_ERROR,

    // download
    DOWNLOAD_EXCEPTION,
    DOWNLOAD_ACCOUNT_CSV_HASH,
    DOWNLOAD_ACCOUNT_CSV_TIME,
    DOWNLOAD_ACCOUNT_CSV_TYPE,
    DOWNLOAD_ACCOUNT_CSV_FEE,
    DOWNLOAD_ACCOUNT_CSV_REWARD, // reward
    // transaction
    DOWNLOAD_ACCOUNT_CSV_FROM,
    DOWNLOAD_ACCOUNT_CSV_TO,
    DOWNLOAD_ACCOUNT_CSV_VALUE,
    DOWNLOAD_ACCOUNT_CSV_STATUS,
    DOWNLOAD_ACCOUNT_CSV_VALUE_IN,
    DOWNLOAD_ACCOUNT_CSV_VALUE_OUT,
    // contract
    DOWNLOAD_CONTRACT_CSV_NAME,
    DOWNLOAD_CONTRACT_CSV_SYMBOL,
    DOWNLOAD_CONTRACT_CSV_BALANCE,
    DOWNLOAD_CONTRACT_CSV_AMOUNT,
    DOWNLOAD_CONTRACT_CSV_DECIMALS,
    DOWNLOAD_CONTRACT_CSV_TXCOUNT,
    DOWNLOAD_CONTRACT_CSV_CONTRACT,
    DOWNLOAD_CONTRACT_CSV_TOKEN_ID,
    DOWNLOAD_CONTRACT_CSV_TOKEN,
    // token id
    DOWNLOAD_TOKEN_CSV_NAME,
    DOWNLOAD_TOKEN_CSV_ADDRESS,
    DOWNLOAD_TOKEN_CSV_TOKEN,
    DOWNLOAD_TOKEN_CSV_TOKEN_ID,
    DOWNLOAD_TOKEN_CSV_TX_COUNT,

    DOWNLOAD_CONTRACT_CSV_ADDRESS,
    DOWNLOAD_CONTRACT_CSV_PERCENT,

    DOWNLOAD_CONTRACT_CSV_VALUE_IN,
    DOWNLOAD_CONTRACT_CSV_VALUE_OUT,
    // vote
    DOWNLOAD_ACCOUNT_CSV_TARGET, // Voting target
    DOWNLOAD_ACCOUNT_CSV_TICKET_COUNT, // Number of valid votes/votes
    DOWNLOAD_ACCOUNT_CSV_TICKET_PRICE, // Ticket price
    DOWNLOAD_ACCOUNT_CSV_VOTE_VALUE, // Vote pledge

    // statement
    DOWNLOAD_ACCOUNT_CSV_NODE_NAME, // node name
    DOWNLOAD_ACCOUNT_CSV_DEPOSIT_VALUE, // Deposit

    DOWNLOAD_BLOCK_CSV_NUMBER,
    DOWNLOAD_BLOCK_CSV_TIMESTAMP,
    DOWNLOAD_BLOCK_CSV_TRANSACTION_COUNT,
    DOWNLOAD_BLOCK_CSV_REWARD,
    DOWNLOAD_BLOCK_CSV_TXN_FEE,

    // format
    FORMAT_DATE_ERROR,

    // request
    REQUEST_INVALID_PARAM,

    // system
    SYSTEM_EXCEPTION,

    // block
    BLOCK_ERROR_DUPLICATE,
    BLOCK_ERROR_NOT_EXIST,

    // node
    NODE_ERROR_DUPLICATE,
    NODE_ERROR_NOT_EXIST,
    NODE_ERROR_NEED_ID_OR_NODE_ID,

    // transaction
    TRANSACTION_TRANSFER,
    TRANSACTION_TRANSFER_SEND,
    TRANSACTION_TRANSFER_RECEIVE,
    TRANSACTION_MPC_TRANSACTION,
    TRANSACTION_CONTRACT_CREATE,
    TRANSACTION_VOTE,
    TRANSACTION_TRANSACTION_EXECUTE,
    TRANSACTION_AUTHORIZATION,

    TRANSACTION_CANDIDATE_DEPOSIT,
    TRANSACTION_CANDIDATE_APPLY_WITHDRAW,
    TRANSACTION_CANDIDATE_WITHDRAW,
    TRANSACTION_VOTE_TICKET,

    TRANSACTION_ERROR_DUPLICATE,
    TRANSACTION_ERROR_NOT_EXIST,
    CONTRACT_EXEC_DESTROY,

    PENDING_ERROR_DUPLICATE,
    PENDING_ERROR_NOT_EXIST,

    //proposal error
    PROPOSAL_PARAM_ERROR,

    TRANSFER,
    CONTRACT_CREATE,
    CONTRACT_EXEC,
    OTHERS,
    MPC,
    STAKE_CREATE,
    STAKE_MODIFY,
    STAKE_INCREASE,
    STAKE_EXIT,
    DELEGATE_CREATE,
    DELEGATE_EXIT,
    PROPOSAL_TEXT,
    PROPOSAL_UPGRADE,
    PROPOSAL_PARAMETER,
    PROPOSAL_CANCEL,
    PROPOSAL_VOTE,
    VERSION_DECLARE,
    REPORT,
    RESTRICTING_CREATE,
    MULTI_SIGN,
    CLAIM_REWARDS,
    EVM_CONTRACT_CREATE,
    WASM_CONTRACT_CREATE,
    ERC20_CONTRACT_CREATE,
    ERC20_CONTRACT_EXEC,
    ERC721_CONTRACT_CREATE,
    ERC721_CONTRACT_EXEC,
    ERC1155_CONTRACT_CREATE,
    ERC1155_CONTRACT_EXEC,
    CODE0,
    CODE1,
    CODE2,
    CODE3,
    CODE301000,
    CODE301001,
    CODE301002,
    CODE301003,
    CODE301004,
    CODE301005,
    CODE301006,
    CODE301007,
    CODE301008,
    CODE301009,
    CODE301100,
    CODE301101,
    CODE301102,
    CODE301103,
    CODE301104,
    CODE301105,
    CODE301106,
    CODE301107,
    CODE301108,
    CODE301109,
    CODE301110,
    CODE301111,
    CODE301112,
    CODE301113,
    CODE301114,
    CODE301115,
    CODE301116,
    CODE301117,
    CODE301118,
    CODE301119,
    CODE301200,
    CODE301201,
    CODE301202,
    CODE301203,
    CODE301204,
    CODE301205,
    CODE301206,
    CODE301207,
    CODE301208,
    CODE302001,
    CODE302002,
    CODE302003,
    CODE302004,
    CODE302005,
    CODE302006,
    CODE302007,
    CODE302008,
    CODE302009,
    CODE302010,
    CODE302011,
    CODE302012,
    CODE302013,
    CODE302014,
    CODE302015,
    CODE302016,
    CODE302017,
    CODE302018,
    CODE302019,
    CODE302020,
    CODE302021,
    CODE302022,
    CODE302023,
    CODE302024,
    CODE302025,
    CODE302026,
    CODE302027,
    CODE302028,
    CODE302029,
    CODE302030,
    CODE302031,
    CODE302032,
    CODE302033,
    CODE302034,

    CODE303000,
    CODE303001,
    CODE303002,
    CODE303003,
    CODE303004,
    CODE303005,
    CODE303006,
    CODE303007,
    CODE303008,
    CODE303009,
    CODE303010,
    CODE304001,
    CODE304002,
    CODE304003,
    CODE304004,
    CODE304005,
    CODE304006,
    CODE304007,
    CODE304008,
    CODE304009,
    CODE304010,
    CODE304011,
    CODE304012,
    CODE304013,
    CODE304014,
    CODE304015,
    CODE305001,
    DELETEGATE_CODE304013;

    private static final Map<String, I18nEnum> ENUMS = new HashMap<>();

    static {
        Arrays.asList(I18nEnum.values()).forEach(en -> ENUMS.put(en.name(), en));
    }

    public static I18nEnum getEnum(String code) {
        return ENUMS.get(code);
    }
}
