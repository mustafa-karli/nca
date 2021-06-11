package com.nauticana.personnel.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.personnel.model.UserAccountOwner;
import com.nauticana.personnel.model.UserAccountOwnerId;

public interface UserAccountOwnerRepository extends JpaRepository<UserAccountOwner, UserAccountOwnerId>{}
