package com.epam.task.framework.service;

import java.util.HashMap;
import java.util.Map;

public class UserCreator {

  public static final String USER_NAME = "user";
  public static final String USER_VALUE = "login_value";
  public static final String PASSWORD = "password";
  public static final String PASSWORD_VALUE = "password_value";
  public static final String QUERY_PARAMETER_KEY = "query_parameter_key";
  public static final String QUERY_PARAMETER_VALUE = "query_parameter_key_value";
  public static final String QUERY_PARAMETER_TOKEN = "query_parameter_token";
  public static final String QUERY_PARAMETER_TOKEN_VALUE = "query_parameter_token_value";

  public static Map<String, String> withCredentialsFromProperty() {
    Map<String, String> data = new HashMap<>();
    data.put(TestDataReader.getTestData(USER_NAME), TestDataReader.getTestData(USER_VALUE));
    data.put(TestDataReader.getTestData(PASSWORD), TestDataReader.getTestData(PASSWORD_VALUE));
    return data;
  }


}