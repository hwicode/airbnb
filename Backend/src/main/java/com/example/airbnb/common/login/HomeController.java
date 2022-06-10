package com.example.airbnb.common.login;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class HomeController {

    @GetMapping("/home")
    public RedirectView goHome(){
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("https://github.com/login/oauth/authorize?client_id=177df418e8432fdceb2b&redirect_uri=http://localhost:8080/api/oauth/callback");
        return redirectView;
    }
}
