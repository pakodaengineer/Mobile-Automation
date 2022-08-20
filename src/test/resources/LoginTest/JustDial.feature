Feature:JustDail Flight booking

    Scenario: Book the return flight
        Given [SetUp] Launch the JustDail App
        Then Go the the Flight section and choose the round trip
        And Enter the Source and Designation
        Then Enter the Departure data of Tomorrow and Return date of three days from today
        And Click on the Continue Button and Select the flights
        Then Click on Book and then Continue
        And Verify the Login Screen appeared
        And Close the app