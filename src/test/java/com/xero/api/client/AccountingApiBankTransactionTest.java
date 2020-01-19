package com.xero.api.client;

import static org.junit.Assert.assertTrue;

import org.junit.*;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Every.everyItem;

import com.xero.api.ApiClient;
import com.xero.api.client.*;
import com.xero.models.accounting.*;

import java.io.File;
import java.net.URL;

import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;

import org.threeten.bp.*;
import java.io.IOException;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

import java.util.Calendar;
import java.util.Map;
import java.util.UUID;
import java.util.List;
import java.util.ArrayList;
import java.math.BigDecimal;

public class AccountingApiBankTransactionTest {

	ApiClient defaultClient; 
	AccountingApi accountingApi; 
	String accessToken;
    String xeroTenantId; 
	
	private static boolean setUpIsDone = false;

	@Before
	public void setUp() {
		// Set Access Token and Tenant Id
        accessToken = "123";
        xeroTenantId = "xyz";
        
        // Init AccountingApi client
        // NEW Sandbox for API Mocking
		//defaultClient = new ApiClient("https://virtserver.swaggerhub.com/Xero/accounting/2.0.0",null,null,null,null);
		defaultClient = new ApiClient("https://twilight-grass-2493.getsandbox.com:443/api.xro/2.0",null,null,null,null);
        
        accountingApi = AccountingApi.getInstance(defaultClient);	
        
		// ADDED TO MANAGE RATE LIMITS while using SwaggerHub to mock APIs
		if (setUpIsDone) {
        	return;
    	}

    	try {
    		System.out.println("Sleep for 60 seconds");
	    	Thread.sleep(60);
    	} catch(InterruptedException e) {
    		System.out.println(e);
    	}
    	// do the setup
    	setUpIsDone = true;	
	}


	public void tearDown() {
		accountingApi = null;
		defaultClient = null;
	}

	@Test
	public void testGetBankTransactions() throws Exception {
		System.out.println("@Test - getBankTransactions");
		OffsetDateTime ifModifiedSince = null;
		String where = null;
        String order = null;
        Integer page = null;
        Integer unitdp = null;
		BankTransactions response = accountingApi.getBankTransactions(accessToken,xeroTenantId,ifModifiedSince, where, order, page, unitdp);
		assertThat(response.getBankTransactions().get(0).getBankTransactionID(), is(equalTo(UUID.fromString("db54aab0-ad40-4ced-bcff-0940ba20db2c"))));
		assertThat(response.getBankTransactions().get(0).getStatus(), is(equalTo(com.xero.models.accounting.BankTransaction.StatusEnum.AUTHORISED)));
		assertThat(response.getBankTransactions().get(0).getType(), is(equalTo(com.xero.models.accounting.BankTransaction.TypeEnum.RECEIVE)));
		assertThat(response.getBankTransactions().get(0).getCurrencyCode(), is(equalTo(com.xero.models.accounting.CurrencyCode.USD)));
		assertThat(response.getBankTransactions().get(0).getIsReconciled(), is(equalTo(false)));
		assertThat(response.getBankTransactions().get(0).getIsReconciled(), is(equalTo(false)));
		assertThat(response.getBankTransactions().get(0).getSubTotal(), is(equalTo(10.0)));
		assertThat(response.getBankTransactions().get(0).getTotal(), is(equalTo(10.0)));
		//System.out.println(response.getBankTransactions().get(0).toString());
	}

	@Test
	public void testGetBankTransaction() throws Exception {
		System.out.println("@Test - getBankTransaction");
		UUID bankTransactionID = UUID.fromString("297c2dc5-cc47-4afd-8ec8-74990b8761e9");
		BankTransactions response = accountingApi.getBankTransaction(accessToken,xeroTenantId,bankTransactionID);
		assertThat(response.getBankTransactions().get(0).getBankTransactionID(), is(equalTo(UUID.fromString("db54aab0-ad40-4ced-bcff-0940ba20db2c"))));
		assertThat(response.getBankTransactions().get(0).getStatus(), is(equalTo(com.xero.models.accounting.BankTransaction.StatusEnum.AUTHORISED)));
		assertThat(response.getBankTransactions().get(0).getType(), is(equalTo(com.xero.models.accounting.BankTransaction.TypeEnum.RECEIVE)));
		assertThat(response.getBankTransactions().get(0).getCurrencyCode(), is(equalTo(com.xero.models.accounting.CurrencyCode.USD)));
		assertThat(response.getBankTransactions().get(0).getIsReconciled(), is(equalTo(false)));
		assertThat(response.getBankTransactions().get(0).getSubTotal(), is(equalTo(10.0)));
		assertThat(response.getBankTransactions().get(0).getTotal(), is(equalTo(10.0)));
		//System.out.println(response.getBankTransactions().get(0).toString());
	}

