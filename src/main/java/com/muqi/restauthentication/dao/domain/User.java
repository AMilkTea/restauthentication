package com.muqi.restauthentication.dao.domain;

import lombok.Data;

/**
 * Created by lyh on 17-4-2.
 */
@Data
public class User {
    public long id;

    public String name;

    public String password;

    public String email;

    public User(long id, String name, String password, String email) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
    }
}
