package com.lacedorium.library.domain.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class User {
    private static final String ADMIN_GROUP = "admin";

    private String name;
    private String displayName;
    private List<String> groups;

    public User(String name, String displayName, List<String> groups) {
        this.name = name;
        this.displayName = displayName;
        this.groups = groups;
    }

    public Boolean isAdmin() {
        return groups.contains(ADMIN_GROUP);
    }
}
