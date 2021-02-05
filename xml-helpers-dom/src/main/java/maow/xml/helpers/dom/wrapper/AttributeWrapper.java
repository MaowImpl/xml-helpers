package maow.xml.helpers.dom.wrapper;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * A null-safe wrapper class for accessing and editing a DOM attribute.
 *
 * @author Maow
 * @since 1.0.0
 */
public class AttributeWrapper {
    private final Attribute attribute;

    private AttributeWrapper(Attribute attribute) {
        this.attribute = attribute;
    }

    public static AttributeWrapper from(Attribute attribute) {
        return new AttributeWrapper(attribute);
    }

    public static AttributeWrapper from(Element element, String name) {
        if (element != null) {
            final Attribute attribute = element.attribute(name);
            return AttributeWrapper.from(attribute);
        }
        return AttributeWrapper.empty();
    }

    public static AttributeWrapper from(Document document, String name) {
        if (document != null) {
            final Element element = document.getRootElement();
            if (element != null) {
                final Attribute attribute = element.attribute(name);
                return AttributeWrapper.from(attribute);
            }
        }
        return AttributeWrapper.empty();
    }

    public static AttributeWrapper empty() {
        return new AttributeWrapper(null);
    }

    public String name() {
        return (attribute != null) ? attribute.getName() : "";
    }

    public String value() {
        return (attribute != null) ? attribute.getValue() : "";
    }

    public void setName(String name) {
        if (attribute != null) attribute.setName(name);
    }

    public void setValue(String text) {
        if (attribute != null) attribute.setValue(text);
    }

    public Stream<Attribute> stream() {
        return (attribute != null)
                ? Stream.of(attribute)
                : Stream.empty();
    }

    public Optional<Attribute> get() {
        return Optional.ofNullable(attribute);
    }

    public boolean isEmpty() {
        return attribute == null;
    }
}
