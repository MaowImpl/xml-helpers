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
import org.dom4j.Document;
import org.dom4j.Element;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * A wrapper around {@link Attribute} that allows for null-safe manipulation.
 *
 * @author Maow
 * @see SafeDocument
 * @see SafeElement
 * @since 1.0.0
 */
public class SafeAttribute implements SafeNode<Attribute> {
    private final Attribute attribute;

    private SafeAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public static SafeAttribute from(Attribute attribute) {
        return new SafeAttribute(attribute);
    }

    public static SafeAttribute from(Element element, String name) {
        if (element != null)
            return SafeAttribute.from(
                    element.attribute(name));
        return SafeAttribute.empty();
    }

    public static SafeAttribute from(Document document, String name) {
        if (document != null) {
            final Element element = document.getRootElement();
            if (element != null)
                return SafeAttribute.from(element.attribute(name));
        }
        return SafeAttribute.empty();
    }

    public static SafeAttribute empty() {
        return new SafeAttribute(null);
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

    @Override
    public Optional<Attribute> get() {
        return Optional.ofNullable(attribute);
    }

    @Override
    public void get(Consumer<Attribute> consumer) {
        if (attribute != null) consumer.accept(attribute);
    }

    @Override
    public boolean isEmpty() {
        return attribute == null;
    }

    @Override
    public String toString() {
        return (attribute != null)
                ? attribute.asXML()
                : "";
    }
}
