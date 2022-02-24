package com.epam.task.framework.api;

public class Endpoints {

  private Endpoints() {
  }

  public static final String TRELLO = "https://api.trello.com";
  public static final String BOARDS = TRELLO + "/1/boards/";
  public static final String BOARDS_NAME = TRELLO + "/1/boards?name=";
  public static final String NAME = "/?name=";
}
