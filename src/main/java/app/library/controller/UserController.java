package app.library.controller;

import app.library.model.UserTable;
import app.library.security.UserTableDetailsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    UserTableDetailsService userService;

    public UserController(UserTableDetailsService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ModelAndView getUsers(@RequestParam(required = false) String username, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("allUsers");
        List<UserTable> users = userService.getAllUsers(username);
        request.setAttribute("viewName", "allUsers");
        mav.addObject("users", users);
        return mav;
    }

    @GetMapping("/creation")
    protected ModelAndView getUserCreationForm(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("addUser");
        request.setAttribute("viewName", "addUser");
        mav.addObject("user", new UserTable());
        return mav;
    }

    @PostMapping
    protected ModelAndView createUser(@ModelAttribute("user") @Valid UserTable user, BindingResult result, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("addUser");
        request.setAttribute("viewName", "addUser");
        if (result.hasErrors()) {
            return mav;
        }

        logger.info("Request with parameters name: {}, lastName: {}, username {}", user.getFirstName(), user.getLastName(), user.getUserName());
        userService.addUser(user);
        mav.addObject("success", "User Was successfully Added!!! You can add another one.");
        logger.info("Redirecting");
        return mav;
    }

    @GetMapping("/search")
    protected ModelAndView getFindAUserForm(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("findUser");
        request.setAttribute("viewName", "findUser");
        mav.addObject("user", new UserTable());
        return mav;
    }

    @GetMapping("/{id}")
    public ModelAndView viewUser(@PathVariable Long id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("displayUser");
        request.setAttribute("viewName", "displayUser");
        UserTable user = userService.getById(id);
        mav.addObject("user", user);
        return mav;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView editUserForm(@PathVariable Long id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("editUser");
        request.setAttribute("viewName", "editUser");
        UserTable user = userService.getById(id);
        mav.addObject("user", user);
        return mav;
    }

    @PostMapping("/{id}/edit")
    public ModelAndView editUser(@ModelAttribute("user") UserTable user, BindingResult result, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("editUser");
        request.setAttribute("viewName", "editUser");
        mav.addObject("user", user);
        if (result.hasErrors()) {
            return mav;
        }
        logger.info("Edit with parameters name: {}, lastName: {}, username {}", user.getFirstName(), user.getLastName(), user.getUserName());
        userService.edit(user);
        mav.addObject("success", "User Was successfully Edited!!!");
        return mav;
    }
}
