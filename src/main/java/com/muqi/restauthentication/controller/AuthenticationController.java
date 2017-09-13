package com.muqi.restauthentication.controller;

import com.muqi.restauthentication.dao.AuthRepository;
import com.muqi.restauthentication.dao.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yonghuilv on 17-9-10.
 */
@RestController
public class AuthenticationController {
    final static Logger LOG = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private AuthRepository authRepository;

    @RequestMapping(value = {"/", "/auth"}, method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> jdbcAuth(@RequestHeader("Authorization") String auth){
        LOG.info("jdbcAuth is invoked, The auth is " + auth);
        String info = auth.substring(6);
        try{
            String s = new String(Base64.getDecoder().decode(info), "US-ASCII");

            String[] array = s.split(":");
            String name = array[0];
            String password = array[1];

            User user = authRepository.queryUserCount(name, password);
            Map<String, Object> model = new HashMap<>();
            Map<String, Object> attributes = new HashMap<>();
            if(user != null){
                System.out.println("name:" + name);
                System.out.println("password:" + password);
                model.put("@class", "org.apereo.cas.authentication.principal.SimplePrincipal");
                model.put("id", name);

                attributes.put("password", password);

                model.put("attributes", attributes);

                System.out.println(model);
                return new ResponseEntity<Map<String, Object>>(model, HttpStatus.OK);
            }

            return new ResponseEntity<Map<String, Object>>(model, HttpStatus.NOT_FOUND);

        }catch (Exception e){
            System.out.print(e.getMessage());
            return null;
        }
    }
}
