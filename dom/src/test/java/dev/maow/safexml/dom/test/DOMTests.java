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
package dev.maow.safexml.dom.test;

import dev.maow.safexml.dom.SafeDocument;
import dev.maow.safexml.dom.SafeElement;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(OrderAnnotation.class)
final class DOMTests {
    static SafeDocument document;

    @BeforeAll
    static void init() {
        document = SafeDocument.empty();
    }

    @Test
    @Order(1)
    @DisplayName("Create Document")
    void createDocument() {
        document = SafeDocument.create();
        assertFalse(document.isEmpty());
    }

    @Test
    @Order(2)
    @DisplayName("Create Root Element")
    void createRootElement() {
        final SafeElement root = document.setRoot("Root");
        assertEquals(root.name(), "Root");
    }

    @Test
    @DisplayName("Create Element")
    void createElement() {
        final SafeElement element = document.root()
                .addElement("Element");
        assertFalse(element.isEmpty());
    }

    @Test
    @DisplayName("Create Element w/ Text")
    void createElementWithText() {
        final SafeElement element = document.root()
                .addElement("Text", "value");
        assertEquals(element.text(), "value");
    }

    @Test
    @DisplayName("Create Element w/ Attribute")
    void createElementWithAttributes() {
        final SafeElement element = document.root()
                .addElement("Attribute")
                .addAttribute("attribute", "value");
        assertEquals(element
                .attribute("attribute")
                .value(), "value");
    }

    @AfterEach
    void printDocument() {
        System.out.println(document);
    }
}