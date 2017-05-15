Feature: Search flights given a route

  Retrieve flights given a route

  @loadAirports
  @loadFlights
  @loadAirlines
  @loadPricingRules
  @CleanData
  Scenario Outline: Search flight by route
    Given a route from "<airportOriginCode>" to "<airportDestinationCode>" with <departureFromNowInDays> days until departure, Adults: <adultAmount>, Children: <childAmount>, and Infants: <infantAmount>
    When you perform a search
    Then you will get flight: "<expectedFlightCode>", price: <expectedPrice>, and the total number of flights retrieved is <expentedAmountOfRecords>

    Examples:
      | airportOriginCode | airportDestinationCode | departureFromNowInDays | adultAmount | childAmount | infantAmount | expectedFlightCode | expectedPrice | expentedAmountOfRecords |
      | BCN               | MAD                    | 17                     | 1           | 0           | 0            | IB2171             | 259           | 2                       |
      | BCN               | MAD                    | 19                     | 1           | 0           | 0            | LH5496             | 293           | 2                       |
      | AMS               | FRA                    | 31                     | 1           | 0           | 0            | TK2372             | 157.6         | 3                       |
      | AMS               | FRA                    | 31                     | 1           | 0           | 0            | TK2659             | 198.4         | 3                       |
      | AMS               | FRA                    | 31                     | 1           | 0           | 0            | LH5909             | 90.4          | 3                       |
      | LHR               | IST                    | 15                     | 2           | 1           | 1            | TK8891             | 806           | 2                       |
      | LHR               | IST                    | 15                     | 2           | 1           | 1            | LH1085             | 481.19        | 2                       |
      | BCN               | MAD                    | 2                      | 1           | 2           | 0            | IB2171             | 909.09        | 2                       |
      | BCN               | MAD                    | 2                      | 1           | 2           | 0            | LH5496             | 1028.43       | 2                       |
      | CDG               | FRA                    | 40                     | 1           | 2           | 0            | IB2171             | -1            | 0                       |
      | IST               | FRA                    | 1                      | 2           | 0           | 0            | IB8911             | 540           | 2                       |
      | IST               | FRA                    | 1                      | 1           | 0           | 0            | FR4727             | 162           | 2                       |
      | BCN               | AMS                    | 33                     | 2           | 1           | 1            | U24985             | 427.88        | 1                       |
      | MAD               | LHR                    | 45                     | 1           | 0           | 1            | TK4199             | 153.8         | 2                       |
      | MAD               | LHR                    | 45                     | 1           | 0           | 1            | IB4124             | 227.6         | 2                       |
