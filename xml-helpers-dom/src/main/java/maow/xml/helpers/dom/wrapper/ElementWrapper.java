package maow.xml.helpers.dom.wrapper;

import org.dom4j.Attribute;
import org.dom4j.Element;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A null-safe wrapper class for accessing and editing a DOM element.
 *
 * @author Maow
 * @since 1.0.0
 */
public class ElementWrapper {
    private final Element element;

    private ElementWrapper(Element element) {
        this.element = element;
    }

    public static ElementWrapper from(Element element) {
        return new ElementWrapper(element);
    }

    public static ElementWrapper empty() {
        return new ElementWrapper(null);
    }

    public String name() {
        return (element != null)
                ? element.getName()
                : "";
    }

    public String text(boolean trim) {
        if (element != null) {
            return (trim)
                    ? element.getTextTrim()
                    : element.getText();
        }
        return "";
    }

    public String text() {
        return text(false);
    }

    public AttributeWrapper attribute(String name) {
        if (element != null) {
            final Attribute attribute = element.attribute(name);
            return AttributeWrapper.from(attribute);
        }
        return AttributeWrapper.empty();
    }

    public AttributeWrapper attribute(int index) {
        if (element != null) {
            final Attribute attribute = element.attribute(index);
            return AttributeWrapper.from(attribute);
        }
        return AttributeWrapper.empty();
    }

    public List<AttributeWrapper> attributes() {
        if (element != null) {
            return element.attributes()
                    .stream()
                    .map(AttributeWrapper::from)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public ElementWrapper element(String name) {
        if (element != null) {
            final Element element = this.element.element(name);
            return ElementWrapper.from(element);
        }
        return ElementWrapper.empty();
    }

    public ElementWrapper element() {
        if (element != null) {
            return elements().get(0);
        }
        return ElementWrapper.empty();
    }

    public List<ElementWrapper> elements(String name) {
        if (element != null) {
            return element.elements(name)
                    .stream()
                    .map(ElementWrapper::from)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public List<ElementWrapper> elements() {
        if (element != null) {
            return element.elements()
                    .stream()
                    .map(ElementWrapper::from)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public void setName(String name) {
        if (element != null) element.setName(name);
    }

    public void setText(String text) {
        if (element != null) element.setText(text);
    }

    public void addAttribute(String name, String value) {
        if (element != null) element.addAttribute(name, value);
    }

    public void addAttribute(String name) {
        addAttribute(name, "");
    }

    public ElementWrapper addElement(String name) {
        if (element != null) {
            final Element element = this.element.addElement(name);
            return ElementWrapper.from(element);
        }
        return ElementWrapper.empty();
    }

    public ElementWrapper addElement(String name, String text) {
        final ElementWrapper wrapper = addElement(name);
        wrapper.setText(text);
        return wrapper;
    }

    public Stream<Element> stream() {
        return (element != null)
                ? Stream.of(element)
                : Stream.empty();
    }

    public Optional<Element> get() {
        return Optional.ofNullable(element);
    }

    public boolean isEmpty() {
        return element == null;
    }
}