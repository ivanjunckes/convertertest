package com.test;

import org.apache.johnzon.jaxrs.JohnzonProvider;
import org.apache.johnzon.mapper.Adapter;
import org.apache.johnzon.mapper.MapperBuilder;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.ext.Provider;
import java.util.List;

import static java.util.Arrays.asList;

@Provider
@Produces("application/json")
@Consumes("application/json")
public class CustomDateProvider extends JohnzonProvider {
    private static MapperBuilder builder = new MapperBuilder();
    private static List<String> ignores;

    public void setIgnores(final String ignores) {
        this.ignores = ignores == null ? null : asList(ignores.split(" *, *"));
    }

    public void setAdapter(final String adapter) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        final String[] parts = adapter.split("/");
        if (parts.length != 3) {
            throw new IllegalArgumentException(String.format("Adapter %s is invalid. It must be composed of 3 " +
                    "parts from/to/FQCN", adapter));
        }

        final Class<?> from = Thread.currentThread().getContextClassLoader().loadClass(parts[0]);
        final Class<?> to = Thread.currentThread().getContextClassLoader().loadClass(parts[1]);
        final Class<?> adapterClass = Thread.currentThread().getContextClassLoader().loadClass(parts[2]);

        builder.addAdapter(from, to, (Adapter<?, ?>) adapterClass.newInstance());
    }

    public CustomDateProvider() {
        super(builder.build(), ignores);
    }

}
