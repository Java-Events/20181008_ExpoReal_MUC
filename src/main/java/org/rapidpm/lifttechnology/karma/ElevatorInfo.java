package org.rapidpm.lifttechnology.karma;

import java.time.LocalDateTime;

public class ElevatorInfo {

  private Long           elevatorID;
  private String         elevatorName;
  private ElevatorStatus elevatorStatus;
  private LocalDateTime  timestamp;


  public ElevatorInfo() {
  }

  public ElevatorInfo(Long elevatorID, String elevatorName, ElevatorStatus elevatorStatus, LocalDateTime timestamp) {
    this.elevatorID = elevatorID;
    this.elevatorName = elevatorName;
    this.elevatorStatus = elevatorStatus;
    this.timestamp = timestamp;
  }

  public Long getElevatorID() {
    return elevatorID;
  }

  public void setElevatorID(Long elevatorID) {
    this.elevatorID = elevatorID;
  }

  public String getElevatorName() {
    return elevatorName;
  }

  public void setElevatorName(String elevatorName) {
    this.elevatorName = elevatorName;
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

}
