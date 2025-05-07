package com.secor.hra1500;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController
{
    @Autowired
    CredentialRepository credentialRepository;

    @GetMapping("/loginredirect")
    public String redirectToLogin()
    {
        return "redirect:/login.html"; // Redirects to the static login.html file
    }

    @PostMapping("/login")
    public String login(@ModelAttribute CredentialView credentialView)
    {
        Credential credential =  credentialRepository.findByEmail(credentialView.getEmail()).get();
        if(credential.getPassword().equals(credentialView.getPassword()))
        {
            return "redirect:/dashboard.html?userid=" + credential.getId(); // Redirect to dashboard with user ID
        }
        else
        {
            return "redirect:/login.html?error=Invalid credentials";
        }
    }
}
