package io.sited.page.web.service.component;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.sited.template.Attributes;
import io.sited.template.Children;
import io.sited.template.ObjectAttribute;
import io.sited.web.AbstractWebComponent;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

/**
 * @author chi
 */
public class PageListComponent extends AbstractWebComponent {
    public PageListComponent() {
        super("page-list", "component/page-list/page-list.html", Lists.newArrayList(
            new ObjectAttribute<>("pages", Iterable.class, null)
        ));
    }

    @Override
    public void output(Map<String, Object> bindings, Attributes attributes, Children children, OutputStream out) throws IOException {
        Map<String, Object> componentBindings = Maps.newHashMap();
        componentBindings.putAll(attributes);
        componentBindings.putAll(bindings);
        template().output(componentBindings, out);
    }
}
