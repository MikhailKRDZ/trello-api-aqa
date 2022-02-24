package com.epam.task;


import static com.epam.task.framework.api.Endpoints.BOARDS;
import static com.epam.task.framework.api.Endpoints.BOARDS_NAME;
import static com.epam.task.framework.api.Endpoints.NAME;
import static com.epam.task.framework.service.UserCreator.withCredentialsFromProperty;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.util.Map;
import com.epam.task.framework.model.Board;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TrelloBoardCreatedTest {

  private final String key = "key";
  private final String queryParameterKey = "6ba72479ab2fc4cd11e1743548851efe";
  private final String token = "token";
  private final String queryParameterToken = "23c6a2ffe0a31e76ce03d1ae6256189cbf8675879cb40b2ed99b9b247d9f419f";
  private final String expectedBoardsCreatedName = "Boards with api and intellij";
  private final String expectedBoardsChangesName = "Boards changes with api and intellij";
  private final String defaultListFalse = "&defaultList=false";
  private final Map<String, String> userData = withCredentialsFromProperty();
  private Board actualBoard;
  private String boardId;

  @Test(priority = 1)
  public void testBoardCreated() {
    actualBoard = given()
        .log()
        .method()
        .queryParam(key, queryParameterKey)
        .queryParam(token, queryParameterToken)
        .body(userData)
        .post(BOARDS_NAME + expectedBoardsCreatedName + defaultListFalse)
        .then()
        .statusCode(200)
        .extract()
        .as(Board.class);
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(actualBoard.getName(), expectedBoardsCreatedName,
        "New Boards name isn't equal expected!");
    softAssert.assertNotEquals(actualBoard.getId(), null,
        "New Boards id isn't created!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void testBoardChangesName() {
    boardId = actualBoard.getShortUrl().split("/")[4];
    actualBoard = given()
        .log()
        .method()
        .queryParam(key, queryParameterKey)
        .queryParam(token, queryParameterToken)
        .body(userData)
        .put(BOARDS + boardId + NAME + expectedBoardsChangesName)
        .then()
        .statusCode(200)
        .extract()
        .as(Board.class);
    assertEquals(actualBoard.getName(), expectedBoardsChangesName,
        "Changes Boards name isn't equal expected!");
  }

  @AfterTest
  public void deleteBoard() {
    given()
        .log()
        .method()
        .queryParam(key, queryParameterKey)
        .queryParam(token, queryParameterToken)
        .body(userData)
        .delete(BOARDS + boardId)
        .then()
        .statusCode(200);
  }
}
