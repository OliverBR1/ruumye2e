Feature: Book a room

  As a customer,
  I want to book a room
  So that i can make a meeting

  Scenario:
    Given the room "Sala América do Sul" exists
    And the room has no bookings for today
    When I book the room for one hour from now
    Then then room should be successfully booked