	@Test
	public void testUpdateBankTransaction() throws Exception {
		System.out.println("@Test - updateBankTransaction");
		UUID bankTransactionID = UUID.fromString("297c2dc5-cc47-4afd-8ec8-74990b8761e9");
		BankTransactions newBankTransactions = new BankTransactions();
		BankTransactions response = accountingApi.updateBankTransaction(accessToken,xeroTenantId,bankTransactionID,newBankTransactions);
					
		assertThat(response.getBankTransactions().get(0).getBankTransactionID(), is(equalTo(UUID.fromString("1289c190-e46d-434b-9628-463ffdb52f00"))));		
		assertThat(response.getBankTransactions().get(0).getStatus(), is(equalTo(com.xero.models.accounting.BankTransaction.StatusEnum.AUTHORISED)));
		assertThat(response.getBankTransactions().get(0).getType(), is(equalTo(com.xero.models.accounting.BankTransaction.TypeEnum.SPEND)));
		assertThat(response.getBankTransactions().get(0).getCurrencyCode(), is(equalTo(com.xero.models.accounting.CurrencyCode.USD)));
		assertThat(response.getBankTransactions().get(0).getIsReconciled(), is(equalTo(false)));
		assertThat(response.getBankTransactions().get(0).getSubTotal(), is(equalTo(18.26)));
		assertThat(response.getBankTransactions().get(0).getTotal(), is(equalTo(20.00)));
		//System.out.println(response.getBankTransactions().get(0).toString());
	}
/*
	@Test
	public void testCreateBankTransaction() throws Exception {
		System.out.println("@Test - createBankTransaction");
		BankTransaction newBankTransaction = new BankTransaction();
		BankTransactions response = accountingApi.createBankTransaction(accessToken,xeroTenantId,newBankTransaction);
					
		assertThat(response.getBankTransactions().get(0).getBankTransactionID(), is(equalTo(UUID.fromString("1289c190-e46d-434b-9628-463ffdb52f00"))));		
		assertThat(response.getBankTransactions().get(0).getStatus(), is(equalTo(com.xero.models.accounting.BankTransaction.StatusEnum.AUTHORISED)));
		assertThat(response.getBankTransactions().get(0).getType(), is(equalTo(com.xero.models.accounting.BankTransaction.TypeEnum.SPEND)));
		assertThat(response.getBankTransactions().get(0).getCurrencyCode(), is(equalTo(com.xero.models.accounting.CurrencyCode.USD)));
		assertThat(response.getBankTransactions().get(0).getIsReconciled(), is(equalTo(false)));
		assertThat(response.getBankTransactions().get(0).getSubTotal(), is(equalTo(18.26)));
		assertThat(response.getBankTransactions().get(0).getTotal(), is(equalTo(20.00)));
		//System.out.println(response.getBankTransactions().get(0).toString());
	}
*/

    @Test
    public void createBankTransactionAttachmentByFileNameTest() throws IOException {
    	System.out.println("@Test - createBankTransactionAttachmentByFileNameTest");
        UUID bankTransactionID = UUID.fromString("297c2dc5-cc47-4afd-8ec8-74990b8761e9");
        ClassLoader classLoader = getClass().getClassLoader();
		File bytes = new File(classLoader.getResource("helo-heros.jpg").getFile());
        String fileName = "sample5.jpg";
		
        Attachments response = accountingApi.createBankTransactionAttachmentByFileName(accessToken,xeroTenantId,bankTransactionID, fileName, bytes);
		assertThat(response.getAttachments().get(0).getAttachmentID(), is(equalTo(UUID.fromString("4508a692-e52c-4ad8-a138-2f13e22bf57b"))));
		assertThat(response.getAttachments().get(0).getFileName().toString(), is(equalTo("sample5.jpg")));
		assertThat(response.getAttachments().get(0).getMimeType().toString(), is(equalTo("image/jpg")));
		assertThat(response.getAttachments().get(0).getUrl().toString(), is(equalTo("https://api.xero.com/api.xro/2.0/BankTransactions/db54aab0-ad40-4ced-bcff-0940ba20db2c/Attachments/sample5.jpg")));
		//System.out.println(response.getAttachments().get(0).toString());
    }


