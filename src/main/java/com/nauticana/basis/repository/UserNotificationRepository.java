package com.nauticana.basis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.basis.model.UserNotification;

public interface UserNotificationRepository extends JpaRepository<UserNotification, Long> {}