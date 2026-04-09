# Disaster Relief System

A Java-based disaster relief management system that allows relief workers to manage disaster victims, supplies, medical records, family relations, cultural requirements, and skills. Built with Java 21, JDBC, and PostgreSQL.

## Prerequisites

- Java 21
- PostgreSQL
- The following JAR files in the `lib/` directory:
  - `postgresql-42.7.3.jar`
  - `junit-4.13.2.jar`
  - `hamcrest-core-1.3.jar`

## Database Setup

1. Start PostgreSQL:

```bash
brew services start postgresql
```

2. Run the project SQL script as the postgres superuser:

```bash
psql -U postgres -h localhost -f IA2_instructions/project.sql
```

## Configuration

Database credentials are stored in `src/main/resources/config.txt`:

```
url=jdbc:postgresql://localhost/ensf380project
user=oop
password=ucalgary
```

## Compiling

From the project root directory:

```bash
javac -cp lib/postgresql-42.7.3.jar:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar -d out src/main/java/edu/ucalgary/oop/*.java
```

## Running

```bash
java -cp out:lib/postgresql-42.7.3.jar edu.ucalgary.oop.Main
```

## Running Tests

```bash
java -cp out:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore edu.ucalgary.oop.DisasterVictimTest
```

## Project Structure

```
30241636/
├── data/                              # Runtime log files (generated automatically)
├── lib/                               # JAR dependencies
├── src/main/java/edu/ucalgary/oop/   # Source files
├── src/main/resources/                # config.txt and .ser files
├── src/main/java/edu/ucalgary/oop/   # Unit tests (same folder as source)
└── IA2_DisasterRelief.pdf             # UML diagram
```

## Features

- Manage disaster victims (add, update, soft/hard delete)
- Manage supplies and allocate to victims
- View victims by location
- Log inquiries from family members
- Record medical treatments
- Track family relationships
- Manage cultural requirements (dietary, language, prayer, safe-space)
- Track victim skills (medical, language, trade)
- Automatic logging of all actions to `data/action_log.txt`
- Error logging to `data/errorlog.txt`

## Author

Azlan
