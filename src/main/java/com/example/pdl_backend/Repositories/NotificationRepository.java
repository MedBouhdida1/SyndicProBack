package com.example.pdl_backend.Repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pdl_backend.Models.Notification;
import com.example.pdl_backend.Models.Syndic;

public interface NotificationRepository extends JpaRepository<Notification, Long>{
    List<Notification> findBySyndic(Syndic syndic);
}
