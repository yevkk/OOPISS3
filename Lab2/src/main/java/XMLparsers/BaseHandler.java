package XMLparsers;

import org.xml.sax.helpers.DefaultHandler;

import java.util.List;

public abstract class BaseHandler extends DefaultHandler {
    public abstract List<?> getList();
}
