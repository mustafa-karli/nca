package com.nauticana.finance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.finance.model.AccountTxTemplateItem;
import com.nauticana.finance.model.AccountTxTemplateItemId;

public interface AccountTxTemplateItemRepository extends JpaRepository<AccountTxTemplateItem, AccountTxTemplateItemId> {}