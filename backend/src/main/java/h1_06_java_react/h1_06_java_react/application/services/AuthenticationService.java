package h1_06_java_react.h1_06_java_react.application.services;

import h1_06_java_react.h1_06_java_react.adapters.dtos.login.RequestLogin;
import h1_06_java_react.h1_06_java_react.adapters.dtos.login.ResponseLogin;

public interface AuthenticationService {
    ResponseLogin login(RequestLogin data);
}
