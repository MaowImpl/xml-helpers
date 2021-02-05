import maow.xml.helpers.dom.wrapper.DocumentWrapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

final class Tests {
    private static DocumentWrapper wrapper;

    private Tests() {}

    @BeforeAll
    static void init() {
        final Path path = Paths.get("Document.xml");
        if (Files.notExists(path)) fail("File does not exist.");
        wrapper = DocumentWrapper.from(path);
    }

    @Test
    @DisplayName("Document exists.")
    void document_exists() {
        assertFalse(wrapper.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = { "Doc" })
    @DisplayName("Root element name equals...")
    void root_element_name_equals(String name) {
        final String rootName = wrapper.root().name();
        assertEquals(name, rootName, "Root element name does not match one supplied by test.");
    }
}