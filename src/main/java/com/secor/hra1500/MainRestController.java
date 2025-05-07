package com.secor.hra1500;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class MainRestController
{

    @Autowired // Dependency Injection
    CredentialRepository credentialRepository;

    @GetMapping("greet")
    public ResponseEntity<String> greet()
    {
        return ResponseEntity.ok("Welcome to HRA1500!");
    }

    @PostMapping("signup")
    public ResponseEntity<String> signup(@ModelAttribute CredentialLoginView credentialLoginView)
    {
        // validate the credential [email and phone]
        // persist the credential in the database
            Credential credential = new Credential();
            credential.setId((long) (Math.random()*100000000)); // set id to null to let the database generate it
            credential.setEmail(credentialLoginView.getEmail());
            credential.setPhone(credentialLoginView.getPhone());
            credential.setPassword(credentialLoginView.getPassword());
            credentialRepository.save(credential);
            return ResponseEntity.ok("Credential saved successfully!");
    }

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody CredentialLoginView credentialLoginView)
    {
        if(credentialLoginView.getEmail() == "")
        {
           Credential credential = credentialRepository.findByPhone(credentialLoginView.getPhone()).stream().findFirst().get();
           if(credential.getPassword().equals(credentialLoginView.getPassword()))
           {
               return ResponseEntity.ok("Login successful!");
           }
           else
           {
               return ResponseEntity.badRequest().body("Invalid credentials!");
           }
        }
        else if (credentialLoginView.getPhone() == "")
        {
            Credential credential = credentialRepository.findByEmail(credentialLoginView.getEmail());
            if(credential.getPassword().equals(credentialLoginView.getPassword()))
            {
                return ResponseEntity.ok("Login successful!");
            }
            else
            {
                return ResponseEntity.badRequest().body("Invalid credentials!");
            }
        }
        else
        {
            return ResponseEntity.badRequest().body("Invalid credentials!");
        }
    }


}
