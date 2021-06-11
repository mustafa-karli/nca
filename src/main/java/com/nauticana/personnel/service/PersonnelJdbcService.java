package com.nauticana.personnel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nauticana.finance.model.BankAccount;
import com.nauticana.personnel.model.CreditCard;
import com.nauticana.personnel.model.ViewEmployeeUser;
import com.nauticana.personnel.repository.PersonnelJdbcRepository;

@Service
public class PersonnelJdbcService {

	@Autowired
	private PersonnelJdbcRepository		r;

	@Autowired
	private CreditCardService			creditCardService;

	public int addCreditCard(String cardNumber, String cvc2, short expireYear, short expireMonth, String nameOnCard, int personId) {
		return r.addCreditCard(cardNumber, cvc2, expireYear, expireMonth, nameOnCard, personId);
	}

	public int paymentTransaction(CreditCard entity, double amount, String currency, char action, BankAccount bankAccount) {
		return 5;
	}
	
	public boolean payment(CreditCard entity, double amount, String currency, char action, BankAccount bankAccount) {
		int paymentId = paymentTransaction(entity, amount, currency, action, bankAccount);
		if (paymentId > 0) {
			CreditCard newEntity = creditCardService.findById(entity.getId());
			if (newEntity == null) {
				addCreditCard(entity.getId(), entity.getCvc2(), entity.getExpireYear(), entity.getExpireMonth(), entity.getNameOnCard(), entity.getPerson().getId());
			}
			return true;
		} else
			return false;
	}

    public List<ViewEmployeeUser> viewEmployeeUsers(int client) {
    	return r.viewEmployeeUsers(client);
    }
	
}
