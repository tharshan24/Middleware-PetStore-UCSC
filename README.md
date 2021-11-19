# PetStore Application - Middleware - UCSC

### Balachandran Priyatharshan

### 18001262

### 2018/CS/126

## Requirements

- Java Version 11.0 or above
- Docker Desktop
- Docker Compose
- GraalVM (https://www.graalvm.org/docs/getting-started/)
- Gradle
- GIT

## Import Application to System

    git clone https://github.com/tharshan24/Middleware-PetStore-UCSC.git

## Database config

In application properties

    quarkus.datasource.db-kind=h2
    quarkus.datasource.username=username-default
    quarkus.datasource.jdbc.url=jdbc:h2:mem:petStore
    quarkus.datasource.jdbc.max-size=13

By default H2 memory data base is configured. Change the database as per needs (https://quarkus.io/guides/datasource)

## Packaging and running the application

If you want to build an _??ber-jar_, execute the following command:

    ./gradlew build -Dquarkus.package.type=uber-jar

To run the application:

    java -jar build/petstore-runner.jar

The application can be also packaged using simple:

    ./gradlew build

It produces the `quarkus-run.jar` file in the `build/quarkus-app/` directory.
Be aware that it is not an _??ber-jar_ as the dependencies are copied into the `build/quarkus-app/lib/` directory.

To launch the test page, open your browser at the following URL

    http://localhost:8080/index.html

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

    ./gradlew quarkusDev

> **_NOTE:_** Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Creating a native executable

Mind having GRAALVM_HOME set to your Mandrel or GraalVM installation. If you didn't follow this

Point the PATH environment variable to the GraalVM bin directory:

    export PATH=/Library/Java/JavaVirtualMachines/<graalvm>/Contents/Home/bin:$PATH

Set the JAVA_HOME environment variable to resolve to the GraalVM installation directory:

    export JAVA_HOME=/Library/Java/JavaVirtualMachines/<graalvm>/Contents/Home

You can create a native executable using:

    ./gradlew build -Dquarkus.package.type=native

Or, if you don't have [Mandrel](https://github.com/graalvm/mandrel/releases/) or
[GraalVM](https://github.com/graalvm/graalvm-ce-builds/releases) installed, you can run the native executable
build in a container using:

    ./gradlew build -Dquarkus.package.type=native -Dquarkus.native.container-build=true

Or to use Mandrel distribution:

    ./gradlew build -Dquarkus.package.type=native -Dquarkus.native.container-build=true -Dquarkus.native.builder-image=quay.io/quarkus/ubi-quarkus-mandrel:20.3-java11

You can then execute your native executable with:

    ./build/petstore-runner

## Testing

The system will run in http://localhost:8080/v1/

To test the cystem with data use Postman (https://www.postman.com/downloads/)

Select a Pet (GET)

    http://localhost:8080/v1/pets/<id of the pet>

Select all pets (GET)

    http://localhost:8080/v1/pets/

Insert a pet (POST)

    http://localhost:8080/v1/pets/

    //raw input
    {
        "petAge":<age of the pet>,
        "petName":"<Name of the pet>",
        "petTypeId:{
            "petTypeId":<pet type id>,
            "petTypeName":"<name of the pet type>"
        }
    }

Update a pet (PUT)

    http://localhost:8080/v1/pets/

    //raw input
    {
        "petId":<id of the pet>,
        "petAge":<age of the pet>,
        "petName":"<Name of the pet>",
        "petTypeId:{
            "petTypeId":<pet type id>,
            "petTypeName":"<name of the pet type>"
        }
    }

Delete a pet (DELETE)

    http://localhost:8080/v1/pets/<id of the pet>

Search a pet by age

    http://localhost:8080/v1/pets/age/<id of the pet>

Search a pet by name

    http://localhost:8080/v1/pets/name/<id of the pet>

---

Select a Pet Type (GET)

    http://localhost:8080/v1/types/<id of the pet type>

Select all pet types (GET)

    http://localhost:8080/v1/types/

Insert a pet type (POST)

    http://localhost:8080/v1/types/

    //raw input
    {
        "petTypeName":"<name of the pet type>",
    }

Update a pet type (PUT)

    http://localhost:8080/v1/types/

    //raw input
    {
        "petTypeId":<pet type id>,
        "petTypeName":"<name of the pet type>",
    }

Delete a pet type (DELETE)

    http://localhost:8080/v1/types/<id of the pet type>

---

Other Tests (In browser)

    http://localhost:8080/config/injected
    http://localhost:8080/config/lookup

    //Health Test
    http://localhost:8080/health/live

    http://localhost:8080/metric/timed
    http://localhost:8080/metrics

## Deployment

- Run

        docker-compose up -d

- Open http://localhost:3000/ and use admin:admin credentials
- Navigate into http://localhost:3000/dashboards
- Open Quarkus Microprofile Metrics dashboard
