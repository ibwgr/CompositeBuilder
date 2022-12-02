package ch.ibw;

import ch.ibw.HtmlDocumentBuilder;
import ch.ibw.HtmlNode;
import ch.ibw.HtmlNodeFactory;
import com.mscharhag.oleaster.runner.OleasterRunner;
import org.junit.runner.RunWith;

import static com.mscharhag.oleaster.matcher.Matchers.expect;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.describe;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.it;

@RunWith(OleasterRunner.class)
public class HtmlDocumentBuilderTest {{

  describe("Single node", () -> {
    it("should be possible to build a single node with doctype", () -> {
     HtmlDocumentBuilder builder = HtmlDocumentBuilder.create("html5");
     HtmlNode node = HtmlNode.create("node");
     builder.setRoot(node);

     String html = builder.generateHtml();
     expect(html).toEqual("xhtml5\n<node></node>");
    });

    it("should be possible to build an empty document", () -> {
      HtmlDocumentBuilder builder = HtmlDocumentBuilder.create(null);

      String html = builder.generateHtml();
      expect(html).toEqual("");
    });

    it("should be possible to build a document with just a doctype", () -> {
      HtmlDocumentBuilder builder = HtmlDocumentBuilder.create("html5");

      String html = builder.generateHtml();
      expect(html).toEqual("html5\n");
    });

    it("should be possible to build a document with just one node", () -> {
      HtmlDocumentBuilder builder = HtmlDocumentBuilder.create("");

      HtmlNode node = HtmlNode.create("node");
      builder.setRoot(node);

      String html = builder.generateHtml();
      expect(html).toEqual("<node></node>");
    });

    it("should be possible to build a single node with single attributes", () -> {
      HtmlDocumentBuilder builder = HtmlDocumentBuilder.create("");
      HtmlNode node = HtmlNode.create("node");
      node.setAttribute("key", "value");
      node.setAttribute("key1", "value1");
      builder.setRoot(node);

      String html = builder.generateHtml();
      expect(html).toEqual("<node key='value' key1='value1'></node>");
    });
  });

  describe("Composition", () -> {
    it("should be possible to create one level nested html", () -> {
      HtmlDocumentBuilder builder = HtmlDocumentBuilder.create("html5");

      HtmlNode root = HtmlNode.create("html");
      root.addChild(HtmlNodeFactory.createA("about:blank"));

      builder.setRoot(root);
      String html = builder.generateHtml();

      expect(html).toEqual("html5\n<html><a href='about:blank'></a></html>");
    });
  });
}}
