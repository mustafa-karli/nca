package com.nauticana.finance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.finance.model.AccountTransactionItem;
import com.nauticana.finance.model.AccountTransactionItemId;

public interface AccountTransactionItemRepository extends JpaRepository<AccountTransactionItem, AccountTransactionItemId> {}