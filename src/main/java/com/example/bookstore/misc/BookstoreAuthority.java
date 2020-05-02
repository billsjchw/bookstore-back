package com.example.bookstore.misc;

import org.springframework.security.core.GrantedAuthority;

public class BookstoreAuthority implements GrantedAuthority {
    private final String authority;

    public BookstoreAuthority(boolean admin) {
        this.authority = admin ? "ADMIN" : "USER";
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BookstoreAuthority))
            return false;
        BookstoreAuthority auth = (BookstoreAuthority) obj;
        return authority.equals(auth.authority);
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
