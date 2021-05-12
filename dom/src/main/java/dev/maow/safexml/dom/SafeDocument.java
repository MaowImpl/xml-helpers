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

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.SAXReader;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * A wrapper around {@link Document} that allows for null-safe manipulation.
 * <p>
 * It is possible to create an empty document using the {@link SafeDocument#create()} method,
 * as well as create a new safe document from an existing document using {@link SafeDocument#from}.
 *
 * @author Maow
 * @see SafeElement
 * @see SafeAttribute
 * @since 1.0.0
 */
public class SafeDocument implements SafeNode<Document> {
    private final Document document;

    private SafeDocument(Document document) {
        this.document = document;
    }

    public static SafeDocument create() {
        return from(DocumentHelper.createDocument());
    }

    public static SafeDocument from(Document document) {
        return new SafeDocument(document);
    }

    public static SafeDocument from(InputStream stream) {
        final SAXReader sr = new SAXReader();
        Document document = null;
        try {
            document = sr.read(stream);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return from(document);
    }

    public static SafeDocument from(Path path)
            throws IOException {
        return from(Files.newInputStream(path));
    }

    public static SafeDocument from(File file)
            throws FileNotFoundException {
        return from(new FileInputStream(file));
    }

    public static SafeDocument empty() {
        return new SafeDocument(null);
    }

    public SafeElement root() {
        if (document != null)
            return SafeElement.from(
                    document.getRootElement());
        return SafeElement.empty();
    }

    public SafeElement setRoot(String name) {
        if (document != null) {
//            final Element element = document.addElement(name);
//            document.setRootElement(element);
//            return SafeElement.from(element);
            return SafeElement.from(
                    document.addElement(name));
        }
        return SafeElement.empty();
    }

    public SafeElement setRoot(String name, String text) {
        final SafeElement wrapper = setRoot(name);
        wrapper.setText(text);
        return wrapper;
    }

    @Override
    public Optional<Document> get() {
        return Optional.ofNullable(document);
    }

    @Override
    public void get(Consumer<Document> consumer) {
        if (document != null) consumer.accept(document);
    }

    @Override
    public boolean isEmpty() {
        return document == null;
    }

    @Override
    public String toString() {
        return (document != null)
                ? document.asXML()
                : "";
    }
}