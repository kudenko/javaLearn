package app.library.controller.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {

    @RequestMapping("/forbidden")
    public String getAccessDeniedError() {
        return "forbidden";
    }

}
