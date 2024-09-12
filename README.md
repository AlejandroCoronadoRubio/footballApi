**How to run the project:**

    mvn clean install
    
    mvn spring-boot:run

If you want to debug use the next line instead and attach a debugger using a Remote JVM Debug configuration

    mvn spring-boot:run "-Dspring-boot.run.jvmArguments=-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005"

**Requirements**

    Java 17
    
    Maven 3.8.1

Graphiql path on local: http://localhost:8080/graphiql?path=/graphql

H2 database path on local: http://localhost:8080/h2-console

When application started importLeague mutation endpoint needs to be called to populate H2 database data:

```
mutation {
  importLeague(leagueCode: "PD") {
    id
    name
    code
    areaName
    teams {
      id
      name
      tla
      shortName
      areaName
      address
      players {
        id
        name
        dateOfBirth
        nationality
        position
      }
      coach {
        id
        name
        dateOfBirth
        nationality
      }
    }
  }
}
```

Available querys:

```
query {
    players(leagueCode: "PD") {
    id
    name
    nationality
    position
    dateOfBirth
  }
}


query {
    team(name: "Villarreal CF") {
        id
        name
        tla
        shortName
        areaName
        address
        players {
            id
            name
            nationality
            position
            dateOfBirth
        }
        coach {
            id
            name
            nationality
            dateOfBirth
        }
    }
}


query {
    competitions {
        id
        name
        code
        areaName
        teams{
        id
        name
        tla
        shortName
        areaName
        address
          players {
              id
              name
              nationality
              position
              dateOfBirth
          }
          coach {
              id
              name
              nationality
              dateOfBirth
          }
        }
    }
}
```

Available league code:

    WC  | FIFA World Cup
    CL  | UEFA Champions League
    BL1 | Bundesliga
    DED | Eredivisie
    BSA | Campeonato Brasileiro Série A
    PD  | Primera Division
    FL1 | Ligue 1
    ELC | Championship
    PPL | Primeira Liga
    EC  | European Championship
    SA  | Serie A
    PL  | Premier League
    CLI | Copa Libertadores


This app uses a free token from https://www.football-data.org/