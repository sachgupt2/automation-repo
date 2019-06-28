package com.cucumber.api.stepdefs;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;
import com.cucumber.api.utils.Utility;
import cucumber.api.DataTable;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class StubRunnerStepDef
{

   private Response            response;
   private final static String input_customer_code_uri    = "/input-customer-code";
   private final static String transaction_uri            = "/transaction";
   private final static String input_bar_code_uri         = "/input-barcode";
   private final static String transfer_transaction_uri   = "/transfer-transaction";
   private final static String message_uri                = "message";
   private final static String response_FakeTranId_header = "FakeTranId";
   private final static String api_input_path             = "/api-input/json/";

   @When( "^a user retrieves transaction details by terminalId \"([^\"]*)\" and clientId \"([^\"]*)\"$" )
   public void a_user_retrieves_transaction_details_by_terminalId_and_clientId( String terminalId, String clientId ) throws Throwable
   {
      Header termlId = new Header( "terminalId", terminalId );

      // Read base_url property from file
      String baseURI = Utility.getPropertyValue( "base_url" );

      // Set base url for API.
      RestAssured.baseURI = baseURI;

      // Set Basic authentication for API
      PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
      authScheme.setUserName( Utility.getPropertyValue( "user_name" ) );
      authScheme.setPassword( Utility.getPropertyValue( "password" ) );
      RestAssured.authentication = authScheme;

      // Prepare the API request on above given parameters.
      RequestSpecification httpRequest = RestAssured.given().queryParam( "clientId", clientId );

      // Set content type for request.
      httpRequest.contentType( "application/json" );
      httpRequest.accept( "application/json" );
      httpRequest.header( termlId );

      // Call API service.
      response = httpRequest.get( transaction_uri );
   }

   @When( "^getTransaction API response includes the following$" )
   public void gettransaction_API_response_includes_the_following( DataTable dt ) throws Throwable
   {
      List<Map<String, String>> list = dt.asMaps( String.class, String.class );

      assertEquals( "Response Code Not Matched : ", list.get( 0 ).get( "Value" ), String.valueOf( response.getStatusCode() ) );
      assertEquals( "Content Type did not matched : ", list.get( 1 ).get( "Value" ), response.header( "Content-Type" ) );
      assertEquals( "Status Line did not matched : ", list.get( 2 ).get( "Value" ), response.getStatusLine().trim() );
      assertEquals( "result did not matched : ", list.get( 3 ).get( "Value" ), response.jsonPath().get( "result" ).toString() );
      assertEquals( "orderId did not matched : ", list.get( 4 ).get( "Value" ), response.jsonPath().get( "success.totalInfo.orderId" ).toString() );
      assertEquals( "dealBarcode did not matched : ", list.get( 5 ).get( "Value" ), response.jsonPath().get( "success.totalInfo.dealBarcode" ).toString() );
      assertEquals( "memberLimitFlag did not matched : ", list.get( 6 ).get( "Value" ), response.jsonPath().get( "success.totalInfo.memberLimitFlag" ).toString() );
      assertEquals( "discountAmount did not matched : ", list.get( 7 ).get( "Value" ), response.jsonPath().get( "success.totalInfo.discountAmount" ).toString() );
      assertEquals( "totalCount did not matched : ", list.get( 8 ).get( "Value" ), response.jsonPath().get( "success.totalInfo.totalCount" ).toString() );
      assertEquals( "regiBagFlg did not matched : ", list.get( 9 ).get( "Value" ), response.jsonPath().get( "success.totalInfo.regiBagFlg" ).toString() );
      assertEquals( "totalAmount did not matched : ", list.get( 10 ).get( "Value" ), response.jsonPath().get( "success.totalInfo.totalAmount" ).toString() );
      assertEquals( "itemErrFlg did not matched : ", list.get( 11 ).get( "Value" ), response.jsonPath().get( "success.totalInfo.itemErrFlg" ).toString() );
      assertEquals( "terminalSts did not matched : ", list.get( 12 ).get( "Value" ), response.jsonPath().get( "success.totalInfo.terminalSts" ).toString() );
      assertEquals( "accountNo did not matched : ", list.get( 13 ).get( "Value" ), response.jsonPath().get( "success.totalInfo.accountNo" ).toString() );
      assertEquals( "dealBarcodeUrl did not matched : ", list.get( 14 ).get( "Value" ), response.jsonPath().get( "success.totalInfo.dealBarcodeUrl" ) );
      assertEquals( "maxSeqNo did not matched : ", list.get( 15 ).get( "Value" ), response.jsonPath().get( "success.totalInfo.maxSeqNo" ).toString() );
      assertEquals( "errorCode did not matched : ", list.get( 16 ).get( "Value" ), response.jsonPath().get( "failure.errorCode" ).toString() );
      assertEquals( "message did not matched : ", list.get( 17 ).get( "Value" ), response.jsonPath().get( "failure.message" ) );
      assertEquals( "Response Header FakeTranId value did not matched : ", list.get( 18 ).get( "Value" ), response.getHeader( response_FakeTranId_header ) );

      RestAssured.reset();
   }

   @When( "^a user creates customer by calling inputCustomerCode API with input file \"([^\"]*)\"$" )
   public void a_user_creates_customer_by_calling_inputCustomerCode_API_with_input_file( String fileName ) throws Throwable
   {
      Header termlId = new Header( "terminalId", Utility.getPropertyValue( "terminal_id" ) );
      String respath = api_input_path + fileName;

      String jsonValue = Utility.generateStringFromResource( respath );

      // Read base_url property from file
      String baseURI = Utility.getPropertyValue( "base_url" );

      // Set base url for API.
      RestAssured.baseURI = baseURI;

      // Set Basic authentication for API
      PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
      authScheme.setUserName( "admin" );
      authScheme.setPassword( "admin" );
      RestAssured.authentication = authScheme;

      // Prepare the API request on above given parameters.
      RequestSpecification httpRequest = RestAssured.given();

      // Set content type for request.
      httpRequest.contentType( "application/json" );
      httpRequest.accept( "application/json" );
      httpRequest.header( termlId );
      httpRequest.body( jsonValue );

      // Call API service.
      response = httpRequest.post( input_customer_code_uri );

   }

   @When( "^inputCustomerCode API response includes the following$" )
   public void inputcustomercode_API_response_includes_the_following( DataTable dt ) throws Throwable
   {
      List<Map<String, String>> list = dt.asMaps( String.class, String.class );

      assertEquals( "Response Code Not Matched : ", list.get( 0 ).get( "Value" ), String.valueOf( response.getStatusCode() ) );
      assertEquals( "Content Type did not matched : ", list.get( 1 ).get( "Value" ), response.header( "Content-Type" ) );
      assertEquals( "Status Line did not matched : ", list.get( 2 ).get( "Value" ), response.getStatusLine().trim() );
      assertEquals( "result did not matched : ", list.get( 3 ).get( "Value" ), response.jsonPath().get( "result" ).toString() );
      assertEquals( "orderId did not matched : ", list.get( 4 ).get( "Value" ), response.jsonPath().get( "success.totalInfo.orderId" ).toString() );
      assertEquals( "dealBarcode did not matched : ", list.get( 5 ).get( "Value" ), response.jsonPath().get( "success.totalInfo.dealBarcode" ).toString() );
      assertEquals( "memberLimitFlag did not matched : ", list.get( 6 ).get( "Value" ), response.jsonPath().get( "success.totalInfo.memberLimitFlag" ).toString() );
      assertEquals( "discountAmount did not matched : ", list.get( 7 ).get( "Value" ), response.jsonPath().get( "success.totalInfo.discountAmount" ).toString() );
      assertEquals( "totalCount did not matched : ", list.get( 8 ).get( "Value" ), response.jsonPath().get( "success.totalInfo.totalCount" ).toString() );
      assertEquals( "regiBagFlg did not matched : ", list.get( 9 ).get( "Value" ), response.jsonPath().get( "success.totalInfo.regiBagFlg" ).toString() );
      assertEquals( "totalAmount did not matched : ", list.get( 10 ).get( "Value" ), response.jsonPath().get( "success.totalInfo.totalAmount" ).toString() );
      assertEquals( "itemErrFlg did not matched : ", list.get( 11 ).get( "Value" ), response.jsonPath().get( "success.totalInfo.itemErrFlg" ).toString() );
      assertEquals( "terminalSts did not matched : ", list.get( 12 ).get( "Value" ), response.jsonPath().get( "success.totalInfo.terminalSts" ).toString() );
      assertEquals( "accountNo did not matched : ", list.get( 13 ).get( "Value" ), response.jsonPath().get( "success.totalInfo.accountNo" ).toString() );
      assertEquals( "dealBarcodeUrl did not matched : ", list.get( 14 ).get( "Value" ), response.jsonPath().get( "success.totalInfo.dealBarcodeUrl" ) );
      assertEquals( "maxSeqNo did not matched : ", list.get( 15 ).get( "Value" ), response.jsonPath().get( "success.totalInfo.maxSeqNo" ).toString() );
      assertEquals( "customerID did not matched : ", list.get( 16 ).get( "Value" ), response.jsonPath().get( "success.customerInfo.customerID" ) );
      assertEquals( "trainingMode did not matched : ", list.get( 17 ).get( "Value" ), response.jsonPath().get( "success.customerInfo.trainingMode" ).toString() );
      assertEquals( "point did not matched : ", list.get( 18 ).get( "Value" ), response.jsonPath().get( "success.customerInfo.point" ).toString() );
      assertEquals( "errorCode did not matched : ", list.get( 19 ).get( "Value" ), response.jsonPath().get( "failure.errorCode" ).toString() );
      assertEquals( "message did not matched : ", list.get( 20 ).get( "Value" ), response.jsonPath().get( "failure.message" ) );
      assertEquals( "Response Header FakeTranId value did not matched : ", list.get( 21 ).get( "Value" ), response.getHeader( response_FakeTranId_header ) );

      RestAssured.reset();

   }

   @When( "^a user input barcode by calling inputBarCode API with input file \"([^\"]*)\"$" )
   public void a_user_input_barcode_by_calling_inputBarCode_API_with_input_file( String fileName ) throws Throwable
   {
      Header termlId = new Header( "terminalId", Utility.getPropertyValue( "terminal_id" ) );
      String respath = api_input_path + fileName;

      String jsonValue = Utility.generateStringFromResource( respath );

      // Read base_url property from file
      String baseURI = Utility.getPropertyValue( "base_url" );

      // Set base url for API.
      RestAssured.baseURI = baseURI;

      // Set Basic authentication for API
      PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
      authScheme.setUserName( "admin" );
      authScheme.setPassword( "admin" );
      RestAssured.authentication = authScheme;

      // Prepare the API request on above given parameters.
      RequestSpecification httpRequest = RestAssured.given();

      // Set content type for request.
      httpRequest.contentType( "application/json" );
      httpRequest.accept( "application/json" );
      httpRequest.header( termlId );
      httpRequest.body( jsonValue );

      // Call API service.
      response = httpRequest.post( input_bar_code_uri );

   }

   @When( "^inputBarCode API response includes the following$" )
   public void inputbarcode_API_response_includes_the_following( DataTable dt ) throws Throwable
   {
      List<Map<String, String>> list = dt.asMaps( String.class, String.class );

      assertEquals( "code did not matched : ", list.get( 16 ).get( "Value" ), response.jsonPath().get( "success.itemInfoList.code" ).toString().replaceAll( "\\[", "" ).replaceAll( "\\]", "" ) );

      assertEquals( "Response Code Not Matched : ", list.get( 0 ).get( "Value" ), String.valueOf( response.getStatusCode() ) );
      assertEquals( "Content Type did not matched : ", list.get( 1 ).get( "Value" ), response.header( "Content-Type" ) );
      assertEquals( "Status Line did not matched : ", list.get( 2 ).get( "Value" ), response.getStatusLine().trim() );
      assertEquals( "result did not matched : ", list.get( 3 ).get( "Value" ), response.jsonPath().get( "result" ).toString() );
      assertEquals( "totalAmount did not matched : ", list.get( 4 ).get( "Value" ), response.jsonPath().get( "success.totalInfo.totalAmount" ).toString() );
      assertEquals( "dealBarcode did not matched : ", list.get( 5 ).get( "Value" ), response.jsonPath().get( "success.totalInfo.dealBarcode" ).toString() );
      assertEquals( "itemErrFlg did not matched : ", list.get( 6 ).get( "Value" ), response.jsonPath().get( "success.totalInfo.itemErrFlg" ).toString() );
      assertEquals( "terminalSts did not matched : ", list.get( 7 ).get( "Value" ), response.jsonPath().get( "success.totalInfo.terminalSts" ).toString() );
      assertEquals( "memberLimitFlag did not matched : ", list.get( 8 ).get( "Value" ), response.jsonPath().get( "success.totalInfo.memberLimitFlag" ).toString() );
      assertEquals( "accountNo did not matched : ", list.get( 9 ).get( "Value" ), response.jsonPath().get( "success.totalInfo.accountNo" ).toString() );
      assertEquals( "discountAmount did not matched : ", list.get( 10 ).get( "Value" ), response.jsonPath().get( "success.totalInfo.discountAmount" ).toString() );
      assertEquals( "totalCount did not matched : ", list.get( 11 ).get( "Value" ), response.jsonPath().get( "success.totalInfo.totalCount" ).toString() );
      assertEquals( "regiBagFlg did not matched : ", list.get( 12 ).get( "Value" ), response.jsonPath().get( "success.totalInfo.regiBagFlg" ).toString() );
      assertEquals( "adultPopupFlg did not matched : ", list.get( 13 ).get( "Value" ), response.jsonPath().get( "success.totalInfo.adultPopupFlg" ).toString() );
      assertEquals( "dealBarcodeUrl did not matched : ", list.get( 14 ).get( "Value" ), response.jsonPath().get( "success.totalInfo.dealBarcodeUrl" ) );
      assertEquals( "maxSeqNo did not matched : ", list.get( 15 ).get( "Value" ), response.jsonPath().get( "success.totalInfo.maxSeqNo" ).toString() );
      assertEquals( "code did not matched : ", list.get( 16 ).get( "Value" ), response.jsonPath().get( "success.itemInfoList.code" ).toString().replaceAll( "\\[", "" ).replaceAll( "\\]", "" ) );
      assertEquals( "seqNo did not matched : ", list.get( 17 ).get( "Value" ), response.jsonPath().get( "success.itemInfoList.seqNo" ).toString().replaceAll( "\\[", "" ).replaceAll( "\\]", "" ) );
      assertEquals( "itemDivide did not matched : ", list.get( 18 ).get( "Value" ), response.jsonPath().get( "success.itemInfoList.itemDivide" ).toString().replaceAll( "\\[", "" ).replaceAll( "\\]", "" ) );
      assertEquals( "warningCode did not matched : ", list.get( 19 ).get( "Value" ), response.jsonPath().get( "success.itemInfoList.warningCode" ).toString().replaceAll( "\\[", "" ).replaceAll( "\\]", "" ) );
      assertEquals( "goodsAttribute did not matched : ", list.get( 20 ).get( "Value" ), response.jsonPath().get( "success.itemInfoList.goodsAttribute" ).toString().replaceAll( "\\[", "" ).replaceAll( "\\]", "" ) );
      assertEquals( "currentUnitPrice did not matched : ", list.get( 21 ).get( "Value" ), response.jsonPath().get( "success.itemInfoList.currentUnitPrice" ).toString().replaceAll( "\\[", "" ).replaceAll( "\\]", "" ) );
      assertEquals( "itemStackPointer did not matched : ", list.get( 22 ).get( "Value" ), response.jsonPath().get( "success.itemInfoList.itemStackPointer" ).toString().replaceAll( "\\[", "" ).replaceAll( "\\]", "" ) );
      assertEquals( "itemCount did not matched : ", list.get( 23 ).get( "Value" ), response.jsonPath().get( "success.itemInfoList.itemCount" ).toString().replaceAll( "\\[", "" ).replaceAll( "\\]", "" ) );
      assertEquals( "delFlg did not matched : ", list.get( 24 ).get( "Value" ), response.jsonPath().get( "success.itemInfoList.delFlg" ).toString().replaceAll( "\\[", "" ).replaceAll( "\\]", "" ) );
      assertEquals( "itemAmount did not matched : ", list.get( 25 ).get( "Value" ), response.jsonPath().get( "success.itemInfoList.itemAmount" ).toString().replaceAll( "\\[", "" ).replaceAll( "\\]", "" ) );
      assertEquals( "itemErrFlg did not matched : ", list.get( 26 ).get( "Value" ), response.jsonPath().get( "success.itemInfoList.itemErrFlg" ).toString().replaceAll( "\\[", "" ).replaceAll( "\\]", "" ) );
      assertEquals( "name did not matched : ", list.get( 27 ).get( "Value" ), response.jsonPath().get( "success.itemInfoList.name" ).toString().replaceAll( "\\[", "" ).replaceAll( "\\]", "" ) );
      assertEquals( "iconCode did not matched : ", list.get( 28 ).get( "Value" ), response.jsonPath().get( "success.itemInfoList.iconCode" ).toString().replaceAll( "\\[", "" ).replaceAll( "\\]", "" ) );
      assertEquals( "discountValueDivide did not matched : ", list.get( 29 ).get( "Value" ), response.jsonPath().get( "success.itemInfoList.discountValueDivide" ).toString().replaceAll( "\\[", "" ).replaceAll( "\\]", "" ) );
      assertEquals( "discountValue did not matched : ", list.get( 30 ).get( "Value" ), response.jsonPath().get( "success.itemInfoList.discountValue" ).toString().replaceAll( "\\[", "" ).replaceAll( "\\]", "" ) );
      assertEquals( "goodName did not matched : ", list.get( 31 ).get( "Value" ), response.jsonPath().get( "success.currentItemInfo.goodName" ) );
      assertEquals( "specialBarCdFlg did not matched : ", list.get( 32 ).get( "Value" ), response.jsonPath().get( "success.specialBarCdFlg" ).toString() );
      assertEquals( "entryPiece did not matched : ", list.get( 33 ).get( "Value" ), response.jsonPath().get( "success.entryPiece" ).toString() );
      assertEquals( "errorCode did not matched : ", list.get( 34 ).get( "Value" ), response.jsonPath().get( "failure.errorCode" ).toString() );
      assertEquals( "message did not matched : ", list.get( 35 ).get( "Value" ), response.jsonPath().get( "failure.message" ) );

      assertEquals( "Response Header FakeTranId value did not matched : ", list.get( 36 ).get( "Value" ), response.getHeader( response_FakeTranId_header ) );

      RestAssured.reset();

   }

   @When( "^a user call transferTransaction API$" )
   public void a_user_call_transferTransaction_API() throws Throwable
   {
      Header termlId = new Header( "terminalId", Utility.getPropertyValue( "terminal_id" ) );

      // Read base_url property from file
      String baseURI = Utility.getPropertyValue( "base_url" );

      // Set base url for API.
      RestAssured.baseURI = baseURI;

      // Set Basic authentication for API
      PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
      authScheme.setUserName( "admin" );
      authScheme.setPassword( "admin" );
      RestAssured.authentication = authScheme;

      // Prepare the API request on above given parameters.
      RequestSpecification httpRequest = RestAssured.given();

      // Set content type for request.
      httpRequest.contentType( "application/json" );
      httpRequest.accept( "application/json" );
      httpRequest.header( termlId );

      // Call API service.
      response = httpRequest.post( transfer_transaction_uri );

   }

   @When( "^transferTransaction API response includes the following$" )
   public void transfertransaction_API_response_includes_the_following( DataTable dt ) throws Throwable
   {
      List<Map<String, String>> list = dt.asMaps( String.class, String.class );

      assertEquals( "Response Code Not Matched : ", list.get( 0 ).get( "Value" ), String.valueOf( response.getStatusCode() ) );
      assertEquals( "Content Type did not matched : ", list.get( 1 ).get( "Value" ), response.header( "Content-Type" ) );
      assertEquals( "Status Line did not matched : ", list.get( 2 ).get( "Value" ), response.getStatusLine().trim() );
      assertEquals( "result did not matched : ", list.get( 3 ).get( "Value" ), response.jsonPath().get( "result" ).toString() );
      assertEquals( "totalAmount did not matched : ", list.get( 4 ).get( "Value" ), response.jsonPath().get( "success.totalInfo.totalAmount" ).toString() );
      assertEquals( "dealBarcode did not matched : ", list.get( 5 ).get( "Value" ), response.jsonPath().get( "success.totalInfo.dealBarcode" ).toString() );
      assertEquals( "itemErrFlg did not matched : ", list.get( 6 ).get( "Value" ), response.jsonPath().get( "success.totalInfo.itemErrFlg" ).toString() );
      assertEquals( "terminalSts did not matched : ", list.get( 7 ).get( "Value" ), response.jsonPath().get( "success.totalInfo.terminalSts" ).toString() );
      assertEquals( "memberLimitFlag did not matched : ", list.get( 8 ).get( "Value" ), response.jsonPath().get( "success.totalInfo.memberLimitFlag" ).toString() );
      assertEquals( "accountNo did not matched : ", list.get( 9 ).get( "Value" ), response.jsonPath().get( "success.totalInfo.accountNo" ).toString() );
      assertEquals( "discountAmount did not matched : ", list.get( 10 ).get( "Value" ), response.jsonPath().get( "success.totalInfo.discountAmount" ).toString() );
      assertEquals( "totalCount did not matched : ", list.get( 11 ).get( "Value" ), response.jsonPath().get( "success.totalInfo.totalCount" ).toString() );
      assertEquals( "regiBagFlg did not matched : ", list.get( 12 ).get( "Value" ), response.jsonPath().get( "success.totalInfo.regiBagFlg" ).toString() );
      assertEquals( "adultPopupFlg did not matched : ", list.get( 13 ).get( "Value" ), response.jsonPath().get( "success.totalInfo.adultPopupFlg" ).toString() );
      assertEquals( "dealBarcodeUrl did not matched : ", list.get( 14 ).get( "Value" ), response.jsonPath().get( "success.totalInfo.dealBarcodeUrl" ) );
      assertEquals( "maxSeqNo did not matched : ", list.get( 15 ).get( "Value" ), response.jsonPath().get( "success.totalInfo.maxSeqNo" ).toString() );
      assertEquals( "code did not matched : ", list.get( 16 ).get( "Value" ), response.jsonPath().get( "success.itemInfoList.code" ).toString().replaceAll( "\\[", "" ).replaceAll( "\\]", "" ) );
      assertEquals( "seqNo did not matched : ", list.get( 17 ).get( "Value" ), response.jsonPath().get( "success.itemInfoList.seqNo" ).toString().replaceAll( "\\[", "" ).replaceAll( "\\]", "" ) );
      assertEquals( "itemDivide did not matched : ", list.get( 18 ).get( "Value" ), response.jsonPath().get( "success.itemInfoList.itemDivide" ).toString().replaceAll( "\\[", "" ).replaceAll( "\\]", "" ) );
      assertEquals( "warningCode did not matched : ", list.get( 19 ).get( "Value" ), response.jsonPath().get( "success.itemInfoList.warningCode" ).toString().replaceAll( "\\[", "" ).replaceAll( "\\]", "" ) );
      assertEquals( "goodsAttribute did not matched : ", list.get( 20 ).get( "Value" ), response.jsonPath().get( "success.itemInfoList.goodsAttribute" ).toString().replaceAll( "\\[", "" ).replaceAll( "\\]", "" ) );
      assertEquals( "currentUnitPrice did not matched : ", list.get( 21 ).get( "Value" ), response.jsonPath().get( "success.itemInfoList.currentUnitPrice" ).toString().replaceAll( "\\[", "" ).replaceAll( "\\]", "" ) );
      assertEquals( "itemStackPointer did not matched : ", list.get( 22 ).get( "Value" ), response.jsonPath().get( "success.itemInfoList.itemStackPointer" ).toString().replaceAll( "\\[", "" ).replaceAll( "\\]", "" ) );
      assertEquals( "itemCount did not matched : ", list.get( 23 ).get( "Value" ), response.jsonPath().get( "success.itemInfoList.itemCount" ).toString().replaceAll( "\\[", "" ).replaceAll( "\\]", "" ) );
      assertEquals( "delFlg did not matched : ", list.get( 24 ).get( "Value" ), response.jsonPath().get( "success.itemInfoList.delFlg" ).toString().replaceAll( "\\[", "" ).replaceAll( "\\]", "" ) );
      assertEquals( "itemAmount did not matched : ", list.get( 25 ).get( "Value" ), response.jsonPath().get( "success.itemInfoList.itemAmount" ).toString().replaceAll( "\\[", "" ).replaceAll( "\\]", "" ) );
      assertEquals( "itemErrFlg did not matched : ", list.get( 26 ).get( "Value" ), response.jsonPath().get( "success.itemInfoList.itemErrFlg" ).toString().replaceAll( "\\[", "" ).replaceAll( "\\]", "" ) );
      assertEquals( "name did not matched : ", list.get( 27 ).get( "Value" ), response.jsonPath().get( "success.itemInfoList.name" ).toString().replaceAll( "\\[", "" ).replaceAll( "\\]", "" ) );
      assertEquals( "iconCode did not matched : ", list.get( 28 ).get( "Value" ), response.jsonPath().get( "success.itemInfoList.iconCode" ).toString().replaceAll( "\\[", "" ).replaceAll( "\\]", "" ) );
      assertEquals( "discountValueDivide did not matched : ", list.get( 29 ).get( "Value" ), response.jsonPath().get( "success.itemInfoList.discountValueDivide" ).toString().replaceAll( "\\[", "" ).replaceAll( "\\]", "" ) );
      assertEquals( "discountValue did not matched : ", list.get( 30 ).get( "Value" ), response.jsonPath().get( "success.itemInfoList.discountValue" ).toString().replaceAll( "\\[", "" ).replaceAll( "\\]", "" ) );
      assertEquals( "goodName did not matched : ", list.get( 31 ).get( "Value" ), response.jsonPath().get( "success.currentItemInfo.goodName" ) );
      assertEquals( "specialBarCdFlg did not matched : ", list.get( 32 ).get( "Value" ), response.jsonPath().get( "success.specialBarCdFlg" ).toString() );
      assertEquals( "entryPiece did not matched : ", list.get( 33 ).get( "Value" ), response.jsonPath().get( "success.entryPiece" ).toString() );
      assertEquals( "errorCode did not matched : ", list.get( 34 ).get( "Value" ), response.jsonPath().get( "failure.errorCode" ).toString() );
      assertEquals( "message did not matched : ", list.get( 35 ).get( "Value" ), response.jsonPath().get( "failure.message" ) );

      assertEquals( "Response Header FakeTranId value did not matched : ", list.get( 36 ).get( "Value" ), response.getHeader( response_FakeTranId_header ) );

      RestAssured.reset();
   }

   @When( "^a user retrieves message details by id \"([^\"]*)\"$" )
   public void a_user_retrieves_message_details_by_id( String id ) throws Throwable
   {
      // Read base_url property from file
      String baseURI = Utility.getPropertyValue( "base_url" );

      // Set base url for API.
      RestAssured.baseURI = baseURI;

      // Set Basic authentication for API
      PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
      authScheme.setUserName( Utility.getPropertyValue( "user_name" ) );
      authScheme.setPassword( Utility.getPropertyValue( "password" ) );
      RestAssured.authentication = authScheme;

      // Prepare the API request on above given parameters.
      RequestSpecification httpRequest = RestAssured.given().queryParam( "id", id );

      // Set content type for request.
      httpRequest.contentType( "application/json" );
      httpRequest.accept( "application/json" );

      // Call API service.
      response = httpRequest.get( message_uri );

   }

   @When( "^getMessage API response includes the following$" )
   public void getmessage_API_response_includes_the_following( DataTable dt ) throws Throwable
   {
      List<Map<String, String>> list = dt.asMaps( String.class, String.class );

      assertEquals( "Response Code Not Matched : ", list.get( 0 ).get( "Value" ), String.valueOf( response.getStatusCode() ) );
      assertEquals( "Content Type did not matched : ", list.get( 1 ).get( "Value" ), response.header( "Content-Type" ) );
      assertEquals( "Status Line did not matched : ", list.get( 2 ).get( "Value" ), response.getStatusLine().trim() );

      assertEquals( "ID did not matched : ", list.get( 3 ).get( "Value" ), response.jsonPath().get( "id" ).toString() );
      assertEquals( "totalAmount did not matched : ", list.get( 4 ).get( "Value" ), response.jsonPath().get( "message" ).toString() );

      RestAssured.reset();
   }

}