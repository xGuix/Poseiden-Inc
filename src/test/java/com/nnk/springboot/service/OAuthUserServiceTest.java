package com.nnk.springboot.service;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class OAuthUserServiceTest
{
    private OAuth2UserService userService = new OAuth2UserService();

    @Test
    public void loadUserWhenUserRequestIsNullThenThrowIllegalArgumentException()
    {
        assertThatIllegalArgumentException().isThrownBy(() -> this.userService.loadUser(null));
    }
}