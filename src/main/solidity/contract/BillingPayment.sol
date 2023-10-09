// SPDX-License-Identifier: UNLICENSED
pragma solidity ^0.8.9;

import "@openzeppelin/contracts/token/ERC1155/ERC1155.sol";
import "@openzeppelin/contracts/access/Ownable.sol";
import "@openzeppelin/contracts/utils/Context.sol";

    struct Payment {
        string uniquePaymentId;
        uint256 amount;
        uint256 splitNumber;
        string splitType;
        string billUniqueId;
        address clientAddress;
    }

    struct BillModel {
        string billUniqueId;
        uint256 billAmount;
        uint256 txAmount;
        uint256 tipAmount;
        uint256 revenueAmount;
        uint256 payedAmount;
        bool processed;
        string processDate;
        string[] payments;
        string billTimeStamp;
        address merchantAddress;
        string metaData;
    }

    struct ReceiptModel {
        string uniqueReceiptId;
        string billUniqueId;
        uint256 amount;
    }

/**
Bill interface and structure
*/
interface IBillingPayment {
    /**
    Events
    */
    /**
    * Emit a success or error when generating a new bill
    */
    event BillGenerated(string _billUniqueId, BillModel _bill);
    event BillProcessed(string _billUniqueId);
    event PaymentReceived(string _paymentUniqueId);
    event ReceiptGenerated(string _receiptUniqueId);

    /**
    *Contract functions
    */
    //Payment processor creates bill for client
    //Can create bill enriched with process payment taxes
    function createBill(
        string memory _billUniqueId,
        uint256 _billAmount,
        uint256 _txAmount,
        uint256 _tipAmount,
        uint256 _revenueAmount,
        string memory _processDate,
        string memory _billTimeStamp,
        address _merchantAddress,
        string memory _metaData) external;

    //All addresses can pay if the bill is non processed or exists than execute payment otherwise revert
    function pay(
        string memory _billUniqueId) external payable;

    //Get all receipts for specific address
    function getReceipts(address _address) external view returns (ReceiptModel[] memory);

    function getBillsForMerchant(address _merchantAddress) external view returns (BillModel[] memory);
}

/**
Main contract functionalities such as pay, createBill, checkBills, checkReceipts
*/
contract BillingPayment is IBillingPayment, Ownable {
    mapping(string => Payment) private paymentsStorage;
    mapping(string => BillModel) private billsStorage;
    mapping(string => ReceiptModel) private receiptsStorage;

    mapping(address => string[]) private merchantBillsStorage;

    address private _owner;

    constructor() {
        _owner = msg.sender;
    }


    function createBill(
        string memory _billUniqueId,
        uint256 _billAmount,
        uint256 _txAmount,
        uint256 _tipAmount,
        uint256 _revenueAmount,
        string memory _processDate,
        string memory _billTimeStamp,
        address _merchantAddress,
        string memory _metaData) external override onlyOwner {

        BillModel memory newBill = BillModel({
            billUniqueId: _billUniqueId,
            billAmount: _billAmount,
            txAmount: _txAmount,
            tipAmount: _tipAmount,
            revenueAmount: _revenueAmount,
            payedAmount: 0,
            processed: false,
            processDate: _processDate,
            payments: new string[](0),
            billTimeStamp: _billTimeStamp,
            merchantAddress: _merchantAddress,
            metaData: _metaData
        });

        //Add the bill to the storage
        billsStorage[_billUniqueId] = newBill;

        //Add bill unique id to merchant address
        merchantBillsStorage[_merchantAddress].push(_billUniqueId);

        //Broadcast bill
        emit BillGenerated(_billUniqueId, newBill);
    }

    function pay(
        string memory _billUniqueId) external payable {}

    function getReceipts(address _address) external view returns (ReceiptModel[] memory) {}

    /**
    *Return the all bills for a specific merchant address
    */
    function getBillsForMerchant(address _merchantAddress) external view returns (BillModel[] memory) {
        string[] memory billsIds = merchantBillsStorage[_merchantAddress];
        BillModel[] memory result = new BillModel[](billsIds.length);

        for(uint256 i = 0; i < billsIds.length; i++) {
            result[i] = billsStorage[billsIds[i]];
        }

        return result;
    }
}


/**
Receipt token
*/
//contract Receipt is IBillingPayment, Ownable, Context {}

