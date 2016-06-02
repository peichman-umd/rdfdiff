# rdfdiff

Command-line RDF diff utility

## Quick Start

```bash
mvn clean package
java -jar target/rdfdiff-0.0.1-SNAPSHOT-jar-with-dependencies.jar a.ttl b.ttl
# with a base URI of <http://example.com/foo/>
java -jar target/rdfdiff-0.0.1-SNAPSHOT-jar-with-dependencies.jar a.ttl b.ttl http://example.com/foo/
```
