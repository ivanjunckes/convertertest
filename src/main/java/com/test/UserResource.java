package com.test;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ApplicationScoped
@Path("users")
public class UserResource {

    private static List<User> USERS = new ArrayList<>();

    static {
        User user = new User(1L, new Date());
        USERS.add(user);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {
        return Response.ok(USERS).build();
    }
}
