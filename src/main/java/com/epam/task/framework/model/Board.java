package com.epam.task.framework.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_EMPTY)
public class Board {
  @JsonProperty("id")
  private String id;
  @JsonProperty("name")
  private String name;
  @JsonProperty("desc")
  private String desc;
  @JsonProperty("closed")
  private boolean closed;
  @JsonProperty("idOrganization")
  private String idOrganization;
  @JsonProperty("idEnterprise")
  private Object idEnterprise;
  @JsonProperty("pinned")
  private boolean pinned;
  @JsonProperty("url")
  private String url;
  @JsonProperty("shortUrl")
  private String shortUrl;

  public Board() {
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDesc() {
    return desc;
  }

  public boolean isClosed() {
    return closed;
  }

  public String getIdOrganization() {
    return idOrganization;
  }

  public Object getIdEnterprise() {
    return idEnterprise;
  }

  public boolean isPinned() {
    return pinned;
  }

  public String getUrl() {
    return url;
  }

  public String getShortUrl() {
    return shortUrl;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Board)) {
      return false;
    }
    Board board = (Board) o;
    return isClosed() == board.isClosed() && isPinned() == board.isPinned()
        && Objects.equals(getId(), board.getId()) && Objects.equals(getName(),
        board.getName()) && Objects.equals(getDesc(), board.getDesc())
        && Objects.equals(getIdOrganization(), board.getIdOrganization())
        && Objects.equals(getIdEnterprise(), board.getIdEnterprise())
        && Objects.equals(getUrl(), board.getUrl()) && Objects.equals(
        getShortUrl(), board.getShortUrl());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getName(), getDesc(), isClosed(), getIdOrganization(),
        getIdEnterprise(), isPinned(), getUrl(), getShortUrl());
  }

  @Override
  public String toString() {
    return "Board{" +
        "id='" + id + '\'' +
        ", name='" + name + '\'' +
        ", desc='" + desc + '\'' +
        ", closed=" + closed +
        ", idOrganization='" + idOrganization + '\'' +
        ", idEnterprise=" + idEnterprise +
        ", pinned=" + pinned +
        ", url='" + url + '\'' +
        ", shortUrl='" + shortUrl + '\'' +
        '}';
  }
}
