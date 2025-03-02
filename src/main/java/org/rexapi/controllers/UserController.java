package org.rexapi.controllers;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.rexapi.models.user.UserDTO;
import org.rexapi.services.UserService;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Usuarios", description = "Gestión de usuarios")
public class UserController {

    @Inject
    UserService userService;

    @POST
    @Operation(summary = "Crear un nuevo usuario", description = "Registra un usuario en la base de datos")
    @APIResponse(responseCode = "200", description = "Usuario creado exitosamente")
    @APIResponse(responseCode = "400", description = "Error en la solicitud", content = @Content(schema = @Schema(implementation = String.class)))
    public void createUser(UserDTO dto) {
        userService.crearUsuario(dto);
    }
}

