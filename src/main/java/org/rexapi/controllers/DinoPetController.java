package org.rexapi.controllers;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.rexapi.models.dinopet.DinoPetDTO;
import org.rexapi.services.DinoPetService;

@Path("/dinopet")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "DinoPet", description = "Gestión de mascotas")
public class DinoPetController {

    @Inject
    DinoPetService dinoPetService;

    @GET
    @Path("/{id}")
    @Operation(summary = "Obtener mascota", description = "Obtiene una mascota por ID de usuario")
    @APIResponse(responseCode = "200", description = "Mascota obtenida exitosamente",
            content = @Content(schema = @Schema(implementation = DinoPetDTO.class)))
    @APIResponse(responseCode = "400", description = "Error en la solicitud",
            content = @Content(schema = @Schema(implementation = String.class)))
    @APIResponse(responseCode = "404", description = "Mascota no encontrada",
            content = @Content(schema = @Schema(implementation = String.class)))
    public DinoPetDTO getById(@PathParam("id") Long id) {
        return dinoPetService.transformDinoPetToDTO(id);
    }
}
