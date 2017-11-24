public class HtmlNodeWriter {
  private static final String TAG_OPEN = "<";
  private static final String TAG_CLOSE = ">";
  private static final String CLOSING_TAG_OPEN = "</";
  private static final String BLANK = " ";

  public static HtmlNodeWriter create(){
    return new HtmlNodeWriter();
  }

  public String write(AttributedCompositeNode node){
    return writeStartTag(node)
            .append(writeChildren(node))
            .append(writeEndTag(node))
            .toString();
  }

  protected HtmlNodeWriter getWriter(){
    return HtmlNodeWriter.create();
  }

  protected StringBuilder writeChildren(AttributedCompositeNode node) {
    StringBuilder children = new StringBuilder();
    node.children.stream().forEach((child) -> children.append(getWriter().write(child)));
    return children;
  }

  protected StringBuilder writeStartTag(AttributedCompositeNode node){
    return new StringBuilder()
            .append(TAG_OPEN)
            .append(node.name)
            .append(writeAttributes(node))
            .append(TAG_CLOSE);
  }

  protected StringBuilder writeEndTag(AttributedCompositeNode node){
    return new StringBuilder()
            .append(CLOSING_TAG_OPEN)
            .append(node.name)
            .append(TAG_CLOSE);
  }

  protected StringBuilder writeAttributes(AttributedCompositeNode node) {
    StringBuilder attributes = new StringBuilder();
    node.attributes.entrySet().stream().forEach((e) ->
          attributes.append(new StringBuilder(BLANK)
            .append(e.getKey())
            .append("='")
            .append(e.getValue())
            .append("'").toString()));
    return attributes;
  }
}