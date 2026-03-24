package com.example.chatservice.repository;

import com.example.chatservice.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {

    Optional<Chat> findByUserPhoneAndContactPhone(String userPhone, String contactPhone);

    List<Chat> findByUserPhone(String userPhone);

}