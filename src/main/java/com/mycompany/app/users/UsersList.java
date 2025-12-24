package com.mycompany.app.users;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UsersList {
    private Map<Integer, List<User>> usersMap;

    public UsersList(List<User> usersList) {
        this.usersMap = usersList.stream().collect(Collectors.groupingBy(User::getUserId));
    }

    // change to User not List of Users
    public Map<Integer, List<User>> getUsersMap() {
        return this.usersMap;
    }
}
