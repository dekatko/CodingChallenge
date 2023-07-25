package de.tarent.challenge.store.model;

import java.util.Objects;

public class UserDTO {
    private String username;

    public UserDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return username.equals(userDTO.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
