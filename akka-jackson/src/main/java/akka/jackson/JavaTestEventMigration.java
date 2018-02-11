/*
 * Copyright (C) 2016-2018 Lightbend Inc. <https://www.lightbend.com>
 */
package akka.jackson;

import com.fasterxml.jackson.databind.node.IntNode;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.JsonNode;
import static akka.jackson.JavaTestMessages.*;

public class JavaTestEventMigration extends JacksonMigration {

  @Override
  public int currentVersion() {
    return 3;
  }

  @Override
  public String transformClassName(int fromVersion, String className) {
    return Event2.class.getName();
  }

  @Override
  public JsonNode transform(int fromVersion, JsonNode json) {
    ObjectNode root = (ObjectNode) json;
    root.set("field1V2", root.get("field1"));
    root.remove("field1");
    root.set("field2", IntNode.valueOf(17));
    return root;
  }

}