    @Test
    public void getBankTransactionAttachmentsTest() throws IOException {
    	System.out.println("@Test - getBankTransactionAttachmentsTest");
    	UUID bankTransactionID = UUID.fromString("297c2dc5-cc47-4afd-8ec8-74990b8761e9");
        Attachments response = accountingApi.getBankTransactionAttachments(accessToken,xeroTenantId,bankTransactionID);

       	assertThat(response.getAttachments().get(0).getAttachmentID(), is(equalTo(UUID.fromString("4508a692-e52c-4ad8-a138-2f13e22bf57b"))));
		assertThat(response.getAttachments().get(0).getFileName(), is(equalTo("sample5.jpg")));
		assertThat(response.getAttachments().get(0).getMimeType(), is(equalTo("image/jpg")));
		assertThat(response.getAttachments().get(0).getUrl(), is(equalTo("https://api.xero.com/api.xro/2.0/BankTransactions/db54aab0-ad40-4ced-bcff-0940ba20db2c/Attachments/sample5.jpg")));
		assertThat(response.getAttachments().get(0).getContentLength(), is(equalTo(new BigDecimal(2878711))));
		//System.out.println(response.getAttachments().get(0).toString());
    }
/*
    @Test
    public void updateBankTransactionAttachmentByFileNameTest() throws IOException {
    	System.out.println("@Test - updateBankTransactionAttachmentByFileNameTest");
    	UUID bankTransactionID = UUID.fromString("297c2dc5-cc47-4afd-8ec8-74990b8761e9");
        ClassLoader classLoade2 = getClass().getClassLoader();
		File bytes = new File(classLoade2.getResource("helo-heros2.jpg").getFile());
    	String fileName = "sample2.jpg";
		
        Attachments response = accountingApi.updateBankTransactionAttachmentByFileName(bankTransactionID, fileName, bytes);
		assertThat(response.getAttachments().get(0).getAttachmentID(), is(equalTo(UUID.fromString("4508a692-e52c-4ad8-a138-2f13e22bf57b"))));
		assertThat(response.getAttachments().get(0).getFileName().toString(), is(equalTo("sample5.jpg")));
		assertThat(response.getAttachments().get(0).getMimeType().toString(), is(equalTo("image/jpg")));
		assertThat(response.getAttachments().get(0).getUrl().toString(), is(equalTo("https://api.xero.com/api.xro/2.0/BankTransactions/db54aab0-ad40-4ced-bcff-0940ba20db2c/Attachments/sample5.jpg")));

    }
*/
	@Test
	public void testGetBankTransactionsHistory() throws Exception {
		System.out.println("@Test - getBankTransactionsHistory");
		UUID bankTransactionID = UUID.fromString("297c2dc5-cc47-4afd-8ec8-74990b8761e9");
		HistoryRecords hr = accountingApi.getBankTransactionsHistory(accessToken,xeroTenantId,bankTransactionID);
		assertThat(hr.getHistoryRecords().get(0).getUser(), is(equalTo("System Generated")));		
		assertThat(hr.getHistoryRecords().get(0).getChanges(), is(equalTo("Attached a file")));		
		//System.out.println(hr.getHistoryRecords().toString());		
	}

	@Test
	public void testCreateBankTransactionHistoryRecord() throws Exception {
		System.out.println("@Test - createBankTransactionHistoryRecord - not implemented");
		
		/*
		HistoryRecords newHistoryRecords = new  HistoryRecords();
		HistoryRecord newHistoryRecord = new  HistoryRecord();
		newHistoryRecord.setDetails("Hello World");
		newHistoryRecords.addHistoryRecordsItem(newHistoryRecord);
		HistoryRecords newHistory = accountingApi.createBankTransactionHistoryRecord(bankTransactionID,newHistoryRecords);
		//assertThat(newHistory.getHistoryRecords().get(0).getDetails(), is(equalTo("Hello World")));		
		System.out.println(newHistory.getHistoryRecords().toString());	
		*/	
	}
	
}
