package com.nauticana.basis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.basis.model.NotificationType;

public interface NotificationTypeRepository extends JpaRepository<NotificationType, Integer> {}