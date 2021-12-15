package com.bridgelabz.greetingapp.controller;
import com.bridgelabz.greetingapp.dto.UserDto;
import com.bridgelabz.greetingapp.model.Greeting;
import com.bridgelabz.greetingapp.model.User;
import com.bridgelabz.greetingapp.service.IGreetingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
    public class GreetingController {
        private static final String template = "Hello, %s!";
        private static final String template2 = "Hello, %s , %s!";
        private final AtomicLong counter = new AtomicLong();

        /*
         *curl localhost:8080/greeting
         @return={id =1 , content="hello world!}
         * localhost:8080/greeting?name=Srikanth
         * @return= { id=2, content="hello Srikanth!
         */
        @GetMapping(value={"/greeting","/greeting/","/greeting/home"})
        public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
            return new Greeting(counter.incrementAndGet(), String.format(template, name));
        }
        /*
        *localhost:8080/greeting/Srikanth
        @return={id =1 , content="hello Srikanth!}
         */
        @GetMapping("greeting/{name}")
        public Greeting greetings(@PathVariable String name) {
            return new Greeting(counter.incrementAndGet(), String.format(template, name));
        }
        @Autowired
        private IGreetingService greetingService;

        /*
           *localhost:8080/greeting/service
           @return={id =1 , content="hello world!}
            */
        @GetMapping("greeting/service")
        public Greeting greeting() {
            return greetingService.greetingMessage();

        }
        @PostMapping("/greeting")
        public String greetingMessage(@RequestBody UserDto userDto) {
            return greetingService.greetingMessageByName(userDto);
        }
        @GetMapping("/find")
        public User findGreetById(@RequestParam long id) {
            return greetingService.getById(id);
        }
        @GetMapping("/allgreetings")
        public List<User> findAllGreeting() {
            return greetingService.getAllGreetingMessages();
        }
        @PutMapping("/editmessage/{id}")
        public User editGreetMesage(@PathVariable long id, @RequestBody UserDto userDto) {
            return greetingService.updateGreetMessage(id, userDto);

        }
        @DeleteMapping("/deletemessage/{id}")
        public String deleteGreetMessage(@PathVariable long id) {
            return greetingService.deleteGreetMessage(id);
        }


    }

