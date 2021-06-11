package com.nauticana.basis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.basis.model.TableViewScenarioItem;
import com.nauticana.basis.model.TableViewScenarioItemId;

public interface TableViewScenarioItemRepository extends JpaRepository<TableViewScenarioItem, TableViewScenarioItemId>{

}
