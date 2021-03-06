# jpa2-integration-tests

[![Build Status](https://travis-ci.org/thinkinglabs/jpa2-integration-tests.svg?branch=master)](https://travis-ci.org/thinkinglabs/jpa2-integration-tests)

This is an example on how you can test your JPA2 annotations against your real database schema taking schema migrations into account.

The integration tests first applies your database migrations against an in-memory database and
then runs the test.

The example uses a lousy model of a company with employees. I would like to use a more complete model. Any
better ideas are welcome.

## How to build

The project was started at the time I didn't know better, so unfortunately it uses
[Apache Maven](https://maven.apache.org/) for building. There are plans to change the build tool.

To build and run the tests:

```bash
mvn clean install
```

## Database migrations

The database migrations are expressed using [Liquibase](http://www.liquibase.org/). For the definition,
I used [YAML](http://yaml.org/) which seemed a good idea, but I am a bit disappointed in the result. In my opinion
the XML specification is far more expressive then the YAML version. And if you have an IDE that can handle the XML Schemas
(which most can today), it is far more productive to specify it using XML as your IDE helps you with the completion (which is not
happening with YAML).

## Credits

The concept of how to test your JPA2 annotations is explained in the [GOOS](http://www.growing-object-oriented-software.com/)
book by [Nat Pryce](http://www.natpryce.com/) and [Steve Freeman](http://higherorderlogic.com/).
