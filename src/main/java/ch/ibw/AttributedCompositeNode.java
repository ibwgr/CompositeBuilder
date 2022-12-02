package ch.ibw;

import ch.ibw.visitor.CompositeVisitor;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AttributedCompositeNode {
  public Map<String, Object> attributes;
  public List<AttributedCompositeNode> children;
  public AttributedCompositeNode parent;

  protected AttributedCompositeNode() {
    this.attributes = new LinkedHashMap<>();
    this.children = new ArrayList<>();
  }

  public
  static AttributedCompositeNode create
          (

          ) {
    return new AttributedCompositeNode();
  }

  public void accept(CompositeVisitor visitor) {
    visitor.visit(this);
  }

  public AttributedCompositeNode setAttribute(String key, String value) {
    attributes.put(key, value);
    return this;
  }

  public Boolean isLastChild(){
    if(parent == null){
      return true;
    }
    return parent.children.get(parent.children.size()-1).equals(this);
  }

  public AttributedCompositeNode addChild(AttributedCompositeNode child) {
    children.add(child);
    child.parent = this;
    return this;
  }
}
