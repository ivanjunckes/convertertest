package com.test;

import org.apache.johnzon.jaxrs.JohnzonProvider;
import org.apache.ziplock.maven.Mvn;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URL;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(Arquillian.class)
public class UserResourceTest {

    @ArquillianResource
    private URL base;

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        final Archive<?> war = Mvn.war();
        return (WebArchive) war;
    }

    @Test
    public void testUsers() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        CustomDateProvider customDateProvider = new CustomDateProvider();
        customDateProvider.setAdapter("java.util.Date/java.lang.String/com.test.DateTimeAdapter");

        Client client = ClientBuilder.newClient().register(customDateProvider);
        WebTarget target = client.target(base.toExternalForm() + "/api");
        Response response = target
                .path("users")
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .get();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        List<User> user = response.readEntity(new GenericType<List<User>>(){});
        assertNotNull(user);
    }
}
