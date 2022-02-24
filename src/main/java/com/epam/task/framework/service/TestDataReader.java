package com.epam.task.framework.service;

import java.util.ResourceBundle;

public class TestDataReader {

  private static ResourceBundle resourceBundle = ResourceBundle.getBundle("user");

  public static String getTestData(String key) {
    return resourceBundle.getString(key);
  }
}
