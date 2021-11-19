package org.mpashka.totemftc.api;

import io.quarkus.security.Authenticated;
import io.smallrye.mutiny.Uni;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Path("/api")
@Authenticated
public class WebResourceTraining {

    private static final Logger log = LoggerFactory.getLogger(WebResourceTraining.class);

    @Inject
    WebSessionService webSessionService;

    @Inject
    DbUser dbUser;

    @Inject
    DbCrudTraining dbTraining;

    @GET
    @Path("trainers/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<DbUser.EntityUser[]> listTrainers() {
        return dbUser.getTrainers();
    }

    @GET
    @Path("training/types")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<DbCrudTraining.EntityTrainingType[]> listTrainingTypes() {
        return dbTraining.getTrainingTypes();
    }

    /**
     * todo [!] check this. How tf is this used.
     */
    @GET
    @Path("training/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<DbCrudTraining.Entity[]> list() {
        return dbTraining.getAll();
    }

    @GET
    @Path("training/byDate/{date}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<DbCrudTraining.Entity[]> byDate(LocalDate date) {
        return dbTraining.getByDate(date);
    }

    @GET
    @Path("userTraining/byDateInterval")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<DbCrudTraining.Entity[]> userTrainingsByDateInterval(@QueryParam("from") LocalDateTime from, @QueryParam("to") LocalDateTime to) {
        return dbTraining.getByDateInterval(from, to);
    }

    @GET
    @Path("masterTraining/byDateInterval")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<DbCrudTraining.Entity[]> masterTrainingsByDateInterval(@QueryParam("from") LocalDateTime from, @QueryParam("to") LocalDateTime to) {
        return dbTraining.getByDateInterval(from, to);
    }

    @DELETE
    @Path("training/{trainingId}")
    public Uni<Void> delete(@PathParam("trainingId") int trainerId) {
        return dbTraining.delete(trainerId);
    }

    @POST
    @Path("training")
    @Consumes(MediaType.APPLICATION_JSON)
    public Uni<Integer> create(DbCrudTraining.Entity training) {
        log.debug("Add training {}", training);
        return dbTraining.add(training);
    }

    @PUT
    @Path("training")
    @Consumes(MediaType.APPLICATION_JSON)
    public Uni<Void> update(DbCrudTraining.Entity training) {
        return dbTraining.update(training);
    }
}
