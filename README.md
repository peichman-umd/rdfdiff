# rdfdiff

Command-line RDF diff utility

## Quick Start

```bash
mvn clean package
java -jar target/rdfdiff-0.0.1-SNAPSHOT-jar-with-dependencies.jar a.ttl b.ttl
# with a base URI of <http://example.com/foo/>
java -jar target/rdfdiff-0.0.1-SNAPSHOT-jar-with-dependencies.jar a.ttl b.ttl http://example.com/foo/
```

## History

The core of this tool was initially developed for the [SESYNC](https://www.sesync.org)
ontology services project.
