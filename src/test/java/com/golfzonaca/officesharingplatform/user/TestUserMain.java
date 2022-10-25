package com.golfzonaca.officesharingplatform.user;

import com.golfzonaca.officesharingplatform.domain.User;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicLong;

public class TestUserMain {
    public static void main(String[] args) {
        User user = new User(new AtomicLong(10), "12@hanmail.net", "1234", new AtomicLong(10), "010-1234-5678");

    }
}
