package com.secor.hra1500;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> signup(@ModelAttribute CredentialView credentialView)
    {
        // validate the credential [email and phone]
        // persist the credential in the database
            Credential credential = new Credential();
            credential.setId((long) (Math.random()*100000000)); // set id to null to let the database generate it
            credential.setEmail(credentialView.getEmail());
            credential.setPhone(credentialView.getPhone());
            credential.setPassword(credentialView.getPassword());
            credentialRepository.save(credential);
            return ResponseEntity.ok("Credential saved successfully!");
    }

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody CredentialView credentialView)
    {
        if(credentialView.getEmail() == "")
        {
           Credential credential = credentialRepository.findByPhone(credentialView.getPhone()).stream().findFirst().get();
           if(credential.getPassword().equals(credentialView.getPassword()))
           {
               return ResponseEntity.ok("Login successful!");
           }
           else
           {
               return ResponseEntity.badRequest().body("Invalid credentials!");
           }
        }
        else if (credentialView.getPhone() == "")
        {
            Credential credential = credentialRepository.findByEmail(credentialView.getEmail()).get();
            if(credential.getPassword().equals(credentialView.getPassword()))
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

    @GetMapping("getfullname/{userid}")
    public ResponseEntity<String> getFullname(@PathVariable("userid") Long id)
    {
        Credential credential = credentialRepository.findById(id).get();
        return ResponseEntity.ok(credential.getEmail());
    }


}
