package com.example.demo.Dataloader;

import com.example.demo.Models.Message;
import com.example.demo.Models.Role;
import com.example.demo.Models.User;
import com.example.demo.Repositories.MessageRepository;
import com.example.demo.Repositories.RoleRepository;
import com.example.demo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("Loading data...");

        roleRepository.save(new Role("USER"));
        roleRepository.save(new Role("ADMIN"));


        Role adminRole = roleRepository.findByRole("ADMIN");
        Role userRole = roleRepository.findByRole("USER");

        User user = new
                User("bob@bob.com", "password", "Bob", "Bobberson", true, "bob");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList(userRole));
        userRepository.save(user);


        user = new
                User("jim@jim.com", "password", "Jim", "Jimmerson", true, "jim");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList(userRole));
        userRepository.save(user);

        user = new
                User("admin@admin.com", "password", "Admin", "User", true, "admin");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList(adminRole));
        userRepository.save(user);

        user = new
                User("sam@everyman.com", "password", "Sam", "Everyman", true, "sam");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList(userRole, adminRole));
        userRepository.save(user);

      Message message  = new Message("Hey Lets grab food","2012-05-05","Jenny");
        messageRepository.save(message);


        Message message1  = new Message("Sure what time and where?","yyyy-MM-dd","Bob");
        messageRepository.save(message1);

        Message message2  = new Message("2pm the Jerk Spot","yyyy-MM-dd","Jenny");
        messageRepository.save(message2);

        Message message3  = new Message("Alright","yyyy-MM-dd","Bob");
        messageRepository.save(message3);
    }
}