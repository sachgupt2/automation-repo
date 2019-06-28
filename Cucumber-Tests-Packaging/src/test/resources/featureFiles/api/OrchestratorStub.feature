# Author: sachin.gupta@toshiba-tsip.com
Feature: Transaction System API Automation.

  @BVT
  Scenario: User calls getTransaction API service to get transaction details using terminalId and verify response
    When a user retrieves transaction details by terminalId "001" and clientId "A_001"
    And getTransaction API response includes the following
      | Field           | Value                          |
      | Status Code     |                            200 |
      | Content-Type    | application/json;charset=UTF-8 |
      | Status line     | HTTP/1.1 200 OK                |
      | result          | true                           |
      | orderId         |                              0 |
      | dealBarcode     |                                |
      | memberLimitFlag |                              0 |
      | discountAmount  |                              0 |
      | totalCount      |                              0 |
      | regiBagFlg      |                              0 |
      | totalAmount     |                              0 |
      | itemErrFlg      |                              0 |
      | terminalSts     |                              2 |
      | accountNo       |                              0 |
      | dealBarcodeUrl  |                                |
      | maxSeqNo        |                              0 |
      | errorCode       |                              0 |
      | message         |                                |
      | FakeTranId      |                             79 |

  @BVT
  Scenario: User calls inputCustomerCode API service to post customer details and verify response
    When a user creates customer by calling inputCustomerCode API with input file "CreateCustomer.json"
    And inputCustomerCode API response includes the following
      | Field           | Value                          |
      | Status Code     |                            200 |
      | Content-Type    | application/json;charset=UTF-8 |
      | Status line     | HTTP/1.1 200 OK                |
      | result          | true                           |
      | orderId         |                              0 |
      | dealBarcode     |                                |
      | memberLimitFlag |                              0 |
      | discountAmount  |                              0 |
      | totalCount      |                              0 |
      | regiBagFlg      |                              0 |
      | totalAmount     |                              0 |
      | itemErrFlg      |                              0 |
      | terminalSts     |                              2 |
      | accountNo       |                              0 |
      | dealBarcodeUrl  |                                |
      | maxSeqNo        |                              0 |
      | customerID      |                 00000000000001 |
      | trainingMode    |                              0 |
      | point           |                              0 |
      | errorCode       |                              0 |
      | message         |                                |
      | FakeTranId      |                             24 |

  @BVT
  Scenario: User calls inputBarCode API service to post barcode details and verify response
    When a user input barcode by calling inputBarCode API with input file "InputBarCode.json"
    And inputBarCode API response includes the following
      | Field               | Value                          |
      | Status Code         |                            200 |
      | Content-Type        | application/json;charset=UTF-8 |
      | Status line         | HTTP/1.1 200 OK                |
      | result              | true                           |
      | totalAmount         |                            147 |
      | dealBarcode         |                                |
      | itemErrFlg          |                              0 |
      | terminalSts         |                              2 |
      | memberLimitFlag     |                              0 |
      | accountNo           |                              0 |
      | discountAmount      |                             -1 |
      | totalCount          |                              1 |
      | regiBagFlg          |                              0 |
      | adultPopupFlg       |                              0 |
      | dealBarcodeUrl      |                                |
      | maxSeqNo            |                              1 |
      | code                |                  4901990077002 |
      | seqNo               |                              1 |
      | itemDivide          |                              0 |
      | warningCode         |                              5 |
      | goodsAttribute      |                            602 |
      | currentUnitPrice    |                              0 |
      | itemStackPointer    |                              1 |
      | itemCount           |                              1 |
      | delFlg              |                              0 |
      | itemAmount          |                            138 |
      | itemErrFlg          |                              0 |
      | name                | フィッシュハンバーグ                     |
      | iconCode            |                             -1 |
      | discountValueDivide |                              0 |
      | discountValue       |                              0 |
      | goodName            |                                |
      | specialBarCdFlg     |                              0 |
      | entryPiece          |                              0 |
      | errorCode           |                              0 |
      | message             |                                |
      | FakeTranId          |                              0 |

  @BVT
  Scenario: User calls transferTransaction API service and verify response
    When a user call transferTransaction API
    And transferTransaction API response includes the following
      | Field               | Value                          |
      | Status Code         |                            200 |
      | Content-Type        | application/json;charset=UTF-8 |
      | Status line         | HTTP/1.1 200 OK                |
      | result              | true                           |
      | totalAmount         |                            147 |
      | dealBarcode         |               0132848385803166 |
      | itemErrFlg          |                              0 |
      | terminalSts         |                              3 |
      | memberLimitFlag     |                              0 |
      | accountNo           |                              0 |
      | discountAmount      |                             -1 |
      | totalCount          |                              1 |
      | regiBagFlg          |                              0 |
      | adultPopupFlg       |                              0 |
      | dealBarcodeUrl      |                                |
      | maxSeqNo            |                              1 |
      | code                |                  4901990077002 |
      | seqNo               |                              1 |
      | itemDivide          |                              0 |
      | warningCode         |                              5 |
      | goodsAttribute      |                            602 |
      | currentUnitPrice    |                              0 |
      | itemStackPointer    |                              1 |
      | itemCount           |                              1 |
      | delFlg              |                              0 |
      | itemAmount          |                            138 |
      | itemErrFlg          |                              0 |
      | name                | フィッシュハンバーグ                     |
      | iconCode            |                             -1 |
      | discountValueDivide |                              0 |
      | discountValue       |                              0 |
      | goodName            |                                |
      | specialBarCdFlg     |                              0 |
      | entryPiece          |                              0 |
      | errorCode           |                              0 |
      | message             |                                |
      | FakeTranId          |                              6 |

  @BVT
  Scenario: User calls getMessage API service and verify response
    When a user retrieves message details by id "1"
    And getMessage API response includes the following
      | Field        | Value                          |
      | Status Code  |                            200 |
      | Content-Type | application/json;charset=UTF-8 |
      | Status line  | HTTP/1.1 200 OK                |
      | id           |                              1 |
      | message      | Hello World!!!                 |
