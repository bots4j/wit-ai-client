A simple Java client for the Wit.ai Bot Framework
====================================================

Work in progress.

Provides a Java REST client api for [https://wit.ai/docs/http/20160330]

Key Features
------------
	
-	Initial support for Converse and Get Intent via Text

Converse Usage
-------------
 
    WitClient client = new WitClient(WIT_SERVER_ACCESS_TOKEN);

    String message = "i need to go to stockholm on monday and come back on wednesday";
    WitContext ctx = new WitContext();

    ConverseResponse response = client.converse("test-converse-id1",message,ctx);
    assertEquals("merge",response.getType());

    String intent = response.getEntities().firstEntityValue("intent");
    assertEquals("book-trip", intent);

    if ( response.getEntities().containsKey("location") ){
        ctx.put("destination",response.getEntities().firstEntityValue("location"));
    }

    List<String> dates = response.getEntities().entityValues("datetime");
    if ( dates != null && !dates.isEmpty() ){
        ctx.put("arrivalDate", dates.get(0));
        if ( dates.size() > 1 ){
            ctx.put("returnDate", dates.get(1));
        }
    }

    response = client.converse("test-converse-id1",null,ctx);
    assertEquals("msg",response.getType());

    response = client.converse("test-converse-id1",null,ctx);
    assertEquals("action",response.getType());

    assertEquals("findFlights", response.getAction());
    
Get Intent via Text Usage
-------------------------
    
    WitClient client = new WitClient(WIT_SERVER_ACCESS_TOKEN);

    String message = "i want to be in paris on the 3rd of may?";

    GetIntentViaTextResponse response = client.getIntentViaText(message,null,null,null,null);
    assertNotNull(response.getOutcomes());

    Outcome outcome = response.getOutcomes().get(0);
    assertEquals("book-trip", outcome.getEntities().firstEntityValue("intent"));
    assertEquals("paris", outcome.getEntities().firstEntityValue("location"));
	
Maven Config
============

Add this repo:

    <repositories>
        <repository>
            <id>bintray-bots4j-maven-repo</id>
            <name>bintray</name>
            <url>http://dl.bintray.com/bots4j/maven-repo</url>
        </repository>
    </repositories>

Add this dependency:

    <dependency>
      <groupId>org.bots4j</groupId>
      <artifactId>wit-ai-client</artifactId>
      <version>0.01</version>
    </dependency>
    


Questions and Issues
--------------------
If you have a question about this project, or are experiencing a problem you're not sure is a bug please post a message to the
[Bots4J mailing list](https://groups.google.com/forum/#!forum/bots4j).

On the other hand if you're pretty certain you've found a bug please open an issue.

Contributing
------------
We welcome bug fixes and new features in the form of pull requests. If you'd like to contribute, please be mindful of the
following guidelines:
* All changes should include suitable tests, whether to demonstrate the bug or exercise and document the new feature.
* Please make one change per pull request.
* If the new feature is significantly large/complex/breaks existing behaviour, please first post a summary of your idea
on the mailing list to generate a discussion. This will avoid significant amounts of coding time spent on changes that ultimately get rejected.
* Try to avoid reformats of files that change the indentation, tabs to spaces etc., as this makes reviewing diffs much
more difficult.
