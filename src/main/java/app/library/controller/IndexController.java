package app.library.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class IndexController {

    private final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @GetMapping("/index")
    protected String getIndexPage(HttpServletRequest request) {
        logger.info("Redirecting to index successful");
        request.setAttribute("viewName", "index");
        return "index";
    }
}
