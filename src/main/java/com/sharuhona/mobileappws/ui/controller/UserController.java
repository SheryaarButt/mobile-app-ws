package com.sharuhona.mobileappws.ui.controller;

import com.sharuhona.mobileappws.ui.model.request.UserDetailsRequestModel;
import com.sharuhona.mobileappws.ui.model.response.UserRest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping(path = "/{userId}",
                produces = {
                            MediaType.APPLICATION_XML_VALUE,
                            MediaType.APPLICATION_JSON_VALUE
                            })
    public ResponseEntity<UserRest> getUser(@PathVariable String userId) {
        UserRest user = new UserRest();
        user.setUserId(userId);
        user.setEmail("email@email.com");
        user.setFirstName("Freddy");
        user.setLastName("Mercury");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping
    public String getUsers(@RequestParam(value="page", defaultValue = "50") int page,
                           @RequestParam(value="limit", defaultValue = "1") int limit,
                           @RequestParam(value="sort", defaultValue="desc") String sort ) {
        return "Get users was called page: " + page + " Limit: " + limit + "Sort: " + sort;
    }

    @PostMapping(consumes = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
    }, produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
    })
    public ResponseEntity<UserRest> addUser(@Valid @RequestBody UserDetailsRequestModel userDetails, HttpServletRequest request) {
        request.getHeaderNames().asIterator().forEachRemaining(headerName -> System.out.println(request.getHeader(headerName)));
        UserRest user = new UserRest();
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setEmail(userDetails.getEmail());

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
