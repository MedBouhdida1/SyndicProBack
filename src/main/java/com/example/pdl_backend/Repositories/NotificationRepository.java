package com.example.pdl_backend.Repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pdl_backend.Models.Notification;
public interface NotificationRepository extends JpaRepository<Notification, Long>{
    
}
