/*
 * MIT License
 *
 * Copyright (c) 2021 Maow
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package dev.maow.safexml.dom;

import org.dom4j.Attribute;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * A wrapper around {@link Element} that allows for null-safe manipulation.
 *
 * @author Maow
 * @see SafeDocument
 * @see SafeAttribute
 * @since 1.0.0
 */
public class SafeElement implements SafeNode<Element> {
    private final Element element;

    private SafeElement(Element element) {
        this.element = element;
    }

    public static SafeElement from(Element element) {
        return new SafeElement(element);
    }

    public static SafeElement empty() {
        return new SafeElement(null);
    }

    public String name() {
        return (element != null)
                ? element.getName()
                : "";
    }

    public String textTrim() {
        if (element != null)
            return element.getTextTrim();
        return "";
    }

    public String text() {
        if (element != null)
            return element.getText();
        return "";
    }

    public SafeAttribute attribute(String name) {
        if (element != null)
            return SafeAttribute.from(
                    element.attribute(name));
        return SafeAttribute.empty();
    }

    public SafeAttribute attribute(int index) {
        if (element != null)
            return SafeAttribute.from(
                    element.attribute(index));
        return SafeAttribute.empty();
    }

    public List<SafeAttribute> attributes() {
        if (element != null) {
            final List<SafeAttribute> attributes = new ArrayList<>();
            for (Attribute attribute : element.attributes())
                attributes.add(SafeAttribute.from(attribute));
            return attributes;
        }
        return Collections.emptyList();
    }

    public SafeElement element(String name) {
        if (element != null)
            return SafeElement.from(
                    element.element(name));
        return SafeElement.empty();
    }

    public SafeElement element() {
        if (element != null)
            return elements().get(0);
        return SafeElement.empty();
    }

    public List<SafeElement> elements(String name) {
        if (element != null) {
            final List<SafeElement> elements = new ArrayList<>();
            for (Element element : element.elements(name))
                elements.add(SafeElement.from(element));
            return elements;
        }
        return Collections.emptyList();
    }

    public List<SafeElement> elements() {
        if (element != null) {
            final List<SafeElement> elements = new ArrayList<>();
            for (Element element : element.elements())
                elements.add(SafeElement.from(element));
            return elements;
        }
        return Collections.emptyList();
    }

    public void setName(String name) {
        if (element != null) element.setName(name);
    }

    public void setText(String text) {
        if (element != null) element.setText(text);
    }

    public SafeElement addAttribute(String name, String value) {
        if (element != null)
            return SafeElement.from(
                    element.addAttribute(name, value));
        return SafeElement.empty();
    }

    public SafeElement addAttribute(String name) {
        return addAttribute(name, "");
    }

    public SafeElement addElement(String name) {
        if (element != null)
            return SafeElement.from(
                    element.addElement(name));
        return SafeElement.empty();
    }

    public SafeElement addElement(String name, String text) {
        final SafeElement wrapper = addElement(name);
        wrapper.setText(text);
        return wrapper;
    }

    @Override
    public Optional<Element> get() {
        return Optional.ofNullable(element);
    }

    @Override
    public void get(Consumer<Element> consumer) {
        if (element != null) consumer.accept(element);
    }

    @Override
    public boolean isEmpty() {
        return element == null;
    }

    @Override
    public String toString() {
        return (element != null)
                ? element.asXML()
                : "";
    }
}