package maow.xml.helpers.dom.wrapper;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * A null-safe wrapper class for accessing and editing a DOM document.
 *
 * @author Maow
 * @since 1.0.0
 */
public class DocumentWrapper {
    private final Document document;

    private DocumentWrapper(Document document) {
        this.document = document;
    }

    public static DocumentWrapper create() {
        final Document document = DocumentHelper.createDocument();
        return DocumentWrapper.from(document);
    }

    public static DocumentWrapper from(Document document) {
        return new DocumentWrapper(document);
    }

    public static DocumentWrapper from(File file) {
        final SAXReader sr = new SAXReader();
        Document document = null;
        try {
            document = sr.read(file);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return DocumentWrapper.from(document);
    }

    public static DocumentWrapper from(Path path) {
        final File file = path.toFile();
        return DocumentWrapper.from(file);
    }

    public static DocumentWrapper empty() {
        return new DocumentWrapper(null);
    }

    public ElementWrapper root() {
        if (document != null) {
            final Element element = document.getRootElement();
            return ElementWrapper.from(element);
        }
        return ElementWrapper.empty();
    }

    public ElementWrapper setRoot(String name) {
        if (document != null) {
            final Element element = document.addElement(name);
            return ElementWrapper.from(element);
        }
        return ElementWrapper.empty();
    }

    public ElementWrapper setRoot(String name, String text) {
        final ElementWrapper wrapper = setRoot(name);
        wrapper.setText(text);
        return wrapper;
    }

    public Stream<Document> stream() {
        return (document != null)
                ? Stream.of(document)
                : Stream.empty();
    }

    public Optional<Document> get() {
        return Optional.ofNullable(document);
    }

    public boolean isEmpty() {
        return document == null;
    }
}
