package client.second.dto;

import client.second.data.TestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserDtoTest {

    @Test
    void createNewUserDtoObject() {

        UserDto result = UserDto
                .builder()
                .username(TestData.username)
                .role(TestData.role)
                .createdAt(TestData.createdAt)
                .updatedAt(TestData.updatedAt)
                .build();
        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getUsername());
        Assertions.assertNotNull(result.getRole());
        Assertions.assertSame(result.getCreatedAt(),TestData.createdAt);
        Assertions.assertSame(result.getUpdatedAt(),TestData.updatedAt);

        Assertions.assertEquals(result.getUsername(),TestData.username);
        Assertions.assertTrue(result.getRole().equals(TestData.role));

    }
}
