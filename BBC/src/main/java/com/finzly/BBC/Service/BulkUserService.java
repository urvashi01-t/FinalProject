package com.finzly.BBC.Service;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.finzly.BBC.Dao.BulkUserRepository;
import com.finzly.BBC.Entity.Customer;
import com.finzly.BBC.Entity.CustomerBill;
import com.finzly.BBC.Entity.PaymentModes;
import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

@Service
public class BulkUserService {
	@Autowired
	BulkUserRepository bulkUser;

	@Autowired
	SessionFactory factory;

	public List<Customer> UploadData(List<CustomerBill> customerBillList) {
		return bulkUser.UploadData(customerBillList);

	}

	public List<CustomerBill> uploadCustomerData(@RequestParam("file") MultipartFile file) throws Exception {
		Session session = factory.openSession();
		org.hibernate.Transaction outerTransaction = null;
		List<CustomerBill> customerBillDatas = new ArrayList<>();

		try (InputStream inputStream = file.getInputStream()) {
			CsvParserSettings settings = new CsvParserSettings();
			settings.setHeaderExtractionEnabled(true);
			CsvParser parser = new CsvParser(settings);

			List<Record> parseAllRecords = parser.parseAllRecords(inputStream);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-DD HH:mm");

			outerTransaction = session.beginTransaction(); // Start the outer transaction

			for (Record record : parseAllRecords) {
				CustomerBill customerBillData = new CustomerBill();
				customerBillData.setBillId(Integer.parseInt(record.getString("billId")));
				customerBillData.setConsumedUnit(Integer.parseInt(record.getString("consumedUnit")));
				customerBillData.setAmount(Double.parseDouble(record.getString("amount")));
				customerBillData.setStatus(record.getString("status"));
				customerBillData.setTotalamount(Double.parseDouble(record.getString("totalamount")));
				customerBillData.setMonth(record.getString("month"));
				

				int customerId = Integer.parseInt(record.getString("customerId"));
				Customer transactionEntity = session.get(Customer.class, customerId);

				int paymentModeId = Integer.parseInt(record.getString("paymentModeId"));
				PaymentModes paymentEntity = session.get(PaymentModes.class, paymentModeId);

				if (transactionEntity == null) {
					// If the Transaction does not exist, create a new one
					transactionEntity = new Customer();
					transactionEntity.setCustomerId(customerId);
				}
				if (paymentEntity == null) {
					// If the Transaction does not exist, create a new one
					paymentEntity = new PaymentModes();
					paymentEntity.setPaymentModeId(paymentModeId);
				}

				try {
					// Set the transaction for customerBillData (due to cascading, this will
					// save/update the associated transaction)
					customerBillData.setCustomerId(transactionEntity);

					// Set other properties of transactionEntity
//	    	                transactionEntity.setTransactionMethod(record.getString("transaction_method"));
					transactionEntity.setName(record.getString("name"));
					transactionEntity.setLastName(record.getString("lastName"));
					transactionEntity.setAddress(record.getString("address"));
					transactionEntity.setEmail(record.getString("email"));
					transactionEntity.setPhoneNumber(Integer.parseInt(record.getString("phoneNumber")));

					// Parse and set dates
					String billStartDate = record.getString("start_date");
					java.util.Date utilDate = dateFormat.parse(billStartDate);
					java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
					customerBillData.setStartDate(sqlDate);

					String billDueDate = record.getString("end_date");
					java.util.Date utilDate1 = dateFormat.parse(billDueDate);
					java.sql.Date sqlDate1 = new java.sql.Date(utilDate1.getTime());
					customerBillData.setEndDate(sqlDate1);

					String DueDate = record.getString("due_date");
					java.util.Date utilDate2 = dateFormat.parse(DueDate);
					java.sql.Date sqlDate2 = new java.sql.Date(utilDate2.getTime());
					customerBillData.setDueDate(sqlDate2);

					paymentEntity.setPaymentModeName(record.getString("paymentModeName"));
					paymentEntity.setDiscountPercentage(Double.parseDouble(record.getString("discountPercentage")));

					// Save or update the CustomerData entity (due to cascading, this will also
					// save/update the associated transaction)
					session.saveOrUpdate(customerBillData);

					customerBillDatas.add(customerBillData);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			outerTransaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (outerTransaction != null) {
				outerTransaction.rollback();
			}
		} finally {
			session.close();
		}

		return customerBillDatas;
	}
}
