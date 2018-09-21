package org.rapidpm.lifttechnology.karma;

import java.time.LocalDateTime;

public class Event {
  private Long           elevatorID;
  private ElevatorStatus elevatorStatus;
  private LocalDateTime  timestamp;
  private String         message;

  public Event(Long elevatorID, ElevatorStatus elevatorStatus, LocalDateTime timestamp, String message) {
    this.elevatorID = elevatorID;
    this.elevatorStatus = elevatorStatus;
    this.timestamp = timestamp;
    this.message = message;
  }

  public Event() {
  }

  public Long getElevatorID() {
    return elevatorID;
  }

  public void setElevatorID(Long elevatorID) {
    this.elevatorID = elevatorID;
  }

  public ElevatorStatus getElevatorStatus() {
    return elevatorStatus;
  }

  public void setElevatorStatus(ElevatorStatus elevatorStatus) {
    this.elevatorStatus = elevatorStatus;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
