package com.mycompany.app.users;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UsersListTest {
    User userA = new User(1, "User A", 0);
    User userB = new User(2, "User B", 10);
    User userC = new User(3, "User C", 5);

    UsersList usersList;

    @BeforeEach
    private void setUsersList() {
        usersList = new UsersList(List.of(userA, userB, userC));
    }

    @Test
    public void testUsersListConstructor() {
        // given
        Map<Integer, List<User>> usersMap = Map.of(1, List.of(userA), 2, List.of(userB), 3, List.of(userC));

        assertEquals(usersMap, usersList.getUsersMap());
    }
}
