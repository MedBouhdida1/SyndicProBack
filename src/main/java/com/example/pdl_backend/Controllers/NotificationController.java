package com.example.pdl_backend.Controllers;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.pdl_backend.Models.Notification;
import com.example.pdl_backend.Models.Syndic;
import com.example.pdl_backend.Repositories.NotificationRepository;
import com.example.pdl_backend.Repositories.SyndicRepository;

@RestController
@RequestMapping(value = "/notification")
@CrossOrigin("*")
public class NotificationController {
    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private SyndicRepository syndicRepository;

    @GetMapping
    private List<Notification> ListAG(){
        return notificationRepository.findAll();
    }

    @PostMapping(value = "{id}")
    private Notification addNotification(@RequestBody Notification notification, @PathVariable Long id){
        Syndic syndic=syndicRepository.findById(id).orElse(null);
        notification.setSyndic(syndic); 
        notification.setCreatedAt(LocalDateTime.now());
        notificationRepository.save(notification);
        return notification;
    }

  

    @DeleteMapping(value = "{id}")
    private void deleteNotification(@PathVariable Long id){
        notificationRepository.deleteById(id);
    }

     @GetMapping(value = "{id}")
    private List<Notification> getNotificationBySyndic(@PathVariable Long id){
        Optional<Syndic> synd = syndicRepository.findById(id);
        return notificationRepository.findBySyndic(synd.get()) ;
    }
}
