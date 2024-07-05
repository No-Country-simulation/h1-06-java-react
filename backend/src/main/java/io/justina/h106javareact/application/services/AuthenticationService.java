package io.justina.h106javareact.application.services;

import io.justina.h106javareact.adapters.dtos.login.RequestLogin;
import io.justina.h106javareact.adapters.dtos.login.ResponseLogin;

public interface AuthenticationService {
    ResponseLogin login(RequestLogin data);
}
