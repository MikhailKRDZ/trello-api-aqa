package com.epam.task;


import static com.epam.task.framework.api.Endpoints.BOARDS;
import static com.epam.task.framework.api.Endpoints.BOARDS_NAME;
import static com.epam.task.framework.api.Endpoints.NAME;
import static com.epam.task.framework.service.UserCreator.QUERY_PARAMETER_KEY;
import static com.epam.task.framework.service.UserCreator.QUERY_PARAMETER_TOKEN;
import static com.epam.task.framework.service.UserCreator.QUERY_PARAMETER_TOKEN_VALUE;
import static com.epam.task.framework.service.UserCreator.QUERY_PARAMETER_VALUE;
import static com.epam.task.framework.service.UserCreator.withCredentialsFromProperty;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import com.epam.task.framework.service.TestDataReader;
import java.util.Map;
import com.epam.task.framework.model.Board;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TrelloBoardCreatedTest {

  private final String expectedBoardsCreatedName = "Boards with api and intellij";
  private final String expectedBoardsChangesName = "Boards changes with api and intellij";
  private final String defaultListFalse = "&defaultList=false";
  private final Map<String, String> userData = withCredentialsFromProperty();
  private Board actualNewBoard;
  private Board actualChangesBoard;
  private String boardsId;

  @Test(priority = 1)
  public void testBoardCreated() {
    actualNewBoard = given()
        .log()
        .method()
        .queryParam(TestDataReader.getTestData(QUERY_PARAMETER_KEY), TestDataReader.getTestData(QUERY_PARAMETER_VALUE))
        .queryParam(TestDataReader.getTestData(QUERY_PARAMETER_TOKEN), TestDataReader.getTestData(QUERY_PARAMETER_TOKEN_VALUE))
        .body(userData)
        .post(BOARDS_NAME + expectedBoardsCreatedName + defaultListFalse)
        .then()
        .statusCode(200)
        .extract()
        .as(Board.class);
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(actualNewBoard.getName(), expectedBoardsCreatedName,
        "New boards name isn't equal expected!");
    softAssert.assertNotEquals(actualNewBoard.getId(), null,
        "New boards id isn't created!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void testBoardChangesName() {
    boardsId = actualNewBoard.getShortUrl().split("/")[4];
    actualChangesBoard = given()
        .log()
        .method()
        .queryParam(TestDataReader.getTestData(QUERY_PARAMETER_KEY), TestDataReader.getTestData(QUERY_PARAMETER_VALUE))
        .queryParam(TestDataReader.getTestData(QUERY_PARAMETER_TOKEN), TestDataReader.getTestData(QUERY_PARAMETER_TOKEN_VALUE))
        .body(userData)
        .put(BOARDS + boardsId + NAME + expectedBoardsChangesName)
        .then()
        .statusCode(200)
        .extract()
        .as(Board.class);
    SoftAssert softAssert = new SoftAssert();
    assertEquals(actualChangesBoard.getName(), expectedBoardsChangesName,
        "Changes boards name isn't equal expected!");
    softAssert.assertEquals(actualNewBoard.getId(), actualChangesBoard.getId(),
        "New boards id isn't equals with changed boards Id!");
    softAssert.assertAll();
  }

  @AfterTest
  public void deleteBoard() {
    given()
        .log()
        .method()
        .queryParam(TestDataReader.getTestData(QUERY_PARAMETER_KEY), TestDataReader.getTestData(QUERY_PARAMETER_VALUE))
        .queryParam(TestDataReader.getTestData(QUERY_PARAMETER_TOKEN), TestDataReader.getTestData(QUERY_PARAMETER_TOKEN_VALUE))
        .body(userData)
        .delete(BOARDS + boardsId)
        .then()
        .statusCode(200);
  }
}
