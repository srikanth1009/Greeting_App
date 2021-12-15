package com.bridgelabz.greetingapp.repository;
import com.bridgelabz.greetingapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGreetingRepository extends JpaRepository<User,Long> {
}