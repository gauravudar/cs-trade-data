# cs-trade-data

This project validates trade data against standard business validation as mentioned below.

Business requirements:
1. The following basic validation rules shall be implemented:

ALL:
- value date cannot be before trade date
- value date cannot fall on weekend or non-working day for currency
(candidate can use any public services e.g. http://fixer.io/ )
- if the counterparty (Customer) is one of the supported ones
- validate currencies if they are valid ISO codes (ISO 4217)
https://en.wikipedia.org/wiki/ISO_4217


SPOT, FORWARD:
- validate the value date against the product type


OPTIONS specific:
- the style can be either American or European
- American option style will have in addition the excerciseStartDate, which has to be after the trade date but before the expiry date
- expiry date and premium date shall be before delivery date

Input for validation will be in JSON format of trade data.

Output will be validation results with trade identifier and error validation message.

The project is build on Maven so one can import it using pom.xml and it uses spring boot so one can run it using maven goal mvn spring-boot:run.

For testing the project you can use POSTMAN.

The below link will help with validating the input json.

"localhost:8080/tradeData/validate"

