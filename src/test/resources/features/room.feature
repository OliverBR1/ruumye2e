Feature: List Rooms

  As a costumer,
  I want to know all the available rooms
  so that I can book them

  Scenario Outline: List rooms
    When I list all the rooms
    Then I detect that the room "<name>" exists

    Examples:
      | name   |
      | Sala América do Sul |
      | Sala Europa |
      | Sala América do Norte |
      | Sala África |