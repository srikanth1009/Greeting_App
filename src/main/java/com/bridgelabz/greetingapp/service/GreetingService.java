package com.bridgelabz.greetingapp.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.greetingapp.dto.UserDto;
import com.bridgelabz.greetingapp.model.Greeting;
import com.bridgelabz.greetingapp.model.User;
import com.bridgelabz.greetingapp.repository.IGreetingRepository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class GreetingService implements IGreetingService {
    private static final String template = "Hello world";
    private final AtomicLong counter = new AtomicLong();
    @Autowired
    IGreetingRepository iGreetingRepository;



    @Override
    public Greeting greetingMessage() {
        return new Greeting(counter.incrementAndGet(), String.format(template));
    }
    @Override
    public String greetingMessageByName(UserDto userDto) {
        User user = new User();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(userDto, user);
        return ("Hello " + user.getFirstName() + " " + user.getLastName());
    }
    @Override
    public User getById(long id) {
        Optional<User> greetById = iGreetingRepository.findById(id);
        return greetById.orElse(null);
    }
    @Override
    public List<User> getAllGreetingMessages() {
        return iGreetingRepository.findAll();
    }
    @Override
    public User updateGreetMessage(long id, UserDto userDto) {
        Optional<User> update = iGreetingRepository.findById(id);
        if (update.isPresent()) {
            update.get().setFirstName(userDto.getFirstName());
            update.get().setLastName(userDto.getLastName());
            iGreetingRepository.save(update.get());
        }
        return update.get();
    }
    @Override
    public String deleteGreetMessage(long id) {
        Optional<User> greetingMessage = iGreetingRepository.findById(id);
        if (greetingMessage.isPresent()) {
            iGreetingRepository.delete(greetingMessage.get());
            return "Record Deleted";
        }
        return "Record not available";
    }

}